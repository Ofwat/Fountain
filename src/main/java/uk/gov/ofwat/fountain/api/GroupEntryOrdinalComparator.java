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

import java.util.Comparator;

import uk.gov.ofwat.fountain.domain.GroupEntry;

public class GroupEntryOrdinalComparator implements Comparator<GroupEntry> {
	// sorts groups by ordinal (0, should be at the end really...)
	public int compare(GroupEntry g1, GroupEntry g2) {
		if (g1.getOrdinal() - g2.getOrdinal()!=0){
			return g1.getOrdinal() - g2.getOrdinal();	
		} else {
			return g1.getId() - g2.getId();
		}
	}
}
