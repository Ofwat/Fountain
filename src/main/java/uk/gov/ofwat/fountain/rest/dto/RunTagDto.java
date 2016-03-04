/**
 * 
 */
package uk.gov.ofwat.fountain.rest.dto;

import java.text.SimpleDateFormat;

import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

/**
 * @author adam.edgar
 *
 */
public class RunTagDto {

	private long id;
	private String displayName;
	private String note;
	private Run run;
	
	
	
	public RunTagDto() {
	}

	public RunTagDto(Run run, RunModelTag tag) {
		SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		this.id = tag.getId();
		this.displayName = tag.getDisplayName();
		this.note = tag.getNote();
		this.run = run;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public Run getRun() {
		return run;
	}

	public void setRun(Run run) {
		this.run = run;
	}
}
