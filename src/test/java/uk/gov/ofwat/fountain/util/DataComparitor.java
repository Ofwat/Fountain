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

package uk.gov.ofwat.fountain.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import uk.gov.ofwat.fountain.util.json.JSONArray;
import uk.gov.ofwat.fountain.util.json.JSONException;
import uk.gov.ofwat.fountain.util.json.JSONObject;
import uk.gov.ofwat.fountain.util.json.JSONTokener;


/**
 * Comparator for regression testing data files against table data requests.
 * 
 * It takes a text file of records for a given company, model and table that takes the format
 *  <code>"BN7000A","2007-08","NOGROUP","77.839","INPUT"
		  "BN7000A","2008-09","NOGROUP","94.306","COPYDATA"
          "BN7000A","2009-10","NOGROUP","110.009","COPYCELL"</code> etc
 *  
 *  and compares them with the corresponding data retrieved from a REST call to the Table resource.
 *  
 *  results are output to a file
 *  
 *  
 */
public class DataComparitor {

	
	/*
	 * The things it needs to know up front
	 */
	private String dataFileDir; // should have subfolders with model names
	private String resultFileDir; // results will be put into subfolders as above
	private HttpClient httpClient; // does all the login and rest calls
	
	private int totalItems = 0;
	private int itemEquations = 0;
	private int itemsDifferent = 0;
	
