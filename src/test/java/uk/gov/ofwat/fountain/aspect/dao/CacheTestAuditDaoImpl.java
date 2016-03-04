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

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.User;

public class CacheTestAuditDaoImpl implements CacheTestAuditDao {

	private Audit audit;
	private List<Object> all;

	public void setAudit(Audit audit) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setAudit");
		this.audit = audit;
	
		System.out.println("TEST: Done");
}
	
	public Audit findById(int id) {
		return this.audit;
	}

	public void delete(int id) {
		System.out.println("CacheTestDaoImpl.delete()");
	}
	
	public void update(Audit audit){
		System.out.println("CacheTestDaoImpl.update()");
	}

	public void setAll(List<Object> all) {
		this.all = all;
	}

	public List<Object> getAll() {
		return all;
	}

	public int create(Audit audit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Audit> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Audit> findByUserCompanyAndTimestamp(User user, Company company, long timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AuditedValue> getAuditedValues(int itemId, int intervalId,
			int companyId, int groupEntryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuditedValue> getAuditedValues(int itemId, int intervalId,
			int companyId, int groupEntryId, int runId, int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
