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

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Table;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class TableDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
    
	@Autowired
	private ModelDao modelDao;
	
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Test
	@Rollback(true)
	public void testCreate() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		ModelType modelType = modelDao.getAllModelTypes().get(0);
		Model model = modelDao.getAllModels(modelType.getId()).get(0);
		
		Table table = new Table();
		table.setName("test table");
		table.setModelId(model.getId());
		
		int id = tableDao.create(table);
		Table rtn = tableDao.findById(id);
		Assert.assertNotNull("retuned table missing",rtn);
		Assert.assertEquals("returned table name incorrect", table.getName(), rtn.getName());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testFindByModelIdAndCompanyType() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindByModelIdAndCompanyType");
		ModelType modelType = modelDao.getAllModelTypes().get(0);
		Model model = modelDao.getAllModels(modelType.getId()).get(0);
		
		CompanyType ct = companyDao.getAllCompanyTypes().get(0);
		
		Table table = new Table();
		table.setName("test table");
		table.setModelId(model.getId());
		table.setCompanyTypeId(ct.getId());
		
		tableDao.create(table);
		
		List<Table> tables = tableDao.findByModelIdAndCompanyType(model.getId(), ct.getId());
		
		// Get the last table, the newest added table
		Table tbl = tables.get(tables.size()-1);
		
		Assert.assertNotNull("retuned table missing",tbl);
		Assert.assertEquals("returned table name incorrect", table.getName(), tbl.getName());
		Assert.assertEquals("returned incorrect companyType", table.getCompanyTypeId(), tbl.getCompanyTypeId());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testAddCompanyTable(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testAddCompanyTable");
		ModelType modelType = modelDao.getAllModelTypes().get(0);
		Model model = modelDao.getAllModels(modelType.getId()).get(0);
		
		Company company = companyDao.getAllCurrent().get(0);
		
		Table table = new Table();
		table.setName("company test table");
		table.setModelId(model.getId());
		
		int tableId = tableDao.create(table);
		tableDao.addCompanyTable(company.getId(), model.getId(), tableId);
		
		List<Table> tables = tableDao.findByModelIdAndCompanyId(model.getId(), company.getId());
		Table tbl = tables.get(0);
		Assert.assertNotNull("retuned table missing",tbl);
		Assert.assertEquals("returned table name incorrect", table.getName(), tbl.getName());
	
		System.out.println("TEST: Done");
}
	
	
	
}
