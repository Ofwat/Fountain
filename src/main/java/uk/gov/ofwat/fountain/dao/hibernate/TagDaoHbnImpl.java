package uk.gov.ofwat.fountain.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import uk.gov.ofwat.fountain.dao.TagDao;
import uk.gov.ofwat.fountain.dao.TagLinkTypeDao;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Value;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.domain.tag.TagLink;
import uk.gov.ofwat.fountain.domain.tag.TagLinkType;


public class TagDaoHbnImpl extends AbstractHbnDao<Tag> implements TagDao {

	private static int BATCH_SIZE = 1000;
	private TagLinkTypeDao tagLinkTypeDao;
	
	public TagLinkTypeDao getTagLinkTypeDao() {
		return tagLinkTypeDao;
	}

	public void setTagLinkTypeDao(TagLinkTypeDao tagLinkTypeDao) {
		this.tagLinkTypeDao = tagLinkTypeDao;
	}	
	
	@Override
	public Tag findTagByName(String tagName) {
		Session s = getSession();
		Query q = s.getNamedQuery("findTagByName");
		q.setCacheable(true);
		q.setString("tagName", tagName);
		Tag tag = (Tag) q.uniqueResult();
		return tag;
	}

	@Override
	public Tag findTagByNameAndType(String tagName, Class clazz) {
		Session s = getSession();
		//s.clear();
		//System.out.println("\n*" + clazz.toString() + "*\n");
		
		//Filter f = s.enableFilter("linkTypeFilter").setParameter("linkTypeString", clazz.toString());	
		
		/*
		Query q = s.getNamedQuery("findTagByName");
		//q.setCacheable(true);
		q.setString("tagName", tagName);
		//q.setString("entityType", clazz.toString());
		Tag tag = (Tag) q.uniqueResult();
		s.disableFilter("linkType");
		*/
		//f.validate();
		
		/***
		 * 
		 * We are filtering on the tag.getTagLinks() call now so we don't apply the filter here anymore! - this is a fudge to deal with a caching
		 * issue and should really be addressed - with some decent tests.  
		 * 
		 */
		
		Tag tag = (Tag) s.createQuery("select distinct tag from Tag tag where tag.name like :tagName").setString("tagName", tagName).uniqueResult();
		//Tag tag = (Tag) s.createQuery("FROM Tag tag LEFT JOIN tag.tagLinks tagLinks WHERE tag.name = :tagName AND tagLinks.entityType like :entityType").setString("tagName", tagName).setString("entityType", clazz.toString()).uniqueResult();
		//Object o = s.createQuery("FROM Tag tag LEFT JOIN tag.tagLinks tagLinks WHERE tag.name = :tagName AND tagLinks.entityType like :entityType").setString("tagName", tagName).setString("entityType", clazz.toString()).uniqueResult();
		//Tag tag = (Tag) s.createQuery("select distinct tag from Tag tag where tag.name like :tagName").setString("tagName", tagName).uniqueResult();
		
		//s.disableFilter("linkTypeFilter");
		
		return tag;
	}

	@Override
	public Tag deleteTagLink(String tagName, long taggedEntityId, String entityType) {
		Tag tag = findTagByName(tagName);
		// iterate over links and see if the selected entity is there and
		// delete.
		if(tag != null){
			Iterator<TagLink> i = tag.getTagLinks().iterator();
			TagLink selTag = null;
			while (i.hasNext()) {
				TagLink tagLink = (TagLink) i.next();
				if ((tagLink.getEntityId() == taggedEntityId) && (tagLink.getEntityType().equals(entityType))) {
					selTag = tagLink;
				}
			}
			if(selTag != null){
				tag.getTagLinks().remove(selTag);
				update(tag);
			}
		}
		return tag;
	}
	
	@Override
	public Tag deleteAllTagLinks(String tagName){
		Tag tag = findTagByName(tagName);
		//Bulk delete all tag links where tagId = tag.id 
		Session session = getSession();
		Query q = session.getNamedQuery("deleteAllTagLinks");
		q.setLong("tagId", tag.getId());
		int result = q.executeUpdate();
		session.flush();
		session.clear();
		return tag;
	}
	
	@Override
	public Long count(String tagName) {
		Session s = getSession();
		Tag tag = findTagByName(tagName);
		return (Long) s
				.createCriteria("uk.gov.ofwat.fountain.domain.tag.TagLink")
				.add(Restrictions.eq("tag", tag))
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long count(String tagName, Class clazz) {
		Session s = getSession();
		Tag tag = findTagByName(tagName);
		TagLinkType tlt = tagLinkTypeDao.findByType(clazz.toString());
		return (Long) s
				.createCriteria("uk.gov.ofwat.fountain.domain.tag.TagLink")
				.add(Restrictions.eq("tag", tag))
				.add(Restrictions.like("entityType", tlt)) 
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tag> getTagsForEntity(long taggedEntityId, Class clazz) {
		Session s = getSession();
		
		//Look up the id for the entityType? - or do we manage it through Hibernate?
		TagLinkType tlt = tagLinkTypeDao.findByType(clazz.toString());
		Criteria c =  s.createCriteria("uk.gov.ofwat.fountain.domain.tag.TagLink")
				.add(Restrictions.like("entityId", taggedEntityId))
				.add(Restrictions.like("entityType", tlt)); 
		List<TagLink> tl = c.list();
		List<Tag> tags = new ArrayList<Tag>();
		Iterator<TagLink> i = tl.iterator();
		while(i.hasNext()){
			tags.add((Tag)i.next().getTag());
		}
		return tags;
	}

	@Override
	public void saveTagLinks(Tag tag, List<? extends Entity> entities, Class clazz, String username) {
		Session session = getSession();
		Date createDate = new Date();
		int entitityCount = 0;
		for (Entity entity: entities) {
			entitityCount++;
			TagLink tagLink = new TagLink();
			tagLink.setTag(tag);
			tagLink.setCreatedBy(username);
			tagLink.setDateCreated(createDate);
			tagLink.setEntityId(entity.getId());
			
			//Get the tagLinkType
			TagLinkType tagLinkType = tagLinkTypeDao.create(clazz.toString());
			/*
			if(tagLinkType == null){
				tagLinkType = new TagLinkType();
				tagLinkType.setEntityType(clazz.toString());
				tagLinkTypeDao.create(tagLinkType);
			}
			*/
			tagLink.setEntityType(tagLinkType);
			
			session.save(tagLink);
		    if ( entitityCount % BATCH_SIZE == 0 ) { //1000, same as the JDBC batch size
		        //flush a batch of inserts and release memory:
		        session.flush();
		        session.clear();
		    }
		}
        session.flush();
        session.clear();
	}
}
