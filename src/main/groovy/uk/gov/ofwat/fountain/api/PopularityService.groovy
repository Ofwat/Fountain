package uk.gov.ofwat.fountain.api

import uk.gov.ofwat.fountain.domain.Entity;

interface PopularityService {
	
	public List<HashMap<Long, Float>> getMostPopular(PopularityPeriod period, Class clazz);
	
	public void addPopularity(Entity entity);
	
	public void addPopularity(String type, long id);
	
	public void cleanUp();
	
}
