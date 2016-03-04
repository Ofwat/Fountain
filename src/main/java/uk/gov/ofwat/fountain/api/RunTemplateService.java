package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.domain.run.RunModel;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;

public interface RunTemplateService {
	
	public List<RunTemplate> getAllRunTemplates();
	
	public RunTemplate getRunTemplate(int id);
	
	public void addModel(int modelId, int runTemplateId, int runOrder);
	
	public void removeModel(int modelId, int runTemplateId);
	
	public RunTemplate createRunTemplate(RunTemplate runTemplate);
	
	public boolean deleteRunTemplate(int runTemplateId);	
	
	public void removeAllModels(int runTemplateId);
	
}
