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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import uk.gov.ofwat.fountain.audit.SkipAudit;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.ListItem;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.TagMap;
import uk.gov.ofwat.fountain.domain.chunk.ChunkSet;
import uk.gov.ofwat.fountain.domain.chunk.Chunkable;
import uk.gov.ofwat.fountain.domain.chunk.ReportChunkSet;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.tag.Tag;

public class ReportDto implements Chunkable, Entity{
	
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
    private long tagId;
	private boolean showAllHeaders;
	private TagMap tagMap;
	private Map<Integer, Integer> defaultRunIdMap;
	private RunModelTagDto tag;
	private Boolean chunked = false;
	private ChunkSet chunkSet = null;

	public ReportDto(Report report) {
		super();
		this.id = report.getId();
		this.dataList = report.getDataList();
		this.groupId = report.getGroup();
		this.groupEntriesByCompany = report.getGroupEntriesByCompany();
		this.cgs = report.getCgs();
		this.codeListsByCodeList = report.getCodeListsByCodeList();
		this.locks = report.getLocks();
		this.company = report.getCompany();
		this.showAllHeaders = false;
		this.run = report.getRun();
		this.tagId = report.getTagId();
		this.tagMap = report.getTagMap();
		this .defaultRunIdMap = report.getDefaultRunIdMap();
		this.tag = new RunModelTagDto(report.getTag());
	}

	/**
	 * groupId can be null if the report does not expand groups
	 * @param id
	 * @param dataList
	 * @param groupId
	 */
//	public ReportDto(int id, Map<String, DataDto> dataList, Integer groupId, Map<Integer, Set<GroupEntry>> groupEntriesByCompany, List<ConfidenceGrade> cgs, Map<Integer, List<ListItem>> codeListsByCodeList, List<Lock> locks, Company company) {
//		super();
//		this.id = id;
//		this.dataList = dataList;
//		this.groupId = groupId;
//		this.groupEntriesByCompany = groupEntriesByCompany;
//		this.cgs = cgs;
//		this.codeListsByCodeList = codeListsByCodeList;
//		this.locks = new ArrayList<Lock>();
//		this.company = company;
//		this.showAllHeaders = false;
//	}


	
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

    public void setTagId(long tagId) {
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

	public RunModelTagDto getTag() {
		return tag;
	}

	public void setTag(RunModelTagDto tag) {
		this.tag = tag;
	}

	public ChunkSet getChunkSet() {
		return chunkSet;
	}

	public void setChunkSet(ChunkSet chunkSet) {
		this.chunkSet = chunkSet;
	}

	public Boolean isChunked() {
		return chunked;
	}

	public void setChunked(Boolean chunked) {
		this.chunked = chunked;
	}

	@Override
	@JsonIgnore
	public Map<String, Object> getChunkableData() {
		//We could do this if performance is an issue:
		//filterMapObj = Collections.<String, Object>unmodifiableMap(filterMap);
		//But this will create an unmodifiable map and wont let us modify the two maps independently
		//See: http://stackoverflow.com/questions/21037263/converting-mapstring-string-to-mapstring-object
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.putAll(dataList);
		return dataMap;
	}

	@Override
	@JsonIgnore
	public Class getChunkableClass() {
		return ReportChunkSet.class;
	}

}

