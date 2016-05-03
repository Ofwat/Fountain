package uk.gov.ofwat.fountain.api.itemTable;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ItemTableService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.TeamService;
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

public class ModelDictionaryParserTest extends TestCase {
	private ItemTableService itemTableService;
	private ItemService mockItemService;
	private GroupService mockGroupService;
	private TeamService mockTeamService;
	private ReferenceService mockReferenceService;

//	private ModelDictionaryParser modelDictionaryParser;
	private Team team;
	private Group group;
	private PriceBase priceBase;
	private CodeList codeList;
	private Item existingItem;
	private ItemProperties existingItemProperties;
	private InflationType inflationType;

	private String teamName;
	private String bonCode;
	private int version;
	private String description;
	private String unit;
	private String groupCode;
	private int dps;
	private String priceBaseCode;
	private String index;
	private String list;
	private String totalEquation;
	private String purpose;
	private String mergingPrinciple;
	private String owner;
	private String definition;
	private String equation;

	public void setUp() {
		mockItemService = mock(ItemService.class);
		mockGroupService = mock(GroupService.class);
		mockTeamService = mock(TeamService.class);
		mockReferenceService = mock(ReferenceService.class);

		itemTableService = new ItemTableService();
		itemTableService.setItemService(mockItemService);
		itemTableService.setGroupService(mockGroupService);
		itemTableService.setTeamService(mockTeamService);
		itemTableService.setReferenceService(mockReferenceService);
		
		teamName = "Universal";
		bonCode = "BM101WT";
		version = 1;
		description = "Employment costs (Water treatment - accounting separation)";
		unit = "£m";
		groupCode = "";
		dps = 3;
		priceBaseCode = "Outturn";
		index = "FYA";
		list = "";
		totalEquation = "";
		purpose = "";
		mergingPrinciple = "";
		owner = "";
		definition = "The sum of the total costs of \"non-manual and manual manpower\" which are directly attributable to each of the individually identified service business units: water resources, raw water distribution, water treatment, treated water distribution and water service total. To be included are the gross salaries and wages of all employees within the relevant business unit, including payments resulting from bonus and profit-related payment schemes, employer's National Insurance contributions, superannuation, pension liabilities, sick pay, sickness benefits, private health insurance, retirement awards, death in service benefits, paid leave, subsistence, travel, entertaining and conference expenses.";
		equation = "";

	}

	private void setupExistingItemProperties(int id, int dp, String definition, String description, Item item, PriceBase priceBase, int version) {
		existingItemProperties = new ItemProperties();
		existingItemProperties.setId(id);
//		existingItemProperties.setAttachedToModel(attachedToModel);
		existingItemProperties.setDecimalPlaces(dp);
		existingItemProperties.setDefinition("The sum of the total costs of \"non-manual and manual manpower\" which are directly attributable to each of the individually identified service business units: water resources, raw water distribution, water treatment, treated water distribution and water service total. To be included are the gross salaries and wages of all employees within the relevant business unit, including payments resulting from bonus and profit-related payment schemes, employer's National Insurance contributions, superannuation, pension liabilities, sick pay, sickness benefits, private health insurance, retirement awards, death in service benefits, paid leave, subsistence, travel, entertaining and conference expenses.");
//		existingItemProperties.setDescription("Employment costs (Water treatment - accounting separation)");
		existingItemProperties.setDescription(description);
		existingItemProperties.setFixedFormula(false);
//		existingItemProperties.setFormulae(formulae);
//		existingItemProperties.setGeneralFormula(formula);
//		existingItemProperties.setGroupTotalFormula(groupTotalFormula);
//		existingItemProperties.setInflationType(inflationType);
		existingItemProperties.setItem(item);
		existingItemProperties.setPriceBase(priceBase);
//		existingItemProperties.setPurpose(purpose);
		existingItemProperties.setReservoirVersion(1);
		existingItemProperties.setVersion(version);
	}

	private void setupExistingItem(int id, String bonCode, CodeList codeList, Group group, String description, Team team, String unit) {
		existingItem = new Item();
		existingItem.setId(id);
		existingItem.setCode(bonCode);
		existingItem.setCodeList(codeList);
		existingItem.setGroup(group);
		existingItem.setLatestDescription(description);
		existingItem.setTeam(team);
		existingItem.setUnit(unit);
	}

	private void setupCodeList() {
		if (list.isEmpty()) {
			codeList = null;
		}
		else {
			codeList = new CodeList();
			codeList.setId(4);
			codeList.setCode(list);
		}
	}

