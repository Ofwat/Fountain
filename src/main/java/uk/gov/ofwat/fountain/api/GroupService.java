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

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Item;

public interface GroupService {
	
	/**
	 * get all of the company group entries for which there is data
	 * for this item
	 * @param item
	 * @param company
	 * @return
	 */
	public List<GroupEntry> getGroupEntriesForItem(Item item, Company company);
	
	/**
	 * straightforward find by id for groupEntry
	 * @param groupEntryId
	 * @return
	 */
	public GroupEntry getGroupEntry(int groupEntryId);

	/**
	 * find by code for group
	 * @param group
	 */
	public Group getGroupByCode(String group);
	
	
	/**
	 * straightforward find by ID
	 * @param id
	 * @return
	 */
	public Group getGroupById(int id);
	
	/**
	 * 
	 * @return all of the possible groups
	 */
	public List<Group> getGroups();
	
	/**
	 * 
	 * @return all of the group entries available for this company and group
	 */
	public List<GroupEntry> getGroupEntriesForCompany(int companyId, int groupId);
	
	/**
	 * set the description for a company's group entry (e.g. the name of a region)
	 * @param groupEntryId
	 * @param description
	 */
	public void setGroupEntryDescription(int groupEntryId, String description);

	/**
	 * @param company the owner of the group entry
	 * @param groupEntryCode group and ordinal e.g. WRZ1
	 * @return
	 */
	public GroupEntry getGroupEntryForCompany(Company company, String groupEntryCode);

}
