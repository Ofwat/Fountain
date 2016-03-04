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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;

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
import org.json.simple.JSONValue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.rest.FountainApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:blackbox_beans.xml" })
/**
 * Class to generate the baseline data for the black box tests - only run this class if you want to 
 * regenerate the json files that the black box tests run against.
 * 
 * Requires  -Dbeans.location=blackbox_beans.xml adding to the VM Arguments when running
 * 
 */
public class BlackBoxGenerator {

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
	public void generateCompanyResourceJSON() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/company");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		System.out.println("Content-Type: " + connection.getContentType());
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				folder + "company.json")));

		String line = reader.readLine();
		while (line != null) {
			out.print(line);
			System.out.println(line);
			line = reader.readLine();

		}
		out.close();

	}

	@Test
	public void generateModelResourceJSON() throws HttpException, IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/model?typeId=1");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		System.out.println("Content-Type: " + connection.getContentType());
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				folder + "model.json")));

		String line = reader.readLine();
		while (line != null) {
			out.print(line);
			System.out.println(line);
			line = reader.readLine();

		}
		out.close();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void generateIndividualModelsJSON() throws JsonMappingException,
			HttpException, IOException {
		// loop through each model
		URL getUrl;
		HttpURLConnection connection;

		BufferedReader reader = new BufferedReader(new FileReader(folder
				+ "model.json"));

		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			Object obj = JSONValue.parse(line);
			JSONArray array = (JSONArray) obj;
			for (Object object : array) {
				HashMap<String, String> dtoMap = (HashMap<String, String>) object;
				getUrl = new URL(dtoMap.get("uri"));

				connection = (HttpURLConnection) getUrl.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("accept", "application/json");
				System.out.println("Content-Type: "
						+ connection.getContentType());
				Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
						.getResponseCode());
				BufferedReader respreader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				
				String filename = folder + "model-" + dtoMap.get("code")+ ".json";
				//System.out.println()
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new FileWriter(filename)));
				String outline = respreader.readLine();
				while (outline != null) {
					out.print(outline);
					System.out.println(outline);
					outline = respreader.readLine();

				}
				out.close();
			}
			line = reader.readLine();

		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void generateTableJSON() throws JsonMappingException, HttpException,
			IOException {
		// loop through each table

		String userId = "themba";
		String password = "password";

		Credentials credentials = new UsernamePasswordCredentials(userId, password);
		HttpClient httpClient = new HttpClient();
		httpClient.getState().setCredentials(AuthScope.ANY, credentials);
		httpClient.getParams().setAuthenticationPreemptive(true);

		ClientExecutor clientExecutor = new ApacheHttpClientExecutor(httpClient);

		// URL getUrl;
		// HttpURLConnection connection;

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
					
					System.out.println( "\n\n company "+ company + " file  " + filenames[i] + " \n\n");
					System.out.println(line);
					Object obj = JSONValue.parse(line);
					HashMap<String, Object> jsonTable = (HashMap<String, Object>) obj;

					// Object tableObj = JSONValue.parse();
					JSONArray array = (JSONArray) jsonTable.get("tableLinks");

					for (Object object : array) {
						HashMap<String, String> dtoMap = (HashMap<String, String>) object;

						try {
							URI uri = new URI(dtoMap.get("uri")+"?companyId="+companies.get(company));
							ClientRequestFactory fac = new ClientRequestFactory(clientExecutor,uri);
							
							System.out.println("\n\n"+uri.toString()+"\n\n");
							
							ClientRequest request = fac.createRequest(uri.toString());
							request.accept("application/json");

							ClientResponse response = request.get();
							Assert.assertEquals(HttpURLConnection.HTTP_OK, response.getStatus());

							String text = (String)response.getEntity(String.class);

							// table-COMPANY-MODELCODE-TABLECODE.json
							PrintWriter out = new PrintWriter(new BufferedWriter(
									new FileWriter(folder+"table-" + company.toString()
											+ "-" + jsonTable.get("name") + "-"
											+ dtoMap.get("name") + ".json")));

							out.print(text);
							out.close();

							response.releaseConnection();

						} catch (Exception e) {
							// TODO: handle exception

							e.printStackTrace();
						}


					} // END of table map for loop
				} // end of companies loop
				line = reader.readLine();
			} // END of file reading while loop
		} // END of filename for loop

	}

	@Test
	public void generateIntervalResourceJSON() throws IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/interval");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		System.out.println("Content-Type: " + connection.getContentType());
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				folder + "interval.json")));

		String line = reader.readLine();
		while (line != null) {
			out.print(line);
			System.out.println(line);
			line = reader.readLine();

		}
		out.close();

	}
	
	@Test
	public void generateCGResourceJSON() throws IOException {
		URL getUrl = new URL(
				"http://localhost:8080/Fountain/rest-services/confidencegrades");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		System.out.println("Content-Type: " + connection.getContentType());
		Assert.assertEquals(HttpURLConnection.HTTP_OK, connection
				.getResponseCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				folder + "cg.json")));

		String line = reader.readLine();
		while (line != null) {
			out.print(line);
			System.out.println(line);
			line = reader.readLine();

		}
		out.close();

	}

	@SuppressWarnings("unchecked")
	private HashMap<String, Long> getCompanyList() throws IOException {
		HashMap<String, Long> companies = new HashMap<String, Long>();

		BufferedReader reader = new BufferedReader(new FileReader(folder
				+ "company.json"));

		String line = reader.readLine();
		while (line != null) {

			System.out.println(line);
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