	private void setupInflationType() {
		if (index.isEmpty()) {
			inflationType = null;
		}
		else {
			inflationType = new InflationType();
			inflationType.setId(5);
			inflationType.setCode(index);
		}
	}

	private void setupPriceBase() {
		priceBase = new PriceBase();
		priceBase.setId(3);
		priceBase.setCode(priceBaseCode.toUpperCase());
	}

	private void setupGroup() {
		group = new Group();
		group.setId(2);
		if (groupCode.isEmpty()) {
			group.setName("NONE");
		}
		else {
			group.setName(groupCode.toUpperCase());
		}
	}

	private void setupTeam() {
		team = new Team();
		team.setId(1);
		team.setName(teamName);
		team.setCode("ROLE_OFWAT\\FOUNTAIN.USERS");
	}

	private void setupParams() {
		setupTeam();
		setupGroup();		
		setupPriceBase();
		setupInflationType();
		setupCodeList();
	}
	
	public void testParse_newItem() {

		setupParams();
		setupExistingItem(6, bonCode, codeList, group, description, team, unit);

		TableDto itemTableDto = new TableDto();
		List<RowDto> rows = new ArrayList<RowDto>();
		rows.add(headerRow());
		rows.add(dataRow(1));
		itemTableDto.setRows(rows);
		
		setUpMockExpectations(null, existingItemProperties);
		
		ModelDictionaryParser modelDictionaryParser = new ModelDictionaryParser(itemTableService, itemTableDto);
		TableDto parsedTableDto = modelDictionaryParser.parse();
		Assert.assertNull(parsedTableDto.getErrorMessage());
		Assert.assertEquals(1, modelDictionaryParser.getCounts().validRows);
		Assert.assertEquals(1, modelDictionaryParser.getCounts().itemsAdded);
	}

	public void testParse_ItemRedefinition() {

		setupParams();
		setupExistingItem(6, bonCode, codeList, group, description, team, "A different unit");

		TableDto itemTableDto = new TableDto();
		List<RowDto> rows = new ArrayList<RowDto>();
		rows.add(headerRow());
		rows.add(dataRow(1));
		itemTableDto.setRows(rows);
		
		setUpMockExpectations(existingItem, existingItemProperties);
		
		ModelDictionaryParser modelDictionaryParser = new ModelDictionaryParser(itemTableService, itemTableDto);
		TableDto parsedTableDto = modelDictionaryParser.parse();
		Assert.assertNull(parsedTableDto.getErrorMessage());
		Assert.assertEquals(1, modelDictionaryParser.getCounts().validRows);
		Assert.assertEquals(1, modelDictionaryParser.getCounts().itemRedefinitions);
	}

	public void testParse_noExistingItemPropertiesSoAddNewOne() {

		setupParams();
		setupExistingItem(6, bonCode, codeList, group, description, team, unit);
		setupExistingItemProperties(7, dps, definition, description, existingItem, priceBase, version);

		TableDto itemTableDto = new TableDto();
		List<RowDto> rows = new ArrayList<RowDto>();
		rows.add(headerRow());
		rows.add(dataRow(1));
		itemTableDto.setRows(rows);
		
		setUpMockExpectations(existingItem, null);
		
		ModelDictionaryParser modelDictionaryParser = new ModelDictionaryParser(itemTableService, itemTableDto);
		TableDto parsedTableDto = modelDictionaryParser.parse();
		Assert.assertNull(parsedTableDto.getErrorMessage());
		Assert.assertEquals(1, modelDictionaryParser.getCounts().validRows);
		Assert.assertEquals(1, modelDictionaryParser.getCounts().itemPropertiesAdded);
	}

	public void testParse_ItemDuplicate() {

		setupParams();
		setupExistingItem(6, bonCode, codeList, group, description, team, unit);
		setupExistingItemProperties(7, dps, definition, description, existingItem, priceBase, version);

		TableDto itemTableDto = new TableDto();
		List<RowDto> rows = new ArrayList<RowDto>();
		rows.add(headerRow());
		rows.add(dataRow(1));
		itemTableDto.setRows(rows);
		
		setUpMockExpectations(existingItem, existingItemProperties);
		
		ModelDictionaryParser modelDictionaryParser = new ModelDictionaryParser(itemTableService, itemTableDto);
		TableDto parsedTableDto = modelDictionaryParser.parse();
		Assert.assertNull(parsedTableDto.getErrorMessage());
		Assert.assertEquals(1, modelDictionaryParser.getCounts().validRows);
		Assert.assertEquals(1, modelDictionaryParser.getCounts().itemDuplicates);
	}

