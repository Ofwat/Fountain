package uk.gov.ofwat.fountain.audit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.Classes;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.discovery.Discovery;
import org.elasticsearch.node.Node;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import uk.gov.ofwat.fountain.dao.mongo.MongoResource;
import static org.elasticsearch.node.NodeBuilder.*;

public class FountainElasticSearchClient { //implements InitializingBean, DisposableBean{

	private static Client client = null;
	private static Properties properties;
	
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = 
        		FountainElasticSearchClient.class.getResourceAsStream("/elasticsearch.properties");
        if (inputStream == null) {
            throw new FileNotFoundException("not loaded!");
        }
        properties.load(inputStream);
        return properties;
    }	
	
	private void FountainElasticSearchClient(){
	
	}

	public static Client getInstance(){
		if(FountainElasticSearchClient.client == null){
			Thread thread = Thread.currentThread();
			ClassLoader contextClassLoader = thread.getContextClassLoader();
			try{
				//Instantiate the client
				
				//TODO look up settings from config. 
		        try {
		            if (properties == null) {
		                try {
		                    properties = loadProperties();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }  					
				
				
				/**
				 * This is instantiating in the parent classloader as when we try and do it in the Tomcat CL it fails:
				 * Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "fountain").build();
				 * Grrrhhh!
				 */
				Settings settings = ImmutableSettings.settingsBuilder().classLoader(contextClassLoader.getParent()).put("cluster.name", properties.getProperty("elasticsearch.search.cluster")).build();
				client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(properties.getProperty("elasticsearch.search.host"), Integer.parseInt(properties.getProperty("elasticsearch.search.port"))));
				//TODO the host and port should come form the environment properties.
				InetSocketTransportAddress addr = new InetSocketTransportAddress(properties.getProperty("elasticsearch.search.host"), Integer.parseInt(properties.getProperty("elasticsearch.search.port"))); //9300 is the 'transport address' 9200 the REST interface.
				((TransportClient) client).addTransportAddress(addr);
			}
			catch(Exception e){
				if(client != null){
					client.close();
				}
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		return client;
	}

	public static void closeClient(){
		if(client != null){
			client.close();
		}
	}


/*	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public void destroy() throws Exception {
		//Close the ES client
		client.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = Thread.currentThread();
		ClassLoader contextClassLoader = thread.getContextClassLoader();
		try {
//			Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "fountain").classLoader(contextClassLoader.getParent()).build();
			Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "fountain").classLoader(contextClassLoader).build();
			client = new TransportClient(settings);
			
*//*
			Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "fountain").build();
			client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
*//*

			//TODO the host and port should come form the environment properties. 
     		InetSocketTransportAddress addr = new InetSocketTransportAddress("localhost", 9300); //9300 is the 'transport address' 9200 the REST interface.
			((TransportClient) client).addTransportAddress(addr);			
		} 
		catch(Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
		}
		finally {
		    thread.setContextClassLoader(contextClassLoader);
		}		
	}*/
	
}
