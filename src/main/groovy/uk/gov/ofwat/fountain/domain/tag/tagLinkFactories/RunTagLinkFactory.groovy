package uk.gov.ofwat.fountain.domain.tag.tagLinkFactories

import org.springframework.context.ApplicationContext;

import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.dao.RunDao;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.run.Run;

class RunTagLinkFactory {
	
	private RunDao runDao

	public void setRunDao(RunDao runDao) {
		this.runDao = runDao
	}

	List<Run> getEntities(tagLinks){
		List<Run> result = new ArrayList<Run>()
		tagLinks.each{
			result.add(runDao.findById((int)it.entityId))
		}
		return result		
	}
}
