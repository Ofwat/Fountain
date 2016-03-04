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

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.User;

public interface AuditService {
	
	/**
	 * Creates a new Audit object (persisted in the database) with the timestamp set to now. 
	 * @param comment
	 * @param user
	 * @return
	 */
	Audit createAudit(String comment, User user, Company company);
	
	List<AuditedValue> getAuditedValues(Integer itemId, Integer intervalId, Integer companyId, Integer groupEntryId);
	List<AuditedValue> getAuditedValues(Integer itemId, Integer intervalId, Integer companyId, Integer groupEntryId, int runId, int tagId);
}
