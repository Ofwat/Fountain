package uk.gov.ofwat.fountain.domain.runTag;

import java.util.Date;

import uk.gov.ofwat.fountain.domain.Entity;

public class RunModelCompanyTag implements Entity {

	
    public static final RunModelCompanyTag LATEST = new RunModelCompanyTag(0, "Latest");
    public static final RunModelCompanyTag PLACE_HOLDER = new RunModelCompanyTag(-1, "NO_TAG_DISPLAY_NAME");
	
	private int id;
	private int runModelTagId; 
	private int companyId; 
	private Date dateCreated;
	private String createdBy;
	private String displayName; 

	private RunModelCompanyTag(int id, String displayName) {
		super();
		this.id = id;
		this.displayName = displayName;
	}

	public RunModelCompanyTag(int id, int runModelTagId, int companyId, Date dateCreated, String createdBy, String displayName) {
		super();
		this.id = id;
		this.runModelTagId = runModelTagId;
		this.companyId = companyId;
		this.dateCreated = dateCreated;
		this.createdBy = createdBy;
		this.displayName = displayName;
	}

	public RunModelCompanyTag() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRunModelTagId() {
		return runModelTagId;
	}

	public void setRunModelTagId(int runModelTagId) {
		this.runModelTagId = runModelTagId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
