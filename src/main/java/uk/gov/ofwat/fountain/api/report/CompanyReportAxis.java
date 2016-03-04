package uk.gov.ofwat.fountain.api.report;

import java.util.List;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;

public class CompanyReportAxis implements ReportAxis {

	ReportStructure reportStructure;
	private ReportService reportService;

	public CompanyReportAxis(ReportStructure reportStructure, ReportService reportService) {
		this.reportStructure = reportStructure;
		this.reportService = reportService;
	}

	public void buildPrimaryTriplets() {
		for (Company company : reportStructure.getCompanies()) {
			Triplet t = new Triplet();
			t.company = company;
			if (null != reportStructure.getTopSecondary()) {
				reportStructure.getTopSecondary().addSecondaryTriplets(t);
			} else {
				reportStructure.getHorizontalLocii().add(t);
			}
		}
	}

	public void addSecondaryTriplets(Triplet t) {
		for (Company company : reportStructure.getCompanies()) {
			Triplet t2 = t.clone();
			t2.company = company;
			if (null != reportStructure.getTopTertiary()) {
				reportStructure.getTopTertiary().addTertiaryTriplets(t2);
			} else {
				reportStructure.getHorizontalLocii().add(t2);
			}
		}
	}

	public void addTertiaryTriplets(Triplet t) {
		for (Company company : reportStructure.getCompanies()) {
			Triplet t2 = t.clone();
			t2.company = company;
			reportStructure.getHorizontalLocii().add(t2);
		}
	}

	public void addPrimaryDataRows() {
		boolean grouped = true; // data rows are always considered grouped
		for(Company company: reportStructure.getCompanies()){
			ReportLine line = new ReportLine();
			line.setGrouped(grouped);
			addLeftHeaderCells(line, company, CellType.ROW_HEADING_NON_REPEAT);
			if(reportStructure.hasLeftSecondaryAxis()){
				addToTriplets(company);
				reportStructure.getLeftSecondary().addSecondaryDataRows(line);
			}
			else{ 
				addDataCells(company, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addSecondaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(Company company: reportStructure.getCompanies()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, company, CellType.ROW_HEADING);
			if(reportStructure.hasLeftTertiaryAxis()){
				addToTriplets(company);
				reportStructure.getLeftTertiary().addTertiaryDataRows(line);
			}
			else{ 
				addDataCells(company, line);
				reportStructure.getLines().add(line);
			}
		}
	}

	public void addTertiaryDataRows(ReportLine rl) {
		ReportLine baseLine = rl.clone(); 
		boolean first = true;
		for(Company company: reportStructure.getCompanies()){
			ReportLine line = null;
			if(first){
				line = rl;
				first = false;
			}
			else{
				line = reportStructure.newReportLine(baseLine);
			}
			addLeftHeaderCells(line, company, CellType.ROW_HEADING);
			addDataCells(company, line);
			reportStructure.getLines().add(line);
		}
	}

	private void addDataCells(Company company, ReportLine line) {
		for (Triplet t : reportStructure.getHorizontalLocii()) {
			t.company = company;
			line.addCell(new ReportCell(t, false, reportService));
			if (reportStructure.isCg()) {
				line.addCell(new ReportCell(t, true, reportService));
			}
		}
	}
	
	private void addToTriplets(Company company) {
		for(Triplet t: reportStructure.getHorizontalLocii()){
			t.company = company;
		}
	}
	
	private void addEmptyHeaderCells(int numHeadingsToAdd, ReportLine line) {
		for(int i = 0; i < numHeadingsToAdd; i++){
			line.addCell(new ReportCell("empty", "", CellType.EMPTY));
		}
	}

	public void addTopHeadingRows(List<ReportLine> lines, int leftOffset,
			int span, int repeats, boolean putCG) {
		ReportLine companyAcronymLine = reportStructure.addReportLine(reportStructure.isAcronym());
		reportStructure.addLeftOffsetCells(companyAcronymLine, leftOffset);
		ReportLine companyNameLine = reportStructure.addReportLine(reportStructure.isFullName());
		reportStructure.addLeftOffsetCells(companyNameLine, leftOffset);

		boolean cgOnFullName = putCG && reportStructure.isFullName(); // if both of these are true, we'll be writing cg on the fullname line
		boolean cgOnAcronym = putCG && (!reportStructure.isFullName());
		for (int i = 0; i < repeats + 1; i++) {
			for (Company company : reportStructure.getCompanies()) {
				reportStructure.addTopHeadingCell(reportStructure.isAcronym(),
						companyAcronymLine, company.getCode(), cgOnAcronym,
						span);
				reportStructure.addTopHeadingCell(reportStructure.isFullName(),
						companyNameLine, company.getName(), cgOnFullName, span);
			}
		}
	}

	public int getAxisSize() {
		return reportStructure.getCompanies().size();
	}

	/**
	 * for a given report axis, how many heading cells will be needed based on
	 * the various heading parameters
	 * 
	 * @param axis
	 * @return
	 */
	public int headingCount() {
		int count = 0;
		if (reportStructure.isFullName()) {
			count++;
		}
		if (reportStructure.isAcronym()) {
			count++;
		}
		if (null != reportStructure.getGroup()
				&& !reportStructure.getGroup().getName().equals("NONE")) {
			count++;
		} // group goes with company
		return count;
	}

	private int addLeftHeaderCells(ReportLine line, Company company,
			CellType cellType) {
		int cellsAdded = 0;
		if (reportStructure.isAcronym()) {
			line.addCell(new ReportCell("heading", company.getCode(), cellType));
			cellsAdded++;
		}
		if (reportStructure.isFullName()) {
			line.addCell(new ReportCell("heading", company.getName(), cellType));
			cellsAdded++;
		}
		if (null != reportStructure.getGroup()
				&& !reportStructure.getGroup().getName().equals("NONE")) {
			line.addCell(new ReportCell("heading", "group_placeHolder",
					CellType.GROUP_ROW_HEADING));
			cellsAdded++;
		}
		return cellsAdded;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

}
