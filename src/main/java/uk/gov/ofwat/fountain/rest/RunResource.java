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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.BranchNotEditableException;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.RunCompanyTemplateService;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.api.RunTemplateService;
import uk.gov.ofwat.fountain.api.RunTagService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;
import uk.gov.ofwat.fountain.rest.dto.DatasourceDto;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.dto.RunDto;
import uk.gov.ofwat.fountain.rest.dto.RunModelCompanyDto;
import uk.gov.ofwat.fountain.rest.dto.RunModelDto;
import uk.gov.ofwat.fountain.rest.dto.RunTemplateDto;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;

@Path("/runs")
public class RunResource extends RestResource {

	private static Logger logger = LoggerFactory.getLogger(RunResource.class);

	private RunTagService runTagService;
	private RunService runService;
	private ModelService modelService;
	private RunTemplateService runTemplateService;
	private RunCompanyTemplateService runCompanyTemplateService;
	private UserService userService;
	private ReferenceService referenceService;
	private static String DATE_FORMAT_STRING = "dd MMM yyyy HH:mm:ss";

	public RunResource() {
	}
	
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setRunTemplateService(RunTemplateService runTemplateService) {
		this.runTemplateService = runTemplateService;
	}

	public void setRunCompanyTemplateService(RunCompanyTemplateService runCompanyTemplateService) {
		this.runCompanyTemplateService = runCompanyTemplateService;
	}

