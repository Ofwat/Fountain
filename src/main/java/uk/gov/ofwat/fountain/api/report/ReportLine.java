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

import java.util.ArrayList;
import java.util.List;

public class ReportLine {

	private boolean grouped;
	
	private List<ReportCell> cells = new ArrayList<ReportCell>();
	
	public boolean isGrouped(){
		return grouped;
	}
	
	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}

	
	public void setCells(List<ReportCell> cells) {
		this.cells = cells;
	}

	public List<ReportCell> getCells(){
		return cells;
	}
	
	public void addCell(ReportCell cell){
		cells.add(cell);
	}

	@Override
	protected ReportLine clone()  {
		ReportLine clone = new ReportLine();
		List<ReportCell> clonedCells = new ArrayList<ReportCell>();
		for (ReportCell reportCell: this.getCells()) {
			clonedCells.add(reportCell.clone());
		}
		clone.setCells(clonedCells);
		clone.setGrouped(this.isGrouped());
		return clone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cells == null) ? 0 : cells.hashCode());
		result = prime * result + (grouped ? 1231 : 1237);
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
		ReportLine other = (ReportLine) obj;
		if (cells == null) {
			if (other.cells != null)
				return false;
		} else if (!cells.equals(other.cells))
			return false;
		if (grouped != other.grouped)
			return false;
		return true;
	}
	
}
