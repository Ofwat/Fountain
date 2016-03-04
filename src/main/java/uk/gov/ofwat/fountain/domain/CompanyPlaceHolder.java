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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "company")
@XmlType(propOrder = { "id", "name" , "code", "companyType", "expired", "description" })

public class CompanyPlaceHolder extends Company {

	private final int id;
	private final String name;
	private final String code;
	private final CompanyType companyType;
	private final boolean expired;
	private final String description;

	public static final CompanyPlaceHolder COMPANY_PLACE_HOLDER = new CompanyPlaceHolder(0, 
																						"place holder", 
																						"NONE", 
																						new CompanyType(1, "WASC", "Water and Sewerage Company"), 
																						false, 
																						"company description place holder");
	
	private CompanyPlaceHolder(int id, String name, String code,
			CompanyType companyType, boolean expired, String description) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.companyType = companyType;
		this.expired = expired;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
	}
	public CompanyType getCompanyType() {
		return companyType;
	}
	public void setCompanyType(CompanyType companyType) {
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
		if(other.getId() != id){return false;}
		if(null != code ){
			if(!code.equals(other.getCode())){return false;}
		}else{
			if(null != other.getCode()){return false;}
		}
		if(null != name){
			if(!name.equals(other.getName())){return false;}
		}else{
			if(null != other.getName()){return false;}
		}
		return id == other.getId();
	}
	
	
}
