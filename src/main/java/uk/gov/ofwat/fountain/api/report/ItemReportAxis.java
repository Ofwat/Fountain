package uk.gov.ofwat.fountain.api.report;

import java.util.List;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;

public class ItemReportAxis implements ReportAxis {

	ReportStructure reportStructure;
	private ReportService reportService;
	
	public ItemReportAxis(ReportStructure reportStructure, ReportService reportService) {
		this.reportStructure = reportStructure;
		this.reportService = reportService;
	}

	public void buildPrimaryTriplets(){
		for(ReportItem ri: reportStructure.getReportItems()){
			Triplet t = new Triplet();
			t.ri = ri;
			if(null != reportStructure.getTopSecondary()){
				reportStructure.getTopSecondary().addSecondaryTriplets(t);
			}
			else{
				reportStructure.getHorizontalLocii().add(t);
			}
		}
	}
	
	public void addSecondaryTriplets(Triplet t) {
		for(ReportItem ri: reportStructure.getReportItems()){
			Triplet t2 = t.clone();
			t2.ri = ri;
			if(null != reportStructure.getTopTertiary()){
				reportStructure.getTopTertiary().addTertiaryTriplets(t2);
			}
			else{
				reportStructure.getHorizontalLocii().add(t2);
			}
		}
	}
	
	public void addTertiaryTriplets(Triplet t) {
		for(ReportItem ri: reportStructure.getReportItems()){
			Triplet t2 = t.clone();
			t2.ri = ri;
			reportStructure.getHorizontalLocii().add(t2);
		}
	}

	public void addPrimaryDataRows() {
		boolean grouped = true; // data rows are always considered grouped
		for(ReportItem ri: reportStructure.getReportItems()){
			ReportLine line = new ReportLine();
			line.setGrouped(grouped);
			addLeftHeaderCells(line, ri, CellType.ROW_HEADING_NON_REPEAT);
			if(reportStructure.hasLeftSecondaryAxis()){
				addToTriplets(ri);
				reportStructure.getLeftSecondary().addSecondaryDataRows(line);
			}
			else{ 
				addDataCells(ri, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addSecondaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(ReportItem ri: reportStructure.getReportItems()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, ri, CellType.ROW_HEADING);
			if(reportStructure.hasLeftTertiaryAxis()){
				addToTriplets(ri);
				reportStructure.getLeftTertiary().addTertiaryDataRows(line);
			}
			else{ 
				addDataCells(ri, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addTertiaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(ReportItem ri: reportStructure.getReportItems()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, ri, CellType.ROW_HEADING);
			addDataCells(ri, line);
			reportStructure.getLines().add(line);
		}
	}

	private void addDataCells(ReportItem ri, ReportLine line) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.ri = ri;
			line.addCell(new ReportCell(t, false, reportService));
			if(reportStructure.isCg()){
				line.addCell(new ReportCell(t, true, reportService));
			}
		}
	}

	private void addToTriplets(ReportItem ri) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.ri = ri;
		}
	}

	public void addTopHeadingRows(List<ReportLine> lines, int leftOffset, int span, int repeats, boolean putCG) {
		ReportLine bonCodeReportLine = reportStructure.addReportLine(reportStructure.isBonCode());
		reportStructure.addLeftOffsetCells(bonCodeReportLine, leftOffset);
		ReportLine descReportLine = reportStructure.addReportLine(reportStructure.isItemDesc());
		reportStructure.addLeftOffsetCells(descReportLine, leftOffset);
		ReportLine unitReportLine = reportStructure.addReportLine(reportStructure.isUnit());
		reportStructure.addLeftOffsetCells(unitReportLine, leftOffset);
		ReportLine modelReportLine = reportStructure.addReportLine(reportStructure.isModel());
		reportStructure.addLeftOffsetCells(modelReportLine, leftOffset);

		
		//work out if and where cgs will go;
		boolean cgOnBon = false;
		boolean cgOnDesc = false;
		boolean cgOnUnit = false;
		boolean cgOnModel = false;
		if(putCG){
			if(reportStructure.isModel()){
				cgOnModel = true;
			}
			else if(reportStructure.isUnit()){
				cgOnUnit = true;
			}
			else if(reportStructure.isItemDesc()){
				cgOnDesc = true;
			}
			else if(reportStructure.isBonCode()){
				cgOnBon = true;
			}
		}
		for(int i = 0; i < repeats+1; i++){
			for(ReportItem ri: reportStructure.getReportItems()){
				reportStructure.addTopHeadingCell(reportStructure.isBonCode(), bonCodeReportLine, ri.getItemProperties().getItem().getCode(), cgOnBon, span);
				reportStructure.addTopHeadingCell(reportStructure.isItemDesc(), descReportLine, ri.getItemProperties().getDescription(), cgOnDesc, span);
				reportStructure.addTopHeadingCell(reportStructure.isUnit(), unitReportLine, ri.getItemProperties().getItem().getUnit(), cgOnUnit, span);
				reportStructure.addTopHeadingCell(reportStructure.isModel(), modelReportLine, ri.getModelDto().getName(), cgOnModel, span);
			}
		}
	}
	
	public int getAxisSize(){
		return reportStructure.getReportItems().size();
	}
	
	/**
	 * for a given report axis, how many heading cells will be needed 
	 * based on the various heading parameters
	 * @param axis
	 * @return
	 */
	public int headingCount(){
		int count = 0;
		if(reportStructure.isItemDesc()){count++;}
		if(reportStructure.isUnit()){count++;}
		if(reportStructure.isModel()){count++;}
		if(reportStructure.isBonCode()){count++;}
		return count;
	}

	private int addLeftHeaderCells(ReportLine line, ReportItem ri, CellType cellType) {
		int cellsAdded = 0;
		if(reportStructure.isBonCode()){
			line.addCell(new ReportCell("heading", ri.getItemProperties().getItem().getCode(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isItemDesc()){
			line.addCell(new ReportCell("heading", ri.getItemProperties().getDescription(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isUnit()){
			line.addCell(new ReportCell("heading", ri.getItemProperties().getItem().getUnit(), cellType));
			cellsAdded++;
		}
		if(reportStructure.isModel()){
			line.addCell(new ReportCell("heading", ri.getModelDto().getName(), cellType));
			cellsAdded++;
		}
		return cellsAdded;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

}
