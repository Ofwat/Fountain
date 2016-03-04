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

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.domain.AgendaPlaceHolder;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.RunPlaceHolder;
import uk.gov.ofwat.fountain.domain.TagPlaceHolder;
public class HtmlReportWriter implements ReportWriter {

	private StringBuilder html;
	private int reportId;
	private String reportName;
	private static Logger logger = LoggerFactory
			.getLogger(HtmlReportWriter.class);

	
	private int groupedRowCount = 0;
	private Boolean startStriping = false;
	private int rowCount = 0;
	private Boolean stripedRow = true;
	private Boolean lastRowHeaderCell = false;
	
	/**
	 * constructor parses the report structure into html
	 * 
	 * @param reportStructure
	 */
	public HtmlReportWriter(ReportStructure reportStructure) {
		html = new StringBuilder();
		reportId = reportStructure.getReportId();
		reportName = reportStructure.getReportName();
		startReport();
		for(ReportLine line: reportStructure.getLines()){
			writeLine(line);
		}
		endReport();
	}
	
	public void generateReportFile(File reportTemplate, File outputDir, String localDir ) {

		try{
			FileInputStream	fis=new FileInputStream(reportTemplate);
			BufferedReader input = new BufferedReader(new InputStreamReader(fis));
			File outLocal = new File(localDir);
			
			// create the output directory if it doesn't exist, the model code.
			if (!outLocal.exists()){
				outLocal.mkdir();
			}
		
			if (!outputDir.exists()){
				outputDir.mkdir();
			}

			File newLocalFile = new File(outLocal , "report-" + reportId + ".html");
			File newFile = new File(outputDir, "report-" + reportId + ".html");
	
			FileOutputStream localFos = new FileOutputStream(newLocalFile);
			FileOutputStream fos=new FileOutputStream(newFile);
			
			DataOutputStream localOutput = new DataOutputStream(localFos);
			DataOutputStream   output = new DataOutputStream (fos);

			String line;	
			while (null != ((line = input.readLine())))
			{
				try{
					line = line.replace("TOKEN_REPORT_TABLE", html.toString() + "\n");
					line = line.replace("TOKEN_REPORT_ID", "" + reportId);
					line = line.replace("TOKEN_REPORT_NAME", "" + reportName);
				}
				catch(Exception e){
					logger.error("exception writing report html: " + e.getMessage());
				}
				output.writeBytes(line);
				localOutput.writeBytes(line);
			}
	    }
        catch (IOException ioe)
        {
            logger.error("I/O Error - " + ioe);
        }
		
	}

	private void startReport() {
		html.append("<div class='tableHeader'>");
		html.append(reportName);
		html.append(" run on ");
		html.append("<span class='timeAndDate'></span>");

		html.append("</div>");
		html.append("\n");
		html.append("<table class='pure-table pure-table-no-border ICS_MARKER'>\n");
		//TODO need to add a table header tag here <thead>, and close it after the headers have been rendered. 
	}
	
	/**
	 * Check to see if this is a row which contains data i.e it is not the top part of the report. 
	 * @param reportLine
	 * @return Boolean Is it in the body of the report.
	 */
	private boolean isDataRow(ReportLine reportLine){
		//int rowSize = reportLine.getCells().size();
		//Need to iterate over all the cells in the line to see if any have rowHeading
		for(ReportCell cell : reportLine.getCells()){
			if(((cell.getCellType() == CellType.ROW_HEADING) || (cell.getCellType() == CellType.ROW_HEADING_NON_REPEAT) || (cell.getCellType() == CellType.GROUP_ROW_HEADING))){
				return true;
			}
		}
		return false;
	}

	/**
	 * Check to see if this is a row which contains Header data i.e it is the top part of the report. 
	 * @param reportLine
	 * @return Boolean Is it in the body of the report.
	 */
	private boolean isHeaderRow(ReportLine reportLine){
		//int rowSize = reportLine.getCells().size();
		//Need to iterate over all the cells in the line to see if any have rowHeading
		for(ReportCell cell : reportLine.getCells()){
			if(cell.getCellType() == CellType.COL_HEADING){
				return true;
			}
		}
		return false;
	}	
	
