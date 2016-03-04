package uk.gov.ofwat.fountain.dao;
import java.util.List;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.tag.*;
import uk.gov.ofwat.fountain.dao.hibernate.*; 

import uk.gov.ofwat.fountain.dao.hibernate.Dao;
import uk.gov.ofwat.fountain.domain.tag.TagLinkType;

public interface TagLinkTypeDao extends Dao<TagLinkType> {
	public TagLinkType findByType(String type);	
	public TagLinkType create(String entityType);
}
