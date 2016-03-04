package uk.gov.ofwat.fountain.api

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;

interface MongoService {

	public void setDbName(String dbName)
	
	public String getDbName()
		
	public <T> Key createUpdate(Object entity)
	
	public ObjectId persistToGridFS(String data, String name, String contentType, String collection)
	
}
