package uk.gov.ofwat.fountain.domain;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class TableUploadMetaData {

	private String uploadFileName;
	private Integer companyId;
	private boolean companyIdFromForm;
	private String modelCode;
	private boolean modelCodeFromForm;
	private String tableName;
	private boolean tableNameFromForm;
	private List<String> results;
	private List<String> errors;
	private List<String> cellWarnings;
	
	private Company company;
	private Model model;
	private Table table;
	private boolean allowHistoricData;
	
	
	private static MessageFormat cellWarningFormat = new MessageFormat("Warning. Model: {0}. Table: {1}. Cell at row: {2}, col: {3}. Current value: {4}. Proposed value: {5}. Message: {6}" ); 
	private static MessageFormat generalWarningFormat = new MessageFormat("Warning. Model: {0}. Table: {1}. Cell at row: {2}, col: {3}. Message: {4}" ); 
	private static MessageFormat generalErrorFormat = new MessageFormat("Error. Model: {0}. Table: {1}. Cell at row: {2}, col: {3}. Message: {4}" ); 

	public TableUploadMetaData() {
		results =  new ArrayList<String>();
		errors =  new ArrayList<String>();
		cellWarnings =  new ArrayList<String>();
	}
	
	public TableUploadMetaData(MultipartFormDataInput input) throws IOException {
		this();
		extractMetaData(input);
	}

	public void addWarning(int row, int col, String originalValue, String proposedValue, String text) {
		Object[] args = {modelCode, tableName, row+1, col+1, originalValue, proposedValue, text}; 
		cellWarnings.add(cellWarningFormat.format(args));
	}
	
	public void addWarning(int row, int col, String text) {
		Object[] args = {modelCode, tableName, row+1, col+1, text}; 
		cellWarnings.add(generalWarningFormat.format(args));
	}

	public void addWarning(String text) {
		cellWarnings.add("Warning " + text);
	}

	 
	public void addError(int row, int col, String text) {
		Object[] args = {modelCode, tableName, row+1, col+1, text}; 
		errors.add(generalErrorFormat.format(args));
	}

	public void addError(String error) {
		errors.add(error);
	}

	public List<String> getCellWarnings() {
		return cellWarnings;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}


	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public void setTableName(String table) {
		this.tableName = table;
	}

	public List<String> getResults() {
		return results;
	}

	public void addResult(String result) {
		this.results.add(result);
	}

	public void setResults(List<String> results) {
		this.results = results;
	}

	
	public List<String> getErrors() {
		return errors;
	}

	public String getTableName() {
		return tableName;
	}

	public void extractMetaData(MultipartFormDataInput input) throws IOException {
		for (InputPart part: input.getParts()) {
			if (part.getMediaType().getType().equals("text") &&
				part.getMediaType().getSubtype().equals("plain")) {
				List<String> contentDisposition = part.getHeaders().get("Content-Disposition");
				if (contentDisposition.size() == 1) {
					if (contentDisposition.get(0).equals("form-data; name=\"submit\"")) {
						// the submit button.
					}
					if (contentDisposition.get(0).equals("form-data; name=\"company\"") &&
						!part.getBodyAsString().isEmpty() &&
						!part.getBodyAsString().equals("0")) {
						setCompanyId(Integer.parseInt(part.getBodyAsString()));
						setCompanyIdFromForm(true);
					}
					if (contentDisposition.get(0).equals("form-data; name=\"model\"") &&
						!part.getBodyAsString().isEmpty() &&
						!part.getBodyAsString().equals("0")) {
						setModelCode(part.getBodyAsString().split("#")[1]);
						setModelCodeFromForm(true);
					}
					if (contentDisposition.get(0).equals("form-data; name=\"page\"") &&
						!part.getBodyAsString().isEmpty() &&
						!part.getBodyAsString().equals("0")) {
						setTableName(part.getBodyAsString());
						setTableNameFromForm(true);
					}
					if (contentDisposition.get(0).equals("form-data; name=\"allowHistoricData\"") &&
						!part.getBodyAsString().isEmpty()) {
						String allowHistoricData = part.getBodyAsString();
						this.setAllowHistoricData(allowHistoricData.equalsIgnoreCase("on") ? true : false);
					}
				}
			}
		}
	}

	public boolean isCompanyIdFromForm() {
		return companyIdFromForm;
	}

	public void setCompanyIdFromForm(boolean companyIdFromForm) {
		this.companyIdFromForm = companyIdFromForm;
	}

	public boolean isModelCodeFromForm() {
		return modelCodeFromForm;
	}

	public void setModelCodeFromForm(boolean modelCodeFromForm) {
		this.modelCodeFromForm = modelCodeFromForm;
	}

	public boolean isTableNameFromForm() {
		return tableNameFromForm;
	}

	public void setTableNameFromForm(boolean tableNameFromForm) {
		this.tableNameFromForm = tableNameFromForm;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean isAllowHistoricData() {
		return allowHistoricData;
	}

	public void setAllowHistoricData(boolean allowHistoricData) {
		this.allowHistoricData = allowHistoricData;
	}
	
}
