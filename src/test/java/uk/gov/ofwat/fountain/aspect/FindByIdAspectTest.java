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
import uk.gov.ofwat.fountain.aspect.dao.CacheTestAuditDao;
import uk.gov.ofwat.fountain.domain.Audit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FindByIdAspectTest {
    @Autowired    
    private CacheTestAuditDao cacheTestDao;

    @Autowired    
    private DaoCacheService daoCacheService;
    
    @BeforeClass
	public static void setup() {
	}

	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(0)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheManagement(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheManagement");
		
		daoCacheService.enableCache();
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(0)).getId());
		
		daoCacheService.disableCache();
		
		// set an audit to be returned
		audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());

		// set a different audit to be returned.
		audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertSame(2, ((Audit)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertNotSame(1, ((Audit)cacheTestDao.findById(0)).getId());
		
		daoCacheService.enableCache();
		
		// set an audit to be returned
		audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());

		// set a different audit to be returned.
		audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(0)).getId());
	
		System.out.println("TEST: Done");
}


	@Test
	public void testTwoCachedItemsAreReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testTwoCachedItemsAreReturned");
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);

		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);

		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(2, ((Audit)cacheTestDao.findById(102)).getId());

		// set a different audit to be returned.
		Audit audit3 = new Audit();
		audit3.setId(3);
		cacheTestDao.setAudit(audit3);

		// The aspect returns the cached value for id 101 
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());
		// The aspect returns the cached value for id 102 
		Assert.assertEquals(2, ((Audit)cacheTestDao.findById(102)).getId());
		// This id is uncached so the base object returns its recorded audit.
		Assert.assertEquals(3, ((Audit)cacheTestDao.findById(77)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheSize() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheSize");
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);

		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);

		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(2, ((Audit)cacheTestDao.findById(102)).getId());

		// Cache is now full
		// The aspect returns the cached value for id 101 
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(101)).getId());
		// The aspect returns the cached value for id 102 
		Assert.assertEquals(2, ((Audit)cacheTestDao.findById(102)).getId());

		// set a different audit to be returned.
		Audit audit3 = new Audit();
		audit3.setId(3);
		cacheTestDao.setAudit(audit3);

		// audit3 will be cached. audit1 will be removed from the cache to make room for it.
		Assert.assertEquals(3, ((Audit)cacheTestDao.findById(103)).getId());
		
		// set a different audit to be returned.
		Audit audit4 = new Audit();
		audit4.setId(4);
		cacheTestDao.setAudit(audit4);

		// since audit1 has been removed from the cache, audit4 should be returned from the base dao. 
		Assert.assertEquals(4, ((Audit)cacheTestDao.findById(101)).getId());

		// now that audit4 has been cached, audit2 should have been removed from the cache.
		Assert.assertEquals(4, ((Audit)cacheTestDao.findById(102)).getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void clearDaoCacheTest() {
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(0)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(0)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(0)).getId());
		
		daoCacheService.clearCaches();
		Assert.assertSame(2, ((Audit)cacheTestDao.findById(0)).getId());
	}

	@Test
	public void removeFromCacheOnDelete() {
		
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(1)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(1)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(1)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(1)).getId());

		// delete audit1 from the cache
		cacheTestDao.delete(1);
		// The response set on the dao should now be returned.  
		Assert.assertSame(2, ((Audit)cacheTestDao.findById(1)).getId());
		// ... because the cached response has been deleted.
		Assert.assertNotSame(1, ((Audit)cacheTestDao.findById(1)).getId());
	}

	@Test
	public void removeFromCacheOnUpdate() {
		
		// set an audit to be returned
		Audit audit1 = new Audit();
		audit1.setId(1);
		cacheTestDao.setAudit(audit1);
		
		// calling findById() caches the audit. The first call dose not use the aspect!!!
		// Note this implementation of findById() ignores the parameter and just returns the audit that was set above.  
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(1)).getId());
		Assert.assertEquals(1, ((Audit)cacheTestDao.findById(1)).getId());

		// set a different audit to be returned.
		Audit audit2 = new Audit();
		audit2.setId(2);
		cacheTestDao.setAudit(audit2);
		// ... but it is not returned  
		Assert.assertNotSame(2, ((Audit)cacheTestDao.findById(1)).getId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(1, ((Audit)cacheTestDao.findById(1)).getId());

		// update audit1 in the cache. This will deleted it.
		cacheTestDao.update(audit1);
		// The response set on the dao should now be returned.  
		Assert.assertSame(2, ((Audit)cacheTestDao.findById(1)).getId());
		// ... because the cached response has been deleted.
		Assert.assertNotSame(1, ((Audit)cacheTestDao.findById(1)).getId());		
	}
}
