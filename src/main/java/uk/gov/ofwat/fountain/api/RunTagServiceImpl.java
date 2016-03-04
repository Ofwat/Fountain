package uk.gov.ofwat.fountain.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.dao.RunModelCompanyTagDao;
import uk.gov.ofwat.fountain.dao.RunModelTagDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class RunTagServiceImpl implements RunTagService {

	private static Logger logger = LoggerFactory.getLogger(RunTagServiceImpl.class);

	private RunModelTagDao runModelTagDao;
	private RunModelCompanyTagDao runModelCompanyTagDao;
	private RunService runService;
	private ReferenceService referenceService;
	private RunTagService runTagService;

	public void setRunTagService(RunTagService runTagService) {
		this.runTagService = runTagService;
	}

	public void setRunModelCompanyTagDao(RunModelCompanyTagDao runModelCompanyTagDao) {
		this.runModelCompanyTagDao = runModelCompanyTagDao;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public void setRunService(RunService runService) {
		this.runService = runService;
	}

	public void setRunModelTagDao(RunModelTagDao runModelTagDao) {
		this.runModelTagDao = runModelTagDao;
	}

	public void setRunModelTagCompanyDao(RunModelCompanyTagDao runModelCompanyTagDao) {
		this.runModelCompanyTagDao = runModelCompanyTagDao;
	}

	private void populateRunModelCompanyTags(RunModelTag runModelTag) {
		List<RunModelCompanyTag> runModelCompanyTags = runModelCompanyTagDao.findByRunModelTagId(runModelTag.getId());
		runModelTag.setRunModelCompanyTags(runModelCompanyTags);
	}

	@Override
	public RunModelCompanyTag findRunModelCompanyTagByRunModelCompany(int runId, int modelId, int companyId) {
		RunModelTag runModelTag = runModelTagDao.findTagByRunModel(runId, modelId);
		if (runModelTag.equals(RunModelTag.PLACE_HOLDER)) {
			return RunModelCompanyTag.PLACE_HOLDER;
		}
		return runModelCompanyTagDao.findTagByRunModelTagAndCompany(runModelTag.getId(), companyId);
	}

	@Override
	@Transactional
	public RunModelTag tagRun(String tagDisplayName, int runId, int modelId) {
		
		RunModelTag runModelTag = findRunModelTagByRunModel(runId, modelId);
		if (!runModelTag.equals(RunModelTag.PLACE_HOLDER)) {
			throw new RunModelTagError("Cannot create as already exists.");
		}
		
		// Create RunModelCompanyTags
		Run run = runService.getRun(runId);
		for (int companyId: run.getCompanyIds()) {
			tagRunCompany(tagDisplayName, runId, modelId, companyId);
		}
		
		runModelTag = findRunModelTagByRunModel(runId, modelId);
		populateRunModelCompanyTags(runModelTag);
		
		logger.info("Created tags for Run: " + run.getName());
		
		return runModelTag;
	}

	private RunModelTag createRunModelTag(String tagDisplayName, int runId,	int modelId) {
		RunModelTag runModelTag = new RunModelTag();
		runModelTag.setRunId(runId);
		runModelTag.setModelId(modelId);
		runModelTag.setDisplayName(tagDisplayName);
		runModelTag.setNote("");
		runModelTag = runModelTagDao.create(runModelTag);
		return runModelTag;
	}

	@Override
	@Transactional
	public RunModelCompanyTag tagRunCompany(String tagDisplayName, int runId, int modelId, int companyId) {
		
		RunModelCompanyTag runModelCompanyTag = findRunModelCompanyTagByRunModelCompany(runId, modelId, companyId);
		if (!runModelCompanyTag.equals(RunModelCompanyTag.PLACE_HOLDER)) {
			throw new RunModelCompanyTagError("Cannot create as already exists.");
		}
		
		RunModelTag runModelTag = findRunModelTagByRunModel(runId, modelId);
		if (runModelTag.equals(RunModelTag.PLACE_HOLDER)) {
			runModelTag = createRunModelTag(tagDisplayName, runId, modelId);
		}
		
		// Create RunModelCompanyTag
		runModelCompanyTag = createRunModelCompanyTag(runModelTag, companyId);
		runModelTag.getRunModelCompanyTags().add(runModelCompanyTag);
		
		Company company = referenceService.getCompany(companyId);
		Run run = runService.getRun(runId);
		logger.info("Created tag [" + runModelCompanyTag.getDisplayName() + "] for  Company: " + company.getCode() + " Run: " + run.getName());
		return runModelCompanyTag;
	}

	
	private RunModelCompanyTag createRunModelCompanyTag(RunModelTag runModelTag, int companyId) {
		RunModelCompanyTag runModelCompanyTag = new RunModelCompanyTag();
		runModelCompanyTag.setRunModelTagId(runModelTag.getId());
		runModelCompanyTag.setCompanyId(companyId);
		runModelCompanyTag.setDisplayName(runModelTag.getDisplayName());
		runModelCompanyTag.setCreatedBy("System");
		runModelCompanyTagDao.create(runModelCompanyTag);
		return runModelCompanyTag;
	}

	@Override
	public RunTag getLatestRunTag(int runId, boolean substituteProxy) {
		Run run = runService.getRun(runId, substituteProxy);
		RunTag newRunTag = new RunTag(run, RunModelTag.LATEST);
		return newRunTag;
	}

	@Override
	public RunTag getLatestRunTag(int runId) {
		return getLatestRunTag(runId, true);
	}

	@Override
	public RunTag getRunTag(int runId, int tagId) {
		return getRunTag(runId, tagId, true);
	}

	@Override
	public RunTag getRunTag(int runId, int tagId, boolean substituteProxy) {
		Run run = runService.getRun(runId, substituteProxy);
		RunModelTag runModelTag = findRunModelTagById(tagId);
		RunTag newRunTag = new RunTag(run, runModelTag);
		return newRunTag;
	}

	
	@Override
	public RunModelTag findRunModelTagById(int id) {
		if (id == RunModelTag.LATEST.getId()) {
			return RunModelTag.LATEST;
		}
		else if (id == RunModelTag.PLACE_HOLDER.getId()) {
			return RunModelTag.PLACE_HOLDER;
		}
		RunModelTag runModelTag = runModelTagDao.findById(id);
		populateRunModelCompanyTags(runModelTag);
		return runModelTag;
	}
	
	@Override
	public RunModelCompanyTag findRunModelCompanyTagById(int id) {
		if (id == RunModelCompanyTag.LATEST.getId()) {
			return RunModelCompanyTag.LATEST;
		}
		else if (id == RunModelCompanyTag.PLACE_HOLDER.getId()) {
			return RunModelCompanyTag.PLACE_HOLDER;
		}
		return runModelCompanyTagDao.findById(id);
	}
	
	public List<Integer> getCompanyIdsFromRunModelTag(RunModelTag runModelTag) {
		List<Integer> companyIds = new ArrayList<Integer>();
		for (RunModelCompanyTag runModelCompanyTag: runModelTag.getRunModelCompanyTags()) {
			companyIds.add(runModelCompanyTag.getCompanyId());
		}
		return companyIds;
	}

	@Override
	public RunModelTag findRunModelTagByRunModelCompany(int runId, int modelId, int companyId) {
		RunModelTag runModelTag = findRunModelTagByRunModel(runId, modelId);
		RunModelCompanyTag runModelCompanyTag = runModelTag.getRunModelCompanyTag(companyId);
		if (runModelCompanyTag.equals(RunModelCompanyTag.PLACE_HOLDER)) {
			// There is no tag for this company
			return RunModelTag.PLACE_HOLDER;
		}
		return runModelTag;
	}

	@Override
	public void removeRunModelCompanyTag(int id) {
		runModelCompanyTagDao.delete(id);
	}

	@Override
	public void removeRunModelTag(int id) {
		runModelTagDao.delete(id);
	}

	@Override
	public RunModelTag findRunModelTagByRunModel(int runId, int modelId) {
		RunModelTag runModelTag = runModelTagDao.findTagByRunModel(runId, modelId);
		populateRunModelCompanyTags(runModelTag);
		return runModelTag;
	}

	@Override
	public List<RunModelTag> tagsForRun(int runId) {
		List<RunModelTag> runModelTags = runModelTagDao.findByRunId(runId);
		return runModelTags;
	}

}
