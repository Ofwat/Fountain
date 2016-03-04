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
package uk.gov.ofwat.fountain.rest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.util.Locations;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class CompanyResourceTest extends AbstractTransactionalJUnit4SpringContextTests {

	
	private static ApplicationContext context = new ClassPathXmlApplicationContext(Locations.CONFIG_LOCATION);
	private static CompanyResource companyResource = new CompanyResource();
	private static SecurityContext mockSecurityContext = mock(SecurityContext.class);
	static{
		companyResource.setReferenceService((ReferenceService)context.getBean("referenceService"));
	}
	/**
	 * Test method for {@link uk.gov.ofwat.fountain.rest.CompanyResource#getCompanies()}.
	 */
	@Test
	public void testGetCompanies() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetCompanies");
		Response response = companyResource.getCompanies();
		GenericEntity<List<Company>> entity = (GenericEntity<List<Company>>)response.getEntity();		
		List<Company> companies = (List<Company>)entity.getEntity();
		assertNotNull("no companies were returned", companies);
	
		System.out.println("TEST: Done");
}

	/**
	 * Test method for {@link uk.gov.ofwat.fountain.rest.CompanyResource#getCompanyTypes()}.
	 */
	@Test
	public void testGetCompanyTypes() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetCompanyTypes");
		Response response = companyResource.getCompanyTypes();
		GenericEntity<List<CompanyType>> entity = (GenericEntity<List<CompanyType>>)response.getEntity();		
		List<CompanyType> cts = (List<CompanyType>)entity.getEntity();
		assertNotNull("no company types were returned", cts);
	
		System.out.println("TEST: Done");
}

	/**
	 * Test method for {@link uk.gov.ofwat.fountain.rest.CompanyResource#getAllCompanies()}.
	 */
	@Test
	public void testGetAllCompanies() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetAllCompanies");
		Response response = companyResource.getAllCompanies();
		GenericEntity<List<Company>> entity = (GenericEntity<List<Company>>)response.getEntity();		
		List<Company> companies = (List<Company>)entity.getEntity();
		assertNotNull("no companies were returned", companies);
	
		System.out.println("TEST: Done");
}

}
