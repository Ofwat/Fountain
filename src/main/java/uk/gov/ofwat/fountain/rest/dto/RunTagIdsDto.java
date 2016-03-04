/**
 * 
 */
package uk.gov.ofwat.fountain.rest.dto;


/**
 * @author adam.edgar
 *
 */

public class RunTagIdsDto {
    private String runName;
	private int runId;
    private String tagName;
	private int tagId;
	
	public RunTagIdsDto() {
	}

	public int getRunId() {
		return runId;
	}

	public void setRunId(int runId) {
		this.runId = runId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

    public String getRunName() { 
    	return runName; 
    }

    public void setRunName(String runName) { 
    	this.runName = runName; 
	}

    public String getTagName() { 
    	return tagName; 
	}

    public void setTagName(String tagName) { 
    	this.tagName = tagName; 
	}
}
