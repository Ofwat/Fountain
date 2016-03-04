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

import java.util.HashMap;

import javax.ws.rs.core.SecurityContext;

import uk.gov.ofwat.fountain.dao.UserDao;
import uk.gov.ofwat.fountain.domain.User;

/**
 * TEST IMPLEMENTATION OF USERSERVICE - hard coded to use Themba
 * Work around for TJWS not working nicely with authenticating a user
 */
public class UserServiceTestImpl implements UserService{
	private HashMap<String, User> users = new HashMap<String, User>();

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUserDao");
		this.userDao = userDao;
	
		System.out.println("TEST: Done");
}
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.UserService#getUser(javax.ws.rs.core.SecurityContext)
	 */
	public User getUser(final SecurityContext context) {
		System.out.println("USING TEST IMPLEMENTATION OF USERSERVICE - THEMBA HARD CODED");
		User user = users.get("themba");
		if (null == user) {
			user = userDao.findUserByName("themba");
			users.put(user.getName(), user);
		}
		return user;
	}

	public User getNamedUser(String username) {
		return userDao.findUserByName(username);
	}
	
	public User createUser(String name){
		return userDao.create(name);
	}

	public User getUser(int userId) {
		return userDao.findById(userId);
	}
	
	

}
