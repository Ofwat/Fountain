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
import java.util.Date;

/**
 * An audited value that shows the data and CG for an audit. It is
 * assumed that you know the data details.
 */
public class AuditedValue implements Serializable, Entity {
	private static final long serialVersionUID = -8551243784986101012L;
	private int id;
	private String comment;
	private User user;
	private Date date;
	private String value;
	private String cg;
	private String runName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCg() {
		return cg;
	}
	public void setCg(String cg) {
		this.cg = cg;
	}
	@Override
	public String toString() {
		return "AuditedValue [id=" + id + ", comment=" + comment + ", user="
				+ user + ", date=" + date + ", value=" + value + ", cg=" + cg
				+ "]";
	}
	public void setRunName(String runName) {
		this.runName = runName;
	}
	public String getRunName() {
		return runName;
	}
	
}
