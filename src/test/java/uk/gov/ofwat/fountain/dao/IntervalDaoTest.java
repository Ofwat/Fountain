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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class IntervalDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	IntervalDao intervalDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		
		IntervalType type = intervalDao.getAll().get(0).getIntervalType();
		assertNotNull("Unable to retrieve an interval type", type);
		Interval interval = new Interval();
		interval.setName("test interval");
		interval.setIntervalType(type);
		interval.setIntervalIndex(7);
		
		int id = intervalDao.create(interval);
		assertTrue("Failed to create an interval in the database", 0 < id);
		
		Interval ret = intervalDao.findById(id);
		assertNotNull("failed to retrieve newly created interval", ret);
		
		assertEquals("incorrect interval name", "test interval", ret.getName());
		assertEquals("incorrect interval type", type, ret.getIntervalType());
	
		System.out.println("TEST: Done");
}

}
