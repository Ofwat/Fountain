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
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.domain.Entity;



@Aspect("perthis(uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.findByIdEntityPC() || " +
				"uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.deleteEntityPC()) ||" +
				"uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.updateEntityPC()")
@Order(215)
public class FindByIdAspect extends DaoCache {
	
	private Map<Integer, Object> cacheOnId;

	public Map getCache() {
		return cacheOnId;
	}
	
	public void setCache(Map cache) {
		cacheOnId = cache;
	}

	@SuppressWarnings("unchecked")
	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.findByIdEntityPC()")
	public Object cacheOnFindByID(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		Integer key = (Integer)args[0];
		
		Object retVal = null;
		if (cacheOnId.containsKey(key)) {
			retVal = cacheOnId.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheOnId.put(key, retVal);
			incrementCache(pjp);
		}
		return retVal;
	}

	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.deleteEntityPC()")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {

		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		if (null != cacheOnId) {
			Integer key = (Integer)args[0];
			pjp.proceed(args);
			cacheOnId.remove(key);
		}
		else {
			pjp.proceed(args);
		}
	}

	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.updateEntityPC()")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args = pjp.getArgs();
		if (null != cacheOnId) {
			Entity entity = (Entity)args[0];
			Integer key = entity.getId(); 
			pjp.proceed(args);
			cacheOnId.remove(key);
		}
		else {
			pjp.proceed(args);
		}
	}
}