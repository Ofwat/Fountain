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

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import uk.gov.ofwat.fountain.dataformat.v1.AuditsDocument;




public class FileProcessor {

	public void processDirectory(File inDir, File outDir) {
		for (File inFile: inDir.listFiles()) {
			if (inFile.isFile() && FilenameUtils.getExtension(inFile.getName()).equalsIgnoreCase("txt")) {
				File outFile = new File(outDir.getAbsolutePath() + File.separator +  FilenameUtils.getBaseName(inFile.getName()) + ".fdf.xml"); 
				try {
					this.processSingleFile(inFile, outFile);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		
	}

	public void processSingleFile(File inFile, File outFile) throws Exception {
		List<String> lines;
		System.out.println("Processing file : " + inFile.getCanonicalPath());
		System.out.println("Creating file   : " + outFile.getCanonicalPath());
		try {
			lines = FileUtils.readLines(inFile);
		} catch (IOException e) {
			throw new Exception("Invalid Water2000 file.", e);
		}
		
		RecordReader recordReader = new RecordReader();
		List<Water2000record> records = recordReader.readWater2000Records(lines);
		AuditsFactory auditsFactory = new AuditsFactory();
		AuditsDocument auditsDoc = auditsFactory.createFountainAudits(records);
		FileUtils.writeStringToFile(outFile, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + auditsDoc.toString());
		System.out.println("Finished file : " + inFile.getCanonicalPath());
		System.out.println("Created file  : " + outFile.getCanonicalPath());
		System.out.println("\n");
	}

}