	private void writeLine(ReportLine line) {
		
		String stripeClassStr = "";
		String headerClassString = "";
		
		if(isHeaderRow(line)){
			headerClassString = " colHeaderRow";
		}
		
		//Check to see if we have a row that is part of the data.
		if(isDataRow(line)){
			
			//We need to add an id to the last <td> which has the class 'rowHeading' of the first data row so that we can line
			//the floating table header up correctly. 
			startStriping = true;
			if(stripedRow){
				stripeClassStr = " pure-table-odd";
			}
			//Flip the flag.
			stripedRow = !stripedRow;
		}else{
			startStriping = false;
		}
				
		if(line.isGrouped()){
			html.append("<tr class='grouped" + stripeClassStr + headerClassString + "'>");
			groupedRowCount++;
		}
		else{
			html.append("<tr class='" + stripeClassStr + headerClassString +"'>");
		} 
		
		/*
		for(ReportCell cell: line.getCells()){
			writeCell(cell);
		}
		*/
		List<ReportCell> cells = line.getCells();
		for(int i=0;i<cells.size();i++){
			ReportCell cell = cells.get(i); 
			//Are we at the last row heading type cell? - give it a class.
			lastRowHeaderCell = false;
			if(isRowHeaderCell(cell) && (cells.size() > i+1)){
				lastRowHeaderCell = isLastRowHeaderCell(cell, cells.get(i+1));
			}
			writeCell(cell);
		}
		
		
		html.append("</tr>\n");
		
	}
	
	private Boolean isLastRowHeaderCell(ReportCell cell, ReportCell nextCell){
		if((cell != null) && (nextCell != null)){
			if(isRowHeaderCell(cell) && !(isRowHeaderCell(nextCell))){
				return true;
			}
		}
		return false;
	}
		
	private Boolean isRowHeaderCell(ReportCell cell){
		CellType cellType = cell.getCellType();
		if((cellType == CellType.ROW_HEADING) || (cellType == CellType.ROW_HEADING_NON_REPEAT) || (cellType == CellType.GROUP_ROW_HEADING)){
			return true;
		}
		return false;
	}
	
	private void writeCell(ReportCell cell){
		CellType type = cell.getCellType();
		String css = "";
		if(lastRowHeaderCell){ //Calculated from the row contents above. This lets us identify the 'data' cells from the last rowHeadercell in the client. 
			css = "lastRowHeaderCell ";
		}
		
		switch(type){
		case EMPTY:
			html.append("<td class='empty'></td>\n");
			break;
		case CALC:
			html.append("<td class='calc alignRight" + (cell.isAddRunTagToKey() ? " addRunTagToKey" : "") + "'><div id='" + cell.getCellContents() + "' class='" +cell.getCellContents() + "'></div></td>");
			break;
		case COL_HEADING:
			html.append(getHeaderCell(cell.getCellContents(), "colHeading"));
			break;
		case INPUT:
			html.append("<td class='input alignRight" + (cell.isAddRunTagToKey() ? " addRunTagToKey" : "") + "'><div id='" + cell.getCellContents() + "' class='" + cell.getCellContents() + "'></div></td>");
			break;
		case ROW_HEADING:
			css = css + "rowHeading ";
			html.append(getHeaderCell(cell.getCellContents(), css));
			break;
		case ROW_HEADING_NON_REPEAT:
			css = css + "rowHeading nonRepeat ";
			html.append(getHeaderCell(cell.getCellContents(), css));
			break;
		case GROUP_ROW_HEADING:
			css = css + "rowHeading groupRowHeading ";
			html.append(getHeaderCell(cell.getCellContents(), css));
			break;
		}
	}

	private String getHeaderCell(String cellContent, String cssClasses) {
		if (cellContent.equals(CompanyPlaceHolder.COMPANY_PLACE_HOLDER.getName())) {
			return "<td class='" + cssClasses + " companyNamePlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(CompanyPlaceHolder.COMPANY_PLACE_HOLDER.getCode())) {
			return "<td class='" + cssClasses + " companyAcronymPlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(AgendaPlaceHolder.AGENDA_PLACE_HOLDER.getName())) {
			return "<td class='" + cssClasses + " agendaNamePlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(AgendaPlaceHolder.AGENDA_PLACE_HOLDER.getCode())) {
			return "<td class='" + cssClasses + " agendaCodePlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(RunPlaceHolder.RUN_PLACE_HOLDER.getName())) {
			return "<td class='" + cssClasses + " runNamePlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(RunPlaceHolder.RUN_PLACE_HOLDER.getDescription())) {
			return "<td class='" + cssClasses + " runDescriptionPlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		if (cellContent.equals(TagPlaceHolder.TAG_PLACE_HOLDER.getDisplayName())) {
			return "<td class='" + cssClasses + " tagNamePlaceHolder'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
		}
		return "<td class='" + cssClasses + "'>"+StringEscapeUtils.escapeHtml(cellContent)+"</td>\n";
	}

	private void endReport(){
		html.append("<span id='endReport'></span></table>\n");
	}

}
