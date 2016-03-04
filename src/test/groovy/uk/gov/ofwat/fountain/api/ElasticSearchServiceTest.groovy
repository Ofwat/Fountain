package uk.gov.ofwat.fountain.api

import junit.framework.TestCase;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate3.SessionFactoryUtils
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.NotImplementedException
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.io.FileSystemUtils;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when
import uk.gov.ofwat.fountain.audit.FountainElasticSearchClient;
import uk.gov.ofwat.fountain.domain.Model;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners([])
@ContextConfiguration(locations = ["classpath:test_beans.xml"])
class ElasticSearchServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * Set of integration tests for ElasticSearch to make sure it behaves as intended. 
	 * We will start a local node for the test.
	 */
	Client client;
	private static String testIndexName = "testindex";
	private static final String HTTP_BASE_URL = "http://localhost";
	private static final String HTTP_PORT = "9205";
	private static final String HTTP_TRANSPORT_PORT = "9305";
	private static final String elasticSearchBaseUrl = HTTP_BASE_URL + ":" + HTTP_PORT;
	private static Node node;
	
	private FountainElasticSearchClient fountainClient = new FountainElasticSearchClient();
	
	@Autowired
	private SearchService elasticSearchService
	
	@BeforeClass
	public static void startElasticSearch() throws Exception{
		final String nodeName = "testnode";
		def settingsMap = [:];
		// create all data directories from root of proj?
		//TODO change this from target to something more suitable
		settingsMap.put("path.conf", "target");
		settingsMap.put("path.data", "target");
		settingsMap.put("path.work", "target");
		settingsMap.put("path.logs", "target");
		// set ports used by Elastic Search to something different than default
		settingsMap.put("http.port", HTTP_PORT);
		settingsMap.put("transport.tcp.port", HTTP_TRANSPORT_PORT);
		settingsMap.put("index.number_of_shards", "1");
		settingsMap.put("index.number_of_replicas", "0");
		settingsMap.put("discovery.zen.ping.multicast.enabled", "false");
		// disable automatic index creation
		//settingsMap.put("action.auto_create_index", "false");
		// disable automatic type creation
		//settingsMap.put("index.mapper.dynamic", "false");
		
		removeOldDataDir("target/" + nodeName);
		
		Settings settings = ImmutableSettings.settingsBuilder()
			.put(settingsMap).build();
		node = nodeBuilder().settings(settings).clusterName(nodeName)
			.client(false).node();
		node.start();
		
	}	
	
	@AfterClass
	public static void stopElasticSearch() {
		node.close();
	}
	
	private static void removeOldDataDir(String datadir) throws Exception {
		File dataDir = new File(datadir);
		if (dataDir.exists()) {
			FileSystemUtils.deleteRecursively(dataDir, true);
		}
	}
	
	@Before
	public void initialize() {
		//Set the client of the spring context to the one initialised during the test setup. 
		client = node.client()
		fountainClient.client = client
		elasticSearchService.fountainElasticSearchClient = fountainClient		
	}
	
	/**
	 * Test to make sure we can successfully add an item and get an ID back
	 */
	@Test
	public void testAddItemToElasticSearch(){
		//Create a new item that could be stored in the elasticSearch index and save it!
		Model model = new Model()
		long modelId = 1000L
		model.id = modelId
		String id = elasticSearchService.put(model, testIndexName, model.class)
		assertNotNull(id)
		//Make sure that we can get it!
		Model returnedModel = elasticSearchService.get(model.class, testIndexName, id)
		assertNotNull(returnedModel)
		assertEquals(model.id, returnedModel.id)
	}
	
	/**
	 * Test to make sure we can get an item from its ID. 
	 */
	@Test
	public void testGetItemFromElasticSearch(){
		//Create a new item that could be stored in the elasticSearch index and save it!
		Model model = new Model()
		long modelId = 1001L
		model.id = modelId
		String id = elasticSearchService.put(model, testIndexName, model.class)
		assertNotNull(id)
		
		model = new Model()
		modelId = 1002L
		model.id = modelId
		id = elasticSearchService.put(model, testIndexName, model.class)
		assertNotNull(id)
		
		//Make sure that we can get it!
		Model returnedModel = elasticSearchService.get(model.class, testIndexName, id)
		assertNotNull(returnedModel)
		assertEquals(model.id, returnedModel.id)
		assertEquals(model.class, returnedModel.class)
	}
	
}
