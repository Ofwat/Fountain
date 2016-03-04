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
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;

public interface UserEditDao {
	
	int create(UserEdit userEdit);
	
	UserEdit findByUserEditId(int id);
	
	List<UserEdit> findByItemIntervalCompany(int itemId,
											  int intervalId,
											  int companyId);
	
	List<UserEdit>findByUser(User user);
	
	void saveEdits(List<UserEdit>edits, User user);
	
	void update(UserEdit userEdit);

	void refreshEditsForUser(User user);
	
	void removeUserEdits(User user);

	List<UserEdit> findByBranchId(int branchId);

}
