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
package uk.gov.ofwat.fountain.aspect.cache.dao.specific.branchDao;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.domain.Branch;

@Aspect
@Order(283)
public class FindByCompanyAndRunAspect extends DaoCache {
	private Map<String, Object> cacheByCompanyAndRun;
	
	@SuppressWarnings("unchecked")
	@Override
	public Map getCache() {
		return cacheByCompanyAndRun ;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByCompanyAndRun = cache;
	}

	@Around("execution(* uk.gov.ofwat.fountain.dao.BranchDao+.findByCompanyAndRun(int, int))")
	public Object cacheOnFindByCompanyAndRun(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}
		
		Object[] args = pjp.getArgs();
		String key = (Integer)args[0] + "-" + (Integer)args[1];
		if (key.equals("0-0")) {
			// old style branches that don't have a company or run 
			return pjp.proceed();
		}

		Object retVal = null;
		if (cacheByCompanyAndRun.containsKey(key)) {
			retVal = cacheByCompanyAndRun.get(key);
		}
		else {
			retVal = pjp.proceed();
			cacheByCompanyAndRun.put(key, (Object)retVal);
			incrementCache(pjp);
		}
		return retVal;
	}

	@Around("execution(public uk.gov.ofwat.fountain.domain.Branch uk.gov.ofwat.fountain.dao.BranchDao+.create(String , int , int ))")
	public Branch removeFromCacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (Branch)pjp.proceed();
		}
		Object[] args = pjp.getArgs();
		if (null != cacheByCompanyAndRun) {
			// Note args[0] not part of key
			String key = (Integer)args[1] + "-" + (Integer)args[2];
			cacheByCompanyAndRun.remove(key);
		}
		return (Branch)pjp.proceed(args);
	}

	@Around("execution(public void uk.gov.ofwat.fountain.dao.BranchDao+.update(uk.gov.ofwat.fountain.domain.Branch))")
	public void removeFromCacheOnUpdate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		Object[] args = pjp.getArgs();
		pjp.proceed(args);
		if (null != cacheByCompanyAndRun) {
			Branch branch = (Branch)args[0];
			String key = branch.getCompanyId() + "-" + branch.getRunId();
			cacheByCompanyAndRun.remove(key);
		}
	}

}
