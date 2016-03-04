package uk.gov.ofwat.fountain.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import uk.gov.ofwat.fountain.api.SearchService;
import uk.gov.ofwat.fountain.api.IndexService;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.rest.dto.CustomReportDto;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;
import uk.gov.ofwat.fountain.search.FountainSearchIndex;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

@Path("/search")
public class SearchResource extends RestResource {

	private SearchService searchService;
	private IndexService indexService;

	@GET
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })
	public Response searchMe(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @QueryParam("query") String query, @QueryParam("start") Integer start, @QueryParam("type") String type, @QueryParam("deleted") Boolean deleted,  @QueryParam("showMyDataOnly") Boolean showMyDataOnly, @QueryParam("sort") String sortString){
		//Run the query using the query builder
		String json;
		switch(type){
			case "report":			
				json = searchService.searchReports(query, start, 20, sortString, FountainSearchIndex.REPORT);
			break;
			case "table":
				json = searchService.searchReports(query, start, 20, sortString, FountainSearchIndex.TABLE);
			break;
			case "item":
				json = searchService.searchReports(query, start, 20, sortString, FountainSearchIndex.ITEM);
			break;
			default:
				json = searchService.searchReports(query, start, 20, sortString, FountainSearchIndex.REPORT);
			break;
		}
		//String json = elasticSearchService.searchReports(query, start, 20, FountainSearchIndex.REPORT);
		//Get the list of results from the elastic search service and render as JSON.
		return Response.ok(json, "application/json; charset=ISO-8859-1").build();
	}

	@DELETE
	@Path("/favorite")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response removeSearchFromFavorites(@Context SecurityContext securityContext, @QueryParam("searchId") Long searchId){
		Boolean result = searchService.removeSearchFromFavorites(securityContext, searchId);
		if(result){
			return Response.ok().build();
		}else{
			return Response.serverError().build();
		}
	}
		
	@POST
	@Path("/favorite")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response addSearchToFavorites(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @BadgerFish SaveSearchDto dto){
		Boolean result = searchService.addSearchToFavorites(securityContext, dto);
		if(result){
			return Response.ok().build();	
		}else{
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/history")
	@Consumes({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response addSearchToHistory(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @BadgerFish SaveSearchDto dto){
		Boolean result = searchService.addSearchToHistory(securityContext, dto);
		if(result){
			return Response.ok().build();	
		}else{
			return Response.serverError().build();
		}
	}	
	
	@GET
	@Path("/favorite")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response getMySearchFavorites(@Context SecurityContext securityContext, @Context UriInfo uriInfo){
		String username = securityContext.getUserPrincipal().getName();
		return Response.ok(searchService.getUserFavoriteSearches(50, username)).build();
	}
	
	@GET
	@Path("/history")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response getSearchHistory(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @QueryParam("user") Boolean user){
		if((user != null) && (user == true)){
			String username = securityContext.getUserPrincipal().getName();
			return Response.ok(searchService.getUserLastSearches(50, username)).build();
		}else{
			return Response.ok(searchService.getAllLastSearches(100)).build();
		}
	}
	
	@GET
	@Path("/history/{id}")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response getSearch(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		if((id != null)){
			SaveSearchDto search = searchService.getSearch(id);
			if(search != null){
				return Response.ok(search).build();
			}
		}
		return Response.serverError().build();
	}
	
	@DELETE
	@Path("/history/{id}")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Users" })		
	public Response deleteSearch(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @PathParam("id") Long id){
		if((id != null)){
			Boolean removed = searchService.deleteSearch(id, securityContext);
			if(removed){
				return Response.ok().build();
			}
		}
		return Response.serverError().build();
	}
	
	/**
	 * Index all Reports or just the passed reportId. 	
	 * @return
	 */
	@POST
	@Path("/index/report")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Admins" })	
	public Response indexReports(@QueryParam("reportIds") String reportIds){
		Boolean result = false;
		if(reportIds == null || reportIds.isEmpty()){
			//index all reports		
			result = indexService.indexAll(ReportSearchWrapper.class);
		}else{
			//index selected report
			result = indexService.index(ReportSearchWrapper.class, reportIds);
		}
		if(result){
			String json = "{'success':true}";
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else{
			return Response.serverError().build();
		}
	}
	
	/**
	 * Index all Items or just the passed itemId. 	
	 * @return
	 */
	@POST
	@Path("/index/item")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Admins" })	
	public Response indexItems(@QueryParam("itemIds") String itemIds){
		Boolean result = false;
		if(itemIds == null || itemIds.isEmpty()){
			//index all items
			result = indexService.indexAll(Item.class);
		}else{
			//index selected item
			result = indexService.index(Item.class, itemIds);
		}
		if(result){
			String json = "{'success':true}";
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else{
			return Response.serverError().build();
		}
	}
	
	@POST
	@Path("/index/table")
	@Produces({"application/json"})
	@RolesAllowed(value = { "OFWAT\\Fountain.Admins" })	
	public Response indexTables(@QueryParam("tableIds") String tableIds){
		Boolean result = false;
		if(tableIds == null || tableIds.isEmpty()){
			//index all items
			result = indexService.indexAll(Table.class);
		}else{
			//index selected item
			result = indexService.index(Table.class, tableIds);
		}
		if(result){
			String json = "{'success':true}";
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}else{
			return Response.serverError().build();
		}
	}	
	
	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}
	
}
