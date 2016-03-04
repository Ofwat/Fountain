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

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class UserEditDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private UserEditDao userEditDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		
		int companyId = 1;
		int groupEntryId = 1;
		int intervalId = 2;
		int itemId = 3;
		int branchId = 0;
		User user = new User();
		user.setId(4);
		user.setName("unit test user");
		
		UserEdit edit = new UserEdit(user, itemId, intervalId, companyId, groupEntryId, branchId, "12345", "11111", EditType.VALUE);
		edit.setTimestamp(new Date());
		
		int id = userEditDao.create(edit);
		assertTrue("returned id is less than 1", 0 < id);
		UserEdit rtn = userEditDao.findByUserEditId(id);
		assertNotNull("could not retrieve newly created ", rtn);
		
		assertEquals("incorrect company id", edit.getCompanyId(), rtn.getCompanyId());
		assertEquals("incorrect interval id", edit.getIntervalId(), rtn.getIntervalId());
		assertEquals("incorrect item id", edit.getItemId(), rtn.getItemId());
		assertEquals("incorrect value", edit.getValue(), rtn.getValue());
		assertEquals("incorrect user", edit.getUser(), rtn.getUser());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testFindByIntervalAndCompany(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindByIntervalAndCompany");
		int groupEntryId = 1;
		int branchId = 0; 

		int company1Id = 1;
		int company2Id = 2;
		
		int item1Id = 4;
		
		int interval1Id = 7;
		int interval2Id = 8;
		
		User user1 = new User();
		user1.setId(1);
		user1.setName("junit user 1");
		User user2 = new User();
		user2.setId(2);
		user2.setName("junit user 2");
		

		UserEdit edit1 = new UserEdit(user1, item1Id, interval1Id, company1Id, groupEntryId, branchId, "hello", "previous", EditType.VALUE);
		edit1.setTimestamp(new Date());
		edit1.setId(userEditDao.create(edit1));
		
		UserEdit edit2 = new UserEdit(user2, item1Id, interval1Id, company1Id, groupEntryId, branchId, "hello", "previous", EditType.VALUE);
		edit2.setTimestamp(new Date());
		edit2.setId(userEditDao.create(edit2));
		
		UserEdit edit3 = new UserEdit(user1, item1Id, interval1Id, company2Id, groupEntryId, branchId, "hello", "previous", EditType.VALUE);
		edit3.setTimestamp(new Date());
		edit3.setId(userEditDao.create(edit3));
		
		UserEdit edit4 = new UserEdit(user1, item1Id, interval2Id, company1Id, groupEntryId, branchId, "hello", "previous", EditType.VALUE);
		edit4.setTimestamp(new Date());
		edit4.setId(userEditDao.create(edit4));
		
		UserEdit edit5 = new UserEdit(user2, item1Id, interval2Id, company1Id, groupEntryId, branchId, "hello", "previous", EditType.VALUE);
		edit5.setTimestamp(new Date());
		edit5.setId(userEditDao.create(edit5));
		
		List<UserEdit> rtn = userEditDao.findByItemIntervalCompany(item1Id, interval1Id, company1Id);
		assertNotNull("no user edits returned", rtn);
		assertEquals("incorrect number of results", 2, rtn.size());
	
		System.out.println("TEST: Done");
}

}
