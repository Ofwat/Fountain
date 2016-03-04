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
package uk.gov.ofwat.fountain.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.UserDao;
import uk.gov.ofwat.fountain.domain.User;


public class UserServiceTest extends TestCase {
	
	private UserServiceImpl userService;
	private SecurityContext mockSecurityContext;
	private Principal mockPrincipal;
	private UserDao userDao;
	private String userName = "username";

	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		userService = new UserServiceImpl();
		mockSecurityContext = mock(SecurityContext.class);
		mockPrincipal = mock(Principal.class);
		userDao = mock(UserDao.class);
		userService.setUserDao(userDao);
	
		System.out.println("TEST: Done");
}

	public void testGetUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetUser");
		when(mockSecurityContext.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(userName);
		when(mockSecurityContext.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(userName);
		User expected = new User();
		expected.setId(100);
		expected.setName(userName);
		when(userDao.findUserByName(userName)).thenReturn(expected);
		User user = userService.getUser(mockSecurityContext);
		Assert.assertEquals(userName, user.getName());
		
		when(mockSecurityContext.getUserPrincipal()).thenReturn(mockPrincipal);
		when(mockPrincipal.getName()).thenReturn(userName);

		user = userService.getUser(mockSecurityContext);
		Assert.assertEquals(userName, user.getName());
	
		System.out.println("TEST: Done");
}
}

