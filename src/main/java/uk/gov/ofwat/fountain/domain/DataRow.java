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

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.rest.dto.DataDto;

@XmlRootElement(name = "dataRow")
@XmlType(propOrder = {"groupedDataRows", "dataDtos", "totalDtos"})
@Deprecated
public class DataRow implements RowOfData{	

	private Map<String, GroupedDataRow> groupedDataRows = new TreeMap<String, GroupedDataRow>();
	private Map<String, DataDto> dataDtos = new TreeMap<String, DataDto>();
	private Map<String, DataDto> totalDtos = new TreeMap<String, DataDto>();
	
	public Map<String, GroupedDataRow> getGroupedDataRows() {
		return groupedDataRows;
	}
	public void setGroupedDataRows(Map<String, GroupedDataRow> groupedDataRow) {
		this.groupedDataRows = groupedDataRow;
	}
	public Map<String, DataDto> getDataDtos() {
		return dataDtos;
	}
	public void setDataDtos(Map<String, DataDto> dataDtos) {
		this.dataDtos = dataDtos;
	}
	public Map<String, DataDto> getTotalDtos() {
		return totalDtos;
	}
	public void setTotalDtos(Map<String, DataDto> totalDtos) {
		this.totalDtos = totalDtos;
	}
	public void addDataDto(String key, DataDto dataDto) {
		dataDtos.put(key, dataDto);
	}
	public void addGroupedDataRow(String key, GroupedDataRow groupedDataRow) {
		groupedDataRows.put(key, groupedDataRow);
	}
}
