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

public class Lock implements Serializable, Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5428956638790547920L;
	
	private int id;
	private User user;
	private int itemId;
	private int intervalId;
	private int companyId;
	private Long runId = 0l;
	
	public Lock() {
	}
	
	public Lock(int itemId, int intervalId, int companyId, User user) {
		this.itemId = itemId;
		this.intervalId = intervalId;
		this.companyId = companyId;
		this.user = user;
	}
	
	public Lock(int itemId, int intervalId, int companyId, User user, Long runId) {
		this.itemId = itemId;
		this.intervalId = intervalId;
		this.companyId = companyId;
		this.user = user;
		this.runId = runId;
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

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}
	
	@Override
	public String toString() {
		return "Lock [id=" + id + ", user=" + user + ", itemId=" + itemId
				+ ", intervalId=" + intervalId + ", companyId=" + companyId
				+ ", runId=" + runId;
	}
	
	
}
