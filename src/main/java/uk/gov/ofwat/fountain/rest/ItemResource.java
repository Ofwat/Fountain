/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.IndexService;
import uk.gov.ofwat.fountain.api.InvalidTableException;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ItemTableService;
import uk.gov.ofwat.fountain.api.ModelItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.rest.dto.ItemDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

@Path("/item")
public class ItemResource extends RestResource{

	private ModelItemService modelItemService;
	private ItemService itemService;
	private ModelService modelService;
	private ItemTableService itemTableService;
	
	private static Logger logger = LoggerFactory.getLogger(ItemResource.class);
	
	public void setModelItemService(ModelItemService modelItemService) {
		this.modelItemService = modelItemService;
	}
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	public ItemResource(){
	}
	
	public void setItemTableService(ItemTableService itemTableService) {
		this.itemTableService = itemTableService;
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response searchItem(@QueryParam("criteria") String criteria,
									@QueryParam("filters") String filters,
								    @QueryParam("extended") @DefaultValue("false") boolean extended){
		logger.debug("starting searchItem");
		List<ModelItemDto> dtos = new ArrayList<ModelItemDto>();
		List<ModelItem>modelItems = null;
		if(null != filters && !("".equals(filters))){
			// this branch indicates the user has sent a list of model ids to filter by
			// parse out an array of model id's from the 'filters' parameter
			// then use the filtered service methods
			String[] modelFilters = filters.split(";");
			int[] ids = new int[modelFilters.length];
			Map<Integer, Integer>childToParentMap = new HashMap<Integer, Integer>();
			// ensure to switch to a family parent model if needed
			for(int i = 0; i < modelFilters.length; i++){
				int modelId = Integer.parseInt(modelFilters[i]);
				int parentId = 0;
				if(childToParentMap.containsKey(modelId)){
					parentId = childToParentMap.get(modelId); 
				}
				else{
					parentId = modelService.getFamilyParentModel(modelService.getModel(modelId)).getId();
					childToParentMap.put(modelId, parentId);
				}
				ids[i] = parentId;
			}
			if(extended){
				modelItems = modelItemService.searchForItemViaDefinition(criteria, ids);
			}
			else{
				modelItems = modelItemService.searchForItem(criteria, ids);
			}
		}
		for(ModelItem modelItem: modelItems){
			dtos.add(new ModelItemDto(modelItem, null));
		}
		logger.debug("finished searchItem");
		GenericEntity<List<ModelItemDto>> entity = new GenericEntity<List<ModelItemDto>>(dtos){};
		return Response.ok(entity).build();	
	}
	
	@GET
	@Path("{id}")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getItem(@PathParam("id") int id, 
            			@Context SecurityContext securityContext,
            			@Context HttpServletRequest httpServletRequest){
		logger.debug("starting getItem");
		ItemDto dto = new ItemDto(itemService.getItem(id));
		logger.debug("finished getItem");
		return Response.ok(dto).build();
		
	}
	
	@POST
	@Path("{id}/team/{teamId}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response setTeamOnItem(@PathParam("id") int itemId, 
			@PathParam("teamId") int teamId,
			@Context SecurityContext securityContext,
			@Context HttpServletRequest httpServletRequest
			){
		logger.debug("setting team id " + teamId + " on item " + itemId);
		itemService.setTeamOnItem(itemId, teamId);
		logger.debug("finished setTeamOnItem");
		return Response.ok().build();
	}

	@POST
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	public Response createItems(@BadgerFish TableDto tableDto,
								@Context SecurityContext securityContext) {

		ResponseBuilder responseBuilder;
		if (tableDto.getName() == "") {
			responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("Table must have a name.");
			return responseBuilder.build();
		}

		TableDto returnTable = itemTableService.createNewItems(tableDto);
		
		responseBuilder = Response.ok(); 
		responseBuilder.entity(returnTable);
		return responseBuilder.build();
	}
	
}
