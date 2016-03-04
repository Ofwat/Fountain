package uk.gov.ofwat.fountain.api.report;

import java.util.List;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;

public class RunReportAxis implements ReportAxis {

	ReportStructure reportStructure;
	private ReportService reportService;
	
	public RunReportAxis(ReportStructure reportStructure, ReportService reportService) {
		this.reportStructure = reportStructure;
		this.reportService = reportService;
	}
	
	@Override
	public void buildPrimaryTriplets() {
		for(RunTag runTag: reportStructure.getRunTags()){
			Triplet t = new Triplet();
			t.runTag = runTag;
			if(null != reportStructure.getTopSecondary()){
				reportStructure.getTopSecondary().addSecondaryTriplets(t);
			}
			else{
				reportStructure.getHorizontalLocii().add(t);
			}
		}
	}

	@Override
	public void addSecondaryTriplets(Triplet t) {
		for(RunTag runTag: reportStructure.getRunTags()){
			Triplet t2 = t.clone();
			t2.runTag = runTag;
			if(null != reportStructure.getTopTertiary()){
				reportStructure.getTopTertiary().addTertiaryTriplets(t2);
			}
			else{
				reportStructure.getHorizontalLocii().add(t2);
			}
		}
	}

	@Override
	public void addTertiaryTriplets(Triplet t) {
		for(RunTag runTag: reportStructure.getRunTags()){
			Triplet t2 = t.clone();
			t2.runTag = runTag;
			reportStructure.getHorizontalLocii().add(t2);
		}
	}

	public void addPrimaryDataRows() {
		boolean grouped = true; // data rows are always considered grouped

		// iterate through runs and add data rows
		for(RunTag runTag: reportStructure.getRunTags()){
			ReportLine line = new ReportLine();
			line.setGrouped(grouped);
			addLeftHeaderCells(line, runTag, CellType.ROW_HEADING_NON_REPEAT);
			if(reportStructure.hasLeftSecondaryAxis()){
				addToTriplets(runTag);
				reportStructure.getLeftSecondary().addSecondaryDataRows(line);
			}
			else{ 
				addDataCells(runTag, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addSecondaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(RunTag runTag: reportStructure.getRunTags()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, runTag, CellType.ROW_HEADING);
			if(reportStructure.hasLeftTertiaryAxis()){
				addToTriplets(runTag);
				reportStructure.getLeftTertiary().addTertiaryDataRows(line);
			}
			else{ 
				addDataCells(runTag, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public ReportLine newReportLine(ReportLine baseLine) {
		ReportLine line;
		if (reportStructure.isShowAllHeadings()) {
			line = baseLine.clone();
		}
		else {
			line = new ReportLine();
			line.setGrouped(baseLine.isGrouped());
			addEmptyHeaderCells(baseLine.getCells().size(), line);
		}
		return line;
	}

	public void addTertiaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(RunTag runTag: reportStructure.getRunTags()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, runTag, CellType.ROW_HEADING);
			addDataCells(runTag, line);
			reportStructure.getLines().add(line);
		}
	}

	private void addToTriplets(RunTag runTag) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.runTag = runTag;
		}
	}

	
	private void addEmptyHeaderCells(int numHeadingsToAdd, ReportLine line) {
		for(int i = 0; i < numHeadingsToAdd; i++){
			line.addCell(new ReportCell("empty", "", CellType.EMPTY));
		}
	}
	
	private void addDataCells(RunTag runTag, ReportLine line) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.runTag = runTag;
			line.addCell(new ReportCell(t, false, reportService));
			if(reportStructure.isCg()){
				line.addCell(new ReportCell(t, true, reportService));
			}
		}
	}

