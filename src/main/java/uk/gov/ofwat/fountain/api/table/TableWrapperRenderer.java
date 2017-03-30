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
package uk.gov.ofwat.fountain.api.table;

import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.TableStructureService;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.table.reader.SectionLayout;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.domain.tableWrapper.*;

import java.util.Arrays;


public class TableWrapperRenderer {
	private ModelService modelService;
	private TableStructureService tableStructureService;
	private TableStructure tableStructure;

	private int sectionId;
	private int rowId;
	private int columnId;
	private int cellId;

	private int nextSectionId(){return ++sectionId;}
	private int nextRowId(){return ++rowId;}
	private int nextColumnId(){return ++columnId;}
	private int nextCellId(){return ++cellId;}

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
		TWModel twModel = new TWModel();
		TWTable twTable = new TWTable();
		twModel.setTwTable(twTable);
		ModelPage modelPage = tableStructure.getModelPage();
		twTable.setId(tableId);
		twTable.setName(modelPage.getPageName());
		twTable.setDescription(modelPage.getTableDescription());
		twTable.setCompanyType(modelPage.getCompanyType());

		for (PageSection pageSection: modelPage.getPageSections()) {
			TWSection twSection = new TWSection();
			// set section specifics here as required
			twTable.addTwSection(twSection);
			TWSectionDetails twSectionDetails = new TWSectionDetails();
			twSection.setTwSectionDetails(twSectionDetails);
			twSection.setId(nextSectionId());
			// set section details
			twSectionDetails.setTwSectionId(twSection.getId());
			twSectionDetails.setCode(pageSection.getCode());
			// get company type from form
//			twSectionDetails.setItemCodeColumn(pageSection.getItemCodeColumn().intValue());
			twSectionDetails.setSectionType(pageSection.getSectionType());
//			twSectionDetails.setDisplay(pageSection.getSectionDetails().getDisplay());
//			twSectionDetails.setAllowGroupSelection(pageSection.getSectionDetails().getAllowGroupSelection());
//			twSectionDetails.setItemCodeColumn(pageSection.getSectionDetails().getItemCodeColumn().intValue());
//			twSectionDetails.setSectionType(pageSection.getSectionDetails().getSectionType());
//			twSectionDetails.setUseConfidenceGrades(pageSection.getSectionDetails().getUseConfidenceGrades());
//			twSectionDetails.setUseItemCode(pageSection.getSectionDetails().getUseItemCode());
//			twSectionDetails.setUseItemDescription(pageSection.getSectionDetails().getUseItemDescription());
//			twSectionDetails.setUseLineNumber(pageSection.getSectionDetails().getUseLineNumber());
//			twSectionDetails.setUseUnit(pageSection.getSectionDetails().getUseUnit());
//			twSectionDetails.setUseYearCode(pageSection.getSectionDetails().getUseYearCode());

			// set columns and rows
			for (PageForm pageForm: pageSection.getPageForms()) {
				// FORM
				twSectionDetails.setRowCount(pageForm.getRows());
				twSectionDetails.setColCount(pageForm.getCols());

				FormDisplayCell[][] formDisplayGrid = pageForm.getCellGrid();
				SectionLayout sectionLayout = new SectionLayout(formDisplayGrid, pageForm.getRows(), pageForm.getCols());

				for (FormDisplayCell[] formDisplayRow: Arrays.asList(formDisplayGrid)) {
					// ROW
					TWRow twRow = new TWRow();
					twSection.addTwRow(twRow);
					twRow.setId(nextRowId());
					twRow.setRowNumber(twRow.getId());
					for (FormDisplayCell formDisplayCell:  Arrays.asList(formDisplayRow)) {
						// CELL
						if (sectionLayout.getCellLayout()[formDisplayCell.getRow()][formDisplayCell.getColumn()].contains("~")) {
							// drop empty cells that there is no room for.
							continue;
						}
						TWCell twCell = new TWCell();
						twCell.setId(nextCellId());
						twCell.setHeader(formDisplayCell.getCellType().equals(CellType.COL_HEADING));
						twCell.setSectionId(twSection.getId());

						// start rows
						twCell.setStartRowId(twRow.getId());
						twCell.setRowSpan(formDisplayCell.getRowSpan());
						// end rows
						// start cols
						twCell.setStartColumnNo(formDisplayCell.getColumn() +1);
						twCell.setColSpan(formDisplayCell.getColumnSpan());
						// end cols

						twCell.setCellType(formDisplayCell.getCellType().name());
//						twCell.setValidations();
						if (formDisplayCell.getCellType().equals(CellType.INPUT) ||
							formDisplayCell.getCellType().equals(CellType.CALC) ||
							formDisplayCell.getCellType().equals(CellType.COPYCELL)) {
							twCell.setKey(formDisplayCell.getCellContents());
							twCell.setDisplayText("");
						}
						else {
							twCell.setKey("");
							twCell.setDisplayText(formDisplayCell.getCellContents());
						}
						twCell.setContent("");
						twRow.addTwCell(twCell);

					}
				}
			}

		}

		return twModel;
	}

	private void printTableWrapper(TWModel twModel) {
		TWTable twTable = twModel.getTwTable();
//		System.out.println("printTableWrapper()");
		for (TWSection twSection: twTable.getTwSections()) {
//			System.out.println("SECTION");
			for (TWRow twRow: twSection.getTwRows()) {
//				System.out.println("ROW ");
				for (TWCell twCell: twRow.getTwCells()) {
					System.out.print("\t" + twCell.getDisplayText());
				}
			}
		}
	}
}