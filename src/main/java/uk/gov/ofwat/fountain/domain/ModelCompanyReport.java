package uk.gov.ofwat.fountain.domain;

public class ModelCompanyReport {

	private String finfout;
	private int modelId;
	private int companyId;
	private int reportId;

	public ModelCompanyReport(String finfout, int modelId, int companyId, int reportId) {
		this.finfout = finfout;
		this.modelId = modelId;
		this.companyId = companyId;
		this.reportId = reportId;
	}
	
	public String getFinfout() {
		return finfout;
	}
	public void setFinfout(String finfout) {
		this.finfout = finfout;
	}
	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
}
