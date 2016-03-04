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

package uk.gov.ofwat.fountain.aspect.dao;

import java.util.List;

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;

public class CacheTestGroupDaoImpl implements CacheTestGroupDao {

	private Group group;

	public void setGroup(Group group) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setGroup");
		this.group = group;
	
		System.out.println("TEST: Done");
}
	
	public Group findById(int id) {
		return this.group;
	}

	public void delete(int id) {
		System.out.println("CacheTestDaoImpl.delete()");
	}
	
	public void update(Group group){
		System.out.println("CacheTestDaoImpl.update()");
	}

	public int create(Group group, int TableId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Group> findByTableId(int tableId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemDao getItemDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntervalDao getYearDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		// TODO Auto-generated method stub
		
	}

	public void setItemDao(ItemDao itemDao) {
		// TODO Auto-generated method stub
		
	}

	public int create(Group group) {
		// TODO Auto-generated method stub
		return 0;
	}

	public GroupEntry findEntryCompanyGroupAndOrdinal(int companyId,
			String groupName, int ordinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public int createGroupEntry(GroupEntry groupEntry) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Group findByName(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GroupEntry> findEntriesForCompanyAndGroup(Company company,
			Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	public GroupEntry findEntryById(int groupEntryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public GroupEntry findEntryCompanyAndName(Company company,
			String groupEntryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public GroupEntry findUngroupedEntry(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	public GroupEntry findEntryCompanyAndName(int companyId,
			String groupEntryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Group> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateGroupEntryDescription(int groupEntryId, String description) {
		// TODO Auto-generated method stub
		
	}

	public void addItemUpdateListener(UpdateListener listener) {
		// TODO Auto-generated method stub
		
	}
	
	
}
