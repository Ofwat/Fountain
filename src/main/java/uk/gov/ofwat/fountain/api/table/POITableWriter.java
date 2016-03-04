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

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.api.TableStructureService;
import uk.gov.ofwat.fountain.domain.DataTable;

public class POITableWriter {
	private TableStructureService tableStructureService;
	private POITableRenderer poiTableRenderer; 
	
	private XSSFWorkbook workBook = null;
	
	public XSSFWorkbook getWorkBook() {
		return workBook;
	}
	public void setWorkBook(XSSFWorkbook workBook) {
		this.workBook = workBook;
	}
	
	public TableStructureService getTableStructureService() {
		return tableStructureService;
	}
	public void setTableStructureService(TableStructureService tableStructureService) {
		this.tableStructureService = tableStructureService;
	}

	public POITableRenderer getPoiTableRenderer() {
		return poiTableRenderer;
	}
	public void setPoiTableRenderer(POITableRenderer poiTableRenderer) {
		this.poiTableRenderer = poiTableRenderer;
	}
	
	/**
	 * Create an excel table version of the data table and write it to the stream
	 * @param report
	 * @param os
	 * @throws IOException 
	 */
	public void streamTableToExcel(List<DataTable> tables, OutputStream os) throws IOException{
		workBook = new XSSFWorkbook();

		for (DataTable table : tables) {
			TableStructure tableStructure = tableStructureService.getTableStructure(table.getId());
			if (tableStructure!=null){
				poiTableRenderer.renderTable(workBook, tableStructure, table);
			}
		}

		workBook.write(os);
	}
	
}
