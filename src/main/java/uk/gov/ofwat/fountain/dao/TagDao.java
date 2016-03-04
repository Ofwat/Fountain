package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.tag.*;
import uk.gov.ofwat.fountain.dao.hibernate.*; 

public interface TagDao extends Dao<Tag>{
	public Tag findTagByName(String name);
	public Tag findTagByNameAndType(String tagName, Class clazz);
	public Long count(String tagName);
	public Long count(String tagName, Class clazz);
	public Tag deleteTagLink(String tagName, long taggedEntityId, String entityType);
	public Tag deleteAllTagLinks(String tagName);
	public List<Tag> getTagsForEntity(long taggedEntityId, Class clazz);
	void saveTagLinks(Tag tag, List<? extends Entity> entities, Class clazz, String username);
}
