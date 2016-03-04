package uk.gov.ofwat.fountain.api

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uk.gov.ofwat.fountain.dao.TagDao;
import uk.gov.ofwat.fountain.domain.Entity;import uk.gov.ofwat.fountain.domain.tag.TagLink;

import uk.gov.ofwat.fountain.domain.tag.Tag;

public interface TagService {
	
	//Get all entities with a tag of 'tagName'
	public <T> List<T> getEntitiesForTag(String tagName, Class<T> type);
	
	public List<TagLink> getAllEntitiesForTag(String tagName);
	
	public <T> T getTagLinkType(TagLink tagLink);
	
	public Tag addTag(String tagDisplayName, String tagName, Entity entityToTag);
	
	public List<Tag> addTags(List<String> tagNames, Entity entityToTag);
	
	public List<Tag> getTags(Entity taggedEntity);
	
	public void removeTag(String tagName, Entity taggedEntity);
	
	public void removeTag(String tagName);
	
	public List<Tag> setTags(List<String> tagNames, Entity entityToTag);
	
	//TODO - What about all the static tag operation i.e GetTotalTagCount...
	public int getTagCount(String tagName)

	public List<Tag> getAllTags();

	public Tag findTagByName(String name);
	
	public Tag findTagById(long id);
		
}
