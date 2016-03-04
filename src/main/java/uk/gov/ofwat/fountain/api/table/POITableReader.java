package uk.gov.ofwat.fountain.api.table;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.TableService;
import uk.gov.ofwat.fountain.api.TableStructureService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.api.table.reader.MetaDataExtractor;
import uk.gov.ofwat.fountain.api.table.reader.table.GroupSelectTableReader;
import uk.gov.ofwat.fountain.api.table.reader.table.TableReader;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.User;

public class POITableReader {

	private static Log log = LogFactory.getLog(POITableReader.class);

	private TableStructureService tableStructureService;
	private TableService tableService;
	private BasketService basketService;
	
	private GroupSelectTableReader groupSelectTableReader;
	private TableReader tableReader;
	private MetaDataExtractor metaDataExtractor;
	
	private XSSFWorkbook workBook = null;

	public MetaDataExtractor getMetaDataExtractor() {
		return metaDataExtractor;
	}
	public void setMetaDataExtractor(MetaDataExtractor metaDataExtractor) {
		this.metaDataExtractor = metaDataExtractor;
	}
	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}
	public void setWorkBook(XSSFWorkbook workBook) {
		this.workBook = workBook;
	}

	public void setWorkBook(InputStream inputStream) throws IOException {
		workBook = new XSSFWorkbook(inputStream);
	}
	
	public void setTableStructureService(TableStructureService tableStructureService) {
		this.tableStructureService = tableStructureService;
	}
	public TableService getTableService() {
		return tableService;
	}
	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}
	public TableStructureService getTableStructureService() {
		return tableStructureService;
	}
	public BasketService getBasketService() {
		return basketService;
	}
	public GroupSelectTableReader getGroupSelectTableReader() {
		return groupSelectTableReader;
	}
	public void setGroupSelectTableReader(
			GroupSelectTableReader groupSelectTableReader) {
		this.groupSelectTableReader = groupSelectTableReader;
	}
	public TableReader getTableReader() {
		return tableReader;
	}
	public void setTableReader(TableReader tableReader) {
		this.tableReader = tableReader;
	}
	
	public TableUploadMetaData streamExcelToTable(TableUploadMetaData metaData, User user, RoleChecker roleChecker) {

		for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
			// if we can't extract metadata move on to the next sheet.
			if(metaDataExtractor.extractMetaData(metaData, workBook.getSheetAt(i))) {
				DataTable dataTable = createDataTable(metaData, user, roleChecker);
				readSpreadsheet(metaData, dataTable, workBook.getSheetAt(i), user);
				if (null != dataTable) {
					basketService.storeChangesToBasket(user, dataTable, metaData);
				}
			}
		}

		return metaData;
	}

	
	private DataTable createDataTable(TableUploadMetaData metaData, User user, RoleChecker roleChecker) {
		if (null == metaData.getTable()) {
			return null;
		}
		if (null == metaData.getCompany()) {
			return null;
		}
		turnOnEditing(user);
		return tableService.getTableForCompany(metaData.getTable().getId(), metaData.getCompany().getId(), user, roleChecker);
	}
	
	
	private void readSpreadsheet(TableUploadMetaData metaData, DataTable dataTable, Sheet sheet, User user) {
		TableStructure tableStructure = tableStructureService.getTableStructure(metaData.getTable().getId());
	    if (tableStructure.getModelPage().isGroupSelect()) {
	    	// group dropdown
	    	groupSelectTableReader.read(dataTable, metaData, tableStructure, sheet);
	    }
	    else {
	    	tableReader.read(dataTable, metaData, tableStructure, sheet);
		}
	}

	private void turnOnEditing(User user) {
		if (null == basketService.getBasketForUser(user)) {
			basketService.createBasket(user);
		}
	}

}
