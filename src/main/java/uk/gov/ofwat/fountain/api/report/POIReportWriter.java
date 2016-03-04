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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.AgendaPlaceHolder;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.TagPlaceHolder;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;

public class POIReportWriter {

	private ReportService reportService;

	XSSFWorkbook workBook = null;
	CreationHelper creationHelper = null; 

	XSSFCellStyle rowHeaderStyle = null;
	XSSFCellStyle colHeaderStyle = null;
	XSSFCellStyle inputDataNumericStyle = null;
	XSSFCellStyle calcDataNumericStyle = null;
	XSSFCellStyle inputDataTextStyle = null;
	XSSFCellStyle calcDataTextStyle = null;
	XSSFCellStyle inputCGStyle = null;
	XSSFCellStyle calcCGStyle = null;
	private Map<Short, XSSFCellStyle> inputDataNumericStyleMap = null;
	private Map<Short, XSSFCellStyle> calcDataNumericStyleMap = null;
	private XSSFColor yellow = null;;
	private XSSFColor lightYellow = null;
	private XSSFColor lightBlue = null;
	
	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	/**
	 * Create an excel table version of the data table and write it to the stream
	 * @param report
	 * @param os
	 * @throws IOException 
	 */
	public synchronized void streamTableToExcel(ReportDto report, OutputStream os) throws IOException{
		workBook = new XSSFWorkbook();
		creationHelper = workBook.getCreationHelper();
		
		ReportStructure reportStructure = reportService.getReportStructure(report.getId(), report.isShowAllHeaders());
		renderReport(reportStructure, report);
		
		workBook.write(os);
	}
	
