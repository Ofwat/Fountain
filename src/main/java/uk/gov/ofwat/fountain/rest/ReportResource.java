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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.cache.NoCache;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.jboss.resteasy.spi.UnhandledException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CannotAcquireLockException;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.ChunkService;
import uk.gov.ofwat.fountain.api.IndexService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.PopularityPeriod;
import uk.gov.ofwat.fountain.api.PopularityService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.ReportServiceImpl.ReportCommitResult;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.api.RunTagService;
import uk.gov.ofwat.fountain.api.TeamService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.chunk.ChunkSet;
import uk.gov.ofwat.fountain.domain.chunk.ReportChunkSet;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.rest.dto.BulkDeleteReportDto;
import uk.gov.ofwat.fountain.rest.dto.BulkModifyReportDto;
import uk.gov.ofwat.fountain.rest.dto.CustomReportDto;
import uk.gov.ofwat.fountain.rest.dto.EditReportDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDetailsDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;
import uk.gov.ofwat.fountain.rest.dto.ReportFlat;
import uk.gov.ofwat.fountain.rest.dto.RunTagIdsDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;
import uk.gov.ofwat.fountain.rest.dto.factory.CustomReportDtoFactory;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

@Path("/report")
public class ReportResource extends RestResource {

	private ReportService reportService;
	private UserService userService;
	private TeamService teamService;
	private CustomReportDtoFactory customReportDtoFactory;
	private ModelService modelService;
	private RunService runService;
	private RunTagService runTagService;
	private ReferenceService referenceService;
	private PopularityService popularityService;
	private IndexService indexService;
	private int maxConcurrentRunningReports;
	private BasketService basketService;
	private ChunkService chunkService;
	private int chunkReportSize;
	
	private static String REPORT_TOO_LARGE_MESSAGE = "The report is too large to generate - please try reducing the number of tags/years and re-running the report.";
	private static String SERVICE_UNAVAILABLE_MESSAGE = "This service is temporarily unavailable due to load on the system. Please try again later.";
	private static int noOfReportsRunning = 0;
	private static Logger logger = LoggerFactory.getLogger(ReportResource.class);

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public ChunkService getChunkService() {
		return chunkService;
	}

	public void setChunkService(ChunkService chunkService) {
		this.chunkService = chunkService;
	}

	public int getMaxConcurrentRunningReports() {
		return maxConcurrentRunningReports;
	}

