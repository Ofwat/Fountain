package uk.gov.ofwat.fountain.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.api.itemTable.ImportItemsParser;
import uk.gov.ofwat.fountain.api.itemTable.ItemTableParser;
import uk.gov.ofwat.fountain.api.itemTable.ModelDictionaryParser;
import uk.gov.ofwat.fountain.rest.dto.TableDto;

public class ItemTableService {
	
	private static Logger logger = LoggerFactory.getLogger(ItemTableService.class);

	private ItemService itemService;
	private GroupService groupService;
	private TeamService teamService;
	private ReferenceService referenceService;
	private IndexService indexService;
	
	private static final int NUM_OF_COLS = 6;

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}
	
	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public ItemService getItemService() {
		return itemService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public ReferenceService getReferenceService() {
		return referenceService;
	}

	@Transactional
	public TableDto createNewItems(final TableDto itemTableDto) {
		// select tableParserToUser
		// use item import by default
		ItemTableParser itemTableParser = null; 
		String tableName = itemTableDto.getName();
		if (tableName.equalsIgnoreCase("Import Items")) {
			itemTableParser = new ImportItemsParser(this, itemTableDto, indexService);
		}
		else if (tableName.equalsIgnoreCase("Model Dictionary") ||
				 tableName.equalsIgnoreCase("Model Dicitonary")) {
			itemTableParser = new ModelDictionaryParser(this, itemTableDto);
		}
		
		if (null == itemTableParser) {
			TableDto errorTableDto = itemTableDto.clone();
			errorTableDto.setError(true);
			errorTableDto.setErrorMessage("Type of payload is unrecognised. TableDto name is (" + itemTableDto.getName() + ") is not recognised. It should be either 'Import Items' or 'Model Dictionary'.");
			return errorTableDto;
		}

		return itemTableParser.parse();
	}
	
}












