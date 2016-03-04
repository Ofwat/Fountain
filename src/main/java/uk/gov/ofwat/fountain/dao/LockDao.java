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
package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;

/**
 * Never cacheable
 */
public interface LockDao {
	
	public int create(Lock lock);
	
	public Lock findByLockId(int id);
	
	public void deleteExpiredLocks();
	
	public void deleteLocksForUser(User user);
	
	public List<Lock>getLocksForUser(User user);
	
	/**
	 * 
	 * @param itemId
	 * @param yearId
	 * @param companyId
	 * @param currentUser
	 * @return true if the data in question is locked by anyone other than the current user
	 * @return true if currentUser is null and data is locked by anyone
	 */
	public boolean isItemLockedByOtherUser(int itemId, 
			                               int intervalId, 
			                               int companyId, 
			                               User currentUser,
			                               long runId);
	
	/**
	 * 
	 * @param itemId
	 * @param yearId
	 * @param companyId
	 * @return the username or null if no locks exist
	 */
	public User getLockingUser(int itemId, 
                               int intervalId, 
                               int companyId,
                               long runId);

	
	/**
	 * @return
	 */
	public List<User> getAllLockingUsers();
	
	public List<Lock> getAllLocks();
	
	public boolean refreshLock(Lock lock);

	public void lockForEdit(Lock lock);

	public void refreshLocks(List<Integer> lockIds);

	public List<Lock> getExpiredLocks();
	
	public void deleteLocks(List<Integer> lockIds);


}
