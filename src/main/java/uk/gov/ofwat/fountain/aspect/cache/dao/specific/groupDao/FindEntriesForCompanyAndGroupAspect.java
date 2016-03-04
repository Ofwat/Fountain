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

package uk.gov.ofwat.fountain.aspect.cache.dao.specific.groupDao;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(250)
public class FindEntriesForCompanyAndGroupAspect extends DaoCache implements UpdateListener{
	
	private Map<String, List<GroupEntry>> cacheByForCompanyAndGroup;
	
	public void setGroupDao(GroupDao groupDao){
		groupDao.addItemUpdateListener(this);
	}
	
	public void updateOccurred() {
		initialiseCache();
	}

	public Map getCache() {
		return cacheByForCompanyAndGroup;
	}
	
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByForCompanyAndGroup = cache;
	}
	

	@SuppressWarnings("unchecked")
	@Around("execution(public * uk.gov.ofwat.fountain.dao.GroupDao+.findEntriesForCompanyAndGroup(uk.gov.ofwat.fountain.domain.Company, uk.gov.ofwat.fountain.domain.Group))")
	public List<GroupEntry> cacheOnFindEntriesForCompanyAndGroup(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return (List)pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		String key = ((Company)args[0]).getId() + "-" + ((Group)args[1]).getId();
		
		List<GroupEntry> retVal = null;
		if (cacheByForCompanyAndGroup.containsKey(key)) {
			retVal = cacheByForCompanyAndGroup.get(key);
		}
		else {
			retVal = (List<GroupEntry>)pjp.proceed();
			cacheByForCompanyAndGroup.put(key, retVal);
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