	@GET
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response listRuns(	@Context SecurityContext securityContext,
								@QueryParam("agendaId") int agendaId,
								@QueryParam("standardRole") boolean standardRole,
								@QueryParam("defaultRole") boolean defaultRole,
								@QueryParam("proxyRole") boolean proxyRole
								) {
		logger.debug("Getting run list");

		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		User user = userService.getUser(securityContext);

		// If no roles are specified then all runs are returned unfiltered. 
		// If roles are specified then runs are returned for the specified roles.

		List<RunRole> roles = new ArrayList<RunRole>();
		if (standardRole) {
			roles.add(RunRole.STANDARD);			
		}
		if (defaultRole) {
			roles.add(RunRole.DEFAULT); 
		}
		if (proxyRole) {
			roles.add(RunRole.PROXY);			
		}
		
		List<Run> runs = null;
		if (0 == agendaId) {
			runs = runService.getRoleFilteredRunsOrderedOnAgenda(roles);
		}
		else {
			runs = runService.getRoleFilteredRunsByAgenda(agendaId, roles);
		}

		//Build a list of RunDtos.
		List<RunDto> runDtos = new ArrayList<RunDto>();
		for(Run run : runs){
			if (!run.isAdminOnly() || roleChecker.isUserInRole(User.FOUNTAIN_ADMIN_ROLE) ) {
				RunDto runDto = new RunDto(run, DATE_FORMAT_STRING, referenceService);
				runDtos.add(runDto);
			}
		}
		
		if (runs.isEmpty()) {
			RunDto runDto = new RunDto();
			runDto.setCompleted(false);
			runDto.setId(0);
			runDto.setName("No Runs available");
			runDtos.add(runDto);
		}
		ResponseBuilder responseBuilder = Response.ok(runDtos);
		return responseBuilder.build();
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRun(@PathParam("id") int id) {
		logger.debug("Getting Individual Run data");
		RunDto runDto = new RunDto(runService.getRun(id), DATE_FORMAT_STRING, referenceService);
		ResponseBuilder responseBuilder = Response.ok(runDto);
		return responseBuilder.build();
	}
	
	@POST
	@Path("/default/{id}")
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response changeDefaultRun(@PathParam("id") int id) {
		runService.setDefaultRun(id);
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@POST
	@Path("/{id}/toggleAdminOnly")
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response toggleAdminOnly(@PathParam("id") int id) {
		runService.toggleRunAdminOnly(id);
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@GET
	@Path("{id}/models")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRunModels(@PathParam("id") int runId) {
		logger.debug("Getting Individual Run data with run models");
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);

		Run run = runService.getRun(runId);
		RunDto runDto = new RunDto(run, DATE_FORMAT_STRING, referenceService);
		
		List<RunModel> runModels = runService.getRunModelsByRun(run.getId());
		List<RunModelDto> runModelDtos = new ArrayList<RunModelDto>();
		
		//The run models returned include company so I will have to manually make them into the required format
		//for rendering in the page to edit in the UI. i.e.
		//run/runModels/runModelCompanies (group by model then company!)
		for (RunModel rm : runModels) {
			if (modelService.getModel(rm.getModelId()).isParent()) {
				continue; // Exclude parents
			}
			if (modelService.getModel(rm.getModelId()).getModelType() != ModelType.EXTERNAL) {
				continue; // Exclude TAG_POINT models
			}
			boolean add = true;
			RunModelDto runModelDto = new RunModelDto();
			runModelDto.setId(rm.getId());
			Model model = modelService.getModel(rm.getModelId());
			ModelDto modelDto = new ModelDto();
			modelDto.setBranch(model.getBranch());
			modelDto.setCode(model.getCode());
			modelDto.setDisplayOrder(model.getDisplayOrder());
			modelDto.setId(model.getId());
			modelDto.setModelTypeId(model.getModelType().getId());
			modelDto.setName(model.getName());
			runModelDto.setModel(modelDto);
			runModelDto.setRunOrder(rm.getRunOrder());
			//Check if we already have the run model in our runModelDtos list.
			for(RunModelDto rmdto : runModelDtos){
				if(rmdto.getModel().getId() == rm.getModelId()){
					runModelDto = rmdto;
					add = false;
				}
			}
			
			ArrayList<RunModelCompanyDto> rmcDtos;
			//Check if the runModelDto has a list of runModelCompanies already.
			if(runModelDto.getRunModelCompanies() != null){
				rmcDtos = runModelDto.getRunModelCompanies();
			}else{
				rmcDtos = new ArrayList<RunModelCompanyDto>();	
			}
			
			//Iterate over the company and if the current is not there add a new one!
			boolean addRmc = true;
			for(RunModelCompanyDto rmcDto : rmcDtos){
				if(rmcDto.getCompanyId() == rm.getCompanyId()){
					//we already have it!
					addRmc = false;
					break;
				}
			}
			if(addRmc){
				RunModelCompanyDto rmcDto = new RunModelCompanyDto();
				Company company = referenceService.getCompany(rm.getCompanyId());
				rmcDto.setCompanyId(company.getId());
				rmcDto.setCompanyName(company.getName());
				rmcDto.setCompleted(rm.isCompleted());
				rmcDto.setCompletedBy(rm.getCompletedBy());
				if(rm.getCompletedDate() != null){
					rmcDto.setCompletedDate(df.format(rm.getCompletedDate()));
				}
				
				rmcDto.setTagged(runService.doesTagExist(run.getId(), company.getId(), model.getId()));
				rmcDtos.add(rmcDto);
			}
			
			runModelDto.setRunModelCompanies(rmcDtos);
			if(add){
				runModelDtos.add(runModelDto);
			}
		}
		runDto.setRunModels(runModelDtos);
		ResponseBuilder responseBuilder = Response.ok(runDto);
		return responseBuilder.build();
	}

	@POST
	@Path("/{runName}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response createRun(@PathParam("runName") String runName,
			@QueryParam("agendaId") int agendaId,
			@QueryParam("sourceRunId") int sourceRunId,
			@QueryParam("sourceTagId") int sourceTagId,
			@QueryParam("runTemplateId") int runTemplateId,
			@QueryParam("runCompanyTemplateId") int runCompanyTemplateId,
			@QueryParam("runDescription") String runDescription,
			@QueryParam("defaultRun") boolean defaultRun,
			@Context SecurityContext securityContext) {
		
		User user = userService.getUser(securityContext);
		
		ResponseBuilder responseBuilder;
		if (runName.length() > 42) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The name of this run is too long. Please limit the name of the run to 42 characters.";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		RunCompanyTemplate runCompanyTemplate = runCompanyTemplateService.getRunCompanyTemplate(runCompanyTemplateId);
		if (null == runCompanyTemplate ||
			runCompanyTemplate.getCompanyIds().isEmpty()) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The Company Set used is invalid. It may have no companies.";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		Run run;
		try {
			run = runService.composeRun(runName, runDescription, agendaId, sourceRunId, sourceTagId, runTemplateId, defaultRun, user, runCompanyTemplateId);
		} catch (BranchNotEditableException e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("BRANCH_NOT_EDITABLE").build());
		}
		if (null == run) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("PRE_EXISTING_EDIT_SESSION");
			return responseBuilder.build();
		}
		responseBuilder = Response.ok(run);
		return responseBuilder.build();
	}

