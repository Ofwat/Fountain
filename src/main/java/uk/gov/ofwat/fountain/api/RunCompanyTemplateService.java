package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;

public interface RunCompanyTemplateService {
	
	public List<RunCompanyTemplate> getAllRunCompanyTemplates();
	
	public RunCompanyTemplate getRunCompanyTemplate(int id);
	
	public void addCompany(int companyId, int runCompanyTemplateId);
	
	public void removeCompany(int companyId, int runCompanyTemplateId);
	
	public RunCompanyTemplate createRunCompanyTemplate(RunCompanyTemplate runCompanyTemplate);
	
	public boolean deleteRunCompanyTemplate(int runCompanyTemplateId);	
	
	public void removeAllCompanies(int runCompanyTemplateId);
	
}
