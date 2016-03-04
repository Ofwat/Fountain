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
package uk.gov.ofwat.fountain.api;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

public class TransferExistingJSP implements  ApplicationContextAware {
	 private static ApplicationContext applicationContext = null;
	// class to check if any pre-created jsps are available
	// transfer them to the usual jsp location
	
	private static String webLocation;
	private static String localLocation;
	private static Logger logger = Logger.getLogger(TransferExistingJSP.class);
	public String getWebLocation() {
		return webLocation;
	}

	public void setWebLocation(String web) {
		webLocation = web;
	}

	public String getLocalLocation() {
		return localLocation;
	}

	public void setLocalLocation(String local) {
		TransferExistingJSP.localLocation = local;
	}

	public TransferExistingJSP(){
		
	}
	
	public boolean moveJSPs(){
		logger.debug("starting moveJSPs");
		try {
			// find where the jsps are...
			File localDir = new File(localLocation);

			Resource webResourceDir = applicationContext.getResource(webLocation);

			File webDir = webResourceDir.getFile();
			if (!webDir.exists()){
				webDir.mkdir();
			}

			FileUtils.copyDirectory(localDir, webDir);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage());
		}

		logger.debug("finished moveJSPs");
		return true;
	}


	public void setApplicationContext(ApplicationContext context)
	throws BeansException {
		applicationContext = context;
	}
}
