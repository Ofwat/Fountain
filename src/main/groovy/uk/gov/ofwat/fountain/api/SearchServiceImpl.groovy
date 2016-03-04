package uk.gov.ofwat.fountain.api

import javax.mail.MethodNotSupportedException;
import javax.ws.rs.core.SecurityContext;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse
import org.elasticsearch.client.Client;

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import uk.gov.ofwat.fountain.audit.AuditExclusionStrategy
import uk.gov.ofwat.fountain.audit.FountainElasticSearchClient
import uk.gov.ofwat.fountain.dao.SearchDao;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.exception.SearchException;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;
import uk.gov.ofwat.fountain.search.FountainSearchIndex;
import uk.gov.ofwat.fountain.search.jsonEntityFactories.AbstractJsonEntityFactory;
import uk.gov.ofwat.fountain.search.jsonEntityFactories.JsonProducer;
import uk.gov.ofwat.fountain.util.LoggerPostInitialisationBean;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders.*;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import groovy.time.TimeCategory;


class SearchServiceImpl implements SearchService {

	AbstractJsonEntityFactory abstractJsonEntityFactory
	SearchDao searchDao
	Client client
	String clusterName
	String INDEX = "fountain"
	Gson gson
	private static Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class)
	
	public SearchServiceImpl(){
		gson = new GsonBuilder().setExclusionStrategies(new AuditExclusionStrategy()).create()
	}

	@Override
	public String put(Entity entity, Class type) throws SearchException {
		try{
			if(client == null){
				client = FountainElasticSearchClient.getInstance()
			}
	
			//turn the entity into appropriate JSON string.
			JsonProducer factory = abstractJsonEntityFactory.getJsonEntityFactory(type)
			def json = factory.getJson(entity, this.gson)
	
	
			//index, type
			IndexResponse response = client.prepareIndex(INDEX, factory.getElasticSearchType(), entity.id.toString())
					.setSource(json)
					.execute()
					.actionGet()
	
			return response.id
		}catch(Exception e){
			//Log the error and re-throw a SearchException. 
			logger.error("Unable to put item in search index: " + type.toString() + "With id: " + entity.id)
			logger.error(e.toString())
			throw new SearchException("Unable to place item in index")
		}
	}

	@Override
	public <T> Object get(Class<T> type, String id, String elasticSearchType) {
		if(client == null){
			client = FountainElasticSearchClient.getInstance()
		}
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(new AuditExclusionStrategy())
				.create()

		GetResponse response = client.prepareGet(INDEX, elasticSearchType, id)
				.execute()
				.actionGet()

		return gson.fromJson(response.sourceAsString, type);
	}

	@Override
	public <T> Object get(Class<T> type, String id) {
		return get(type, INDEX, id, type.toString())
	}

	public String searchReports(String query, int start, int maxRows, String sort, FountainSearchIndex index){
		//Search the reports index for all reports that contain the string in the title or description.
		if(client == null){
			client = FountainElasticSearchClient.getInstance()
		}
		
			//Parse the querystring to see if a date field is in the query - currently last_modified only. 
			def dateFields = ['last_modified']
			def foundDateRanges = []
			def range = false;
			
			dateFields.each{ df ->
				if(query.indexOf(df) >= 0){
					//Probably need to add the search contents.
					def d1 = (query =~ /$df:\[.+?\ /)
					if(d1){ //Do we have a range?
						range = true;			
						
						d1 = d1[0].substring(df.size()+2,d1[0].size()).trim()
						println "${d1}"
						
						def d2 = query =~ /$df:\[.*[0-9].+?\]/
		
						def toPos = d2[0].toLowerCase().indexOf('to')
						println toPos
						
						d2 = d2[0].substring(toPos+3,d2[0].size()-1).trim()
						println "${d2}"
						
						def from = Date.parse("dd/MM/yyyy", d1)
						def strFrom = from.format("yyyy-MM-dd'T'HH:mm:ssZZ")
						def to = Date.parse("dd/MM/yyyy", d2)
						def strTo = to.format("yyyy-MM-dd'T'HH:mm:ssZZ")
						
						def dates = [from: strFrom, to:strTo]
						foundDateRanges.add(dates)
					}else{
						d1 = (query =~ /$df:.+?\ /)
						println d1[0]
						d1 = d1[0].substring(df.size()+1, d1[0].size()).trim()
						def from = Date.parse("dd/MM/yyyy", d1)
						def strFrom = from.format("yyyy-MM-dd'T'HH:mm:ssZZ")
						
						def to = Date.parse("dd/MM/yyyy", d1)
						use (TimeCategory) {
							to = to + 1.day
						}
						def strTo = to.format("yyyy-MM-dd'T'HH:mm:ssZZ")
						
						def date = [from:strFrom, to:strTo]
						foundDateRanges.add(date) //Add this date + 24 hours as the to date.
						 
					}
					
				}
		}
		//Remove any dates from the query. 
		query = query.replaceAll(  /(and|or|^)\ *last_modified:\[.+?\]/, '')
		query = query.replaceAll(  /(and|or|^)\ *last_modified:\.+\ /, '')
		query = query.replaceAll(  /last_modified:.+?\ /, '')
		//If we start with an 'and', remove it. 
		if(query.size() > 3){
			if(query.trim().substring(0,3).toLowerCase().equals("and")){
				query = query.trim()
				query = query.substring(3,query.size())
			}
			
			if(query.trim().substring(0,2).toLowerCase().equals("or")){
				query = query.trim()
				query = query.substring(2,query.size())
			}
		}
		QueryBuilder qb = QueryBuilders.queryString(query)
		
		SearchRequestBuilder srb = client.prepareSearch(INDEX).setTypes(index.getSearchIndexName()).setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setQuery(qb)
		
		def strFrom
		def strTo
		if(foundDateRanges.size() > 0 && range){
			def date = foundDateRanges.getAt(0)
			strFrom = date["from"]
			strTo = date["to"]
			srb.setPostFilter(FilterBuilders.rangeFilter("lastModified").from(strFrom).to(strTo))
		}else if(foundDateRanges.size() > 0 && !range){
			def date = foundDateRanges.getAt(0)
			strFrom = date["from"]
			strTo = date["to"]
			srb.setPostFilter(FilterBuilders.rangeFilter("lastModified").from(strFrom).to(strTo))
		}
		
		srb.setFrom(start).setSize(maxRows)
				
		//Parse the sort. 
		//Expect it to be of the form  'lastModified:desc'
		if(sort != null && sort != "" && index == FountainSearchIndex.REPORT){ //We can only do this for the reports currently. 
			def searchComponents = sort.split(':')
			def sortField = searchComponents[0]
			def sortOrder
			if(searchComponents[1].equalsIgnoreCase("desc")){
				sortOrder = SortOrder.DESC
			}else{
				sortOrder = SortOrder.ASC
			}
			srb.addSort(sortField, sortOrder)
		}
		
		SearchResponse response = srb.execute().actionGet();
		return response.toString();
	}
	
	
	/**
	 * Add search to history. 
	 * @param securityContext
	 * @param saveSearchDto
	 * @return
	 */
	public Boolean addSearchToHistory(SecurityContext securityContext, SaveSearchDto saveSearchDto){
		def userName = securityContext.getUserPrincipal().name
		try{
			saveSearchDto.user = userName
			searchDao.saveSearchHistory(saveSearchDto)
			return true
		}catch(Exception e){
			//TODO Log the error!
			println e
			return false
		}
	}
	
	/**
	 * Add the search to a users set of favourite searches. 
	 * @param securityContext
	 * @param saveSearchDto
	 * @return
	 */
	public Boolean addSearchToFavorites(SecurityContext securityContext, SaveSearchDto saveSearchDto){
		def userName = securityContext.getUserPrincipal().name
		try{
			saveSearchDto.user = userName
			searchDao.saveSearchFavorite(saveSearchDto)
			return true
		}catch(Exception e){
			//TODO Log the error!
			println e
			return false
		}
	}
	
	/**
	 * Remove the search from a users set of favourites.
	 * @param securityContext
	 * @param saveSearchDto
	 * @return
	 */
	public boolean removeSearchFromFavorites(SecurityContext securityContext, Long id){
		try{
			SaveSearchDto searchDto = searchDao.getSearchDto(id)
			if(searchDto?.getUser() == securityContext.getUserPrincipal().name){
				//They can delete the search
				searchDao.deleteSearch(id)
				return true
			}else{
				//It's not there search - can't delete it. 
				return false
			}
		}catch(Exception e){
			//TODO Log the error!
			println e
			return false
		}	
	}
	
	/**
	 * Get the searches that a user has favorited. 
	 * @param maxQtyToReturn
	 * @param user
	 * @return
	 */
	public List<SaveSearchDto> getUserFavoriteSearches(int maxQtyToReturn, String user){
		return searchDao.getSearchFavoriteDtos(maxQtyToReturn, user);
	}
	
	/**
	 * Get the last X searches for a particular user (may be 0)!
	 * @param maxQtyToReturn
	 * @param user
	 * @return
	 */
	public List<SaveSearchDto> getUserLastSearches(int maxQtyToReturn, String user){
		return searchDao.getSearchHistoryDtos(maxQtyToReturn, user)
	}
	
	/**
	 * Get the last X searches for all users. 
	 * @param maxQtyToReturn
	 * @return
	 */
	public List<SaveSearchDto> getAllLastSearches(int maxQtyToReturn){
		return searchDao.getSearchHistoryDtos(maxQtyToReturn)
	}
	
	public Boolean deleteSearch(Long searchId, SecurityContext securitycontext){
		if(searchDao.getSearchDto(searchId)?.user == securitycontext.getUserPrincipal().getName()){
			return searchDao.deleteSearch(searchId)
		}else{
			return false;
		}
		
	}
	
	public SaveSearchDto getSearch(Long searchId){
		return searchDao.getSearchDto(searchId)
	}
	

}
