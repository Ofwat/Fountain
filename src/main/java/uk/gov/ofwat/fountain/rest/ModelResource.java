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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;
import uk.gov.ofwat.fountain.rest.dto.ModelSummaryDto;

@Path("/model")
public class ModelResource extends RestResource {

	
	private ModelService modelService;
	private static Log log = LogFactory.getLog(ModelResource.class);
	private BasketService basketService;
	private ReportService reportService;
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}


	public ModelResource(){
	}
	
	@GET
	@Path("/external")
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getExternalModelSummaryForFinOrFout(@QueryParam("fInputId") int fInputId,
														@QueryParam("fOutputId") int fOutputId,
														@Context SecurityContext securityContext){
		if (0 == fInputId &&
			0 == fOutputId) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("Either fInputId or fOutputId are required.");
			throw new WebApplicationException(responseBuilder.build());
				
		}
		if (0 != fInputId &&
			0 != fOutputId) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("Only fInputId or fOutputId should be sent. Not both.");
			throw new WebApplicationException(responseBuilder.build());
		}

		ModelSummaryDto modelSummaryDto = null;
		if (0 != fInputId) {
			modelSummaryDto = getModelSummaryDto("fin", fInputId);
		}
		else {
			modelSummaryDto = getModelSummaryDto("fout", fOutputId);
		}
			
		ResponseBuilder responseBuilder = Response.ok(modelSummaryDto);
		return responseBuilder.build();
	}	
	
   @GET
   @Path("{id}")
   @Produces({"application/json"})
   @RolesAllowed(value={"OFWAT\\Fountain.Users"})
   public Response getModel(@PathParam("id") int id, @Context UriInfo uriInfo, @Context SecurityContext securityContext) {
	  log.debug("starting getModel");
      Model model = modelService.getModel(id);
      
      if (null == model) {
    	  throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      Collection<Link> tableLinks = createLinks(modelService.getTableAddressesForModelId(id), "table/", uriInfo, securityContext);
      ModelDto modelDto = new ModelDto(model, tableLinks);
      log.debug("finished getModel");
      return Response.ok(modelDto).build();
   }
	   

	@GET
	@Produces({"application/json"})
	@Wrapped(element="Model-list")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getModels(@QueryParam("typeId") int typeId, @Context UriInfo uriInfo){
		log.debug("starting getModels");
		List<ModelSummaryDto> dtos = new ArrayList<ModelSummaryDto>();

		List<ModelType> modelTypes = null;
		if (typeId==0) {
			// 0 - means get all models excluding external models and tag point models. 
			List<ModelType> exclusions = new ArrayList<ModelType>();
			exclusions.add(ModelType.EXTERNAL);
			exclusions.add(ModelType.TAG_POINT);
			modelTypes = modelService.getModelTypes(exclusions);
		}
		else {
			// Otherwise, just use the specified model type
			modelTypes = new ArrayList<ModelType>();
			modelTypes.add(ModelType.getById(typeId));
		}
		
		// Now read for each model type
		for (ModelType mt : modelTypes) {
			for(Model model : modelService.getModels(mt.getId())){
				ModelSummaryDto dto = new ModelSummaryDto(model); 
				setLinkOnResource(model, dto, "model/", uriInfo);
				dtos.add(dto); 
			}
		}

		dtos = sortModelDtosAlphabetically(dtos);
		
		log.debug("finished getModels");
		GenericEntity<List<ModelSummaryDto>> entity = new GenericEntity<List<ModelSummaryDto>>(dtos){};
		return Response.ok(entity).build();	
	}

	private List<ModelSummaryDto> sortModelDtosAlphabetically(List<ModelSummaryDto> dtos) {
		SortedMap<String, ModelSummaryDto> alphaSortedMap = new TreeMap<String, ModelSummaryDto>(); 
		for (ModelSummaryDto dto: dtos) {
			alphaSortedMap.put(dto.getName(), dto);
		}
		return new ArrayList<ModelSummaryDto>(alphaSortedMap.values());
	}

	@GET
	@Produces({"application/json", "application/xml" })
	@Wrapped(element="table-list")
	@Path("{id}/company")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getPageLinks(@PathParam("id") int modelId, @QueryParam("companyId") int companyId, @Context UriInfo uriInfo, @Context SecurityContext securityContext){			
		GenericEntity<List<Link>> entity = new GenericEntity<List<Link>>(createLinks(modelService.getTablesForModelAndCompany(modelId, companyId),"table/", uriInfo, securityContext)){};
		return Response.ok(entity).build();
	}
	
	@GET
	@Produces({"application/json", "application/xml" })
	@Wrapped(element="table-list")
	@Path("{id}/table/structure")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getTableStructureLinks(@PathParam("id") int modelId, @Context UriInfo uriInfo, @Context SecurityContext securityContext){	
		GenericEntity<List<Link>> entity = new GenericEntity<List<Link>>(createLinks(modelService.getTableAddressesForModelId(modelId),"table/", "/structure" , uriInfo, securityContext)){};
		return Response.ok(entity).build();		
	}
	
	@GET
	@Produces({"application/json", "application/xml" })
	@Path("/families")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getModelFamilies(@Context UriInfo uriInfo, @Context SecurityContext securityContext){			
		//GenericEntity<List<Link>> entity = new GenericEntity<List<Link>>(createLinks(modelService.getTablesForModelAndCompany(modelId, companyId),"table/", uriInfo, securityContext)){};
		List<ModelFamily> modelFamilies = modelService.getAllModelFamilies();
		GenericEntity<List<ModelFamily>> entity = new GenericEntity<List<ModelFamily>>(modelFamilies){};
		return Response.ok(entity).build();
	}
		

	@PUT
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response updateModel(@BadgerFish ModelSummaryDto modelSummaryDto,
			                @Context SecurityContext securityContext){

		if (!basketService.getEditsForBranch(modelSummaryDto.getBranch()).isEmpty()) {
			// There are outstanding edits for a model using the branch to be altered.
			throw new WebApplicationException(Response.Status.CONFLICT);
		}

		Model model = modelService.getModel(modelSummaryDto.getId());
		model.setBranch(modelSummaryDto.getBranch());
		model.setCode(modelSummaryDto.getCode());
		model.setDisplayOrder(modelSummaryDto.getDisplayOrder());
		model.setFamily(modelSummaryDto.getFamily());
		model.setModelType(ModelType.getByName(modelSummaryDto.getModelType()));
		model.setName(modelSummaryDto.getName());
		model.setParent(modelSummaryDto.isParent());
		Map<String, ModelInput> modelInputs = new HashMap<String, ModelInput>();
		for (ModelInput modelInput: modelSummaryDto.getModelInputs()) {
			modelInputs.put(modelInput.getCode(), modelInput);
		}
		model.setModelInputs(modelInputs);
		
		modelService.updateModel(model);

		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}
	
	@PUT
	@Path("/external")
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response putExternalModelSummary(@BadgerFish ModelSummaryDto modelSummaryDto,
								@QueryParam("companyId") int companyId,
								@Context SecurityContext securityContext){

		ensureRequiredFieldsArePresent(modelSummaryDto);

		Model existingModel = modelService.getModel(modelSummaryDto.getCode());
		if (modelSummaryDto.getId() == 0) {
			// new model. If there is an existing model for this code then re-use the existing model.
			if (null != existingModel) {
				modelSummaryDto.setId(existingModel.getId());
			}
		}
		else {
			// existing model. check that the code is either: not in use; or is the same model as the one supplied. 
			if (null != existingModel &&
				existingModel.getId() != modelSummaryDto.getId()) {
				ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("model code is already in use by another model");
				throw new WebApplicationException(responseBuilder.build());
			}
		}

		ensureReportsExist(modelSummaryDto);
		ensureReportsAreNotAlreadyInUse(modelSummaryDto);

		
		Model model;
		if (modelSummaryDto.getId() == 0) {
			// new model. Need to create one here.
			
			if (modelSummaryDto.getCode().length() >20) {
				ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
				responseBuilder.entity("Model code must be less than 20 characters");
				throw new WebApplicationException(responseBuilder.build());
			}
			
			String branch = modelService.getParentBranch(modelSummaryDto.getFamily()).getName();
			model = modelService.createModel(	modelSummaryDto.getName(), // name 
												modelSummaryDto.getCode(), //code // Must come from client. Appended with the run number when we implement runs. 
												ModelType.EXTERNAL.getName(), // type 
												modelSummaryDto.getFamily().getCode(), // family // Client should set this. It could be hard coded in the client for IPL. 
												branch, // branch //TODO will need to work this out from run eventually. If there is no model for this branch then we need to clone an existing one from a previous branch. 
												false, // parent
												0, // displayOrder
												RunPlaceHolder.RUN_PLACE_HOLDER.getId()
												);
			if (!modelService.assignExternalModelCompanyReports(model, companyId, modelSummaryDto.getFInputId(), modelSummaryDto.getFOutputId())) {
				//TODO - do we rollback any changes already made to the model???????????????????
				ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
				responseBuilder.entity("Input and output report ids are both the same.");
				throw new WebApplicationException(responseBuilder.build());
			}
//			reportService.addReportItemsToModel(modelSummaryDto.getFOutputId());
		} 
		else {
			// update existing model.
			try {
				model = modelService.getModel(modelSummaryDto.getId());
			} catch (Exception e) {
				ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
				throw new WebApplicationException(responseBuilder.build());
			}
			ModelSummaryDto existingModelSummaryDto = new ModelSummaryDto(model);
			if (!modelSummaryDto.equals(existingModelSummaryDto)) {
				Branch branch = modelService.getParentBranch(modelSummaryDto.getFamily());
				model.setBranch(branch); //TODO will need to work this out from run eventually. Need new ModelRun domain object at this point. 
				model.setCode(modelSummaryDto.getCode()); // Must come from client. Appended with the run number when we implement runs.
				model.setDisplayOrder(0);
				ModelFamily modelFamily = modelService.getModelFamily(modelSummaryDto.getFamily().getCode());
				model.setFamily(modelFamily); // Client should set this. It could be hard coded in the client for IPL.
				model.setModelType(ModelType.EXTERNAL);
				model.setName(modelSummaryDto.getName());
				model.setParent(false);
				Map<String, ModelInput> modelInputs = new HashMap<String, ModelInput>();
				// No model inputs. We need to find these from F_input
				model.setModelInputs(modelInputs);
				if (!modelService.assignExternalModelCompanyReports(model, companyId, modelSummaryDto.getFInputId(), modelSummaryDto.getFOutputId())) {
					//TODO - do we rollback any changes already made to the model???????????????????
					ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
					responseBuilder.entity("Input and output report ids are both the same.");
					throw new WebApplicationException(responseBuilder.build());
				}
				modelService.updateModel(model);
//				reportService.addReportItemsToModel(modelSummaryDto.getFOutputId());
			}
		}

		return Response.ok(model.getId()).build();
	}

	private void ensureReportsExist(ModelSummaryDto modelSummaryDto) {
		if (modelSummaryDto.getFInputId() != 0 &&
			reportService.getSummaryById(modelSummaryDto.getFInputId()) == null) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("Report with id " + modelSummaryDto.getFInputId() + " does not exist.");
			throw new WebApplicationException(responseBuilder.build());
		}
		//Checking to see if we have an external model before performing this check as the FOUTPUTID will be 0!
		if (modelSummaryDto.getFOutputId() != 0 &&
			(modelSummaryDto.getId() != 0) && (reportService.getSummaryById(modelSummaryDto.getFOutputId()) == null)) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
			responseBuilder.entity("Report with id " + modelSummaryDto.getFOutputId() + " does not exist.");
			throw new WebApplicationException(responseBuilder.build());
		}
			
	}

	private void ensureReportsAreNotAlreadyInUse(ModelSummaryDto modelSummaryDto) {
		if (modelService.reportInUseByAnotherModel(modelSummaryDto.getId(), modelSummaryDto.getFInputId())) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
			ModelCompanyReport mrc = modelService.getModelCompanyReport(modelSummaryDto.getFInputId());
			Model modelUsingReport = modelService.getModel(mrc.getModelId());
			responseBuilder.entity("Report (id:" + modelSummaryDto.getFInputId() + ") is already in use by another model (code:" + modelUsingReport.getCode() + ").");
			throw new WebApplicationException(responseBuilder.build());
		}
		if (modelService.reportInUseByAnotherModel(modelSummaryDto.getId(), modelSummaryDto.getFOutputId())) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.CONFLICT);
			ModelCompanyReport mrc = modelService.getModelCompanyReport(modelSummaryDto.getFOutputId());
			Model modelUsingReport = modelService.getModel(mrc.getModelId());
			responseBuilder.entity("Report (id:" + modelSummaryDto.getFInputId() + ") is already in use by another model (code:" + modelUsingReport.getCode() + ").");
			throw new WebApplicationException(responseBuilder.build());
		}
	}

	private void ensureRequiredFieldsArePresent(ModelSummaryDto modelSummaryDto) {
		if (null == modelSummaryDto.getName() ||
			modelSummaryDto.getName().isEmpty()) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("model has no name");
			throw new WebApplicationException(responseBuilder.build());
		}
		if (null == modelSummaryDto.getCode() ||
			modelSummaryDto.getCode().isEmpty()) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("model has no code");
			throw new WebApplicationException(responseBuilder.build());
		}
		if (null == modelSummaryDto.getFamily() ||
			null == modelSummaryDto.getFamily().getCode() ||
			modelSummaryDto.getFamily().getCode().isEmpty()) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.BAD_REQUEST);
			responseBuilder.entity("model has no family");
			throw new WebApplicationException(responseBuilder.build());
		}
	}

	@GET
	@Path("/external/{id}")
	@Consumes({"application/xml", "application/json"})
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getExternalModelSummary(@PathParam("id") int modelId,
										@QueryParam("companyId") int companyId,
//										@QueryParam("runId") int runId,
										@Context SecurityContext securityContext){
		//TODO will need to add 'run' to this.
		//TODO this will error rather than return null.
		Model model;
		try {
			model = modelService.getModel(modelId);
		} catch (Exception e) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
			throw new WebApplicationException(responseBuilder.build());
		}
		if (null == model) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
			throw new WebApplicationException(responseBuilder.build());
		}

		ModelSummaryDto modelSummaryDto = retreiveModelSummaryDto(model, companyId);
		
		ResponseBuilder responseBuilder = Response.ok(modelSummaryDto);
		return responseBuilder.build();
	}
	
	@GET
	@Path("/dependency/{id}")
	@Produces({"application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getModelRunDependencies(@PathParam("id") int modelId, @Context UriInfo uriInfo){
		log.debug("starting getModels deps");
		List<ModelSummaryDto> dtos = new ArrayList<ModelSummaryDto>();
		List<Model> models =  modelService.getModelRunDependencies(modelId);
		for (Model model : models) {
			ModelSummaryDto dto = new ModelSummaryDto(model); 
			setLinkOnResource(model, dto, "model/", uriInfo);
			dtos.add(dto); 
		}
		log.debug("finished getModels deps");
		GenericEntity<List<ModelSummaryDto>> entity = new GenericEntity<List<ModelSummaryDto>>(dtos){};
		return Response.ok(entity).build();			
	}	
	
	@POST
	@Produces({"application/json"})
	@RolesAllowed(value={"OFWAT\\G Fountain Run Admin"})
	public Response addModelRunDependency(@PathParam("id") int modelId, 
										@QueryParam("dependencyId") int dependencyId){
		modelService.addDependency(modelId, dependencyId);
		return null;
	}	

	@DELETE
	@Path("/dependency/{id}")
	//Add a queryParam?
	@Produces({"application/json"})
	@RolesAllowed(value={"OFWAT\\G Fountain Run Admin"})
	public Response removeModelRunDependency(@PathParam("id") int modelId, 
										@QueryParam("dependencyId") int dependencyId){
		//the path param is the model and the dependencyId is well the dependency...
		modelService.removeDependency(modelId, dependencyId);
		return null;
	}		
	
	private ModelSummaryDto getModelSummaryDto(String finfout, int reportId) {
		ModelCompanyReport mcr;
		mcr = modelService.getModelCompanyReport(reportId);
		if (null == mcr) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
			throw new WebApplicationException(responseBuilder.build());
		}
		if (!finfout.equals(mcr.getFinfout())) {
			// We are looking for either in or out. Need to filter out the other.
			ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
			throw new WebApplicationException(responseBuilder.build());
		}
		Model model = modelService.getModel(mcr.getModelId());
		if (null == model) {
			ResponseBuilder responseBuilder = Response.status(Response.Status.NOT_FOUND);
			throw new WebApplicationException(responseBuilder.build());
		}
		ModelSummaryDto modelSummaryDto = retreiveModelSummaryDto(model, mcr.getCompanyId());
		return modelSummaryDto;
	}
	
	private ModelSummaryDto retreiveModelSummaryDto(Model model, int companyId) {
		ModelSummaryDto modelSummaryDto = new ModelSummaryDto(model);
		ModelCompanyReport fInMCR = modelService.getExternalModel("fin", model.getId(), companyId);
		if (null == fInMCR) {
			modelSummaryDto.setFInputId(0);
		}
		else {
			modelSummaryDto.setFInputId(fInMCR.getReportId());
		}
		
		ModelCompanyReport fOutMCR = modelService.getExternalModel("fout", model.getId(), companyId);
		if (null == fOutMCR) {
			modelSummaryDto.setFOutputId(0);
		}
		else {
			modelSummaryDto.setFOutputId(fOutMCR.getReportId());
		}
		return modelSummaryDto;
	}

	
}
