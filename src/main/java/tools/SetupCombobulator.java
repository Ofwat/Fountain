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

package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetupCombobulator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File inFile = new File("setup.xml");
		File outFile = new File("out.xml");
		FileReader fRead = null;
		BufferedReader bRead = null;
		FileWriter fWrite = null;
		BufferedWriter bWrite = null;
		List<String> valueLines = new ArrayList<String>();
		
		try{
			
			fRead = new FileReader(inFile);
			bRead = new BufferedReader(fRead);
			fWrite = new FileWriter(outFile);
			bWrite = new BufferedWriter(fWrite);
			String line = null;
			int valueId = 1;
			while ((line = bRead.readLine()) != null)   {
				line = line.trim();
				String[] tokens = line.split("\\s", 3);
				
				if(!tokens[0].startsWith("</dataset>") && !tokens[0].startsWith("<tbl_data")){
					// just write the line straight out
					bWrite.write(line + "\n");
					bWrite.flush();
				
			    }
				else if(tokens[0].startsWith("<tbl_data")){
					// extract the value and create a new value line.
					// split the whole line into tokens - things aren't always in the right order
					tokens = line.split("\\s");
					String value = null;
					String dataId = null;
					String auditId = null;
					String confidenceGradeId = null;
					for(String token: tokens){
						if(token.startsWith("id=\"")){
							dataId = extractAtrbVal(token);// get the number
							bWrite.write(token);
							bWrite.write(" ");
						}
						else if(token.startsWith("value=\"")){
							value = extractAtrbVal(token);
						}
						else if(token.startsWith("auditId=\"")){
							auditId = extractAtrbVal(token);
						}
						else if(token.startsWith("confidenceGradeID=\"")){
							confidenceGradeId = extractAtrbVal(token);
						}
						else if(token.startsWith("timestamp=\"")){
							// not used anymore
						}
						else if(token.startsWith("<tbl_data")){
							bWrite.write(token);
							bWrite.write(" ");
						}
						else if(token.startsWith("intervalId=")){
							bWrite.write(token);
							bWrite.write(" ");
						}
						else if(token.startsWith("itemId=")){
							bWrite.write(token);
							bWrite.write(" ");
						}
						else if(token.startsWith("groupEntryId=")){
							bWrite.write(token);
							bWrite.write(" ");
						}
						else if(token.startsWith("companyId=")){
							bWrite.write(token);
							bWrite.write(" ");
						}
						else{
							// skip this field
						}
					}
					bWrite.write("\n");
					// create a new value row
					StringBuffer valueBuf = new StringBuffer();
					valueBuf.append("<tbl_value id=\"");
					valueBuf.append(valueId++);
					valueBuf.append("\" value=\"");
					valueBuf.append(value);
					valueBuf.append("\" dataId=\"");
					valueBuf.append(dataId);
					valueBuf.append("\" ");
					valueBuf.append("auditId=\"");
					if(null != auditId){
						valueBuf.append(auditId);
					}
					else{
						// default audit id for previously unadited data
						valueBuf.append(1);
					}
					valueBuf.append("\" ");
				
					if(null != confidenceGradeId){
						valueBuf.append("confidenceGradeId=\"");
						valueBuf.append(confidenceGradeId);
						valueBuf.append("\" ");
					}
					
					valueBuf.append(" />\n");
					valueLines.add(valueBuf.toString());
				}
				else if(tokens[0].startsWith("</dataset>")){
					// last line of the file so write out the values
					for(String value: valueLines){
						bWrite.write(value);
						
					}
					bWrite.write("</dataset>\n");
					bWrite.flush();
				}
			}
		}
		catch(IOException ioe){
			System.out.println("error: " + ioe.getMessage());
		}

	}
	
	private static String extractAtrbVal(String attrib){
		int firstQuote = attrib.indexOf("\"");
		int secondQuote = attrib.indexOf("\"", firstQuote+1);
		if(-1 != firstQuote && -1!= secondQuote){
			return attrib.substring(firstQuote+1, secondQuote);
		}
		return null;
	}

}
