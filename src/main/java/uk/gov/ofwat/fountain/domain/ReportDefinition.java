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

import uk.gov.ofwat.fountain.api.report.RunTag;

import java.util.List;

public class ReportDefinition implements Entity {
	
	private int id;
	private String name;
	private String ownerUser;
	private List<Integer>intervalIds;
	private List<ModelItem>modelItems;
	private List<Integer>companyIds;
	private List<String> layoutLeft;
	private List<String> layoutTop;
	private List<RunTag>runTags;
    private Group group;
	private int teamId;
	private boolean publicReport;
	private ReportDisplay reportDisplay;
	private boolean deleted;
	private boolean iplRun;
	private boolean readOnly;
	private String description;
	private String[] tags;
		
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isInterchangeableRun() {
		if (getRunTags().isEmpty()) {
			return true;
		}
		else if (1 == getRunTags().size() &&
				 0 == getRunTags().get(0).getRun().getId()) {
			return true;
		}
		return false;
	}
	public boolean isInterchangeableCompany() {
		if (getCompanyIds().isEmpty()) {
			return true;
		}
		else if (1 == getCompanyIds().size() &&
				 0 == getCompanyIds().get(0)) {
			return true;
		}
		return false;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwnerUser() {
		return ownerUser;
	}
	public void setOwnerUser(String ownerUser) {
		this.ownerUser = ownerUser;
	}
	public List<Integer> getIntervalIds() {
		return intervalIds;
	}
	public void setIntervalIds(List<Integer> intervalIds) {
		this.intervalIds = intervalIds;
	}
	public List<ModelItem> getModelItems() {
		return modelItems;
	}
	public void setModelItems(List<ModelItem> modelItems) {
		this.modelItems = modelItems;
	}
	public List<String> getLayoutLeft() {
		return layoutLeft;
	}
	public void setLayoutLeft(List<String> layoutLeft) {
		this.layoutLeft = layoutLeft;
	}
	public List<String> getLayoutTop() {
		return layoutTop;
	}
	public void setLayoutTop(List<String> layoutTop) {
		this.layoutTop = layoutTop;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public List<Integer> getCompanyIds() {
		return companyIds;
	}
	public void setCompanyIds(List<Integer> companyIds) {
		this.companyIds = companyIds;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public ReportDisplay getReportDisplay() {
		return reportDisplay;
	}
	public void setReportDisplay(ReportDisplay reportDisplay) {
		this.reportDisplay = reportDisplay;
	}
	public boolean isPublicReport() {
		return publicReport;
	}
	public void setPublicReport(boolean publicReport) {
		this.publicReport = publicReport;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isIplRun() {
		return iplRun;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	public void setIplRun(boolean iplRun) {
		this.iplRun = iplRun;
	}
    public List<RunTag> getRunTags() { return runTags; }
    public void setRunTags(List<RunTag> runTags) { this.runTags = runTags; }
	public String[] getTags() {
		return tags;
	}
	public void setTags(String[] tags) {
		this.tags = tags;
	}

}
