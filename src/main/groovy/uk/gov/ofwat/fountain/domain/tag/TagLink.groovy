package uk.gov.ofwat.fountain.domain.tag

class TagLink {
	long id
	Tag tag
	private TagLinkType entityType
	long entityId
	Date dateCreated
	String createdBy
	
	public void setEntityType(TagLinkType tagLinkType){
		this.entityType = tagLinkType;	
	}
	
	public TagLinkType getEntityType(){
		return entityType;
	}
		
}
