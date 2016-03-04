/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package uk.gov.ofwat.fountain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

import junit.framework.Assert;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.codehaus.jackson.map.JsonMappingException;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.util.file.FilenamePrefixFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.rest.FountainApplication;


/**
 * Requires  -Dbeans.location=blackbox_beans.xml adding to the VM Arguments when running
 * 
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:blackbox_beans.xml" })
public class BlackBoxTests {

	private static final String folder = "test-data/jsonTestData/";

	private static TJWSEmbeddedJaxrsServer tjws;
	protected static ResteasyDeployment deployment;

	@BeforeClass
	public static void setup() {
		tjws = new TJWSEmbeddedJaxrsServer();
	
		tjws.setPort(8080);
		tjws.setBindAddress("localhost");
		tjws.setRootResourcePath("/Fountain/rest-services/");
		deployment = new ResteasyDeployment();
		deployment.setApplication(new FountainApplication());
		tjws.setDeployment(deployment);
			
		SpringBeanProcessor processor = new SpringBeanProcessor(tjws
				.getDeployment().getDispatcher(), tjws.getDeployment()
				.getRegistry(), tjws.getDeployment().getProviderFactory());
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"blackbox_beans.xml");
		context.refresh();

		context.addBeanFactoryPostProcessor(processor);

		tjws.start();
	}

	@AfterClass
	public static void finish() {
		if (tjws != null) {
			tjws.stop();
		}
	}

	@Test
	/**
	 * Compare the current output from the company resource with the baseline file.
	 */
	public void testCompanyResource() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/company");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		BufferedReader original = new BufferedReader(new FileReader(folder+"company.json"));
		
		String newLine = reader.readLine();
		String originalLine = original.readLine();
		
		Object newObj, originalObj;
		JSONArray newArray, origArray;
		
		while (newLine != null) {
			newObj = JSONValue.parse(newLine);
			originalObj = JSONValue.parse(originalLine);
			newArray = (JSONArray) newObj;
			origArray = (JSONArray) originalObj;
			Assert.assertEquals(origArray, newArray);
			newLine = reader.readLine();
			originalLine = original.readLine();
		}
	}
	
	
	@Test
	public void testModelResource() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/model?typeId=1");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		BufferedReader original = new BufferedReader(new FileReader(folder
				+ "model.json"));

		String newLine = reader.readLine();
		String originalLine = original.readLine();
		Object newObj, originalObj;
		JSONArray newArray, origArray;
		
		while (newLine != null) {
			newObj = JSONValue.parse(newLine);
			originalObj = JSONValue.parse(originalLine);
			newArray = (JSONArray) newObj;
			origArray = (JSONArray) originalObj;
			Assert.assertEquals(origArray, newArray);
			newLine = reader.readLine();
			originalLine = original.readLine();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testEachModelResource() throws JsonMappingException,
			HttpException, IOException {
		// loop through each model
		URL getUrl;
		HttpURLConnection connection;

		BufferedReader reader = new BufferedReader(new FileReader(folder
				+ "model.json"));

		String line = reader.readLine();
		while (line != null) {
			Object obj = JSONValue.parse(line);
			JSONArray array = (JSONArray) obj;
			for (Object object : array) {
				HashMap<String, String> dtoMap = (HashMap<String, String>) object;
				getUrl = new URL(dtoMap.get("uri"));

				connection = (HttpURLConnection) getUrl.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("accept", "application/json");
				
				Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
						.getResponseCode());
				BufferedReader respReader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				BufferedReader original = new BufferedReader(new FileReader(folder+ "model-" + dtoMap.get("code")
								+ ".json"));
				String newLine = respReader.readLine();
				String originalLine = original.readLine();
				Object newObj, originalObj;
				JSONObject jNewObj, jOrigObj; 
				
				while (newLine != null) {
					newObj = JSONValue.parse(newLine);
					originalObj = JSONValue.parse(originalLine);
					jNewObj = (JSONObject) newObj;
					jOrigObj = (JSONObject) originalObj;
					Assert.assertEquals(jOrigObj, jNewObj);
					newLine = reader.readLine();
					originalLine = original.readLine();
				}
			}
			line = reader.readLine();
		}
	}
	
	@Test
	public void testIntervalResource() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/interval");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		BufferedReader original = new BufferedReader(new FileReader(folder+"interval.json"));
		
		String newLine = reader.readLine();
		String originalLine = original.readLine();
		Object newObj, originalObj;
		JSONArray newArray, origArray;
		while (newLine != null) {
			newObj = JSONValue.parse(newLine);
			originalObj = JSONValue.parse(originalLine);
			newArray = (JSONArray) newObj;
			origArray = (JSONArray) originalObj;
			Assert.assertEquals(origArray, newArray);
			newLine = reader.readLine();
			originalLine = original.readLine();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTableJSON() throws JsonMappingException, HttpException,
			IOException {
		// loop through each table

		String userId = "themba";
		String password = "password";

		Credentials credentials = new UsernamePasswordCredentials(userId, password);
		HttpClient httpClient = new HttpClient();
		httpClient.getState().setCredentials(AuthScope.ANY, credentials);
		httpClient.getParams().setAuthenticationPreemptive(true);

		ClientExecutor clientExecutor = new ApacheHttpClientExecutor(httpClient);

		File list = new File(folder);
		FilenamePrefixFilter filter = new FilenamePrefixFilter("model-");
		String[] filenames = list.list(filter);

		HashMap<String, Long> companies = getCompanyList();

		BufferedReader reader;
		for (int i = 0; i < filenames.length; i++) {
			reader = new BufferedReader(new FileReader(folder+filenames[i]));

			String line = reader.readLine();
			while (line != null) {

				for (String company : companies.keySet()) {
					System.out.println(line);
					Object obj = JSONValue.parse(line);
					HashMap<String, Object> jsonTable = (HashMap<String, Object>) obj;

					// Object tableObj = JSONValue.parse();
					JSONArray array = (JSONArray) jsonTable.get("tableLinks");
					
					// get the tables for this company
					

					for (Object object : array) {
						HashMap<String, String> dtoMap = (HashMap<String, String>) object;

						try {
							URI uri = new URI(dtoMap.get("uri")+"?companyId="+companies.get(company));
							ClientRequestFactory fac = new ClientRequestFactory(clientExecutor,uri); 
							ClientRequest request = fac.createRequest(uri.toString());
							request.accept("application/json");

							ClientResponse response = request.get();
							Assert.assertEquals(HttpURLConnection.HTTP_OK, response.getStatus());

							String text = (String)response.getEntity(String.class);

							
							BufferedReader original = new BufferedReader(new FileReader(folder+"table-" + company.toString()
									+ "-" + jsonTable.get("name") + "-"
									+ dtoMap.get("name") + ".json"));
							System.out.println("Processing " + folder + "table-" + company.toString()
									+ "-" + jsonTable.get("name") + "-"
									+ dtoMap.get("name") + ".json" + "\n");

							String origLine = original.readLine();
							Object newObj, originalObj;
							JSONObject jNewObj, jOrigObj; 
							newObj = JSONValue.parse(text);
							originalObj = JSONValue.parse(origLine);
							jNewObj = (JSONObject) newObj;
							jOrigObj = (JSONObject) originalObj;
							
							
							compareJSONObjects(jOrigObj, jNewObj);
							//Assert.assertEquals(jOrigObj, jNewObj);
							
							response.releaseConnection();

						} catch (Exception e) {
							System.out.println("Didn't process " + object + " for " + company);
							e.printStackTrace(System.out);
						}


					} // END of table map for loop
				} // end of companies loop
				line = reader.readLine();
			} // END of file reading while loop
		} // END of filename for loop

	}
	
	private void compareJSONObjects(JSONObject o1, JSONObject o2) {
		Assert.assertEquals("Expected the JSON object to be same size", o1.keySet().size(), o2.keySet().size());

		
		Set<?> k1 = o1.keySet();
		for (Object k : k1) {
			Object v1 = o1.get(k);
			if (!o2.containsKey(k)) {
				throw new RuntimeException("First JSON has entry for " + k + ", but second doesn't");
			}
			Object v2 = o2.get(k);
			
			if (v1 instanceof JSONObject && v2 instanceof JSONObject) {
				// Sub JSON objects - compare these
				compareJSONObjects((JSONObject) v1, (JSONObject) v2);
			}
			else {
				String s1 = v1==null ? "" : v1.toString();
				String s2 = v2==null ? "" : v2.toString();
				if (s1.contains("ERR -") && s2.contains("ERR -")) {
					// Both errors - assume equal
				}
				else {
					// Not errors so check they are the same
					Assert.assertEquals("[" + s1 + "] [" + s2 + "] Got different JSON values for '" + k + "'", v1, v2);
				}
			}
		}
	}
	

	@Test
	public void testCGResource() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/confidencegrades");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		BufferedReader original = new BufferedReader(new FileReader(folder+"cg.json"));
		
		String newLine = reader.readLine();
		String originalLine = original.readLine();
		Object newObj, originalObj;
		JSONArray newArray, origArray;
		while (newLine != null) {
			newObj = JSONValue.parse(newLine);
			originalObj = JSONValue.parse(originalLine);
			newArray = (JSONArray) newObj;
			origArray = (JSONArray) originalObj;
			Assert.assertEquals(origArray, newArray);
			newLine = reader.readLine();
			originalLine = original.readLine();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private HashMap<String, Long> getCompanyList() throws IOException {
		HashMap<String, Long> companies = new HashMap<String, Long>();

		BufferedReader reader = new BufferedReader(new FileReader(folder
				+ "company.json"));

		String line = reader.readLine();
		while (line != null) {

			Object obj = JSONValue.parse(line);
			JSONArray array = (JSONArray) obj;
			for (Object object : array) {
				HashMap<String, Object> dtoMap = (HashMap<String, Object>) object;
				companies.put((String) dtoMap.get("code"), (Long) dtoMap
						.get("id"));
			}
			line = reader.readLine();
		}
		return companies;
	}
	
}
