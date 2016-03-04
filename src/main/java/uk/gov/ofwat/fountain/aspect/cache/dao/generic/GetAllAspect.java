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

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.dao.CachableCodedDao;
import uk.gov.ofwat.fountain.dao.CachableEntityDao;
import uk.gov.ofwat.fountain.dao.EntityDao;
import uk.gov.ofwat.fountain.domain.Coded;
import uk.gov.ofwat.fountain.domain.Entity;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect("perthis(execution(public * uk.gov.ofwat.fountain.dao.*Dao+.getAll()))")
@Order(205)
public class GetAllAspect extends DaoCache {
	
	private List<Object> cacheAll;
	
	public Map getCache() {
		throw new UnsupportedOperationException();
	}

	public void setCache(Map cache) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		if (null != cacheAll) {
			cacheAll.clear();
		}
	}

	public void initialiseCache(Class target) {
		if (null != target) {
			setTarget(target);
		}
	}

	public int getPercentageFull() {
		return 100;
	}

	@SuppressWarnings("unchecked")
	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.getAllEntityPC()")
	public List cacheOnGetAll(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return (List)pjp.proceed();
		}
		if (null == cacheAll || cacheAll.isEmpty()) {
			cacheAll = (List<Object>) pjp.proceed();
			initialiseCache(pjp.getTarget().getClass());
		}
		return cacheAll;
	}

	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.deleteDaoPC()")
	public void removeFromCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {
		
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		if (null != cacheAll) {
			Integer key = (Integer)args[0];
			Object domainObj = ((EntityDao)pjp.getThis()).findById(key);
			pjp.proceed(args);
			cacheAll.remove(domainObj);
		}
		else {
			pjp.proceed(args);
		}
	}

	@Around("uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache.updateDaoPC()")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		if (null != cacheAll) {
			Object domainObj = args[0];
			pjp.proceed(args);
			cacheAll.clear();
		}
		else {
			pjp.proceed(args);
		}
	}

}