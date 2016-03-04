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
package uk.gov.ofwat.fountain.domain;

import junit.framework.Assert;
import junit.framework.TestCase;

public class LockTest extends TestCase {

	private int id;
	private User user;
	private int itemId;
	private int yearId;
	private int companyId;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		id = 7;
		user = new User();
		user.setName("barry");
		itemId = 1;
		yearId = 2;
		companyId = 3;
	
		System.out.println("TEST: Done");
}
	
	public void testLock() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testLock");
		Lock lock = new Lock();
		lock.setId(id);
		lock.setUser(user);
		lock.setItemId(itemId);
		lock.setIntervalId(yearId);
		lock.setCompanyId(companyId);

		Assert.assertEquals("", id, lock.getId());
		Assert.assertEquals("", user, lock.getUser());
		Assert.assertEquals("", itemId, lock.getItemId());
		Assert.assertEquals("", yearId, lock.getIntervalId());
		Assert.assertEquals("", companyId, lock.getCompanyId());
	
		System.out.println("TEST: Done");
}

}
