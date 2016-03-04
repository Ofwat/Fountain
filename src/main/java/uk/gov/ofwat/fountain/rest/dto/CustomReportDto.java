/*
 *  Copyright (C) 2010 Water Services Regulation Authority (Ofwat)
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

import java.util.List;

import uk.gov.ofwat.fountain.domain.Team;

public class CustomReportDto {
	private List<ModelItemDto>items;
	private List<Integer>intervals;
	private List<Integer>companies;
	private List<String>layoutLeft;
	private List<String>layoutTop;
	private String name;
	private String description;
	private boolean publicReport;
	private boolean displayCGs;
	private boolean displayUnit;
	private boolean displayBoncode;
	private boolean displayDesc;
	private boolean displayModel;
	private boolean displayCompanyName;
	private boolean displayCompanyAcronym;
    private boolean displayRunName;
    private boolean displayRunDescription;
    private boolean displayTagName;
	private boolean displayAgendaName;
	private boolean displayAgendaCode;
	private String group;
	private int teamId;
	private boolean readOnly;
    private List<RunTagIdsDto> runTagIds;

	public boolean isDisplayCGs() {
		return displayCGs;
	}
	public void setDisplayCGs(boolean displayCGs) {
		this.displayCGs = displayCGs;
	}
	public boolean isDisplayUnit() {
		return displayUnit;
	}
	public void setDisplayUnit(boolean displayUnit) {
		this.displayUnit = displayUnit;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public boolean isDisplayBoncode() {
		return displayBoncode;
	}
	public void setDisplayBoncode(boolean displayBoncode) {
		this.displayBoncode = displayBoncode;
	}
	public boolean isDisplayDesc() {
		return displayDesc;
	}
	public void setDisplayDesc(boolean displayDesc) {
		this.displayDesc = displayDesc;
	}
	public boolean isDisplayModel() {
		return displayModel;
	}
	public void setDisplayModel(boolean displayModel) {
		this.displayModel = displayModel;
	}
	public boolean isDisplayCompanyName() {
		return displayCompanyName;
	}
	public void setDisplayCompanyName(boolean displayCompanyName) {
		this.displayCompanyName = displayCompanyName;
	}
	public boolean isDisplayCompanyAcronym() {
		return displayCompanyAcronym;
	}
	public void setDisplayCompanyAcronym(boolean displayCompanyAcronym) {
		this.displayCompanyAcronym = displayCompanyAcronym;
	}
	public boolean isPublicReport() {
		return publicReport;
	}
	public void setPublicReport(boolean publicReport) {
		this.publicReport = publicReport;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public List<ModelItemDto> getItems() {
		return items;
	}
	public void setItems(List<ModelItemDto> items) {
		this.items = items;
	}
	public List<Integer> getIntervals() {
		return intervals;
	}
	public void setIntervals(List<Integer> intervals) {
		this.intervals = intervals;
	}
	public List<Integer> getCompanies() {
		return companies;
	}
	public void setCompanies(List<Integer> companies) {
		this.companies = companies;
	}
	public List<String> getLayoutLeft() {
		return layoutLeft;
	}
	public void setLayoutLeft(List<String> layoutLeft) { this.layoutLeft = layoutLeft; }
	public List<String> getLayoutTop() {
		return layoutTop;
	}
	public void setLayoutTop(List<String> layoutTop) {
		this.layoutTop = layoutTop;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public List<RunTagIdsDto> getRunTagIds() {
		return runTagIds;
	}
	public void setRunTagIds(List<RunTagIdsDto> runTagIds) {
		this.runTagIds = runTagIds;
	}
    public boolean isDisplayRunName() {
        return displayRunName;
    }
    public void setDisplayRunName(boolean displayRunName) {
        this.displayRunName = displayRunName;
    }
    public boolean isDisplayRunDescription() {
        return displayRunDescription;
    }
    public void setDisplayRunDescription(boolean displayRunDescription) { this.displayRunDescription = displayRunDescription; }
    public boolean isDisplayTagName() { return displayTagName; }
    public void setDisplayTagName(boolean displayTagName) { this.displayTagName = displayTagName; }
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public boolean isDisplayAgendaName() {
		return displayAgendaName;
	}
	public void setDisplayAgendaName(boolean displayAgendaName) {
		this.displayAgendaName = displayAgendaName;
	}
	public boolean isDisplayAgendaCode() {
		return displayAgendaCode;
	}
	public void setDisplayAgendaCode(boolean displayAgendaCode) {
		this.displayAgendaCode = displayAgendaCode;
	}

}
