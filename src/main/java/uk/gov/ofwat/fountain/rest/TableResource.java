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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.annotations.providers.jaxb.DoNotUseJAXBProvider;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import uk.gov.ofwat.fountain.api.DisplayService;
import uk.gov.ofwat.fountain.api.ModelItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.TableService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.Line;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.rest.dto.IntervalDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDtoComparator;
import uk.gov.ofwat.fountain.rest.dto.ReportStructureDto;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;


@Path("/table")
public class TableResource extends RestResource {
	
	private ModelService modelService;
	private ReferenceService referenceService;
	private UserService userService;
	private DisplayService displayService;
	private ModelItemService modelItemService;
	private TableService tableService;
	private static Log log = LogFactory.getLog(TableResource.class);
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setDisplayService(DisplayService displayService) {
		this.displayService = displayService;
	}
	public void setModelItemService(ModelItemService modelItemService) {
		this.modelItemService = modelItemService;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
	public TableResource(){
	}
	
	@GET
	@Path("{id}")
	@Produces({"application/xml", "application/json"})
	@NoCache
	@Wrapped(element="dataTable")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTableForCompany(@PathParam("id") int id, 
			                            @QueryParam("companyId") int companyId, 
			                            @Context SecurityContext securityContext,
					                    @Context HttpServletRequest httpServletRequest) {
		log.debug("starting getTableForCompany");
		User user = userService.getUser(securityContext);
		
		Table table = modelService.getTable(id);
		if (null == table) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		if (0 == companyId) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		Company company = referenceService.getCompany(companyId);
		if (null == company) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		DataTable dataTable = tableService.getTableForCompany(id, companyId, user, roleChecker);
		return Response.ok(dataTable).build();
	}


	/**
	 * get the table structure as a reportStructureDto. NOTE - this will switch the items
	 * to items from the parent of the model family if appropriate.
	 */
	@GET
	@Path("/{id}/structure")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTableAsStructure(@PathParam("id") int id){

		
		// get table from the original (e.g. ICS ) model as we want to filter items
		// based on this model
		log.debug("starting getTableAsStructure");
		Table table = modelService.getTable(id);
		// return the table as a reportStructure dto
		ReportStructureDto dto = new ReportStructureDto();
		
		// TODO - this is actually the table id. There is no report id
		// as there is not report. Alternative is to set to 0;
		dto.setId(id);
		List<Integer>intervalIds = new ArrayList<Integer>();
		List<IntervalDto>intervals = new ArrayList<IntervalDto>();
		
		SortedSet<ModelItemDto>modelItemDtos = new TreeSet<ModelItemDto>(new ModelItemDtoComparator());
		Map<String, ModelItem> localMiCache = new HashMap<String, ModelItem>();
		
		Model parent = null;
		boolean useLine = false;
	
		for(Pot pot: table.getPots().values()){
			if( null == parent){
				parent = modelService.getFamilyParentModel(pot.getModel());
				useLine = pot.getModel().getModelType().equals( ModelType.ICS);			
			}
			
			if(!intervalIds.contains(pot.getInterval().getId())){
				intervalIds.add(pot.getInterval().getId());
				intervals.add(new IntervalDto(pot.getInterval()));
			}
			// this is the point at which we want to switch to the parent model.
			// as a source of item properties. Keeping a local cache for efficiency.
			
			ModelItem mi =  localMiCache.get("" + pot.getItem().getId() + ":" + parent.getId());
			if(null == mi){
				mi = modelItemService.getModelItem(pot, parent);
				localMiCache.put("" + pot.getItem().getId() + ":" + parent.getId(), mi);
			}
		}
		for(ModelItem mi: localMiCache.values()){
			Line line = null;
			if(useLine){
				line = displayService.getLine(table.getId(), mi.getItemId());
				
			}
			ModelItemDto mid = new ModelItemDto(mi, line);
			modelItemDtos.add(mid);
		}

		log.debug("got model items");
		dto.setIntervalIds(intervalIds);
		dto.setIntervalDtos(intervals);
		dto.setModelItemDtos(new ArrayList<ModelItemDto>(modelItemDtos));
		dto.setName("Report from table: " + table.getName());	
		dto.setOwningUser("none");
		log.debug("finished getTableAsStructure");
		return Response.ok(dto).build();
	}
	

	@GET
	@Path("/excel/tables")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@NoCache
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTables(@QueryParam("tableId") List<Integer> tableIds, 
			@QueryParam("companyId")List<Integer> companyIds, @Context SecurityContext securityContext){
	
		log.debug("starting getTables");
		User user = userService.getUser(securityContext);
		List<Table> tables = new ArrayList<Table>();
		for (Integer tableId : tableIds) {
			tables.add(modelService.getTable(tableId));
		}
		
		if (tables.size()==0) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		List<Company> companies = new ArrayList<Company>();
		for (Integer companyId : companyIds) { 
			companies.add(referenceService.getCompany(companyId));
		}
		if (companies.size()==0){
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		List<DataTable> dataTables = new ArrayList<DataTable>();
		for (Company company : companies) {
			for (Table table : tables) {
				DataTable dataTable = tableService.getTableForCompany(table.getId(), company.getId(), user, roleChecker);
				dataTables.add(dataTable);
			}
		}

		GenericEntity<List<DataTable>> entity = new GenericEntity<List<DataTable>>(dataTables) {};
		ResponseBuilder response = Response.ok(entity);
		response.header("Content-Disposition", "attachment; filename=" + exportFileName(tables, companies) + ".xlsx");
		log.debug("finished getTables");
		return response.build();
	}
	private String exportFileName(List<Table> tables, List<Company> companies) {
		Set<String> modelCodes = new HashSet<String>();
		Set<String> tableNames = new HashSet<String>();
		Set<String> companyCodes = new HashSet<String>();
		
		for (Company company : companies) {
			companyCodes.add(company.getCode());
			for (Table table : tables) {
				modelCodes.add(modelService.getModel(table.getModelId()).getCode());
				tableNames.add(table.getName());
			}
		}

		String fileName = "";
		if (modelCodes.size() > 1) {
			fileName = fileName + "models_"; 
		}
		else {
			for (String modelCode: modelCodes) {
				fileName = fileName + modelCode + "_";
			}
		}

		if (tableNames.size() > 3) {
			fileName = fileName + "tables_";
		}
		else {
			for (String tableName: tableNames) {
				fileName = fileName + tableName  + "_";
			}
		}
		
		if (companyCodes.size() > 3) {
			fileName = fileName + "companies";
		}
		else {
			Iterator<String> it = companyCodes.iterator();
			while (it.hasNext()) {
				fileName = fileName + it.next();
				if (it.hasNext()) {
					fileName = fileName + "_";
				}
			}
		}
		return fileName;
	}
}
