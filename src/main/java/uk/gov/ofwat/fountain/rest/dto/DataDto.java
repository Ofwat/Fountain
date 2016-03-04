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

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;

@XmlRootElement(name = "data")
@XmlType(propOrder = { "identifier", 
					   "item", 
					   "itemPropertiesDto", 
					   "intervalDto",
					   "modelDto",
					   "value", 
					   "updatedValue",
					   "rawValue",
					   "confidenceGrade", 
					   "updatedConfidenceGrade", 
					   "company", 
					   "groupEntry", 
					   "editable", 
					   "calculated", 
					   "formula", 
					   "locked", 
					   "lockingUser",
					   "branchId",
					   "runId",
					   "tagId"})

public class DataDto {

	private String identifier;
	private String value;
	private Double rawValue;
	private String confidenceGrade;
	private String updatedValue = null;
	private String updatedConfidenceGrade = null;
	private boolean locked;
	private String lockingUser;
	private Item item;
	private IntervalDto intervalDto;
	private Company company;
	private GroupEntry groupEntry;
	private ModelDto modelDto;
	private boolean editable;
	private boolean calculated = false;
	private String formula;
	private ItemPropertiesDto itemPropertiesDto;
	private int branchId;
	private int runId;
	private Long tagId;
	
	
	
	public DataDto() {
	}
	
	
	
	/**
	 * use this constructor where you don't need the model id in the identifier string.
	 * @param company
	 * @param interval
	 * @param editable
	 * @param itemProperties
	 * @param groupEntry
	 * @param value
	 * @param cg
	 */
	public DataDto(Company company, Interval interval, boolean editable, ItemProperties itemProperties, GroupEntry groupEntry, String value, ConfidenceGrade cg, Double rawValue, ModelDto modelDto) {
		this(company, interval, editable, itemProperties, groupEntry, value, cg, rawValue, modelDto, 0);
	}
	
	public DataDto(Company company, Interval interval, boolean editable, ItemProperties itemProperties, GroupEntry groupEntry, String value, ConfidenceGrade cg, Double rawValue, ModelDto modelDto, int branchId) {
		this(company, interval, editable, itemProperties, groupEntry, value, cg, rawValue, modelDto, 0, 0, 0);
	}
	
	public DataDto(Company company, Interval interval, boolean editable, ItemProperties itemProperties, GroupEntry groupEntry, String value, ConfidenceGrade cg, Double rawValue, ModelDto modelDto, int branchId, int runId, long tagId) {
		
		identifier = "i" + itemProperties.getItem().getId() + "-p" +  itemProperties.getId() + "-y" + interval.getId();
		if (0 != company.getId()) {
			identifier += "-c" + company.getId();
		}
		if (null != groupEntry){
			identifier += "-g" + groupEntry.getId();
		}
		identifier += "-m" + modelDto.getId();

        if (0 != runId){
    		identifier += "-r" + runId;
       		identifier += "-t" + tagId;
       		this.runId = runId;
       		this.tagId = tagId;
        }

		setModelDto(modelDto);
		setValue(value);
		setItem(itemProperties.getItem());
		setIntervalDto(new IntervalDto(interval));
		setCompany(company);
		setEditable(editable);
		setGroupEntry(groupEntry);
		setItemPropertiesDto(new ItemPropertiesDto(itemProperties));
		
		setCalculated(!itemProperties.isRawDataValue(interval));
		if(calculated){
			setFormula(itemProperties.getFormula(interval));
		}
		else {
			setFormula("");
		}
		
		if (cg!=null) {
			this.confidenceGrade = cg.getCode();
		}
		if (this.confidenceGrade==null) {
			this.confidenceGrade = "";
		}
		
		setRawValue(rawValue);
		setBranchId(branchId);
	}
	
	public void setRawValue(Double rawValue) {
		this.rawValue = rawValue;
	}
	public Double getRawValue() {
		return rawValue;
	}
	
	public void setCalculated(boolean calculated) {
		this.calculated = calculated;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	public ItemPropertiesDto getItemPropertiesDto() {
		return itemPropertiesDto;
	}

	public void setItemPropertiesDto(ItemPropertiesDto itemPropertiesDto) {
		this.itemPropertiesDto = itemPropertiesDto;
		calculated = false; // hte default position
	}

	
	public GroupEntry getGroupEntry() {
		return groupEntry;
	}

	public void setGroupEntry(GroupEntry groupEntry) {
		this.groupEntry = groupEntry;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setIntervalDto(IntervalDto intervalDto) {
		this.intervalDto = intervalDto;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isCalculated() {
		return calculated;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getLockingUser() {
		return lockingUser;
	}
	public void setLockingUser(String lockingUser) {
		this.lockingUser = lockingUser;
	}
	public String getUpdatedValue() {
		return updatedValue;
	}
	public void setUpdatedValue(String updatedValue) {
		this.updatedValue = updatedValue;
	}

	public IntervalDto getIntervalDto() {
		return intervalDto;
	}

	public void setConfidenceGrade(String confidenceGrade) {
		this.confidenceGrade = confidenceGrade;
	}
	public String getConfidenceGrade() {
		return confidenceGrade;
	}
	public String getUpdatedConfidenceGrade() {
		return updatedConfidenceGrade;
	}

	public void setUpdatedConfidenceGrade(String updatedConfidenceGrade) {
		this.updatedConfidenceGrade = updatedConfidenceGrade;
	}
	public ModelDto getModelDto() {
		return modelDto;
	}
	public void setModelDto(ModelDto modelDto) {
		this.modelDto = modelDto;
	}



	public int getBranchId() {
		return branchId;
	}



	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}



	public int getRunId() {
		return runId;
	}



	public void setRunId(int runId) {
		this.runId = runId;
	}



	public Long getTagId() {
		return tagId;
	}



	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	
	
}	
