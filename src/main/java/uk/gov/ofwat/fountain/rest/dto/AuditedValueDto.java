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

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.User;

@XmlRootElement(name = "audit")
@XmlType(propOrder = { "id", "comment", "user", "date", "value", "cg", "runName" })
public class AuditedValueDto implements Serializable{
	
	private static final long serialVersionUID = 5083861505534644261L;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy HH:mm" );

	private int id;
	private String comment;
	private User user;
	private String date;
	private String value;
	private String cg;
	private String runName;
	
	
	public AuditedValueDto(AuditedValue auditedValue){
		this.id = auditedValue.getId();
		this.comment = auditedValue.getComment();
		this.user = auditedValue.getUser();
		this.value = auditedValue.getValue();
		this.cg = auditedValue.getCg();
		this.date = sdf.format(auditedValue.getDate()); 
		this.runName = auditedValue.getRunName(); 
	}

	public int getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	public String getDate() {
		return date;
	}

	public String getValue() {
		return value;
	}

	public String getCg() {
		return cg;
	}

	public String getRunName() {
		return runName;
	}

}
