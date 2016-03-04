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


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.Table;
import uk.gov.ofwat.fountain.domain.run.Run;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class PotDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
    
	@Autowired
	private PotDao potDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private IntervalDao intervalDao;
	
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private ModelService modelService;

	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		Pot pot = new Pot();
		Model model = modelDao.getAllModels(modelDao.getAllModelTypes().get(0).getId()).get(0);
		pot.setModelService(modelService);
		pot.setModelId(model.getId());
		pot.setItem(itemDao.getItemsByPosition(0, 1).get(0));
		pot.setInterval(intervalDao.getAll().get(0));
		Run run = new Run();
		run.setId(58);
		RunTag runTag = new RunTag(run, null);
		pot.setRunTag(runTag);
		
		Table table = tableDao.findByModelId(model.getId()).get(0);
		int id = potDao.create(pot, table.getId());
		assertTrue("failed to create pot", id > 0);
		
	
		System.out.println("TEST: Done");
}
}
