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

package uk.gov.ofwat.fountain.aspect.cache.dao.specific.lockDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import clover.org.jfree.ui.about.SystemProperties;

import uk.gov.ofwat.fountain.aspect.cache.dao.DaoCache;
import uk.gov.ofwat.fountain.dao.LockDao;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.form.DataKey;


/**
 * log time taken to execute a method.
 * 
 */
@Aspect
@Order(248)
public class LockDaoAspect extends DaoCache {
	
	private Map<String, Lock> cacheByKey;
	private LockDao lockDao;
	
	public void setLockDao(LockDao lockDao) {
		this.lockDao = lockDao;
	}

	public Map getCache() {
		return cacheByKey;
	}
	
	@SuppressWarnings("unchecked")
	public void setCache(Map cache) {
		cacheByKey = cache;
		// load existing locks from database
		for (Lock lock: lockDao.getAllLocks()) {
			DataKey key = new DataKey();
			key.setItemId(lock.getItemId());
			key.setIntervalId(lock.getIntervalId());
			key.setCompanyId(lock.getCompanyId());
			cacheByKey.put(key.getKey(false), lock);
		}
	}
	
// 	create
	@SuppressWarnings("unchecked")
	@Around("execution(public * uk.gov.ofwat.fountain.dao.LockDao+.create(uk.gov.ofwat.fountain.domain.Lock))")
	public int cacheOnCreate(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (Integer)pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		Lock lock = (Lock)args[0];
		 
		DataKey key = new DataKey();
		key.setItemId(lock.getItemId());
		key.setIntervalId(lock.getIntervalId());
		key.setCompanyId(lock.getCompanyId());
		
		Integer retVal = null;
		if (cacheByKey.containsKey(key.getKey(false))) {
			Lock lockFromCache = cacheByKey.get(key.getKey(false));
			retVal = lockFromCache.getId(); 
		}
		else {
			retVal = (Integer)pjp.proceed();
			cacheByKey.put(key.getKey(false), lock);
			incrementCache(pjp);
		}

		return retVal;
	}

//	deleteLocksForUser
	@SuppressWarnings("unchecked")
	@Around("execution(void uk.gov.ofwat.fountain.dao.LockDao+.deleteLocksForUser(uk.gov.ofwat.fountain.domain.User))")
	public void removeOnDeleteLocksForUser(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		if (null == getCache()) {
			pjp.proceed();
			return;
		}

		Object[] args = pjp.getArgs();
		User user = (User)args[0];
		
		pjp.proceed();
		List<Lock> locksToRemove = new ArrayList<Lock>();
		synchronized (cacheByKey) {   
			for (Lock lock: cacheByKey.values()) {
				if (lock.getUser().equals(user)) {
					locksToRemove.add(lock);
				}
			}
		}
		for (Lock lock: locksToRemove) {
			DataKey key = new DataKey();
			key.setItemId(lock.getItemId());
			key.setIntervalId(lock.getIntervalId());
			key.setCompanyId(lock.getCompanyId());
			cacheByKey.remove(key.getKey(false));
			//	TODO decrementCache(pjp);
		}
	}

//	getLockingUser
	@Around("execution(public uk.gov.ofwat.fountain.domain.User uk.gov.ofwat.fountain.dao.LockDao+.getLockingUser(int, int, int))")
	public User cacheOnGetLockingUser(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			return (User)pjp.proceed();
		}
		if (null == getCache()) {
			initialiseCache(pjp.getTarget().getClass());
		}

		Object[] args = pjp.getArgs();
		DataKey key = new DataKey();
		key.setItemId((Integer)args[0]);
		key.setIntervalId((Integer)args[1]);
		key.setCompanyId((Integer)args[2]);
		
		User retVal = null;
		if (cacheByKey.containsKey(key.getKey(false))) {
			retVal = cacheByKey.get(key.getKey(false)).getUser();
		}
		else {
			retVal = null;
		}

		return retVal;
	}

//	deleteExpiredLocks
	@SuppressWarnings("unchecked")
	@Around("execution(void uk.gov.ofwat.fountain.dao.LockDao+.deleteExpiredLocks())")
	public void deleteExpiredLocks(ProceedingJoinPoint pjp) throws Throwable {
		if(!isEnabled()){
			pjp.proceed();
			return;
		}
		if (null == getCache()) {
			pjp.proceed();
			return;
		}

		List<Lock> locksToRemove = lockDao.getExpiredLocks();
		List<Integer> lockIds = new ArrayList<Integer>();
		for (Lock lock: locksToRemove) {
			lockIds.add(lock.getId());
			DataKey key = new DataKey();
			key.setItemId(lock.getItemId());
			key.setIntervalId(lock.getIntervalId());
			key.setCompanyId(lock.getCompanyId());
			cacheByKey.remove(key.getKey(false));
		}
		if (!lockIds.isEmpty()) {
			lockDao.deleteLocks(lockIds);
		}
	}
	
	
	// methods NOT cached
//	public Lock findByLockId(int lockId){
//	public List<Lock>getLocksForUser(User user){
//	public boolean isItemLockedByOtherUser(int itemId, int intervalId, int companyId, User currentUser) {
//	public void refreshLock(Lock lock) {
//	public void refreshLocks(List<Integer> lockIds) {
	
}
