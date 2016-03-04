package uk.gov.ofwat.fountain.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.dao.AgendaDao;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.RunDao;
import uk.gov.ofwat.fountain.dao.RunModelDao;
import uk.gov.ofwat.fountain.domain.Agenda;
import uk.gov.ofwat.fountain.domain.AgendaPlaceHolder;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;



public class RunServiceImpl implements RunService {

	private static Logger logger = LoggerFactory.getLogger(RunServiceImpl.class);

	private RunDao runDao;
	private BranchDao branchDao;
	private RunTemplateService runTemplateService;
	private RunCompanyTemplateService runCompanyTemplateService;
	private DataService dataService;
	private BasketService basketService;
	private LockService lockService;
	private RunTagService runTagService;
	private RunModelDao runModelDao;
	private ReferenceService referenceService;
	private ModelService modelService;
	private ReportService reportService;
	private AgendaDao agendaDao;
	
	
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setRunModelDao(RunModelDao runModelDao) {
		this.runModelDao = runModelDao;
	}

	public void setRunTagService(RunTagService runRunTagService) {
		this.runTagService = runRunTagService;
	}

	public RunTemplateService getRunTemplateService() {
		return runTemplateService;
	}

	public void setRunCompanyTemplateService(RunCompanyTemplateService runCompanyTemplateService) {
		this.runCompanyTemplateService = runCompanyTemplateService;
	}

	public RunCompanyTemplateService getRunCompanyTemplateService() {
		return runCompanyTemplateService;
	}

