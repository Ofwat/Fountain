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

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JspReportWriter implements ReportWriter {

	private StringBuilder jsp;
	private int reportId;
	private String reportName;
	private static Logger logger = LoggerFactory
			.getLogger(JspReportWriter.class);

	/**
	 * constructor parses the report structure into htmls
	 * 
	 * @param reportStructure
	 */
	public JspReportWriter(ReportStructure reportStructure) {
		jsp = new StringBuilder();
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

			File newLocalFile = new File(outLocal , "report-" + reportId + ".jsp");
			File newFile = new File(outputDir, "report-" + reportId + ".jsp");
	
			FileOutputStream localFos = new FileOutputStream(newLocalFile);
			FileOutputStream fos=new FileOutputStream(newFile);
			
			DataOutputStream localOutput = new DataOutputStream(localFos);
			DataOutputStream   output = new DataOutputStream (fos);

			String line;	
			while (null != ((line = input.readLine())))
			{
				try{
					line = line.replace("TOKEN_REPORT_TABLE", jsp.toString() + "\n");
					line = line.replace("TOKEN_REPORT_ID", "" + reportId);
					line = line.replace("TOKEN_REPORT_NAME", "" + reportName);
				}
				catch(Exception e){
					logger.error("exception writing report jsp: " + e.getMessage());
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
		jsp.append("<div class='tableHeader'>");
		jsp.append(reportName);
		jsp.append(" run on ");
		//TODO replace these JSP tags with HTML
		jsp.append("<jsp:useBean id=\"now\" class=\"java.util.Date\" scope=\"page\" />"); //TODO this is just the time the report is viewed, can be replaced with JS
		jsp.append("<fmt:formatDate value=\"${now}\" pattern=\"dd MMM yyyy HH:mm\" />"); //TODO This is jsut a formatter for the date, can replace with a JS lib.
		jsp.append("</div>");
		jsp.append("\n");
		jsp.append("<table class='ICS'>\n");
	}

	private void writeLine(ReportLine line) {
		//TODO replace the ofwat tags with static HTML equiv
		jsp.append("<ofwat:largePage>\n"); //TODO this tag can go once we  convert to HTML - no longer required see LargeSupportPageTaqg comments. 
		if(line.isGrouped()){
			jsp.append("<tr class='grouped'>");
		}
		else{
			jsp.append("<tr>");
		}
		for(ReportCell cell: line.getCells()){
			writeCell(cell);
		}
		jsp.append("</tr>\n");
		jsp.append("</ofwat:largePage>\n");
	}
	
	private void writeCell(ReportCell cell){
		CellType type = cell.getCellType();
		
		switch(type){
		case EMPTY:
			jsp.append("<td class='empty'></td>\n");
			break;
		case CALC:
			jsp.append("<td class='calc alignRight'><div id='" + cell.getCellContents() + "' class='" +cell.getCellContents()+ "'></div></td>");
			break;
		case COL_HEADING:
			jsp.append("<td class='colHeading'>"+StringEscapeUtils.escapeHtml(cell.getCellContents())+"</td>\n");
			break;
		case INPUT:
			jsp.append("<td class='input alignRight'><div id='" + cell.getCellContents() + "' class='" + cell.getCellContents() + "'></div></td>");
			break;
		case ROW_HEADING:
			jsp.append("<td class='rowHeading'>"+StringEscapeUtils.escapeHtml(cell.getCellContents())+"</td>\n");
			break;
		case ROW_HEADING_NON_REPEAT:
			jsp.append("<td class='rowHeading nonRepeat'>"+StringEscapeUtils.escapeHtml(cell.getCellContents())+"</td>\n");
			break;
		case GROUP_ROW_HEADING:
			jsp.append("<td class='rowHeading groupRowHeading'><div class='" + cell.getCellContents() + "'></div></td>\n");
			break;
		}
	}

	private void endReport(){
		jsp.append("</table>\n");
	}



	
	

}