	@PUT
	@Path("/{runId}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response completeRun(@PathParam("runId") int runId,
								@QueryParam("completed") boolean completed,
								@Context SecurityContext securityContext) {
		User user = userService.getUser(securityContext);
		ResponseBuilder responseBuilder;
		
		Run run = runService.getRun(runId);
		if (null == run) {
			responseBuilder = Response.status(Response.Status.NOT_FOUND);
			return responseBuilder.build();
		}

		if (run.isUpdating()) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("Run '" + run.getName() + "' is currently being updated. Please try again later.");
			return responseBuilder.build();
		}
		
		if (completed) {
			if (run.isCompleted()) {
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("Run '" + run.getName() + "' has already been completed.");
				return responseBuilder.build();
			}
			try {
				runService.setRunUpdatingFlag(run.getId(), user, true);
				runService.completeAndTagRun(run.getId(), user);
			} catch (RuntimeException e) {
				String errMsg = "Unable to complete run: " + run.getName(); 
				logger.error(errMsg);
				e.printStackTrace();
				responseBuilder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
				responseBuilder.entity(errMsg);
				return responseBuilder.build();
			} finally {
				runService.setRunUpdatingFlag(run.getId(), user, false);
			}
		}
		else {
			if (!run.isCompleted()) {
				responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("Run '" + run.getName() + "' has not been completed.");
				return responseBuilder.build();
			}
			try {
				runService.setRunUpdatingFlag(run.getId(), user, true);
				runService.uncompleteAndTagRun(run.getId(), user);
			} catch (RuntimeException e) {
				String errMsg = "Unable to uncomplete run: " + run.getName(); 
				logger.error(errMsg);
				e.printStackTrace();
				responseBuilder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
				responseBuilder.entity(errMsg);
				return responseBuilder.build();
			} finally {
				runService.setRunUpdatingFlag(run.getId(), user, false);
			}
		
		}

		
		responseBuilder = Response.ok(run);
		return responseBuilder.build();
	}

//	@PUT
//	@Produces({ "application/xml", "application/json" })
//	@Consumes({ "application/xml", "application/json" })
//	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
//	public Response createUpdateRun(@BadgerFish RunDto runDto,
//			@Context SecurityContext securityContext) {
//		return null;
//	}

