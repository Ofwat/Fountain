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

package uk.gov.ofwat.fountain.domain.form;

import java.util.StringTokenizer;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;

/**
 * key structure for jsp pages.
 */
public class DataKey {
	
	private String itemId;
	private String itemPropertiesId;
	private String intervalId;
	private String companyId;
	private String groupEntryId;
	private String modelId;
	private String branchId;
    private String runId;
    private String tagId;
	boolean cg;
    boolean runTag = false;
	
	
	
	public DataKey() {
	}
 
	public DataKey(Item item, Interval interval, Company company, GroupEntry groupEntry, EditType editType, Run run, RunModelCompanyTag tag) {
		this(item, interval, company, groupEntry, editType);
		this.setRunId(run.getId());
		this.setTagId(tag.getId());
		this.setRunTag(true);
	}
	
	public DataKey(Item item, Interval interval, Company company, GroupEntry groupEntry, EditType editType) {
		setItemId(item.getId());
		setIntervalId(interval.getId());
		setCompanyId(company.getId());
		setGroupEntryId(groupEntry.getId());
		if (editType == EditType.CONFIDENCE_GRADE) {
			setCg(true);
		}
	}
 
	public DataKey(int itemId, int intervalId, int companyId, int groupEntryId, EditType editType) {
		setItemId(itemId);
		setIntervalId(intervalId);
		setCompanyId(companyId);
		setGroupEntryId(groupEntryId);
		if (editType == EditType.CONFIDENCE_GRADE) {
			setCg(true);
		}
	}

	public DataKey (String key) { 
		if(key.startsWith("CG")){
			cg = true; 
			key = key.substring(2); // get rid of the initial 'CG' so the switch statement works
		}
		StringTokenizer st = new StringTokenizer(key, "-");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			switch(token.charAt(0)) {
				case 'i': itemId = token.substring(1); 
				break;
				case 'p': itemPropertiesId = token.substring(1); 
				break;
				case 'y': intervalId = token.substring(1);
				break;
				case 'c': companyId = token.substring(1);
				break;
				case 'g': groupEntryId = token.substring(1);
				break;
				case 'm': modelId = token.substring(1);
				break;
				case 'b': branchId = token.substring(1);
                break;
                case 'r': runId = token.substring(1);
				break;
                case 't': tagId = token.substring(1);
                break;
			}
		}
	}
	
	public String getItemId() {
		return itemId;
	}
	public Integer getItemIdInteger() {
		return Integer.parseInt(getItemId());
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setItemId(int itemId) {
		setItemId("" + itemId);
	}
	public String getItemPropertiesId() {
		return itemPropertiesId;
	}
	public Integer getItemPropertiesIdInteger() {
		return Integer.parseInt(getItemPropertiesId());
	}
	public void setItemPropertiesId(String itemPropertiesId) {
		this.itemPropertiesId = itemPropertiesId;
	}
	public void setItemPropertiesId(int itemPropertiesId) {
		setItemPropertiesId("" + itemPropertiesId);
	}
	public String getIntervalId() {
		return intervalId;
	}
	public Integer getIntervalIdInteger() {
		return Integer.parseInt(getIntervalId());
	}
	public void setIntervalId(String intervalId) {
		this.intervalId = intervalId;
	}
	public void setIntervalId(int intervalId) {
		setIntervalId("" + intervalId);
	}
	public String getCompanyId() {
		return companyId;
	}
	public Integer getCompanyIdInteger() {
		return Integer.parseInt(getCompanyId());
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public void setCompanyId(int companyId) {
		setCompanyId("" + companyId);
	}
	public String getGroupEntryId() {
		return groupEntryId;
	}
	public Integer getGroupEntryIdInteger() {
		return Integer.parseInt(getGroupEntryId());
	}
	public void setGroupEntryId(String groupEntryId) {
		this.groupEntryId = groupEntryId;
	}
	public void setGroupEntryId(int groupEntryId) {
		setGroupEntryId("" + groupEntryId);
	}
	public String getModelId() {
		return modelId;
	}
	public Integer getModelIdInteger() {
		return Integer.parseInt(getModelId());
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	public void setModelId(int modelId) {
		setModelId("" + modelId);
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		setBranchId("" + branchId);
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public boolean isCg() {
		return cg;
	}
	public void setCg(boolean cg) {
		this.cg = cg;
	}
    public String getRunId() { 
    	return runId; 
	}
    public void setRunId(String runId) { 
    	this.runId = runId; 
    }
    public void setRunId(int runId) { 
    	setRunId("" + runId); 
    }
    public String getTagId() { 
    	return tagId; 
	}
    public void setTagId(String tagId) { 
    	this.tagId = tagId; 
	}
    public void setTagId(int tagId) { 
    	setTagId("" + tagId); 
	}
    public boolean isRunTag() { 
    	return runTag; 
	}
    public void setRunTag(boolean runTag) { 
    	this.runTag = runTag; 
	}

    public String getKey(boolean includeGroupEntry) {
		return getKey(includeGroupEntry, false);
	}
		
	public String getKey(boolean includeGroupEntry, boolean includeBranch){
		StringBuilder builder = new StringBuilder();
		if (cg) {
			builder.append("CG");
		}
		builder.append("i");
		builder.append(itemId);
		builder.append("-p");
		builder.append(itemPropertiesId);
		builder.append("-y");
		builder.append(intervalId);
		builder.append("-c");
		builder.append(companyId);
		if(includeGroupEntry){
			builder.append("-g");
			builder.append(groupEntryId);
		}
		builder.append("-m");
		builder.append(modelId);
		if(includeBranch){
			builder.append("-b");
			builder.append(branchId);
		}

        if(runTag){
            builder.append("-r");
            builder.append(runId);
            builder.append("-t");
            builder.append(tagId);
        }

		return builder.toString();
	}

	public int getRunIdInteger() {
		return Integer.parseInt(getRunId());
	}

	public int getTagIdInteger() {
		return Integer.parseInt(getTagId());
	}

}
