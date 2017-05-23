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
package uk.gov.ofwat.fountain.api.table.TableWrapper;

import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.TableStructureService;
import uk.gov.ofwat.fountain.api.table.TableStructure;
import uk.gov.ofwat.fountain.api.table.reader.SectionLayout;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.tableWrapper.*;


public class TableWrapperRenderer {
	private ModelService modelService;
	private TableStructureService tableStructureService;
	private TableStructure tableStructure;
	private SectionLayout sectionLayout;

	private int sectionId;
	private int rowId;
	private int columnId;
	private int cellId;

	public Integer nextSectionId(){return ++sectionId;}
	public Integer nextRowId(){return ++rowId;}
	public Integer nextColumnId(){return ++columnId;}
	public Integer nextCellId(){return ++cellId;}

	public Integer currentSectionId(){return sectionId;}
	public Integer currentRowId(){return rowId;}
	public Integer currentColumnId(){return columnId;}
	public Integer currentCellId(){return cellId;}

	public SectionLayout getSectionLayout() {
		return sectionLayout;
	}

	public void setSectionLayout(SectionLayout sectionLayout) {
		this.sectionLayout = sectionLayout;
	}

	public void setTableStructureService(TableStructureService tableStructureService) {
		this.tableStructureService = tableStructureService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public TableStructureService getTableStructureService() {
		return tableStructureService;
	}

	public TWModel renderTable(int tableId) {
		tableStructure = tableStructureService.getTableStructure(tableId);
		ModelPage modelPage = tableStructure.getModelPage();
		TableWrapperTableStrategyImpl tableWrapperTableStrategyImpl = new TableWrapperTableStrategyImpl();
		TWTable twTable = (TWTable)tableWrapperTableStrategyImpl.render(this, modelPage, null, null, null);
		TWModel twModel = new TWModel();
		twModel.setTwTable(twTable);
		return twModel;
	}

	private void printTableWrapper(TWModel twModel) {
		TWTable twTable = twModel.getTwTable();
		for (TWSection twSection: twTable.getTwSections()) {
			for (TWRow twRow: twSection.getTwRows()) {
				for (TWCell twCell: twRow.getTwCells()) {
					System.out.print("\t" + twCell.getDisplayText());
				}
			}
		}
	}
}