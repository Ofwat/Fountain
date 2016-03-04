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

package uk.gov.ofwat.fountain.api.report;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

/**
 * Java representation of the report that knows where the axis lies and is 
 * capable of populating data / expanding rows.
 * 
 * Writers can iterate over the lines and cells and convert straight to jsp / poi / whatever
 * specific cells they like.
 */
public class ReportCell {
	private String cellClass;
	private String cellContents;
	private CellType cellType;
	private boolean dataCG = false;
	private String colHeader;
	private boolean addRunTagToKey = false;
	
	public ReportCell(String cellClass, String cellContents, CellType cellType){
		this(cellClass, cellContents, cellType, "");
	}

	public ReportCell(String cellClass, String cellContents, CellType cellType, String colHeader){
		this.cellClass = cellClass;
		this.cellContents = cellContents;
		this.cellType = cellType;
		this.colHeader = colHeader;
	}

	/**
	 * populate the reportcell based on the values in the triplet
	 * @param triplet
	 */
	public ReportCell(Triplet triplet, boolean cg, ReportService reportService) {
		ItemProperties ip = triplet.ri.getItemProperties();

		DataKey dataKey = new DataKey();
		dataKey.setItemId(ip.getItem().getId());
		dataKey.setIntervalId(triplet.interval.getId());
		dataKey.setCompanyId(triplet.company.getId());
		dataKey.setCg(cg);
		dataKey.setItemPropertiesId(ip.getId());
		dataKey.setModelId(triplet.ri.getModelDto().getId());

		if (ip.isRawDataValue(triplet.interval)) {
			cellType = CellType.INPUT;
		}
		else {
			cellType = CellType.CALC;
		}
		
		if(cg){
			cellType = CellType.INPUT;
		}

		if((null == triplet.runTag) ||
			RunPlaceHolder.RUN_PLACE_HOLDER.getId() == triplet.runTag.getRun().getId()) {					  		
			// There is no run present
			// Mark cell to add RunTag to key at runtime
			addRunTagToKey = true;
			cellContents = dataKey.getKey(false);
    	}
		else {	
			// We have a run
			// Add the RunTag to the key now
			dataKey.setRunTag(true);
			dataKey.setRunId(String.valueOf(triplet.runTag.getRun().getId()));

			if (RunModelTag.LATEST.equals(triplet.runTag.getRunModelTag())) {
				dataKey.setTagId("" + RunModelCompanyTag.LATEST.getId());
			}
			else {
				// proper tag
				if (null == triplet.company ||			 
					0 == triplet.company.getId()) {
					// no company so use id of RunModelTag (can use this and company id to find the correct RunModelCompanyTag id at runtime)
					dataKey.setTagId("" + triplet.runTag.getRunModelTag().getId());
				}
				else {
					// we have a company
					dataKey.setTagId("" + triplet.runTag.getRunModelTag().getRunModelCompanyTag(triplet.company.getId()).getId());
				}
			}
			cellContents = dataKey.getKey(false);
		}
	}

	public String getCellClass() {
		return cellClass;
	}
	public void setCellClass(String cellClass) {
		this.cellClass = cellClass;
	}
	public String getCellContents() {
		return cellContents;
	}
	public void setCellContents(String cellContents) {
		this.cellContents = cellContents;
	}
	
	public CellType getCellType() {
		return cellType;
	}

	public boolean isDataCG() {
		return dataCG;
	}

	
	
	
	public String getColHeader() {
		return colHeader;
	}

	@Override
	protected ReportCell clone()  {
		ReportCell clone = new ReportCell(this.cellClass, this.cellContents, this.cellType, this.colHeader);
		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cellClass == null) ? 0 : cellClass.hashCode());
		result = prime * result
				+ ((cellContents == null) ? 0 : cellContents.hashCode());
		result = prime * result
				+ ((cellType == null) ? 0 : cellType.hashCode());
		result = prime * result + (dataCG ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportCell other = (ReportCell) obj;
		if (cellClass == null) {
			if (other.cellClass != null)
				return false;
		} else if (!cellClass.equals(other.cellClass))
			return false;
		if (cellContents == null) {
			if (other.cellContents != null)
				return false;
		} else if (!cellContents.equals(other.cellContents))
			return false;
		if (cellType == null) {
			if (other.cellType != null)
				return false;
		} else if (!cellType.equals(other.cellType))
			return false;
		if (dataCG != other.dataCG)
			return false;
		return true;
	}

	public boolean isAddRunTagToKey() {
		return addRunTagToKey;
	}

}
