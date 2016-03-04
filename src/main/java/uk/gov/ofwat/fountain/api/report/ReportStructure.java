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

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.run.Run;



/**
 * Java representation of the report that knows where the axis lies and is 
 * capable of populating data / expanding rows.
 * 
 * Writers can iterate over the lines and cells and convert straight to jsp / poi / whatever
 * specific cells they like.
 */
public class ReportStructure implements Entity{

	private int reportId;
	private String reportName;
	private List<ReportLine>lines;
	private boolean cg;
	private boolean itemDesc;
	private boolean unit;
	private boolean model;
	private boolean fullName;
	private boolean acronym;
	private boolean bonCode;
	private boolean runName;
	private boolean runDesc;
	private boolean tagDisplayName;
	private boolean agendaName;
	private boolean agendaCode;
	
	private ReportAxis leftPrimary;
	private ReportAxis leftSecondary;
	private ReportAxis leftTertiary;
	
	private ReportAxis topPrimary;
	private ReportAxis topSecondary;
	private ReportAxis topTertiary;
	
	private List<ReportItem> reportItems;
	private List<Interval> intervals;
	private List<Company> companies;
	private List<RunTag> runTags;
	
	// we'll build this up as we work across the top headings, filling in 1 or 2 of the 
	// elements depending on what the top axis is
	private List<Triplet> horizontalLocii = new ArrayList<Triplet>();

	private Group group;
	private boolean showAllHeadings;
   


	public boolean isRunName() {
		return runName;
	}
	public void setRunName(boolean runName) {
		this.runName = runName;
	}
	public boolean isRunDesc() {
		return runDesc;
	}
	public void setRunDesc(boolean runDesc) {
		this.runDesc = runDesc;
	}
	public boolean isTagDisplayName() {
		return tagDisplayName;
	}
	public void setTagDisplayName(boolean tagDisplayName) {
		this.tagDisplayName = tagDisplayName;
	}
	public ReportAxis getLeftPrimary() {
		return leftPrimary;
	}
	public ReportAxis getLeftSecondary() {
		return leftSecondary;
	}
	public ReportAxis getLeftTertiary() {
		return leftTertiary;
	}
	public ReportAxis getTopPrimary() {
		return topPrimary;
	}
	public ReportAxis getTopSecondary() {
		return topSecondary;
	}
	public ReportAxis getTopTertiary() {
		return topTertiary;
	}
	public List<ReportItem> getReportItems() {
		return reportItems;
	}
	public List<Interval> getIntervals() {
		return intervals;
	}
	public List<Company> getCompanies() {
		return companies;
	}
	public List<RunTag> getRunTags() {
		return runTags;
	}
	public List<Triplet> getHorizontalLocii() {
		return horizontalLocii;
	}
	public int getReportId() {
		return reportId;
	}
	public List<ReportLine> getLines() {
		if(null == lines){
			renderReport();
		}
		return lines;
	}
	public void setLines(List<ReportLine> lines) {
		this.lines = lines;
	}
	public boolean isCg() {
		return cg;
	}
	public void setCg(boolean cg) {
		this.cg = cg;
	}
	public boolean isBonCode() {
		return bonCode;
	}
	public void setBonCode(boolean bonCode) {
		this.bonCode = bonCode;
	}
	public boolean isItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(boolean itemDesc) {
		this.itemDesc = itemDesc;
	}
	public boolean isUnit() {
		return unit;
	}
	public void setUnit(boolean unit) {
		this.unit = unit;
	}
	public boolean isModel() {
		return model;
	}
	public void setModel(boolean model) {
		this.model = model;
	}
	public boolean isFullName() {
		return fullName;
	}
	public void setFullName(boolean fullName) {
		this.fullName = fullName;
	}
	public boolean isAcronym() {
		return acronym;
	}
	public void setAcronym(boolean acronym) {
		this.acronym = acronym;
	}
	public boolean isShowAllHeadings() {
		return showAllHeadings;
	}

	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
    public void setReportItems(List<ReportItem> reportItems) {
		this.reportItems = reportItems;
	}
	public void setIntervals(List<Interval> intervals) {
		this.intervals = intervals;
	}
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	public void setRunTags(List<RunTag> runTags) {
		this.runTags = runTags;
	}

	
	public boolean isAgendaName() {
		return agendaName;
	}
	public void setAgendaName(boolean agendaName) {
		this.agendaName = agendaName;
	}
	public boolean isAgendaCode() {
		return agendaCode;
	}
	public void setAgendaCode(boolean agendaCode) {
		this.agendaCode = agendaCode;
	}
	public void setLeftPrimary(ReportAxis leftPrimary) {
		this.leftPrimary = leftPrimary;
	}
	public void setLeftSecondary(ReportAxis leftSecondary) {
		this.leftSecondary = leftSecondary;
	}
	public void setLeftTertiary(ReportAxis leftTertiary) {
		this.leftTertiary = leftTertiary;
	}
	public void setTopPrimary(ReportAxis topPrimary) {
		this.topPrimary = topPrimary;
	}
	public void setTopSecondary(ReportAxis topSecondary) {
		this.topSecondary = topSecondary;
	}
	public void setTopTertiary(ReportAxis topTertiary) {
		this.topTertiary = topTertiary;
	}
	
