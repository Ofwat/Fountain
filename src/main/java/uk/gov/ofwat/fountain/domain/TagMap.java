package uk.gov.ofwat.fountain.domain;

import java.util.Map;

public class TagMap {
	
	private String tagMapType;
	private Map tags;

	public void setTagMapType(String tagMapType) {
		this.tagMapType = tagMapType; 
	}

	public void setTags(Map tagMap) {
		this.tags = tagMap; 
	}

	public String getTagMapType() {
		return tagMapType;
	}

	public Map getTags() {
		return tags;
	}

	
}
