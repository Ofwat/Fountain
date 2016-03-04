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

import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import junit.framework.Assert;
import junit.framework.TestCase;

public class UserEditTest extends TestCase {

	private User user;
	private String value;
	private int itemId;
	private int intervalId;
	private int companyId;
	private int groupEntry;
	private int branchId;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		user = new User();
		user.setName("barry");
		value = "77";
		itemId = 1;
		intervalId = 2;
		companyId = 3;
		groupEntry = 0; 
		branchId = 0;
	
		System.out.println("TEST: Done");
}
	
	public void testUserEdit() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUserEdit");
		UserEdit userEdit = new UserEdit(user, itemId, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		userEdit.setUser(user);
		userEdit.setValue(value);
		userEdit.setItemId(itemId);
		userEdit.setIntervalId(intervalId);
		userEdit.setCompanyId(companyId);

		Assert.assertEquals("", user, userEdit.getUser());
		Assert.assertEquals("", value, userEdit.getValue());
		Assert.assertEquals("", itemId, userEdit.getItemId());
		Assert.assertEquals("", intervalId, userEdit.getIntervalId());
		Assert.assertEquals("", companyId, userEdit.getCompanyId());
	
		System.out.println("TEST: Done");
}
	
	public void testEquals(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquals");
		User user2 = new User();
		user2.setId(23);
		user2.setName("fred");
		UserEdit userEdit1 = new UserEdit(user, itemId, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditSame = new UserEdit(user, itemId, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffUser = new UserEdit(user2, itemId, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffItem = new UserEdit(user, 99, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffInterval = new UserEdit(user, itemId, 99, companyId, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffCompany = new UserEdit(user, itemId, intervalId, 99, groupEntry, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffGroup = new UserEdit(user, itemId, intervalId, companyId, 99, branchId, "1", "0", EditType.VALUE);
		UserEdit userEditDiffValue = new UserEdit(user, itemId, intervalId, companyId, groupEntry, branchId, "2", "0", EditType.VALUE);
		UserEdit userEditDiffEditType = new UserEdit(user, itemId, intervalId, companyId, groupEntry, branchId, "1", "0", EditType.CONFIDENCE_GRADE);
		
		assertTrue("should be equal", userEdit1.equals(userEditSame));
		assertFalse("different user", userEdit1.equals(userEditDiffUser));
		assertFalse("different item", userEdit1.equals(userEditDiffItem));
		assertFalse("different interval", userEdit1.equals(userEditDiffInterval));
		assertFalse("different company", userEdit1.equals(userEditDiffCompany));
		assertFalse("different group", userEdit1.equals(userEditDiffGroup));
		assertFalse("different value", userEdit1.equals(userEditDiffValue));
		assertFalse("different editType", userEdit1.equals(userEditDiffEditType));
		
	
		System.out.println("TEST: Done");
}

}
