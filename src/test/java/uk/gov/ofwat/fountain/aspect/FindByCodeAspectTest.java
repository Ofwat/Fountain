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
import uk.gov.ofwat.fountain.aspect.dao.CacheTestCompanyDao;
import uk.gov.ofwat.fountain.domain.Company;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class FindByCodeAspectTest {
    @Autowired    
    private CacheTestCompanyDao cacheTestDao;

    @Autowired
	private DaoCacheService daoCacheService;
    
    @BeforeClass
	public static void setup() {
	}

	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);
		// ... but it is not returned  
		Assert.assertNotSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
		// ... because the response has been cached by the aspect.
		Assert.assertSame("1", ((Company)cacheTestDao.findByCode("0")).getCode());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheManagement(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheManagement");
		
		daoCacheService.enableCache();
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);
		// ... but it is not returned  
		Assert.assertNotSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
		// ... because the response has been cached by the aspect.
		Assert.assertSame("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		
		daoCacheService.disableCache();
		
		// set an company to be returned
		company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());

		// set a different company to be returned.
		company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);
		// ... but it is not returned  
		Assert.assertSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
		// ... because the response has been cached by the aspect.
		Assert.assertNotSame("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		
		daoCacheService.enableCache();
		
		// set an company to be returned
		company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());

		// set a different company to be returned.
		company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);
		// ... but it is not returned  
		Assert.assertNotSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
		// ... because the response has been cached by the aspect.
		Assert.assertSame("1", ((Company)cacheTestDao.findByCode("0")).getCode());
	
		System.out.println("TEST: Done");
}

	@Test
	public void testTwoCachedItemsAreReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testTwoCachedItemsAreReturned");
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);

		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);

		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("2", ((Company)cacheTestDao.findByCode("102")).getCode());

		// set a different company to be returned.
		Company company3 = new Company();
		company3.setCode("3");
		cacheTestDao.setCompany(company3);

		// The aspect returns the cached value for id "101" 
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());
		// The aspect returns the cached value for id "102" 
		Assert.assertEquals("2", ((Company)cacheTestDao.findByCode("102")).getCode());
		// This id is uncached so the base object returns its recorded company.
		Assert.assertEquals("3", ((Company)cacheTestDao.findByCode("77")).getCode());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void testCacheSize() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCacheSize");
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);

		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);

		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("2", ((Company)cacheTestDao.findByCode("102")).getCode());

		// Cache is now full
		// The aspect returns the cached value for id "101" 
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("101")).getCode());
		// The aspect returns the cached value for id "102" 
		Assert.assertEquals("2", ((Company)cacheTestDao.findByCode("102")).getCode());

		// set a different company to be returned.
		Company company3 = new Company();
		company3.setCode("3");
		cacheTestDao.setCompany(company3);

		// company3 will be cached. company1 will be removed from the cache to make room for it.
		Assert.assertEquals("3", ((Company)cacheTestDao.findByCode("103")).getCode());
		
		// set a different company to be returned.
		Company company4 = new Company();
		company4.setCode("4");
		cacheTestDao.setCompany(company4);

		// since company1 has been removed from the cache, company4 should be returned from the base dao. 
		Assert.assertEquals("4", ((Company)cacheTestDao.findByCode("101")).getCode());

		// now that company4 has been cached, company2 should have been removed from the cache.
		Assert.assertEquals("4", ((Company)cacheTestDao.findByCode("102")).getCode());
	
		System.out.println("TEST: Done");
}
	
	@Test
	public void clearDaoCacheTest() {
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("0")).getCode());

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);
		// ... but it is not returned  
		Assert.assertNotSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
		// ... because the response has been cached by the aspect.
		Assert.assertSame("1", ((Company)cacheTestDao.findByCode("0")).getCode());
		
		daoCacheService.clearCaches();
		Assert.assertSame("2", ((Company)cacheTestDao.findByCode("0")).getCode());
	}

	@Test
	public void removeFromCacheOnDelete() {
		
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("1")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("1")).getCode());

		// delete company1 from the cache
		cacheTestDao.delete(1);

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);

		// The response set on the dao should now be returned.  
		Assert.assertEquals("2", ((Company)cacheTestDao.findByCode("1")).getCode());
		// ... because the cached response has been deleted.
		Assert.assertNotSame("1", ((Company)cacheTestDao.findByCode("1")).getCode());
	}

	@Test
	public void removeFromCacheOnUpdate() {
		
		// set an company to be returned
		Company company1 = new Company();
		company1.setCode("1");
		cacheTestDao.setCompany(company1);
		
		// calling findByCode() caches the company. The first call dose not use the aspect!!!
		// Note this implementation of findByCode() ignores the parameter and just returns the company that was set above.  
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("1")).getCode());
		Assert.assertEquals("1", ((Company)cacheTestDao.findByCode("1")).getCode());

		// update company1 in the cache. This will deleted it.
		cacheTestDao.update(company1);

		// set a different company to be returned.
		Company company2 = new Company();
		company2.setCode("2");
		cacheTestDao.setCompany(company2);

		// The response set on the dao should now be returned.  
		Assert.assertSame("2", ((Company)cacheTestDao.findByCode("1")).getCode());
		// ... because the cached response has been deleted.
		Assert.assertNotSame("1", ((Company)cacheTestDao.findByCode("1")).getCode());		
	}
}
