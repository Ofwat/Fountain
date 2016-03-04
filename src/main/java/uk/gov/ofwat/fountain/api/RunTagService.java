package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public interface RunTagService {


	RunModelTag findRunModelTagByRunModel(int runId, int modelId);

	RunModelTag findRunModelTagByRunModelCompany(int runId, int modelId, int companyId);

	RunModelCompanyTag findRunModelCompanyTagByRunModelCompany(int runId, int modelId, int companyId);

	RunModelTag findRunModelTagById(int id);

	RunModelCompanyTag findRunModelCompanyTagById(int id);

	RunModelTag tagRun(String tagDisplayName, int runId, int modelId);
	
	RunTag getLatestRunTag(int runId);

	RunTag getRunTag(int runId, int tagId);

	RunModelCompanyTag tagRunCompany(String tagDisplayName, int runId, int modelId, int companyId);

	void removeRunModelCompanyTag(int id);

	void removeRunModelTag(int id);

	RunTag getRunTag(int runId, int tagId, boolean substituteProxy);

	List<RunModelTag> tagsForRun(int runId);

	RunTag getLatestRunTag(int runId, boolean substituteProxy);

}