	public void setRunTemplateService(RunTemplateService runTemplateService) {
		this.runTemplateService = runTemplateService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void setRunDao(RunDao runDao){
		this.runDao = runDao;
	}
	
	public void setBranchDao(BranchDao branchDao) {
		this.branchDao = branchDao;
	}
	
	public void setAgendaDao(AgendaDao agendaDao) {
		this.agendaDao = agendaDao;
	}

	public Run getRun(int id) {
		return getRun(id, true);
	}

	@Transactional
	public Run getRun(int id, boolean substituteProxy) {
        if(id == RunPlaceHolder.RUN_PLACE_HOLDER.getId()){
            return RunPlaceHolder.RUN_PLACE_HOLDER;
        }
		Run run = runDao.findById(id);
		if (run != null) {
			if (RunRole.PROXY == run.getRunRole() &&
				substituteProxy) {
				// If its the proxy run then use the default run.
				run = runDao.findDefault(run);
			}
			if (run != null) {
				setDataSourceNameOnRun(run);
				setBaseTagNameOnRun(run);
				setAgendaOnRun(run);	
			}
		}
		return run;
	}

	@Override
	public Run getRun(String runCode) {
		return getRun(runCode, true);
	}

	@Transactional
	public Run getRun(String runCode, boolean substituteProxy) {
        if(runCode.equals(RunPlaceHolder.RUN_PLACE_HOLDER.getCode())){
            return RunPlaceHolder.RUN_PLACE_HOLDER;
        }
		Run run = runDao.findByCode(runCode);
		if (run != null) {
			if (RunRole.PROXY == run.getRunRole() &&
				substituteProxy) {
				// If its the proxy run then use the default run.
				run = runDao.findDefault(run);
			}
			if (run != null) {
				setDataSourceNameOnRun(run);
				setBaseTagNameOnRun(run);
				setAgendaOnRun(run);	
			}
		}
		return run;
	}

	private void setDataSourceNameOnRun(Run run) {
		Run sourceRun = runDao.findById(run.getDataSourceId());
		if (null != sourceRun) {
			run.setDataSourceName(sourceRun.getName());
		}
	}

	private void setBaseTagNameOnRun(Run run) {
	    RunModelTag baseTag = runTagService.findRunModelTagById(run.getBaseTagId());
	    if (null != baseTag) {
	    	run.setBaseTagName(baseTag.getDisplayName());
	    }
	}
	
	private void setAgendaOnRun(Run run) {
		Agenda agenda = getAgenda(run.getAgendaId());
		run.setAgenda(agenda);
	}

	
	@Transactional
	public List<Run> getAllRuns() {
		List<Run> runs = runDao.getAll();
		for (Run run: runs) {
			setDataSourceNameOnRun(run);
			setBaseTagNameOnRun(run);
			setAgendaOnRun(run);	
		}
		return runs;
	}

	@Override
	public List<Run> getAllRunsOrderedOnAgenda() {
		return getRoleFilteredRunsOrderedOnAgenda(new ArrayList<RunRole>());
	}

	@Override
	public List<Run> getRoleFilteredRunsOrderedOnAgenda(List<RunRole> runRoles) {
		if (runRoles.isEmpty()) {
			addAllRoles(runRoles);
		}
		SortedMap<Integer, List<Run>> runMap = new TreeMap<Integer, List<Run>>(); // map of run lists - one list for each agenda
		for (Run run: getAllRuns()) {
			if (!runRoles.contains(run.getRunRole())) {
				continue;
			}
			if (!runMap.containsKey(run.getAgendaId())) {
				// new agenda
				List<Run> runsForAgenda = new ArrayList<Run>();
				addRunToList(run, runsForAgenda);
				runMap.put(run.getAgendaId(), runsForAgenda);
			}
			else {
				// existing agenda
				List<Run> runsForAgenda = runMap.get(run.getAgendaId());
				addRunToList(run, runsForAgenda);
			}
		}
		// flatten the map of lists into one list.
		List<Run> runs = new ArrayList<Run>();
		for (List<Run> runsForAgenda: runMap.values()) {
			for (Run run: runsForAgenda) {
				runs.add(run);
			}
		}
		return runs;
	}

	private void addRunToList(Run run, List<Run> runsForAgenda) {
		if (run.getRunRole() == RunRole.PROXY) {
			runsForAgenda.add(0, run); // proxy first
		}
		else if (run.getRunRole() == RunRole.DEFAULT) {
			if (!runsForAgenda.isEmpty() &&
				runsForAgenda.get(0).getRunRole().getId() == RunRole.PROXY.getId()) {
				runsForAgenda.add(1, run); // default second
			}
			else {
				runsForAgenda.add(0, run); // default first
			}
		}
		else {
			runsForAgenda.add(run);
		}
	}

	@Override
	public List<Run> getRunsByAgenda(int agendaId) {
		return getRoleFilteredRunsByAgenda(agendaId, new ArrayList<RunRole>());
	}

	@Override
	public List<Run> getRoleFilteredRunsByAgenda(int agendaId, List<RunRole> runRoles) {
		if (runRoles.isEmpty()) {
			addAllRoles(runRoles);
		}
		List<Run> runsForAgenda = new ArrayList<Run>();
		for (Run run: getAllRuns()) {
			if (agendaId != run.getAgendaId()) {
				continue;
			}
			if (!runRoles.contains(run.getRunRole())) {
				continue;
			}
			addRunToList(run, runsForAgenda);
		}
		return runsForAgenda;
	}

	private void addAllRoles(List<RunRole> runRoles) {
		runRoles.add(RunRole.STANDARD);			
		runRoles.add(RunRole.DEFAULT); 
		runRoles.add(RunRole.PROXY);			
	}

	@Transactional
	public Run composeRun(String runName, String runDescription, int agendaId, int sourceRunId, int sourceTagId, int runTemplateId, boolean defaultRun, User user, int runCompanyTemplateId) throws BranchNotEditableException {
		RunTemplate runTemplate = runTemplateService.getRunTemplate(runTemplateId); 
		RunCompanyTemplate runCompanyTemplate = runCompanyTemplateService.getRunCompanyTemplate(runCompanyTemplateId); 
		Run newRun = createRun(runName, runDescription, user, runTemplate, agendaId, sourceTagId, sourceRunId, defaultRun, runCompanyTemplate);
		if (0 == sourceTagId) {
			branchRun(runName, sourceRunId, user, runTemplate, newRun);
		}
		else {
			branchRunFromTag(runName, sourceRunId, sourceTagId, user, runTemplate, newRun);
		}
		return newRun;
	}


	private void branchRun(String runName, int sourceRunId, User user, RunTemplate runTemplate, Run newRun) throws BranchNotEditableException {
		// Tag source run
		Model branchPointModel = modelService.createModel("BP-" + ((newRun.getName().length() > 33) ? newRun.getName().substring(0, 33) : newRun.getName()), "BP_RUNID" + newRun.getId(), ModelType.TAG_POINT.getName(), null, null, false, 0,
				RunPlaceHolder.RUN_PLACE_HOLDER.getId());
		RunModelTag sourceRunModelTag = runTagService.tagRun("Branched " + newRun.getName(), sourceRunId, branchPointModel.getId());

		// create RunModel for the branch point model 
		for (Integer companyId: newRun.getCompanyIds()) {
			createRunModel(getRun(sourceRunId), companyId, user, branchPointModel);
		}

		branchRunFromTag(runName, sourceRunId, sourceRunModelTag.getId(), user, runTemplate, newRun);
	}

	private void branchRunFromTag(String runName, int sourceRunId, int sourceTagId, User user, RunTemplate runTemplate, Run newRun) throws BranchNotEditableException {
		newRun.setBaseTagId(sourceTagId);
	    RunModelTag sourceRunModelTag = runTagService.findRunModelTagById(sourceTagId);
		
		Model originModel = modelService.getModel("ORIGIN");
		if (null == originModel) {
			throw new RuntimeException("Model with code 'ORIGIN' could not be found.");
		}

		for (Integer companyId: newRun.getCompanyIds()) {
			branchDataFromRunAndTag(runName, newRun.getId(), sourceRunId, sourceRunModelTag, companyId, user);
			createRunModels(newRun, runTemplate, companyId, user);
			runTagService.tagRunCompany("Origin", newRun.getId(), originModel.getId(), companyId);
		}
		runDao.update(newRun);
	}

	private Branch branchDataFromRunAndTag(String runName, int newRunId, int sourceRunId, RunModelTag sourceRunModelTag, int companyId, User user) throws BranchNotEditableException {
		RunModelCompanyTag sourceRunModelCompanyTag = sourceRunModelTag.getRunModelCompanyTag(companyId);
		Branch newBranch = branchDao.create(runName, companyId, newRunId);
		List<Data> dataToBranch = dataService.getTaggedDataForRun(sourceRunId, companyId, sourceRunModelCompanyTag.getId());
		if (!branchData(dataToBranch, user, newBranch.getId(), runName, sourceRunId, sourceRunModelCompanyTag)) {
			return null;
		}
		return newBranch;
	}

	
	private void createRunModels(Run run, RunTemplate runTemplate, int companyId, User user) {
		for (Model model: runTemplate.getModels()) {
			createRunModel(run, companyId, user, model);
		}
	}

	public void createRunModel(Run run, int companyId, User user, Model model) {
		RunModel runModel = new RunModel(run, model.getId(), companyId, false, user.getName());
		runModelDao.create(runModel);
		// create runModel for parent model if it dosen't already exist.
		Model parent = modelService.getFamilyParentModel(model);
		RunModel parentRunModel = getRunModel(parent.getId(), run, companyId);
		if (null == parentRunModel) {
			// no parent run model yet so create it
			parentRunModel = new RunModel(run, parent.getId(), companyId, false, user.getName());
			runModelDao.create(parentRunModel);
		}
	}

	@Transactional
	public List<RunModel> getRunModelsByRun(int runId) {
		List<RunModel> runModels = runModelDao.findByRun(runId);
		for (RunModel runModel: runModels) {
			Run run = getRun(runModel.getRunId());
			runModel.setRun(run);
		}
		return runModels;
	}

		
	@Transactional
	public List<RunModel> getRunModelsByRunAndCompany(int runId, int companyId) {
		List<RunModel> runModels = runModelDao.findByRunAndCompany(runId, companyId);
		for (RunModel runModel: runModels) {
			Run run = getRun(runModel.getRunId());
			runModel.setRun(run);
		}
		return runModels;
	}

		
	@Transactional
	public RunModel getRunModel(int modelId, Run run, int companyId) {
		if (null == run) {
			return null;
		}
		RunModel runModel = runModelDao.findByRunAndModelAndCompany(run.getId(), modelId, companyId);
		if (null == runModel) {
			// The model isn't on this run.
			// or have we just got a parent model?
			Model model = modelService.getModel(modelId);
			Model parent = modelService.getFamilyParentModel(model);
			runModel = runModelDao.findByRunAndModelAndCompany(run.getId(), parent.getId(), companyId);
			if (null == runModel) {
				// No - its not the parent
				return null;
			}
		}
		runModel.setRun(run);
		runModel.setModelId(modelId);
		return runModel;
	}

	
	@Transactional
	public void updateRunModel(RunModel runModel) {
		runModelDao.update(runModel);
	}	
		

	private Run createRun(String runName, String runDescription, User user, RunTemplate runTemplate, int agendaId, int srcTagId, int srcRunId, boolean defaultRun, RunCompanyTemplate runCompanyTemplate) {
		Run run = new Run();
		run.setName(runName);
		run.setDescription(runDescription);
		run.setCompleted(false);
		run.setDeleted(false);
		run.setRunTemplate(runTemplate);
		run.setCreatedBy(user.getName());
		run.setDataSourceId(srcRunId);
		setDataSourceNameOnRun(run);
		run.setBaseTagId(srcTagId);
		run.setUpdating(false);
		run.setAgendaId(agendaId);
		setAgendaOnRun(run);
		RunRole runRole = RunRole.STANDARD;
		run.setRunRole(runRole);
		setBaseTagNameOnRun(run);
		run.setRunCompanyTemplate(runCompanyTemplate);
		run.setCompanyIds(runCompanyTemplate.getCompanyIds());
		run = runDao.createRun(run);
		if (defaultRun) {
			setDefaultRun(run.getId());
		}
		
		return run;
	}

	public void updateRun(Run run) {
		runDao.update(run);
	}

	@Transactional
	public boolean branchData(List<Data> dataToBranch, User user, int branchId, String runName, int sourceRunId, RunModelCompanyTag sourceTag) throws BranchNotEditableException {
		if (null == dataToBranch) {
			return false;
		}

		Run sourceRun = getRun(sourceRunId);
		String auditComment = "Run '" + runName + "' created from run '" + sourceRun.getName() + "'";
		if (null != sourceTag) {
			auditComment = auditComment + " tag '" + sourceTag.getDisplayName() + "'";
		}
			
		// get basket
		Basket basket = basketService.getBasketForUser(user);
		if (null != basket) {
			return false;
		}
		basket = basketService.createBasket(user);

		for (Data data: dataToBranch) {
			updateValue(user, data, basket, branchId);
		}
		
		basket.setAuditComment(auditComment);
		try {
			dataService.saveNewRunBasket(basket);
		} catch (BranchNotEditableException e) {
			throw new BranchNotEditableException(e.getMessage());
		}
		finally {
			basketService.clearBasket(user);
		}
		
		return true;
	}

	private void updateValue(User user, Data data, Basket basket, int branchId) {
		if (null != data.getValue()) {
			UserEdit valueEdit = new UserEdit(user, 
											data.getItem().getId(), data.getInterval().getId(), data.getCompany().getId(),
											data.getGroupEntry().getId(), branchId, data.getValue(), "", EditType.VALUE); 
			if (null != valueEdit) {
				basket.putEdit(valueEdit);
			}
		}

		if (null != data.getConfidenceGrade()) {
			UserEdit cgEdit = new UserEdit(user, 
										data.getItem().getId(), data.getInterval().getId(), data.getCompany().getId(),
										data.getGroupEntry().getId(), branchId, data.getConfidenceGrade().getCode(), "", EditType.CONFIDENCE_GRADE); 
			if (null != cgEdit) {
				basket.putEdit(cgEdit);
			}
		}
		// Note: Not saved to user edit table in database.
		// Note: No locking needed. Method only used when creating a new run. Until it is created no-one can access the new run!
	}

	@Override
	@Transactional
	public void completeAndTagRun(int runId, User user) {
		completeRun(runId, user);
		tagAsCompleted(runId, user);
	}

	@Override
	@Transactional
	public void uncompleteAndTagRun(int runId, User user) {
		uncompleteRun(runId, user);
		untagCompleted(runId, user);
	}

	public void completeRun(int runId, User user) {
		Run run = getRun(runId);
		run.setCompleted(true);
		run.setLastModified(new Date());
		run.setLastModifiedBy(user.getName());
		updateRun(run);
		disableEditing(run);
	}

	public void uncompleteRun(int runId, User user) {
		Run run = getRun(runId);
		run.setCompleted(false);
		run.setLastModified(new Date());
		run.setLastModifiedBy(user.getName());
		updateRun(run);
		enableEditing(run);
	}
	

	@Override
	@Transactional
	public void setRunUpdatingFlag(int runId, User user, Boolean updating) {
		Run run = getRun(runId);
		run.setUpdating(updating);
		updateRun(run);
	}

	private void disableEditing(Run run) {
		for (Integer companyId: run.getCompanyIds()) {
			Branch branch = branchDao.findByCompanyAndRun(companyId, run.getId());
			branch.setEditable(false);
			branchDao.update(branch);
		}
	}

	private void enableEditing(Run run) {
		for (Integer companyId: run.getCompanyIds()) {
			Branch branch = branchDao.findByCompanyAndRun(companyId, run.getId());
			branch.setEditable(true);
			branchDao.update(branch);
		}
	}

	public void tagAsCompleted(int runId, User user) {
		Model completedModel = modelService.getModel("COMPLETED");
		if (null == completedModel) {
			throw new RuntimeException("Model with code 'COMPLETED' could not be found.");
		}
		Run run = runDao.findById(runId);
		runTagService.tagRun("Completed", runId, completedModel.getId());
	}
	
	private void untagCompleted(int runId, User user) {
		Model completedModel = modelService.getModel("COMPLETED");
		if (null == completedModel) {
			throw new RuntimeException("Model with code 'COMPLETED' could not be found.");
		}
		
		RunModelTag runModelTag = runTagService.findRunModelTagByRunModel(runId, completedModel.getId());
		for (RunModelCompanyTag runModelCompanyTag: runModelTag.getRunModelCompanyTags()) {
			deleteDataTag(runId, completedModel.getId(), runModelCompanyTag.getCompanyId());
		}
		runTagService.removeRunModelTag(runModelTag.getId());
	}
	
	@Transactional
	public void deleteDataTag(int runId, int modelId, int companyId) {
		Run run = getRun(runId);
		RunModel runModel = getRunModel(modelId, run, companyId);
		runModel.setCompleted(false);
		updateRunModel(runModel);

		RunModelCompanyTag existingTag = runTagService.findRunModelCompanyTagByRunModelCompany(runId, modelId, companyId);
		if (null == existingTag) {
			return;
		}
		runTagService.removeRunModelCompanyTag(existingTag.getId());
		
		Company company = referenceService.getCompany(companyId);
		logger.info("finished deleting tags for Company: " + company.getCode() + " Run: " + run.getName());		
		return;
	}



	@Transactional
	public Map<Integer, RunModelTag> tagsForReportCompanies(int runId, int reportId) {
		List<Company> companies = new ArrayList<Company>();
		for (Integer id: reportService.getReportDefinition(reportId).getCompanyIds()) {
			companies.add(referenceService.getCompany(id));
		}
		List<RunModel> runModels = getAllRunModels(runId);
		Map<Integer, RunModelTag> runModelIdTagMap = filterRunModelsForCompanies(runId, companies, runModels);
		return runModelIdTagMap;
	}

	@Transactional
	public Map<Integer, RunModelTag> tagsForCompany(int runId, int companyId) {
		List<Company> companies = new ArrayList<Company>();
		companies.add(referenceService.getCompany(companyId));
		List<RunModel> runModels = getAllRunModels(runId);
		Map<Integer, RunModelTag> runModelIdTagMap = filterRunModelsForCompanies(runId, companies, runModels);
		return runModelIdTagMap;
	}

	@Transactional
	public Map<Integer, RunModelTag> tagsForAllCompanies(int runId) {
		List<Company> companies = referenceService.getCompanies() ;
		List<RunModel> runModels = getAllRunModels(runId);
		Map<Integer, RunModelTag> runModelIdTagMap = filterRunModelsForCompanies(runId, companies, runModels);
		return runModelIdTagMap;
	}

	private Map<Integer, RunModelTag> filterRunModelsForCompanies(int runId, List<Company> companies, List<RunModel> runModels) {
		Map<Integer, RunModelTag> runModelIdTagMap = new HashMap<Integer, RunModelTag>(); 
		Run run = runDao.findById(runId);
		// are there any companies for the run?
		if (run.getCompanyIds().isEmpty()) {
			return runModelIdTagMap;
		}
		
		for (RunModel runModel: runModels) {
			runModelIdTagMap.put(runModel.getId(), null);
			for (Integer companyId: run.getCompanyIds()) {
				RunModelTag tag = runTagService.findRunModelTagByRunModelCompany(runId, runModel.getModelId(), companyId);
				if (tag.equals(RunModelTag.PLACE_HOLDER)) {
					// RunModel is not tagged for the current company, so remove it from the list
					runModelIdTagMap.remove(runModel.getId());
					break;
				}
				else {
					if (runModelIdTagMap.containsKey(runModel.getId()) &&
						null == runModelIdTagMap.get(runModel.getId())) {
						// has not been removed from the map AND has not yet been filled by a Tag. So put a tag in it
						runModelIdTagMap.put(runModel.getId(), tag);
					}
				}
			}
		}
		return runModelIdTagMap;
	}
	
	public boolean doesTagExist(int runId, int companyId, int modelId) {
		RunModelCompanyTag tag = runTagService.findRunModelCompanyTagByRunModelCompany(runId, companyId, modelId);
		if (null == tag) {
			return false;
		}
		return true;
	}
	
	private List<RunModel> getAllRunModels(int runId) {
		List <RunModel> runModels = null;
		Run run = runDao.findById(runId);
		for (Integer companyId: run.getCompanyIds()) {
			// TODO aaarrgh! This just uses company 30!
			// Don't like this - change it to use ANY company. 
//			SELECT * FROM tbl_runModel WHERE runId=22 AND companyId=30 ORDER BY runOrder ASC
//			SELECT * FROM tbl_runModel WHERE runId=22 ORDER BY runId, modelId, companyId ASC
			runModels = getRunModelsByRunAndCompany(runId, companyId);
			break;
		}
		return runModels;
	}

	@Override
	public Agenda getAgenda(int id) {
        if(id == AgendaPlaceHolder.AGENDA_PLACE_HOLDER.getId()){
            return AgendaPlaceHolder.AGENDA_PLACE_HOLDER;
        }
        return agendaDao.findById(id);
	}

	@Override
	public Agenda getAgenda(String code) {
		return agendaDao.findByCode(code);
	}

	@Override
	public void createAgenda(Agenda agenda) {
		agendaDao.create(agenda);
	}

	@Override
	public List<Agenda> getAllAgenda() {
		return agendaDao.findAllAgenda();
	}

	@Override
	@Transactional
	public void setDefaultRun(int id) {
		Run newDefaultRun = getRun(id);
		Run oldDefaultRun = runDao.findDefault(newDefaultRun);

		oldDefaultRun.setRunRole(RunRole.STANDARD);
		runDao.update(oldDefaultRun);

		newDefaultRun.setRunRole(RunRole.DEFAULT);
		runDao.update(newDefaultRun);
	}

	@Override
	public Agenda createAgenda(String agendaName, String agendaCode, String agendaDescription) {
		Agenda agenda = new Agenda(agendaCode, agendaName, agendaDescription);
		agenda.setId(agendaDao.create(agenda));
		return agenda;
	}

	@Override
	public void toggleRunAdminOnly(int id) {
		Run run = getRun(id);
		run.setAdminOnly(!run.isAdminOnly());
		runDao.update(run);
	}

}

