package uk.gov.ofwat.fountain.domain.tag

class Tag {
	
	long id
	Date dateCreated
	String createdBy
	String name
	String displayName
	String note	
	private Set<TagLink> tagLinks = new HashSet<TagLink>()
	
	public void addTagLink(TagLink tagLink){
		tagLink.tag = this
		this.tagLinks.add(tagLink)		
	}
	
	public void removeTagLink(TagLink tagLink){
		tagLinks.remove(tagLink)
	}
	
	public void removeAllTagLinks(){
		tagLinks.clear()		
	}
	
	public void setTagLinks(Set<TagLink> tagLinks){
		this.tagLinks = tagLinks
	}
	
	public Set<TagLink> getTagLinks(){
		return tagLinks;
	}
	
	public Set<TagLink> getTagLinks(String type){
		Set<TagLink> filteredTagLinks = new HashSet<TagLink>()
		tagLinks.each{
			if(it.entityType.equals(type)){
				filteredTagLinks.add(it);
			}		
		}
		return filteredTagLinks;
	}
}
