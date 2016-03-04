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

package uk.gov.ofwat.fountain.aspect.cache.dao.specific.potDao;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Pot;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(245)
public class FindByTableIdAspect extends DaoCache implements UpdateListener{
	
	private Map<String, List<Pot>> cacheByTableId;
	
	public Map getCache() {
		return cacheByTableId;
	}
	
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByTableId = cache;
	}

	public void setItemDao(ItemDao itemDao){
		itemDao.addItemUpdateListener(this);
	}
	
	public void updateOccurred() {
		initialiseCache();
	}

	@SuppressWarnings("unchecked")
	@Around("execution(* uk.gov.ofwat.fountain.dao.PotDao+.findByTableId(int))")
	public List<Pot> cacheOnFindByTableId(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return (List<Pot>)pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = "" + (Integer)args[0];
		
		List<Pot> retVal = null;
		if (cacheByTableId.containsKey(key)) {
			retVal = cacheByTableId.get(key);
		}
		else {
			retVal = (List<Pot>)pjp.proceed();
			cacheByTableId.put(key, retVal);
			incrementCache(pjp);
		}

		return retVal;
	}

	@Around("execution(void uk.gov.ofwat.fountain.dao.PotDao+.delete(int))")
	public void clearCacheOnDelete(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		throw new UnsupportedOperationException("uk.gov.ofwat.fountain.dao.PotDao.delete(int) has not been implemented. If you are implementing it you must implement clearCacheOnDelete()");
	}

	@Around("execution(void uk.gov.ofwat.fountain.dao.PotDao+.update(uk.gov.ofwat.fountain.domain.Pot))")
	public void clearCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}

		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if (null != cacheByTableId) {
			this.clear();
		}
	}

	@Around("execution(int uk.gov.ofwat.fountain.dao.PotDao+.create(uk.gov.ofwat.fountain.domain.Pot, int))")
	public int clearCacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {

		if(!isEnabled()){
			return (Integer)pjp.proceed();
		}
		Object[] args = pjp.getArgs();
		Integer retVal = (Integer)pjp.proceed(args);
		if (null != cacheByTableId) {
			this.clear();
		}
		return retVal;
	}
	
	

}