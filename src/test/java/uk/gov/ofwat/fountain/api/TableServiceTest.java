package uk.gov.ofwat.fountain.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ItemPropertyIntervalDao;
import uk.gov.ofwat.fountain.dao.ModelPageDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.form.ModelPage;
import junit.framework.TestCase;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TableServiceTest extends TestCase {
	
	private TableServiceImpl tableService;
	private ModelPageDao mockModelPageDao;
	private ModelPage mockModelPage;
	private Table mockTable;
	
	public static String DESCRIPTION = "SAMPLE_DESCRIPTION";
	public static int MOCK_TABLE_ID = 1;
	
	public void setUp(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		System.out.println("TEST: TableServiceTest.setUp()");
		tableService = new TableServiceImpl();
		mockModelPageDao = mock(ModelPageDao.class);
		mockModelPage = mock(ModelPage.class);
		when(mockModelPage.getTableDescription()).thenReturn(DESCRIPTION);
		when(mockModelPageDao.findByTableId(MOCK_TABLE_ID)).thenReturn(mockModelPage);
		tableService.setModelPageDao(mockModelPageDao);			
		//convention
		System.out.println("TEST: Done");
	}
	
	public void tearDown(){
		//clean up. 
	}
	
	/*
	 * Test to make sure that descriptions are added correctly to the list of tables.  
	 */
	public void testAddTableDescriptions(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddTableDescriptions");
		System.out.println("TEST: ItemServiceTest.testAddTableDescriptions()");

		List<Table> tables = new ArrayList<Table>();
		for(int i=0;i<10;i++){
			Table table = new Table();
			table.setId(MOCK_TABLE_ID);
			tables.add(table);
		}
		
		List<Table> tablesWithDescriptions = new ArrayList<Table>();
		tablesWithDescriptions = tableService.addTableDescriptions(tables);
		
		//check all the tables have a populated desc and there are 10 of them.
		assertTrue(tablesWithDescriptions.size() >= 10);
		
		boolean updatedAll = true;
		for(Table table : tablesWithDescriptions){
			if(!table.getDescription().equals(DESCRIPTION)){
				updatedAll = false;
			}
			
		}
		assertEquals(true, updatedAll);
		System.out.println("TEST: Done");
	}
}
