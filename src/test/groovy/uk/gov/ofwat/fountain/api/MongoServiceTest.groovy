package uk.gov.ofwat.fountain.api

import static org.junit.Assert.*;

import java.util.Date
import java.util.HashMap
import javax.servlet.ServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB
import com.mongodb.DBCollection;
import com.mongodb.DBObject
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.util.JSON

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Key
import org.mongodb.morphia.Morphia;

import uk.gov.ofwat.fountain.audit.AuditExclusionStrategy;
import uk.gov.ofwat.fountain.dao.mongo.MongoResource;
import uk.gov.ofwat.fountain.domain.RestAudit

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners([])
@ContextConfiguration(locations = ["classpath:test_beans.xml"])
class MongoServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private MongoService mongoService
	
	@Test
	public void testCreateMongoItem(){
		RestAudit restAudit = buildRestAudit()
		//Persist the object. 
		Key<RestAudit> key = mongoService.<RestAudit>createUpdate(restAudit)
		assertNotNull(key)
		//make sure the object is still there by manually looking up from client as we don't have a get yet.
		DB db = MongoResource.INSTANCE.getDb(mongoService.getDbName())
		DBCollection coll = db.getCollection("RestAudit") //From Morphia mapping.
		DBObject myDoc = coll.findOne(key.id)
		println(myDoc)
		assertNotNull(myDoc)		
		Morphia morphia = new Morphia().map(RestAudit.class)
		RestAudit retrievedAudit = morphia.fromDBObject(RestAudit, myDoc)
		assertEquals(retrievedAudit.uri, restAudit.uri)
	}
	
	@Test
	public void testCreateCreateGridFSItem(){
		RestAudit restAudit = buildRestAudit()
		//Serialize to JSON and store as a gridFS entry, we could try other objects/files but for now this will do. 
		Gson gson = new GsonBuilder()
			.setExclusionStrategies(new AuditExclusionStrategy())
			.create();
		String json = gson.toJson(restAudit)
		ObjectId id = mongoService.persistToGridFS(json, "TEST_JSON", "application/json", "JsonReports")
		assertNotNull(id)
		
		//Test we can get it again. 
		DB db = MongoResource.INSTANCE.getDb(mongoService.getDbName())
		GridFS gfs = new GridFS(db, "JsonReports")
		GridFSDBFile jsonFile = gfs.findOne(id)
		InputStream stream = jsonFile.getInputStream()
		String returnedJson = stream.getText()
		assertEquals(returnedJson, json)
		
	}
	
	@After
	public void clear() {
		//TODO clear down collections.
		DB db = MongoResource.INSTANCE.getDb(mongoService.getDbName())
		DBCollection coll = db.getCollection("RestAudit")
		coll.drop()
	}
	
	private RestAudit buildRestAudit(){
		RestAudit restAudit = new RestAudit();
		//The content and reponseEntity should be of type DBObject!
		//Need to include these in the test!
		restAudit.setHttpMethod("GET")
		restAudit.setUri("SAMPLE URI")
		restAudit.setUser("SAMPLE USER")
		restAudit.setStartDate(new Date())
		restAudit.setFountainUsers(true)
		restAudit.setFountainEditors(true)
		restAudit.setFountainAdmins(true)
		restAudit.setFountainRunAdmin(true)
		restAudit.setResourceClass("SAMPLE CLASS NAME")
		restAudit.setResourceMethod("SAMPLE METHOD NAME")
		DBObject contentDbo = (DBObject)JSON.parse("{'key':'value1'}")
		restAudit.setContent(contentDbo)
		DBObject ResponseEntityDbo = (DBObject)JSON.parse("{'key':'value2'}")
		restAudit.setResponseEntity(contentDbo)
		return restAudit
	}
		
}
