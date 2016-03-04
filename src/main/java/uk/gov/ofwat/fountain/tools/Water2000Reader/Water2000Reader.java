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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;



public class Water2000Reader {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String HELP_TEXT = "Please";
		if (0 == args.length ||
			2 < args.length) {
			System.out.println(HELP_TEXT);
			System.exit(1);
		}
		
		String inFileName = args[0];
		String outFileName = null;
			
		if (2 == args.length) {
			outFileName = args[1];
		}
		
		File inFile = new File(inFileName); // "D:/workspaces/fountain/FountainDataFormat/test-data/ANG_JR_12MAY10.TXT"
		if (inFile.isFile()) {
			if (!FilenameUtils.getExtension(inFileName).equalsIgnoreCase("txt")) {
				System.out.println(HELP_TEXT);
				System.exit(1);
			}
			if (null == outFileName) {
				outFileName = FilenameUtils.removeExtension(inFileName) + ".fdf.xml";
			}
			File outFile = new File(outFileName);
			FileUtils.touch(outFile);
			if (outFile.isFile()) {
				FileProcessor fileProcessor = new FileProcessor();
				fileProcessor.processSingleFile(inFile, outFile);
			}
			else if (outFile.isDirectory()) {
				outFile = new File(outFileName + File.pathSeparator + FilenameUtils.getBaseName(inFileName));
				if (outFile.isFile()) {
					FileProcessor fileProcessor = new FileProcessor();
					fileProcessor.processSingleFile(inFile, outFile);
				}
				else {
					System.out.println(HELP_TEXT);
					System.exit(1);
				}
			}
			else {
				System.out.println(HELP_TEXT);
				System.exit(1);
			}
		}
		else if (inFile.isDirectory()) {
			if (null == outFileName) {
				outFileName = FilenameUtils.removeExtension(inFileName);
			}
			File outFile = new File(outFileName);
			if (outFile.isFile()) {
				System.out.println(HELP_TEXT);
				System.exit(1);
			}
			else if (inFile.isDirectory()) {
				FileProcessor fileProcessor = new FileProcessor();
				fileProcessor.processDirectory(inFile, outFile);
			}
			else {
				System.out.println(HELP_TEXT);
				System.exit(1);
			}
		}
		else {
			System.out.println(HELP_TEXT);
			System.exit(1);
		}
	}

}

