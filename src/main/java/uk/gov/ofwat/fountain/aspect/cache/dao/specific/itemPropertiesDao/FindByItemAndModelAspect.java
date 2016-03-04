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
package uk.gov.ofwat.fountain.aspect.cache.dao.specific.itemPropertiesDao;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.ItemDao;

@Aspect
@Order(241)
public class FindByItemAndModelAspect extends DaoCache implements UpdateListener {
	private Map<String, Object> cacheByItemAndModel;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map getCache() {
		return cacheByItemAndModel;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByItemAndModel = cache;
	}

	public void updateOccurred() {
		initialiseCache();
	}
	
	/**
	 * Because we want to invalidate on changes to item
	 * @param itemDao
	 */
	public void setItemDao(ItemDao itemDao){
		itemDao.addItemUpdateListener(this);
	}
	
	@Around("execution(* uk.gov.ofwat.fountain.dao.ItemPropertiesDao+.findByItemAndModel(int, int))")
	public Object cacheOnFindByItemAndModel(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1];
		
		Object retVal = null;
		if (cacheByItemAndModel.containsKey(key)) {
			retVal = cacheByItemAndModel.get(key);
		}
		else {
			retVal = pjp.proceed();
			if (null != retVal) {
				// don't store nulls. Items may be added to the model later. 
				cacheByItemAndModel.put(key, (Object)retVal);
				incrementCache(pjp);
			}
		}
		return retVal;
	}
}
