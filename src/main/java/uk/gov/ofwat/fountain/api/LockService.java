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

import java.util.List;

import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;

public interface LockService {

	void startLockTimer();

	void expireLocks();
	
	Long getDuration();
	
	/**
	 * 
	 * @param itemId
	 * @param intervalId
	 * @param companyId
	 * @param user - the user invoking the request
	 * @param editable
	 * @param readOnly
	 * @return the user with a lock on the cell
	 */
	Lock updateLockStatus(int itemId, int intervalId, int companyId, User user, boolean editable);
	
	Lock updateLockStatus(int itemId, int intervalId, int companyId, User user, boolean editable, long runId);
	
	void clearLocksForUser(int userId);
	
	User getLockingUser(int itemId, int intervalId, int companyId);
	
	User getLockingUser(int itemId, int intervalId, int companyId, long runId);
	
	User getLockingUser(UserEdit userEdit);

	/**
	 * 
	 * @return a list of all users currently locking items
	 */
	List<User> getLockingUsers();
	
	public void refreshLock(Lock lock);

	void lockForEdit(UserEdit edit, User user);

	void refreshLocks(List<Lock> locks);


}
