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
package uk.gov.ofwat.fountain;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import uk.gov.ofwat.fountain.api.APIIntegrationTestSuite;
import uk.gov.ofwat.fountain.util.DBSetup;
import uk.gov.ofwat.fountain.util.DBSetupWithScripts;

@RunWith(Suite.class)
@Suite.SuiteClasses( { APIIntegrationTestSuite.class })
public class IntegrationTestSuite {
	
	@BeforeClass
	public static void setUp() throws Exception {
		File baseScript = new File("db/testSetup.sql");
		File updateFolder = new File("db/updates");		
		
		DBSetup setup = new DBSetupWithScripts(baseScript, updateFolder);
		setup.startup();
		
	}

}
