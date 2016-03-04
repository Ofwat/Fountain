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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class PoiReportBuilder  {

	private XSSFWorkbook workBook;
	private CreationHelper creationHelper = null; 

	private Sheet sheet;
	private Row row;
	private int rowIdx = -1;
	private int cellIdx = 0;
	private Font font;
	
	public PoiReportBuilder(Map<String, DataDto> dataMap) {
		this.workBook = new XSSFWorkbook();
		creationHelper = workBook.getCreationHelper();

		this.sheet = workBook.createSheet();
		
	    // font
	    font = workBook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        
	}

	void writeToStream(OutputStream os) {
		try {
			workBook.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void startReport(String reportName) {
		newRow();
		makeHeaderCell(reportName, IndexedColors.WHITE.getIndex());
		endRow();
	}

	void newRow() {
		cellIdx = 0;
		row = sheet.createRow(++rowIdx);
	}

	void newRow(Entity entity) {
		cellIdx = 0;
		row = sheet.createRow(++rowIdx);
	}

	void endRow() {
	}

	void emptyCell() {
		cellIdx++;
	}
	
	
	private void makeHeaderCell(String content, short colour) {
		Cell cell = row.createCell(cellIdx++);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		RichTextString rts = 	creationHelper.createRichTextString(content);
		cell.setCellValue(rts);
		XSSFCellStyle style = workBook.createCellStyle();
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(colour); 
        style.setFont(font);
		cell.setCellStyle(style);
	}

}