	private void renderReport(ReportStructure reportStructure, ReportDto report){
		
		Sheet sheet = workBook.createSheet();
		Map<Integer, Set<GroupEntry>> groupEntries = report.getGroupEntriesByCompany();
		inputDataNumericStyleMap = new HashMap<Short, XSSFCellStyle>();
		calcDataNumericStyleMap = new HashMap<Short, XSSFCellStyle>();

        yellow = new XSSFColor(new java.awt.Color(255,255,0));
	    lightYellow = new XSSFColor(new java.awt.Color(255,255,224));
	    lightBlue = new XSSFColor(new java.awt.Color(224,255,255));
        
	    // Styles
		// Row header style
	    rowHeaderStyle = workBook.createCellStyle();
		// Col header style
		colHeaderStyle = workBook.createCellStyle();
		colHeaderStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		colHeaderStyle.setFillForegroundColor(yellow); 
		Font colHeaderFont = workBook.createFont();
		colHeaderFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		colHeaderStyle.setFont(colHeaderFont);
        // Input numeric Data cell style
		inputDataNumericStyle = workBook.createCellStyle();
		inputDataNumericStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		inputDataNumericStyle.setFillForegroundColor(lightYellow);
        // Calc numeric Data cell style
		calcDataNumericStyle = workBook.createCellStyle();
		calcDataNumericStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		calcDataNumericStyle.setFillForegroundColor(lightBlue);
		// Input text data cell style
		inputDataTextStyle = workBook.createCellStyle();
		inputDataTextStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		inputDataTextStyle.setFillForegroundColor(lightYellow);
		// Calc text data cell style
		calcDataTextStyle = workBook.createCellStyle();
		calcDataTextStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		calcDataTextStyle.setFillForegroundColor(lightBlue);
		// Input CG style
		inputCGStyle = workBook.createCellStyle();
		inputCGStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		inputCGStyle.setFillForegroundColor(lightYellow);
		// Calc CG style
		calcCGStyle = workBook.createCellStyle();
		calcCGStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		calcCGStyle.setFillForegroundColor(lightBlue);
	    
	    // data format
        DataFormat format = workBook.createDataFormat();
        
        int rownum = 1; // starting point
        Row infoRow  = sheet.createRow(rownum);
        Cell titleCell = infoRow.createCell(2);
        titleCell.setCellType(Cell.CELL_TYPE_STRING);
		String title = reportStructure.getReportName();
		RichTextString rts = creationHelper.createRichTextString(title);
		titleCell.setCellValue(rts);
		XSSFCellStyle style = workBook.createCellStyle(); 
		Font font = workBook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setUnderline(Font.U_SINGLE);
		style.setFont(font);
		titleCell.setCellStyle(style);
		
		Cell dateCell = infoRow.createCell(4);
		dateCell.setCellType(Cell.CELL_TYPE_STRING);
		String DATE_FORMAT = "dd MMM yyyy H:mm";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    Calendar c1 = Calendar.getInstance(); // today
	    String today = sdf.format(c1.getTime());
		RichTextString dateRts = creationHelper.createRichTextString("Run on " + today);
		dateCell.setCellValue(dateRts);
		dateCell.setCellStyle(style);

		rownum++;
	    rownum++;
        
		ReportDefinition reportDefinition = reportService.getReportDefinition(report.getId());

		if (reportStructure.isCompanyAcrossTop()) {
        	for(ReportLine line: reportStructure.getLines()){
        		// get the group entries for this line
    			// ungrouped line
    			Row row  = sheet.createRow(rownum);
    			rownum++;
    			List<ReportCell> nonRepeatHeaderCells = new ArrayList<ReportCell>();
    			writeLine(line, report, workBook, row, format, null, nonRepeatHeaderCells, groupEntries, reportDefinition);
        	}
        }
        else {
        	for(ReportLine line: reportStructure.getLines()){
        		// get the group entries for this line
        		Set<GroupEntry> groupEntrySet = null;
        		for(ReportCell cell: line.getCells()){
        			if(cell.getCellType() == CellType.CALC || cell.getCellType() == CellType.INPUT){
        				String id = cell.getCellContents();
        				DataKey key = new DataKey(id);
        				// get the company
        				if (report.getCompany() != null) {
        					// interchangable company. A single company supplied with data rather than with the report template.
        					groupEntrySet = groupEntries.get(report.getCompany().getId());
        				}
        				else{
        					// get the company for this line
        					groupEntrySet = groupEntries.get(key.getCompanyIdInteger());
        				}
        				break;
        			}
        		}
        		if(null != groupEntrySet){
        			// group line
        			List<ReportCell> nonRepeatHeaderCells = new ArrayList<ReportCell>();
        			for(GroupEntry ge: groupEntrySet){
        				Row row  = sheet.createRow(rownum);
        				rownum++;
        				writeLine(line, report, workBook, row, format, ge, nonRepeatHeaderCells, null, reportDefinition);
        			}
        		}
        		else{
        			// ungrouped line
        			Row row  = sheet.createRow(rownum);
        			rownum++;
        			writeLine(line, report, workBook, row, format, null, null, null, reportDefinition);
        		}
        	}
        }
        
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
	private void writeLine(ReportLine line, ReportDto report, Workbook workBook,  Row row, DataFormat format, GroupEntry ge, List<ReportCell> nonRepeatHeaderCells, Map<Integer, Set<GroupEntry>> groupEntries, ReportDefinition reportDefinition){
		int colIdx = 0;
    	for(ReportCell cell: line.getCells()){
    		switch(cell.getCellType()){
    		case EMPTY:
    			colIdx++;
    			break;
    		case ROW_HEADING_NON_REPEAT:
    			if (null != nonRepeatHeaderCells &&
    				!nonRepeatHeaderCells.contains(cell)) {
        			makeHeaderCell(workBook, row, colIdx, getHeaderCellText(cell.getCellContents(), report), rowHeaderStyle);
    				nonRepeatHeaderCells.add(cell);
    			}
    			colIdx++;
    			break;
    		case COL_HEADING:
    			makeHeaderCell(workBook, row, colIdx, getHeaderCellText(cell.getCellContents(), report), colHeaderStyle);
    			colIdx++;
    			break;
    		case ROW_HEADING:
    			makeHeaderCell(workBook, row, colIdx, getHeaderCellText(cell.getCellContents(), report), rowHeaderStyle);
    			colIdx++;
    			break;
    		case CALC:
    			// DELIBERATE FALLTHROUGH
    		case INPUT:
    			DataKey key = new DataKey(cell.getCellContents()); // for data cells getCellContents() is the key.
				if (report.getCompany() != null) {
					// interchangable company. A single company supplied with data rather than with the report template.
					key.setCompanyId(report.getCompany().getId());
				}
    			if(null == ge &&
    			   null != groupEntries){
    				Set<GroupEntry> groupEntrySet = groupEntries.get(key.getCompanyIdInteger());
    				for(GroupEntry groupEntry: groupEntrySet){
        				key.setGroupEntryId(groupEntry.getId());
    				}
    			}
    			if(null != ge){
    				key.setGroupEntryId(ge.getId());
    			}
    			
    			if (cell.isAddRunTagToKey()) {
    				key.setRunId("" + report.getRun().getId());
    				key.setTagId("" + report.getTagId());
    				key.setRunTag(true);
    			}
    			else if (key.getRunId() != null &&
    					!key.getRunId().equals("0")) {
    				key.setRunTag(true);
    			}

    			if (!key.isRunTag()) {
    				throw new RuntimeException("You said you wanted to know if a key had no run.");
    			}

    			// Replace defult run if needed.
    			if (null != report.getDefaultRunIdMap() &&
    				null != report.getDefaultRunIdMap().get(key.getRunIdInteger())) {
    				key.setRunId(report.getDefaultRunIdMap().get(key.getRunIdInteger()));
    			}
    			
    			if(reportDefinition.isInterchangeableCompany() && !reportDefinition.isInterchangeableRun()){
    				//Selecting just the company at runtime
    				if (report.getTagMap().getTagMapType().equalsIgnoreCase("tagTagMap")) {
    					Integer tagId = (Integer)report.getTagMap().getTags().get(key.getTagIdInteger());
    					key.setTagId("" + tagId);
    				}
    			} 
    			else if(!reportDefinition.isInterchangeableCompany() && reportDefinition.isInterchangeableRun()){
    				//Selecting just the tag at runtime
    				if (report.getTagMap().getTagMapType().equalsIgnoreCase("companyTagMap")) {
    					Integer tagId = (Integer)report.getTagMap().getTags().get(key.getCompanyIdInteger());
    					key.setTagId("" + tagId);
    				}
    			}
    			
    			if(key.isCg()){
    				key.setCg(false);
    				DataDto dto = report.getDataList().get(key.getKey(true));
    				makeCGCell(workBook, format, row, colIdx, cell, dto);
    			}
    			else{
    				DataDto dto = report.getDataList().get(key.getKey(true));
    				makeDataCell(workBook, format, row, colIdx, cell, dto);
    			}
    			colIdx++;
    			break;
    		case GROUP_ROW_HEADING:
    			if( null != ge &&
    				!ge.getDescription().equals("NON GROUPED ITEM")) {
					makeHeaderCell(workBook, row, colIdx, getHeaderCellText(ge.getDescription(), report), rowHeaderStyle);
    			}
    			colIdx++;
    			break;
    		}
    	}
	}
	
	private String getHeaderCellText(String cellContent, ReportDto report) {
		if (cellContent.equalsIgnoreCase(CompanyPlaceHolder.COMPANY_PLACE_HOLDER.getCode())) {
			return report.getCompany().getCode();
		}
		if (cellContent.equalsIgnoreCase(CompanyPlaceHolder.COMPANY_PLACE_HOLDER.getName())) {
			return report.getCompany().getName();
		}
		if (cellContent.equals(AgendaPlaceHolder.AGENDA_PLACE_HOLDER.getName())) {
			return report.getRun().getAgenda().getName();
		}
		if (cellContent.equals(AgendaPlaceHolder.AGENDA_PLACE_HOLDER.getCode())) {
			return report.getRun().getAgenda().getCode();
		}
		if (cellContent.equals(RunPlaceHolder.RUN_PLACE_HOLDER.getName())) {
			return report.getRun().getName();
		}
		if (cellContent.equals(RunPlaceHolder.RUN_PLACE_HOLDER.getDescription())) {
			return report.getRun().getDescription();
		}
		if (cellContent.equals(TagPlaceHolder.TAG_PLACE_HOLDER.getDisplayName())) {
			return report.getTag().getDisplayName();
		}
		return cellContent;
	}
	
	private void makeHeaderCell(Workbook workBook,  Row row, int cellIdx, String cellText, XSSFCellStyle style) {
		Cell cell = row.createCell(cellIdx++);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		RichTextString rts = creationHelper.createRichTextString(cellText);
		cell.setCellValue(rts);
		cell.setCellStyle(style);
	}
	
	private void makeDataCell(Workbook workBook, DataFormat format, Row row, int cellIdx, ReportCell reportCell, DataDto dto) {
		Cell cell = row.createCell(cellIdx++);
		String orig = "";
		String sVal = ""; // if no dto then make an empty cell
		if(null != dto){
			if (dto.getRawValue()!=null) 	orig = "" + dto.getRawValue();
			else if (dto.getValue()!=null)	orig = dto.getValue();
		}
		sVal = orig.replaceAll(",", "");
		
		// is it a numeric value
		try{
			double dVal = Double.parseDouble(sVal);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(dVal);
			if (reportCell.getCellType() == CellType.INPUT) {
				cell.setCellStyle(getInputDataNumericStyle(format, dto));
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
			if (reportCell.getCellType() == CellType.INPUT) {
				cell.setCellStyle(inputDataTextStyle);
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
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(lightYellow);
		style.setDataFormat(formatIndex);
		inputDataNumericStyleMap.put(formatIndex, style);
		return style;
	}
	
	private XSSFCellStyle getCalcDataNumericStyle(DataFormat format, DataDto dataDto) {
		short formatIndex = cellFormat(format, dataDto);
		if (calcDataNumericStyleMap.containsKey(formatIndex)) {
			return calcDataNumericStyleMap.get(formatIndex);
		}
		XSSFCellStyle style = workBook.createCellStyle();
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(lightBlue);
		style.setDataFormat(formatIndex);
		calcDataNumericStyleMap.put(formatIndex, style);
		return style;
	}

	private void makeCGCell(Workbook workBook, DataFormat format, Row row, int cellIdx, ReportCell reportCell, DataDto dto){
		Cell cell = row.createCell(cellIdx++);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		String cgVal = "";
		if(null != dto){
			cgVal = dto.getConfidenceGrade();
		}
		cell.setCellValue(creationHelper.createRichTextString(cgVal));
		if (reportCell.getCellType() == CellType.INPUT) {
			cell.setCellStyle(inputCGStyle);
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
