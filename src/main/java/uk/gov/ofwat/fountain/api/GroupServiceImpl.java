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

import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.dao.CompanyDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.formula.ProcessingException;

public class GroupServiceImpl implements GroupService{
	
	private GroupDao groupDao;
	private CompanyDao companyDao; 
	
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	public GroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	public List<GroupEntry> getGroupEntriesForItem(Item item, Company company) {
		return groupDao.findEntriesForCompanyAndGroup(company, item.getGroup());
	}
	public GroupEntry getGroupEntry(int groupEntryId) {
		return groupDao.findEntryById(groupEntryId);
	}
	public Group getGroupByCode(String group) {
		return groupDao.findByName(group);
	}
	public Group getGroupById(int id) {
		return groupDao.findById(id);
	}
	public List<GroupEntry> getGroupEntriesForCompany(int companyId, int groupId) {
		Company company = companyDao.findById(companyId);
		Group group = groupDao.findById(groupId);
		return groupDao.findEntriesForCompanyAndGroup(company, group);
	}

	public List<Group> getGroups() {
		
		return groupDao.getAllGroups();
	}
	public void setGroupEntryDescription(int groupEntryId, String description) {
		groupDao.updateGroupEntryDescription(groupEntryId, description);
		
	}
	
	public GroupEntry getGroupEntryForCompany(Company company,
			String groupEntryCode) {
		String groupName = groupEntryCode.replaceAll("[0-9]*", "");
		String ordinalString = groupEntryCode.replaceAll("[a-zA-Z]*", "");
		int ordinal = 0;
		
		try{
			ordinal = Integer.parseInt(ordinalString);
		}
		catch(NumberFormatException nfe){
			throw new ProcessingException("cannot parse group ordinal from " + groupEntryCode);
		}
		return groupDao.findEntryCompanyGroupAndOrdinal(company.getId(), groupName, ordinal);
	}

}
