package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.run.RunTemplate;

public interface RunTemplateDao {
	
	public List<RunTemplate> getAll();
	
	public RunTemplate findById(int runTemplateId);
	
	public RunTemplate create(RunTemplate runTemplate);
	
	public boolean delete(int runTemplateId);
	
	public void addModel(int templateId, int modelId, int runOrder);
	
	public void removeModel(int templateId, int modelId);
	
	public void removeAllModels(int runTemplateId);
	
}