	public void testParse_existingItemPropertiesSoAddNewOne() {

		setupParams();
		setupExistingItem(6, bonCode, codeList, group, description, team, unit);
		setupExistingItemProperties(7, dps, definition, "An existing description", existingItem, priceBase, version);

		TableDto itemTableDto = new TableDto();
		List<RowDto> rows = new ArrayList<RowDto>();
		rows.add(headerRow());
		rows.add(dataRow(1));
		itemTableDto.setRows(rows);
		
		setUpMockExpectations(existingItem, existingItemProperties);
		
		ModelDictionaryParser modelDictionaryParser = new ModelDictionaryParser(itemTableService, itemTableDto);
		TableDto parsedTableDto = modelDictionaryParser.parse();
		Assert.assertNull(parsedTableDto.getErrorMessage());
		Assert.assertEquals(1, modelDictionaryParser.getCounts().validRows);
		Assert.assertEquals(1, modelDictionaryParser.getCounts().itemPropertiesAdded);
	}

	private void setUpMockExpectations(Item overrideExistingItem, ItemProperties overrideExistingItemProperties) {
		// validateItemRow
		when(mockTeamService.getTeamByName(teamName)).thenReturn(team);
		if (!groupCode.isEmpty()) {
			when(mockGroupService.getGroupByCode("NONE")).thenReturn(group);
		}
		when(mockReferenceService.getPriceBase(priceBaseCode.toUpperCase())).thenReturn(priceBase);
		if (!index.isEmpty()) {
			when(mockReferenceService.getInflationType(index)).thenReturn(inflationType);
		}
		if (!list.isEmpty()) {
			when(mockItemService.getCodeList(list)).thenReturn(codeList);
		}

		// createItemAndItemProperties
		// createItem
		when(mockGroupService.getGroupByCode(groupCode)).thenReturn(null);
		when(mockGroupService.getGroupByCode("NONE")).thenReturn(group);
		if (!list.isEmpty()) {
			when(mockItemService.getCodeList(list)).thenReturn(codeList);
		}
		// createItemAndItemProperties
		when(mockItemService.getItem(bonCode)).thenReturn(overrideExistingItem);
		// createItemProperties
		when(mockReferenceService.getPriceBase("OUTTURN")).thenReturn(priceBase);
		// createItemAndItemProperties
		when(mockItemService.getLatestPropertiesForItem(existingItem.getId())).thenReturn(overrideExistingItemProperties);
		when(mockItemService.createItemProperties(isA(ItemProperties.class))).thenReturn(7);
		when(mockReferenceService.getInflationType("FYA")).thenReturn(inflationType);
	}

	private RowDto dataRow(int rowNo) { 
		RowDto row = new RowDto();
		List<CellDto> cells = new ArrayList<CellDto>();
//		CellDto(int row, int col, String value, int decimalPlaces, String cellFormat, String dataType, String dataFormat, String style, boolean errorFlag, String errorText, String key, CellType cellType);
		cells.add(new CellDto(rowNo, 0, bonCode, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 1, Integer.toString(version), 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 2, description, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 3, unit, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 4, groupCode, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 5, Integer.toString(dps), 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 6, priceBaseCode, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 7, index, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 8, totalEquation, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 9, purpose, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 10, mergingPrinciple, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 11, owner, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 12, definition, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 13, definition, 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(rowNo, 14, equation, 0, null, null, null, null, false, null, null));
		row.setCells(cells);
		return row;
	}
	
	private RowDto headerRow() { 
		RowDto row = new RowDto();
		List<CellDto> cells = new ArrayList<CellDto>();
		cells.add(new CellDto(0, 0, "BonCode", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 1, "Version", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 2, "Description", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 3, "Unit", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 4, "Group", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 5, "DPs", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 6, "Price Base", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 7, "Index", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 8, "list", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 9, "TotalEquation", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 10, "Purpose", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 11, "MergingPrinciple", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 12, "Owner", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 13, "Definition", 0, null, null, null, null, false, null, null));
		cells.add(new CellDto(0, 14, "Equation", 0, null, null, null, null, false, null, null));
		row.setCells(cells);
		return row;
	}

	
		

}
