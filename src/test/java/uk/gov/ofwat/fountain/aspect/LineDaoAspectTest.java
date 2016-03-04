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
package uk.gov.ofwat.fountain.aspect;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.aspect.dao.CacheTestLineDao;
import uk.gov.ofwat.fountain.domain.Line;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:cacheAspectTest_beans.xml" })
public class LineDaoAspectTest {
   
    @Autowired    
    private CacheTestLineDao cacheTestDao;
	
	@Autowired    
    private DaoCacheService daoCacheService;
   
	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void testCachedObjectIsReturned() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCachedObjectIsReturned");
		// set up a list of lines to be cached
		List<Line> lines = new ArrayList<Line>();
		Line line = new Line();
		
		line.setTableId(1);
		line.setItemId(2);
		lines.add(line);
		
		cacheTestDao.setLines(lines);
		Assert.assertEquals(2, ((List<Line>)cacheTestDao.findByTableItem(1, 2)).get(0).getItemId());
		Assert.assertEquals(2, ((List<Line>)cacheTestDao.findByTableItem(1, 2)).get(0).getItemId());
		
	// set up another list of lines
		List<Line> lines2 = new ArrayList<Line>();
		Line line2 = new Line();
		
		line2.setTableId(2);
		line2.setItemId(3);
		lines2.add(line2);
		
		
		
		cacheTestDao.setLines(lines2);
		// ... but it is not returned
		Assert.assertNotSame(3, ((List<Line>)cacheTestDao.findByTableItem(1, 2)).get(0).getItemId());
		// ... because the response has been cached by the aspect.
		Assert.assertSame(2, ((List<Line>)cacheTestDao.findByTableItem(1, 2)).get(0).getItemId());
	
		System.out.println("TEST: Done");
}
	
}
