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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.aspect.dao.CacheTestGroupDao;
import uk.gov.ofwat.fountain.domain.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GroupDaoAspectTest {
    @Autowired    
    private CacheTestGroupDao cacheTestDao;

    @Autowired    
    private DaoCacheService daoCacheService;
    
    @BeforeClass
	public static void setup() {
	}

	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(0)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheManagement(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheManagement");
		
		daoCacheService.enableCache();
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(0)).getId());
		
		daoCacheService.disableCache();
		
		// set an group to be returned
		group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());

		// set a different group to be returned.
		group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertSame(2, ((Group)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertNotSame(1, ((Group)cacheTestDao.findById(0)).getId());
		
		daoCacheService.enableCache();
		
		// set an group to be returned
		group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());

		// set a different group to be returned.
		group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(0)).getId());
	
		System.out.println("TEST: Done");
}


	@Test
	public void testTwoCachedItemsAreReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testTwoCachedItemsAreReturned");
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);

		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);

		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(2, ((Group)cacheTestDao.findById(102)).getId());

		// set a different group to be returned.
		Group group3 = new Group();
		group3.setId(3);
		cacheTestDao.setGroup(group3);

		// The aspect returns the cached value for id 101 
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());
		// The aspect returns the cached value for id 102 
		Assert.assertEquals(2, ((Group)cacheTestDao.findById(102)).getId());
		// This id is uncached so the base object returns its recorded group.
		Assert.assertEquals(3, ((Group)cacheTestDao.findById(77)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheSize() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheSize");
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);

		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);

		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(2, ((Group)cacheTestDao.findById(102)).getId());

		// Cache is now full
		// The aspect returns the cached value for id 101 
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(101)).getId());
		// The aspect returns the cached value for id 102 
		Assert.assertEquals(2, ((Group)cacheTestDao.findById(102)).getId());

		// set a different group to be returned.
		Group group3 = new Group();
		group3.setId(3);
		cacheTestDao.setGroup(group3);

		// group3 will be cached. group1 will be removed from the cache to make room for it.
		Assert.assertEquals(3, ((Group)cacheTestDao.findById(103)).getId());
		
		// set a different group to be returned.
		Group group4 = new Group();
		group4.setId(4);
		cacheTestDao.setGroup(group4);

		// since group1 has been removed from the cache, group4 should be returned from the base dao. 
		Assert.assertEquals(4, ((Group)cacheTestDao.findById(101)).getId());

		// now that group4 has been cached, group2 should have been removed from the cache.
		Assert.assertEquals(4, ((Group)cacheTestDao.findById(102)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void clearDaoCacheTest() {
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(0)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(0)).getId());
		
		daoCacheService.clearCaches();
		Assert.assertSame(2, ((Group)cacheTestDao.findById(0)).getId());
	}

	@Test
	public void removeFromCacheOnDelete() {
		
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(1)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(1)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(1)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(1)).getId());

		// delete group1 from the cache
		try {
			cacheTestDao.delete(1);
			Assert.fail("Should have thrown a UnsupportedOperationException.");
		} catch (UnsupportedOperationException e) {
			// UnsupportedOperationException expected
		}
		// The response set on the dao is not returned because the cached response has not been deleted.
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(1)).getId());
		// The cached response is still returned because it has not been deleted.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(1)).getId());
	}

	@Test
	public void removeFromCacheOnUpdate() {
		
		// set an group to be returned
		Group group1 = new Group();
		group1.setId(1);
		cacheTestDao.setGroup(group1);
		
		// calling findById() caches the group. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the group that was set above.  
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(1)).getId());
		Assert.assertEquals(1, ((Group)cacheTestDao.findById(1)).getId());

		// set a different group to be returned.
		Group group2 = new Group();
		group2.setId(2);
		cacheTestDao.setGroup(group2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(1)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(1)).getId());

		// update group1 in the cache. This will deleted it.
		try {
			cacheTestDao.update(group1);
			Assert.fail("Should have thrown a UnsupportedOperationException.");
		} catch (UnsupportedOperationException e) {
			// UnsupportedOperationException expected
		}
		// The response set on the dao is not returned because the cached response has not been deleted.
		Assert.assertNotSame(2, ((Group)cacheTestDao.findById(1)).getId());
		// The cached response is still returned because it has not been deleted.
		Assert.assertSame(1, ((Group)cacheTestDao.findById(1)).getId());
	}
}
