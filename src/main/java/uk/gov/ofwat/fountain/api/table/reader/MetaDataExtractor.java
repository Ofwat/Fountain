package uk.gov.ofwat.fountain.api.table.reader;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;

import uk.gov.ofwat.fountain.api.CompanyService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;

public class MetaDataExtractor {

	private static Log log = LogFactory.getLog(MetaDataExtractor.class);

	private ModelService modelService;
	private CompanyService companyService;
	
	public ModelService getModelService() {
		return modelService;
	}
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	public CompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	public Boolean extractMetaData(TableUploadMetaData metaData, Sheet sheet) {
		Boolean success = true;
		RichTextString titleCell1 = sheet.getRow(1).getCell(0).getRichStringCellValue();
		String titleString1 = titleCell1.getString();
		RichTextString titleCell2 = sheet.getRow(2).getCell(0).getRichStringCellValue();
		String titleString2 = titleCell2.getString();
		
		Company company = extractCompany(metaData, sheet.getSheetName(), titleString1);
		if (null == company) {
			String msg = "Error: Sheet " + sheet.getSheetName() + ". Cannot find a company in either: the web form; the sheet name; or the title string.";
			log.error("Table import. " + msg);
			metaData.addError(msg);
			success = false;
		}
		else {
			metaData.setCompany(company);
		}
		
		Model model = extractModel(metaData, titleString1);
		if (null == model) {
			String msg = "Error: Sheet " + sheet.getSheetName() + ". Cannot find a model in either: the web form or the title string.";
			log.error("Table import. " + msg);
			metaData.addError(msg);
			success = false;
		}
		else {
			metaData.setModel(model);
		}

		Table table = extractTable(metaData, sheet.getSheetName(), titleString2, model);
		if (null == table) {
			String msg = "Warning: Sheet '" + sheet.getSheetName() + "'. Cannot find a table in either: the web form; the sheet name; or the title string. Unable to process sheet.";
			log.error("Table import. " + msg);
			metaData.addWarning(msg);
			success = false;
		}
		else {
			metaData.setTable(table);
		}
		
		return success;
	}
	
	
	Table extractTable(TableUploadMetaData metaData, String sheetName, String titleString2, Model model) {
		Table table = null;
		if (!metaData.isTableNameFromForm()) {
			table = parseTableFromSheetName(sheetName, model);
			if (null == table) {
				table = parseTableFromTitleString(titleString2, model);
			}
			if (null != table) {
				metaData.setTableName(table.getName());
			}
		}
		else {
			table = model.getTable(metaData.getTableName());
		}
		return table;
	}

	private Table parseTableFromSheetName(String sheetName, Model model) {
		String[] names = sheetName.split(" ");
		for (int i=0; i<names.length; i++) {
			Table table = model.getTable(names[i]);
			if (null != table) {
				return table;
			}
		}
		return null;
	}
	private Table parseTableFromTitleString(final String titleString, Model model) {
		int endIndex = titleString.indexOf("-");
		if (endIndex < 1) {
			return null;
		}
		else {
			String title = titleString.substring(0, endIndex).trim();
			if (title.equalsIgnoreCase("Table")) {
				endIndex = titleString.indexOf("-", endIndex);
				title = titleString.substring(0, endIndex).trim();
			}
			return model.getTable(title);
		}
	}
	
	Model extractModel(TableUploadMetaData metaData, String titleString1) {
		Model model = null;
		if (!metaData.isModelCodeFromForm()) {
			model = parseModelCodeFromTitleString(titleString1);
			if (null == model) {
				return null;
			}
			metaData.setModelCode(model.getCode());
		}
		else {
			model = modelService.getModel(metaData.getModelCode());
		}
		return model;
	}

	private Model parseModelCodeFromTitleString(final String titleString) {
		String[] titleElements = titleString.split(":");
		if (3 != titleElements.length) {
			return null;
		}
		String[] titleElementsWithoutDate = titleElements[2].split("for");
		if (2 != titleElementsWithoutDate.length) {
			return null;
		}
		List<Model> models = modelService.getModels();
		for (Model model: models) {
			if (model.getCode().equalsIgnoreCase(titleElementsWithoutDate[0].trim())) {
				return model;
			}
		}
		return null;
	}
	
	Company extractCompany(TableUploadMetaData metaData, String sheetName, String titleString1) {
		Company company = null;
		if (!metaData.isCompanyIdFromForm()) {
			company = parseCompanyFromSheetName(sheetName);
			if (null == company) {
				company = parseCompanyFromTitleString(titleString1);
			}
			if (null != company) {
				metaData.setCompanyId(company.getId());
			}
		}
		else {
			company = companyService.getCompany(metaData.getCompanyId());
		}
		return company;
	}
	private Company parseCompanyFromSheetName(String sheetName) {
		String[] names = sheetName.split(" ");
		for (int i=0; i<names.length; i++) {
			if (companyService.isCompanyCode(names[i])) {
				return companyService.getCompanyByCode(names[i]);
			}
		}
		return null;
	}

	private Company parseCompanyFromTitleString(final String titleString) {
		String[] titleElements = titleString.split(":");
		if (3 != titleElements.length) {
			return null;
		}
		String[] titleElementsWithoutDate = titleElements[2].split("for");
		if (2 != titleElementsWithoutDate.length) {
			return null;
		}
		return companyService.getCompanyByName(titleElementsWithoutDate[1].trim());
	}


}
