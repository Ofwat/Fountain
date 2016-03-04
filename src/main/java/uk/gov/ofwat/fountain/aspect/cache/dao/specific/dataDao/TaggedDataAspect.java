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

import java.util.ArrayList;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Order(263)
public class TaggedDataAspect extends DaoCache implements UpdateListener{
	
	private static Logger logger = LoggerFactory.getLogger(TaggedDataAspect.class);

	private Map<String, Object> taggedData;
	public Map getCache() {
		return taggedData;
	}
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		taggedData = cache;
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

//	getTaggedData(int itemId, int yearId, int companyId, int groupEntryId, int runId, long tagId)
	@Around("execution(public * uk.gov.ofwat.fountain.dao.DataDao.getTaggedData(int, int, int, int, int, long))")
	public Object cacheOnBranchData(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1] + "-" + (Integer)args[2] + "-" + (Integer)args[3] + "-" + (Integer)args[4] + "-" + (Long)args[5];
		
		Object retVal = null;
		if (taggedData.containsKey(key)) {
			retVal = taggedData.get(key);
		}
		else {
			logger.warn("Tagged data not found in cache: " + key);
		}

		return retVal;
	}

	@SuppressWarnings("unchecked")
	@Around("execution(public void uk.gov.ofwat.fountain.dao.DataDao.addToTaggedCache(java.util.Map<String, Object>))")
	public void addToCache(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return;
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		logger.debug("start addToTaggedCache");
		Object[] args = pjp.getArgs();
		Map<String, Object> dataMap = (Map<String, Object>)args[0];
		taggedData.putAll(dataMap);
		logger.debug("end addToTaggedCache");
		return;
	}


	@Around("execution(public void uk.gov.ofwat.fountain.dao.DataDao.removeFromCacheForValueId(int))")
	public void removeFromCacheOnRemoveFromCacheForValueId(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return;
		}
		
		Object[] args = pjp.getArgs();
		String key = ""+args[0];

		if (null != taggedData) {
	    	taggedData.remove(key);
		}
	}

}