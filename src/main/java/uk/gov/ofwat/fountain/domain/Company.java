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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.audit.SkipAudit;

@XmlRootElement(name = "company")
@XmlType(propOrder = { "id", "name" , "code", "companyType", "expired", "description"})

public class Company  implements Addressable, Entity, Coded, Serializable {

	private static final long serialVersionUID = -724090187980905155L;

	private int id;
	private String name;
	private String code;
	@SkipAudit
	private CompanyType companyType;
	private boolean expired;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public CompanyType getCompanyType() {
		return companyType;
	}
	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	public boolean isCompanyInType(String type){
		if(null == type || "".equals(type)){
			// no type attribute so applies to all companies
			return true;
		}
		if("ALL".equalsIgnoreCase(type)){
			return true;
		}
		else if(companyType.getCode().equalsIgnoreCase(type)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (null==obj){	return false;}
		if(! (obj instanceof Company)){return false;}
		Company other = (Company)obj;
		if(other.id != id){return false;}
		if(null != code ){
			if(!code.equals(other.code)){return false;}
		}else{
			if(null != other.code){return false;}
		}
		if(null != name){
			if(!name.equals(other.name)){return false;}
		}else{
			if(null != other.name){return false;}
		}
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", code=" + code
				+ ", companyType=" + companyType + ", expired=" + expired
				+ ", description=" + description + "]";
	}
	
}
