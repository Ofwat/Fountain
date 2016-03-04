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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class LockDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	

	@Autowired
	private LockDao lockDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		
		// assume a few ids..
		int companyId = 1;
		int itemId = 1;
		int intervalId = 1;
		User user = new User();
		user.setId(1);
		user.setName("test user");
		
		Lock lock = new Lock();
		
		lock.setCompanyId(companyId);
		lock.setItemId(itemId);
		lock.setIntervalId(intervalId);
		lock.setUser(user);
		int id = lockDao.create(lock);
		Lock rtn = lockDao.findByLockId(id);
		assertNotNull("Unable to find recently created lock", rtn);
	
		System.out.println("TEST: Done");
}
	

	@Test
	@Rollback(true)
	public void testFindAndDeleteUserLocks(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindAndDeleteUserLocks");
		int companyId = 1;
		int item1Id = 1;
		int item2Id = 2;
		int interval1Id = 1;
		int interval2Id = 1;
		User user = new User();
		user.setId(1);
		user.setName("test user");
		
		
		lockDao.deleteLocksForUser(user);
		List<Lock> locks = lockDao.getLocksForUser(user);
		assertNotNull("returned locks are null", locks);
		assertTrue("there should be no locks for this user" ,locks.isEmpty());
		
		Lock lock1 = new Lock();
		Lock lock2 = new Lock();
		
		lock1.setCompanyId(companyId);
		lock1.setItemId(item1Id);
		lock1.setIntervalId(interval1Id);
		lock1.setUser(user);

		lock2.setCompanyId(companyId);
		lock2.setItemId(item2Id);
		lock2.setIntervalId(interval2Id);
		lock2.setUser(user);
		
		lockDao.create(lock1);
		lockDao.create(lock2);
		
		locks = lockDao.getLocksForUser(user);
		assertNotNull("failed to retrieve new locks", locks);
		assertEquals("there should be 2locks", 2, locks.size());
		Lock rtn1 = locks.get(0);
		Lock rtn2 = locks.get(1);
		assertNotNull(rtn1);
		assertNotNull(rtn2);
		
	
		System.out.println("TEST: Done");
}
	

	
	

}
