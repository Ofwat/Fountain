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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class CompanyDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CompanyDao companyDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		Company company = new Company();
		CompanyType companyType = new CompanyType();
		companyType.setCode("WOC");
		companyType.setDescription("Water Only Company");
		companyType.setId(2);
		company.setName("test company");
		company.setCode("CPY");
		company.setCompanyType(companyType);
		int id = companyDao.create(company);
		
		Company rtn = companyDao.findById(id);
		assertNotNull("failed to find new company", rtn);
		assertEquals("wrong name", "test company", rtn.getName());
		assertEquals("wrong code", "CPY", rtn.getCode());
		assertEquals("wrong type", 2, rtn.getCompanyType().getId());
		assertEquals(false, rtn.isExpired());
	
		System.out.println("TEST: Done");
}

}
