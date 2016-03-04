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
package uk.gov.ofwat.fountain.aspect;
        

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.aspect.dao.CacheTestLockDao;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class LockDaoAspectTest {
    @Autowired    
    private CacheTestLockDao cacheTestDao;

    @Autowired    
    private DaoCacheService daoCacheService;
    
    @BeforeClass
	public static void setup() {
	}

	@Test
	public void testLockIsCachedAndReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testLockIsCachedAndReturned");
		// set an group to be returned
		User user1 = new User();
		user1.setId(1);
		cacheTestDao.setUser(user1);
		cacheTestDao.setAllLocks(new ArrayList<Lock>());
		
		// calling getLockingUser() does NOT caches the lock and does not check the database.
		Assert.assertEquals(null, cacheTestDao.getLockingUser(1, 1, 1, 0));

		// create a lock. This caches the lock.
		Lock lock = new Lock();
		lock.setItemId(1);
		lock.setIntervalId(2);
		lock.setCompanyId(3);
		User user3 = new User();
		user3.setId(3);
		lock.setUser(user3);
		cacheTestDao.create(lock);
		
		// the user from the cached lock is returned
		Assert.assertEquals(3, ((User)cacheTestDao.getLockingUser(1, 2, 3, 0)).getId());
		
		// create another lock. This caches the lock.
		Lock lock2 = new Lock();
		lock2.setItemId(1);
		lock2.setIntervalId(2);
		lock2.setCompanyId(3);
		User user4 = new User();
		user4.setId(4);
		lock2.setUser(user4);
		cacheTestDao.create(lock2);
		
		// we should get lock cached earlier rather than the one we just created.
		Assert.assertEquals(3, ((User)cacheTestDao.getLockingUser(1, 2, 3, 0)).getId());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testLocksAreCachedOnStartup() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testLocksAreCachedOnStartup");
		// create some locks 
		Lock lock = new Lock();
		lock.setItemId(1);
		lock.setIntervalId(2);
		lock.setCompanyId(3);
		User user1 = new User();
		user1.setId(4);
		lock.setUser(user1);
		
		Lock lock2 = new Lock();
		lock2.setItemId(5);
		lock2.setIntervalId(6);
		lock2.setCompanyId(7);
		User user2 = new User();
		user2.setId(8);
		lock2.setUser(user2);
		
		List<Lock> locks = new ArrayList<Lock>();
		locks.add(lock);
		locks.add(lock2);
		cacheTestDao.setAllLocks(locks);
		
		// calling getLockingUser() does NOT caches the lock and does not check the database.
		Assert.assertEquals(4, ((User)cacheTestDao.getLockingUser(1, 2, 3, 0)).getId());
		Assert.assertEquals(8, ((User)cacheTestDao.getLockingUser(5, 6, 7, 0)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testLocksAreDeletedForUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testLocksAreDeletedForUser");
		// create some locks 
		Lock lock = new Lock();
		lock.setItemId(1);
		lock.setIntervalId(2);
		lock.setCompanyId(3);
		User user1 = new User();
		user1.setId(4);
		user1.setName("Gertrude");
		lock.setUser(user1);
		
		Lock lock2 = new Lock();
		lock2.setItemId(5);
		lock2.setIntervalId(6);
		lock2.setCompanyId(7);
		User user2 = new User();
		user2.setId(8);
		user2.setName("Tinky Winky");
		lock2.setUser(user2);
		
		List<Lock> locks = new ArrayList<Lock>();
		locks.add(lock);
		locks.add(lock2);
		cacheTestDao.setAllLocks(locks);
		
		// calling getLockingUser() does NOT caches the lock and does not check the database.
		Assert.assertEquals(4, ((User)cacheTestDao.getLockingUser(1, 2, 3, 0)).getId());
		Assert.assertEquals(8, ((User)cacheTestDao.getLockingUser(5, 6, 7, 0)).getId());
		
		cacheTestDao.deleteLocksForUser(user1);
		// user1 should be gone
		Assert.assertEquals(null, cacheTestDao.getLockingUser(1, 2, 3, 0));
		// user 2 should still be there
		Assert.assertEquals(8, ((User)cacheTestDao.getLockingUser(5, 6, 7, 0)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testDeleteExpiredLocks() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDeleteExpiredLocks");
		// create some locks 
		Lock lock = new Lock();
		lock.setId(1);
		lock.setItemId(1);
		lock.setIntervalId(2);
		lock.setCompanyId(3);
		User user1 = new User();
		user1.setId(4);
		user1.setName("Gertrude");
		lock.setUser(user1);
		
		Lock lock2 = new Lock();
		lock2.setId(2);
		lock2.setItemId(5);
		lock2.setIntervalId(6);
		lock2.setCompanyId(7);
		User user2 = new User();
		user2.setId(8);
		user2.setName("Tinky Winky");
		lock2.setUser(user2);
		
		List<Lock> locks = new ArrayList<Lock>();
		locks.add(lock);
		locks.add(lock2);
		cacheTestDao.setAllLocks(locks);
		
		// To create a cache.
		Assert.assertEquals(4, ((User)cacheTestDao.getLockingUser(1, 2, 3, 0)).getId());

		// calling getLockingUser() does NOT caches the lock and does not check the database.
		Assert.assertEquals(locks, cacheTestDao.getExpiredLocks());
		
		cacheTestDao.deleteExpiredLocks();
		// user1 should be gone
		Assert.assertTrue(cacheTestDao.getExpiredLocks().isEmpty());
	
		System.out.println("TEST: Done");
}
	
}
