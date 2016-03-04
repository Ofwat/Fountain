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
import java.util.Map;
import java.util.Set;


public class ItemProperties implements Entity, Serializable  {
	private int id;
	private Item item;
	private int version;
	private String description;
	private String definition;
	private Map<Interval, ItemPropertyInterval>formulae;
	private boolean fixedFormula;
	private String generalFormula;
	private String groupTotalFormula;
	private PriceBase priceBase;
	private InflationType inflationType;
	private Purpose purpose;
	private int decimalPlaces;
	private int reservoirVersion;
	private boolean attachedToModel;
	
	/**
	 * Creates a new ItemProperties with the minimum  necessary information
	 * @param item - the item for which this is a property
	 * @param version - an incremental version number
	 * @param description - a textual description for the user to see
	 * @param formulae - a map of ItemPropertyIntervals providing formulae for each interval needed. Use this when the formula 
	 * changes by year or only applies to a subset of years.
	 * @param fixedFormula - set this to true to indicate there is a constant formula to apply to all years.
	 * @param generalFormula - A general formula to apply in all cases when fxedFormula is set to true.
	 * @param reservoirVersion - the version of the item in the reservoir dictionary
	 * @param attachedToModel - set to true only when a model is imported and uses this item   
	 */
	public ItemProperties(Item item, 
			              int version, 
			              String description, 
			              String definition,
			              int decimalPlaces,
			              Map<Interval, ItemPropertyInterval>formulae,
			              boolean fixedFormula,
			              String generalFormula, 
			              String groupTotalFormula,
			              int reservoirVersion,
			              boolean attachedToModel
			              ){
		this.setItem(item);
		this.setVersion(version);
		this.setDescription(description);
		this.setDefinition(definition);
		this.setDecimalPlaces(decimalPlaces);
		this.setFormulae(formulae);
		this.setFixedFormula(fixedFormula);
		this.setGeneralFormula(generalFormula);
		this.reservoirVersion=reservoirVersion;
		this.attachedToModel = attachedToModel;
		
		if(fixedFormula && (null==generalFormula)){
			throw new InstantiationError("You must include a general formula when setting fixed formula to true");
		}
		this.setGroupTotalFormula(groupTotalFormula);
	}

	// Just for unit tests.
	public ItemProperties() {
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
	public String getDefinition() {
		return definition;
	}
	
	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * returns true if this item properties has a fixed formula that doesn't change over time.
	 * If false, use getFormulae() to return a map of interval specific formulae;
	 * @return
	 */
	public boolean isFixedFormula() {
		return fixedFormula;
	}
	public void setFixedFormula(boolean fixedFormula) {
		this.fixedFormula = fixedFormula;
	}
	public void setGeneralFormula(String formula) {
		this.generalFormula = formula;
	}
	public Map<Interval, ItemPropertyInterval> getFormulae() {
		return formulae;
	}
	public String getFormula(Interval interval){
		if(fixedFormula){
			return generalFormula;
		}
		if(null == formulae){
			return null;
		}
		if(null == formulae.get(interval)){
			return null;
		}
		return formulae.get(interval).getFormula();
	}
	public String getGeneralFormula(){
		return generalFormula;
	}
	public Set<Interval>getForumlaIntervals(){
		if(null == formulae){return null;}
		return formulae.keySet();
	}
	public void setFormulae(Map<Interval, ItemPropertyInterval> formulae) {
		this.formulae = formulae;
	}
	public PriceBase getPriceBase() {
		return priceBase;
	}

	public void setPriceBase(PriceBase priceBase) {
		this.priceBase = priceBase;
	}

	public InflationType getInflationType() {
		return inflationType;
	}

	public void setInflationType(InflationType inflationType) {
		this.inflationType = inflationType;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}
	

	public String getGroupTotalFormula() {
		return groupTotalFormula;
	}

	public void setGroupTotalFormula(String groupTotalFormula) {
		this.groupTotalFormula = groupTotalFormula;
	}

	public boolean isRawDataValue(Interval interval) {
		if(fixedFormula){
			// this item is always a formula
			return false;
		}
		if(null == formulae || formulae.isEmpty()){
			// there are no general or annual formulae - all values are data
			return true;
		}
		// decide whether there is an annual formula for the given interval
		return !formulae.keySet().contains(interval);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemProperties other = (ItemProperties) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (id != other.id)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (decimalPlaces != other.decimalPlaces)
			return false;
		if (version != other.version)
			return false;
		if (formulae == null ||
			formulae.isEmpty()) {
			if (other.formulae != null &&
				!other.formulae.isEmpty())
				return false;
		} 
		else if(null == other.formulae || formulae.size() != other.formulae.size()){
			return false;
		}
		else{
			for(Interval interval: formulae.keySet()){
				if(!other.formulae.containsKey(interval)){
					return false;
				}
				if(!formulae.get(interval).equals(other.formulae.get(interval))){
					return false;
				}
			}
		}
		if (generalFormula == null) {
			if (other.generalFormula != null)
				return false;
		} else if (!generalFormula.equals(other.generalFormula  ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemProperties [id=" + id + ", item=" + item + ", version="
				+ version + ", description=" + description + ", definition="
				+ definition + ", formulae=" + formulae + ", fixedFormula="
				+ fixedFormula + ", generalFormula=" + generalFormula
				+ ", groupTotalFormula=" + groupTotalFormula + ", priceBase="
				+ priceBase + ", inflationType=" + inflationType + ", purpose="
				+ purpose + ", decimalPlaces=" + decimalPlaces
				+ ", reservoirVersion=" + reservoirVersion
				+ ", attachedToModel=" + attachedToModel + "]";
	}

	public boolean equalsExecptingId(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemProperties other = (ItemProperties) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equalsExecptingVersionedData(other.item))
			return false;
		if (decimalPlaces != other.decimalPlaces)
			return false;
		if (version != other.version)
			return false;
		if (formulae == null ||
			formulae.isEmpty()) {
			if (other.formulae != null &&
				!other.formulae.isEmpty())
				return false;
		} 
		else if(null == other.formulae || formulae.size() != other.formulae.size()){
			return false;
		}
		else{
			for(Interval interval: formulae.keySet()){
				if(!other.formulae.containsKey(interval)){
					return false;
				}
				if(!formulae.get(interval).equals(other.formulae.get(interval))){
					return false;
				}
			}
		}
		if (generalFormula == null) {
			if (other.generalFormula != null)
				return false;
		} else if (!generalFormula.equals(other.generalFormula  ))
			return false;
		return true;
	}

	public int getReservoirVersion() {
		return reservoirVersion;
	}

	public void setReservoirVersion(int reservoirVersion) {
		this.reservoirVersion = reservoirVersion;
	}

	public boolean isAttachedToModel() {
		return attachedToModel;
	}

	public void setAttachedToModel(boolean attachedToModel) {
		this.attachedToModel = attachedToModel;
	}

	
}
