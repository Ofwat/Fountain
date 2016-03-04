package uk.gov.ofwat.fountain.aspect.cache.dao.specific.groupDao;


import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.domain.GroupEntry;

@Aspect
@Order(266)
public class FindEntriesByIdAspect extends DaoCache implements UpdateListener{

	private Map<Integer, GroupEntry> cacheById;
	
	public void setGroupDao(GroupDao groupDao){
		groupDao.addItemUpdateListener(this);
	}
	
	public void updateOccurred() {
		initialiseCache();
	}

	public Map getCache() {
		return cacheById;
	}
	
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheById = cache;
	}	
	
	
	@SuppressWarnings("unchecked")
	@Around("execution(public * uk.gov.ofwat.fountain.dao.GroupDao+.findEntryById(int))")	
	public GroupEntry cacheOnFindEntryById(ProceedingJoinPoint pjp) throws Throwable{
		if(!isEnabled()){
			return (GroupEntry)pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		
		Integer key = ((Integer)args[0]);
		
		GroupEntry retVal = null;
		if (cacheById.containsKey(key)) {
			retVal = cacheById.get(key);
		}
		else {
			retVal = (GroupEntry)pjp.proceed();
			cacheById.put(key, retVal);
			incrementCache(pjp);
		}
		return retVal;		
	}	
	
	@Around("execution(public * uk.gov.ofwat.fountain.dao.GroupDao+.delete(int))")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		throw new UnsupportedOperationException("uk.gov.ofwat.fountain.dao.GroupDao.delete(int) has not been implemented. If you are implementing it you must implement removeFromCacheOnDelete()");
	}

	@Around("execution(public * uk.gov.ofwat.fountain.dao.GroupDao+.update(uk.gov.ofwat.fountain.domain.Group))")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		throw new UnsupportedOperationException("uk.gov.ofwat.fountain.dao.GroupDao.delete(int) has not been implemented. If you are implementing it you must implement removeFromCacheOnUpdate()");
	}	
	
}
