package uk.gov.ofwat.fountain.rest.dto.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.CompanyService;
import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.report.ReportAxis;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.CustomReportDto;
import uk.gov.ofwat.fountain.rest.dto.ModelItemDto;
import uk.gov.ofwat.fountain.rest.dto.RowDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public class CustomReportDtoFactory {
	private ItemService itemService;
	private ReferenceService referenceService;
	private GroupService groupService;
	private CompanyService companyService;
	private ModelService modelService;
	private ModelItemService modelItemService;
	
	// These are fixed. More coding needed if they become variable.
	private String topPrimary = "INTERVAL";
	private String topSecondary = null;
	private String leftPrimary = "COMPANY";
	private String leftSecondary = "ITEM";

	private Map <String, Integer> colNameToNumber;
	private CustomReportDto customReportDto;
	private Map<Integer, ItemAndModel> itemAndModels;
	
	private boolean structuralError;
	private boolean cellError;
	private Company suppliedCompany;

	public synchronized CustomReportDto create(TableDto reportTableDto, int suppliedCompanyId) {
		
		if (0 == suppliedCompanyId) {
			this.suppliedCompany = null; 
		}
		else {
			this.suppliedCompany = companyService.getCompany(suppliedCompanyId);
		}
		
		customReportDto = new CustomReportDto();
		customReportDto.setCompanies(new ArrayList<Integer>());
		customReportDto.setIntervals(new ArrayList<Integer>());
		customReportDto.setItems(new ArrayList<ModelItemDto>());
		colNameToNumber = new HashMap<String, Integer>();
		itemAndModels =  new HashMap<Integer, ItemAndModel>(); 
		
		structuralError = false;
		cellError = false;

		if (reportTableDto.getRows().size() == 0 ||
			reportTableDto.getRows().get(0).getCells().size() == 0) {
			// TODO Error there are no rows or no cells.
			structuralError = true;
		}
		else {
			// at least one row and one cell. we can put the errors here.
			CellDto cellDto = reportTableDto.getRows().get(0).getCells().get(0);
			if (cellDto.getValue() == null) {
				cellDto.setValue(""); // don't have to deal will nulls later.
			}
			if (reportTableDto.getRows().size() < 4) {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. There should be at least four rows. One for the title, one for the headers, one blank row and at least one for data.");
				structuralError = true;
			}
			else {
				if (reportTableDto.getRows().get(1).getCells().size() < 6) { // header row
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Structural error. There should be at least six columns: 'Acronym', 'Reference', 'Item description', 'Unit', 'Model' and at least one year.");
					structuralError = true;
				}
				if (reportTableDto.getRows().get(2).getCells().size() < 6) { // blank row
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Structural error. There should be at least six columns: 'Acronym', 'Reference', 'Item description', 'Unit', 'Model' and at least one year.");
					structuralError = true;
				}
				if (reportTableDto.getRows().get(2).getCells().size() < 6) { // first data row
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Structural error. There should be at least six columns with data for : Acronym, Reference, Item description, Unit, Model and at least one year.");
					structuralError = true;
				}
			}
			
		}
		if (structuralError) {
			return null;
		}

		for (RowDto rowDto: reportTableDto.getRows()) {
			for (CellDto cellDto: rowDto.getCells()) {
				if (cellDto.getValue() == null) {
					cellDto.setValue(""); // don't have to deal will nulls later.
				}
				
				if (0 == cellDto.getRow()) {
					// first row - titles.
				}
				else if (1 == cellDto.getRow()) {
					// second row - top header.
					headerRowCell(cellDto, customReportDto);
				}
				else if (2 == cellDto.getRow()) {
					// third row - blank.
					blankRowCell(cellDto);
				}
				else {
					if (structuralError) {
						return null;
					}
					// other rows
					dataRowCell(cellDto, customReportDto);
				}
				
//				System.out.println("|");
//				System.out.print("Row: " + cellDto.getRow() + "  Col: " + cellDto.getCol() + "  Key: " + cellDto.getKey() + "  DP: " + cellDto.getDecimalPlaces() + "  Value: " + cellDto.getValue() + "|");
			}
//			System.out.println("");
		}

		if (cellError) {
			return null;
		}
		
		List<String> layoutTop = new ArrayList<String>();
		layoutTop.add("Year");
		layoutTop.add("Run");
		customReportDto.setLayoutTop(layoutTop);
		List<String> layoutLeft = new ArrayList<String>();
		layoutLeft.add("Company");
		layoutLeft.add("Item");
		customReportDto.setLayoutLeft(layoutLeft);
		
		customReportDto.setDisplayCGs(false);
		customReportDto.setName(reportTableDto.getName());
		customReportDto.setPublicReport(true);
		Team team = referenceService.getTeam("IPL"); // all external reports are for team IPL.
		customReportDto.setTeamId(team.getId());
		customReportDto.setGroup(null);

		return customReportDto;
	}




	private void storeAxisMemberToAxis(CustomReportDto customReportDto, CellDto cellDto, String axis) {
		switch (axis) {
		case "COMPANY" :
			if (companyService.isCompanyCode(cellDto.getValue())) {
				if (!customReportDto.getCompanies().contains(companyService.getCompanyByCode(cellDto.getValue()).getId())) {
					customReportDto.getCompanies().add(companyService.getCompanyByCode(cellDto.getValue()).getId());
				}
			}
			break;
		case "INTERVAL" :
			if (!customReportDto.getIntervals().contains(referenceService.getInterval(cellDto.getValue()).getId())) {
				customReportDto.getIntervals().add(referenceService.getInterval(cellDto.getValue()).getId());
			}
			break;
		case "ITEM" :
			ItemAndModel itemAndModel = itemAndModels.get(cellDto.getRow());
			if (null != itemAndModel) {
				Item item = itemService.getItem(itemAndModel.getItemCode());
				if (null == item) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Item error. This column should contain items codes. The cell value is not a valid item code.");
					cellError = true;
				}
				
				Model model = modelService.getModelByName(itemAndModel.getModelName());
				if (null == model) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Model error. This column should contain model names. The cell value is not a valid model name.");
					cellError = true;
				}
				
				if (null == item ||
					null == model) {
					break;
				}

				ModelItem modelItem = modelItemService.getModelItem(item, model);
				if (null == modelItem) {
					// not in the model
					modelItem = createModelItem(item, model, customReportDto.getIntervals(), cellDto);
					if (null == modelItem) {
						break;
					}
				}
				ModelItemDto modelItemDto = new ModelItemDto(modelItem, null);
				if (!customReportDto.getItems().contains(modelItemDto)) {
					customReportDto.getItems().add(modelItemDto);
				}
			}
			break;
		}
	}




	private ModelItem createModelItem(Item item, Model model, List<Integer> intervals, CellDto cellDto) {
		// Not in model

		ItemProperties latestProps = itemService.getLatestPropertiesForItem(item.getId());
		if (null == latestProps){
			cellDto.setErrorFlag(true);
			cellDto.setStyle("error");
			cellDto.setErrorText("Item properties for item " + item.getCode() + " not found.");
			cellError = true;
			return null;
		}
		
		for (int intervalId: intervals) {
			Interval interval = referenceService.getInterval(intervalId);
			if (!latestProps.isRawDataValue(interval)) {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Item " + item.getCode() + " is a calculated item in year " + interval.getName() + ".");
				cellError = true;
			}
		}

		ModelPropertiesMap mpm = null;
		Model parentModel = modelService.getFamilyParentModel(model);
		if(parentModel.getId() == model.getId()){
			// model has no parent or is the parent 
			// add item to model
			mpm = modelItemService.createModelPropertiesMap(model, latestProps);
		}
		else {
			// model has a parent
			ModelItem parentModelItem = modelItemService.getModelItem(item, parentModel);
			if (null == parentModelItem) {
				// item is not in the parent
				// add item to parent and child
				mpm = modelItemService.createModelPropertiesMap(model, latestProps);
				modelItemService.createModelPropertiesMap(parentModel, latestProps);
			}
			else {
				// item is in the parent
				// add parent version to model rather than adding the latest.
				ItemProperties parentProps = parentModel.getItemPropertiesForItem(item.getId());
				mpm = modelItemService.createModelPropertiesMap(model, parentProps);
			}
		}

		ModelItem modelItem = modelItemService.getModelItem(item, model, mpm);
		if (null == modelItem) {
			cellDto.setErrorFlag(true);
			cellDto.setStyle("error");
			cellDto.setErrorText("Item " + item.getCode() + " has not been added to model " + model.getName() + ".");
			cellError = true;
		}

		return modelItem;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}


	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}


	public CompanyService getCompanyService() {
		return companyService;
	}


	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}


	public GroupService getGroupService() {
		return groupService;
	}


	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}


	public ItemService getItemService() {
		return itemService;
	}


	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public ModelService getModelService() {
		return modelService;
	}


	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}


	public ModelItemService getModelItemService() {
		return modelItemService;
	}

	public void setModelItemService(ModelItemService modelItemService) {
		this.modelItemService = modelItemService;
	}

	private void headerRowCell(CellDto cellDto, CustomReportDto customReportDto) {

		switch (cellDto.getCol()) {
		case 0:
			styleForColHeader(cellDto);
			if (cellDto.getValue().equalsIgnoreCase("Acronym") ||
				cellDto.getValue().equalsIgnoreCase("Company")) {
				// let users use either 'acronym' or 'company' to indicate acronym.
				colNameToNumber.put("acronym", cellDto.getCol());
				customReportDto.setDisplayCompanyAcronym(true);
			}
			else {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. This column should contain company acronyms. The text in the cell should be 'Company'.");
				structuralError = true;
			}
			break;
		case 1:
			styleForColHeader(cellDto);
			if (cellDto.getValue().equalsIgnoreCase("Reference")) {
				colNameToNumber.put("bonCode", cellDto.getCol());
				customReportDto.setDisplayBoncode(true);
			}
			else {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. This column should contain item codes. The text in the cell should be 'Reference'.");
				structuralError = true;
			}
			break;
		case 2:
			styleForColHeader(cellDto);
			if (cellDto.getValue().equalsIgnoreCase("Item description")) {
				colNameToNumber.put("itemDesc", cellDto.getCol());
				customReportDto.setDisplayDesc(true);
			}
			else {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. This column should contain item descriptions. The text in the cell should be 'Item description'.");
				structuralError = true;
			}
			break;
		case 3:
			styleForColHeader(cellDto);
			if (cellDto.getValue().equalsIgnoreCase("Unit")) {
				colNameToNumber.put("unit", cellDto.getCol());
				customReportDto.setDisplayUnit(true);
			}
			else {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. This column should contain item units. The text in the cell should be 'Unit'.");
				structuralError = true;
			}
			break;
		case 4:
			styleForColHeader(cellDto);
			if (cellDto.getValue().equalsIgnoreCase("Model") ||
				cellDto.getValue().equalsIgnoreCase("Destination model")) {
				// let users use either 'model' or 'destination model' to indicate model. 
				colNameToNumber.put("model", cellDto.getCol());
				customReportDto.setDisplayModel(true);
			}
			else {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Structural error. This column should contain models. The text in the cell should be 'Model'.");
				structuralError = true;
			}
			break;
		default:
			styleForColHeader(cellDto);
			if (null == referenceService.getInterval(cellDto.getValue())) {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Interval error. This cell should contain an interval. The cell value is not a valid interval.");
				cellError = true;
				break;
			}
			storeAxisMemberToAxis(customReportDto, cellDto, topPrimary);
			break;
		}
	}
	
	private void blankRowCell(CellDto cellDto) {
		if (cellDto.getValue() != null &&
			!cellDto.getValue().trim().isEmpty()) {
			cellDto.setErrorFlag(true);
			cellDto.setStyle("error");
			cellDto.setErrorText("Structural error. This row should be blank.");
			structuralError = true;
		}
	}

	private void dataRowCell(CellDto cellDto, CustomReportDto customReportDto) {
		if (cellDto.getCol() == colNameToNumber.get("acronym")) {
			styleForRowHeader(cellDto);
			if (null == suppliedCompany) {
				// No supplied company so use the ones in the table.
				if (null == cellDto.getValue() ||
					!companyService.isCompanyCode(cellDto.getValue())) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Company error. This column should contain company acronyms. The cell value is not a valid company acronym.");
					cellError = true;
					return;
				}
				storeAxisMemberToAxis(customReportDto, cellDto, leftPrimary);
			}
			else {
				// A company has been supplied so its an interchangable company report. Don't store any company to the report and check that the table cell is empty!
				if (null != cellDto.getValue() &&
					!cellDto.getValue().isEmpty()) {
					cellDto.setErrorFlag(true);
					cellDto.setStyle("error");
					cellDto.setErrorText("Company error. As company has been selected for the report, this column should NOT contain company acronyms. This cell should be blank.");
					cellError = true;
					return;
				}
			}
		}
		else if (cellDto.getCol() == colNameToNumber.get("bonCode")) {
			styleForRowHeader(cellDto);
			if (null == cellDto.getValue() ||
				null == itemService.getItem(cellDto.getValue())) {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Item error. This column should contain items codes. The cell value is not a valid item code.");
				cellError = true;
				return;
			}
			itemAndModels.put(cellDto.getRow(), new ItemAndModel(cellDto.getValue()));
		}
		else if (cellDto.getCol() == colNameToNumber.get("model")) {
			styleForRowHeader(cellDto);
			if (null == cellDto.getValue() ||
				null == modelService.getModelByName(cellDto.getValue())) {
				cellDto.setErrorFlag(true);
				cellDto.setStyle("error");
				cellDto.setErrorText("Model error. This column should contain model names. The cell value is not a valid model name.");
				cellError = true;
				return;
			}
			ItemAndModel itemAndModel = itemAndModels.get(cellDto.getRow());
			if (null == itemAndModel) {
				return; // boncode must have been in error or we would have a itemAndModel.
			}
			itemAndModel.setModelName(cellDto.getValue());
			storeAxisMemberToAxis(customReportDto, cellDto, leftSecondary);
		}
		else if (cellDto.getCol() == colNameToNumber.get("itemDesc") ||
				 cellDto.getCol() == colNameToNumber.get("unit") ) {
			styleForRowHeader(cellDto);
		}
		else {
			// data
			styleForData(cellDto);
		}
	}

	private void styleForData(CellDto cellDto) {
		cellDto.setStyle("input");
		cellDto.setDataType("input");
	}
	
	private void styleForColHeader(CellDto cellDto) {
		cellDto.setCellFormat("text");
		cellDto.setStyle("col_header");
	}

	private void styleForRowHeader(CellDto cellDto) {
		cellDto.setCellFormat("text");
		cellDto.setStyle("row_header");
	}

	private class ItemAndModel {
		private String itemCode;
		private String modelName;
		
		public ItemAndModel(String itemCode) {
			this.itemCode = itemCode;
		}

		public ItemAndModel(String itemCode, String modelName) {
			this.itemCode = itemCode;
			this.modelName = modelName;
		}

		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		public String getModelName() {
			return modelName;
		}

		public void setModelName(String modelName) {
			this.modelName = modelName;
		}
	}
}
