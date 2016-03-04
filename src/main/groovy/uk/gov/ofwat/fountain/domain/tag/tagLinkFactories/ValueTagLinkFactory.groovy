package uk.gov.ofwat.fountain.domain.tag.tagLinkFactories

import uk.gov.ofwat.fountain.dao.ValueDao
import uk.gov.ofwat.fountain.domain.Value

class ValueTagLinkFactory {
	
	private ValueDao valueDao;
	
	public void setValueDao(ValueDao valueDao) {
		this.valueDao = valueDao
	}
	
	List<Value> getEntities(tagLinks){
		List<Value> result = new ArrayList<Value>()
		int[] valueIds = new int[tagLinks.size()]
		int i = 0;
		tagLinks.each{
			//build up a list of values and do a bulk select!
			valueIds[i] = (int)it.entityId;
			i++;
		}
		result = valueDao.findByIdList(valueIds);
		return result		
	}
}
