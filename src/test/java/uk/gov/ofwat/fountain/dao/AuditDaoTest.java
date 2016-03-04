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
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class AuditDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private AuditDao auditDao;
	
	@Autowired
	private DataDao dataDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private IntervalDao intervalDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	@Rollback(true)
	public void testCreate(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreate");
		Audit audit = new Audit();
		audit.setComment("oh the huge manatee");
		User user = new User();
		user.setId(1);
		user.setName("Michael Maus");
		audit.setUser(user);
		Company company = new Company();
		company.setId(2);
		CompanyType companyType = new CompanyType(); 
		companyType.setId(1);
		company.setCompanyType(companyType);
		audit.setCompany(company);
		int id = auditDao.create(audit);
		assertTrue("Id should be greater than 0", id > 0);
		Audit ret = auditDao.findById(id);
		assertNotNull("Failed to retrieve the newly created audit", ret);
		assertEquals("Comments don't match", audit.getComment(), ret.getComment());
//		assertEquals("Companies don't match", audit.getCompany().getId(), ret.getCompany().getId());
		assertEquals("Users don't match", audit.getUser().getId(), ret.getUser().getId());
	
		System.out.println("TEST: Done");
}
	
	@Test
	@Rollback(true)
	public void testFindByUser(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindByUser");
		Audit audit1 = new Audit();
		Audit audit2 = new Audit();
		Audit audit3 = new Audit();

		audit1.setComment("It's life Jim");
		audit2.setComment("le weekend c'est ici");
		audit3.setComment("omoshiroi desu");
		
		User user1 = new User();
		user1.setId(1);
		user1.setName("Zim the mighty");
		User user2 = new User();
		user2.setId(3);
		user2.setName("Grr the incapable");
		
		audit1.setUser(user1);
		audit2.setUser(user2);
		audit3.setUser(user2);
		

		Company company1 = new Company();
		CompanyType companyType1 = new CompanyType(); 
		companyType1.setId(1);
		company1.setCompanyType(companyType1);
		company1.setId(1);
		Company company2 = new Company();
		CompanyType companyType2 = new CompanyType(); 
		companyType2.setId(1);
		company2.setCompanyType(companyType2);
		company2.setId(2);

		audit1.setCompany(company1);
		audit2.setCompany(company2);
		audit3.setCompany(company2);

		int id1 = auditDao.create(audit1);
		auditDao.create(audit2);
		auditDao.create(audit3);
		
		List<Audit> retList = auditDao.findByUser(user2);
		
		assertNotNull("No audits returned", retList);
		assertEquals("Expected 2 audits", 2,  retList.size());
		
		Audit ret1 = retList.get(0);
		Audit ret2 = retList.get(1);
		
		if(ret1.getId() != id1){
			// id's in wrong order - swap them
			ret1 = retList.get(1);
			ret2 = retList.get(0);
		}
		
		assertEquals("comment 1 doesn't match", audit2.getComment(), ret2.getComment());
		assertEquals("comment 2 doesn't match", audit3.getComment(), ret1.getComment());
	
		System.out.println("TEST: Done");
}
	
	
	@Test
	@Rollback(true)
	public void testFindByData(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindByData");
		
		// create a new Item so we aren't clashing with existing data
		Item item = new Item();
		item.setCode("B999999");
		item.setName("B999999");
		item.setUnit("DM");
		Group group = new Group();
		group.setId(1);
		group.setName("NO GROUP");
		item.setGroup(group);
		item.setId(itemDao.create(item));
		
		Company company = companyDao.getAllCurrent().get(0);
		Interval interval = intervalDao.getAll().get(0);		
		GroupEntry groupEntry = groupDao.findEntriesForCompanyAndGroup(company, item.getGroup()).get(0);
		
		Data data = new Data();
		data.setItem(item);
		data.setValue("123");
		data.setCompany(company);
		data.setGroupEntry(groupEntry);
		data.setInterval(interval);
		Branch branch = new Branch();
		branch.setId(9);
		data.setBranch(branch);
		
		Audit audit1 = new Audit();
		Audit audit2 = new Audit();
		Audit audit3 = new Audit();
		
		audit1.setComment("alas poor Yorik");
		audit2.setComment("nothing comes of nothing");
		audit3.setComment("neither fish nor fowl");
		
		List<User> users = userDao.getAll();
		User user1 = users.get(0);
		
		audit1.setUser(user1);
		audit2.setUser(user1);
		audit3.setUser(user1);

		Company company1 = new Company();
		company1.setId(1);
		CompanyType companyType1 = new CompanyType(); 
		companyType1.setId(1);
		company1.setCompanyType(companyType1);
		Company company2 = new Company();
		company2.setId(2);
		CompanyType companyType2 = new CompanyType(); 
		companyType2.setId(1);
		company.setCompanyType(companyType2);

		audit1.setCompany(company1);
		audit2.setCompany(company2);
		audit3.setCompany(company2);
		Calendar cal = new GregorianCalendar();
		cal.set(2011, Calendar.JANUARY, 1);
		audit1.setDate(cal.getTime());
		cal.set(2011, Calendar.JANUARY, 2);
		audit2.setDate(cal.getTime());
		audit1.setId(auditDao.create(audit1));
		audit2.setId(auditDao.create(audit2));
		// audit 3 uses the database to timestamp the record, so will be most recent
		audit3.setId(auditDao.create(audit3));

		// make 3 data entries for the same item, company and year with 3 audits
		dataDao.create(data, audit1);
		data.setValue("234");
		dataDao.create(data, audit2);
		data.setValue("345");
		dataDao.create(data, audit3);
		// need to create some data against
		
		int itemId = data.getItem().getId();
		int intervalId = data.getInterval().getId();
		int companyId = data.getCompany().getId();
		int groupEntryId = data.getGroupEntry().getId();
		List<AuditedValue> retList = auditDao.getAuditedValues(itemId, intervalId, companyId, groupEntryId);
		
		assertNotNull("No Audits returned", retList);
		assertEquals("Expected 3 audits for the given data", 3, retList.size());
		
		// now make sure all our audits returned
		assertEquals("Audit 1 is wrong", retList.get(0).getComment(), audit3.getComment()); 
		assertEquals("Audit 2 is wrong", retList.get(1).getComment(), audit2.getComment()); 
		assertEquals("Audit 3 is wrong", retList.get(2).getComment(), audit1.getComment()); 
	
		System.out.println("TEST: Done");
}
}
