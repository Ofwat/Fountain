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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import uk.gov.ofwat.fountain.api.table.TableWrapperRendererTest;
import uk.gov.ofwat.fountain.api.table.TableWrapperRendererTestRowAndColSpans;

/**
 * Unit test suite for the API package
 * 
 * do not add heavyweight file import tests - put them in the APITestSuite only.
 * These tests get run by UnitTestSuite and GrandTestSuite
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { 
		DataServiceTest.class, 
		ItemServiceTest.class,
		ReferenceServiceTest.class, 
		ModelServiceTest.class,
		UserServiceTest.class, 
		AuditServiceTest.class, 
		LockServiceTest.class,
		BasketServiceTest.class, 
		TableServiceTest.class,
		TableWrapperRendererTest.class,
		TableWrapperRendererTestRowAndColSpans.class
		})
public class APIUnitTestSuite {

	public class AllTests {
	}

}
