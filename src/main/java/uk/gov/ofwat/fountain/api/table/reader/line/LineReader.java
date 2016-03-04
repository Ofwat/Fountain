package uk.gov.ofwat.fountain.api.table.reader.line;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import uk.gov.ofwat.fountain.api.ConfidenceGradeService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.api.table.reader.Section;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.Formatter;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.form.FormDisplayCell;
import uk.gov.ofwat.fountain.domain.form.PageForm;
import uk.gov.ofwat.fountain.domain.form.PageSection;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public abstract class LineReader {

	protected static Log log = LogFactory.getLog(LineReader.class);
	private Formatter formatter = new Formatter();

	protected DataTable dataTable;
	protected TableUploadMetaData metaData;
	protected FormDisplayCell[] cells;
	protected Section section;
	protected Sheet sheet;
	protected ItemService itemService;
	protected ConfidenceGradeService confidenceGradeService;
	protected Row row;
	protected ReferenceService referenceService;

	public abstract void read(GroupEntry groupEntry, Company company);

	public void initialise(DataTable dataTable, TableUploadMetaData metaData,
			FormDisplayCell[] cells, Section section, Sheet sheet, Row row) {
		this.dataTable = dataTable;
		this.metaData = metaData;
		this.cells = cells;
		this.section = section;
		this.sheet = sheet;
		this.row = row;
	}

	public boolean newSection(Row row, PageSection nextSection) {
		for (PageForm form: nextSection.getPageForms()) {
			 FormDisplayCell[][] cellGrid = form.getCellGrid();
				 for(FormDisplayCell[] line: cellGrid) {
					int colIdx = 0;
					for(FormDisplayCell cell: line){
						if (null != cell) {
//							System.out.println("Model: '" + cell.getCellContents() + "' Spreadsheet: '" + row.getCell(colIdx) + "'");
				    		switch(cell.getCellType()) {
					    		case EMPTY:
					    			colIdx++;
					    			break;
					    		case COL_HEADING:
					    			Cell spreadsheetCell = row.getCell(colIdx);
					    			// Check that the spreadsheet cell content is the same as the model. 
					    			if (null == spreadsheetCell) {
					    				// The spreadsheetCell can be null as long as there is nothing in the model cell.
					    				if (!cell.getCellContents().isEmpty()) {
					    					return false;
					    				}
					    			}
					    			else {
					    				try {
											if (!cell.getCellContents().equals(spreadsheetCell.getStringCellValue())) {
												// The next section and the current row are not the same.
//												System.out.println("Not a new section");
												return false;
											}
										} catch (Exception e) {
//											System.out.println("Not a new section (not a String cell)");
											return false;
										}
					    			}
					    			colIdx++;
					    			break;
					    		case ROW_HEADING_NON_REPEAT:
					    		case ROW_HEADING:
					    		case CALC:
					    		case COPYCELL:
					    		case INPUT:
					    			colIdx++;
					    			break;
							 }
						 }
					 }
				 }
			 }
			System.out.println("Is a new section at row " + row.getRowNum() +1);
			return true;
	 }

	public boolean isSectionHeader(Row row) {
			boolean isSectionHeader = true;
			for(FormDisplayCell cell: cells){
				if (null != cell) {
		    		switch(cell.getCellType()) {
			    		case EMPTY:
			    		case COL_HEADING:
			    		case ROW_HEADING_NON_REPEAT:
			    		case ROW_HEADING:
			    			break;
			    		case CALC:
			    		case COPYCELL:
			    		case INPUT:
			    			isSectionHeader = false;
			    			break;
					 }
				 }
			 }
			return isSectionHeader;
	 }

	public boolean isEmpty(Row row) {
		boolean isEmpty = true;
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (!isCellEmpty(cell)) {
				isEmpty = false;
				break;
			}
		}	
		 return isEmpty;
	 }

	public boolean allCalcs() {
		if (hasDataCells()) {
			return false;
		}
		if (hasCalcCells()) {
			return true;
		}
		return false;
	}

	public boolean isBlank(Company company, GroupEntry ge,
			Map<String, DataDto> dataLookup) {
		if (hasData(company, ge, dataLookup)) {
			return false;
		}
		if (allCalcs()) {
			return false;
		}
		return true;
	}

	public boolean hasDataCells() {
		for (FormDisplayCell cell : cells) {
			if (null != cell) {
				if (cell.getCellType().equals(CellType.COPYCELL)
						|| cell.getCellType().equals(CellType.INPUT)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasCalcCells() {
		for (FormDisplayCell cell : cells) {
			if (null != cell) {
				if (cell.getCellType().equals(CellType.CALC)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasData(Company company, GroupEntry ge,
			Map<String, DataDto> dataLookup) {
		for (FormDisplayCell cell : cells) {
			if (null != cell) {
				if (cell.getCellType().equals(CellType.COPYCELL)
						|| cell.getCellType().equals(CellType.INPUT)) {
					DataKey key = new DataKey(cell.getCellContents()); // for
																		// data
																		// cells
																		// getCellContents()
																		// is
																		// the
																		// key.
					key.setCompanyId(company.getId());
					if (null != ge) {
						key.setGroupEntryId(ge.getId());
					}
					if (key.isCg()) {
						key.setCg(false);
						DataDto dto = dataLookup.get(key.getKey(true));
						if (null != dto && !dto.getConfidenceGrade().isEmpty()) {
							return true;
						}
					} else {
						DataDto dto = dataLookup.get(key.getKey(true));
						if (null != dto && !dto.getValue().isEmpty()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public String readHeaderCell(Row row, int cellIdx) {
		Cell cell = row.getCell(cellIdx);
		if (null == cell) {
			return null;
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING ||
			cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			try {
				RichTextString value = cell.getRichStringCellValue();
				return value.getString();
			} catch (IllegalStateException e) {
				log.warn("Table import. Table " + metaData.getTableName()
						+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
						+ (cellIdx + 1) + " cell is not a string. CELL TYPE is "
						+ cell.getCellType() + ". Cannot read cell.");
				metaData.addWarning(row.getRowNum(), cellIdx, " current value: " + " cell is not string. " +
						"CELL TYPE is " + cell.getCellType() + ". Cannot read cell.");
			}
		}
		
		log.warn("Table import. Table " + metaData.getTableName()
				+ " Header cell at row: " + (row.getRowNum() + 1)
				+ " col: " + (cellIdx + 1) + " cell is is not a string. CELL TYPE is "
				+ cell.getCellType());
		return null;
	}

	public void readUnchangableCell(Row row, int cellIdx,
			FormDisplayCell tableCell, DataDto dto) {
		Cell cell = row.getCell(cellIdx);
		if (null == cell) {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: " + dto.getValue()
					+ " cell is null.");
		}
		if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC &&
			cell.getCellType() != Cell.CELL_TYPE_FORMULA) {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: " + dto.getValue()
					+ " cell is is not numeric.");
		}

		double editedValue = cell.getNumericCellValue();
		if("0.00%".equals(cell.getCellStyle().getDataFormatString())) { 
			// Percent
			editedValue = editedValue*100;
		} 
		String editedValueString = formatter.formatValueToAtLeastDP(dto, editedValue);

		if (!editedValueString.equals(dto.getValue())
				&& !editedValueString.isEmpty()) {
			// value has changed so log a warning
			metaData.addWarning(row.getRowNum(), cellIdx, dto.getValue(),
					editedValueString, "This is a "
							+ tableCell.getCellType().name()
							+ " cell. It cannot be changed.");
		}
	}

	public String readDataCell(Row row, int cellIdx, DataDto dto,
			CellType cellType) {
		Cell cell = row.getCell(cellIdx);
		if (null == cell) {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: " + dto.getValue()
					+ " cell is null.");
			return null;
		}

		String editedValueString = null;
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC ||
			cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			try {
				double editedValue = cell.getNumericCellValue();
				if("0.00%".equals(cell.getCellStyle().getDataFormatString())) { 
					// Percent
					editedValue = editedValue*100;
				} 
				editedValueString = formatter.formatValueToAtLeastDP(dto, editedValue);
			} catch (IllegalStateException e) {
				log.warn("Table import. Table " + metaData.getTableName()
						+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
						+ (cellIdx + 1) + " current value: " + dto.getValue()
						+ " cell is not numeric. CELL TYPE is "
						+ cell.getCellType() + ". Cannot read cell.");
				metaData.addWarning(row.getRowNum(), cellIdx, " current value: " + dto.getValue()
						+ " cell is not numeric. CELL TYPE is "
						+ cell.getCellType() + ". Cannot read cell.");
			}

		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			RichTextString editedValue = cell.getRichStringCellValue();
			if (editedValue.getString().isEmpty()) {
				editedValueString = "";
			} else {
				if (isNumberInString(editedValue.getString())) {
					editedValueString = editedValue.getString();
				} else {
					log.warn("Table import. Table " + metaData.getTableName()
							+ " Cell at row: " + (row.getRowNum() + 1)
							+ " col: " + (cellIdx + 1) + " current value: "
							+ dto.getValue() + " data '"
							+ editedValue.getString()
							+ "' is not numeric. CELL TYPE is "
							+ cell.getCellType());
				}
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			editedValueString = "";
		} else {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: " + dto.getValue()
					+ " cell is not numeric. CELL TYPE is "
					+ cell.getCellType());
		}

		return editedValueString;
	}

	private boolean isNumberInString(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	protected void updateDtoValue(DataDto dto, CellType cellType,
			String editedValueString) {
		if (null == editedValueString) {
			return;
		}
		if (null == dto) {
			return;
		}
		if (editedValueString.equals(dto.getValue())) {
			return;
		}

		// value has changed so store it as an edit. Blank value is valid.
		if (CellType.INPUT == cellType || CellType.COPYCELL == cellType) {
			if (dto.getItem().getUnit().equals("%")) {
				editedValueString = String.valueOf(Double.parseDouble(editedValueString)/100); 
			}
			dto.setUpdatedValue(editedValueString);
		}
	}

	protected void reportInvalidChange(int row, int col, DataDto dto,
			CellType cellType, String editedValueString) {
		if (null == editedValueString) {
			return;
		}
		if (null == dto) {
			return;
		}
		if (editedValueString.equals(dto.getValue())) {
			return;
		}

		// value has changed so report it.
		if (CellType.COPYCELL == cellType) {
			if (null == dto.getItem() || null == dto.getIntervalDto()) { 
				metaData.addWarning(row, col, dto.getValue(), editedValueString, "Historic values cannot be changed on import.");
			}
			else {
				metaData.addWarning(row, col, dto.getValue(), editedValueString, "Historic values cannot be changed on import. Item: " + dto.getItem().getCode() + " Interval: " + dto.getIntervalDto().getName());
			}
		}
	}

	public String readCGCell(Row row, int cellIdx, DataDto dto,
			CellType cellType) {
		Cell cell = row.getCell(cellIdx);
		if (null == cell) {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: "
					+ dto.getConfidenceGrade() + " cell is null.");
			return null;
		}
		if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
			log.warn("Table import. Table " + metaData.getTableName()
					+ " Cell at row: " + (row.getRowNum() + 1) + " col: "
					+ (cellIdx + 1) + " current value: "
					+ dto.getConfidenceGrade()
					+ " CG cell is not of type String.");
			return null;
		}

		RichTextString cg = cell.getRichStringCellValue();
		return cg.getString();
	}

	protected void updateDtoConfidenceGrade(Row row, int cellIdx, DataDto dto,
			CellType cellType, String editedCGString) {
		if (null == editedCGString) {
			return;
		}
		if (editedCGString.isEmpty()) {
			return;
		}
		if (editedCGString.equals(dto.getConfidenceGrade())) {
			return;
		}
		// value has changed so convert it to CG index and store it as an edit
		ConfidenceGrade confidenceGrade = confidenceGradeService
				.getConfidenceGrade(editedCGString);
		if (null == confidenceGrade) {
			metaData.addWarning(row.getRowNum(), cellIdx, dto.getValue(),
					editedCGString,
					"This is not a valid value for a Confidence Grade. It cannot be changed.");
			return;
		}
		if (CellType.INPUT == cellType) {
			// value has changed so store it as an edit.
			dto.setUpdatedConfidenceGrade("" + confidenceGrade.getId());
		}
	}

	protected DataKey makeKey(GroupEntry groupEntry, Company company,
			FormDisplayCell cell) {
		DataKey key = new DataKey(cell.getCellContents()); // for data cells
															// getCellContents()
															// is the key.
		key.setCompanyId(company.getId());
		key.setGroupEntryId(groupEntry.getId());
		return key;
	}

	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}

	public TableUploadMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(TableUploadMetaData metaData) {
		this.metaData = metaData;
	}

	public FormDisplayCell[] getCells() {
		return cells;
	}

	public void setCells(FormDisplayCell[] cells) {
		this.cells = cells;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ConfidenceGradeService getConfidenceGradeService() {
		return confidenceGradeService;
	}

	public void setConfidenceGradeService(
			ConfidenceGradeService confidenceGradeService) {
		this.confidenceGradeService = confidenceGradeService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}

	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	protected void reportMissingItemCodeAndInterval(String rowItemCode, int colIdx) {
		metaData.addError("Item not found in model. Item: " + section.getColumn(colIdx).getCodePrefix() + "_" + rowItemCode + " Year: " + referenceService.getInterval(section.getColumn(colIdx).getIntervalId()));
	}

	protected boolean isCellEmpty(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK){
			return true;
		}
		try {
			String cellStringValue = cell.getStringCellValue();
			if (!cellStringValue.isEmpty()) {
    			return false;
			}
		} catch (Exception e) {
			// Do nothing. Its just not a String cell. 
		}
		try {
			Double cellNumericValue = cell.getNumericCellValue();
			if (0 != cellNumericValue) { 
				// from the poi documentation 'For blank cells we return a 0.'. Groan! So since a zero is a valid value, how do I test for an empty numeric cell?
    			return false;
			}
		} catch (Exception e) {
			// Do nothing. Its just not a Numeric cell. 
		}
		// Its either empty or a not a string or numeric.
		return true;
	}
}
