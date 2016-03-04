package uk.gov.ofwat.fountain.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.domain.run.Run;

public class RunModel implements Entity, Serializable  {

	private int id;
	private int runId;
	private Run run;
	private int modelId;
	private int companyId;
	private boolean completed;
	private int parentModelId;
	private Date completedDate;
	private String completedBy;
	private String lastModifiedBy;
	private int runOrder;
	
	public int getRunOrder() {
		return runOrder;
	}

	public void setRunOrder(int runOrder) {
		this.runOrder = runOrder;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public RunModel(Run run, int modelId, int companyId, boolean completed, String lastModifiedBy) {
		this.run = run;
		if (null != run) { 
			this.runId = run.getId();
		}
		this.modelId = modelId;
		this.companyId = companyId;
		this.completed = completed;
		this.lastModifiedBy= lastModifiedBy; 
	}

	public RunModel(int id, boolean completed) {
		this.id = id;
		this.completed = completed;
	}

	public Run getRun() {
		return run;
	}

	public void setRun(Run run) {
		this.run = run;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setParentModelId(int parentModelId) {
		this.parentModelId = parentModelId;
	}

	public int getParentModelId() {
		return parentModelId;
	}

}
