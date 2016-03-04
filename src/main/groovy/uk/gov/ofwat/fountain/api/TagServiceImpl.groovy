package uk.gov.ofwat.fountain.api

import org.springframework.transaction.annotation.Transactional

import uk.gov.ofwat.fountain.dao.TagDao
import uk.gov.ofwat.fountain.dao.TagLinkTypeDao;
import uk.gov.ofwat.fountain.domain.Entity
import uk.gov.ofwat.fountain.domain.LatestTag
import uk.gov.ofwat.fountain.domain.tag.Tag
import uk.gov.ofwat.fountain.domain.tag.TagLink
import uk.gov.ofwat.fountain.domain.tag.tagLinkFactories.AbstractTagLinkFactory
import uk.gov.ofwat.fountain.domain.tag.tagLinkFactories.TagLinkFactory

@Transactional
class TagServiceImpl implements TagService {

	TagDao tagDao
	TagLinkTypeDao tagLinkTypeDao
	AbstractTagLinkFactory abstractTagLinkFactory
	
	@Override
	public <T> List<T> getEntitiesForTag(String tagName, Class<T> type) {
		// TODO Auto-generated method stub
		def tag = tagDao.findTagByNameAndType(tagName, type)
		List<T> entities = new ArrayList<T>()
		if (null != tag) {
			TagLinkFactory.factory = abstractTagLinkFactory.getTagLinkFactory(type)
			entities = TagLinkFactory.getEntities(tag.getTagLinks(type.toString()))
		}
		return entities
	}
	
	public List<TagLink> getAllEntitiesForTag(String tagName){
		def tag = tagDao.findTagByName(tagName)
		List<TagLink> entities = new ArrayList<TagLink>()
		tag?.getTagLinks().each{
			entities.add(it)
		}
		return entities
	}

	@Override
	public Tag addTag(String tagDisplayName, String tagName, Entity entityToTag) {
		//find by name first!
		Tag tag = tagDao.findTagByName(tagName)
		if(!tag){
			tag = new Tag();
			tag.name = tagName
			tag.displayName = tagDisplayName
			tag.createdBy = "XXX" //TODO this should come from the context. 
			tag.note = "note"
			tagDao.create(tag)
		}
		//Check if a tagLink already exists
		def link
		tag.getTagLinks().each { tagLink ->
			if( tagLink.entityId == entityToTag.id && 
				tagLink.entityType.equalsIgnoreCase(entityToTag.class.toString()))
				link = tagLink
		}
		//If there isn't one then create a new one. 
		if(!link){
			def tagLink = new TagLink()
			tagLink.createdBy = "XXX" //TODO this should come from the context.
			tagLink.dateCreated = new Date()
			tagLink.entityId = entityToTag.id
			tagLink.entityType = tagLinkTypeDao.create(entityToTag.class.toString())
			tag.addTagLink(tagLink)
			tagDao.update(tag)
		}
		return tag
	}

	public <T> T getTagLinkType(TagLink tagLink){
		return (T)tagLink 	
	}
	
	@Override
	public List<Tag> addTags(List<String> tagNames, Entity entityToTag) {
		tagNames.each { tagName ->
			addTag(tagName, tagName, entityToTag)
		}
		return getTags(entityToTag)
	}

	@Override
	public List<Tag> getTags(Entity taggedEntity) {
		List<Tag> result = tagDao.getTagsForEntity(taggedEntity.id, taggedEntity.class)
		return result;
	}

	@Override
	public void removeTag(String tagName, Entity taggedEntity) {
		Tag tag = tagDao.deleteTagLink(tagName, taggedEntity.id, taggedEntity.class.toString())
		//Need to remove the parent tag if there are no tags left at all!
		if(tagDao.count(tagName) == 0){
			tagDao.deleteById(tag.id);
		}	
	}

	/**
	 * Remove all tagLinks and the tag - batch operation.
	 * @param tagName
	 */
	@Override
	public void removeTag(String tagName) {
		Tag tag = tagDao.deleteAllTagLinks(tagName)
		tagDao.deleteById(tag.id)
	}
	
	@Override
	public List<Tag> setTags(List<String> tagNamesToSet, Entity entityToTag) {
		//TODO - remove any that aren't here!
		def existingTags = getTags(entityToTag)
		def existingNames = []
		existingTags.each{
			existingNames.add(it.name)
		}

		//Everything in existingNames that doesn't exist in tagNamesToSet
		def namesToRemove = existingNames.minus(tagNamesToSet)
		namesToRemove.each{ tagName ->
			removeTag(tagName, entityToTag)
		}
				
		tagNamesToSet.each { tagName ->
			addTag(tagName, tagName, entityToTag)
		}
		return getTags(entityToTag)
	}

	@Override
	public int getTagCount(String tagName) {
		return tagDao.count(tagName)
	}

	@Override
	public List<Tag> getAllTags() {
		List<Tag> result = tagDao.getAll()
		return result;
	}
	
	@Override
	public Tag findTagByName(String name) {
		return tagDao.findTagByName(name);
	}

	@Override
	public Tag findTagById(long id) {
	        if(id == 0){
	            return LatestTag.LATEST_TAG;
	        }
		return tagDao.get(id);
	}
			
}
