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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.DataDaoImpl;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Data;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(260)
public class GetBranchDataAspect extends DaoCache implements UpdateListener {
	private static final Log log = LogFactory.getLog(GetBranchDataAspect.class);
	
	private Map<String, Object> cacheBranchData;

	public Map getCache() {
		return cacheBranchData;
	}
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheBranchData = cache;
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
	
	@Around("execution(public * uk.gov.ofwat.fountain.dao.DataDao.getBranchData(int, int, int, int, int))")
	public Object cacheOnBranchData(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1] + "-" + (Integer)args[2] + "-" + (Integer)args[3] + "-" + (Integer)args[4];
		
		Object retVal = null;
		if (cacheBranchData.containsKey(key)) {
			retVal = cacheBranchData.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheBranchData.put(key, (Object)retVal);
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
		if (null != cacheBranchData) {
			Data data = (Data)args[0];
	    	if (null != data.getBranch()) {
	    		// Only remove from cache if there is a branch. 
				String key = data.getItem().getId() + "-" + data.getInterval().getId() + "-" + data.getCompany().getId() + "-" + data.getGroupEntry().getId() + "-" + data.getBranch().getId();
				cacheBranchData.remove(key);
				cacheBranchData.put(key, (Object)data);
	    	}
		}
	}

	@Around("execution(public int uk.gov.ofwat.fountain.dao.DataDao.create(uk.gov.ofwat.fountain.domain.Data, uk.gov.ofwat.fountain.domain.Audit))")
	public Integer recacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (Integer)pjp.proceed();
		}
		Object[] args = pjp.getArgs();
		Integer dataId = (Integer)pjp.proceed(args);
		if (null != cacheBranchData) {
			Data data = (Data)args[0];
	    	if (null != data.getBranch()) {
	    		// Only recache from cache if there is a branch. 
				String key = data.getItem().getId() + "-" + data.getInterval().getId() + "-" + data.getCompany().getId() + "-" + data.getGroupEntry().getId() + "-" + data.getBranch().getId();
				cacheBranchData.remove(key);
				cacheBranchData.put(key, (Object)data);
	    	}
		}
		return dataId;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(public void uk.gov.ofwat.fountain.dao.DataDao.addToCache(java.util.Map<String, Object>))")
	public void addToCache(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return;
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		log.debug("start addToCache");
		Object[] args = pjp.getArgs();
		Map<String, Object> dataMap = (Map<String, Object>)args[0];
		cacheBranchData.putAll(dataMap);
		log.debug("end addToCache");
		return;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(public * uk.gov.ofwat.fountain.dao.DataDao.isDataInCache(String))")
	public Boolean isDataInCache(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return false;
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = (String)args[0];
		
		if (cacheBranchData.containsKey(key)) {
			return true;
		}

		return false;
	}

}