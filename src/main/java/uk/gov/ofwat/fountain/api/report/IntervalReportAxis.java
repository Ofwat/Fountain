package uk.gov.ofwat.fountain.api.report;

import java.util.List;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;

public class IntervalReportAxis implements ReportAxis  {

	ReportStructure reportStructure;
	private ReportService reportService;
	
	public IntervalReportAxis(ReportStructure reportStructure, ReportService reportService) {
		this.reportStructure = reportStructure;
		this.reportService = reportService;
	}

	public void buildPrimaryTriplets(){
		for(Interval interval: reportStructure.getIntervals()){
			Triplet t = new Triplet();
			t.interval = interval;
			if(null != reportStructure.getTopSecondary()){
				reportStructure.getTopSecondary().addSecondaryTriplets(t);
			}
			else{
				reportStructure.getHorizontalLocii().add(t);
			}
		}
	}
	
	
	/**
	 * create triplets for the secondary top axis
	 * @param t - a triplet loaded with the entity from the top primary axis. Will be cloned
	 */
	public void addSecondaryTriplets(Triplet t) {
		for(Interval interval: reportStructure.getIntervals()){
			Triplet t2 = t.clone();
			t2.interval = interval;
			if(null != reportStructure.getTopTertiary()){
				reportStructure.getTopTertiary().addTertiaryTriplets(t2);
			}
			else{
				reportStructure.getHorizontalLocii().add(t2);
			}
		}
	}
	
	public void addTertiaryTriplets(Triplet t) {
		for(Interval interval: reportStructure.getIntervals()){
			Triplet t2 = t.clone();
			t2.interval = interval;
			reportStructure.getHorizontalLocii().add(t2);
		}
	}
	
	public void addPrimaryDataRows() {
		boolean grouped = true; // data rows are always considered grouped
		for(Interval interval: reportStructure.getIntervals()){
			ReportLine line = new ReportLine();
			line.setGrouped(grouped);
			addLeftHeaderCells(line, interval, CellType.ROW_HEADING_NON_REPEAT);
			if(reportStructure.hasLeftSecondaryAxis()){
				addToTriplets(interval);
				reportStructure.getLeftSecondary().addSecondaryDataRows(line);
			}
			else{ 
				addDataCells(interval, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addSecondaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(Interval interval: reportStructure.getIntervals()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, interval, CellType.ROW_HEADING);
			if(reportStructure.hasLeftTertiaryAxis()){
				addToTriplets(interval);
				reportStructure.getLeftTertiary().addTertiaryDataRows(line);
			}
			else{ 
				addDataCells(interval, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addTertiaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(Interval interval: reportStructure.getIntervals()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, interval, CellType.ROW_HEADING);
			addDataCells(interval, line);
			reportStructure.getLines().add(line);
		}
	}

	private void addDataCells(Interval interval, ReportLine line) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.interval = interval;
			line.addCell(new ReportCell(t, false, reportService));
			if(reportStructure.isCg()){
				line.addCell(new ReportCell(t, true, reportService));
			}
		}
	}
	
	private void addToTriplets(Interval interval) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.interval = interval;
		}
	}

	
	private void addEmptyHeaderCells(int numHeadingsToAdd, ReportLine line) {
		for(int i = 0; i < numHeadingsToAdd; i++){
			line.addCell(new ReportCell("empty", "", CellType.EMPTY));
		}
	}

	public void addTopHeadingRows(List<ReportLine> lines, int leftOffset, int span, int repeats, boolean putCG) {
		ReportLine intervalLine = reportStructure.addReportLine(true);
		reportStructure.addLeftOffsetCells(intervalLine, leftOffset);

		for(int i = 0; i < repeats+1; i++){
			for(Interval interval: reportStructure.getIntervals()){
				reportStructure.addTopHeadingCell(true, intervalLine, interval.getName(), putCG, span);
			}
		}
	}
	
	private boolean isPluginLayout() {
		if (reportStructure.getLeftPrimary() instanceof CompanyReportAxis &&
			reportStructure.getLeftSecondary() instanceof ItemReportAxis &&
			null == reportStructure.getLeftTertiary() &&
			reportStructure.getTopPrimary() instanceof IntervalReportAxis &&
			reportStructure.getTopSecondary() instanceof RunReportAxis &&
			null == reportStructure.getTopTertiary()) {
			return true;
			}
		return false;
	}
	
	public int getAxisSize(){
		return reportStructure.getIntervals().size();
	}
	
	public int headingCount(){
		int count = 0;
		count++; // only ever one field
		return count;
	}

	private int addLeftHeaderCells(ReportLine line, Interval interval, CellType cellType) {
		int cellsAdded = 1;
		line.addCell(new ReportCell("heading", interval.getName(), cellType));
		return cellsAdded;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

}
