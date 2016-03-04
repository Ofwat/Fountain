package uk.gov.ofwat.fountain.api

import java.util.List;

import uk.gov.ofwat.fountain.dao.PopularityDao;
import uk.gov.ofwat.fountain.domain.Entity;

class PopularityServiceImpl implements PopularityService {

	PopularityDao popularityDao	
	
	@Override
	public List<HashMap<Long, Float>> getMostPopular(PopularityPeriod period, Class clazz) {
		List<HashMap<Long, Float>> popularities= popularityDao.getItemsByPopularity(clazz.toString(), period, 10)
		return popularities
	}

	@Override
	public void addPopularity(Entity entity) {
		popularityDao.addPopularity(entity)
	}

	@Override
	public void cleanUp() {
		popularityDao.cleanUp(PopularityPeriod.YEAR)
	}

	@Override
	public void addPopularity(String type, long id) {
		popularityDao.addPopularity(type, id)
	}
	
}
