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
package uk.gov.ofwat.fountain.aspect.cache.dao.specific.lineDao;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.ItemDao;

@Aspect
@Order(242)
public class FindByTableItemAspect extends DaoCache implements UpdateListener {

	private Map<String, Object> cacheByTableAndItem;
	
	@Override
	public Map getCache() {
		return cacheByTableAndItem;
	}

	/**
	 * Because we want to invalidate on changes to item
	 * @param itemDao
	 */
	public void setItemDao(ItemDao itemDao){
		itemDao.addItemUpdateListener(this);
	}
	
	@Override
	public void setCache(Map cache) {
		this.cacheByTableAndItem = cache;
		
	}

	public void updateOccurred() {
		initialiseCache();
		
	}
	
	@Around("execution(* uk.gov.ofwat.fountain.dao.LineDao+.findByTableItem(int, int))")
	public Object cacheOnFindByTableAndItem(ProceedingJoinPoint pjp)
			throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1];
		
		Object retVal = null;
		if (cacheByTableAndItem.containsKey(key)) {
			retVal = cacheByTableAndItem.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheByTableAndItem.put(key, (Object)retVal);
			incrementCache(pjp);
		}

		return retVal;
	}

}