//	@DELETE
//	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
//	public Response deleteRun(@QueryParam("runId") int runId,
//			@Context SecurityContext securityContext) {
//		return null;
//	}

	@GET
	@Path("/templates")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response listRunTemplates() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		List<RunTemplateDto> runTemplateDtos = new ArrayList<RunTemplateDto>();
		List<RunTemplate> runTemplates = runTemplateService.getAllRunTemplates();
		// Put 'No model' first in the list.
		for (RunTemplate rt : runTemplates) {
			RunTemplateDto rtdto = new RunTemplateDto();
			if (rt.getName().equals("No model")) {
				// populate the vals.
				rtdto.setCreatedAt(df.format(rt.getCreated()));
				rtdto.setCreatedBy(rt.getCreatedBy());
				rtdto.setDescription(rt.getDescription());
				rtdto.setId(rt.getId());
				rtdto.setName(rt.getName());
				rtdto.setModels(buildModelDtos(rt.getModels()));
				runTemplateDtos.add(rtdto);
			}
		}
		for (RunTemplate rt : runTemplates) {
			RunTemplateDto rtdto = new RunTemplateDto();
			if (!rt.getName().equals("No model")) {
				// populate the vals.
				rtdto.setCreatedAt(df.format(rt.getCreated()));
				rtdto.setCreatedBy(rt.getCreatedBy());
				rtdto.setDescription(rt.getDescription());
				rtdto.setId(rt.getId());
				rtdto.setName(rt.getName());
				rtdto.setModels(buildModelDtos(rt.getModels()));
				runTemplateDtos.add(rtdto);
			}
		}
		Map<String, List<RunTemplateDto>> wrapper = new HashMap<String, List<RunTemplateDto>>();
		wrapper.put("runTemplates", runTemplateDtos);
		ResponseBuilder responseBuilder = Response.ok(wrapper);
		return responseBuilder.build();
	}

	@GET
	@Path("/templates/{id}")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRunTemplate(@PathParam("id") int id) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		RunTemplateDto runTemplateDto = new RunTemplateDto();
		RunTemplate runTemplate = runTemplateService.getRunTemplate(id);
		runTemplateDto.setCreatedAt(df.format(runTemplate.getCreated()));
		runTemplateDto.setCreatedBy(runTemplate.getCreatedBy());
		runTemplateDto.setDescription(runTemplate.getDescription());
		runTemplateDto.setId(runTemplate.getId());
		runTemplateDto.setModels(buildModelDtos(runTemplate.getModels()));
		runTemplateDto.setName(runTemplate.getName());
		ResponseBuilder responseBuilder = Response.ok(runTemplate);
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("/templates/{id}")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response deleteRunTemplate(@PathParam("id") int id) {
		boolean result = runTemplateService.deleteRunTemplate(id);
		ResponseBuilder responseBuilder;
		if(result){
			responseBuilder = Response.ok();
		}else{
			responseBuilder = Response.notModified();
		}
		return responseBuilder.build();
	}	

	// Helper to build ModelDtos from Model list.
	private List<ModelDto> buildModelDtos(List<Model> models) {
		List<ModelDto> modelDtos = new ArrayList<ModelDto>();
		for (Model model : models) {
			ModelDto mdto = new ModelDto();
			mdto.setBranch(model.getBranch());
			mdto.setCode(model.getCode());
			// mdto.setCompany();
			mdto.setDisplayOrder(model.getDisplayOrder());
			mdto.setId(model.getId());
			mdto.setModelTypeId(model.getModelType().getId());
			mdto.setName(model.getName());
			// mdto.setTableLinks();
			modelDtos.add(mdto);
		}
		return modelDtos;
	}

	@GET
	@Path("/templates/{id}/models")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRunTemplateModels(@PathParam("id") int id) {
		List<ModelDto> models = new ArrayList<ModelDto>();
		try {
			RunTemplate runTemplate = runTemplateService.getRunTemplate(id);
			for (Model model : runTemplate.getModels()) {
				ModelDto modelDto = new ModelDto();
				modelDto.setName(model.getName());
				modelDto.setId(model.getId());
				modelDto.setCode(model.getCode());
				models.add(modelDto);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ResponseBuilder responseBuilder = Response.ok(models);
		return responseBuilder.build();
	}

	@POST
	@Path("/templates/{id}/models")
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response addRunTemplateModel(@PathParam("id") int id, List<HashMap<String, Integer>> listMap) {
		//Remove all models from a runTemplate. 
		RunTemplate rt = runTemplateService.getRunTemplate(id);
		runTemplateService.removeAllModels(id);
		//Add all the new ones.
		for(HashMap<String, Integer> map : listMap){
			runTemplateService.addModel(map.get("id") , rt.getId(), map.get("displayOrder"));
		}
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@DELETE
	@Path("/templates/{id}/models")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response removeRunTemplateModel(@PathParam("id") int id,
			@QueryParam("modelId") int modelId) {
		runTemplateService.removeModel(modelId, id);
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@PUT
	@Path("/templates")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response createUpdateRunTemplate(
			@FormParam("name") String runTemplateName,
			@FormParam("description") String runTemplateDescription,
			@Context SecurityContext securityContext) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		RunTemplate runTemplate = new RunTemplate();
		runTemplate.setCreated(new Date());
		runTemplate.setCreatedBy(userService.getUser(securityContext).getName());
		runTemplate.setDescription(runTemplateDescription);
		runTemplate.setName(runTemplateName);
		runTemplateService.createRunTemplate(runTemplate);

		RunTemplateDto runTemplateDto = new RunTemplateDto();
		runTemplateDto.setCreatedAt(df.format(runTemplate.getCreated()));
		runTemplateDto.setCreatedBy(runTemplate.getCreatedBy());
		runTemplateDto.setDescription(runTemplate.getDescription());
		runTemplateDto.setName(runTemplate.getName());
		runTemplateDto.setId(runTemplate.getId());
		ResponseBuilder responseBuilder = Response.ok(runTemplateDto);
		return responseBuilder.build();
	}

//	@DELETE
//	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
//	public Response deleteRunTemplate(
//			@QueryParam("runTemplateId") int runTemplateId,
//			@Context SecurityContext securityContext) {
//		return null;
//	}

	@GET
	@Path("/datasources")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response listRunDatasources() {
		// Get a list of datasources... This is a list of runs excluding the
		// passed run.
		// It should also include ofwat view? - id 0!

		// Mock a list of datasources.
		List<DatasourceDto> datasources = new ArrayList<DatasourceDto>();
		for (int i = 1; i <= 5; i++) {
			DatasourceDto ds = new DatasourceDto();
			ds.setName("Datasource " + i);
			ds.setId(i);
			datasources.add(ds);
		}
		Map<String, List<DatasourceDto>> wrapper = new HashMap<String, List<DatasourceDto>>();
		wrapper.put("dataSources", datasources);
		ResponseBuilder responseBuilder = Response.ok(wrapper);
		return responseBuilder.build();
	}

	
// run company template

	@GET
	@Path("/companyTemplate")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response listRunCompanyTemplates() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
//		List<RunCompanyTemplateDto> runCompanyTemplateDtos = new ArrayList<RunCompanyTemplateDto>();
		List<RunCompanyTemplate> runCompanyTemplates = runCompanyTemplateService.getAllRunCompanyTemplates();
//		for (RunCompanyTemplate rct : runCompanyTemplates) {
//			RunCompanyTemplateDto rctdto = new RunCompanyTemplateDto();
//			// populate the vals.
//			rctdto.setCreatedAt(df.format(rct.getCreated()));
//			rctdto.setCreatedBy(rct.getCreatedBy());
//			rctdto.setDescription(rct.getDescription());
//			rctdto.setId(rct.getId());
//			rctdto.setName(rct.getName());
//			rctdto.setModels(buildModelDtos(rct.getCompanies()));
//			runCompanyTemplateDtos.add(rctdto);
//		}
		Map<String, List<RunCompanyTemplate>> wrapper = new HashMap<String, List<RunCompanyTemplate>>();
		wrapper.put("runCompanyTemplates", runCompanyTemplates);
		ResponseBuilder responseBuilder = Response.ok(wrapper);
		return responseBuilder.build();
	}

	@GET
	@Path("/companyTemplate/{id}")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRunCompanyTemplate(@PathParam("id") int id) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
//		RunCompanyTemplateDto runCompanyTemplateDto = new RunCompanyTemplateDto();
		RunCompanyTemplate runCompanyTemplate = runCompanyTemplateService.getRunCompanyTemplate(id);
//		runCompanyTemplateDto.setCreatedAt(df.format(runCompanyTemplate.getCreated()));
//		runCompanyTemplateDto.setCreatedBy(runCompanyTemplate.getCreatedBy());
//		runCompanyTemplateDto.setDescription(runCompanyTemplate.getDescription());
//		runCompanyTemplateDto.setId(runCompanyTemplate.getId());
//		runCompanyTemplateDto.setCompanies(buildModelDtos(runCompanyTemplate.getCompanies()));
//		runCompanyTemplateDto.setName(runCompanyTemplate.getName());
		ResponseBuilder responseBuilder = Response.ok(runCompanyTemplate);
		return responseBuilder.build();
	}
	
	@DELETE
	@Path("/companyTemplate/{id}")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response deleteRunCompanyTemplate(@PathParam("id") int id) {
		boolean result = runCompanyTemplateService.deleteRunCompanyTemplate(id);
		ResponseBuilder responseBuilder;
		if(result){
			responseBuilder = Response.ok();
		}else{
			responseBuilder = Response.notModified();
		}
		return responseBuilder.build();
	}	

	@GET
	@Path("/companyTemplate/{id}/company")
	@Produces({ "application/json" })
	@RolesAllowed(value = { "ROLE_OFWAT\\FOUNTAIN.USERS" })
	public Response getRunCompanyTemplateModels(@PathParam("id") int id) {
		List<Company> companies = new ArrayList<Company>();
		RunCompanyTemplate runCompanyTemplate = runCompanyTemplateService.getRunCompanyTemplate(id);
		ResponseBuilder responseBuilder = Response.ok(runCompanyTemplate.getCompanies());
		return responseBuilder.build();
	}

	@POST
	@Path("/companyTemplate/{id}/company")
	@Consumes({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response addRunCompanyTemplateModel(@PathParam("id") int id, List<HashMap<String, Integer>> listMap) {

		RunCompanyTemplate rt = runCompanyTemplateService.getRunCompanyTemplate(id);
		runCompanyTemplateService.removeAllCompanies(id);

		for(Map<String, Integer> map : listMap){
			runCompanyTemplateService.addCompany(map.get("id") , rt.getId());
		}
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@DELETE
	@Path("/companyTemplate/{id}/company")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response removeRunCompanyTemplateModel(@PathParam("id") int id,
			@QueryParam("companyId") int companyId) {
		runCompanyTemplateService.removeCompany(companyId, id);
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}

	@PUT
	@Path("/companyTemplate")
	@Produces({ "application/json" })
	@RolesAllowed(value={"ROLE_OFWAT\\G FOUNTAIN RUN ADMIN"})
	public Response createUpdateRunCompanyTemplate(
			@FormParam("name") String runCompanyTemplateName,
			@FormParam("description") String runCompanyTemplateDescription,
			@Context SecurityContext securityContext) {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
		RunCompanyTemplate runCompanyTemplate = new RunCompanyTemplate();
		runCompanyTemplate.setCreated(new Date());
		runCompanyTemplate.setCreatedBy(userService.getUser(securityContext).getName());
		runCompanyTemplate.setDescription(runCompanyTemplateDescription);
		runCompanyTemplate.setName(runCompanyTemplateName);
		runCompanyTemplateService.createRunCompanyTemplate(runCompanyTemplate);

//		RunCompanyTemplateDto runCompanyTemplateDto = new RunCompanyTemplateDto();
//		runCompanyTemplateDto.setCreatedAt(df.format(runCompanyTemplate.getCreated()));
//		runCompanyTemplateDto.setCreatedBy(runCompanyTemplate.getCreatedBy());
//		runCompanyTemplateDto.setDescription(runCompanyTemplate.getDescription());
//		runCompanyTemplateDto.setName(runCompanyTemplate.getName());
//		runCompanyTemplateDto.setId(runCompanyTemplate.getId());
		ResponseBuilder responseBuilder = Response.ok(runCompanyTemplate);
		return responseBuilder.build();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}