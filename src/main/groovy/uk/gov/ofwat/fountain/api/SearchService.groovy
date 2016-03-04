package uk.gov.ofwat.fountain.api

import java.util.List;

import javax.ws.rs.core.SecurityContext;

import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.exception.SearchException;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;
import uk.gov.ofwat.fountain.search.FountainSearchIndex;

interface SearchService {
	
	public String put(Entity entity, Class type) throws SearchException
		
	public <T> Object get(Class<T> type, String id, String elasticSearchType)
	
	public <T> Object get(Class<T> type, String id)
	
	public String searchReports(String query, int start, int maxSize, String sort, FountainSearchIndex searchIndex)
	
	public Boolean addSearchToHistory(SecurityContext securityContext, SaveSearchDto saveSearchDto)
	
	public Boolean addSearchToFavorites(SecurityContext securityContext, SaveSearchDto saveSearchDto)
	
	public boolean removeSearchFromFavorites(SecurityContext securityContext, Long id)
	
	public List<SaveSearchDto> getUserFavoriteSearches(int maxQtyToReturn, String user)
		
	public List<SaveSearchDto> getUserLastSearches(int maxQtyToReturn, String user)
	
	public List<SaveSearchDto> getAllLastSearches(int maxQtyToReturn)
	
	public SaveSearchDto getSearch(Long searchId)
	
	public Boolean deleteSearch(Long searchId, SecurityContext securitycontext)
	
}
	