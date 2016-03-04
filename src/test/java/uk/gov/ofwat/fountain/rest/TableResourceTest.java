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
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.util.Locations;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class TableResourceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	
	/************** STATICS **********************/
	private static ApplicationContext context = new ClassPathXmlApplicationContext(Locations.CONFIG_LOCATION);
	private static TableResource tableResource = (TableResource)context.getBean("tableResource");

	
	/************** INSTANCE **********************/
	@Autowired
	private ReferenceService referenceService;
	
	@Autowired
	private ModelService modelService;
	

	
	@Test
	@Rollback(true)
	public void testGet(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGet");
		
		int companyId = referenceService.getCompanies().get(0).getId();
		int modelTypeId  = modelService.getModelTypes().get(0).getId();
		int modelId = modelService.getModels(modelTypeId).get(1).getId();
		int tableId = modelService.getTablesForModel(modelId).get(0).getId();
		
		SecurityContext securityContext = mock(SecurityContext.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("themba");
		when(securityContext.getUserPrincipal()).thenReturn(principal);
		Response response = tableResource.getTableForCompany(tableId, companyId, securityContext, httpServletRequest);
		DataTable table = (DataTable)response.getEntity();
		assertNotNull("Failed to get a table back", table);
		assertNotNull("List of table data not returned", table.getDataList());
		assertNotNull("", table.getColHeadings());
				
	
		System.out.println("TEST: Done");
}
	
}
