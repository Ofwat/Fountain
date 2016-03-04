package uk.gov.ofwat.fountain.api

import java.nio.charset.Charset

import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Key

import uk.gov.ofwat.fountain.dao.RestAuditDaoImpl
import uk.gov.ofwat.fountain.dao.mongo.MongoResource
import uk.gov.ofwat.fountain.domain.RestAudit

import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSInputFile

class MongoServiceImpl implements MongoService {

	//TODO What about find and delete methods -> Implement as required.
	
	private dbName = "Fountain"
	
	public <T> Key createUpdate(Object entity) {
		MongoResource mr = MongoResource.INSTANCE
		Datastore ds = mr.getDatastore(dbName)
		Key<T> key = ds.save(entity)
		return key;
	}
	
	public ObjectId persistToGridFS(String data, String name, String contentType, String collection){
		MongoResource mr = MongoResource.INSTANCE
		GridFS gridFSReport = new GridFS(mr.getDb(dbName), collection)
		GridFSInputFile gfsFile = gridFSReport.createFile(data.getBytes(Charset.forName("UTF-8")))
		gfsFile.setFilename(name)
		//gfsFile.setContentType("application/json");
		gfsFile.setContentType(contentType)
		gfsFile.save()
		return (ObjectId) gfsFile.getId()
	}

	@Override
	public void setDbName(String dbName) {
		this.dbName = dbName
	}

	@Override
	public String getDbName() {
		return dbName
	}
	
}
