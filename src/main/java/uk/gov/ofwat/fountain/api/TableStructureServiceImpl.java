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
package uk.gov.ofwat.fountain.api;

import java.util.List;

import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.table.TableStructure;
import uk.gov.ofwat.fountain.dao.FormCellDao;
import uk.gov.ofwat.fountain.dao.ModelPageDao;
import uk.gov.ofwat.fountain.dao.PageFormDao;
import uk.gov.ofwat.fountain.dao.PageSectionDao;
import uk.gov.ofwat.fountain.dao.TableDao;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.FormEmptyCell;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;

public class TableStructureServiceImpl implements TableStructureService {

	private ModelPageDao modelPageDao; 
	private FormCellDao formCellDao; 
	private PageFormDao pageFormDao;
	private PageSectionDao pageSectionDao;
	private TableDao tableDao;

	public FormCellDao getFormCellDao() {
		return formCellDao;
	}

	public void setFormCellDao(FormCellDao formCellDao) {
		this.formCellDao = formCellDao;
	}

	public PageFormDao getPageFormDao() {
		return pageFormDao;
	}

	public void setPageFormDao(PageFormDao pageFormDao) {
		this.pageFormDao = pageFormDao;
	}

	public PageSectionDao getPageSectionDao() {
		return pageSectionDao;
	}

	public void setPageSectionDao(PageSectionDao pageSectionDao) {
		this.pageSectionDao = pageSectionDao;
	}

	public TableStructure getTableStructure(int tableId) {
		TableStructure tableStructure = new TableStructure();
		ModelPage modelPage = modelPageDao.findByTableId(tableId);
		Table table = tableDao.findById(modelPage.getTable().getId());
		
		tableStructure.setModelId(table.getModelId());
		tableStructure.setModelPage(modelPage);
		tableStructure.setTableId(modelPage.getTable().getId());
		tableStructure.setTableName(table.getName());
		
		List<PageSection> pageSections = pageSectionDao.findByModelPageId(modelPage.getId()); 
		modelPage.setPageSections(pageSections);
		
		for (PageSection pageSection: pageSections) {
			List<PageForm> pageForms = pageFormDao.findByPageSectionId(pageSection.getId());
			for (PageForm pageForm: pageForms) {
				pageSection.addPageForm(pageForm);
				List<FormDisplayCell> allCells = formCellDao.findByFormId(pageForm.getId());
				FormDisplayCell[][] cellGrid = new FormDisplayCell[pageForm.getRows()][pageForm.getCols()];
				for (FormDisplayCell cell: allCells) {
					cellGrid[cell.getRow()][cell.getColumn()] = cell;
				}
				// fill in empty cells
				for(FormDisplayCell[] line: cellGrid){
					for(int i=0; i < line.length; i++){
						if (null == line[i]) {
							FormDisplayCell emptyCell = new FormEmptyCell();
							emptyCell.setCellType(CellType.EMPTY);
							line[i] = emptyCell;
						}
					}
				}
				pageForm.setCellGrid(cellGrid);
			}
		}
		
		
		return tableStructure;
	}

	
	public ModelPageDao getModelPageDao() {
		return modelPageDao;
	}

	public void setModelPageDao(ModelPageDao modelPageDao) {
		this.modelPageDao = modelPageDao;
	}

	public TableDao getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDao tableDao) {
		this.tableDao = tableDao;
	}

	public int saveModelPageStructure(ModelPage modelPage) {
		int id = modelPageDao.create(modelPage);
		
		for (PageSection pageSection: modelPage.getPageSections()) {
			pageSectionDao.create(pageSection);
			for (PageForm pageForm: pageSection.getPageForms()){
				pageFormDao.create(pageForm);
				formCellDao.batchInsert(pageForm.getDataDisplayCells());
				formCellDao.batchInsert(pageForm.getLeftDisplayCells());
				formCellDao.batchInsert(pageForm.getTopDisplayCells());
			}
		}
		return id;
	}

}
