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

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateBroadcaster;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;

public interface GroupDao extends CachableEntityDao, UpdateBroadcaster {

	public Group findById(int groupId);
	
	public GroupEntry findEntryById(int groupEntryId);

	public GroupEntry findEntryCompanyAndName(int companyId, String groupEntryName);
	
	public GroupEntry findUngroupedEntry(Company company);
	
	public List<GroupEntry> findEntriesForCompanyAndGroup(Company company, Group group);

	public Group findByName(String group);
	
	public int create(Group group);
	
	public int createGroupEntry(GroupEntry groupEntry);
	
	public List <Group> getAllGroups();

	/**
	 * @param groupEntryId
	 * @param description - the user entered description for this group area (e.g. name of a region or town)
	 */
	public void updateGroupEntryDescription(int groupEntryId, String description);

	/**
	 * @param companyId
	 * @param groupName e.g. WRZ
	 * @param ordinal e.g. 1 for the first group entry, (0 for NO_GROUP group entry).
	 * @return the group entry for this company, group at the specified ordinal.
	 */
	public GroupEntry findEntryCompanyGroupAndOrdinal(int companyId, String groupName, int ordinal);
	
}
