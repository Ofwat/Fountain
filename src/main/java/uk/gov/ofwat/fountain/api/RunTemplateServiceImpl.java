package uk.gov.ofwat.fountain.api;

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.dao.RunTemplateDao;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;

public class RunTemplateServiceImpl implements RunTemplateService {

	private RunTemplateDao runTemplateDao;
	private RunService runService;
	private ModelService modelService;

	
	
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public RunTemplateDao getRunTemplateDao() {
		return runTemplateDao;
	}

	public void setRunTemplateDao(RunTemplateDao runTemplateDao) {
		this.runTemplateDao = runTemplateDao;
	}

	public RunService getRunService() {
		return runService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}
		
	
	@Override
	public List<RunTemplate> getAllRunTemplates() {
		List<RunTemplate> runTemplates = runTemplateDao.getAll();
		for(RunTemplate runTemplate : runTemplates){
			setModelsOnRunTemplate(runTemplate);
		}
		return runTemplates;
	}

	
	public boolean deleteRunTemplate(int runTemplateId){
		return runTemplateDao.delete(runTemplateId);
	}
	
	@Override
	public void addModel(int modelId, int runTemplateId, int runOrder) {
		runTemplateDao.addModel(runTemplateId, modelId, runOrder);
	}

	@Override
	public void removeModel(int modelId, int runTemplateId) {
		runTemplateDao.removeModel(runTemplateId, modelId);
	}

	@Override
	public RunTemplate getRunTemplate(int id) {
		RunTemplate runTemplate = runTemplateDao.findById(id);
		setModelsOnRunTemplate(runTemplate);
		return runTemplate;
	}

	private void setModelsOnRunTemplate(RunTemplate runTemplate) {
		for (Integer modelId : runTemplate.getModelIds()) {
			Model model = modelService.getModel(modelId);
			runTemplate.getModels().add(model);
		}
	}

	@Override
	public RunTemplate createRunTemplate(RunTemplate runTemplate) {
		RunTemplate newRunTemplate = runTemplateDao.create(runTemplate);
		setModelsOnRunTemplate(newRunTemplate);

		List<Integer> modelIds = newRunTemplate.getModelIds();
		Model originModel = modelService.getModel("ORIGIN");
		if (!modelIds.contains(originModel)) {
			addModel(originModel.getId(), runTemplate.getId(), 0);
		}
		Model completedModel = modelService.getModel("COMPLETED");
		if (!modelIds.contains(completedModel)) {
			addModel(completedModel.getId(), runTemplate.getId(), 1);
		}

		return newRunTemplate;
	}
	
	@Override
	public void removeAllModels(int runTemplateId){
		runTemplateDao.removeAllModels(runTemplateId);
	}
}
