package uk.gov.ofwat.fountain.api.itemTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.IndexService;
import uk.gov.ofwat.fountain.api.ItemTableService;
import uk.gov.ofwat.fountain.api.report.CellType;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.PriceBase;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.RowDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public class ImportItemsParser extends ItemTableParser {
	
	private static Logger logger = LoggerFactory.getLogger(ImportItemsParser.class);

	private Map <String, Integer> colNameToNumber;
	private Team team;
	private Boolean valid;

	private int invalidRows;
	private int itemDuplicates;
	private int itemRedefinitions;
	private int itemsAdded;
	private int validRows;
	
	private static final int NUM_OF_COLS = 6;
	
	private final TableDto parsedTableDto;
	private ItemTableService itemTableService;
	private IndexService indexService;

	public ImportItemsParser(ItemTableService itemTableService, final TableDto itemTableDto, IndexService indexService) {
		this.itemTableService = itemTableService;
		this.parsedTableDto = itemTableDto.clone();
		this.indexService = indexService;
	}
	
	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.itemTable.ItemTableParser#parse(uk.gov.ofwat.fountain.rest.dto.TableDto)
	 */
	@Transactional
	public TableDto parse() {
		logger.info("Adding new items - start.");
		invalidRows = 0;
		validRows = 0;
		itemDuplicates = 0;
		itemRedefinitions = 0;
		itemsAdded = 0;

		valid = true;
		boolean headerValid = true;
		colNameToNumber = new HashMap<String, Integer>();
		team = itemTableService.getTeamService().getTeamByName("Universal");

		int rowCount = 0;
		for (RowDto rowDto: parsedTableDto.getRows()) {
			if (0 == rowCount) {
				// first row - titles.
			}
			else if (1 == rowCount) {
				// second row - top header.
				headerValid = headerRow(rowDto, rowCount);
			}
			else {
				// other rows
				if (!headerValid) {
					break; // Let users deal with the header errors first.
				}
				itemRow(rowDto, rowCount);

				if (((rowCount-2) % 100) == 0) {
					logger.info("Adding new items - " + (rowCount-2) + " items processed.");
				}
			}
			rowCount++;
		}
		
		logger.info("Adding new items - " + (rowCount-2) + " rows processed.");
		logger.info("Adding new items - " + validRows + " valid rows.");
		logger.info("Adding new items - " + invalidRows + " invalid rows.");
		logger.info("Adding new items - " + itemsAdded + " items added.");
		logger.info("Adding new items - " + itemDuplicates + " item duplicates.");
		logger.info("Adding new items - " + itemRedefinitions + " item redefinitions. Cannot redefine an item.");
		
		if (!valid) {
			logger.error("Adding new items - ItemTable is invalid. Any items added will be rolled back.");
		}
		logger.info("Adding new items - complete.");
		return parsedTableDto;
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
			setCellError(cellDto, "Error. There should be " + NUM_OF_COLS + " columns: 'Reference', 'Item description', 'Unit', 'DP', 'Price Base' and 'Definition'.");
			valid = false;
			return false;
		}
		
		valid = valid & addToColNameToNumber(cellDtos, 0, "Reference", colNameToNumber, "bonCode");
		valid = valid & addToColNameToNumber(cellDtos, 1, "Item description", colNameToNumber, "itemDesc");
		valid = valid & addToColNameToNumber(cellDtos, 2, "Unit", colNameToNumber, "unit");
		valid = valid & addToColNameToNumber(cellDtos, 3, "DP", colNameToNumber, "dp");
		valid = valid & addToColNameToNumber(cellDtos, 4, "Price Base", colNameToNumber, "priceBase");
		valid = valid & addToColNameToNumber(cellDtos, 5, "Definition", colNameToNumber, "definition");
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
			Item item = createItem(cellDtos);
			if (null != item) {
				// new item created so create item properties 
				ItemProperties itemProperties = composeItemProperties(cellDtos, item);
				itemTableService.getItemService().createItemProperties(itemProperties);
				
				//Add the item to the search index. 
				indexService.index(Item.class, item.getId());
				
			}
		}
	}

	private boolean validateItemRow(List<CellDto> cellDtos) {
		// all we need to validate is ... Code, Name, Unit, dp
		boolean validRow = true;
		String code = cellDtos.get(colNameToNumber.get("bonCode")).getValue();
		RE codeRegexp = null;
		try {
			codeRegexp = new RE(CODE_TOKEN_REGEXP);
		} catch (RESyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean matches = codeRegexp.match(code);
		if (!matches) {
			setCellError(cellDtos.get(colNameToNumber.get("bonCode")), "Error: Reference is not a valid Bon code. " +
					"Reference (BON code) should consist of the following in sequence: " +
					"An upper case letter. " +
					"Zero or more upper case letters or underscores. " +
					"At least one digit. " +
					"Zero or more number upper case letters, underscores, or digits.");
			validRow = false;
		}
		
		String description = cellDtos.get(colNameToNumber.get("itemDesc")).getValue();
		if (description.length() > 255) {
			setCellError(cellDtos.get(colNameToNumber.get("itemDesc")), "Error: Description must be less than 256 characters.");
			validRow = false;
		}
		
		String unit = cellDtos.get(colNameToNumber.get("unit")).getValue();
		if (unit.length() > 20) {
			setCellError(cellDtos.get(colNameToNumber.get("unit")), "Error: Unit must be less than 21 characters.");
			validRow = false;
		}
		
		try {
			Integer dp = Integer.parseInt(cellDtos.get(colNameToNumber.get("dp")).getValue());
		} catch (NumberFormatException e) {
			setCellError(cellDtos.get(colNameToNumber.get("dp")), "Error: DP must be a positive whole number.");
			validRow = false;
		}
		
		String priceBaseCode = cellDtos.get(colNameToNumber.get("priceBase")).getValue();
		PriceBase priceBase = null;
		try {
			priceBase = itemTableService.getReferenceService().getPriceBase(priceBaseCode);
		} catch (Exception e) {
			priceBase = null;
		}
		if (!unit.equalsIgnoreCase("Text") &&
			null == priceBase) {
			setCellError(cellDtos.get(colNameToNumber.get("priceBase")), "Error: Price Base must be one of BASEYR, OUTTURN, NONE, 2002-03, 2006-07, BSK YR, OT BSK YR, 2007-08, 2012-13, NOMINAL.");
			validRow = false;
		}
		
		// Don't think there is any way to validate definition.
		cellDtos.get(colNameToNumber.get("definition")).getValue();
		if (validRow) {
			validRows++;
		}
		else {
			invalidRows++;
		}
		return validRow;
	}

	private Item createItem(List<CellDto> cellDtos) {
		Item item = new Item();
		String code = cellDtos.get(colNameToNumber.get("bonCode")).getValue();
		item.setCode(code);
		item.setLatestDescription(cellDtos.get(colNameToNumber.get("itemDesc")).getValue());
		item.setUnit(cellDtos.get(colNameToNumber.get("unit")).getValue());
		//TODO assumes no group. This will need to change. 
		Group group = itemTableService.getGroupService().getGroupByCode("NONE");
		item.setGroup(group);
		item.setTeam(team);
		item.setCodeList(null);
		// all we need is ... Code, Name, Unit, GroupId, teamId, listId
		
		Item existingItem = itemTableService.getItemService().getItem(code);
		if (null != existingItem) {
			if (!item.equalsExecptingVersionedData(existingItem)) {
				// Item already exists (AND is different to existing one) so set an error.
				CellDto cellDto = cellDtos.get(colNameToNumber.get("bonCode"));
				setCellError(cellDto, "Item Error. Item is already in use. Items cannot be redefined. Unit:" + existingItem.getUnit() + ". CodeList:" + existingItem.getCodeList()  + ". Group:" + existingItem.getGroup()  + ". Team:" + existingItem.getTeam());
				itemRedefinitions++;
			}
			else {
				// NOTE: If the item is the SAME as existing one then we don't tell the user. 
				itemDuplicates++;
			}
			return null;
		}
		
		itemTableService.getItemService().createItem(item);
		itemsAdded++;
		return item;
	}

	private ItemProperties composeItemProperties(List<CellDto> cellDtos, Item item) {
		Integer dp = Integer.parseInt(cellDtos.get(colNameToNumber.get("dp")).getValue());
		String priceBaseCode = cellDtos.get(colNameToNumber.get("priceBase")).getValue();
		PriceBase pricaBase = itemTableService.getReferenceService().getPriceBase(priceBaseCode);

		ItemProperties itemProperties =  
		new ItemProperties(item, 
					   1, 
					   cellDtos.get(colNameToNumber.get("itemDesc")).getValue(),
					   cellDtos.get(colNameToNumber.get("definition")).getValue(),
					   dp, 
					   null,  
					   false, 
					   null,
					   null, 
					   1, 
					   false);
		itemProperties.setPurpose(null);
		itemProperties.setInflationType(null);
		itemProperties.setPriceBase(pricaBase);
		return itemProperties; 
	}
	
}
