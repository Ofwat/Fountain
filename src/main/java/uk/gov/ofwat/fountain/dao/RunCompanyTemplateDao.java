package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;

public interface RunCompanyTemplateDao {
	
	List<RunCompanyTemplate> getAll();
	
	RunCompanyTemplate findById(int runCompanyTemplateId);
	
	RunCompanyTemplate create(RunCompanyTemplate runCompanyTemplate);
	
	boolean delete(int runCompanyTemplateId);
	
	void addCompany(int runCompanyTemplateId, int companyId);
	
	void removeCompany(int runCompanyTemplateId, int companyId);
	
	void removeAllCompanies(int runCompanyTemplateId);
	
}
