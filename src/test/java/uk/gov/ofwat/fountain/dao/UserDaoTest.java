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

import static org.junit.Assert.assertTrue;

import uk.gov.ofwat.fountain.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Rollback(true)
	public void testRead() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testRead");
		List<User> users = userDao.getAll();
		assertTrue("No users brought back", users.size()>0);
	
		System.out.println("TEST: Done");
}

}
