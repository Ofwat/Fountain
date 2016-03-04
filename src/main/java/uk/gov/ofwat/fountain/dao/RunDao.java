package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.run.Run;

public interface RunDao extends CachableCodedDao, CachableEntityDao {

	public Run findById(int id);

	Run findByCode(String code);
	
	List<Run> getAll();

	Run createRun(Run run);
	
	void update(Run run);

	public Run findDefault(Run run);

//	public void updateRunRole(int id, RunRole default1);
//
//	public void makeDefaultRunStandard(int agendaId);
//
//	public void updateAdminOnly(int id, boolean adminOnly);

}
