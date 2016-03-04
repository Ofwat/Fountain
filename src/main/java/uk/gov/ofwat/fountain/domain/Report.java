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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.gov.ofwat.fountain.audit.SkipAudit;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class Report {
	
	private int id;
	private Integer groupId; // if set, report will expand items in this group
	private Map<String, DataDto> dataList;
	private Map<Integer, Set<GroupEntry>> groupEntriesByCompany;
	@SkipAudit private List<ConfidenceGrade> cgs;
	private Map<Integer, List<ListItem>> codeListsByCodeList;
	@SkipAudit private List<Lock> locks;
	private Company company;
    //optional Run for reports with selectable Run/Tag - should this be in a DTO?
    private Run run;
    //optional Tag for reports with selectable Run/Tag - should this be in a DTO?
    private int tagId;
	private boolean showAllHeaders;
	private TagMap tagMap;
	private Map<Integer, Integer> defaultRunIdMap;
	private RunModelTag tag; 
	
	/**
	 * groupId can be null if the report does not expand groups
	 * @param id
	 * @param dataList
	 * @param groupId
	 */
	public Report(int id, Map<String, DataDto> dataList, Integer groupId, Map<Integer, Set<GroupEntry>> groupEntriesByCompany, List<ConfidenceGrade> cgs, Map<Integer, List<ListItem>> codeListsByCodeList, List<Lock> locks, Company company, Run run, RunModelTag tag, TagMap tagMap, Map defaultRunIdMap) {
		super();
		this.id = id;
		this.dataList = dataList;
		this.groupId = groupId;
		this.groupEntriesByCompany = groupEntriesByCompany;
		this.cgs = cgs;
		this.codeListsByCodeList = codeListsByCodeList;
		if (null == locks) {
			this.locks = new ArrayList<Lock>();
		}
		else {
			this.locks = locks;
		}
		this.company = company;
		this.showAllHeaders = false;
        this.run = run;
        if (null == tag) {
        	this.tagId = 0;
        }
        else {
        	if (null == company) {
        		this.tagId = tag.getId();
        	}
        	else {
        		this.tagId = tag.getRunModelCompanyTag(company.getId()).getId();
        	}
        }
        this.tag = tag; 
        this.tagMap = tagMap; 
        this.defaultRunIdMap = defaultRunIdMap;
	}


	
	
	public boolean isShowAllHeaders() {
		return showAllHeaders;
	}



	public void setShowAllHeaders(boolean showAllHeaders) {
		this.showAllHeaders = showAllHeaders;
	}



	public Map<Integer, List<ListItem>> getCodeListsByCodeList() {
		return codeListsByCodeList;
	}


	public void setCodeListsByCodeList(
			Map<Integer, List<ListItem>> codeListsByCodeList) {
		this.codeListsByCodeList = codeListsByCodeList;
	}


	public Integer getGroup() {
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


	public Map<String, DataDto> getDataList() {
		return dataList;
	}

	public void setDataMap(Map<String, DataDto> dataList) {
		this.dataList = dataList;
	}
	
	public Map<Integer, Set<GroupEntry>> getGroupEntriesByCompany() {
		return groupEntriesByCompany;
	}
	
	public void setGroupEntriesByCompany(
			Map<Integer, Set<GroupEntry>> groupEntriesByCompany) {
		this.groupEntriesByCompany = groupEntriesByCompany;
	}

	public List<ConfidenceGrade> getCgs() {
		return cgs;
	}

	public void setCgs(List<ConfidenceGrade> cgs) {
		this.cgs = cgs;
	}
	public List<Lock> getLocks() {
		return locks;
	}


	public void setLocks(List<Lock> locks) {
		this.locks = locks;
	}


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
	public String toString() {
		return "Report [id=" + id + ", groupId=" + groupId + ", dataList="
				+ dataList + ", groupEntriesByCompany=" + groupEntriesByCompany
				+ ", cgs=" + cgs + ", codeListsByCodeList="
				+ codeListsByCodeList + ", locks=" + locks + ", company="
				+ company + "]";
	}

	public void setTagMap(TagMap tagMap) {
		this.tagMap = tagMap;
	}

	public TagMap getTagMap() {
		return tagMap;
	}

	public Map<Integer, Integer> getDefaultRunIdMap() {
		return defaultRunIdMap;
	}

	public void setDefaultRunIdMap(Map<Integer, Integer> defaultRunIdMap) {
		this.defaultRunIdMap = defaultRunIdMap;
	}

	public RunModelTag getTag() {
		return tag;
	}

	public void setTag(RunModelTag tag) {
		this.tag = tag;
	}
	
}

