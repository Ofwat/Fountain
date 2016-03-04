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

public class Value implements Entity, Serializable {

	private int id;
//	private String value;
//	private int dataId;
//	private int auditId;
//	private int pricebaseId;
//	private int confidenceGradeId;
//	private int branchTagId; 

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public String getValue() {
//		return value;
//	}
//	public void setValue(String value) {
//		this.value = value;
//	}
//	public int getDataId() {
//		return dataId;
//	}
//	public void setDataId(int dataId) {
//		this.dataId = dataId;
//	}
//	public int getAuditId() {
//		return auditId;
//	}
//	public void setAuditId(int auditId) {
//		this.auditId = auditId;
//	}
//	public int getPricebaseId() {
//		return pricebaseId;
//	}
//	public void setPricebaseId(int pricebaseId) {
//		this.pricebaseId = pricebaseId;
//	}
//	public int getConfidenceGradeId() {
//		return confidenceGradeId;
//	}
//	public void setConfidenceGradeId(int confidenceGradeId) {
//		this.confidenceGradeId = confidenceGradeId;
//	}
//	public int getBranchTagId() {
//		return branchTagId;
//	}
//	public void setBranchTagId(int branchTagId) {
//		this.branchTagId = branchTagId;
//	}

}
