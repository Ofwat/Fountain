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
package uk.gov.ofwat.fountain.api.report;

import java.util.Set;
import java.util.TreeSet;

import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;

public class ReportCompanyGroups {
	
	private boolean calcsOnly = true;
	private Set<GroupEntry> groupEntriesForCalcOnlyData = new TreeSet<GroupEntry>();
	private Set<GroupEntry> groupEntriesForValueAndCalculatedData = new TreeSet<GroupEntry>();
	
	public ReportCompanyGroups(int companyId) {
	}

	public void addEntry(Data data, GroupEntry entry) {
		if (data.isValueCalculated()) {
			groupEntriesForCalcOnlyData.add(entry);
		}
		else {
			calcsOnly = false;
			groupEntriesForValueAndCalculatedData.add(entry);
		}
	}

	public Set<GroupEntry> retrieveEntries() {
		if (calcsOnly) {
			return groupEntriesForCalcOnlyData;
		}
		else {
			return groupEntriesForValueAndCalculatedData;
		}
	}
	
}
