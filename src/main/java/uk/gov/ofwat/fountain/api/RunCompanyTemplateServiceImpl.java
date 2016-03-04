package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.dao.RunCompanyTemplateDao;
import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;

public class RunCompanyTemplateServiceImpl implements RunCompanyTemplateService {

	private RunCompanyTemplateDao runCompanyTemplateDao;
	private RunService runService;
	private ReferenceService referenceService;

	
	
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public RunCompanyTemplateDao getRunCompanyTemplateDao() {
		return runCompanyTemplateDao;
	}

	public void setRunCompanyTemplateDao(RunCompanyTemplateDao runCompanyTemplateDao) {
		this.runCompanyTemplateDao = runCompanyTemplateDao;
	}

	public RunService getRunService() {
		return runService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}
		
	
	@Override
	public List<RunCompanyTemplate> getAllRunCompanyTemplates() {
		List<RunCompanyTemplate> runCompanyTemplates = runCompanyTemplateDao.getAll();
		return runCompanyTemplates;
	}

	
	public boolean deleteRunCompanyTemplate(int runCompanyTemplateId){
		return runCompanyTemplateDao.delete(runCompanyTemplateId);
	}
	
	@Override
	public void addCompany(int companyId, int runTemplateId) {
		runCompanyTemplateDao.addCompany(runTemplateId, companyId);
	}

	@Override
	public void removeCompany(int companyId, int runTemplateId) {
		runCompanyTemplateDao.removeCompany(runTemplateId, companyId);
	}

	@Override
	public RunCompanyTemplate getRunCompanyTemplate(int id) {
		RunCompanyTemplate runCompanyTemplate = runCompanyTemplateDao.findById(id);
		return runCompanyTemplate;
	}

	@Override
	public RunCompanyTemplate createRunCompanyTemplate(RunCompanyTemplate runCompanyTemplate) {
		RunCompanyTemplate newRunCompanyTemplate = runCompanyTemplateDao.create(runCompanyTemplate);
		return newRunCompanyTemplate;
	}
	
	@Override
	public void removeAllCompanies(int runCompanyTemplateId){
		runCompanyTemplateDao.removeAllCompanies(runCompanyTemplateId);
	}
}