	public void setMaxConcurrentRunningReports(int maxConcurrentRunningReports) {
		this.maxConcurrentRunningReports = maxConcurrentRunningReports;
	}

	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}	
	
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	
	public RunTagService getRunTagService() {
		return runTagService;
	}
	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	public void setCustomReportDtoFactory(
			CustomReportDtoFactory customReportDtoFactory) {
		this.customReportDtoFactory = customReportDtoFactory;
	}
	public PopularityService getPopularityService() {
		return popularityService;
	}

	public void setPopularityService(PopularityService popularityService) {
		this.popularityService = popularityService;
	}

	public ReportResource(){
	}
	
	@POST
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response saveReport(@BadgerFish CustomReportDto dto,
							@Context SecurityContext securityContext) {
		logger.debug("starting report save");
		User user = userService.getUser(securityContext);
		
		List<ModelItem> modelItems = new ArrayList<ModelItem>();
		for(ModelItemDto mid: dto.getItems()){
			modelItems.add(new ModelItem(mid));
		}
		
		int reportId = reportService.saveReport(dto.getName(), 
				                 user, 
				                 teamService.getTeamById(dto.getTeamId()),
				                 modelItems, 
				                 dto.getGroup(),
				                 dto.getIntervals(), 
				                 dto.getLayoutLeft(),
				                 dto.getLayoutTop(),
				                 dto.isPublicReport(),
				                 dto.getCompanies(),
				                 dto.isDisplayCGs(),
				                 dto.isDisplayUnit(),
				                 dto.isDisplayBoncode(),
				                 dto.isDisplayDesc(),
				                 dto.isDisplayModel(),
				                 dto.isDisplayCompanyName(), 
				                 dto.isDisplayCompanyAcronym(),
                                 dto.getRunTagIds(),
                                 dto.isDisplayRunName(),
                                 dto.isDisplayRunDescription(),
                                 dto.isDisplayTagName(),
                                 dto.isDisplayAgendaName(),
                                 dto.isDisplayAgendaCode(),
                                 dto.isReadOnly(),
                                 null); //TODO This is the report description param and should be populated from the DTO - not doing this at the moment as we will need to evaluate how this affects other parts of the system e.e. the Excel plugin.
		reportService.buildReport(reportId);
		logger.debug("finished report save");
		
		indexReport(reportId);
		//return reportId;
		return Response.ok(Integer.valueOf(reportId)).build();
	}

	@GET
	@Produces({"application/json", "application/xml"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportsForUser(@Context SecurityContext securityContext, @Context UriInfo uriInfo, @QueryParam("showSummary") boolean showSummary, @QueryParam("limit") Integer limit){
		logger.debug("starting getReportsForUser");
		
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		List<Integer> teamIds = teamService.findTeamIdsForUser(roleChecker);
		Team team = teamService.findTeamForUser(roleChecker);
		List<ReportSummary> results = null;
		if (null == team) {
			results = reportService.getReportsForTeam(null, limit);
		} else {
			results = reportService.getReportsForTeams(teamIds, showSummary, limit);
		}
		List<ReportDetailsDto> reports = new ArrayList<ReportDetailsDto>();
		for(ReportSummary summary: results){
			Link link = createLink(summary, "report/", "", uriInfo, securityContext);
			ReportDetailsDto dto = new ReportDetailsDto();
			dto.setId(summary.getId());
			dto.setLink(link);
			dto.setName(summary.getName());
			dto.setDescription("Report " + summary.getName());
			SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			dto.setLastModified(df.format(summary.getLastModified()));
			dto.setPublicReport(summary.isPublicReport());
			Team reportTeam = teamService.getTeamById(summary.getTeamId());
			dto.setTeam(reportTeam.getName());
			dto.setRedesign(securityContext.isUserInRole("ROLE_OFWAT\\FOUNTAIN.USERS"));
			dto.setInterchangeableCompany(summary.isInterchangeableCompany());
			dto.setInterchangeableRun(summary.isInterchangeableRun());
			reports.add(dto);
		}
		logger.debug("finished getReportsForUser");
		GenericEntity<List<ReportDetailsDto>> entity = new GenericEntity<List<ReportDetailsDto>>(reports){};
		return Response.ok(entity).build();		
	}

	@GET
	@Produces({"application/json"})
	@Path("/trending/{period}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportPopularity(@PathParam("period") String period,
	                        @Context SecurityContext securityContext, @Context UriInfo uriInfo){
		
		//Get the period that we are going to pass
		PopularityPeriod p;
		switch(period){
			case "YEAR":
				p = PopularityPeriod.YEAR;
			break;
			case "MONTH":
				p = PopularityPeriod.MONTH;
			break;
			case "DAY":
				p = PopularityPeriod.DAY;
			break;
			case "WEEK":
				p = PopularityPeriod.WEEK;
			break;
			case "ALL_TIME":
				p = PopularityPeriod.ALL_TIME;
			break;
			default:
				p = PopularityPeriod.DAY;
		}
				
		//Get the HashMap popularities
		List<HashMap<Long, Float>> popularReports = popularityService.getMostPopular(p, Report.class);
		
		//Get an array list of ReportSummary
		ReportSummary reportSummary;
		List<ReportSummary> reportSummaries = new ArrayList<ReportSummary>();
		
		for (HashMap<Long, Float> resultMap: popularReports) {
			int reportId = 0;
			try{
				reportId = resultMap.entrySet().iterator().next().getKey().intValue();
				reportSummary = reportService.getReportSummary(reportId);
				reportSummaries.add(reportSummary);
			}
			catch(Exception e){
				logger.error("Unable to find report with id: " + reportId);
			}
		}
		
		//Return as JSON.
		List<ReportDetailsDto> reports = new ArrayList<ReportDetailsDto>();
		for(ReportSummary summary: reportSummaries){
			Link link = createLink(summary, "report/", "", uriInfo, securityContext);
			ReportDetailsDto dto = new ReportDetailsDto();
			dto.setId(summary.getId());
			dto.setLink(link);
			dto.setName(summary.getName());
			dto.setDescription("Report " + summary.getName());
			SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			dto.setLastModified(df.format(summary.getLastModified()));
			dto.setPublicReport(summary.isPublicReport());
			Team reportTeam = teamService.getTeamById(summary.getTeamId());
			dto.setTeam(reportTeam.getName());
			dto.setRedesign(securityContext.isUserInRole("ROLE_OFWAT\\FOUNTAIN.USERS"));
			dto.setInterchangeableCompany(summary.isInterchangeableCompany());
			reports.add(dto);
		}
		logger.debug("finished getReportsForUser");
		GenericEntity<List<ReportDetailsDto>> entity = new GenericEntity<List<ReportDetailsDto>>(reports){};
		return Response.ok(entity).build();			
	}
	
	
	public int getChunkReportSize() {
		return chunkReportSize;
	}

	public void setChunkReportSize(int chunkReportSize) {
		this.chunkReportSize = chunkReportSize;
	}

	@GET
	@Produces({"application/json", "application/xml"})
	@Path("{id}")
	@Wrapped(element="report")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReport(@PathParam("id") int id,
							@QueryParam("companyId") int companyId,
							@QueryParam("runId") int runId,
							@QueryParam("tagId") int tagId,
	                        @Context SecurityContext securityContext){
		User user = userService.getUser(securityContext); 
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		
		validateRunAndTag(id, runId, companyId);

		Report report = runReport(id, user, rc, companyId, runId, tagId, true);
		popularityService.addPopularity(report.getClass().toString(), new Long(report.getId()));
		ReportDto reportDto = new ReportDto(report);		
		
		//CHUNKING disabled for live release
//		if(chunkReport(reportDto)){
//			
//			//We need to get the report structure here to make sure our chunks are the same order as the structure. i.e we chunk the topmost cells first - 
//			//this is done in the factory see ReportChunkJsonEntityFactory  
//			try{		
//				ChunkSet chunkSet = doChunking(reportDto);
//				reportDto.setChunkSet(chunkSet);
//				reportDto.setChunked(true);		
//				
//				reportDto.setDataMap(null);
//			}catch(Exception e){
//				logger.error("There was a problem chunking the report.");
//				logger.error(e.toString());
//			}
//		}
			
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity(reportDto);
		return responseBuilder.build();
	}
	

	
	
	@GET
	@Produces({"application/json"})
	@Path("/chunk/{chunkSetId}/{chunkId}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportChunk(@PathParam("chunkSetId") String chunkSetId,
							@PathParam("chunkId") String chunkId,
	                        @Context SecurityContext securityContext){
		
		ArrayList json = (ArrayList) chunkService.getChunk(chunkSetId, chunkId);
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity(json);
		return responseBuilder.build();
	}	
	
	private ChunkSet doChunking(ReportDto reportDto){
		ChunkSet chunkSet = chunkService.doChunking(reportDto);
		return chunkSet;
	}
	
	private Boolean chunkReport(ReportDto reportDto){
		if(reportDto.getDataList().size() > chunkReportSize){
			return true;
		}
		return false;
	}

	private void validateRunAndTag(int id, int runId, int companyId) {
		ResponseBuilder responseBuilder;
		ReportDefinition reportDef = reportService.getReportDefinition(id);

		if (reportDef.isInterchangeableRun()) {
			// interchangable
			if (0 == runId) {
				// but no run supplied
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("This report has no run. It must be supplied with a run.");
				throw new WebApplicationException(responseBuilder.build());
			}
			// run supplied
			Run run = runService.getRun(runId);
			if (null == run) {
				responseBuilder = Response.status(Response.Status.NOT_FOUND);
				responseBuilder.entity("The run '" + runId + "' could not be found.");
				throw new WebApplicationException(responseBuilder.build());
			}
		}
		else {
			// report has runs build in
			if (0 != runId) {
				// but a run has been supplied
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("This report has a run. It must not be supplied with a run.");
				throw new WebApplicationException(responseBuilder.build());
			}
			// no run supplied
		}
	}


	@GET
	@Produces({"application/xml"})
	@Path("{id}/xml")
	@Wrapped(element="report")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getXmlReport(@PathParam("id") int id,
			                           @Context SecurityContext securityContext){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		Report report = runReport(id, user, rc, true); //TODO This should really have run and company
		return Response.ok(new ReportFlat(report)).build();
	}

	@GET
	@Path("{id}/excel")
	@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	@NoCache
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getExcelReport(@PathParam("id") int id,
									@QueryParam("companyId") int companyId,
									@QueryParam("runId") int runId,
									@QueryParam("tagId") int tagId,
			                           @Context SecurityContext securityContext){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		validateRunAndTag(id, runId, companyId);
		ReportDto reportDto = new ReportDto(runReport(id, user, rc, companyId, runId, tagId, true));
		reportDto.setShowAllHeaders(true);
		return Response.ok(reportDto).build();
	}

	@GET
	@Produces({"application/json", "application/xml"})
	@Path("/table/{id}")
	@Wrapped(element="reportTable")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportTable(	@PathParam("id") int id,
									@QueryParam("companyId") int companyId,
									@QueryParam("runId") int runId,
									@QueryParam("tagId") int tagId,
									@Context SecurityContext securityContext){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		ResponseBuilder responseBuilder;
		if (!reportService.isValidReportTable(id)) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("The 'Layout' or 'Display Options' of the requested report are incorrect. " +
					"Please redesign the report in Fountain so that: Layout Rows contain Company followed by Item, and Layout Columns contains Year. " +
					"The Display Options should have these options checked: Description, Unit, Boncode, Model, Acronym.");
			return responseBuilder.build();
		}
		validateRunAndTag(id, runId, companyId);
		
		if (null != basketService.getBasketForUser(user)) {
			// basket is open
			responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("PRE_EXISTING_EDIT_SESSION");
			return responseBuilder.build();
		}

		
		TableDto reportTableDto;
		if (interchangeableCompanyReport(id, companyId)) {
			Report report = runReport(id, user, rc, companyId, runId, tagId, true);
			ReportDto reportDto = new ReportDto(report);
			reportTableDto = reportService.writeReportTable(reportDto);
			reportService.removeCompanyAcronyms(reportTableDto);
		}
		else {
			Report report =  runReport(id, user, rc, runId, tagId, true);
			ReportDto reportDto = new ReportDto(report);
			reportTableDto = reportService.writeReportTable(reportDto); 
		}
		
		responseBuilder = Response.ok();
		responseBuilder.entity(reportTableDto);
		return responseBuilder.build();
	}
	
	
	@POST
	@Produces({"application/json", "application/xml"})
	@Path("/description/{id}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response postReportDescription(@PathParam("id") int id,
									@QueryParam("description") String description){
		//User user = userService.getUser(securityContext);
		//RoleChecker rc = new RestServiceRoleChecker(securityContext);
		Boolean result = reportService.updateReportDescription(id, description);
		return Response.ok().build();
	}

	@POST
	@Consumes({"application/json", "application/xml"})
	@Produces({"application/json", "application/xml"})
	@Path("/table/{id}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	public Response postReportTable(	@PathParam("id") int id,
									@QueryParam("companyId") int companyId,
									@QueryParam("auditComment") String auditComment,
									@QueryParam("runId") int runId,
									@QueryParam("excelDocMongoId") String excelDocMongoId,
									@Context SecurityContext securityContext,
									@BadgerFish TableDto tableDto){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		ResponseBuilder responseBuilder;

		if (reportService.getSummaryById(id) == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		if (modelOnRun(id, runId, companyId) &&
			modelCompleted(id, runId, companyId)) {
			responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("Cannot make data changes. Model has been completed.");
			return responseBuilder.build();
		}
		
		validateRunAndTag(id, runId, companyId);

		Report serverReport = null;
		TableDto serverReportTableDto = null;
		if (interchangeableCompanyReport(tableDto.getId(), companyId)) {
			if (!reportService.isAcronymColEmpty(tableDto)) {
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("A company has been supplied but company acronym column is not empty. Please clear this before sending the F_Outputs sheet.");
				return responseBuilder.build();
			}
			serverReport = runReport(tableDto.getId(), user, rc, companyId, runId, RunModelTag.LATEST.getId(), false);
			ReportDto serverReportDto = new ReportDto(serverReport);
			serverReportTableDto = reportService.writeReportTable(serverReportDto);
			reportService.removeCompanyAcronyms(serverReportTableDto);
		}
		else {
			serverReport = runReport(tableDto.getId(), user, rc, runId, RunModelTag.LATEST.getId(), false);
			ReportDto serverReportDto = new ReportDto(serverReport);
			serverReportTableDto = reportService.writeReportTable(serverReportDto);
		}
		
		String equalityStatus = reportService.checkHeaderCellValuesAreTheSame(serverReportTableDto, tableDto);
		if (!equalityStatus.isEmpty()) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("The structure of the report submitted is not the same as the report on the server.\\n" + equalityStatus);
			return responseBuilder.build();
		}
		reportService.correctHeaderValues(serverReportTableDto, tableDto);
		reportService.copyValuesForItemDescriptionAndUnit(serverReportTableDto, tableDto);
		reportService.copyDataCellKeysAndFormating(serverReportTableDto, tableDto);
		reportService.copyHeaderCellFormating(serverReportTableDto, tableDto);
		ReportCommitResult reportResult = reportService.commitReportTableData(tableDto, auditComment, user, rc, serverReport, companyId); 
		
		switch (reportResult.getStatus()) {
			case NO_AUDIT_COMMENT :
				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
				responseBuilder.entity("NO_AUDIT_COMMENT");
				break; 
			case CELL_ERROR :
				responseBuilder = Response.ok();
				//JT - Temp. fix due to RestSharp truncating 40x responses. 
				tableDto.setError(true);
				tableDto.setErrorMessage("There was a problem with the submitted cells.");
				responseBuilder.entity(tableDto);
				break; 
			case PRE_EXISTING_EDIT_SESSION :
				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
				responseBuilder.entity("PRE_EXISTING_EDIT_SESSION");
				break; 
			case BRANCH_NOT_EDITABLE :
				responseBuilder = Response.status(Response.Status.BAD_REQUEST);
				responseBuilder.entity("BRANCH_NOT_EDITABLE");
				break; 
			default : // catches NO_CHANGES_MADE and OK
				HashMap<String, Boolean> result = new HashMap<String, Boolean>();
				result.put("Success", true);
				//How do we automate this?
				responseBuilder = Response.ok(result).header("audits", reportResult.getAudits());
				responseBuilder.entity(tableDto);
				break;
		}
		Response response = responseBuilder.build();
		
		return response;
	}
	
	private boolean modelOnRun(int reportId, int runId, int companyId) {
		Run run = runService.getRun(runId);
		ModelCompanyReport mrc = modelService.getModelCompanyReport(reportId);
		if (null == mrc) {
			return false;
		}
		Model modelUsingReport = modelService.getModel(mrc.getModelId());
		RunModel runModel = runService.getRunModel(modelUsingReport.getId(), run, companyId);
		if (null == runModel) {
			return false;
		}
		return true;
	}
	
	private boolean modelCompleted(int reportId, int runId, int companyId) {
		Run run = runService.getRun(runId);
		ModelCompanyReport mrc = modelService.getModelCompanyReport(reportId);
		Model modelUsingReport = modelService.getModel(mrc.getModelId());

		if (interchangeableCompanyReport(reportId, companyId) &&
			!run.getCompanyIds().contains(companyId)) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT).entity("This company is not on the '" + run.getName() + "' run.");
			throw new WebApplicationException(responseBuilder.build());
		}

		RunModel runModel = runService.getRunModel(modelUsingReport.getId(), run, companyId);
		if (null == runModel) {
			// a model not on the run cannot be completed
			return false;
		}
		return runModel.isCompleted();
	}

	private boolean interchangeableCompanyReport(int reportId, int companyId) {
		ReportSummary reportSummary = reportService.getSummaryById(reportId);
		if (reportSummary.isInterchangeableCompany()) {
			if (0 == companyId) { 						
				// but no company supplied
				ResponseBuilder responseBuilder;
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("This report has no company so a company must be supplied.");
				throw new WebApplicationException(responseBuilder.build());
			}
			// company supplied
			return true;
		}
		else {
			// report has companies built in
			if (0 != companyId) { 						
				// but a company has been supplied
				ResponseBuilder responseBuilder;
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("This report has a company allocated at design time but a company has been supplied with the request to get the report. Please re-design this report to remove the allocated company or do not supply a company for this report.");
				throw new WebApplicationException(responseBuilder.build());
			}
			// no company supplied
			return false;
		}
	}

	@POST
	@Consumes({"application/json", "application/xml"})
	@Produces({"application/json", "application/xml"})
	@Path("/table/equality/{id}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response reportEquality(	@PathParam("id") int id,
									@QueryParam("companyId") int companyId,
									@QueryParam("runId") int runId,
									@QueryParam("tagId") int tagId,
									@Context SecurityContext securityContext,
									@BadgerFish TableDto tableDto){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);
		
		validateRunAndTag(id, runId, companyId);
		
		TableDto serverReportTableDto = null;
		ResponseBuilder responseBuilder;
		if (interchangeableCompanyReport(tableDto.getId(), companyId)) {
			Report report = runReport(id, user, rc, companyId, runId, tagId, true);
			ReportDto reportDto = new ReportDto(report);
			serverReportTableDto = reportService.writeReportTable(reportDto);
			reportService.removeCompanyAcronyms(serverReportTableDto);
		}
		else {
			Report report = runReport(id, user, rc, runId, tagId, true);
			ReportDto reportDto = new ReportDto(report);
			serverReportTableDto = reportService.writeReportTable(reportDto);
		}
		String equalityStatus = reportService.checkReportTableEquality(tableDto, serverReportTableDto);
		
		if (equalityStatus.equals("equal")) {
			responseBuilder = Response.ok();
		}
		else {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity(equalityStatus);
		}
		return responseBuilder.build();
	}

	@GET
	@Produces({"application/json", "application/xml"})
	@Path("/table/structure/{id}")
	@Wrapped(element="reportTable")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportTableStructure(@PathParam("id") int id,
											@QueryParam("companyId") int companyId,
											@QueryParam("runId") int runId,
											@QueryParam("tagId") int tagId,
											@Context SecurityContext securityContext){
		User user = userService.getUser(securityContext);
		RoleChecker rc = new RestServiceRoleChecker(securityContext);

		validateRunAndTag(id, runId, companyId);

		Report report = null;
		if (interchangeableCompanyReport(id, companyId)) {
			report = runReport(id, user, rc, companyId, runId, tagId, false);
		}
		else {
			report = runReport(id, user, rc, runId, tagId, false);
		}
		ReportDto reportDto = new ReportDto(report);
		TableDto reportTableDto = reportService.writeReportTableStructure(reportDto);
		return Response.ok(reportTableDto).build();
	}

	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/table/structure")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	public Response createReportDefinition(@BadgerFish TableDto tableDto,
										@QueryParam("companyId") int companyId,
										@QueryParam("runId") int runId,
										@Context SecurityContext securityContext) {
		logger.debug("starting report save");
		User user = userService.getUser(securityContext);
		
		ResponseBuilder responseBuilder;
		if (tableDto.getName() == "") {
			return Response.status(Response.Status.BAD_REQUEST).entity("Table must have a name.").build();
		}
		
		CustomReportDto dto = customReportDtoFactory.create(tableDto, companyId);
		
		if (null == dto) {
			return Response.status(Response.Status.OK).entity(tableDto).build();
		}
		
		List<ModelItem> modelItems = new ArrayList<ModelItem>();
		for (ModelItemDto modelItemDto: dto.getItems()) {
			modelItems.add(new ModelItem(modelItemDto));
		}

		Integer reportId;
		if (tableDto.getId() == 0) {
			// new report. Need to create one here.
			reportId = reportService.saveReport(
						dto.getName(), 
						user, 
				        teamService.getTeamById(dto.getTeamId()),
				        modelItems, 
						dto.getGroup(),
						dto.getIntervals(), 
						dto.getLayoutLeft(),
						dto.getLayoutTop(),
						dto.isPublicReport(),
						dto.getCompanies(),
						dto.isDisplayCGs(),
						dto.isDisplayUnit(),
						dto.isDisplayBoncode(),
						dto.isDisplayDesc(),
						dto.isDisplayModel(),
						dto.isDisplayCompanyName(), 
						dto.isDisplayCompanyAcronym(),
                        dto.getRunTagIds(),
                        dto.isDisplayRunName(),
                        dto.isDisplayRunDescription(),
                        dto.isDisplayTagName(),
                        dto.isDisplayAgendaName(),
                        dto.isDisplayAgendaCode(),
                        dto.isReadOnly(),
                        null); //TODO This is the report description param and should be populated from the DTO - not doing this at the moment as we will need to evaluate how this affects other parts of the system e.e. the Excel plugin.  

		}
		else {
			// update existing report.
			reportId = reportService.saveUpdatedReport(tableDto.getId(), 
						dto.getName(), 
						user, 
				        teamService.getTeamById(dto.getTeamId()),
				        modelItems, 
						dto.getGroup(),
						dto.getIntervals(), 
						dto.getLayoutLeft(),
						dto.getLayoutTop(),
						dto.isPublicReport(),
						dto.getCompanies(),
						dto.isDisplayCGs(),
						dto.isDisplayUnit(),
						dto.isDisplayBoncode(),
						dto.isDisplayDesc(),
						dto.isDisplayModel(),
						dto.isDisplayCompanyName(), 
						dto.isDisplayCompanyAcronym(),
                        dto.getRunTagIds(),
                        dto.isDisplayRunName(),
                        dto.isDisplayRunDescription(),
                        dto.isDisplayTagName(),
                        dto.isDisplayAgendaName(),
                        dto.isDisplayAgendaCode(),
                        dto.isReadOnly(),
                        null); //TODO This is the report description param and should be populated from the DTO - not doing this at the moment as we will need to evaluate how this affects other parts of the system e.e. the Excel plugin.  
                        
		}
		
		reportService.buildReport(reportId);

		indexReport(reportId);
		
		responseBuilder = Response.ok(reportId);
		return responseBuilder.build();
	}
	

	@POST
	@Produces({"application/json"})
	@Path("/readOnly/{reportIds}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response markReportReadOnly(@PathParam("reportIds") String reportIds,	
									@QueryParam("readOnly") Boolean readOnly,
			 @Context SecurityContext securityContext) {
		logger.debug("Invoked bulk readOnly reports.");
		
		List<BulkModifyReportDto> results = null;
		if(reportIds == null || reportIds.isEmpty()){
			results = new ArrayList<BulkModifyReportDto>();
		}else{
			results = reportService.bulkUpdateReportReadOnlyStatus(reportIds, securityContext, readOnly);
		}
		//Check if any of the delete calls error.
		for(BulkModifyReportDto dto : results){
			if(dto.readOnly == false){
				ResponseBuilder responseBuilder = Response.serverError();
				responseBuilder.entity(results);
				return responseBuilder.build();
			}
		}
		
		ResponseBuilder responseBuilder = Response.ok();
		responseBuilder.entity(results);
		return responseBuilder.build();
	}	


	@DELETE
	@Produces({"application/json"})
	@Path("{reportIds}/bulk")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response deleteReport(@PathParam("reportIds") String reportIds,	
			 @Context SecurityContext securityContext) {
		logger.debug("Invoked bulk delete reports.");
		List<BulkDeleteReportDto> results = null;
		if(reportIds == null || reportIds.isEmpty()){
			results = new ArrayList<BulkDeleteReportDto>();
		}else{
			results = reportService.bulkDeleteReports(reportIds, securityContext);
		}
		//Check if any of the delete calls error.
		for(BulkDeleteReportDto dto : results){
			if(dto.deleted == false){
				ResponseBuilder responseBuilder = Response.serverError();
				responseBuilder.entity(results);
				return responseBuilder.build();
			}
		}		
		ResponseBuilder responseBuilder = Response.ok();
		//ResponseBuilder responseBuilder = Response.serverError();
		responseBuilder.entity(results);
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("{id}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response deleteReport(@PathParam("id") int id,
								 @Context SecurityContext securityContext) {
		logger.debug("invoked deleteReport");
		
		if (0 == id) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		
		// check if the user is allowed to delete this report
		// don't trust that the user is allowed to delete the report because they sent a request
		ReportSummary summary = reportService.getSummaryById(id);
		
		if (!securityContext.isUserInRole(teamService.getTeamById(summary.getTeamId()).getCode())) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		
		reportService.deleteReport(id);
		
		ResponseBuilder responseBuilder = Response.noContent();
		logger.debug("finished deleteReport");
		return responseBuilder.build();
	}

	@GET
	@Path("{id}/edit")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getReportDetails(@PathParam("id") int id,
			@Context SecurityContext securityContext){
		logger.debug("starting getReportDetails");
		EditReportDto report = new EditReportDto();
		ReportDefinition def = reportService.getReportDefinition(id);

		//Assign the runTags to the report definition.
		if (!def.isInterchangeableRun()) {
	        if(def.getRunTags() != null){
	            List<RunTagIdsDto> runTagIds = new ArrayList<RunTagIdsDto>();
	            for(RunTag rt : def.getRunTags()){
	                RunTagIdsDto rtid = new RunTagIdsDto();
	                rtid.setRunName(rt.getRun().getName());
	                rtid.setRunId(rt.getRun().getId());
	                rtid.setTagName(rt.getRunModelTag().getDisplayName());
	                rtid.setTagId((rt.getRunModelTag().getId()));
	                runTagIds.add(rtid);
	            }
	            report.setRunTagIds(runTagIds);
	        }
        }

		if (!def.isInterchangeableCompany()) {
			report.setCompanies(def.getCompanyIds());
		}
		report.setIntervals(def.getIntervalIds());
		report.setItems(new ArrayList<ModelItemDto>());
		for (ModelItem mi : def.getModelItems()) {
			report.getItems().add(new ModelItemDto(mi,null));
		}
		//report.setItems(def.getModelItems());
		report.setLayoutLeft(def.getLayoutLeft());
		report.setLayoutTop(def.getLayoutTop());
		report.setName(def.getName());
		report.setPublicReport(def.isPublicReport());
		// need to know if it's a report for this team
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		//note this just checks you belong to the team for this report. 
		report.setMyReport(teamService.isMyTeam(teamService.getTeamById(def.getTeamId()), roleChecker));
		
		//Set if this user is the actual creator of the report.
		String username = securityContext.getUserPrincipal().getName();
		report.setReportCreator(username.equals(def.getOwnerUser()));
		//Set if this user is an admin user
		report.setReportCreatorName(def.getOwnerUser());
		report.setAdmin(roleChecker.isUserInRole(User.FOUNTAIN_ADMIN_ROLE));
		
		
		// find out the display options
		report.setDisplayBoncode(def.getReportDisplay().isDisplayBoncode());
		report.setDisplayCGs(def.getReportDisplay().isDisplayCGs());
		report.setDisplayCompanyAcronym(def.getReportDisplay().isDisplayCompanyAcronym());
		report.setDisplayCompanyName(def.getReportDisplay().isDisplayCompanyName());
		report.setDisplayDesc(def.getReportDisplay().isDisplayDesc());
		report.setDisplayModel(def.getReportDisplay().isDisplayModel());
		report.setDisplayUnit(def.getReportDisplay().isDisplayUnit());
        report.setDisplayRunName(def.getReportDisplay().isDisplayRunName());
        report.setDisplayRunDescription(def.getReportDisplay().isDisplayRunDesc());
        report.setDisplayTagName(def.getReportDisplay().isDisplayTagDisplayName());
        report.setDisplayAgendaName(def.getReportDisplay().isDisplayAgendaName());
        report.setDisplayAgendaCode(def.getReportDisplay().isDisplayAgendaCode());
        report.setDescription(def.getDescription());
        
        report.setReadOnly(def.isReadOnly());
        
		if (null != def.getGroup()) {
			report.setGroup(def.getGroup().getName());
		}
		else {
			report.setGroup("NONE");
		}
		report.setTeamId(def.getTeamId());
		
		logger.debug("finished getReportDetails");
		return Response.ok(report).build();
	}

	@POST
	@Path("{id}/edit")
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response saveUpdatedReport(@PathParam("id") int id,
			@BadgerFish CustomReportDto dto,
			@Context SecurityContext securityContext){
		User user = userService.getUser(securityContext);
		
		List<ModelItem> modelItems = new ArrayList<ModelItem>();
		for(ModelItemDto mid: dto.getItems()){
			modelItems.add(new ModelItem(mid));
		}

		int reportId = reportService.saveUpdatedReport(id,
		dto.getName(), 
		user, 
        teamService.getTeamById(dto.getTeamId()),
		modelItems, 
		dto.getGroup(),
		dto.getIntervals(), 
		dto.getLayoutLeft(),
		dto.getLayoutTop(),
		dto.isPublicReport(),
		dto.getCompanies(),
		dto.isDisplayCGs(),
		dto.isDisplayUnit(),
		dto.isDisplayBoncode(),
		dto.isDisplayDesc(),
		dto.isDisplayModel(),
		dto.isDisplayCompanyName(), 
		dto.isDisplayCompanyAcronym(),
        dto.getRunTagIds(),
        dto.isDisplayRunName(),
        dto.isDisplayRunDescription(),
        dto.isDisplayTagName(),
        dto.isDisplayAgendaName(),
        dto.isDisplayAgendaCode(),
        dto.isReadOnly(),
        dto.getDescription()); //TODO This is the report description param and should be populated from the DTO - not doing this at the moment as we will need to evaluate how this affects other parts of the system i.e. the Excel plugin.  
        
		
		reportService.buildReport(reportId);
		indexReport(reportId);
		return Response.ok(reportId).build();
	}
	
	//Add a report to the search index
	private Boolean indexReport(int reportId){
		return indexService.index(ReportSearchWrapper.class, reportId);
	}
	
	@POST
	@Path("{id}/publish")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response publishReport(@PathParam("id") int id,
								@Context SecurityContext securityContext){
		logger.debug("starting publishReport");
		// check if the user is allowed to publish this report
		// don't trust that the user is allowed to publish the report because they sent a request
		ReportSummary summary = reportService.getSummaryById(id);
		
		if (!securityContext.isUserInRole(teamService.getTeamById(summary.getTeamId()).getCode())) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		ResponseBuilder responseBuilder = Response.notModified();
		if (reportService.publishReport(id)) {
			responseBuilder = Response.ok();
		} 
		logger.debug("finished publishReport");
		
		return responseBuilder.build();
	}

	@POST
	@Path("{id}/unpublish")
	@Produces({"application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response hideReport(@PathParam("id") int id,
								@Context SecurityContext securityContext){
		logger.debug("starting hideReport");
		// check if the user is allowed to hide this report
		// don't trust that the user is allowed to hide the report just because they sent a request
		ReportSummary summary = reportService.getSummaryById(id);
		
		if (!securityContext.isUserInRole(teamService.getTeamById(summary.getTeamId()).getCode())) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		ResponseBuilder responseBuilder = Response.notModified();
		if (reportService.hideReport(id)) {
			responseBuilder = Response.ok();
		} 
		logger.debug("finished hideReport");
		
		return responseBuilder.build();
	}

	@PUT
	@Produces({"application/json"})
	@Consumes({"application/json"})
	@Path("/MaxConcurrentRunningReports/{maxConcurrentRunningReports}")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response overrideMaxConcurrentRunningReports(@PathParam("maxConcurrentRunningReports") int maxConcurrentRunningReports,
			@Context SecurityContext securityContext) {
		logger.info("Maximum concurrent running reports changed from " + this.maxConcurrentRunningReports + " to " + maxConcurrentRunningReports);
		this.maxConcurrentRunningReports = maxConcurrentRunningReports;
		return Response.ok().build();
	}
	
	private Report runReport(int id, User user, RoleChecker rc, int companyId, int runId, int runModelCompanyTagId, boolean incrementReportsRunning) {
		if (!incrementReportsRunning) {
			try {
				return reportService.runReport(id, user, rc, companyId, runId, runModelCompanyTagId);
			}
			catch (CannotAcquireLockException cale){
				throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
			}
			catch (UnhandledException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
		}
		if (noOfReportsRunning >= maxConcurrentRunningReports) {
			tooManyReports(id, user);
		}
		noOfReportsRunning++;
		logger.info("Number of reports running increased to : " + noOfReportsRunning);
		try {
			return reportService.runReport(id, user, rc, companyId, runId, runModelCompanyTagId);
		}
		catch (CannotAcquireLockException cale){
			throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
		}
		catch (UnhandledException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		finally {
			noOfReportsRunning--;
			logger.info("Number of reports running decreased to : " + noOfReportsRunning);
		}
	}

	private Report runReport(int id, User user, RoleChecker rc, boolean incrementReportsRunning) {
		if (!incrementReportsRunning) {
			try {
				return reportService.runReport(id, user, rc);
			}
			catch (CannotAcquireLockException cale){
				throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
			}
			catch (UnhandledException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
		}
		if (noOfReportsRunning >= maxConcurrentRunningReports) {
			tooManyReports(id, user);
		}
		noOfReportsRunning++;
		logger.info("Number of reports running increased to : " + noOfReportsRunning);
		try {
			return reportService.runReport(id, user, rc);
		}
		catch (CannotAcquireLockException cale){
			throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
		}
		catch (UnhandledException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		finally {
			noOfReportsRunning--;
			logger.info("Number of reports running decreased to : " + noOfReportsRunning);
		}
	}

	private Report runReport(int id, User user, RoleChecker rc, int runId, int runModelCompanyTagId, boolean incrementReportsRunning) {
		if (!incrementReportsRunning) {
			try {
				return reportService.runReport(id, user, rc, runId, runModelCompanyTagId);
			}
			catch (CannotAcquireLockException cale){
				throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
			}
			catch (UnhandledException e) {
				throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
			}
		}
		if (noOfReportsRunning >= maxConcurrentRunningReports) {
			tooManyReports(id, user);
		}
		noOfReportsRunning++;
		logger.info("Number of reports running increased to : " + noOfReportsRunning);
		try {
			return reportService.runReport(id, user, rc, runId, runModelCompanyTagId);
		}
		catch (CannotAcquireLockException cale){
			throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
		}
		catch (UnhandledException e) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		finally {
			noOfReportsRunning--;
			logger.info("Number of reports running decreased to : " + noOfReportsRunning);
		}
	}

	private void tooManyReports(int id, User user) {
		String userMessage = "Report could not be run as there are too many other reports running at present. Please try again later.";
		String logMessage = "Canceled report id: " + id + " for user " + user.getName() + userMessage + " The limit of " + maxConcurrentRunningReports + " reports has been reached.";
		logger.info(logMessage);
		ResponseBuilder responseBuilder = Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(userMessage);
		throw new WebApplicationException(responseBuilder.build());
	}

}

