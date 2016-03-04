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

@XmlRootElement(name = "team")
@XmlType(propOrder = { "id", "name", "code"})
public class Team implements Entity, Serializable {
	
	private static final long serialVersionUID = -3004622241801051406L;

	int id;
	String name;
	String code;
	
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
	@Override
	public boolean equals(Object obj) {
		if (null==obj){	return false;}
		if(! (obj instanceof Team)){return false;}
		Team other = (Team)obj;
		if(other.id != id){return false;}
		if(null != name ){
			if(!name.equals(other.name)){return false;}
		}else{
			if(null != other.name){return false;}
		}
		if (null!=code){
			if(!code.equals(other.code)){return false;}
		}else{
			if(null != other.code){return false;}	
		}
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	
}
