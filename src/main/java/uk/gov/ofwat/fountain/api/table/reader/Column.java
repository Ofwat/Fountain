package uk.gov.ofwat.fountain.api.table.reader;

import uk.gov.ofwat.fountain.rest.dto.IntervalDto;

public class Column {

	private Integer intervalId;
	private String codePrefix;
	
	public Column(Integer intervalId, String codePrefix) {
		this.intervalId = intervalId;
		this.codePrefix = codePrefix;
	}
	
	public Integer getIntervalId() {
		return intervalId;
	}
	public void setIntervalId(int intervalId) {
		this.intervalId = intervalId;
	}
	public String getCodePrefix() {
		return codePrefix;
	}
	public void setCodePrefix(String codePrefix) {
		this.codePrefix = codePrefix;
	}
	
}
