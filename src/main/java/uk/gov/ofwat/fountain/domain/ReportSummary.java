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

import java.io.Serializable;
import java.util.Date;

public class ReportSummary implements Addressable, Entity, Serializable  {

	private int id;
	private String name;
	private Date lastModified;
	private int teamId;
	private boolean publicReport;
	private String description;
	private boolean interchangeableCompany;
	private boolean interchangeableRun;
	private boolean readOnly;
		
	public boolean isInterchangeableCompany() {
		return interchangeableCompany;
	}
	public void setInterchangeableCompany(boolean interchangeableCompany) {
		this.interchangeableCompany = interchangeableCompany;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public boolean isPublicReport() {
		return publicReport;
	}
	public void setPublicReport(boolean publicReport) {
		this.publicReport = publicReport;
	}
	public boolean isInterchangeableRun() {
		return interchangeableRun;
	}
	public void setInterchangeableRun(boolean interchangeableRun) {
		this.interchangeableRun = interchangeableRun;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
