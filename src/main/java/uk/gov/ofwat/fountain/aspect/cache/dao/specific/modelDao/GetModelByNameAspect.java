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

package uk.gov.ofwat.fountain.aspect.cache.dao.specific.modelDao;

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
import uk.gov.ofwat.fountain.domain.Model;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(383)
public class GetModelByNameAspect extends DaoCache {
	
	private Map<String, Object> cacheModelByName;

	public Map<String, Object> getCache() {
		return cacheModelByName;
	}

	public void setCache(Map cache) {
		cacheModelByName = cache;
	}
	
	@Around("execution(uk.gov.ofwat.fountain.domain.Model uk.gov.ofwat.fountain.dao.ModelDao+.getModelByName(String))")
	public Object cacheOnFindByCode(ProceedingJoinPoint pjp) throws Throwable {

		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		Object[] args = pjp.getArgs();
		String key = (String)args[0];
		
		Object retVal = null;
		if (cacheModelByName.containsKey(key)) {
			retVal = cacheModelByName.get(key);
		}
		else {
			retVal = pjp.proceed();
			if (null != retVal) {
				// don't store nulls.
				cacheModelByName.put(key, (Object)retVal);
				incrementCache(pjp);
			}
		}

		return retVal;
	}


	@Around("execution(void uk.gov.ofwat.fountain.dao.ModelDao+.delete(int))")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {

		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if(!isEnabled()){
			return;
		}
		if (null == cacheModelByName) {
			return;
		}

		Integer key = (Integer)args[0];
		Model model = (Model) ((EntityDao)pjp.getThis()).findById(key);
		String name = model.getName(); 
		cacheModelByName.remove(name);
	}

	@Around("execution(void uk.gov.ofwat.fountain.dao.ModelDao+.update(uk.gov.ofwat.fountain.domain.Model))")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if(!isEnabled()){
			return;
		}
		if (null == cacheModelByName) {
			return;
		}

		Model model = (Model)args[0];
		String name = model.getName(); 
		cacheModelByName.remove(name);
	}
}