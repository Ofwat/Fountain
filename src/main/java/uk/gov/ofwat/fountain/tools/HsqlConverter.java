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
package uk.gov.ofwat.fountain.tools;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HsqlConverter {
	
	public static void main(String[] args){
		
		String inFile = "db/testSetupConv.sql";
		String outFile = "db/converted.sql";
		HsqlConverter converter = new HsqlConverter();
		converter.processFile(inFile, outFile);
		System.out.println("HsqlConverter: done");
		
	}
	
	
	public void processFile(String inputFileName, String outputFileName){
		File inFile = new File(inputFileName);
		File outFile = new File(outputFileName);
		int lineNum = 0;
		/*
		 * This is the structure we're expecting
		 * 
		 * 
		    DROP TABLE IF EXISTS "tbl_company";
			CREATE TABLE  "tbl_company" (
  			"Id" int(10) unsigned NOT NULL AUTO_INCREMENT,
  			"Name" varchar(45) NOT NULL,
  			"Code" varchar(10) NOT NULL,
  			PRIMARY KEY ("Id")
			) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
			INSERT INTO "tbl_company" ("Id","Name","Code") VALUES 
		 * 
		 * 
		 */
		
		try{
			FileReader reader = new FileReader(inFile);
			LineNumberReader lnr = new LineNumberReader(reader);
			FileWriter fw = new FileWriter(outFile);
//			StringBuffer outBuffer = new StringBuffer();
			String currentLine = null;
			String insertLine = null;
			while(null != (currentLine = lnr.readLine())){
				lineNum = lnr.getLineNumber();
//				System.out.println("line number: " + lineNum);
				
				// what kind of a line is this?
				if(currentLine.indexOf("DROP TABLE") > -1){
					// remove any "fountain". prefix and pass it to the output as is
					fw.write(currentLine.replace("\"fountain\".", "").replaceAll("\"", ""));
					fw.write("\n");
					continue;
				}
				if(currentLine.indexOf("INSERT INTO") > -1){
					insertLine = processInsert(currentLine, fw);
					// line may end with "VALUES" - i.e. multiline statement with the actual values on the next line
					// or may be a complete statement on one line
					if(currentLine.trim().toUpperCase().endsWith("VALUES")){
						// the actual values are on the following lines
						continue;
					}
					// this is a single line statement with values following the insert statement
					// so we'll remove everything up to and including "VALUES" and let the rest be handled
					// by the following clause
					int valIndex = currentLine.toUpperCase().indexOf("VALUES");
					currentLine = currentLine.substring(valIndex + 6);
				}
				// lines that start with an opening bracket are insert statements
				// they can be copied directly
				if(currentLine.trim().startsWith("(")){
//					apppend the previous INSERT line as hsql doesn't allow multiple inserts in 1 statement
					fw.write(insertLine);
					if(currentLine.trim().endsWith(",")){
						currentLine = currentLine.substring(0, currentLine.length() - 1);
					}
					fw.write(currentLine.replace("\\\'", "''"));
					fw.write(";\n");
					continue;
				}
				if(currentLine.trim().startsWith("CREATE")){
					// deal with the whole create block
					if(currentLine.toLowerCase().indexOf("database") > -1){
						// it's a create database line not a create table
						continue;
					}
					processCreate(currentLine, lnr, fw);
					continue;
				}
				
			}
			
//			fw.write(outBuffer.toString());
			fw.flush();
			fw.close();
			
		}
		catch(IOException ioe){
			System.out.println("Error: " + ioe.getMessage());
		}
		catch(Exception e){
			System.out.println("Error at line " + lineNum + ": " + e.getMessage());
			e.printStackTrace(System.out);
		}
	}

	/**
	 *  remove fountain prefix and quotes from the field names 
	 * @param currentLine
	 * @param outBuffer
	 * @return - the "INSERT INTO.. VALUES(" part for use with subsequent inserts
	 */
	private String processInsert(String currentLine, FileWriter fw) {
		
		int valIndex = currentLine.toUpperCase().indexOf(" VALUES");
		//remove all quotes lto the left of valIndex
		String firstPart = currentLine.substring(0, valIndex).replaceAll("\"", "").replace("fountain.", "");
		String secondPart = currentLine.substring(valIndex);
		// remove trailing comma 
//		outBuffer.append(firstPart);
//		if(secondPart.endsWith(",")){
//			secondPart = secondPart.substring(0, secondPart.length() - 1);
//		}
//		outBuffer.append(secondPart);
////		and add semicolon
//		outBuffer.append(";");
		return firstPart + " VALUES ";
	}


	private  void processCreate(String currentLine, LineNumberReader lnr,
			FileWriter fw) throws IOException {
		/*
		 turn this 
		CREATE TABLE  "fountain"."tbl_tag" (
  		"id" int(10) unsigned NOT NULL AUTO_INCREMENT,
  		"label" varchar(45) NOT NULL,
  		PRIMARY KEY ("id")
		) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
		
		 into 
		CREATE TABLE tbl_tag (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
   		label VARCHAR(20))	 
		 */
		
		// first line is create statement. Remove the fountain prefix
		String createLine = currentLine.replace("\"fountain\".", "").replaceAll("\"", "").replaceAll("`", "");
		fw.write(createLine);
		fw.write("\n");
		List<ColDef> colDefs = new ArrayList<ColDef>();
		
		// process each line until the last ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
		
		while(!((currentLine = lnr.readLine()).indexOf("ENGINE") > -1)){
			// two twpes of line possible - a column definition or a primary key statement.
			// normalise the escape characters
			if(currentLine.trim().startsWith("\"")){
				// process col def
				ColDef cd = new ColDef();
				String[] parts = currentLine.trim().split(" ");
				cd.name = parts[0].replaceAll("\"", "");
				int sizeIndex = parts[1].indexOf("(");
				if(sizeIndex > 0){
					cd.type = parts[1].substring(0, sizeIndex);
					cd.size = Integer.parseInt(parts[1].substring(sizeIndex + 1, parts[1].indexOf(")") ));
				}
				else{
					cd.type = parts[1].replaceAll(",", "");
				}
					
				
				for(int i = 2; i < parts.length; i++){
					if(parts[i].equalsIgnoreCase("unsigned")){
						// ignore - no such concept in hsql
						continue;
					}
					if(parts[i].equalsIgnoreCase("NOT")){
						// "not null"
						cd.notNull = true;
						i++;
						continue;
					}
					if(parts[i].equalsIgnoreCase("DEFAULT")){
						// set the default value to the next token
						cd.defaultVal = parts[++i].replaceAll(",", "");
						continue;
					}
					if(parts[i].toUpperCase().startsWith("AUTO_INCREMENT")){
						cd.autoIncrement = true;
					}
					
				}
				
				colDefs.add(cd);
			}
			else if(currentLine.trim().startsWith("PRIMARY")){
				// parse out the primary key
				
			}
			
		}
		fw.write(buildCreate(colDefs));
		
		// currentline is now pointing to the ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
		// line - we can discard this and close off the create statement
		
		
	}
	
	/**
	 * build up a hsql complient create statement based on the col defs provided
	 * @param colDefs
	 * @return
	 */
	private String buildCreate(List<ColDef> colDefs) {
		StringBuilder sb = new StringBuilder();
		boolean firstPrimary= true; // there can only be one primary key in hsql
									// sometimes mysql will incorrectly assign multiple keys
		
		if(colDefs.size() == 0){
			// just return;
			return "";
		}
		for(ColDef cd: colDefs){
			sb.append(cd.name);
			sb.append(" ");
			String newType = typeConversions.get(cd.type);
			if(null == newType){
				System.out.println("error finding conversion type for " + cd.type);
			}
			sb.append(newType);
			if(cd.size > 0 && !("INTEGER".equals(newType))){
				sb.append("(");
				sb.append(cd.size);
				sb.append(")");
			}
			sb.append(" ");
			if(null != cd.defaultVal){
				sb.append(" DEFAULT ");
				sb.append(cd.defaultVal);
				sb.append(" ");
			}
			if(cd.autoIncrement){
				sb.append("GENERATED BY DEFAULT AS IDENTITY ");
			}
			if(cd.notNull){
				sb.append("NOT NULL ");
			}
			if(cd.primary){
				if(firstPrimary){
					sb.append("PRIMARY "); 
					firstPrimary = false;
				}
			}
			sb.append(",\n");
		}
		sb.deleteCharAt(sb.length() - 2); // remove trailing comma
		sb.append(");");
		sb.append("\n");
		return sb.toString(); 
	}
	
	private static Map<String, String> typeConversions = new HashMap<String, String>();
	static{
		typeConversions.put("int", "INTEGER");
		typeConversions.put("timestamp", "TIMESTAMP");
		typeConversions.put("varchar", "VARCHAR");
		typeConversions.put("datetime", "TIMESTAMP");
		typeConversions.put("tinyint", "INTEGER");
		typeConversions.put("longtext", "LONGVARCHAR");		
		typeConversions.put("smallint", "INTEGER");		
	}

	private class ColDef{
		String name;
		String type;
		String defaultVal;
		int size;
		boolean primary;
		boolean notNull;
		boolean autoIncrement;
	}

}
