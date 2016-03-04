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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;

public class UserEdit {

	public enum EditType {
		VALUE, CONFIDENCE_GRADE
	}

	private int id;
	private User user;
	private String value;
	private String original;
	private Date timestamp;
	private int itemId;
	private int intervalId;
	private int companyId;
	private int groupEntryId;
	private EditType editType;
	private int branchId;
	private RunModel runModel;
	private long runId = 0;

	private String key = null;

	public UserEdit() {
	};

	public UserEdit(User user, int itemId, int intervalId, int companyId,
			int groupEntryId, int branchId, String value, String original,
			EditType editType) {
		setUser(user);
		setItemId(itemId);
		setIntervalId(intervalId);
		setCompanyId(companyId);
		setBranchId(branchId);
		setGroupEntryId(groupEntryId);
		setValue(value);
		setOriginal(original);
		setEditType(editType);
	}
	
	public UserEdit(User user, int itemId, int intervalId, int companyId,
			int groupEntryId, int branchId, String value, String original,
			EditType editType, long runId) {
		setUser(user);
		setItemId(itemId);
		setIntervalId(intervalId);
		setCompanyId(companyId);
		setBranchId(branchId);
		setGroupEntryId(groupEntryId);
		setValue(value);
		setOriginal(original);
		setEditType(editType);
		setRunId(runId);
	}	

	public UserEdit(User user, DataDto dataDto, EditType editType) {
		setUser(user);
		setItemId(dataDto.getItem().getId());
		setIntervalId(dataDto.getIntervalDto().getId());
		setCompanyId(dataDto.getCompany().getId());
		setGroupEntryId(dataDto.getGroupEntry().getId());

		if(dataDto.getRunId() != 0){
			this.runId = dataDto.getRunId();
		}
		
		if (0 != dataDto.getBranchId()) { 
			setBranchId(dataDto.getBranchId());
		}
		else {
			Branch branch = dataDto.getModelDto().getBranch();
			if (null != branch) {
				setBranchId(branch.getId());
			}
		}
		
		if (editType == EditType.CONFIDENCE_GRADE) {
			// cg changed
			setValue(dataDto.getUpdatedConfidenceGrade());
			setOriginal(dataDto.getConfidenceGrade());
			setEditType(EditType.CONFIDENCE_GRADE);
		} else {
			// value changed
			if (dataDto.getItem().getUnit().equals("%")) {
				NumberFormat f = NumberFormat.getInstance();
				try {
					Number numberVal = f.parse(dataDto.getUpdatedValue());
					Double dUpVal = numberVal.doubleValue() /100; 
					setValue("" + dUpVal);
				} catch (ParseException pe) {
					// can't parse it so leave it as it was.
					// numeric validation needs to happen elsewhere.
					setValue(dataDto.getUpdatedValue());
				}
				try {
					Number numberVal = f.parse(dataDto.getValue());
					Double dUpVal = numberVal.doubleValue() /100; 
					setOriginal("" + dUpVal);
				} catch (ParseException pe) {
					// can't parse it so leave it as it was.
					// numeric validation needs to happen elsewhere.
					if (dataDto.getValue() == null) {
						setOriginal("");
					} else {
						setOriginal(dataDto.getValue());
					}
				}
			} else {
				setValue(dataDto.getUpdatedValue());
				if (dataDto.getValue() == null) {
					setOriginal("");
				} else {
					setOriginal(dataDto.getValue());
				}
			}
			setEditType(EditType.VALUE);
		}
	}

	
	
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public int getGroupEntryId() {
		return groupEntryId;
	}

	public void setGroupEntryId(int groupEntryId) {
		this.groupEntryId = groupEntryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getIntervalId() {
		return intervalId;
	}

	public void setIntervalId(int intervalId) {
		this.intervalId = intervalId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public EditType getEditType() {
		return editType;
	}

	public void setEditType(EditType editType) {
		this.editType = editType;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getKey() {
		if (null == key) {
			
			DataKey dataKey = new DataKey(getItemId(), getIntervalId(),
				getCompanyId(), getGroupEntryId(), getEditType());
			//If we are also using a run and tag. 
			if(runId != 0){
				dataKey.setRunId(new Long(this.runId).toString());
				dataKey.setTagId("0"); // must be latest, can't edit a tag.
				dataKey.setRunTag(true);
			}
			dataKey.setBranchId("" + branchId);
			key = dataKey.getKey(true, true);
		}
		return key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchId;
		result = prime * result + companyId;
		result = prime * result
				+ ((editType == null) ? 0 : editType.hashCode());
		result = prime * result + groupEntryId;
		result = prime * result + id;
		result = prime * result + intervalId;
		result = prime * result + itemId;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result
				+ ((original == null) ? 0 : original.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEdit other = (UserEdit) obj;
		if (branchId != other.branchId)
			return false;
		if (runId != other.runId)
			return false;			
		if (companyId != other.companyId)
			return false;
		if (editType != other.editType)
			return false;
		if (groupEntryId != other.groupEntryId)
			return false;
		if (id != other.id)
			return false;
		if (intervalId != other.intervalId)
			return false;
		if (itemId != other.itemId)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public long getRunId() {
		return runId;
	}

	public void setRunId(long runId) {
		this.runId = runId;
	}
}
