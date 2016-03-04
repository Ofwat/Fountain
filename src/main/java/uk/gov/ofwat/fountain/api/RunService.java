package uk.gov.ofwat.fountain.api;

import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.Agenda;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.RunModel;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;


public interface RunService {

	Run getRun(int id);
	
	Run getRun(int id, boolean substituteProxy);
	
	List<Run> getAllRuns();

	Run composeRun(String runName, String runDescription, int agendaId, int sourceRunId, int sourceTagId, int runTemplateId, boolean defaultRun, User user, int runCompanyTemplateId) throws BranchNotEditableException;
	
	RunModel getRunModel(int modelId, Run run, int companyId);

	void updateRunModel(RunModel runModel);

	List<RunModel> getRunModelsByRun(int runId);

	List<RunModel> getRunModelsByRunAndCompany(int runId, int companyId);


	void updateRun(Run run);

	void completeRun(int runId, User user);

	void uncompleteRun(int runId, User user);

	Map<Integer, RunModelTag> tagsForReportCompanies(int runId, int reportId);
	
	Map<Integer, RunModelTag> tagsForCompany(int runId, int companyId);

	Map<Integer, RunModelTag> tagsForAllCompanies(int runId);

	void createRunModel(Run run, int companyId, User user, Model model);

	boolean doesTagExist(int runId, int companyId, int modelId);

	void setRunUpdatingFlag(int runId, User user, Boolean updating);
	void completeAndTagRun(int runId, User user);
	void uncompleteAndTagRun(int runId, User user);
	
	List<Agenda> getAllAgenda();
	Agenda getAgenda(int id);
	Agenda getAgenda(String code);
	void createAgenda(Agenda agenda);

	void setDefaultRun(int id);

	List<Run> getAllRunsOrderedOnAgenda();
	List<Run> getRoleFilteredRunsOrderedOnAgenda(List<RunRole> runRoles);

	List<Run> getRunsByAgenda(int agendaId);
	List<Run> getRoleFilteredRunsByAgenda(int agendaId, List<RunRole> runRoles);

	Agenda createAgenda(String agendaName, String agendaCode, String agendaDescription);

	void toggleRunAdminOnly(int id);

	void deleteDataTag(int runId, int modelId, int companyId);

	Run getRun(String runCode);

	Run getRun(String runCode, boolean substituteProxy);

}
