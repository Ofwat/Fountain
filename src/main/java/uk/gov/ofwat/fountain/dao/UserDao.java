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

import java.util.List;

import uk.gov.ofwat.fountain.domain.User;


/**
 * used to map the username received from the realm database to the application user.
 * 
 * The application user table stores userid and is used to keep track of edits. The
 * users in the application table can continue to exist even if they no longer exist in the realm, so 
 * we can still see what changes someone made even if they are no longer a user of the system. 
 * 
 * Not cachable
 */
public interface UserDao {
	
	/**
	 * match the security realm name with the local user. User is created locally if the don't exist
	 * @param name
	 * @return {@link User}
	 */
	User findUserByName(String name);
	
	User create(String name);

	/**
	 * Return all users
	 * @return {@link List} of {@link User}s
	 */
	List<User> getAll();

	/**
	 * @param userId
	 * @return {@link User}
	 */
	User findById(int userId); 

}
