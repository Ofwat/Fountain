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


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uk.gov.ofwat.fountain.api.*;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.tableWrapper.TWModel;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/tableWrapper")
public class TableWrapperResource extends RestResource {

	private ModelService modelService;
	private ReferenceService referenceService;
	private UserService userService;
	private DisplayService displayService;
	private ModelItemService modelItemService;
	private TableService tableService;
	private TableWrapperService tableWrapperService;
	private static Log log = LogFactory.getLog(TableWrapperResource.class);

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
	public TableWrapperResource(){
	}

	public void setTableWrapperService(TableWrapperService tableWrapperService) {
		this.tableWrapperService = tableWrapperService;
	}

	@GET
	@Path("/{tableId}/structure")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTableWrapperAsStructure(@PathParam("tableId") int tableId,
											   @Context SecurityContext securityContext,
											   @Context HttpServletRequest httpServletRequest){
		User user = userService.getUser(securityContext);

		TWModel twModel = tableWrapperService.renderTable(tableId);
		return Response.ok(twModel).build();
	}

	@GET
	@Path("/{tableId}")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTableWrapperWithData(@PathParam("tableId") int tableId,
											   @QueryParam("companyId") int companyId,
											   @Context SecurityContext securityContext,
											   @Context HttpServletRequest httpServletRequest){
		User user = userService.getUser(securityContext);

		TWModel twModel = tableWrapperService.renderTable(tableId);
		// Run the table and fill in the data in the table wrapper
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		DataTable dataTable = tableService.getTableForCompany(tableId, companyId, user, roleChecker);
		twModel = tableWrapperService.populateWithData(twModel, dataTable, companyId);
		return Response.ok(twModel).build();
	}

}
