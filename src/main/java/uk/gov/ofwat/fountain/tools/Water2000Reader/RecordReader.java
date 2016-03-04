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
package uk.gov.ofwat.fountain.tools.Water2000Reader;

import java.util.ArrayList;
import java.util.List;

public class RecordReader {

	List<Water2000record> records;;
	String dataset;
	String company;
	String item;
	Water2000record previousRecord; 

	public List<Water2000record> readWater2000Records(List<String> lines) throws Exception {
		
		records = new ArrayList<Water2000record>();
		dataset = "";
		company = "";
		item = "";
		previousRecord = null; 
		
		int headerCount = 0;
			for (String line: lines) {
				if (0 == headerCount && 0 == line.length()) {
					// header line 1
					headerCount++;
				}
				else if (1 == headerCount && line.startsWith("DATASET:")) {
					// header line 2
					// dataset
					headerCount++;
					dataset = line.substring("DATASET:".length()).trim();
				}
				else if (2 == headerCount && line.contains("REG.AUDIT")) {
					// header line 3
					headerCount++;
				}
				else if (3 == headerCount && line.contains("COMPANY")) {
					headerCount++;
				}
				else if (4 == headerCount && line.startsWith("ITEM")) {
					// header line 5
					// company
					headerCount++;
					company = line.substring("ITEM".length()).trim();
				}
				else if (5 == headerCount && line.startsWith("-------------- ")) {
					// header line 6
					headerCount++;
				}
				else if (5 < headerCount) {
					headerCount++;
					if (line.contains("Year       Action             From Data      To Data        User     Date      Time     Comments")) {
						// record header line
						// item line
						item = line.substring(0, 14).trim();
					}
					else if (line.contains("===================================================================================================================")) {
						// underscore line
					}
					else if (line.length() >= 102) {
						// data line
						Water2000record w2Record;
						try {
							w2Record = new Water2000record(line, dataset, company, item);
							records.add(w2Record);
							previousRecord = w2Record;
						} catch (Exception e) {
							// extra comments line
							previousRecord.setComments(previousRecord.getComments() + " " + line.trim());
						}
					}
					else {
						// extra comments line
						previousRecord.setComments(previousRecord.getComments() + " " + line.trim());
					}
				}
				else {
					throw new Exception("Invalid Water200 file. Error on line no. " + headerCount + ". [" + line + "].");
				}
		} 
		return records;
	}

}
