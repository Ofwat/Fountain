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

import java.util.Collection;
import java.util.List;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.User;

public interface AuditDao extends CachableEntityDao {

	public int create(Audit audit);
	
	public Audit findById(int id);
	
	/**
	 * Returns all audit items initiated by the given user
	 * @param user
	 */
	public List<Audit> findByUser(User user);

	/**
	 * Return a list of audits that apply to a given point of data
	 *
	 * @param itemId
	 * @param intervalId
	 * @param companyId
	 * @param groupEntryId
	 * @return a {@link List} of {@link AuditedValue}s
	 */
	public List<AuditedValue> getAuditedValues(int itemId, int intervalId, int companyId, int groupEntryId);
	public List<AuditedValue> getAuditedValues(int itemId, int intervalId, int companyId, int groupEntryId, int runId, int tagId);
	
	/**
	 * @param user
	 * @param timestamp
	 */
	public List<Audit> findByUserCompanyAndTimestamp(User user, Company company, long timestamp);

	/**
	 * @param id - deletes this audit and all associated data. 
	 * Are you SURE you mean to call this?
	 */
	public void delete(int id);

}
