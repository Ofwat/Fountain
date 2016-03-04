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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.AuditService;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.rest.dto.BasketDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class AuditResourceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/************** STATICS **********************/
	private static AuditResource auditResource = null;
	private static AuditService mockAuditService = mock(AuditService.class);	
	
	private static SecurityContext mockSecurityContext = mock(SecurityContext.class);
	private static User mockUser = mock(User.class);
	private static Principal mockPrincipal = mock(Principal.class);

	static{
		auditResource = new AuditResource();
		auditResource.setAuditService(mockAuditService);
	}
	
	@Before
	public void onSetUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":onSetUp");
	
		System.out.println("TEST: Done");
}

	@Test
	public void testGetAuditedValues(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetAuditedValues");

		List<AuditedValue> auditValues = new ArrayList<AuditedValue>();
		AuditedValue av1 = new AuditedValue();
		av1.setId(1);
		auditValues.add(av1);
		AuditedValue av2 = new AuditedValue();
		av2.setId(2);
		auditValues.add(av2);
		AuditedValue av3 = new AuditedValue();
		av3.setId(3);
		auditValues.add(av3);

		when(mockAuditService.getAuditedValues(1, 2, 3, 4)).thenReturn(auditValues);
		
		String key = "i1-p99-y2-c3-g4";
		Response response = auditResource.getAuditedValues(key);
		
		assertNotNull("no response", response);
		assertEquals("expected 200 status (HTTP SUCCESS)", 200, response.getStatus());
		
		List<AuditedValue> responseValues = (List<AuditedValue>)response.getEntity();
		assertNotNull("no responseValues", responseValues);
		assertEquals("audit incorrect", 1, responseValues.get(0).getId());
		assertEquals("audit incorrect", 2, responseValues.get(1).getId());
		assertEquals("audit incorrect", 3, responseValues.get(2).getId());
	
		System.out.println("TEST: Done");
}
	
}
