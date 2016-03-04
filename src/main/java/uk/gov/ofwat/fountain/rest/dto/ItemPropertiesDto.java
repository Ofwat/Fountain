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

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import uk.gov.ofwat.fountain.audit.SkipAudit;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;

@XmlRootElement(name = "itemproperties")
@XmlType(propOrder = { "id", "version", "description", "generalFormula", "decimalPlaces", "priceBase", "inflationType", "item", "definition", "purpose"})
public class ItemPropertiesDto {
	private int id;
	@SkipAudit private Item item;
	private int version;
	private String description;
	private String generalFormula = "";
	private String purpose;
	private String definition;
	private String priceBase;
	private String inflationType;
	
	
	
	private int decimalPlaces;
	private Map<IntervalDto, String> intervalFormulae;
	public ItemPropertiesDto(){
	}
	
	public ItemPropertiesDto(ItemProperties itemProperties){
		setItem(itemProperties.getItem());
		setVersion(itemProperties.getVersion());
		setDescription(itemProperties.getDescription());
		setDecimalPlaces(itemProperties.getDecimalPlaces());
		setDefinition(itemProperties.getDefinition());
		if(null != itemProperties.getPriceBase()){
			setPriceBase(itemProperties.getPriceBase().getDescription());
		}
		if(null != itemProperties.getInflationType()){
			setInflationType(itemProperties.getInflationType().getDescription());
		}
		if(null != itemProperties.getPurpose()){
			setPurpose(itemProperties.getPurpose().getDescription());
		}
		if(itemProperties.isFixedFormula()){
			setGeneralFormula(itemProperties.getGeneralFormula());
		}
		else if(null != itemProperties.getFormulae()){
			intervalFormulae = new HashMap<IntervalDto, String>();
			for(Interval interval: itemProperties.getFormulae().keySet()){
				IntervalDto iDto = new IntervalDto();
				iDto.setId(interval.getId());
				iDto.setIntervalTypeId(interval.getIntervalType().getId());
				iDto.setName(interval.getName());
				intervalFormulae.put(iDto, itemProperties.getFormulae().get(interval).getFormula());
			}
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriceBase() {
		return priceBase;
	}
	public void setPriceBase(String priceBase) {
		this.priceBase = priceBase;
	}
	public String getInflationType() {
		return inflationType;
	}
	public void setInflationType(String inflationType) {
		this.inflationType = inflationType;
	}
	public String getGeneralFormula() {
		return generalFormula;
	}
	public void setGeneralFormula(String generalFormula) {
		this.generalFormula = generalFormula;
	}
	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}
	public int getDecimalPlaces() {
		return decimalPlaces;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return "ItemPropertiesDto [id=" + id + ", item=" + item + ", version="
				+ version + ", description=" + description
				+ ", generalFormula=" + generalFormula + ", purpose=" + purpose
				+ ", definition=" + definition + ", priceBase=" + priceBase
				+ ", inflationType=" + inflationType + ", decimalPlaces="
				+ decimalPlaces + ", intervalFormulae=" + intervalFormulae
				+ "]";
	}

	
}
