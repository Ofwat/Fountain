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
package uk.gov.ofwat.fountain.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.dao.DeadlockLoserDataAccessException;

import uk.gov.ofwat.fountain.dao.LockDao;
import uk.gov.ofwat.fountain.dao.UserDao;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;

public class LockServiceImpl implements LockService {
	
	private Long duration;
	private LockDao lockDao;
	private UserDao userDao;
	private Boolean timerStarted = false;


	public void startLockTimer() {
		System.out.println("startLockTimer()");
		Timer timer = new Timer();
		timer.schedule(new ExpireLocksTask(), this.getDuration() * 1000, this.getDuration() * 1000);
		timerStarted = true;
	}
	

	class ExpireLocksTask extends TimerTask {
		public void run() {
			System.out.println("ExpireLocksTask.run()");
			lockDao.deleteExpiredLocks();
		}
	}
	
	public void setLockDao(LockDao lockDao){
		this.lockDao = lockDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public List<Lock> getLocksForUser(User user){
		return lockDao.getLocksForUser(user);
	}
	
	public Long getDuration() {
		return duration;
	}

	public void expireLocks() {
		lockDao.deleteExpiredLocks();
	}

	public Lock updateLockStatus(int itemId,
            int intervalId, 
            int companyId, 
            User user, 
            boolean editable, 
            long runId) {
		if (!timerStarted) {
			startLockTimer();
		}
		User lockingUser = getLockingUser(itemId, intervalId, companyId, runId);
		Lock lock = new Lock(itemId, intervalId, companyId, user, runId);
		if (lockingUser!=null){
			if (!lockingUser.equals(user)){
				// someone else has a lock
				lock.setUser(lockingUser);
				return lock;
			} else {
				// this user already has it locked.
				refreshLock(lock);
				return lock;
			}
		} else {
			if (editable){
				// item was not locked but a lock was requested
				lockDao.create(lock);
				return lock;
			}
			else{
				// no one has a lock and the lock was not requested
				return null;
			}
		}
		
		
	}
	
	public Lock updateLockStatus(int itemId,
			                     int intervalId, 
			                     int companyId, 
			                     User user, 
			                     boolean editable) {
		if (!timerStarted) {
			startLockTimer();
		}
		User lockingUser = getLockingUser(itemId, intervalId, companyId);
		Lock lock = new Lock(itemId, intervalId, companyId, user);
		if (lockingUser!=null){
			if (!lockingUser.equals(user)){
				// someone else has a lock
				lock.setUser(lockingUser);
				return lock;
			} else {
				// this user already has it locked.
				refreshLock(lock);
				return lock;
			}
		} else {
			if (editable){
				// item was not locked but a lock was requested
				lockDao.create(lock);
				return lock;
			}
			else{
				// no one has a lock and the lock was not requested
				return null;
			}
		}
	}
	
	public void refreshLock(Lock lock) {
		User currentUser;
		if(lock.getRunId() != 0){
			currentUser = getLockingUser(lock.getItemId(), lock.getIntervalId(), lock.getCompanyId(), lock.getRunId());
		}else{
			currentUser = getLockingUser(lock.getItemId(), lock.getIntervalId(), lock.getCompanyId());
		}
		if (currentUser!= null){
			if (currentUser.equals(lock.getUser())){
				boolean success = lockDao.refreshLock(lock);
				int tries = 1;
				while (!success && tries  < 5) {
					tries++;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					success = lockDao.refreshLock(lock);
				}
				if (!success) {
					throw new DeadlockLoserDataAccessException("Refresh lock " + lock.getItemId() + " failed 5 times.", new Exception("Caused by db deadlock com.mysql.jdbc.exceptions.jdbc4.MySQLTransactionRollbackException"));
				}
			}
		}
	}
	
	public void refreshLocks(List<Lock> locks) {
		List<Integer> lockIds = new ArrayList<Integer>();
		for (Lock lock: locks) {
			User currentUser;
			if(lock.getRunId() != 0){
				currentUser = getLockingUser(lock.getItemId(), lock.getIntervalId(), lock.getCompanyId(), lock.getRunId());
			}else{
				currentUser = getLockingUser(lock.getItemId(), lock.getIntervalId(), lock.getCompanyId());
			}
			if (currentUser!= null &&
				currentUser.equals(lock.getUser())){
				lockIds.add(lock.getId());
			}
		}
		if (!lockIds.isEmpty()) {
			lockDao.refreshLocks(lockIds);
		}
	}

	public void lockForEdit(UserEdit edit, User user) {
		Lock lock = new Lock(edit.getItemId(), edit.getIntervalId(), edit.getCompanyId(), user, edit.getRunId());
		lockDao.lockForEdit(lock);
	}

	public User getLockingUser(int itemId, int intervalId, int companyId) {
		return lockDao.getLockingUser(itemId, intervalId, companyId, 0);
	}
	
	public User getLockingUser(int itemId, int intervalId, int companyId, long runId) {
		return lockDao.getLockingUser(itemId, intervalId, companyId, runId);
	}

	public User getLockingUser(UserEdit userEdit) {
		return getLockingUser(userEdit.getItemId(), userEdit.getIntervalId(), userEdit.getCompanyId(), userEdit.getRunId());
	}

	public void clearLocksForUser(int userId) {
		User user = userDao.findById(userId);
		lockDao.deleteLocksForUser(user);	
	}

	public List<User> getLockingUsers() {
		return lockDao.getAllLockingUsers();
	}

}
