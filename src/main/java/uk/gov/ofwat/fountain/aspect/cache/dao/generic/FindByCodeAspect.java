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

package uk.gov.ofwat.fountain.aspect.cache.dao.generic;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.collections.map.LRUMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.dao.EntityDao;
import uk.gov.ofwat.fountain.domain.Coded;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect("perthis(uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.findByCodePC() ||" +
				"uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.deleteCodedPC() ||" +
				"uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.updateCodedPC())")
@Order(210)
public class FindByCodeAspect extends DaoCache {
	
	private Map<String, Object> cacheByCode;

	public Map<String, Object> getCache() {
		return cacheByCode;
	}

	public void setCache(Map cache) {
		cacheByCode = cache;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.findByCodePC()")
	public Object cacheOnFindByCode(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		if (null == cacheByCode) {
			cacheByCode = Collections.synchronizedMap(new LRUMap(getCacheSize()));
		}
		Object[] args = pjp.getArgs();
		String key = (String)args[0];
		
		Object retVal = null;
		if (cacheByCode.containsKey(key)) {
			retVal = cacheByCode.get(key);
		}
		else {
			retVal = pjp.proceed();
			if (null != retVal) {
				// don't store nulls.
				cacheByCode.put(key, (Object)retVal);
				incrementCache(pjp);
			}
		}

		return retVal;
	}


	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.deleteCodedPC()")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {

		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		if (null != cacheByCode) {
			Integer key = (Integer)args[0];
			Coded coded = (Coded) ((EntityDao)pjp.getThis()).findById(key);
			String code = coded.getCode(); 
			pjp.proceed(args);
			cacheByCode.remove(code);
		}
		else {
			pjp.proceed(args);
		}
	}

	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.updateCodedPC()")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		if (null != cacheByCode) {
			Coded coded = (Coded)args[0];
			String code = coded.getCode(); 
			pjp.proceed(args);
			cacheByCode.remove(code);
		}
		else {
			pjp.proceed(args);
		}
	}
}