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

import uk.gov.ofwat.fountain.rest.Link;

@XmlRootElement(name = "reportDetails")
@XmlType(propOrder = { "id" , "name", "link", "description", "lastModified", "publicReport", "team", "redesign", "interchangeableCompany", "interchangeableRun"})
public class ReportDetailsDto {
	
	private Link link;
	private int id;
	private String description;
	private String name;
	private String lastModified;
	private boolean publicReport;
	private String team;
	private boolean redesign;
	private boolean interchangeableCompany;		
	private boolean interchangeableRun;
	
	
	public boolean isInterchangeableRun() {
		return interchangeableRun;
	}

	public void setInterchangeableRun(boolean interchangeableRun) {
		this.interchangeableRun = interchangeableRun;
	}

	public boolean isRedesign() {
		return redesign;
	}

	public void setRedesign(boolean redesign) {
		this.redesign = redesign;
	}

	public boolean isPublicReport() {
		return publicReport;
	}

	public void setPublicReport(boolean publicReport) {
		this.publicReport = publicReport;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInterchangeableCompany() {
		return interchangeableCompany;
	}

	public void setInterchangeableCompany(boolean interchangeableCompany) {
		this.interchangeableCompany = interchangeableCompany;
	}

	@Override
	public String toString() {
		return "ReportDetailsDto [link=" + link + ", id=" + id
				+ ", description=" + description + ", name=" + name
				+ ", lastModified=" + lastModified + ", publicReport="
				+ publicReport + ", team=" + team + ", redesign=" + redesign
				+ ", interchangeableCompany=" + interchangeableCompany
				+ ", interchangeableRun=" + interchangeableRun + "]";
	}

	
	
}
