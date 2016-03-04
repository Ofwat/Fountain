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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class ModelDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private ModelDao modelDao;

	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		ModelType mt = modelDao.getAllModelTypes().get(0);
		Branch bt = null;
		Model m = new Model("test model", "CODE", mt);
		m.setBranch(bt);
		int id = modelDao.create(m);
		
		Model rtn = modelDao.findById(id);
		assertNotNull("", rtn);
		assertEquals("returned name incorrect", "test model", rtn.getName());
		assertEquals("returned code incorrect", "CODE", rtn.getCode());
		assertEquals("returned modelType incorrect", mt, rtn.getModelType());
	
		System.out.println("TEST: Done");
}
	
	public void testSetup(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSetup");
		Model m = modelDao.getAllModels(modelDao.getAllModelTypes().get(0).getId()).get(0);
		assertEquals("setup failed", "MODEL 1", m.getName());
	
		System.out.println("TEST: Done");
	}
	
	
	public void testAddModelRunDependency(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddModelRunDepdendency");
	}
	
	public void testRemoveModelRunDependency(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testRemoveModelRunDepdendency");
	}
	

}
