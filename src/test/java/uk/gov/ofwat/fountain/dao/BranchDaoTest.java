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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.Branch;


import uk.gov.ofwat.fountain.domain.Branch;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class BranchDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private BranchDao branchDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		String Name = "Toby";
		Branch bt = branchDao.create(Name);
		assertNotNull("no branch  created", bt);
		assertEquals(" name incorrect", Name, bt.getName());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testFindById(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindById");
		String Name = "Phumi";
		int id = branchDao.create(Name).getId();

		Branch bt = branchDao.findById(id);
		assertNotNull("couldn't find branch", bt);
		assertEquals("incorrect  name", Name , bt.getName());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testUpdate() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdate");
		String Name = "Zing";
		int id = branchDao.create(Name).getId();
		Branch bt = branchDao.findById(id);
		bt.setEditable(false);
		branchDao.update(bt);

		Branch btRetrievedAfterEdit = branchDao.findById(id);
		assertNotNull("couldn't find branch", btRetrievedAfterEdit);
		assertEquals("Branch  edit has failed", bt, btRetrievedAfterEdit);

		System.out.println("TEST: Done");
}

}
