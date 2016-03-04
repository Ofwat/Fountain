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

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.aspect.dao.CacheTestItemPropertiesDao;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
public class ItemPropertiesDaoAspectTest {
   
    @Autowired    
    private CacheTestItemPropertiesDao cacheTestDao;
	
	@Autowired    
    private DaoCacheService daoCacheService;
   
	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set up a ItemProperties to be cached
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		Item item1 = new Item();
		item1.setName("item 1 name");
		item1.setCode("0");
		item1.setGroup(group);
		item1.setId(1);
		ItemProperties ip = new ItemProperties(item1, 1, "description 1", "definition ZZ101", 1,  null, false, null, "", 1, false);

		ip.setId(1);
			
		cacheTestDao.setItemProperties(ip);
		Assert.assertEquals(1, ((ItemProperties)cacheTestDao.findByItemAndModel(1, 2)).getId());
		Assert.assertEquals(1, ((ItemProperties)cacheTestDao.findByItemAndModel(1, 2)).getId());
		
		// create another ItemProperties
		ItemProperties ip2 = new ItemProperties(item1, 1, "description 1", "definition ZZ101", 1,  null, false, null, "", 1, false);
		ip2.setId(2);
		
		
		cacheTestDao.setItemProperties(ip2);
		// ... but it is not returned
		Assert.assertNotSame(2, ((ItemProperties)cacheTestDao.findByItemAndModel(1, 2)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((ItemProperties)cacheTestDao.findByItemAndModel(1, 2)).getId());
	
		System.out.println("TEST: Done");
}
	
}
