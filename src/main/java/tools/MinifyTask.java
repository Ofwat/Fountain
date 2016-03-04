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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.MatchingTask;

import com.yahoo.platform.yui.compressor.Bootstrap;

public class MinifyTask extends MatchingTask  {

	File jsDir;
 
	public File getJsDir() {
		return jsDir;
	}

	public void setJsDir(File jsDir) {
		this.jsDir = jsDir;
	}

	public void execute() throws BuildException {

		DirectoryScanner ds = super.getDirectoryScanner(jsDir);
		String[] files = ds.getIncludedFiles();
		
		for (int i = 0; i < files.length; i++) {
			processFile(files[i]);
		}

	}
	
	private void processFile(String jsFile)throws BuildException{
		String oldCP =System.getProperty("java.class.path"); 
		System.setProperty("java.class.path", "lib/minitask/minitask.jar;lib/yuicompressor/yuicompressor-2.4.2.jar");
		String[] args = {jsDir.getAbsolutePath() + "/" + jsFile, "-o", jsDir.getAbsolutePath() + "/" + jsFile};
		try {
			Bootstrap.main( args);
		} catch (Exception e) {
			System.setProperty("java.class.path", oldCP);
			throw new BuildException(e);
			
		}
		System.setProperty("java.class.path", oldCP);
		
	}

}
