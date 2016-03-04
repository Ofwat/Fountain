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
import uk.gov.ofwat.fountain.aspect.dao.CacheTestAuditDao;
import uk.gov.ofwat.fountain.domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GetAllAspectTest {
    @Autowired    
    private CacheTestAuditDao cacheTestDao;

    @Autowired    
    private DaoCacheService daoCacheService;
    
    @BeforeClass
	public static void setup() {
	}

	@Test
	public void testCacheIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheIsReturned");
		// set a list to be returned
		List<Object> all = new ArrayList<Object>();
		Audit audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		Audit audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		List<Object> returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		List<Object> anotherAll = new ArrayList<Object>();
		Audit audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheManagement(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheManagement");
		
		daoCacheService.enableCache();
		// set a list to be returned
		List<Object> all = new ArrayList<Object>();
		Audit audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		Audit audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		List<Object> returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		List<Object> anotherAll = new ArrayList<Object>();
		Audit audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		daoCacheService.disableCache();
		
		// set a list to be returned
		all = new ArrayList<Object>();
		audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		anotherAll = new ArrayList<Object>();
		audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertFalse(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit3));
		
		daoCacheService.enableCache();
		
		// set a list to be returned
		all = new ArrayList<Object>();
		audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		anotherAll = new ArrayList<Object>();
		audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();

		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
	
		System.out.println("TEST: Done");
}


	@Test
	public void clearDaoCacheTest() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":clearDaoCacheTest");
		// set a list to be returned
		List<Object> all = new ArrayList<Object>();
		Audit audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		Audit audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		List<Object> returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		List<Object> anotherAll = new ArrayList<Object>();
		Audit audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		daoCacheService.clearCaches();
		Assert.assertTrue(returnedCache.isEmpty());
		System.out.println("TEST: Done");
	}

	@Test
	public void removeFromCacheOnDelete() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":removeFromCacheOnDelete");
		// set a list to be returned
		List<Object> all = new ArrayList<Object>();
		Audit audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		Audit audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		List<Object> returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		List<Object> anotherAll = new ArrayList<Object>();
		Audit audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// delete an item from the cache
		cacheTestDao.setAudit(audit2);
		cacheTestDao.delete(audit2.getId());

		// confirm that the item has been removed.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertFalse(returnedCache.contains(audit2));
		System.out.println("TEST: Done");
	}

	@Test
	public void removeFromCacheOnUpdate() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":removeFromCacheOnUpdate");
		// set a list to be returned
		List<Object> all = new ArrayList<Object>();
		Audit audit1 = new Audit();
		audit1.setId(1);
		all.add(audit1);
		Audit audit2 = new Audit();
		audit2.setId(2);
		all.add(audit2);
		cacheTestDao.setAll(all);
		
		// Cache all 
		List<Object> returnedCache = cacheTestDao.getAll();
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// set a new list to be returned
		List<Object> anotherAll = new ArrayList<Object>();
		Audit audit3 = new Audit();
		audit3.setId(3);
		anotherAll.add(audit3);
		cacheTestDao.setAll(anotherAll);

		// confirm that all is cached.
		returnedCache = cacheTestDao.getAll();
		Assert.assertTrue(returnedCache.contains(audit1));
		Assert.assertTrue(returnedCache.contains(audit2));
		
		// delete an item from the cache
		cacheTestDao.update(audit2);

		// confirm that the cache has been cleared ...
		returnedCache = cacheTestDao.getAll();
		Assert.assertFalse(returnedCache.contains(audit1));
		Assert.assertFalse(returnedCache.contains(audit2));
		
		// ... and reloaded form the db
		Assert.assertTrue(returnedCache.contains(audit3));
		System.out.println("TEST: Done");
	}
}