	@Override
	public void addTopHeadingRows(List<ReportLine> lines, int leftOffset, int span, int repeats, boolean putCG) {
		ReportLine agendaNameReportLine = reportStructure.addReportLine(reportStructure.isAgendaName());
		reportStructure.addLeftOffsetCells(agendaNameReportLine, leftOffset);
		ReportLine agendaCodeReportLine = reportStructure.addReportLine(reportStructure.isAgendaCode());
		reportStructure.addLeftOffsetCells(agendaCodeReportLine, leftOffset);
		ReportLine runNameReportLine = reportStructure.addReportLine(reportStructure.isRunName());
		reportStructure.addLeftOffsetCells(runNameReportLine, leftOffset);
		ReportLine runDescReportLine = reportStructure.addReportLine(reportStructure.isRunDesc());
		reportStructure.addLeftOffsetCells(runDescReportLine, leftOffset);
		ReportLine tagDisplayNameReportLine = reportStructure.addReportLine(reportStructure.isTagDisplayName());
		reportStructure.addLeftOffsetCells(tagDisplayNameReportLine, leftOffset);

		//work out if and where cgs will go;
		boolean cgOnAgendaName = false;
		boolean cgOnAgendaCode = false;
		boolean cgOnRunName = false;
		boolean cgOnRunDesc = false;
		boolean cgOnTagDisplayName = false;
		if(putCG){
			if(reportStructure.isTagDisplayName()){
				cgOnTagDisplayName = true;
			}
			else if(reportStructure.isRunDesc()){
				cgOnRunDesc = true;
			}
			else if(reportStructure.isRunName()){
				cgOnRunName = true;
			}
			else if(reportStructure.isAgendaCode()){
				cgOnAgendaCode = true;
			}
			else if(reportStructure.isAgendaName()){
				cgOnAgendaName = true;
			}
		}

		for(int i = 0; i < repeats+1; i++){
			for(RunTag runTag: reportStructure.getRunTags()){
				reportStructure.addTopHeadingCell(reportStructure.isAgendaName(), agendaNameReportLine, runTag.getRun().getAgenda().getName(), cgOnAgendaName, span);
				reportStructure.addTopHeadingCell(reportStructure.isAgendaCode(), agendaCodeReportLine, runTag.getRun().getAgenda().getCode(), cgOnAgendaCode, span);
				reportStructure.addTopHeadingCell(reportStructure.isRunName(), runNameReportLine, runTag.getRun().getName(), cgOnRunName, span);
				reportStructure.addTopHeadingCell(reportStructure.isRunDesc(), runDescReportLine, runTag.getRun().getDescription(), cgOnRunDesc, span);
				reportStructure.addTopHeadingCell(reportStructure.isTagDisplayName(), tagDisplayNameReportLine, runTag.getRunModelTag().getDisplayName(), cgOnTagDisplayName, span);
			}
		}
	}

	public int getAxisSize(){
		return reportStructure.getRunTags().size();
	}

	/**
	 * for a given report axis, how many heading cells will be needed 
	 * based on the various heading parameters
	 * @param axis
	 * @return
	 */
	public int headingCount(){
		int count = 0;
		if(reportStructure.isAgendaName()){count++;}
		if(reportStructure.isAgendaCode()){count++;}
		if(reportStructure.isRunName()){count++;}
		if(reportStructure.isRunDesc()){count++;}
		if(reportStructure.isTagDisplayName()){count++;}
		return count;
	}

	public int addLeftHeaderCells(ReportLine line, RunTag runTag, CellType cellType) {
		int cellsAdded = 0;
		if(reportStructure.isAgendaName()){
			line.addCell(new ReportCell("heading", runTag.getRun().getAgenda().getName(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isAgendaCode()){
			line.addCell(new ReportCell("heading", runTag.getRun().getAgenda().getCode(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isRunName()){
			line.addCell(new ReportCell("heading", runTag.getRun().getName(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isRunDesc()){
			line.addCell(new ReportCell("heading", runTag.getRun().getDescription(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isTagDisplayName()){
			line.addCell(new ReportCell("heading", runTag.getRunModelTag().getDisplayName(), cellType));
			cellsAdded++;
		}
		return cellsAdded;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

}
