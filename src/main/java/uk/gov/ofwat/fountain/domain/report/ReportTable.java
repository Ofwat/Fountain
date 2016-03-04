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

package uk.gov.ofwat.fountain.domain.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class ReportTable {
	
	/**
	 * The company to which the whole lot belongs
	 */
	private Company company;
	
	/**
	 * the item id against a list of Data objects
	 */
	private Map<String, List<DataDto>> rows = new HashMap<String, List<DataDto>>();

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Map<String, List<DataDto>> getRows() {
		return rows;
	}

	public void setRows(Map<String, List<DataDto>> rows) {
		this.rows = rows;
	}
	
	public void addRow(String key, List<DataDto> data){
		this.rows.put(key, data);
	}
	
}
