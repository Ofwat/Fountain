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
package uk.gov.ofwat.fountain.aspect.dao;

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;

public class CacheTestLockDaoImpl implements CacheTestLockDao {

	private Lock lock;
	private User user;
	private List<Lock> allLocks;
	
	public void setUser(User user) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUser");
		this.user = user;
	
		System.out.println("TEST: Done");
}
	
	public void setLock(Lock lock) {
		this.lock = lock;
	}
	
	public Lock findById(int id) {
		return this.lock;
	}
	
	public void delete(int id) {
		System.out.println("CacheTestDaoImpl.delete()");
	}

	public void update(Lock lock) {
		System.out.println("CacheTestDaoImpl.updatee()");
	}



	public int create(Lock lock) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteExpiredLocks() {
		// TODO Auto-generated method stub

	}

	public void deleteLocksForUser(User user) {
		// TODO Auto-generated method stub

	}

	public Lock findByLockId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllLockingUsers() {
		return null;
	}

	public User getLockingUser(int itemId, int intervalId, int companyId) {
		return user;
	}

	public List<Lock> getLocksForUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isItemLockedByOtherUser(int itemId, int intervalId,
			int companyId, User currentUser) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Lock> getAllLocks() {
		return allLocks;
	}

	public void setAllLocks(List<Lock> locks) {
		this.allLocks = locks;
	}

	public boolean refreshLock(Lock lock) {
		// TODO Auto-generated method stub
		return true;
	}

	public void deleteLocks(List<Integer> lockIds) {
		if (lockIds.equals("1,2")) {
			allLocks.clear();
		}
	}

	public List<Lock> getExpiredLocks() {
		return allLocks;
	}

	public void lockForEdit(Lock lock) {
		// TODO Auto-generated method stub
		
	}

	public void refreshLocks(List<Integer> lockIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemLockedByOtherUser(int itemId, int intervalId,
			int companyId, User currentUser, long runId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getLockingUser(int itemId, int intervalId, int companyId,
			long runId) {
		return user;
	}

}
