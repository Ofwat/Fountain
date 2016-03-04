package uk.gov.ofwat.fountain.api.itemTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.ItemTableService;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.InflationType;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.PriceBase;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.RowDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public class ModelDictionaryParser extends ItemTableParser {
	
	private static Logger logger = LoggerFactory.getLogger(ModelDictionaryParser.class);

	private Map <String, Integer> colNameToNumber;
	private Team team;
	private Boolean valid;

	private static final int NUM_OF_COLS = 15;
	
	private final TableDto parsedTableDto;
	private ItemTableService itemTableService;
	
	private ParserCounts counts;

	
	private String code; 
	private String unit;
	private String description;
	private Group group;
	private Integer dp;
	private PriceBase priceBase;
	private InflationType inflationType;
	private CodeList codeList;

	private String definition;

	
	public ParserCounts getCounts() {
		return counts;
	}

	public ModelDictionaryParser(ItemTableService itemTableService, final TableDto itemTableDto) {
		this.itemTableService = itemTableService;
		this.parsedTableDto = itemTableDto.clone();
		this.counts = new ParserCounts();
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.itemTable.ItemTableParser#parse(uk.gov.ofwat.fountain.rest.dto.TableDto)
	 */
	@Transactional
	public TableDto parse() {
		logger.info("Adding new items - start.");

		parseTable();
		
		logger.info("Adding new items - " + (counts.rowCount-1) + " rows processed.");
		logger.info("Adding new items - " + counts.validRows + " valid rows.");
		logger.info("Adding new items - " + counts.invalidRows + " invalid rows.");
		logger.info("Adding new items - " + counts.itemsAdded + " items added.");
		logger.info("Adding new items - " + counts.itemPropertiesAdded + " item properties added.");
		logger.info("Adding new items - " + counts.itemDuplicates + " item duplicates.");
		logger.info("Adding new items - " + counts.itemRedefinitions + " item redefinitions. Cannot redefine an item.");
		
		if (!valid) {
			logger.error("Adding new items - ItemTable is invalid. Any items added will be rolled back.");
		}
		logger.info("Adding new items - complete.");
		return parsedTableDto;
	}
	
	public void parseTable() {
		logger.info("Adding new items - start.");

		valid = true;
		boolean headerValid = true;
		colNameToNumber = new HashMap<String, Integer>();
		team = itemTableService.getTeamService().getTeamByName("Universal");

		for (RowDto rowDto: parsedTableDto.getRows()) {
			if (0 == counts.rowCount) {
				// second row - top header.
				headerValid = headerRow(rowDto, counts.rowCount);
			}
			else {
				// other rows
				if (!headerValid) {
					break; // Let users deal with the header errors first.
				}
				removeLeadingSingleQuoteFromUnit(rowDto);
				itemRow(rowDto, counts.rowCount);
				fixUnitsWithThreeZeros(rowDto);
				
				if (((counts.rowCount-1) % 100) == 0) {
					logger.info("Adding new items - " + (counts.rowCount-1) + " items processed.");
				}
			}
			counts.rowCount++;
		}
		
		if (!valid) {
			logger.error("Adding new items - ItemTable is invalid. Any items added will be rolled back.");
		}
		logger.info("Adding new items - complete.");
		return;
	}
	
	private void removeLeadingSingleQuoteFromUnit(RowDto rowDto) {
		List<CellDto> cellDtos = rowDto.getCells();
		CellDto unitCell = cellDtos.get(colNameToNumber.get("unit"));
		unit = unitCell.getValue();
		if (null != unit && unit.startsWith("'")) {
			unit = unit.substring(1, unit.length());
			unitCell.setValue(unit);
		}
	}

	private void fixUnitsWithThreeZeros(RowDto rowDto) {
		List<CellDto> cellDtos = rowDto.getCells();
		CellDto unitCell = cellDtos.get(colNameToNumber.get("unit"));
		unit = unitCell.getValue();
		if (null != unit && unit.equals("000")) {
			unit = "'000";
			unitCell.setValue(unit);
		}
	}

	private boolean headerRow(RowDto rowDto, int rowCount) {
		List<CellDto> cellDtos = rowDto.getCells();
		if (cellDtos.size() < 4) {
			CellDto cellDto = cellDtos.get(0);
			if (null == cellDto) {
//				There are no cells in this row so add an error cell.
				cellDto = createCell(rowDto, rowCount, 0);
				rowDto.getCells().add(cellDto);
			}
			setCellError(cellDto, "Error. There should be " + NUM_OF_COLS + " columns: 'BonCode', 'Version', 'Description', 'Unit', 'Group', 'DPs', 'Price Base', 'Index', 'List', 'TotalEquation', 'Purpose', 'MergingPrinciple', 'Owner', 'Definition' and 'Equation'.");
			valid = false;
			return false;
		}

		valid = valid & addToColNameToNumber(cellDtos, 0, "BonCode", colNameToNumber, "bonCode");
		valid = valid & addToColNameToNumber(cellDtos, 1, "Version", colNameToNumber, "version");
		valid = valid & addToColNameToNumber(cellDtos, 2, "Description", colNameToNumber, "itemDesc");
		valid = valid & addToColNameToNumber(cellDtos, 3, "Unit", colNameToNumber, "unit");
		valid = valid & addToColNameToNumber(cellDtos, 4, "Group", colNameToNumber, "group");
		valid = valid & addToColNameToNumber(cellDtos, 5, "DPs", colNameToNumber, "dp");
		valid = valid & addToColNameToNumber(cellDtos, 6, "Price Base", colNameToNumber, "priceBase");
		valid = valid & addToColNameToNumber(cellDtos, 7, "Index", colNameToNumber, "index");
		valid = valid & addToColNameToNumber(cellDtos, 8, "List", colNameToNumber, "list");
		valid = valid & addToColNameToNumber(cellDtos, 9, "TotalEquation", colNameToNumber, "totalEquation");
		valid = valid & addToColNameToNumber(cellDtos, 10, "Purpose", colNameToNumber, "purpose");
		valid = valid & addToColNameToNumber(cellDtos, 11, "MergingPrinciple", colNameToNumber, "mergingPrinciple");
		valid = valid & addToColNameToNumber(cellDtos, 12, "Owner", colNameToNumber, "owner");
		valid = valid & addToColNameToNumber(cellDtos, 13, "Definition", colNameToNumber, "definition");
		valid = valid & addToColNameToNumber(cellDtos, 14, "Equation", colNameToNumber, "equation");
		return valid;
	}

	private CellDto createCell(RowDto rowDto, int rowCount, int cellCount) {
		CellDto cellDto = new CellDto();
		cellDto.setRow(rowCount);
		cellDto.setCol(cellCount);
		cellDto.setDecimalPlaces(0);
		cellDto.setKey("");
		cellDto.setErrorFlag(false);
		cellDto.setErrorText("");
		cellDto.setCellType(CellType.ROW_HEADING);
		cellDto.setCellFormat("text");
		cellDto.setStyle("text");
		cellDto.setValue("");
		return cellDto;
	}
	
	private void itemRow(RowDto rowDto, int rowCount) {
		resetItemVariables();
		
		List<CellDto> cellDtos = rowDto.getCells();
		if (cellDtos.size() < NUM_OF_COLS) {
			// error
			CellDto cellDto = cellDtos.get(0);
			if (null == cellDto) {
//				There are no cells in this row so add an error cell.
				cellDto = createCell(rowDto, rowCount, 0);
				rowDto.getCells().add(cellDto);
			}
			setCellError(cellDto, "Item Error. All columns need to have a value.");
			valid = false;
			return;
		}

		if (validateItemRow(cellDtos)) {
			createItemAndItemProperties(cellDtos);
		}
	}

	private boolean validateItemRow(List<CellDto> cellDtos) {
		boolean validRow = true;
		code = cellDtos.get(colNameToNumber.get("bonCode")).getValue();
		if (null == code || code.isEmpty()) {
			recordInvalidRow(cellDtos, "bonCode", "There is no BonCode. There must be a BonCode.");
			validRow = false;
		}
		else {
			RE codeRegexp = null;
			try {
				codeRegexp = new RE(CODE_TOKEN_REGEXP);
			} catch (RESyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean matches = codeRegexp.match(code);
			if (!matches) {
				recordInvalidRow(cellDtos, "bonCode", "Error: Reference is not a valid Bon code. " +
						"Reference (BON code) should consist of the following in sequence: " +
						"An upper case letter. " +
						"Zero or more upper case letters or underscores. " +
						"At least one digit. " +
						"Zero or more number upper case letters, underscores, or digits.");
				validRow = false;
			}
		}
		
		description = cellDtos.get(colNameToNumber.get("itemDesc")).getValue();
		if (null == description || description.isEmpty()) {
			recordInvalidRow(cellDtos, "itemDesc", "Error: There is no Description. There must be a Description.");
			validRow = false;
		}
		else {
			if (description.equalsIgnoreCase("Awaiting details from Sam")) {
				recordInvalidRow(cellDtos, "itemDesc", "Error: Awaiting details from Sam.");
				validRow = false;
			}
			if (description.length() > 255) {
				recordInvalidRow(cellDtos, "itemDesc", "Error: Description must be less than 256 characters.");
				validRow = false;
			}
		}

		// Ignore the version. We are going to set it for the user.
		
		unit = cellDtos.get(colNameToNumber.get("unit")).getValue();
		if (null == unit || unit.isEmpty()) {
			recordInvalidRow(cellDtos, "unit", "Error: There is no Unit. There must be a Unit.");
			validRow = false;
		}
		else {
			if (unit.length() > 20) {
				recordInvalidRow(cellDtos, "unit", "Error: Unit must be less than 21 characters.");
				validRow = false;
			}
		}
		
		String groupCode = cellDtos.get(colNameToNumber.get("group")).getValue();
		// It is valid to have no group code.
		if (null != groupCode && !groupCode.isEmpty()) {
			try {
				group = itemTableService.getGroupService().getGroupByCode(groupCode.toUpperCase());
			} catch (Exception e) {
				group = null;
			}
			if (null == group) {
				recordInvalidRow(cellDtos, "group", "Error: Group must be one of 'NONE', 'REGION', 'SEF', 'STW', 'WRZ', 'TRCAP', 'TRPAL', 'DISP', 'WEM', 'AREAS', 'BORROWINGS', 'CUSTOMERSIZES', 'INVPROPS', 'CLAIMS', 'SUBPROGS', 'NOCON', 'OTHCHG'.");
				validRow = false;
			}
		}
		
		try {
			String dpString = cellDtos.get(colNameToNumber.get("dp")).getValue();
			dp = Integer.parseInt(dpString);
		} catch (NumberFormatException e) {
			recordInvalidRow(cellDtos, "dp", "Error: DP must be a positive whole number.");
			validRow = false;
		}
		
		String priceBaseCode = cellDtos.get(colNameToNumber.get("priceBase")).getValue();
		if (null != priceBaseCode && !priceBaseCode.isEmpty()) {
			// It is valid to have no price cakes.
			try {
				priceBase = itemTableService.getReferenceService().getPriceBase(priceBaseCode.toUpperCase());
			} catch (Exception e) {
				priceBase = null;
			}
			if (null != unit && !unit.equalsIgnoreCase("Text") &&
				null == priceBase ) {
				recordInvalidRow(cellDtos, "priceBase", "Error: Price Base must be one of 'BASEYR', 'OUTTURN', 'NONE', '2002-03', '2006-07', 'BSK YR', 'OT BSK YR', '2007-08'.");
				validRow = false;
			}
		}
		
		String inflationTypeCode = cellDtos.get(colNameToNumber.get("index")).getValue();
		if (null != inflationTypeCode && !inflationTypeCode.isEmpty()) {
			// It is valid to have no inflation type.
			try {
				inflationType = itemTableService.getReferenceService().getInflationType(inflationTypeCode);
			} catch (Exception e){
				inflationType = null;
			}
			if (null == inflationType) {
				recordInvalidRow(cellDtos, "index", "Error: Index must be one of 'Tolerance', 'STATE', 'TWCLASS', 'YESNO', 'RATING', 'FQPERF'.");
				validRow = false;
			}
		}

		String codeListCode = cellDtos.get(colNameToNumber.get("list")).getValue();
		if (null != codeListCode && !codeListCode.isEmpty()) {
			// It is valid to have no list code.
			try {
				codeList = itemTableService.getItemService().getCodeList(codeListCode);
			} catch (Exception e) {
				codeList = null;
			}
			if (null == codeList) {
				recordInvalidRow(cellDtos, "list", "Error: List must be one of ''Avg', 'FYA', 'Bsk Yr', 'COPI', 'NONE', 'FYE'.");
				validRow = false;
			}
		}
		
		//TODO validate totalEquation? Is it used?
		cellDtos.get(colNameToNumber.get("totalEquation")).getValue();
		//TODO validate purpose? Is it used?
		cellDtos.get(colNameToNumber.get("purpose")).getValue();
		//TODO validate mergingPrinciple? Is it used?
		cellDtos.get(colNameToNumber.get("mergingPrinciple")).getValue();
		//TODO validate owner? Is it used?
		cellDtos.get(colNameToNumber.get("owner")).getValue();

		// Don't think there is any way to validate definition.
		definition = cellDtos.get(colNameToNumber.get("definition")).getValue();

		//TODO validate equation? Is it used?
		cellDtos.get(colNameToNumber.get("equation")).getValue();
		
		if (validRow) {
			counts.validRows++;
		}
		else {
			counts.invalidRows++;
		}
		return validRow;
	}
	
	private void recordInvalidRow(List<CellDto> cellDtos, String col, String errorMessage) {
		CellDto codeCellDto = cellDtos.get(colNameToNumber.get("bonCode"));
		CellDto errorCellDto = cellDtos.get(colNameToNumber.get(col));
		logger.error("Invalid dictionary row " + codeCellDto.getRow() + ". " + codeCellDto.getValue() + ". " + errorMessage);
		setCellError(errorCellDto, "Error: " + errorMessage);
	}

	private void createItemAndItemProperties(List<CellDto> cellDtos) {
		Item newItem = createItem(cellDtos);
		Item existingItem = itemTableService.getItemService().getItem(newItem.getCode());
		if (null == existingItem) {
			// new item created so create item properties 
			itemTableService.getItemService().createItem(newItem);
			ItemProperties itemProperties = composeItemProperties(cellDtos, newItem, 1, 1);
			itemTableService.getItemService().createItemProperties(itemProperties);
			setVersionInCell(cellDtos, 1);
			counts.itemsAdded++;
			return;
		}
		// item exists
		if (!newItem.equalsExecptingVersionedData(existingItem)) {
			// Trying to redefine an existing item.
			CellDto cellDto = cellDtos.get(colNameToNumber.get("bonCode"));
			setCellError(cellDto, "Item Error. Item is already in use. " + existingItem.toString());
			counts.itemRedefinitions++;
			return;
		}
		// Item the same as existing one
		ItemProperties existingItemProperties = itemTableService.getItemService().getLatestPropertiesForItem(existingItem.getId());
		if (null == existingItemProperties) {
			// There are no ips for an existing item! Should never happen. Create a new ips.
			ItemProperties itemProperties = composeItemProperties(cellDtos, existingItem, 1, 1);
			itemTableService.getItemService().createItemProperties(itemProperties);
			setVersionInCell(cellDtos, 1);
			counts.itemPropertiesAdded++;
			return;
		}
		// itemProperties exists
		// Comparison must ignore versions as we don't know them for the new ip. So compose the IP with existing versions.
		ItemProperties itemProperties = composeItemProperties(cellDtos, existingItem, existingItemProperties.getReservoirVersion(), existingItemProperties.getVersion());
		if (!itemProperties.equalsExecptingId(existingItemProperties)) {
			// Add a new itemProperties.
			// Increment versions.
			itemProperties.setReservoirVersion(existingItemProperties.getReservoirVersion() +1);
			itemProperties.setVersion(existingItemProperties.getVersion() +1);
			itemTableService.getItemService().createItemProperties(itemProperties);
			setVersionInCell(cellDtos, existingItemProperties.getReservoirVersion() +1);
			counts.itemPropertiesAdded++;
			return;
		}
		// Item and itemProperties are exactly the same as existing ones. Don't tell the user about this.
		// It can only vary by equation and we won't know those until the model is imported.
		setVersionInCell(cellDtos, existingItemProperties.getReservoirVersion());
		counts.itemDuplicates++;
		return;
	}

	private void setVersionInCell(List<CellDto> cellDtos, int versionNo) {
		CellDto versionCellDto = cellDtos.get(colNameToNumber.get("version"));
		versionCellDto.setValue("" + versionNo);
		cellDtos.set(colNameToNumber.get("version"), versionCellDto);
	}

	private Item createItem(List<CellDto> cellDtos) {
		Item item = new Item();
		String code = cellDtos.get(colNameToNumber.get("bonCode")).getValue();
		item.setCode(code);
		item.setLatestDescription(cellDtos.get(colNameToNumber.get("itemDesc")).getValue());
		item.setUnit(cellDtos.get(colNameToNumber.get("unit")).getValue());

		String groupCode = cellDtos.get(colNameToNumber.get("group")).getValue();
		Group group;
		if (null != groupCode && !groupCode.isEmpty()) {
			group = itemTableService.getGroupService().getGroupByCode(groupCode.toUpperCase());
		}
		else {
			group = itemTableService.getGroupService().getGroupByCode("NONE");
		}
		item.setGroup(group);

		item.setTeam(team);

		String codeListCode = cellDtos.get(colNameToNumber.get("list")).getValue();
		if (null != codeListCode && !codeListCode.isEmpty()) {
			CodeList codeList = itemTableService.getItemService().getCodeList(codeListCode);
			item.setCodeList(codeList);
		}
		else {
			item.setCodeList(null);
		}
		return item;
	}
		
	private ItemProperties composeItemProperties(List<CellDto> cellDtos, Item item, int reservoirVersion, int fountainVersion) {

		ItemProperties itemProperties =  
		new ItemProperties(	item, 
							fountainVersion,
							description,
							definition,
							dp, 
							null,  
							false, // fixed formula. What should I put here? 
							null,  // generalFormula
							null,  // groupTotalFormula 
							reservoirVersion,
							false);
		itemProperties.setPurpose(null);
		itemProperties.setInflationType(null);
		itemProperties.setPriceBase(priceBase);
		itemProperties.setInflationType(inflationType);
		// totalEquation not used
		// purpose not used
		// mergingPrinciple not used
		// owner not used
		// equation not used
		return itemProperties;
	}

	private void resetItemVariables() {
		code = null; 
		unit = null;
		description = null;
		group = null;
		dp = 0;
		priceBase = null;
		inflationType = null;
		codeList = null;
		definition = null;
	}
	
	class ParserCounts {
		int itemPropertiesAdded = 0;
		int rowCount = 0;
		int invalidRows = 0;
		int itemDuplicates = 0;
		int itemRedefinitions = 0;
		int itemsAdded = 0;
		int validRows = 0;
	}
}
