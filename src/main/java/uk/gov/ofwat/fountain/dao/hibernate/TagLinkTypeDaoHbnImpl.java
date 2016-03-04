package uk.gov.ofwat.fountain.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import uk.gov.ofwat.fountain.dao.TagLinkTypeDao;
import uk.gov.ofwat.fountain.domain.tag.TagLinkType;

public class TagLinkTypeDaoHbnImpl  extends AbstractHbnDao<TagLinkType> implements TagLinkTypeDao{

	@Override
	public TagLinkType findByType(String type) {
		Session s = getSession();
		Query q = s.getNamedQuery("findTagLinkByType");
		q.setCacheable(true);
		q.setString("entityType", type);
		TagLinkType tagLinkType = (TagLinkType) q.uniqueResult();
		return tagLinkType;
	}

	public TagLinkType create(String entityType) {
		TagLinkType tlt = findByType(entityType);
		if(tlt != null){
			return tlt;
		}else{
			TagLinkType newTlt = new TagLinkType(entityType);
			super.create(newTlt);
			return newTlt;
		}
	}	
	/**
	 * Override the create to check if one already exists!
	 */
	@Override
	public void create(TagLinkType tagLinkType) {
		TagLinkType tlt = findByType(tagLinkType.getEntityType());
		if(tlt != null){
			tagLinkType = tlt;
		}else{
			super.create(tagLinkType);
		}
	}
	
}