	public void setDataFileDir(String dataFileDir) {
		this.dataFileDir = dataFileDir;
	}
	public void setResultFileDir(String resultFileDir) {
		this.resultFileDir = resultFileDir;
	}
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}	


	

	/**
	 * @param args - 
	 */
	public static void main(String[] args) {
		DataComparitor dc = new DataComparitor();
		
		// SET THE VARIABLES HERE 
		String dataFileDir = args[0]; 
		String resultsFileDir = args[1]; 
		String webAppRootAddress = args[2];
		String username = args[3];
		String password = args[4];
		String[] modelsToProcess = {args[5]}; // should also correspond to folders under ${dataFileDir}
		
		dc.setDataFileDir(dataFileDir);
		dc.setResultFileDir(resultsFileDir);
		HttpClient client = new HttpClient(webAppRootAddress,
				                           username,
				                           password);
		dc.setHttpClient(client);
		for(String modelName: modelsToProcess){
			dc.processModelFiles(modelName);
		}
	}
	
	/**
	 * process all available files for the given model
	 * @param model
	 */
	public void processModelFiles(String model){
		// enumerate the files relating to the given model
		File modelDir = new File(dataFileDir + "/" + model);
		if((!modelDir.exists()) && modelDir.isDirectory() ){
			throw new RuntimeException("requested model dir: " + dataFileDir + "/" + model + " does not exist, or is empty");
		}
		
		String[] dataFiles = modelDir.list();
		File currentDataFile = null;
		String currentCompany = null;
		List<String> results = new ArrayList<String>();;

		for(String dFile: dataFiles){
			currentDataFile = new File(modelDir, dFile);
			if(!currentDataFile.isDirectory()){
				// process this file
				if (isNewCompany(currentCompany, currentDataFile)) {
					// New company
					if (null != currentCompany) {
						// Not first company
						// print out previous companies data and clear counters etc
						if(null != results && results.size() != 0){	
							writeResults(model, currentCompany, results);
						}
						results.clear();
					}
					currentCompany = getCompany(currentDataFile);
				}
					
				String table = currentDataFile.getName().substring(4, currentDataFile.getName().length() - 4);
				results.addAll(compareTable(model, table, currentCompany, currentDataFile));
			}
		}
		// last company
		if(null != results && results.size() != 0){	
			writeResults(model, currentCompany, results);
		}
	}
	

	
	private boolean isNewCompany(String currentCompany, File currentDataFile) {
		return !getCompany(currentDataFile).equalsIgnoreCase(currentCompany);
	}
	
	private String getCompany(File currentDataFile) {
		return currentDataFile.getName().substring(0, 3); // parse this from the file name (first 3 characters)
	}
	
	private List<String> compareTable(String model, String table, String company, File dataFile){
		TableDataSet fileData = getDataFromFile(dataFile);
		String serverData = httpClient.requestDataFromServer(model, table, company, false);
		List<String> returnResults = new ArrayList<String>();
		List<String> results = compareData(fileData, parseJSONString(serverData));
		if (!results.isEmpty()) {
			returnResults.add("\r\n" + "Table " + table + "\r\n");
		}
		returnResults.addAll(results);
		return returnResults;
	}
	
	
	private TableDataSet parseJSONString(String json){
		
		JSONTokener jtk = new JSONTokener(json);
		JSONObject jObj = null;
		TableDataSet tds = new TableDataSet();
		JSONObject rows = null;
		
		try {
			jObj = new JSONObject(jtk);
			rows = jObj.getJSONObject("rows");
			
			JSONArray names = rows.names();
			for(int i = 0; i < names.length(); i++){
				String itemName = names.getString(i);
				JSONObject namedRow = rows.getJSONObject(itemName);
	
				// see if it has groupedRows
				String groupName = null;
				JSONObject groupedRows = namedRow.getJSONObject("groupedDataRows");
				if(null != groupedRows && 0 != groupedRows.length()){
					// we have grouped rows
					JSONArray groups = groupedRows.names();
					for(int k = 0; k < groups.length(); k++){
						String groupRef = groups.getString(k);
						JSONObject dataDtos = groupedRows.getJSONObject(groupRef).getJSONObject("dataDtos");
						// iterate the years on this row
						JSONArray intervals = dataDtos.names();
						for(int l = 0; l < intervals.length(); l++)
						{
							String intervalName = intervals.getString(l);
							JSONObject dto = dataDtos.getJSONObject(intervalName);
							String value = dto.getString("value");
							// normalise numeric formatting
							try{
								Double.parseDouble(value.replaceAll(",", ""));
								value = value.replaceAll(",", ""); // remove numeric formatting e.g. 123,456.000 -> 123456.000
							}
							catch(NumberFormatException nfe){
								// not a numeric value - ignore.
							}
							
							// groupName is in the form ANH_GRP1 so remove first 4 characters
							groupName = dto.getJSONObject("groupEntry").getString("description").substring(4); 

							DataItem di = new DataItem(itemName, intervalName, groupName, value, "unavailable");
							tds.addDataItem(di);
						}
					}
				}
				else{
					// no grouped rows so just iterate the dataDtos
					groupName = "NOGROUP";
					JSONObject dataDtos = namedRow.getJSONObject("dataDtos");
					JSONArray intervals = dataDtos.names();
					for(int j = 0; j < intervals.length(); j++)
					{
						String intervalName = intervals.getString(j);
						JSONObject dto = dataDtos.getJSONObject(intervalName);
						String value = dto.getString("value");
						try{
							Double.parseDouble(value.replaceAll(",", ""));
							value = value.replaceAll(",", ""); // remove numeric formatting e.g. 123,456.000 -> 123456.000
						}
						catch(NumberFormatException nfe){
							// not a numeric value - ignore
						}
						DataItem di = new DataItem(itemName, intervalName, groupName, value, "unavailable");
						tds.addDataItem(di);
					}
					
				}
				
				
			}
			
		} catch (JSONException e) {
			
			System.out.println("Error parsing JSON: " + e.getMessage()+ " \n\n");
			System.out.println("content: \n\n" + json + "\n\n");
		}
		return tds;
	}
	
	private TableDataSet getDataFromFile(File file){
		FileReader fr = null;
		BufferedReader br = null;
		String currentLine = null;
		TableDataSet tds = new TableDataSet();
		try{
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while((currentLine = br.readLine()) != null){
				
				String[] parts = currentLine.split(",");
				if(null == parts || parts.length != 5){
					System.out.println("error reading file record: " + currentLine + " in " + file.getAbsolutePath());
				}
				
				DataItem di = new DataItem(parts[0].replaceAll("\"","") , parts[1].replaceAll("\"",""), parts[2].replaceAll("\"",""), parts[3].replaceAll("\"",""), parts[4].replaceAll("\"",""));
				tds.addDataItem(di);
			}
		}
		catch(IOException ioe){
			System.out.println("error processing file " + file.getName() + ":  " + ioe.getMessage());
		}
		finally{
			try{
				if(null != br){
					br.close();
				}
				if(null != fr){
					fr.close();
				}
			}
			catch(Exception e){
				// nada
			}
		}
		
		return tds; 
	}
	
	private List<String> compareData(TableDataSet fileData, TableDataSet jsonData){
		List<String> results = new ArrayList<String>();
		
		Collection<DataItem> fileItems = fileData.getItems();
		for(DataItem fileItem: fileItems){
			totalItems++; 
			 
			// filter out equations
			if ("CALC".equals(fileItem.getCellType())) {
				itemEquations++;
			}
			
			DataItem jsonItem = jsonData.getItem(fileItem.getIdentifier());

			if(null == jsonItem){
				results.add("no data returned for bon " + fileItem.getBonCode() + 
						    ", interval " + fileItem.getInterval() + 
						    ", group " + fileItem.getGroup() + "\r\n");
				itemsDifferent++;
				continue;
			}
			
			String fileString = fileItem.getValue();
			String jsonString = jsonItem.getValue();
			Float fileNumber = null;
			Float jsonNumber = null;
			try {
				fileNumber = Float.parseFloat(fileItem.getValue());
				jsonNumber = Float.parseFloat(jsonItem.getValue());
			} catch (NumberFormatException e) {
				// Its OK, it dosen't have to be a number.
			}
			
			// Filter out regression copycells with value of zero where system value is a zero length string.
			if (fileItem.getCellType().equals("COPYCELL") &&
				null != fileNumber && 
				0 == fileNumber &&
				jsonString.isEmpty()) {
				// These are effectively equal.
				continue;
			}

			if (null != fileNumber &&
				null != jsonNumber) {
				// both values are numbers so compare numerically.
				if(!fileNumber.equals(jsonNumber)){
					// both exist but the values are different
					results.add("different values for bon " + fileItem.getBonCode() + 
						    ", interval " + fileItem.getInterval() + 
						    ", group " + fileItem.getGroup() + 
						    ". file value: " + fileNumber + 
						    " system value: " + jsonNumber + "\r\n");
					itemsDifferent++;
					continue;
				}
			}
			else {
				// One or both values are strings so compare as strings.
				if(!fileString.equals(jsonString)){
					if ("ERR - Div by zero error".equals(jsonString)) {
						// a copycell from a calc. 
						itemEquations++;
						continue;
					}
					// both exist but the values are different
					results.add("different values for bon " + fileItem.getBonCode() + 
						    ", interval " + fileItem.getInterval() + 
						    ", group " + fileItem.getGroup() + 
						    ". file value: " + fileString + 
						    " system value: " + jsonString + "\r\n");
					itemsDifferent++;
					continue;
				}
			}
		}

		return results; 
	}
	
	private void writeResults(String model, String company, List<String> results) {
		// check the dir exists
		// check the file doesn't already exist
		File resultsDir = new File(resultFileDir);
		if(!resultsDir.exists()){
			resultsDir.mkdir();
		}
		File modelDir = new File(resultsDir, model);
		if(!modelDir.exists()){
			modelDir.mkdir();
		}
		if(null != results && 0 != results.size()){
			
			FileWriter fw = null;
			BufferedWriter br = null;
			try{		
				// we have some differences
				File outFile = new File(modelDir, company + "-diff.txt" );
				fw = new FileWriter(outFile);
				br = new BufferedWriter(fw);
				br.write("Difference report for company " + company + ", model " + model + "\r\n");
				br.write("Reported on " + new Date() + "\r\n");
				for(String result: results){
					br.write(result);
				}
				br.write("\r\n");
				br.write("\r\n");
				br.write("+++++++++++++++++++++++++++" + "\r\n");
				br.write("Equations         " + itemEquations + "\r\n");
				int itemsCorrect = totalItems - itemEquations - itemsDifferent;
				DecimalFormat formatTo2DP = new DecimalFormat("#0.00");
				br.write("Different values  " + itemsDifferent + " (" + formatTo2DP.format(((100.00/(itemsCorrect+itemsDifferent))*itemsDifferent)) + "%)" + "\r\n");
				br.write("Correct values    " + itemsCorrect + " (" + formatTo2DP.format(((100.00/(itemsCorrect+itemsDifferent))*itemsCorrect)) + "%)" + "\r\n");
				br.write("Total items      " + totalItems + "\r\n");
				br.write("+++++++++++++++++++++++++++" + "\r\n");
			}
			catch(IOException ioe){
				System.out.println("failed to write results: " + ioe.getMessage());
			}
			finally{
				totalItems = 0;
				itemEquations = 0;
				itemsDifferent = 0;
				try{
					if(null != br){
						br.flush();
						br.close();
					}
					if(null != fw){
						fw.flush();
						fw.close();
					}
				}
				catch(IOException ioe){
					// nothing we can do...
				}
			}
		
		}
	}
	
	
	
	

}
