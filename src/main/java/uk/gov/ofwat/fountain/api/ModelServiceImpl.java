/*
ode *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
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

package uk.gov.ofwat.fountain.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.ModelDao;
import uk.gov.ofwat.fountain.dao.ModelFamilyDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.dao.PotDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Addressable;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelCompanyReport;
import uk.gov.ofwat.fountain.domain.ModelFamily;
import uk.gov.ofwat.fountain.domain.ModelInput;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.formula.ProcessingException;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

/**
 * Responsible for all operations to do with Models, tables and Pots and reports.
 * I.e. all the structural relationships between elements on a table.  
 */
public class ModelServiceImpl implements ModelService {
    
	private static Log log = LogFactory.getLog(ModelServiceImpl.class);

	private ModelDao modelDao;
	private ModelFamilyDao modelFamilyDao;
	private PotDao potDao;
	private TableDao tableDao;
	private ModelPropertiesMapDao modelPropertiesMapDao;
	private BranchDao branchDao;	
	
	private TableService tableService;	
	private ItemService itemService;
	private DataService dataService;
	private ReferenceService referenceService;
	private GroupService groupService;
	private RunTagService runTagService;
	private Boolean disablePreLoad = false;

	
	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public Boolean getDisablePreLoad() {
		return disablePreLoad;
	}
	
    public void init() {
    	if(disablePreLoad){
    		log.info("Model preload is OFF.");
    	} else {
    		log.info("Model preload has been overridden and is OFF.");
//    		for(Model model : getModels()) {
//        		log.info("Preloading model " + model.getName() + " ...");
//    			model.getTables(); // cache pots and items
//        		log.info("Preloaded model " + model.getName());
//    		}
    	}
	}

	public BranchDao getBranchDao() {
		return branchDao;
	}
	
	/**
	 * avoid preloading models on deployment - convenient for development.
	 * @param disablePreLoad
	 */
	public void setDisablePreLoad(Boolean disablePreLoad) {
		this.disablePreLoad = disablePreLoad;
	}

