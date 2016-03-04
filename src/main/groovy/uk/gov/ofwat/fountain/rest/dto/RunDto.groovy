package uk.gov.ofwat.fountain.rest.dto;

import java.text.SimpleDateFormat

import uk.gov.ofwat.fountain.api.ReferenceService
import uk.gov.ofwat.fountain.domain.Agenda
import uk.gov.ofwat.fountain.domain.Company
import uk.gov.ofwat.fountain.domain.RunRole
import uk.gov.ofwat.fountain.domain.run.Run

public class RunDto {
	int id
	String name
	String description
	String created
	String completedDate
	String createdBy
	String lastModified
	String lastModifiedBy
	Boolean completed 
	String runTemplateName
	String runCompanyTemplateName
	String dataSourceName
	String BaseTagName
	Boolean deleted
	Boolean updating
	List<RunModelDto> runModels	= []	int agendaId;
	Agenda agenda;
	RunRole runRole;
	Boolean adminOnly;
	List<Company> companies = [];
	
	public RunDto() {
	}

	public RunDto(Run run, String dateFormat, ReferenceService referenceService) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		this.completed = run.isCompleted(); //TODO this needs to be set!
		this.updating = run.isUpdating();
		this.created = df.format(run.getCreated());
		this.createdBy = run.getCreatedBy();
		this.description = run.getDescription();
		this.id = run.getId();
		if(run.getLastModified() != null){
			this.lastModified = df.format(run.getLastModified());
		}
		this.lastModifiedBy = run.getLastModifiedBy();//TODO set this correctly
		this.name = run.getName();
		if (null != run.getRunTemplate()) {
			this.runTemplateName = run.getRunTemplate().getName();
		}
		if (null != run.getRunCompanyTemplate()) {
			this.runCompanyTemplateName = run.getRunCompanyTemplate().getName();
		}
		if(run.completed){
			this.completedDate = df.format(run.getLastModified());
		}
		this.dataSourceName = run.getDataSourceName();
		this.baseTagName = run.getBaseTagName();
		this.agenda = run.getAgenda();
		this.runRole = run.getRunRole();
		this.adminOnly = run.isAdminOnly();
		for (Integer companyId: run.getCompanyIds()) {
			this.companies.add(referenceService.getCompany(companyId));
		}
	}
	
}
