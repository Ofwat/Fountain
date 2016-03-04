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
package uk.gov.ofwat.fountain.aspect.cache.dao.specific.RunModelDao;

import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.RunModel;

@Aspect
@Order(270)
public class FindByRunAndModelAndCompanyAspect extends DaoCache {
	private Map<String, Object> cacheByRunAndModelAndCompany;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map getCache() {
		return cacheByRunAndModelAndCompany ;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByRunAndModelAndCompany = cache;
	}

	@Around("execution(* uk.gov.ofwat.fountain.dao.RunModelDao+.findByRunAndModelAndCompany(int, int, int))")
	public Object cacheOnFindByItemAndModel(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1] + "-" + (Integer)args[2];
		
		Object retVal = null;
		if (cacheByRunAndModelAndCompany.containsKey(key)) {
			retVal = cacheByRunAndModelAndCompany.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheByRunAndModelAndCompany.put(key, (Object)retVal);
			incrementCache(pjp);
		}
		return retVal;
	}


	@Around("execution(public int uk.gov.ofwat.fountain.dao.RunModelDao+.create(uk.gov.ofwat.fountain.domain.RunModel))")
	public Integer removeFromCacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (Integer)pjp.proceed();
		}
		Object[] args = pjp.getArgs();
		if (null != cacheByRunAndModelAndCompany) {
			RunModel runModel = (RunModel)args[0];
			String key = runModel.getRunId() + "-" + runModel.getModelId() + "-" + runModel.getCompanyId();
			cacheByRunAndModelAndCompany.remove(key);
		}
		return (Integer)pjp.proceed(args);
	}

	@Around("execution(public void uk.gov.ofwat.fountain.dao.RunModelDao+.update(uk.gov.ofwat.fountain.domain.RunModel))")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if (null != cacheByRunAndModelAndCompany) {
			RunModel runModel = (RunModel)args[0];
			String key = runModel.getRunId() + "-" + runModel.getModelId() + "-" + runModel.getCompanyId();
	    	cacheByRunAndModelAndCompany.remove(key);
		}
	}

}
