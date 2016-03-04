package uk.gov.ofwat.fountain.domain.tag.tagLinkFactories

import org.springframework.context.ApplicationContext;
import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.domain.Data;

class DataTagLinkFactory {
	
	private DataDao dataDao;
	
	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao
	}
	
	List<Data> getEntities(tagLinks){
		List<Data> result = new ArrayList<Data>()
		tagLinks.each{
			result.add(dataDao.findById((int)it.entityId))
		}
		return result		
	}
}
