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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ItemPropertyIntervalDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private ItemPropertyIntervalDao itemPropertyIntervalDao;
	
	@Autowired
	private IntervalDao intervalDao;
	
	@Autowired
	private ItemPropertiesDao itemPropertiesDao;
	
	@Autowired
	private ItemDao itemDao;
	

	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		ItemPropertyInterval ipi = new ItemPropertyInterval();
		ipi.setDescription("test description");
		ipi.setFormula("e=mc^2");
		Interval interval = intervalDao.getAll().get(0);
		ipi.setInterval(interval);
		Item item = itemDao.getItemsByPosition(0, 1).get(0);
		ItemProperties itemProps = itemPropertiesDao.getAllForItemId(item.getId()).get(0);
		ipi.setItemProperties(itemProps);
		int id = itemPropertyIntervalDao.create(ipi);
		assertTrue("Failed to create itemPropertyInterval", id > 0);
		ItemPropertyInterval rtn = itemPropertyIntervalDao.findById(id);
		assertNotNull("failed to retrieve ", rtn);
		
		assertEquals("descriptions don't match", ipi.getDescription(), rtn.getDescription());
		assertEquals("formulae don't match", ipi.getFormula(), rtn.getFormula());
		assertEquals("intervals don't match", ipi.getInterval(), rtn.getInterval());
		assertEquals("properties don't match", ipi.getItemProperties(), rtn.getItemProperties());
	
		System.out.println("TEST: Done");
}

}
