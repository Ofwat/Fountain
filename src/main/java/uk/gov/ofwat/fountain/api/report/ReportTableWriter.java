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

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.TeamService;
import uk.gov.ofwat.fountain.domain.AgendaPlaceHolder;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.TagPlaceHolder;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;
import uk.gov.ofwat.fountain.rest.dto.RowDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public class ReportTableWriter {

	private static final int NO_OF_LEFT_HEADER_COLS = 5;
	private ReportService reportService;
	private TeamService teamService;
	private static Logger logger = LoggerFactory.getLogger(ReportTableWriter.class);

	public TableDto write(ReportDto report) {
		return write(report, true);
	}

	public TableDto write(ReportDto report, boolean includeData) {
		logger.info("Writing TableDto for report id: " + report.getId());
		ReportStructure reportStructure = reportService.getReportStructure(report.getId());
		TableDto reportTableDto = new TableDto();
		reportTableDto.setId(report.getId());
		reportTableDto.setName(reportStructure.getReportName());
		ReportDefinition reportDefinition = reportService.getReportDefinition(report.getId());
		reportTableDto.setTeam(teamService.getTeamById(reportDefinition.getTeamId()).getName());
		reportTableDto.setUser(reportDefinition.getOwnerUser());
		
		int lineCount = writeTitleLine(reportTableDto, reportDefinition.getName(), reportDefinition.getIntervalIds().size() + NO_OF_LEFT_HEADER_COLS);
		
    	for(ReportLine line: reportStructure.getLines()){
    		if (1 == lineCount) {
    			// header line - acronym etc
				writeLine(reportTableDto, line, lineCount++, null, report, includeData, reportDefinition);
				continue;
    		}
    		else if (2 == lineCount) {
    			lineCount = writeBlankLine(reportTableDto, lineCount, reportDefinition.getIntervalIds().size() + NO_OF_LEFT_HEADER_COLS);
    		}

    		// get the group entries for this line
    		Set<GroupEntry> groupEntrySet = null;
    		for(ReportCell cell: line.getCells()){
    			if(cell.getCellType() == CellType.CALC || cell.getCellType() == CellType.INPUT){
    				String id = cell.getCellContents();
    				DataKey key = new DataKey(id);
    				// get the company
    				if (report.getCompany() != null) {
    					// interchangable company. A single company supplied with data rather than with the report template.
    					groupEntrySet = report.getGroupEntriesByCompany().get(report.getCompany().getId());
    				}
    				else{
    					// get the company for this line
    					groupEntrySet = report.getGroupEntriesByCompany().get(key.getCompanyIdInteger());
    				}
    				break;
    			}
    		}
    		if(null != groupEntrySet){
    			// group line
    			for(GroupEntry ge: groupEntrySet){
    				writeLine(reportTableDto, line, lineCount++, report.getCompany(), ge, report, includeData, reportDefinition);
    			}
    		}
    		else{
    			// ungrouped line
    			writeLine(reportTableDto, line, lineCount++, report.getCompany(), report, includeData, reportDefinition);
    		}
    	}
		logger.info("Completed writing TableDto for report id: " + report.getId());
		return reportTableDto;
	}
	
	private int writeTitleLine(TableDto tableDto, String reportName, int noOfCols) {
		int rowNumber=0;
		RowDto rowDto = new RowDto();
		for (int colNumber=0; colNumber < noOfCols; colNumber++){
			CellDto cellDto = blankCell(rowNumber, colNumber);
			if (2 == colNumber) {
				cellDto.setStyle("table_header");
				cellDto.setValue(reportName);
			}
			else {
				cellDto.setStyle("text");
				cellDto.setValue("");
			}
			rowDto.getCells().add(cellDto);
		}

		tableDto.getRows().add(rowDto);
		return ++rowNumber;
	}

	private int writeBlankLine(TableDto tableDto, int rowNumber, int noOfCols) {
		RowDto rowDto = new RowDto();
		for (int colNumber=0; colNumber < noOfCols; colNumber++){
			CellDto cellDto = blankCell(rowNumber, colNumber);
			rowDto.getCells().add(cellDto);
		}

		tableDto.getRows().add(rowDto);
		return ++rowNumber;
	}

	private CellDto blankCell(int rowNumber, int colNumber) {
		CellDto cellDto;
		cellDto = new CellDto();
		cellDto.setRow(rowNumber);
		cellDto.setCol(colNumber);
		cellDto.setDecimalPlaces(0);
		cellDto.setKey("");
		cellDto.setErrorFlag(false);
		cellDto.setErrorText("");
		cellDto.setCellFormat("text");
		return cellDto;
	}

	
	
	private void writeLine(TableDto tableDto, ReportLine line, int lineCount, Company company, ReportDto report, Boolean includeData, ReportDefinition reportDefinition) {
		writeLine(tableDto, line, lineCount++, company, null, report, includeData, reportDefinition);
	}
	private void writeLine(TableDto tableDto, ReportLine line, int lineCount, Company company, GroupEntry ge, ReportDto report, Boolean includeData, ReportDefinition reportDefinition) {
		int cellCount = 0;
		RowDto rowDto = new RowDto();
		for(ReportCell cell: line.getCells()){
			writeCell(tableDto, rowDto, cell, lineCount, cellCount, company, ge, report, includeData, reportDefinition);
			cellCount++;
		}
		tableDto.getRows().add(rowDto);
	}
	
	private void writeCell(TableDto tableDto, RowDto rowDto, ReportCell cell, int lineCount, int cellCount, Company company, GroupEntry ge, ReportDto report, Boolean includeData, ReportDefinition reportDefinition){
		Map<Integer, Set<GroupEntry>> groupEntries = report.getGroupEntriesByCompany();
		CellDto cellDto = new CellDto();
		cellDto.setRow(lineCount);
		cellDto.setCol(cellCount);
		cellDto.setDecimalPlaces(0);
		cellDto.setKey("");
		cellDto.setErrorFlag(false);
		cellDto.setErrorText("");

		DataDto dataDto = null;
		CellType cellType = cell.getCellType();
		cellDto.setCellType(cellType);
		switch(cellType){
			case EMPTY:
				if (1 == cellDto.getRow()) {
					switch (cellCount) {
					case 0: cellDto.setValue("Acronym"); break;
					case 1: cellDto.setValue("Reference"); break;
					case 2: cellDto.setValue("Item description"); break;
					case 3: cellDto.setValue("Unit"); break;
					case 4: cellDto.setValue("Model"); break;
					default: cellDto.setValue(getHeaderCellText(cell.getCellContents(), report)); break;
					}
				}
				else if (cellDto.getRow() > 1) {
					// This is to reinstate left primary axis text. It is removed in ReportStructure. A better solution would be to stop ReportStructure removing it and have the writers remove it if they want too. 
					RowDto previousRowDto = tableDto.getRows().get(cellDto.getRow()-1);
					CellDto previousRowCellDto = previousRowDto.getCells().get(cellDto.getCol());
					if(previousRowCellDto.getCellType() == CellType.ROW_HEADING_NON_REPEAT) {
						cellDto.setValue(getHeaderCellText(previousRowCellDto.getValue(), report));
						cellDto.setCellType(CellType.ROW_HEADING_NON_REPEAT);
					}
				} 
				else {
					cellDto.setCellFormat("text");
					cellDto.setStyle("text");
				}
				break;
			case CALC:
				dataDto = report.getDataList().get(getKeyWithGroup(cell, company, ge, groupEntries, report, reportDefinition));
				cellDto.setDataType("calc");
				if (null == dataDto) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Item error. There is a problem with this item. This is probably a problem with the item's runtag or group.");
					break;
				}
				cellDto.setStyle("calc");
				cellDto.setDataFormat(cellFormat(dataDto));
				if (dataDto.getItem().getUnit().equals("%")) {
					cellDto.setCellFormat("percentage");
				}
				else {
					cellDto.setCellFormat("numeric");
				}
				if (includeData) {
					if (dataDto.getRawValue() == null) {
						cellDto.setValue("");
					}
					else {
						cellDto.setValue("" + dataDto.getRawValue());
					}
				}
				cellDto.setDecimalPlaces(dataDto.getItemPropertiesDto().getDecimalPlaces());
				cellDto.setKey(dataDto.getIdentifier());
				break;
			case INPUT:
				dataDto = report.getDataList().get(getKeyWithGroup(cell, company, ge, groupEntries, report, reportDefinition));
				cellDto.setDataType("input");
				if (null == dataDto) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Item error. There is a problem with this item. This is probably a problem with either the item's runtag or group.");
					break;
				}
				cellDto.setStyle("input");
				cellDto.setDataFormat(cellFormat(dataDto));
				if (dataDto.getItem().getUnit().equals("%")) {
					cellDto.setCellFormat("percentage");
				}
				else if (dataDto.getItem().getUnit().equalsIgnoreCase("text")) {
					cellDto.setCellFormat("text");
					cellDto.setDataFormat("");
				}
				else {
					cellDto.setCellFormat("numeric");
				}
				if (includeData) {
					if (dataDto.getRawValue() == null) {
						cellDto.setValue(dataDto.getValue());
					}
					else {
						cellDto.setValue("" + dataDto.getRawValue());
					}
				}
				cellDto.setDecimalPlaces(dataDto.getItemPropertiesDto().getDecimalPlaces());
				cellDto.setKey(dataDto.getIdentifier());
				break;
			case COL_HEADING:
				cellDto.setCellFormat("text");
				cellDto.setStyle("col_header");
				cellDto.setValue(getHeaderCellText(cell.getCellContents(), report));
				break;
			case ROW_HEADING:
				// deliberate fall through.
			case ROW_HEADING_NON_REPEAT:
				cellDto.setCellFormat("text");
				cellDto.setStyle("row_header");
				cellDto.setValue(getHeaderCellText(cell.getCellContents(), report));
				break;
			case GROUP_ROW_HEADING:
    			if( null != ge &&
					!ge.getDescription().equals("NON GROUPED ITEM")) {
						cellDto.setCellFormat("text");
						cellDto.setStyle("text");
						cellDto.setValue(getHeaderCellText(ge.getDescription(), report));
				}
				break;
		}
		
		rowDto.getCells().add(cellDto);
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
	
	private String getKeyWithGroup(ReportCell cell, Company company, GroupEntry ge, Map<Integer, Set<GroupEntry>> groupEntries, ReportDto report, ReportDefinition reportDefinition) {
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

		return key.getKey(true);
	}

	public ReportService getReportService() {
		return reportService;
	}
	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	private String cellFormat(DataDto dataDto) {
		String formatString = "#,##0"; // default format
		if(null == dataDto){
			return formatString; // default
		}
		if (dataDto.getItemPropertiesDto().getDecimalPlaces() > 0) {
			formatString = formatString + ".";
			for (int i=1; i <= dataDto.getItemPropertiesDto().getDecimalPlaces(); i++ ) {
				formatString = formatString + "0";
			}
		}
		if (dataDto.getItem().getUnit().equals("%")) {
			formatString  = "0.00%";
		}
		return formatString;
	}


}
