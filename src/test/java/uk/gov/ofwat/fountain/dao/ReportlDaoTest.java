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
package uk.gov.ofwat.fountain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportDisplay;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ReportlDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private ReportDao reportDao;

	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		ReportDefinition r = new ReportDefinition();
	
		r.setName("Test Report");
		r.setOwnerUser("username");
		r.setPublicReport(true);
		r.setTeamId(1);
		r.setDeleted(false);
	
		ArrayList<Integer> intervalIds = new ArrayList<Integer>();
		intervalIds.add(1);
		r.setIntervalIds(intervalIds);
		
		ArrayList<ModelItem> modelItems = new ArrayList<ModelItem>();
		ModelItem mi = new ModelItem();
		mi.setDescription("desc");
		mi.setItemCode("b101");
		mi.setItemId(1);
		mi.setItemName("item name");
		mi.setModelCode("JR");
		mi.setModelId(1);
		mi.setModelName("jr");
		mi.setTeamName("team name");
		mi.setModelPropertiesMapId(11);
		modelItems.add(mi);
		r.setModelItems(modelItems);
		
		ArrayList<Integer> companyIds = new ArrayList<Integer>();
		companyIds.add(3);
		r.setCompanyIds(companyIds);
		
		ArrayList<String> layoutLeft = new ArrayList<String>();
		layoutLeft.add("Company");
		layoutLeft.add("Item");
		r.setLayoutLeft(layoutLeft);
		ArrayList<String> layoutTop = new ArrayList<String>();
		layoutTop.add("Year");
		r.setLayoutTop(layoutTop);
		
		ReportDisplay rd = new ReportDisplay();
		
		rd.setDisplayBoncode(true);
		rd.setDisplayCGs(false);
		rd.setDisplayCompanyAcronym(true);
		rd.setDisplayCompanyName(false);
		rd.setDisplayDesc(true);
		rd.setDisplayModel(false);
		rd.setDisplayUnit(true);
		r.setReportDisplay(rd);
		
		int id = reportDao.create(r);
		
		ReportDefinition rtn = reportDao.findById(id);
		assertNotNull("", rtn);
		assertEquals("returned name incorrect", "Test Report", rtn.getName());
		assertEquals("returned owner incorrect", "username", rtn.getOwnerUser());

	
		System.out.println("TEST: Done");
}	

}
