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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class DataDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private IntervalDao intervalDao;
	
	@Autowired
	private AuditDao auditDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ConfidenceGradeDao confidenceGradeDao;


	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		Data data = new Data();
		Company company = companyDao.getAllCurrent().get(0);
		Item item = itemDao.getItemsByPosition(0, 1).get(0);
		Interval interval = intervalDao.getAll().get(0);
		GroupEntry groupEntry = groupDao.findEntriesForCompanyAndGroup(company, item.getGroup()).get(0);
		data.setCompany(company);
		data.setGroupEntry(groupEntry);
		data.setItem(item);
		data.setInterval(interval);
		data.setValue("tet value");
		Branch branch = new Branch();
		branch.setId(9);
		data.setBranch(branch);
		
		int id = dataDao.create(data, buildAudit());
		Data rtn = dataDao.findById(id);
		assertNotNull("Failed to find new data", rtn);
		assertEquals("incorrect item", data.getItem(), rtn.getItem());
		assertEquals("incorrect year", data.getInterval(), rtn.getInterval());
		
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testUpdate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testUpdate");
		
		// first create a data item
		Data data = new Data();
		Company company = companyDao.getAllCurrent().get(0);
		Item item = itemDao.getItemsByPosition(0, 1).get(0);
		Interval interval = intervalDao.getAll().get(0);
		GroupEntry groupEntry = groupDao.findEntriesForCompanyAndGroup(company, item.getGroup()).get(0);
		data.setCompany(company);
		data.setGroupEntry(groupEntry);
		data.setItem(item);
		data.setInterval(interval);
		data.setValue("test value");
		Branch branch = new Branch();
		branch.setId(9);
		data.setBranch(branch);
		
		Audit audit1 = buildAudit();
		data.setId(dataDao.create(data, audit1));
		
		Audit audit2 = buildAudit();
		data.setValue("changed");
		dataDao.updateValue(data, audit2);
		
		Data ret = dataDao.findById(data.getId());
		assertNotNull("Unable to find updated data", ret);
		assertEquals("", "changed", ret.getValue());

	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testFindByAudit(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindByAudit");
		Data data1 = new Data();
		Data data2 = new Data();
		Company company = companyDao.getAllCurrent().get(0);
		Item item1 = itemDao.getItemsByPosition(0, 1).get(0);
		Item item2 = itemDao.getItemsByPosition(1, 1).get(0);
		GroupEntry groupEntry1 = groupDao.findEntriesForCompanyAndGroup(company, item1.getGroup()).get(0);
		GroupEntry groupEntry2 = groupDao.findEntriesForCompanyAndGroup(company, item2.getGroup()).get(0);
		Interval interval = intervalDao.getAll().get(0);

		Branch branch = new Branch();
		branch.setId(9);

		data1.setCompany(company);
		data1.setGroupEntry(groupEntry1);
		data2.setCompany(company);
		data2.setGroupEntry(groupEntry2);
		data1.setItem(item1);
		data2.setItem(item2);
		data1.setInterval(interval);
		data2.setInterval(interval);
		data1.setValue("111");
		data2.setValue("222");
		data1.setBranch(branch);
		data2.setBranch(branch);
		Audit audit = new Audit();
		audit.setComment("test audit");
		User user = new User();
		user.setId(1);
		user.setName("test user");
		audit.setUser(user);
		audit.setCompany(company);
		audit.setId(auditDao.create(audit));
		
		int id1 = dataDao.create(data1, audit);
		int id2 = dataDao.create(data2, audit);
		
		List<Data> retList = dataDao.getDataForAudit(audit);
		assertEquals(" data should be returned", 2, retList.size() );
		Data ret1 = retList.get(0);
		Data ret2 = retList.get(1);
		
		if(ret1.getId() != id1){
			// swap them round
			ret1 = retList.get(1);
			ret2 = retList.get(0);
		}
			
		assertEquals("incorrect id for second data", id2, ret2.getId());
		assertEquals("incorrect value for data 1", data1.getValue(), ret1.getValue());
		assertEquals("incorrect value for data 2", data2.getValue(), ret2.getValue());
		
		
	
		System.out.println("TEST: Done");
}
	
	//deferred until we have a requirement to implement this
	

	@Test
	@Rollback(true)
	public void testCGData() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCGData");
		ConfidenceGrade cg = confidenceGradeDao.getAll().get(0);
		
		Company company = companyDao.getAllCurrent().get(0);
		Item item = itemDao.getItemsByPosition(0, 1).get(0);
		Interval interval1 = intervalDao.getAll().get(0);
		Interval interval2 = intervalDao.getAll().get(1);
		GroupEntry groupEntry = groupDao.findEntriesForCompanyAndGroup(company, item.getGroup()).get(0);
		Audit audit = buildAudit();

		// Data without a cg
		Data noCG = new Data();
		noCG.setCompany(company);
		noCG.setGroupEntry(groupEntry);
		noCG.setItem(item);
		noCG.setInterval(interval1);
		noCG.setValue("test value");
		Branch branch = new Branch();
		branch.setId(9);
		noCG.setBranch(branch);

		int noCGid = dataDao.create(noCG, audit);
		assertTrue("No data without a CG created", noCGid>0);
		Data noCG2 = dataDao.findById(noCGid);
		assertNotNull("Unable to read back data", noCG2);
		assertTrue("Non-cg data had a CG when read back", noCG.getConfidenceGrade()==null && noCG2.getConfidenceGrade()==null);
		
		// Data with a cg
		Data withCG = new Data();
		withCG.setCompany(company);
		withCG.setGroupEntry(groupEntry);
		withCG.setItem(item);
		withCG.setInterval(interval2);
		withCG.setValue("test value");
		withCG.setConfidenceGrade(cg);
		withCG.setBranch(branch);
		
		int withCGid = dataDao.create(withCG, audit);
		assertTrue("No data with a CG created", withCGid>0);
		Data withCG2 = dataDao.findById(withCGid);
		assertNotNull("Unable to read back data", withCG2);
		assertTrue("CG data had no CG when read back", withCG.getConfidenceGrade()!=null && withCG2.getConfidenceGrade()!=null);
		assertEquals("CG data has different CH when read back", withCG.getConfidenceGrade().getCode(), withCG2.getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}
	
	private Audit buildAudit() {
		Audit audit = new Audit();

		User user = new User();
		user.setId(1);
		user.setName("test user");
		audit.setUser(user);

		Company company = new Company();
		company.setId(1);
		audit.setCompany(company);

		audit.setComment("test audit");
		
		int auditId = auditDao.create(audit);
		audit.setId(auditId);
		
		return audit;

	}

}
