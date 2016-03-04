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
package uk.gov.ofwat.fountain.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Report;

@XmlRootElement(name = "Report")
@XmlType(propOrder = { "id", "groupId", "cgs", "dataList"})
public class ReportFlat {
	
	private int id;
	private Integer groupId; // if set, report will expand items in this group
	private List<DataDto> dataList;
	private List<ConfidenceGrade> cgs;
	
	public ReportFlat() {
	}
	
	/**
	 * groupId can be null if the report does not expand groups
	 * @param id
	 * @param dataList
	 * @param groupId
	 */
	public ReportFlat(Report report) {
		super();
		
		this.id = report.getId();	
		this.dataList = new ArrayList<DataDto>(report.getDataList().values());
		this.groupId = report.getGroup();
		this.cgs = report.getCgs();
	}

	
	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<DataDto> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataDto> dataList) {
		this.dataList = dataList;
	}
	
	

	public List<ConfidenceGrade> getCgs() {
		return cgs;
	}

	public void setCgs(List<ConfidenceGrade> cgs) {
		this.cgs = cgs;
	}

	@Override
	public String toString() {
		return "ReportFlat [id=" + id + ", groupId=" + groupId + ", dataList="
				+ dataList + ", cgs=" + cgs + "]";
	}
	
}

