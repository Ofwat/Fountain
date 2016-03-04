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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.FileCopyUtils;


public class CollateFiles {
	private static final File baseFolder = new File("O:\\ALLshare\\Reservoir\\Dev\\12_Data\\fountain\\data");
	private static final File outputFolder = new File("D:\\temp\\data\\upload");
	
	private static final Log log = LogFactory.getLog(CollateFiles.class);
	
	public static void main(String[] args) {
		FileFilter filter = FileFilterUtils.suffixFileFilter("xml");
		int cnt = 1;
		
		outputFolder.mkdirs();
		try {
			FileUtils.cleanDirectory(outputFolder);
		}
		catch (IOException ex) {
			throw new RuntimeException("Couldn't delete " + outputFolder, ex);
		}
		outputFolder.mkdirs();
		
		log.info("Reading files in " + baseFolder);
		List<File> files = readFolder(baseFolder, filter);
		log.info("Read " + files.size() + " file(s)");
		
		for (File file : files) {
			String index = null;
			if (cnt<10)			index = "000" + cnt; 
			else if (cnt<100)	index = "00" + cnt; 
			else if (cnt<1000)	index = "0" + cnt; 
			else 				index = "" + cnt;
			cnt++;
			
			String name = file.getName();
			String dir = file.getParentFile().getName();
			File newFile = new File(outputFolder, index + "-" + dir + "-" + name);
			try {
				log.info(file + " -> " + newFile);
				FileCopyUtils.copy(file, newFile);
			}
			catch (IOException ex) {
				log.error("Couldn't copy " + file + " to " + newFile);
			}
		}
		
		
	}

	private static List<File> readFolder(File folder, FileFilter filter) {
		List<File> files = readFilesInFolder(folder, filter);
		Collections.sort(files);
		return files;
	}

	private static List<File> readFilesInFolder(File folder, FileFilter filter) {
		// Get the folders
		List<File> folders = new Vector<File>();
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				folders.add(file);
			}
		}
		
		// 2nd read to get all the xml files
		List<File> files = new Vector<File>();
		for (File file : folder.listFiles(filter)) {
			files.add(file);
		}
		
		// Recurse through folders
		for (File fld : folders) {
			files.addAll(readFilesInFolder(fld, filter));
		}
		return files;
	}
}
