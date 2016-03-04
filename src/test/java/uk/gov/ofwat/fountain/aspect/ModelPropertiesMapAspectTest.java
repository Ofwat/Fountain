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
import uk.gov.ofwat.fountain.aspect.dao.CacheTestModelPropertiesMapDao;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
public class ModelPropertiesMapAspectTest {
   
    @Autowired    
    private CacheTestModelPropertiesMapDao cacheTestDao;
	
	@Autowired    
    private DaoCacheService daoCacheService;
   
	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set up a modelItemproperties to be cached
		ModelPropertiesMap mpm = new ModelPropertiesMap();
		mpm.setId(1);
		mpm.setItemCode("BON101");
		mpm.setItemId(2);
		mpm.setItemPropertiesId(3);
		mpm.setModelId(4);
		
		cacheTestDao.setModelPropertiesMap(mpm);
		Assert.assertEquals(1, ((ModelPropertiesMap)cacheTestDao.findByModelAndItem(4, 2)).getId());
		Assert.assertEquals(1, ((ModelPropertiesMap)cacheTestDao.findByModelAndItem(4, 2)).getId());
		
		// create another modelpropertiesmap
		ModelPropertiesMap mpm2 = new ModelPropertiesMap();
		mpm2.setId(2);
		mpm2.setItemCode("BON102");
		mpm2.setItemId(3);
		mpm2.setItemPropertiesId(4);
		mpm2.setModelId(4);
		
		cacheTestDao.setModelPropertiesMap(mpm2);
		// ... but it is not returned
		Assert.assertNotSame(2, ((ModelPropertiesMap)cacheTestDao.findByModelAndItem(4, 2)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((ModelPropertiesMap)cacheTestDao.findByModelAndItem(4, 2)).getId());
	
		System.out.println("TEST: Done");
}
	
}
