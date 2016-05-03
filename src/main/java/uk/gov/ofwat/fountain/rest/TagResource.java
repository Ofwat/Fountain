package uk.gov.ofwat.fountain.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.api.RunTagService;
import uk.gov.ofwat.fountain.api.TagAllreadyExistsException;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.rest.dto.RunTagDto;

@Path("/tags")
public class TagResource extends RestResource {

	private static Logger logger = LoggerFactory.getLogger(TagResource.class);

	private RunTagService runRunTagService;
	private RunService runService;
	private DataService dataService;
	private ReferenceService referenceService;
	private UserService userService; 
	private static String DATE_FORMAT_STRING = "dd MMM yyyy HH:mm:ss";
	private ModelService modelService;
	private ReportService reportService;
	
	public ModelService getModelService() {
		return modelService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public TagResource() {
	}
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setRunTagService(RunTagService runRunTagService) {
		this.runRunTagService = runRunTagService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	@PUT
	@Path("/data/{tagName}")
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	@Produces({"application/json" })
	public Response createDataTag(@PathParam("tagName") String tagName,
			@QueryParam("runId") int runId, 
			@QueryParam("modelId") int modelId,
			@QueryParam("companyId") int companyId,
			@QueryParam("tagOnCompletion") boolean tagOnCompletion,
			@Context SecurityContext securityContext) {
		User user = userService.getUser(securityContext);
		ResponseBuilder responseBuilder;

		Run run = runService.getRun(runId);
		if (run.isCompleted()) {
			return Response.status(Response.Status.CONFLICT).entity("Cannot complete this model as the run has been completed.").build();
		}
			
		Model model = modelService.getModel(modelId);
		if (model.getCode().equalsIgnoreCase("COMPLETED")) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("'COMPLETED' cannot be tagged.");
			return responseBuilder.build();
		}

		if (!dataService.completeModel(tagName, run.getId(), companyId, modelId, user.getName(), tagOnCompletion)) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("A tag with this name already exists.");
			return responseBuilder.build();
		}
		//TODO Replace this with a DTO
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("user", user.getName());
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		result.put("completedDate", df.format(new Date()));		
		if (tagOnCompletion) {
			result.put("tagged", "1");
		}
		else {
			result.put("tagged", "0");
		}
		result.put("completed", "1");
		responseBuilder = Response.ok(result);
		return responseBuilder.build();
	}
	
	@PUT
	@Path("/data/free/{tagName}")
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	@Produces({"application/json" })
	public Response freeDataTag(@PathParam("tagName") String tagName,
			@QueryParam("runId") int runId, 
			@Context SecurityContext securityContext) {
		User user = userService.getUser(securityContext);
		ResponseBuilder responseBuilder;

		Run run = runService.getRun(runId);
		if (run.isCompleted()) {
			return Response.status(Response.Status.CONFLICT).entity("Cannot tag this run as it has been completed.").build();
		}
			
		try {
			dataService.freeDataTag(tagName, run.getId(), user.getName(), user);
		} catch (TagAllreadyExistsException e) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("A tag with this name already exists.");
			return responseBuilder.build();
		}
		//TODO Replace this with a DTO
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("user", user.getName());
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		result.put("completedDate", df.format(new Date()));		
		responseBuilder = Response.ok(result);
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("/data/{tagName}")
	@Produces({ "application/xml", "application/json" })
	@Consumes({ "application/xml", "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response deleteDataTag(@PathParam("tagName") String tagName,
			@QueryParam("runId") int runId, 
			@QueryParam("modelId") int modelId,
			@QueryParam("companyId") int companyId,
			@Context SecurityContext securityContext) {

		ResponseBuilder responseBuilder;
		Run run = runService.getRun(runId);
		if (run.isCompleted()) {
			return Response.status(Response.Status.CONFLICT).entity("Cannot undo this model as the run has been completed.").build();
		}

		Model model = modelService.getModel(modelId);
		if (model.getCode().equalsIgnoreCase("ORIGIN") ||
			model.getCode().equalsIgnoreCase("COMPLETED") ||
			model.getCode().startsWith("BP_RUNID")) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("This tag cannot be deleted.");
			return responseBuilder.build();
		}
		
		
		RunModel runModel = runService.getRunModel(modelId, run, companyId);
		if (!runModel.isCompleted()) {
			return Response.status(Response.Status.CONFLICT).entity("Cannot undo this model as it has no been completed.").build();
		}

		runService.deleteDataTag(run.getId(), modelId, companyId);
		
		responseBuilder = Response.ok();
		return responseBuilder.build();
	}
	
	@GET
	@Path("/run")
	@Produces({"application/json" })
	@RolesAllowed(value = {"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response listTags(
			@QueryParam("companyId") int companyId,
			@QueryParam("runId") int runId,
			@QueryParam("reportId") int reportId,
			@Context SecurityContext securityContext) {
		logger.debug("Getting tag list");
		
		List<RunTagDto> runTagDtos = new ArrayList<RunTagDto>();
		if (0 == runId) {
			runTagDtos.add(blankRunTagDto());
			return  Response.ok(runTagDtos).build();
		}

		Run run = runService.getRun(runId, false);
		List<RunModelTag> runModelTags = runRunTagService.tagsForRun(run.getId());
		for (RunModelTag runModelTag: runModelTags) {
			runTagDtos.add(new RunTagDto(run, runModelTag)); 
		}

		return  Response.ok(runTagDtos).build();
	}

	private RunTagDto blankRunTagDto() {
		Run blankRun = new Run();
		blankRun.setId(0);
		blankRun.setName("No Runs available");
		Tag blankTag = new Tag();
		blankTag.setId(0);
		blankTag.setDisplayName("No Tags available");
		
		RunTagDto runTagDto = new RunTagDto(RunPlaceHolder.RUN_PLACE_HOLDER, RunModelTag.PLACE_HOLDER);
		return runTagDto;
	}
}
