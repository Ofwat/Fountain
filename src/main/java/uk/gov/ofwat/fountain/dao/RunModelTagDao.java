package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public interface RunModelTagDao extends CachableEntityDao {
	
	RunModelTag findById(int id);
	
	RunModelTag findTagByRunModel(int runId, int modelId);

	RunModelTag create(RunModelTag runModelTag);

	void delete(int id);

	List<RunModelTag> findByRunId(int runId);

}
