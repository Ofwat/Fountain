package uk.gov.ofwat.fountain.domain.run

import java.util.List;

import uk.gov.ofwat.fountain.domain.Agenda
import uk.gov.ofwat.fountain.domain.Coded;
import uk.gov.ofwat.fountain.domain.Entity
import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;
import uk.gov.ofwat.fountain.domain.RunModel
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.RunRole

class Run implements Coded, Entity {
    
	private int id;
	private String description;
	private String name;
	private String code;
	private Boolean completed;
	private Boolean deleted;
	private RunTemplate runTemplate;
	private ArrayList<RunModel> runModels;
	private int baseTagId;
	private String baseTagName;
	private int dataSourceId
	private String dataSourceName;
	private Date created;
	private String createdBy;
	private Date lastModified;
	private String lastModifiedBy;
	private Boolean updating;
	private int agendaId;
	private Agenda agenda;
	private RunRole runRole;
	private boolean adminOnly;
	private List<Integer> companyIds;
	private RunCompanyTemplate runCompanyTemplate;
	
	
	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getBaseTagName() {
		return baseTagName;
	}

	public void setBaseTagName(String baseTagName) {
		this.baseTagName = baseTagName;
	}

	public int getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(int dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public int getBaseTagId() {
		return baseTagId;
	}

	public void setBaseTagId(int baseTagId) {
		this.baseTagId = baseTagId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public RunTemplate getRunTemplate() {
		return runTemplate;
	}

	public void setRunTemplate(RunTemplate runTemplate) {
		this.runTemplate = runTemplate;
	}

	public ArrayList<RunModel> getRunModels() {
		return runModels;
	}

	public void setRunModels(ArrayList<RunModel> runModels) {
		this.runModels = runModels;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Boolean isUpdating() {
		return updating;
	} 		

	public void setUpdating(boolean updating) {
		this.updating = updating;
	}

	public int getAgendaId(){
		return agendaId;
	}

	public void setAgendaId(int agendaId){
		this.agendaId = agendaId;
	}

	public Agenda getAgenda(){
		return agenda;
	}

	public void setAgenda(Agenda agenda){
		this.agenda = agenda;
		this.agendaId = agenda.getId();
	}

	public void setRunRole(RunRole runRole) {
		this.runRole = runRole;
	}	
	
	public RunRole getRunRole() {
		return runRole;
	}

	public void setAdminOnly(boolean adminOnly) {
		this.adminOnly = adminOnly;
	}
	
	public boolean isAdminOnly() {
		return adminOnly;
	}

	public void setCompanyIds(List<Integer> companyIds) {
		this.companyIds = companyIds; 
	}

	public List<Integer> getCompanyIds() {
		return companyIds;
	}

	public void setRunCompanyTemplate(RunCompanyTemplate runCompanyTemplate) {
		this.runCompanyTemplate = runCompanyTemplate;
	}

	public RunCompanyTemplate getRunCompanyTemplate() {
		return runCompanyTemplate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