	public TableService getTableService() {
		return tableService;
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	public void setModelFamilyDao(ModelFamilyDao modelFamilyDao) {
		this.modelFamilyDao = modelFamilyDao;
	}

	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void setItemService(ItemService itemService){
		this.itemService = itemService;
	}
	
	public void setModelDao(ModelDao modelDao) {
		this.modelDao = modelDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public void setPotDao(PotDao potDao) {
		this.potDao = potDao;
	}
	
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setModelPropertiesMapDao(ModelPropertiesMapDao modelPropertiesMapDao) {
		this.modelPropertiesMapDao = modelPropertiesMapDao;
	}

	public Model getModel(int id) {
		Model m = modelDao.findById(id);
		setBranchOnModel(m);
		setServicesOnModel(m);
		return m;
	}
	
	public Model getModel(String code) {
		Model m = modelDao.findByCode(code);
		if (null == m) {
			return null;
		}
		setBranchOnModel(m);
		setServicesOnModel(m);
		return m;
	}

	public Model getModelByName(String name) {
		Model m = modelDao.getModelByName(name);
		if (null == m) {
			return null;
		}
		setBranchOnModel(m);
		setServicesOnModel(m);
		return m;
	}

	
	public List<ModelFamily> getAllModelFamilies(){
		List<ModelFamily> modelFamilies = modelFamilyDao.findAll();
		return modelFamilies;
	}
	
//	public Model getModelByName(String name) {
//		List<Model> models = getModels();
//		for (Model model: models) {
//			if (name.equals(model.getName())) {
//				return model;
//			}
//		}
//		return null;
//	}
	public List<Model> getModels(){
		List<Model> models = modelDao.getAllModels();
		setBranchOnModels(models);
		setServicesOnModels(models);
		return models;
	}
	public List<Model> getModels(int modelTypeId){
		List<Model> models = modelDao.getAllModels(modelTypeId);
		setBranchOnModels(models);
		setServicesOnModels(models);
		return models;
	}
	public List<ModelType>getModelTypes(){
		return modelDao.getAllModelTypes();
	}
	
	public List<ModelType>getModelTypes(List<ModelType> excludedTypes){
		List<ModelType> modelTypes = modelDao.getAllModelTypes();
		List<ModelType> filteredModelTypes = new ArrayList<ModelType>();
		for(ModelType modelType : modelTypes){
			if(!excludedTypes.contains(modelType)){
				filteredModelTypes.add(modelType);
			}
		}
		return filteredModelTypes;		
	}
	
	public Table getTable(int id) {
		Table t = tableDao.findById(id);
		t.setPots(getPotsForTable(id));
		return t;
	}

	public List<Table> getTablesForModel(int modelId){
		List<Table> tables = tableDao.findByModelId(modelId);
		for(Table t: tables){
			t.setPots(getPotsForTable(t.getId()));
		}
		return tables;
	}
	
	
	
	public List<? extends Addressable> getTableAddressesForModelId(int modelId) {
		return tableDao.findByModelId(modelId);	
	}


	public List<Pot> getPotsForTable(int tableId){
		List<Pot> pots = potDao.findByTableId(tableId);
		for(Pot p: pots){
			p.setDataService(dataService);
			p.setModelService(this);
			p.setReferenceService(referenceService);
			p.setItemService(itemService);
			p.setGroupService(groupService);
			p.setInterval(referenceService.getInterval(p.getInterval().getId()));
			p.setBranchDao(branchDao);
			RunTag runTag = runTagService.getRunTag(p.getRunId(), RunModelTag.LATEST.getId());
			p.setRunTag(runTag);
		}
		return pots;
	}
	
	public Pot createPot(Model model, String code,  Interval interval, int runId) {
		Pot pot;
		ItemProperties itemProperties = model.getItemPropertiesForItem(code);
		
		pot = new Pot();
		pot.setInterval(interval);
		pot.setModelId(model.getId());
		pot.setModelService(this);
		pot.setItem(itemProperties.getItem());
		pot.setDataService(dataService);
		pot.setReferenceService(referenceService);
		pot.setItemService(itemService);
		pot.setGroupService(groupService);
		pot.setModelPropertiesMap(modelPropertiesMapDao.findByModelAndItem(model.getId(), pot.getItem().getId()));
		pot.setBranchDao(branchDao);
		RunTag runTag = runTagService.getRunTag(runId, RunModelTag.LATEST.getId());
		pot.setRunTag(runTag);
		return pot;
	}

	private void setBranchOnModels(List<Model> models) {
		for(Model m: models){
			setBranchOnModel(m);
		}
	}

	private void setBranchOnModel(Model m) {
		Branch branch = branchDao.findById(m.getBranchId());
		m.setBranch(branch);
	}

	private void setServicesOnModels(List<Model> models) {
		for(Model m: models){
			setServicesOnModel(m);
		}
	}

	private void setServicesOnModel(Model m) {
		m.setModelService(this);
		m.setItemService(itemService);
	}

	
	public Model createModel(String name, String code, String type, String family, String branch, boolean parent, int displayOrder, int runId) {
		return createModel(null, name, code, type, family, branch, parent, displayOrder, runId);
	}

	public Model createModel(Integer id, String name, String code, String type, String family, String branch, boolean parent, int displayOrder, int runId) {
		ModelType mt = null;
		try {
			mt = modelDao.findModelTypeByName(type);
		}
		catch (EmptyResultDataAccessException ex) {}
		if(null == mt){
			throw new RuntimeException("Unable to find modelType for " + type);
		}

		Branch bt = null;
		if (null != branch) {
			bt = branchDao.findByName(branch);
			if (null == bt) {
				bt = branchDao.create(branch);
			}
		}
		
		ModelFamily modelFamily = null;
		if(null != family && !"".equals(family)){
			modelFamily = modelFamilyDao.findByCode(family);
			if(null == modelFamily){
				// model refers to a non-existent family. This is not the place to create a model Family so throw an error
				throw new ProcessingException("Cannot find model family with code " + family);
			}
		}
		Model model = new Model(name, code, mt);
		model.setBranch(bt);
		model.setRunId(runId);
		model.setDisplayOrder(displayOrder);
		if (null == id) {
			// this is not a re-import of a model - it's a new one
			if(null != modelFamily ){
				// there is a recognised model family for this model
				if(parent){
					model.setParent(true);
					// and the new model want's to be the parent of the family
					Model oldParent = modelDao.getFamilyParentModel(modelFamily.getId());
					if(null != oldParent ){
						// error situation - trying top replace an existing parent model
						// for this family. The importer is not the place to do this.
						throw new ProcessingException("Model family " + family + " already has a parent model " + oldParent.getName());
					}
				}
				model.setFamily(modelFamily);
			}
			model.setId(modelDao.create(model));
		}
		else {
			// re-import. make sure it's not trying to usurp the existing parent of a model family
			model.setId(id);
			if(null != modelFamily){
				// incoming model is part of a family
				if(parent){
					model.setParent(true);
					// ...and wants to be a parent
					Model oldParent = modelDao.getFamilyParentModel(modelFamily.getId());
					if(null != oldParent && id != oldParent.getId()){
						// ...but there's already a different parent 
						throw new ProcessingException("Attempting to change the parent of model family " + modelFamily.getName());
					}
				}
				model.setFamily(modelFamily);
			}		
			model.setFamily(modelFamily);
			model.setId(modelDao.create(id, model));
		}
		return model;
	}

	public boolean assignExternalModelCompanyReports(Model model, int companyId, int fInputId, int fOutputId) {
		if (fInputId > 0 &&
			fInputId == fOutputId) {
			return false;
		}
		if (ModelType.EXTERNAL == model.getModelType()) {
			assignModelCompanyReport("fin", model, companyId, fInputId);
			assignModelCompanyReport("fout", model, companyId, fOutputId);
		}
		return true;
	}

	private void assignModelCompanyReport(String direction, Model model, int companyId, int reportId) {
		// Deal with zero report id.
		if (0 == reportId) {
			// reportId is not a valid report so we don't add it.
			// but this may be an instruction to remove the association with this report.
			modelDao.deleteModelCompanyReport(direction, model.getId(), companyId);
			return;
		}
		// Deal with situation where a report is already attached to the model for this direction.
		ModelCompanyReport reportAssignedForDirectionMCR = modelDao.findModelCompanyReport(direction, model.getId(), companyId);
		if (null != reportAssignedForDirectionMCR) {
			// The model & company & direction already have a report 
			if (reportAssignedForDirectionMCR.getReportId() == reportId) {
				// The model & company & direction already have THE report. There is no change here. 
				return;
			}
			// The model & company & direction already have ANOTHER report. Remove this association before adding another. 
			modelDao.deleteModelCompanyReport(direction, model.getId(), companyId);
		}

		// Deal with situation where a report is already attached to the model for the other direction. 
		String otherDirection = ("fin" == direction) ? "fout" : "fin";
		ModelCompanyReport reportAssignedForOtherDirectionMCR = modelDao.findModelCompanyReport(otherDirection, model.getId(), companyId);
		if (null != reportAssignedForOtherDirectionMCR) {
			// The model & company & OTHER direction already have a report 
			if (reportAssignedForOtherDirectionMCR.getReportId() == reportId) {
				// The model & company & OTHER direction already have THE report. Remove this association before adding one for THE direction.  
				modelDao.deleteModelCompanyReport(otherDirection, model.getId(), companyId);
			}
			// The model & company & OTHER direction already have ANOTHER report. This is OK. 
		}
		modelDao.createModelCompanyReport(direction, model.getId(), companyId, reportId);
	}

	public boolean reportInUseByAnotherModel(int modelId, int reportId) {
		ModelCompanyReport mrc = getModelCompanyReport(reportId);
		if (null == mrc) return false;
		if (modelId == mrc.getModelId()) return false;
		return true;
	}
	
	public int addTableToModel(int modelId, Table table) {
		Model model = getModel(modelId);
		table.setModelId(modelId);
		table.setId(tableDao.create(table));
		model.getTables().add(table);
		modelDao.update(model);
		return table.getId();
	}

	public String getModelCodeForInput(String inputCode, int parentId) {
		return modelDao.getInputModelCode(inputCode, parentId);
	}

	public ModelInput createModelInput(ModelInput modelInput) {
		modelInput.setId(modelDao.createInput(modelInput));
		return modelInput;
	}

	public List<Table> getTablesForModelAndCompany(int modelId, int companyId) {
		// Try and get tables for this specific company
		// if none, try for tables for companytype
		List<Table> tables = tableDao.findByModelIdAndCompanyId(modelId, companyId);
		if (tables.size()==0){
			Company c = referenceService.getCompany(companyId);
			tables = tableDao.findByModelIdAndCompanyType(modelId, c.getCompanyType().getId());
		}
		tables = tableService.addTableDescriptions(tables);
		return tables;
	}
	
	public List<Table> getTablesForModelCodeAndCompany(String modelCode, int companyId){
		Model model = modelDao.findByCode(modelCode);
		return getTablesForModelAndCompany(model.getId(), companyId);
	}

	public int addCompanyTable(int companyId, int modelId, int tableId) {
		return tableDao.addCompanyTable(companyId, modelId, tableId);
	}

	public List<Group> getGroupsForTable(int tableId) {
		
		List<Integer> groupIds = tableDao.getGroupIdsForTable(tableId);
		List<Group> groups = new ArrayList<Group>();
		for(Integer groupId: groupIds){
			groups.add(groupService.getGroupById(groupId));
		}
		
		return groups;
	}

	public Model getFamilyParentModel(Model model) {
		if(null == model.getFamily()){
			return model; // either it is the parent or it's a stand alone model
		}
		Model parentModel = modelDao.getFamilyParentModel(model.getFamily().getId());
		setBranchOnModel(parentModel);
		setServicesOnModel(parentModel);
		return parentModel;
	}
	
	public Model getFamilyParentModel(String familyCode) {
		ModelFamily modelFamily = getModelFamily(familyCode);
		if(null == modelFamily){
			return null; // family does not exist
		}
		Model parentModel = modelDao.getFamilyParentModel(modelFamily.getId());
		setBranchOnModel(parentModel);
		setServicesOnModel(parentModel);
		return parentModel;
	}
	
	public ModelCompanyReport getExternalModel(String finfout, int modelId, int companyId) {
		return modelDao.findModelCompanyReport(finfout, modelId, companyId);
	}
	
	public void updateModel(Model model) {
		// Update branch if it has changed
		if (model.getBranch() != null &&
			!model.getBranch().equals(branchDao.findById(model.getBranch().getId()))) {
			branchDao.update(model.getBranch());
		}
		modelDao.update(model);
	}
	
	public ModelCompanyReport getModelCompanyReport(int reportId) {
		return modelDao.findModelCompanyReport(reportId);
	}
	
	public ModelFamily getModelFamily(String code) {
		return modelFamilyDao.findByCode(code);
	}


	public Branch getBranch(String branch){
		return branchDao.findByName(branch);
	}
	
	public Branch getParentBranch(ModelFamily family) {
		Model parentModel = getFamilyParentModel(family.getCode());
		String code = null;
		Branch branch = null;
		if (parentModel != null &&
			parentModel.getBranch() != null) {
			code = parentModel.getBranch().getName();
			branch = getBranch(code);
		}
		return branch;
	}
	
	/**
	 * Get 
	 * @param modelId
	 * @return list of models that the passed model is dependent on before it can be processed in a run. 
	 */
	public List<Model> getModelRunDependencies(int modelId){
		List<Model> dependencies = new ArrayList<Model>();
		Model model = getModel(modelId);
		if(model.getModelType().equals(ModelType.EXTERNAL)){
			dependencies = modelDao.getModelRunDependencies(modelId);
		}
		return dependencies;
	}
	
	public void addDependency(int modelId, int dependencyId){
		Model model = getModel(modelId);
		if(model.getModelType().equals(ModelType.EXTERNAL)){
			modelDao.addModelDependency(modelId, dependencyId);
		}
	}
	
	public void removeDependency(int modelId, int dependencyId){
		Model model = getModel(modelId);
		if(model.getModelType().equals(ModelType.EXTERNAL)){
			modelDao.removeModelDependency(modelId, dependencyId);
		}
	}

}
