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
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.BranchNotEditableException;
import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.api.LockService;
import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.Basket;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class CheckoutResourceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/************** STATICS **********************/
	private static CheckoutResource checkoutResource = null;
	private static BasketService mockBasket = mock(BasketService.class);	
	private static DataService mockDataService = mock(DataService.class);
	private static LockService mockLockService = mock(LockService.class);
	private static UserService mockUserService = mock(UserService.class);
	private static SecurityContext mockSecurityContext = mock(SecurityContext.class);
	private static User mockUser = mock(User.class);
	private static Principal mockPrincipal = mock(Principal.class);

	static{
		checkoutResource = new CheckoutResource();
		checkoutResource.setDataService(mockDataService);
		checkoutResource.setLockService(mockLockService);
		checkoutResource.setBasketService(mockBasket);
		checkoutResource.setUserService(mockUserService);
		
	}

	@Before
	public void onSetUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":onSetUp");
		when(mockPrincipal.getName()).thenReturn("quetzalcoatl");
		when(mockSecurityContext.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockUserService.getUser(mockSecurityContext)).thenReturn(mockUser);
		when(mockUser.getId()).thenReturn(17);
		when(mockUser.getName()).thenReturn("stephanie");
	
		System.out.println("TEST: Done");
}

	@Test
	@Rollback(true)
	public void testPostBasketToCheckout_auditCommentIsNull() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testPostBasketToCheckout_auditCommentIsNull");

		try {
			checkoutResource.postBasketToCheckout(null, mockSecurityContext);
			fail("expected a WebApplicationException");
		} catch (WebApplicationException e) {
			Response response = e.getResponse();
			assertNotNull("no response", response);
			assertEquals("expected 400 status (HTTP BAD REQUEST)", 400, response.getStatus());
		}
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testPostBasketToCheckout() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testPostBasketToCheckout");
		Basket mockCache = mock(Basket.class);

		when(mockBasket.getBasketForUser(mockUser)).thenReturn(mockCache);
		
		Response response = checkoutResource.postBasketToCheckout("test audit", mockSecurityContext);
		
		verify(mockLockService).clearLocksForUser(mockUser.getId());
		verify(mockBasket).clearBasket(mockUser);

		try {
			verify(mockDataService).saveUserChanges(mockCache);
		} catch (BranchNotEditableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		assertNotNull("no response", response);
		assertEquals("expected 204 status (HTTP NO CONTENT)", 204, response.getStatus());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testPostBasketToCheckout_failsWhereThereIsNoServerBasket() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testPostBasketToCheckout_failsWhereThereIsNoServerBasket");

		when(mockBasket.getBasketForUser(mockUser)).thenReturn(null);
		
		Response response;
		try {
			response = checkoutResource.postBasketToCheckout("test audit", mockSecurityContext);
			fail("Should have got 409 CONFLICT.");
		} catch (WebApplicationException e) {
			assertEquals("expected 409 status (HTTP CONFLICT)", 409, e.getResponse().getStatus());
		}
	
		System.out.println("TEST: Done");
}

}