	public void setShowAllHeadings(boolean showAllHeadings) {
		this.showAllHeadings = showAllHeadings;
	}
	/**
	 * construct the lines and cells
	 */
	private void renderReport(){
		// make sure all the elements are set
		checkReportContents();
		lines = new ArrayList<ReportLine>();
		
		// work out what the spans are for the top headings
		int topPrimarySpan = 1;
		int topSecondarySpan = 1;
		int topTertiarySpan = 1;
		if (null != topSecondary) {
			topPrimarySpan = topSecondary.getAxisSize();
			if (null != topTertiary) {
				topPrimarySpan = topPrimarySpan * topTertiary.getAxisSize();
				topSecondarySpan = topTertiary.getAxisSize();
			}
		}
		
		// work out what the repeats are for the top headings
		int topPrimaryRepeat = 0;
		int topSecondaryRepeat = 0;
		int topTertiaryRepeat = 0;
		if (null != topSecondary) {
			topSecondaryRepeat = topPrimary.getAxisSize()-1;
			if (null != topTertiary) {
				topTertiaryRepeat = ((topSecondaryRepeat+1) * topSecondary.getAxisSize())-1;
			}
		}
		
		int leftOffset = getLeftOffset();
		if(null != topTertiary){
			topPrimary.addTopHeadingRows(lines, leftOffset, topPrimarySpan, topPrimaryRepeat, false);
			topSecondary.addTopHeadingRows(lines, leftOffset, topSecondarySpan, topSecondaryRepeat, false);
			topTertiary.addTopHeadingRows(lines, leftOffset, topTertiarySpan, topTertiaryRepeat, cg );
		}
		else if(null != topSecondary){
			topPrimary.addTopHeadingRows(lines, leftOffset, topPrimarySpan, topPrimaryRepeat, false);
			topSecondary.addTopHeadingRows(lines, leftOffset, topSecondarySpan, topSecondaryRepeat, cg );
		}
		else{
			topPrimary.addTopHeadingRows(lines, leftOffset, topPrimarySpan, topPrimaryRepeat, cg);
		}
		// build the triplets that will provide the horizontal axis elements
		topPrimary.buildPrimaryTriplets();
		leftPrimary.addPrimaryDataRows();
	}
	private int getLeftOffset() {
		int leftOffset = leftPrimary.headingCount();
		if(null != leftSecondary){
			leftOffset += leftSecondary.headingCount();
			if(null != leftTertiary){
				leftOffset += leftTertiary.headingCount();
			}
		}
		return leftOffset;
	}
	
