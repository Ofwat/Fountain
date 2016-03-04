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

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.rest.dto.DataDto;

@XmlRootElement(name = "data")
@XmlType(propOrder = { "name", "colHeadings", "rows", "writeCGs", "CGs",
		"codeListsByCodeList", "locks", "dataList", "groupEntries", "company" })
public class DataTable implements GenericTable {

	private int id;
	private String name;
	// TODO Remove the rows (and therefore the class at a later point)
	@Deprecated
	private Map<String, DataRow> rows;
	private Set<String> colHeadings;
	boolean writeCGs = false;
	private List<ConfidenceGrade> cgs;
	private Map<Integer, List<ListItem>> codeListsByCodeList;
	private List<Lock> locks;
	private Map<String, DataDto> dataList; // ideally this should be a map..
	private Set<GroupEntry> groupEntries; // don't need a map of sets as it's
											// all for 1 company currently
	private Company company;
	private Map<Integer, Integer> defaultRunIdMap;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Lock> getLocks() {
		return locks;
	}

	public void setLocks(List<Lock> locks) {
		this.locks = locks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Deprecated
	public Map<String, DataRow> getRows() {
		return rows;
	}

	@Deprecated
	public void setRows(Map<String, DataRow> rows) {
		this.rows = rows;
	}

	public Set<String> getColHeadings() {
		return colHeadings;
	}

	public void setColHeadings(Set<String> colHeadings) {
		this.colHeadings = colHeadings;
	}

	public boolean isWriteCGs() {
		return writeCGs;
	}

	public void setWriteCGs(boolean writeCGs) {
		this.writeCGs = writeCGs;
	}

	public List<ConfidenceGrade> getCgs() {
		return cgs;
	}

	public void setCgs(List<ConfidenceGrade> cgs) {
		this.cgs = cgs;
	}

	public Map<Integer, List<ListItem>> getCodeListsByCodeList() {
		return codeListsByCodeList;
	}

	public void setCodeListsByCodeList(
			Map<Integer, List<ListItem>> codeListsByCodeList) {
		this.codeListsByCodeList = codeListsByCodeList;
	}

	public Map<String, DataDto> getDataList() {
		return dataList;
	}

	public void setDataList(Map<String, DataDto> dataList) {
		this.dataList = dataList;
	}

	public Set<GroupEntry> getGroupEntries() {
		return groupEntries;
	}

	public void setGroupEntries(Set<GroupEntry> groupEntries) {
		this.groupEntries = groupEntries;
	}

	@Override
	public String toString() {
		return "DataTable [id=" + id + ", name=" + name + ", rows=" + rows
				+ ", colHeadings=" + colHeadings + ", writeCGs=" + writeCGs
				+ ", cgs=" + cgs + ", codeListsByCodeList="
				+ codeListsByCodeList + ", locks=" + locks + ", dataList="
				+ dataList + ", groupEntries=" + groupEntries + ", company="
				+ company + "]";
	}

	public Map<Integer, Integer> getDefaultRunIdMap() {
		return defaultRunIdMap;
	}

	public void setDefaultRunIdMap(Map<Integer, Integer> defaultRunIdMap) {
		this.defaultRunIdMap = defaultRunIdMap;
	}

}
