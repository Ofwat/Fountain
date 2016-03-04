package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public interface RunModelCompanyTagDao extends CachableEntityDao {

	RunModelCompanyTag findById(int id);

	RunModelCompanyTag create(RunModelCompanyTag runModelCompanyTag);

	List<RunModelCompanyTag> findByRunModelTagId(int runModelTagId);

	RunModelCompanyTag findTagByRunModelTagAndCompany(int runModelTagId, int companyId);

	void delete(int id);

}
