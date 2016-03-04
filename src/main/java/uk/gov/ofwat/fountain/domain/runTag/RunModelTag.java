package uk.gov.ofwat.fountain.domain.runTag;

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.domain.Entity;

public class RunModelTag implements Entity {

    public static final RunModelTag LATEST = new RunModelTag(0, "Latest");
    public static final RunModelTag PLACE_HOLDER = new RunModelTag(-1, "NO_TAG_DISPLAY_NAME");

    private int id;
	private int runId;
	private int modelId;
	private String displayName; 
	private String note;
	private List<RunModelCompanyTag> runModelCompanyTags;
	
	private RunModelTag(int id, String displayName) {
		super();
		this.id = id;
		this.displayName = displayName;
	}
	
	public RunModelTag(int id, int runId, int modelId, String displayName, String note, List<RunModelCompanyTag> runModelCompanyTags) {
		super();
		this.id = id;
		this.runId = runId;
		this.modelId = modelId;
		this.displayName = displayName;
		this.note = note;
		this.runModelCompanyTags = runModelCompanyTags;
	}

	public RunModelTag() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
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

	public List<RunModelCompanyTag> getRunModelCompanyTags() {
		if (null == runModelCompanyTags) {
			return new ArrayList<RunModelCompanyTag>();
		}
		return runModelCompanyTags;
	}

	public void setRunModelCompanyTags(List<RunModelCompanyTag> runModelCompanyTags) {
		this.runModelCompanyTags = runModelCompanyTags;
	}

	public RunModelCompanyTag getRunModelCompanyTag(int companyId) {
		if (this.equals(LATEST)) {
			return RunModelCompanyTag.LATEST;
		}
		if (null == runModelCompanyTags) {
			return RunModelCompanyTag.PLACE_HOLDER;
		}
		for (RunModelCompanyTag runModelCompanyTag: runModelCompanyTags) {
			if (runModelCompanyTag.getCompanyId() == companyId) {
				return runModelCompanyTag; 
			}
		}
		return RunModelCompanyTag.PLACE_HOLDER;
	} 
	
}
