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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.api.TableStructureService;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class POITableRenderer {
	public TableStructureService tableStructureService;
	public XSSFWorkbook workBook;
	public CreationHelper creationHelper = null; 
	
	public XSSFCellStyle rowHeaderStyle;
	public XSSFCellStyle colHeaderStyle;
	public XSSFCellStyle inputDataTextStyle;
	public XSSFCellStyle copyCellTextStyle;
	public XSSFCellStyle calcDataTextStyle;
	public XSSFCellStyle inputCGStyle;
	public XSSFCellStyle copyCellCGStyle;
	public XSSFCellStyle calcCGStyle;
	public Map<Short, XSSFCellStyle> inputDataNumericStyleMap;
	public Map<Short, XSSFCellStyle> copyCellDataNumericStyleMap;
	public Map<Short, XSSFCellStyle> calcDataNumericStyleMap;
	private Sheet sheet;
	
	private XSSFColor yellow = null;;
	private XSSFColor lightYellow = null;
	private XSSFColor lightBlue = null;
	private XSSFColor pink = null;
	
	public Workbook renderTable(XSSFWorkbook workBook, TableStructure tableStructure, DataTable table){
		this.workBook = workBook;
		creationHelper = workBook.getCreationHelper();
		
		sheet = workBook.createSheet(table.getCompany().getCode()+ " Table "+ tableStructure.getTableName());
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
		

		inputDataNumericStyleMap = new HashMap<Short, XSSFCellStyle>();
		copyCellDataNumericStyleMap = new HashMap<Short, XSSFCellStyle>();
		calcDataNumericStyleMap = new HashMap<Short, XSSFCellStyle>();

	    yellow = new XSSFColor(new java.awt.Color(255,255,0));
	    lightYellow = new XSSFColor(new java.awt.Color(255,255,224));
	    lightBlue = new XSSFColor(new java.awt.Color(224,255,255));
	    pink = new XSSFColor(new java.awt.Color(255,204,204));
        
	    
	    // Styles
		// Row header style
	    rowHeaderStyle = workBook.createCellStyle();
		// Col header style
		colHeaderStyle = workBook.createCellStyle();
		colHeaderStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		colHeaderStyle.setFillForegroundColor(yellow); 
		Font colHeaderFont = workBook.createFont();
		colHeaderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		colHeaderStyle.setFont(colHeaderFont);
		// Copycell text data cell style
		copyCellTextStyle = workBook.createCellStyle();
		copyCellTextStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		copyCellTextStyle.setFillForegroundColor(pink);
		// Input text data cell style
		inputDataTextStyle = workBook.createCellStyle();
		inputDataTextStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		inputDataTextStyle.setFillForegroundColor(lightYellow);
		// Calc text data cell style
		calcDataTextStyle = workBook.createCellStyle();
		calcDataTextStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		calcDataTextStyle.setFillForegroundColor(lightBlue);
		// Input CG style
		inputCGStyle = workBook.createCellStyle();
		inputCGStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		inputCGStyle.setFillForegroundColor(lightYellow);
		// Input CG style
		copyCellCGStyle = workBook.createCellStyle();
		copyCellCGStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		copyCellCGStyle.setFillForegroundColor(pink);
		// Calc CG style
		calcCGStyle = workBook.createCellStyle();
		calcCGStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		calcCGStyle.setFillForegroundColor(lightBlue);
		
	    // data format
        DataFormat format = workBook.createDataFormat();
        
        int rownum = 1; // starting point
        Row infoRow1  = sheet.createRow(rownum);
        CellStyle style = workBook.createCellStyle(); 
        Font font = workBook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);
		
		Cell titleCell1 = infoRow1.createCell(0);
		titleCell1.setCellType(Cell.CELL_TYPE_STRING);
		String DATE_FORMAT = "dd MMM yyyy h:mm";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    Calendar c1 = Calendar.getInstance(); // today
	    String today = sdf.format(c1.getTime());
	    RichTextString dateRts = creationHelper.createRichTextString(today + 
															": " + 
															tableStructure.getModelPage().getModel().getCode() + 
															" for " + 
															table.getCompany().getName());
	    titleCell1.setCellValue(dateRts);
	    titleCell1.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 9));  

		rownum++;
        Row infoRow2  = sheet.createRow(rownum);
        Cell titleCell = infoRow2.createCell(0);
        titleCell.setCellType(Cell.CELL_TYPE_STRING);
		RichTextString rts = creationHelper.createRichTextString(tableStructure.getModelPage().getTable().getName() + 
														" - " + 
														tableStructure.getModelPage().getTableDescription());
		titleCell.setCellValue(rts);
		titleCell.setCellStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(rownum, rownum, 0, 4));  
		
		rownum++;
	    rownum++;

	    if (tableStructure.getModelPage().isGroupSelect()) {
	    	// group dropdown
	    	groupSelectTable(tableStructure, table, workBook, sheet, format, rownum);
	    }
	    else {
			tableWithoutGroupSelect(tableStructure, table, workBook, sheet, format, rownum);
		}
        return workBook;
	}

	private void tableWithoutGroupSelect(TableStructure tableStructure,
			DataTable table, Workbook workBook, Sheet sheet,
			DataFormat format,
			int rownum) {
		
		for (PageSection section: tableStructure.getModelPage().getPageSections()) {
			for (PageForm form: section.getPageForms()) {
				if (table.getCompany().isCompanyInType(form.getCompanyType())) {
					FormDisplayCell[][] cellGrid = form.getCellGrid();
					for(FormDisplayCell[] line: cellGrid) {
						if (section.isGrouped()) {
							rownum = groupedLine(table, workBook, sheet,
									format, table.getDataList(), rownum,
									line);
						}
			    		else {
							rownum = ungroupedLine(table, workBook, sheet,
									format, table.getDataList(), rownum,
									line);
			    		}
					}
				}
			}
		}
	}

	private int ungroupedLine(DataTable table, Workbook workBook,
			Sheet sheet, 
			DataFormat format, Map<String, DataDto> dataLookup, int rownum,
			FormDisplayCell[] line) {

		Set<GroupEntry> groupEntrySet = table.getGroupEntries();
		if(	(hasDataCells(line) || hasCalcCells(line)) &&
			null != groupEntrySet){
			List<FormDisplayCell> nonRepeatHeaderCells = new ArrayList<FormDisplayCell>();
			for(GroupEntry ge: groupEntrySet){
				if (ge.getDescription().equals("NON GROUPED ITEM")) {
					Row row  = sheet.createRow(rownum);
					rownum++;
					writeLine(line, table, workBook, row, format, ge, nonRepeatHeaderCells, 0, false, null);
				}
			}
		}
		else{
			Row row  = sheet.createRow(rownum);
			rownum++;
			writeLine(line, table, workBook, row, format, null, null, 0, false, null);
		}
		return rownum;
	}

	private int groupedLine(DataTable table, Workbook workBook,
			Sheet sheet, 
			DataFormat format, Map<String, DataDto> dataLookup, int rownum,
			FormDisplayCell[] line) {

		Set<GroupEntry> groupEntrySet = table.getGroupEntries();
		if(	(hasDataCells(line) || hasCalcCells(line)) &&
			null != groupEntrySet){
			List<FormDisplayCell> nonRepeatHeaderCells = new ArrayList<FormDisplayCell>();
			int groupLineNumber = 0;
			for(GroupEntry ge: groupEntrySet){
				if (!ge.isAggregate() &&
					!isBlank(line, table, ge)) {
					Row row  = sheet.createRow(rownum);
					rownum++;
					groupLineNumber++;
					writeLine(line, table, workBook, row, format, ge, nonRepeatHeaderCells, 0, false, groupLineNumber);
				}
			}
		}
		else {
			Row row  = sheet.createRow(rownum);
			rownum++;
			writeLine(line, table, workBook, row, format, null, null, 0, false, null);
		}
		return rownum;
	}
	

	private void groupSelectTable(TableStructure tableStructure,
			DataTable table, Workbook workBook, Sheet sheet,
			DataFormat format,
			int rownum) {
		// 17A type table
		Set<GroupEntry> groupEntrySet = table.getGroupEntries();
		for(GroupEntry ge: groupEntrySet){
			if (!isTableEmpty(tableStructure,
					table, workBook, sheet,
					ge, format,
					rownum)) {
				for (PageSection section: tableStructure.getModelPage().getPageSections()) {
					for (PageForm form: section.getPageForms()) {
						if (table.getCompany().isCompanyInType(form.getCompanyType())) {
							FormDisplayCell[][] cellGrid = form.getCellGrid();
							for(FormDisplayCell[] line: cellGrid){
								Row row  = sheet.createRow(rownum);
								rownum++;
								if(null != groupEntrySet){
				        			List<FormDisplayCell> nonRepeatHeaderCells = new ArrayList<FormDisplayCell>();
									writeLine(line, table, workBook, row, format, ge, nonRepeatHeaderCells, 0, false, null);
								}
				        		else{
				            		writeLine(line, table, workBook, row, format, null, null, 0, false, null);
				        		}
				    		}
						}
					}
				}
				sheet.createRow(rownum);
				rownum++;
				sheet.createRow(rownum);
				rownum++;
			}
		}
	}
	private boolean isTableEmpty(TableStructure tableStructure,
				DataTable table, Workbook workBook, Sheet sheet,
				GroupEntry ge, DataFormat format,
				int rownum) {
		for (PageSection section: tableStructure.getModelPage().getPageSections()) {
			for (PageForm form: section.getPageForms()) {
				if (table.getCompany().isCompanyInType(form.getCompanyType())) {
					FormDisplayCell[][] cellGrid = form.getCellGrid();
					for(FormDisplayCell[] line: cellGrid){
						if(	(hasDataCells(line) || hasCalcCells(line))){
							if (hasData(line, table, ge)) {
								return false;
							}
			    		}
					}
				}
			}
		}
		return true;
	}
	private boolean hasDataCells(FormDisplayCell[] line) {
		for(FormDisplayCell cell: line){
			if(null != cell){
				if (cell.getCellType().equals(CellType.COPYCELL) ||
					cell.getCellType().equals(CellType.INPUT)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasCalcCells(FormDisplayCell[] line) {
		for(FormDisplayCell cell: line){
			if(null != cell){
				if (cell.getCellType().equals(CellType.CALC)) {
					return true; 
				}
			}
		}
		return false;
	}
	
	private boolean hasData(FormDisplayCell[] line, DataTable table, GroupEntry ge) {
		for(FormDisplayCell cell: line){
			if(null != cell){
				if (cell.getCellType().equals(CellType.COPYCELL) ||
					cell.getCellType().equals(CellType.INPUT)) {
	    			DataKey key = new DataKey(cell.getCellContents()); // for data cells getCellContents() is the key.
	    			key.setRunTag(true);
	    			key.setCompanyId(table.getCompany().getId());
	    			if(null != ge){
	    				key.setGroupEntryId(ge.getId());
	    			}
	    			if(key.isCg()){
	    				key.setCg(false);
	    				DataDto dto = table.getDataList().get(key.getKey(true));
	    				if (null != dto &&
	    					!dto.getConfidenceGrade().isEmpty()) {
	    					return true;
	    				}
	    			}
	    			else{
	    				DataDto dto = table.getDataList().get(key.getKey(true));
	    				if (null != dto &&
	    					!dto.getValue().isEmpty()) {
	    					return true;
	    				}
	    			}
    			}
			}
		}
		return false;
	}
		
	private boolean allCalcs(FormDisplayCell[] line) {
		if (hasDataCells(line)) {
			return false;
		}
		if (hasCalcCells(line)) {
			return true;
		}
		return false;
	}
	
	private boolean isBlank(FormDisplayCell[] line, DataTable table, GroupEntry ge) {
		if (hasData(line, table, ge)) {
			return false;
		}
		if (allCalcs(line)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param line
	 * @param dataLookup
	 * @param workBook
	 * @param row
	 * @param format
	 * @param ge - null for non grouped lines
	 */
	private void writeLine(FormDisplayCell[] line, DataTable table, Workbook workBook,  Row row, DataFormat format, GroupEntry ge, List<FormDisplayCell> nonRepeatHeaderCells, int colIdx, boolean dataOnly, Integer groupLineNumber){
		for(FormDisplayCell cell: line){
			if (null != cell) {
	    		switch(cell.getCellType()){
	    		case EMPTY:
	    			colIdx++;
	    			break;
	    		case ROW_HEADING_NON_REPEAT:
	    			if (!dataOnly) {
		    			if (null != nonRepeatHeaderCells &&
		    				!nonRepeatHeaderCells.contains(cell)) {
		        			makeHeaderCell(workBook, row, colIdx, cell.getCellContents(), rowHeaderStyle, cell.isLineNo(), groupLineNumber);
		        			mergeCells(row, colIdx, cell);		
		    				nonRepeatHeaderCells.add(cell);
		    			}
		    			colIdx++;
	    			}
	    			break;
	    		case COL_HEADING:
	    			if (!dataOnly) {
	    				makeHeaderCell(workBook, row, colIdx, cell.getCellContents(), colHeaderStyle, cell.isLineNo(), groupLineNumber);
	        			mergeCells(row, colIdx, cell);		
	    				colIdx++;
	    			}
	    			break;
	    		case ROW_HEADING:
	    			if (!dataOnly) {
		    			makeHeaderCell(workBook, row, colIdx, cell.getCellContents(), rowHeaderStyle, cell.isLineNo(), groupLineNumber);
	        			mergeCells(row, colIdx, cell);		
		    			colIdx++;
	    			}
	    			break;
	    		case CALC:
	    			// DELIBERATE FALLTHROUGH
	    		case INPUT:
	    			// DELIBERATE FALLTHROUGH
	    		case COPYCELL:
	    			DataKey key = new DataKey(cell.getCellContents()); // for data cells getCellContents() is the key.
	    			key.setRunTag(true);
	    			key.setRunId(table.getDefaultRunIdMap().get(key.getRunIdInteger()));
	    			key.setCompanyId(table.getCompany().getId());
	    			if(null != ge){
	    				key.setGroupEntryId(ge.getId());
	    			}
	    			if(key.isCg()){
	    				key.setCg(false);
	    				DataDto dto = table.getDataList().get(key.getKey(true));
	    				makeCGCell(workBook, format, row, colIdx, cell, dto);
	    			}
	    			else{
	    				DataDto dto = table.getDataList().get(key.getKey(true));
	    				makeDataCell(workBook, format, row, colIdx, cell, dto);
	    			}
	    			colIdx++;
	    			break;
	    		case GROUP_ROW_HEADING:
	    			if (!dataOnly) {
		    			if(null != ge){
		    				if (cell.isLineNo()) {
		    					makeHeaderCell(workBook, row, colIdx, cell.getCellContents(), rowHeaderStyle, cell.isLineNo(), groupLineNumber);
		    				}
		    				else {
		    					makeHeaderCell(workBook, row, colIdx, ge.getDescription(), rowHeaderStyle, cell.isLineNo(), groupLineNumber);
		    				}
		        			mergeCells(row, colIdx, cell);		
		    			}
		    			colIdx++;
	    			}
	    			break;
	    		}
			}
    	}
	}

	private void mergeCells(Row row, int colIdx, FormDisplayCell cell) {
		if (cell.getColumnSpan() >1 ||
			cell.getRowSpan() >1) {
			sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum() + cell.getRowSpan()-1, colIdx, colIdx + cell.getColumnSpan()-1));  
		}
	}
	
	private void makeHeaderCell(Workbook workBook,  Row row, int cellIdx, String cellContent, CellStyle style, boolean isLineNumber, Integer groupLineNumber) {
		Cell Cell = row.createCell(cellIdx++);
		Cell.setCellType(Cell.CELL_TYPE_STRING);
		RichTextString rts; 
		if (isLineNumber &&
			null != groupLineNumber) {
			rts = creationHelper.createRichTextString(cellContent + "." + groupLineNumber);
		}
		else {
			rts = creationHelper.createRichTextString(cellContent);
		}
		Cell.setCellValue(rts);
		Cell.setCellStyle(style);
	}
	
	private void makeDataCell(Workbook workBook, DataFormat format, Row row, int cellIdx, FormDisplayCell tableCell, DataDto dto) {
		Cell cell = row.createCell(cellIdx++);
		String orig = "";
		String sVal = ""; // if no dto then make an empty cell
		if(null != dto) {
			if (dto.getRawValue()!=null) orig = "" + dto.getRawValue();
			else 						 orig = dto.getValue();
		}
		sVal = orig.replaceAll(",", "");
		
		// is it a numeric value
		try{
			double dVal = Double.parseDouble(sVal);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			if (tableCell.getCellType() == CellType.INPUT) {
				cell.setCellStyle(getInputDataNumericStyle(format, dto));
			}
			else if (tableCell.getCellType() == CellType.COPYCELL) {
				cell.setCellStyle(getCopyCellDataNumericStyle(format, dto));
			}
			else {
				cell.setCellStyle(getCalcDataNumericStyle(format, dto));
			}
			cell.setCellValue(dVal);
		}
		catch(NumberFormatException nfe){
			 // expected exception
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(creationHelper.createRichTextString(orig));
			if (tableCell.getCellType() == CellType.INPUT) {
				cell.setCellStyle(inputDataTextStyle);
			}
			else if (tableCell.getCellType() == CellType.COPYCELL) {
				cell.setCellStyle(copyCellTextStyle);
			}
			else {
				cell.setCellStyle(calcDataTextStyle);
			}
		}
	}
	
	private XSSFCellStyle getInputDataNumericStyle(DataFormat format, DataDto dataDto) {
		short formatIndex = cellFormat(format, dataDto);
		if (inputDataNumericStyleMap.containsKey(formatIndex)) {
			return inputDataNumericStyleMap.get(formatIndex);
		}
		XSSFCellStyle style = workBook.createCellStyle();
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(lightYellow);
		style.setDataFormat(formatIndex);
		inputDataNumericStyleMap.put(formatIndex, style);
		return style;
	}
	
	private XSSFCellStyle getCopyCellDataNumericStyle(DataFormat format, DataDto dataDto) {
		short formatIndex = cellFormat(format, dataDto);
		if (copyCellDataNumericStyleMap.containsKey(formatIndex)) {
			return copyCellDataNumericStyleMap.get(formatIndex);
		}
		XSSFCellStyle style = workBook.createCellStyle();
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(pink);
		style.setDataFormat(formatIndex);
		copyCellDataNumericStyleMap.put(formatIndex, style);
		return style;
	}
	
	private XSSFCellStyle getCalcDataNumericStyle(DataFormat format, DataDto dataDto) {
		short formatIndex = cellFormat(format, dataDto);
		if (calcDataNumericStyleMap.containsKey(formatIndex)) {
			return calcDataNumericStyleMap.get(formatIndex);
		}
		XSSFCellStyle style = workBook.createCellStyle();
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(lightBlue);
		style.setDataFormat(formatIndex);
		calcDataNumericStyleMap.put(formatIndex, style);
		return style;
	}
	
	private void makeCGCell(Workbook workBook, DataFormat format, Row row, int cellIdx, FormDisplayCell tableCell, DataDto dto){
		Cell cell = row.createCell(cellIdx++);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String cgVal = "";
		if(null != dto){
			cgVal = dto.getConfidenceGrade();
		}
		cell.setCellValue(creationHelper.createRichTextString(cgVal));
		if (tableCell.getCellType() == CellType.INPUT) {
			cell.setCellStyle(inputCGStyle);
		}
		else if (tableCell.getCellType() == CellType.COPYCELL) {
			cell.setCellStyle(copyCellCGStyle);
		}
		else {
			cell.setCellStyle(calcCGStyle);
		}
	}

	private short cellFormat(DataFormat format, DataDto dataDto) {
		String formatString = "#,##0"; // default format
		short formatCode = format.getFormat(formatString); 
		if(null == dataDto){
			return formatCode; // default
		}
		if (dataDto.getItemPropertiesDto().getDecimalPlaces() > 0) {
			formatString = formatString + ".";
			for (int i=1; i <= dataDto.getItemPropertiesDto().getDecimalPlaces(); i++ ) {
				formatString = formatString + "0";
			}
			formatCode = format.getFormat(formatString);
		}
		if (dataDto.getItem().getUnit().equals("%")) {
			formatCode = format.getFormat("0.00%");
		}
		return formatCode;
	}

}