package uk.gov.ofwat.fountain.rest.dto;

import java.util.Date;

import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;

public class RunModelTagDto {

	private long id;
	private Date dateCreated;
	private String createdBy;
	private String name;
	private String displayName;
	private String note;

	public RunModelTagDto(RunModelTag runModelTag) {
		this.id = runModelTag.getId();
//		this.name= runModelTag.getName();
		this.displayName =  runModelTag.getDisplayName();
//		this.createdBy = runModelTag.getCreatedBy();
//		this.dateCreated = runModelTag.getDateCreated();
		this.note = runModelTag.getNote();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
