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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "report")
@XmlType(propOrder = { "name", "description", "lastModified", "publicReport", "myReport", "items", "intervals", "companies", "layoutLeft", "layoutTop", 
		"displayCGs", "displayUnit", "displayBoncode", "displayDesc", "displayModel", "displayCompanyName", "displayCompanyAcronym", "group"
        ,"displayRunName","displayRunDescription","displayTagName", "readOnly", "admin", "reportCreator"})
public class EditReportDto extends CustomReportDto {
	private boolean myReport;
	private boolean admin;
	private boolean reportCreator;
	private String reportCreatorName;
		
	public String getReportCreatorName() {
		return reportCreatorName;
	}

	public void setReportCreatorName(String reportCreatorName) {
		this.reportCreatorName = reportCreatorName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isReportCreator() {
		return reportCreator;
	}

	public void setReportCreator(boolean reportCreator) {
		this.reportCreator = reportCreator;
	}

	public boolean isMyReport() {
		return myReport;
	}
	
	public void setMyReport(boolean myReport) {
		this.myReport = myReport;
	}

	@Override
	public String toString() {
		return "EditReportDto [myReport=" + myReport + ", isAdmin=" + admin + " , reportCreator="  + reportCreator + "]";
	}
	
	
}
