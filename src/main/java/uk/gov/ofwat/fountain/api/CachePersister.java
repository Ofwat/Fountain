package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;

public interface CachePersister {
	
	public void persistCaches(DaoCache daoCache);
	
	public List<DaoCache> loadCaches();
	
}
