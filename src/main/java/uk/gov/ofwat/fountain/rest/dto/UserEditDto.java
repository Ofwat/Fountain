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

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;


@XmlRootElement(name = "userEdit")
@XmlType(propOrder = { "id", "user", "value", "original", "timestamp", "itemDto", "intervalDto", "company", "groupEntry", "key", "editType"})
public class UserEditDto {

	private int id;
	private User user;
	private String value;
	private String original;
	private Date timestamp;
	private ItemDto itemDto;
	private IntervalDto intervalDto;
	private Company company;
	private GroupEntry groupEntry;
	private String key;	
	private EditType editType;
	private long runId;
	private String runName;

	public UserEditDto() {
	}
	
	public String getOriginal() {
		if (null == original) {
			return "";
		}
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public void setKey(String key) {
		this.key = key;
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
	
	public ItemDto getItemDto() {
		return itemDto;
	}

	public void setItemDto(ItemDto itemDto) {
		this.itemDto = itemDto;
	}

	public IntervalDto getIntervalDto() {
		return intervalDto;
	}

	public void setIntervalDto(IntervalDto intervalDto) {
		this.intervalDto = intervalDto;
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

	public String getKey(){
		return key;
	}

	public EditType getEditType() {
		return editType;
	}

	public void setEditType(EditType editType) {
		this.editType = editType;
	}

	public long getRunId() {
		return runId;
	}

	public void setRunId(long runId) {
		this.runId = runId;
	}

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

}
