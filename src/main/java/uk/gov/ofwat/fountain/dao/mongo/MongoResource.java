package uk.gov.ofwat.fountain.dao.mongo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.RestAudit;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

public enum MongoResource {
	
    INSTANCE;
    private MongoClient mongoClient;
    private Properties properties;
    
    private MongoResource(){
        try {
            if (properties == null) {
                try {
                    properties = loadProperties();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mongoClient == null)
                mongoClient = getClient();
        } catch (Exception e) {
            e.printStackTrace();
        }    	    	
    }
    
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = 
          MongoResource.class.getResourceAsStream("/mongodb.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("not loaded!");
        }
        properties.load(inputStream);
        return properties;
    }    
    
    @Nullable
    private MongoClient getClient() {
    	String username = properties.getProperty("mongo.db.username");
    	String password = properties.getProperty("mongo.db.password");
    	String dbname = properties.getProperty("mongo.db.loginDatabase");
        try {
        	MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbname, password.toCharArray());
        	ServerAddress serverAddress = new ServerAddress(properties.getProperty("mongo.db.host"),
                    Integer.valueOf(properties.getProperty("mongo.db.port")));
        	return new MongoClient(serverAddress, Arrays.asList(credential));
            
        } catch (UnknownHostException uh) {
            uh.printStackTrace();
        }
        return null;
    }    
    
    @Nullable
    public Datastore getDatastore(@NotNull String dbName) {
        Datastore ds;
        Morphia morphia;
        //with authentication?
        if (!properties.getProperty("mongo.db.username").isEmpty() &&
            !properties.getProperty("mongo.db.password").isEmpty()) {
            morphia = new Morphia();
            mapClasses(morphia);
        	ds = morphia.createDatastore(mongoClient,dbName);

        }
        else {
            morphia = new Morphia();
            mapClasses(morphia);
            ds = morphia.createDatastore(mongoClient,dbName);
        }
        return ds;
    }    
    
    private void mapClasses(Morphia morphia){
		morphia.map(RestAudit.class);
		// morphia.map(uk.gov.ofwat.fountain.rest.dto.CustomReportDto.class);
		// morphia.map(uk.gov.ofwat.fountain.rest.dto.ModelItemDto.class);
    }
    
    public DB getDb(@NotNull String dbName){
    	return mongoClient.getDB(dbName);
    }
    
}