	/**
	 * throws a runtime exception if there's anything missing
	 * or incompatible in the report structure 
	 */
	private void checkReportContents(){
		if(null == reportName){
			throw new RuntimeException("Report name cannot be null");
		}
		if(null == intervals || 0 == intervals.size()){
			throw new RuntimeException("Reports need intervals");
		}
		if(null == reportItems || 0 ==reportItems.size()){
			throw new RuntimeException("Reports need some items");
		}
		if(null == companies){
			companies = new ArrayList<Company>(); 
		}
		if(0 == companies.size()){
			companies.add(CompanyPlaceHolder.COMPANY_PLACE_HOLDER); 
		}
		if(null == leftPrimary){
			throw new RuntimeException("No primary left axis declared");
		}
		if(null == topPrimary){
			throw new RuntimeException("No primary top axis declared");
		}
		if(null == leftSecondary && null == topSecondary){
			throw new RuntimeException("No secondary axis declared");
		}
//		make sure group reports have the company on the left
		if(null != group && !group.getName().equalsIgnoreCase("NONE")){
			if(!(leftPrimary instanceof CompanyReportAxis) && (leftSecondary instanceof CompanyReportAxis)){
				throw new RuntimeException("grouped reports must have company on the left axis");
			}
		}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("id = " + this.reportId + "\n");
		sb.append("name = " + this.reportName + "\n");
		if(this.lines != null){
			for(ReportLine rl: this.lines){
				sb.append("  line: [");
				for(ReportCell cell: rl.getCells()){
					sb.append(" cell: class = " + cell.getCellClass() + " contents = " + cell.getCellContents() );
				}
				sb.append("  ]\n");
			}
		
			// a graphical rep
			for(ReportLine rl: this.lines){
				sb.append("\n");
				for(ReportCell cell: rl.getCells()){
					sb.append("[" + cell.getCellType()  + " cd?" +cell.isDataCG()+ "]");
				}
			}
		}
		return sb.toString();
	}
	
	public boolean isCompanyAcrossTop() {
		if (null != topPrimary && topPrimary instanceof CompanyReportAxis) {
			return true;
		}
		if (null != topSecondary && topSecondary instanceof CompanyReportAxis) {
			return true;
		}
		return false;
	}
	
	
	public ReportLine addReportLine(boolean createLine) {
		if (!createLine) {
			return null;
		}

		ReportLine reportLine = new ReportLine();
		reportLine.setGrouped(false);
		lines.add(reportLine);
		return reportLine;
	}

	public void addLeftOffsetCells(ReportLine reportLine, int leftOffset) {
		if (null == reportLine) {
			return;
		}
		for(int i = 0; i < leftOffset; i++){
			reportLine.addCell(new ReportCell("empty", "", CellType.EMPTY));
		}
		return;
	}

	public boolean hasLeftSecondaryAxis() {
		return (null != getLeftSecondary());
	}
	
	public boolean hasLeftTertiaryAxis() {
		return (null != getLeftTertiary());
	}
	
	public void addTopHeadingCell(boolean addHeader, ReportLine reportLine, String text, boolean addCGCol, int span) {
		if(addHeader){
			reportLine.addCell(new ReportCell("heading", text, CellType.COL_HEADING));
			if(isCg() && addCGCol){
				reportLine.addCell(new ReportCell("heading", "CG", CellType.COL_HEADING));
			}
			else{
				int emptyCells = span-1;
				if (isCg()) {
					emptyCells = (span *2)-1;
				}
				for(int i = 0; i < emptyCells; i++){
					if (isShowAllHeadings()) {
						reportLine.addCell(new ReportCell("heading", text, CellType.COL_HEADING));
					}
					else {
						reportLine.addCell(new ReportCell("empty", "", CellType.EMPTY));
					}
				}
			}
		}
	}
	public ReportLine newReportLine(ReportLine baseLine) {
		ReportLine line;
		if (isShowAllHeadings()) {
			line = baseLine.clone();
		}
		else {
			line = new ReportLine();
			line.setGrouped(baseLine.isGrouped());
			addEmptyHeaderCells(baseLine.getCells().size(), line);
		}
		return line;
	}

	public void addEmptyHeaderCells(int numHeadingsToAdd, ReportLine line) {
		for(int i = 0; i < numHeadingsToAdd; i++){
			line.addCell(new ReportCell("empty", "", CellType.EMPTY));
		}
	}
	
	@Override
	public int getId() {
		return reportId;
	}

}
