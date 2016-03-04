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

public enum CellType {
	EMPTY (1), INPUT (2), CALC(3), COPYCELL(4), COL_HEADING(5), ROW_HEADING(6), GROUP_ROW_HEADING(7), ROW_HEADING_NON_REPEAT(8);
	
	private final Integer value;

	CellType(Integer value) {
		this.value = value;
	}

	public static CellType fromValue(Integer value) {
		if (value != null) {
			for (CellType cellType : values()) {
				if (cellType.value.equals(value)) {
					return cellType;
				}
			}
		}
		// or throw an exception
		throw new IllegalArgumentException("Invalid CellType: " + value);
	}

	public Integer toValue() {
		return value;
	}

}
