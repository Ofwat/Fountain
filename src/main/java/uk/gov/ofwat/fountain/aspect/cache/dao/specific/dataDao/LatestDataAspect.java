/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.aspect.cache.dao.specific.dataDao;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Data;


/**
 * Cache for latest data - clears on changes to itemDao
 * 
 */
@Aspect
@Order(255)
public class LatestDataAspect extends DaoCache implements UpdateListener{
	private static final Log log = LogFactory.getLog(LatestDataAspect.class);
	
	private Map<String, Object> cacheLatestData;
	public Map getCache() {
		return cacheLatestData;
	}
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheLatestData = cache;
	}

	public void setItemDao(ItemDao itemDao){
		itemDao.addItemUpdateListener(this);
	}
	public void setGroupDao(GroupDao groupDao){
		groupDao.addItemUpdateListener(this);
	}
	
	public void updateOccurred() {
		initialiseCache();
	}

//	@Around("execution(public uk.gov.ofwat.fountain.domain.Data uk.gov.ofwat.fountain.dao.DataDao.getLatestData(int, int, int, int)) || execution(public uk.gov.ofwat.fountain.domain.Data uk.gov.ofwat.fountain.dao.DataDao.getBranchData(int, int, int, int, int))")
	@Around("execution(public uk.gov.ofwat.fountain.domain.Data uk.gov.ofwat.fountain.dao.DataDao.getLatestData(int, int, int, int))")
	public Object cacheOnLatestData(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = "";
		for (int i=0; i<args.length; i++) {
			key = key+(Integer)args[i] + "-";
		}

		Object retVal = null;
		if (cacheLatestData.containsKey(key)) {
			retVal = cacheLatestData.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheLatestData.put(key, retVal);
			incrementCache(pjp);
		}

		return retVal;
	}


	@Around("execution(public void uk.gov.ofwat.fountain.dao.DataDao.delete(int))")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		throw new UnsupportedOperationException("uk.gov.ofwat.fountain.dao.DataDao.delete(int) has not been implemented. If you are implementing it you must implement removeFromCacheOnDelete()");
	}

	@Around("execution(public void uk.gov.ofwat.fountain.dao.DataDao.updateValue(uk.gov.ofwat.fountain.domain.Data, uk.gov.ofwat.fountain.domain.Audit))")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if (null != cacheLatestData) {
			Data data = (Data)args[0];
			String key = data.getItem().getId() + "-" + data.getInterval().getId() + "-" + data.getCompany().getId() + "-" + data.getGroupEntry().getId() + "-";
	    	if (null != data.getBranch()) {
				key = key + data.getBranch().getId() + "-";
	    	}
	    	cacheLatestData.remove(key);
		}
	}

	@Around("execution(public int uk.gov.ofwat.fountain.dao.DataDao.create(uk.gov.ofwat.fountain.domain.Data, uk.gov.ofwat.fountain.domain.Audit))")
	public Integer removeFromCacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (Integer)pjp.proceed();
		}

		Object[] args = pjp.getArgs();
		Integer dataId = (Integer)pjp.proceed(args);
		if (null != cacheLatestData) {
			Data data = (Data)args[0];
			String key = data.getItem().getId() + "-" + data.getInterval().getId() + "-" + data.getCompany().getId() + "-" + data.getGroupEntry().getId() + "-";
	    	if (null != data.getBranch()) {
				key = key + data.getBranch().getId() + "-";
	    	}
	    	cacheLatestData.remove(key);
		}
		return dataId;
	}

}