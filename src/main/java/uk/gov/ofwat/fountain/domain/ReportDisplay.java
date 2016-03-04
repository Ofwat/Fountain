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

public class ReportDisplay {

	private boolean displayCGs;
	private boolean displayUnit;
	private boolean displayBoncode;
	private boolean displayDesc;
	private boolean displayModel;
	private boolean displayCompanyName;
	private boolean displayCompanyAcronym;
	private boolean displayRunName;
	private boolean displayRunDesc;
	private boolean displayTagDisplayName;
	private boolean displayAgendaName;
	private boolean displayAgendaCode;

	public boolean isDisplayRunName() {
		return displayRunName;
	}
	public void setDisplayRunName(boolean displayRunName) {
		this.displayRunName = displayRunName;
	}
	public boolean isDisplayRunDesc() {
		return displayRunDesc;
	}
	public void setDisplayRunDesc(boolean displayRunDesc) {
		this.displayRunDesc = displayRunDesc;
	}
	public boolean isDisplayTagDisplayName() {
		return displayTagDisplayName;
	}
	public void setDisplayTagDisplayName(boolean displayTagDisplayName) {
		this.displayTagDisplayName = displayTagDisplayName;
	}
	public boolean isDisplayCGs() {
		return displayCGs;
	}
	public void setDisplayCGs(boolean displayCGs) {
		this.displayCGs = displayCGs;
	}
	public boolean isDisplayUnit() {
		return displayUnit;
	}
	public void setDisplayUnit(boolean displayUnit) {
		this.displayUnit = displayUnit;
	}
	public boolean isDisplayBoncode() {
		return displayBoncode;
	}
	public void setDisplayBoncode(boolean displayBoncode) {
		this.displayBoncode = displayBoncode;
	}
	public boolean isDisplayDesc() {
		return displayDesc;
	}
	public void setDisplayDesc(boolean displayDesc) {
		this.displayDesc = displayDesc;
	}
	public boolean isDisplayModel() {
		return displayModel;
	}
	public void setDisplayModel(boolean displayModel) {
		this.displayModel = displayModel;
	}
	public boolean isDisplayCompanyName() {
		return displayCompanyName;
	}
	public void setDisplayCompanyName(boolean displayCompanyName) {
		this.displayCompanyName = displayCompanyName;
	}
	public boolean isDisplayCompanyAcronym() {
		return displayCompanyAcronym;
	}
	public void setDisplayCompanyAcronym(boolean displayCompanyAcronym) {
		this.displayCompanyAcronym = displayCompanyAcronym;
	}
	public boolean isDisplayAgendaName() {
		return displayAgendaName;
	}
	public void setDisplayAgendaName(boolean displayAgendaName) {
		this.displayAgendaName = displayAgendaName;
	}
	public boolean isDisplayAgendaCode() {
		return displayAgendaCode;
	}
	public void setDisplayAgendaCode(boolean displayAgendaCode) {
		this.displayAgendaCode = displayAgendaCode;
	}
	
}
