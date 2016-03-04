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

import javax.ws.rs.core.SecurityContext;

import uk.gov.ofwat.fountain.domain.User;

public interface UserService {

	/**
	 * Get the current logged in user (Use this for all security decisions)
	 * @param context
	 * @return
	 */
	User getUser(SecurityContext context);
	
	/**
	 * Get a user from the database (as opposed to the realm).
	 * 
	 * Use this for retrieving audits for other users etc.
	 * @param username
	 * @return
	 */
	User getNamedUser(String username);
	
	/**
	 * Get a user from the database (as opposed to the realm).
	 * 
	 * Use this for retrieving audits for other users etc.
	 * @param userId
	 * @return
	 */
	User getUser(int userId);
	
	User createUser(String name);

}
