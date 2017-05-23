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
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
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
import javax.ws.rs.core.MediaType;
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

		TWModel twModel = tableWrapperService.renderTable(tableId);
		return Response.ok(twModel).build();
	}

	@OPTIONS
	@Path("{path : .*}")
	public Response options() {
		return Response.ok("")
				.header("Access-Control-Allow-Origin", "http://localhost")
				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
				.header("Access-Control-Max-Age", "2000")
				.build();
	}

	@GET
	@Path("/{tableId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
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
//		return Response.ok(twModel)
//				.header("Access-Control-Allow-Origin", "http://localhost")
//				.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//				.header("Access-Control-Allow-Credentials", "true")
//				.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//				.header("Access-Control-Max-Age", "1209600")
//				.build();
	}

	@POST
	@Consumes({"application/json", "application/xml"})
	@Produces({"application/json", "application/xml"})
	@Path("/{tableId}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	public Response postReportTable(	@PathParam("tableId") int tableId,
										@QueryParam("companyId") int companyId,
										@QueryParam("auditComment") String auditComment,
										@Context SecurityContext securityContext,
										@BadgerFish TWModel twModel){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		Response.ResponseBuilder responseBuilder;

		if (modelService.getTable(tableId) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		TWModel serverTWModel = tableWrapperService.renderTable(tableId);
		// Run the table and fill in the data in the table wrapper
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		DataTable dataTable = tableService.getTableForCompany(tableId, companyId, user, roleChecker);
		serverTWModel = tableWrapperService.populateWithData(serverTWModel, dataTable, companyId);

		String equalityStatus = tableWrapperService.checkHeaderCellValuesAreTheSame(serverTWModel, twModel);
		if (!equalityStatus.isEmpty()) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("The structure of the report submitted is not the same as the report on the server.\\n" + equalityStatus);
			return responseBuilder.build();
		}
//		reportService.correctHeaderValues(serverReportTableDto, tableDto);
//		reportService.copyValuesForItemDescriptionAndUnit(serverReportTableDto, tableDto);
//		reportService.copyDataCellKeysAndFormating(serverReportTableDto, tableDto);
//		reportService.copyHeaderCellFormating(serverReportTableDto, tableDto);

// commit data changes
//		ReportServiceImpl.ReportCommitResult reportResult = reportService.commitReportTableData(tableDto, auditComment, user, rc, serverReport, companyId);

// switch on results status
//		switch (reportResult.getStatus()) {
//			case NO_AUDIT_COMMENT :
//				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
//				responseBuilder.entity("NO_AUDIT_COMMENT");
//				break;
//			case CELL_ERROR :
//				responseBuilder = Response.ok();
//				//JT - Temp. fix due to RestSharp truncating 40x responses.
//				tableDto.setError(true);
//				tableDto.setErrorMessage("There was a problem with the submitted cells.");
//				responseBuilder.entity(tableDto);
//				break;
//			case PRE_EXISTING_EDIT_SESSION :
//				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
//				responseBuilder.entity("PRE_EXISTING_EDIT_SESSION");
//				break;
//			case BRANCH_NOT_EDITABLE :
//				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
//				responseBuilder.entity("BRANCH_NOT_EDITABLE");
//				break;
//			default : // catches NO_CHANGES_MADE and OK
//				HashMap<String, Boolean> result = new HashMap<String, Boolean>();
//				result.put("Success", true);
//				//How do we automate this?
//				responseBuilder = Response.ok(result).header("audits", reportResult.getAudits());
//				responseBuilder.entity(tableDto);
//				break;
//		}
		Response response = Response.ok().build(); //responseBuilder.build();

		return response;
	}

}
