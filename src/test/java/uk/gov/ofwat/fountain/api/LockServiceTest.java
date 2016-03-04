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

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.LockDao;
import uk.gov.ofwat.fountain.dao.UserDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;


public class LockServiceTest extends TestCase {
	
	private LockServiceImpl lockService;
	private LockDao mockLockDao;
	private UserDao mockUserDao;
	private User mockUser;
	private User mockLockingUser;
	private Data mockData;
	private Item mockItem;
	private Interval mockInterval;
	private Company mockCompany;
	private int itemId = 1;
	private int intervalId = 1;
	private int companyId = 1;
	private Long duration = new Long(7);
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		lockService = new LockServiceImpl();
		mockLockDao = mock(LockDao.class);
		mockUserDao = mock(UserDao.class);
		mockUser = mock(User.class);
		when(mockUser.getId()).thenReturn(23);
		when(mockUser.getName()).thenReturn("beelzebub");
		when(mockUserDao.findById(23)).thenReturn(mockUser);
		mockLockingUser = mock(User.class);
		mockData = mock(Data.class);
		mockCompany = mock(Company.class);
		mockInterval = mock(Interval.class);
		mockItem = mock(Item.class);
	
		System.out.println("TEST: Done");
}

	public void testClearLocksForUser(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testClearLocksForUser");
		lockService.setLockDao(mockLockDao);
		lockService.setUserDao(mockUserDao);
		lockService.clearLocksForUser(mockUser.getId());

		verify(mockLockDao).deleteLocksForUser(mockUser);
	
		System.out.println("TEST: Done");
}

	public void testExpireLocks(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testExpireLocks");
		lockService.setLockDao(mockLockDao);
		lockService.expireLocks();

		verify(mockLockDao).deleteExpiredLocks();
	
		System.out.println("TEST: Done");
}

	public void testSetGetDuration() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSetGetDuration");
		lockService.setDuration(duration);
		Assert.assertEquals(duration, lockService.getDuration());
	
		System.out.println("TEST: Done");
}
	
	public void testGetLocksForUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetLocksForUser");
		List<Lock> locks = new ArrayList<Lock>();
		when(mockLockDao.getLocksForUser(mockUser)).thenReturn(locks);
		lockService.setLockDao(mockLockDao);
		
		Assert.assertTrue(locks == lockService.getLocksForUser(mockUser));
	
		System.out.println("TEST: Done");
}

	
	public void testUpdateLockStatus_NoLockingUser_NotForEdit() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateLockStatus_NoLockingUser_NotForEdit");
		getIdsFromData();
		when(mockLockDao.getLockingUser(1, 1, 1, 0)).thenReturn(null);
		
		lockService.setLockDao(mockLockDao);
		lockService.setDuration(new Long(60));
		lockService.updateLockStatus(1, 1, 1, null, false);
	
		System.out.println("TEST: Done");
}
	
	public void testUpdateLockStatus_NoLockingUser_ForEdit() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateLockStatus_NoLockingUser_ForEdit");
		getIdsFromData();
		when(mockLockDao.getLockingUser(itemId, intervalId, companyId, 0)).thenReturn(null);
		
		lockService.setLockDao(mockLockDao);
		lockService.setDuration(new Long(60));
		lockService.updateLockStatus(itemId, intervalId, companyId, null, true);

		verify(mockLockDao).create(isA(Lock.class));
	
		System.out.println("TEST: Done");
}
	
	public void testUpdateLockStatus_LockingUserIsUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateLockStatus_LockingUserIsUser");
		getIdsFromData();
		when(mockLockDao.getLockingUser(itemId, intervalId, companyId, 0)).thenReturn(mockUser);
		when(mockLockDao.refreshLock(isA(Lock.class))).thenReturn(true);
		
		lockService.setLockDao(mockLockDao);
		lockService.setDuration(new Long(60));
		Lock lock = lockService.updateLockStatus(itemId, intervalId, companyId, mockUser, true);
		assertEquals(mockUser, lock.getUser());

	
		System.out.println("TEST: Done");
}
	
	public void testUpdateLockStatus_LockingUserNotUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdateLockStatus_LockingUserNotUser");
		getIdsFromData();
		when(mockLockDao.getLockingUser(itemId, intervalId, companyId, 0)).thenReturn(mockLockingUser);
		
		lockService.setLockDao(mockLockDao);
		lockService.setDuration(new Long(60));
		Lock lock = lockService.updateLockStatus(itemId, intervalId, companyId, mockUser, true);
		User user = lock.getUser();
		assertEquals("wrong user now has lock", mockLockingUser, user);
		
	
		System.out.println("TEST: Done");
}
	
	public void testRefreshLocks() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testRefreshLocks");
		List<Lock> locks = new ArrayList<Lock>();
		Lock lock1 = new Lock();
		lock1.setId(3);
		lock1.setItemId(3);
		lock1.setIntervalId(2);
		lock1.setCompanyId(1);
		lock1.setUser(mockUser);
		locks.add(lock1);
		Lock lock2 = new Lock();
		lock2.setId(6);
		lock2.setItemId(3);
		lock2.setIntervalId(2);
		lock2.setCompanyId(1);
		lock2.setUser(mockUser);
		locks.add(lock2);
		Lock lock3 = new Lock();
		lock3.setId(9);
		lock3.setItemId(3);
		lock3.setIntervalId(2);
		lock3.setCompanyId(1);
		lock3.setUser(mockUser);
		locks.add(lock3);

		lockService.setLockDao(mockLockDao);
		when(mockLockDao.getLockingUser(3, 2, 1, 0)).thenReturn(mockUser);
		
		lockService.refreshLocks(locks);
		List<Integer> lockIds = new ArrayList<Integer>();
		lockIds.add(3);
		lockIds.add(6);
		lockIds.add(9);
		verify(mockLockDao).refreshLocks(lockIds);
	
		System.out.println("TEST: Done");
}

	
	private void getIdsFromData() {
		when(mockData.getItem()).thenReturn(mockItem);
		when(mockItem.getId()).thenReturn(itemId);
		when(mockData.getInterval()).thenReturn(mockInterval);
		when(mockInterval.getId()).thenReturn(intervalId);
		when(mockData.getCompany()).thenReturn(mockCompany);
		when(mockCompany.getId()).thenReturn(companyId);
	}
}

