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
package uk.gov.ofwat.fountain.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class DataTableTest extends TestCase {

	private Map<String, Map<String, DataDto>>rows;
	private Map<String, DataDto> itemLine;
	private DataDto dataDto1;
	private Set<String> rowHeadings;
	private Set<String> colHeadings;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		itemLine = new TreeMap<String, DataDto>();
		dataDto1 = new DataDto();
		itemLine.put("dataDTO1", dataDto1);
		rows = new HashMap<String, Map<String, DataDto>>();
		rows.put("name of pot 1", itemLine);

		rowHeadings = new HashSet<String>();
		rowHeadings.add("r1");
		rowHeadings.add("r2");
		
		colHeadings = new HashSet<String>();
		colHeadings.add("c1");
		colHeadings.add("c2");
		
	
		System.out.println("TEST: Done");
}
	
	public void testDataTable() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDataTable");
		DataTable dataTable = new DataTable();
		dataTable.setColHeadings(colHeadings);
		
		Assert.assertEquals("", colHeadings, dataTable.getColHeadings());
	
		System.out.println("TEST: Done");
}

}
