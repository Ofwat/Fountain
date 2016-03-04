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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to apply the script to the database setup
 * Using processes directly as the DBUnit scripts did
 * not cope with the large startup scripts
 */
public class DBSetupWithScripts implements DBSetup {
	private static Log log = LogFactory.getLog(DBSetupWithScripts.class);
	private File baseScript;
	private File updateFolder;
	
	public DBSetupWithScripts(File baseScript, File updateFolder) {
		this.baseScript = baseScript;
		this.updateFolder = updateFolder;
	}

	public void startup() throws RuntimeException {
		log.info("Setting up the database");
		File[] updateFiles = getUpdateFiles();
		/*
		try{
			System.out.println(baseScript.getCanonicalPath());
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		*/
		processFile(baseScript);
		if (updateFiles!=null) {
			for (File file : updateFiles) {
				processFile(file);
			}
		}
	}

	public void teardown() throws RuntimeException {
		// Nothing to do for this
	}

	private void processFile(File file) {
		log.info("Processing " + file);
		try {
			ResourceBundle rb = ResourceBundle.getBundle("test");
			String dbName = rb.getString("test.db.name");
			String bin = rb.getString("test.db.bin");
			String port = rb.getString("test.db.port");
			String user = rb.getString("test.db.user");
			String password = rb.getString("test.db.password");
			String cmd = "cmd.exe /C \"" +
				bin + " -u" + user + " -p" + password + " " +
				"--port=" + port + " " +
				dbName + " < " + file.getPath() + " >>dbLog.txt\"";
			
			log.info("Test setUp start using " + dbName);
			log.info("About to run " + cmd);
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
			if (process.exitValue() != 0) {
				throw new RuntimeException("Failed to process " + file);
			}
			
			log.info("Finished processing " + file);
		}
		catch (IOException ex) {
			throw new RuntimeException("Bad IO while processing " + file, ex);
		}
		catch (InterruptedException ex) {
			throw new RuntimeException("Interrupted while processing " + file, ex);
		}
	}
	
	/**
	 * Return a sorted array of SQL files in the specified folder
	 */
	private File[] getUpdateFiles() {
		File[] files = updateFolder.listFiles(new SQLFilter());
		Arrays.sort(files, new FileComparator());
		return files;
	}
	
	private class SQLFilter implements FileFilter {
		public boolean accept(File pathname) {
			String name = pathname.getName().toUpperCase();
			return name.endsWith(".SQL");
		}
		
	}
	
	private class FileComparator implements Comparator<File> {

		public int compare(File o1, File o2) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
		
	}

}
