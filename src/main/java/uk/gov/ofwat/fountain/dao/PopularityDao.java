package uk.gov.ofwat.fountain.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.gov.ofwat.fountain.api.PopularityPeriod;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.popularity.View;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.dao.hibernate.Dao;

public interface PopularityDao extends Dao<View>{
	//A list of entities and their 'score'.
	public List<HashMap<Long, Float>> getItemsByPopularity(String type, PopularityPeriod period, int count);
	
	public void addPopularity(Entity entity);
	
	public void addPopularity(String type, Long id);
	
	public void removePopularity(Entity entity);
	
	public View findLatestViewByEntityId(Long entityId);
	
	public View findEarliestViewByEntityId(Long entityId);
	
	public void cleanUp(PopularityPeriod period);
	
}
