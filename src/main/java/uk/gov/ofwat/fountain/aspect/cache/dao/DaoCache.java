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
package uk.gov.ofwat.fountain.aspect.cache.dao;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.collections.map.LRUMap;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.api.DaoCacheService;

@Aspect
@Order(10)
public abstract class DaoCache {

	private static boolean enabled = true; // default position
	
	/**
	 * is it enabled?
	 * @return true / false
	 */
	public static boolean isEnabled(){
		return enabled;
	}
	
	/**
	 * toggle cache on / off
	 * @param enabled
	 */
	public static void setEnabled(boolean bEnabled){
		enabled = bEnabled;
	}
	// Entity point cuts
	@Pointcut("execution(public * uk.gov.ofwat.fountain.dao.CachableEntityDao+.findById(int))")
	public void findByIdEntityPC() {}

	@Pointcut("execution(public * uk.gov.ofwat.fountain.dao.CachableEntityDao+.getAll())")
	public void getAllEntityPC() {}

	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.CachableEntityDao+.delete(int))")
	public void deleteEntityPC() {}

	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.CachableEntityDao+.update(uk.gov.ofwat.fountain.domain.Entity+))")
	public void updateEntityPC() {}


	// Coded point cuts
	@Pointcut("execution(public * uk.gov.ofwat.fountain.dao.CachableCodedDao+.findByCode(String))")
	public void findByCodePC() {}

	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.CachableCodedDao+.delete(int))")
	public void deleteCodedPC() {}

	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.CachableCodedDao+.update(uk.gov.ofwat.fountain.domain.Coded+))")
	public void updateCodedPC() {}
	
	// Dao point cuts
	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.*Dao+.delete(int))")
	public void deleteDaoPC() {}

	@Pointcut("execution(public void uk.gov.ofwat.fountain.dao.*Dao+.update(Object+))")
	public void updateDaoPC() {}
	

	
	private static org.apache.log4j.Logger log = Logger.getLogger(DaoCache.class);
	private DaoCacheService daoCacheService;
	private int cacheSize;
	private Class target;

	public abstract Map<String, Object> getCache();
	public abstract void setCache(Map cache);
	
	public void init() {
		daoCacheService.addCache(this);
	}
	
	public void clear() {
		Map<String, Object> cache = getCache();
		if (null != cache) {
			cache.clear();
		}
	}

	protected void initialiseCache() {
		initialiseCache(null);
	}
	
	@SuppressWarnings("unchecked")
	public void initialiseCache(Class target) {
		setCache(Collections.synchronizedMap(new LRUMap(getCacheSize())));
		if (null != target) {
			this.target = target;
		}
	}

	protected void incrementCache(ProceedingJoinPoint pjp) {
//		if (getNoOfObjectsCached() > ((getCacheSize() /100) * getPercentageFull() +1)) {
//			if (0 == (getPercentageFull() %10)) {
//				if ((getPercentageFull() %10) > 75) {
//					log.info("Cache " + getClass().getSimpleName() + " for target " + getTarget().getSimpleName() + " is " + getPercentageFull() + "% of cache capacity has been used.");
//				}
//				else {
//					log.warn("Cache " + getClass().getSimpleName() + " for target " + getTarget().getSimpleName() + " is " + getPercentageFull() + "% of cache capacity has been used.");
//				}
//			}
//		}
	}
	
	public DaoCacheService getDaoCacheService() {
		return daoCacheService;
	}

	public void setDaoCacheService(DaoCacheService daoCacheService) {
		this.daoCacheService = daoCacheService;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getNoOfObjectsCached() {
		if (null == getCache()) {
			return 0;
		}
		return getCache().size();
	}

	public int getPercentageFull() {
		if (null == getCache()) {
			return 0;
		}
		return (100/getCacheSize()) * getNoOfObjectsCached();
	}

	public Class getTarget() {
		return target;
	}
	public void setTarget(Class target) {
		this.target = target;
	}
	
	public String getPersistantFileName() {
		if (null == getTarget()) {
			return null;
		}
		return getClass().getSimpleName() + "-" + getTarget().getSimpleName() + ".ser";
	}
}
