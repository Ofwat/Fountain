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

import uk.gov.ofwat.fountain.api.report.RunTag;

/**
 * Holds the data values. This class contains both a <code>value</code> and a <code>rawValue</code> field.
 * For numeric data, the <code>rawValue</code> holds the underlying calculated or numeric value. If it is 
 * null, it means that there is no underlying data - because the value is either text or can't be formatted.
 * 
 */
public class Data implements Entity, Serializable {

	private static final long serialVersionUID = -4359662419366428536L;

	private int id;
	private String value;
	private ConfidenceGrade confidenceGrade;
	private Item item;
	private Interval interval;
	private Company company;
	private GroupEntry groupEntry;
	private Branch branch;
	private boolean locked;
	private Double rawValue;
	
	private User lockingUser;
	private boolean calculatedValue;
	private int valueId;

	private int runId;
	private long tagId;
	
	public int getValueId() {
		return valueId;
	}
	public void setValueId(int valueId) {
		this.valueId = valueId;
	}
	public Double getRawValue() {
		return rawValue;
	}
	public void setRawValue(Double rawValue) {
		this.rawValue = rawValue;
	}

	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public User getLockingUser() {
		return lockingUser;
	}
	public void setLockingUser(User lockingUser) {
		this.lockingUser = lockingUser;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public GroupEntry getGroupEntry() {
		return groupEntry;
	}
	public void setGroupEntry(GroupEntry groupEntry) {
		this.groupEntry = groupEntry;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Interval getInterval() {
		return interval;
	}
	public void setInterval(Interval interval) {
		this.interval = interval;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ConfidenceGrade getConfidenceGrade() {
		return confidenceGrade;
	}
	public void setConfidenceGrade(ConfidenceGrade confidenceGrade) {
		this.confidenceGrade = confidenceGrade;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public int getBranchId() {
		if (null == getBranch()) {
			return 0;
		}
		else {
			return getBranch().getId();
		}
	}
	public boolean isValueCalculated() {
		return calculatedValue;
	}
	public void setCalculatedValue(boolean calculatedValue) {
		this.calculatedValue = calculatedValue;
	}
	public int getRunId() {
		return runId;
	}
	public void setRunId(int runId) {
		this.runId = runId;
	}
	public long getTagId() {
		return tagId;
	}
	public void setTagId(long tagId) {
		this.tagId = tagId;
	}
}
