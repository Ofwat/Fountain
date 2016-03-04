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

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.AuditDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.User;


public class AuditServiceTest extends TestCase {
	
	private AuditServiceImpl auditService;
	private AuditDao mockAuditDao;
	private User mockUser;
	private Company mockCompany;
	private String userName = "username";
	private int auditId = 1;
	private String comment = "comment";

	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		auditService = new AuditServiceImpl();
		mockAuditDao = mock(AuditDao.class);
		mockCompany = mock(Company.class);
		mockUser = mock(User.class);
	
		System.out.println("TEST: Done");
}

	public void testGetUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetUser");
		when(mockUser.getName()).thenReturn(userName);
		when(mockAuditDao.create(isA(Audit.class))).thenReturn(auditId);
		
		auditService.setAuditDao(mockAuditDao);
		Audit audit = auditService.createAudit(comment, mockUser, mockCompany);

		Assert.assertEquals(userName, audit.getUser().getName());
	
		System.out.println("TEST: Done");
}
	
	public void testGetAuditedValues() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetAuditedValues");
		List<AuditedValue> auditValues = new ArrayList<AuditedValue>();
		AuditedValue av1 = new AuditedValue();
		av1.setId(1);
		auditValues.add(av1);
		AuditedValue av2 = new AuditedValue();
		av2.setId(2);
		auditValues.add(av2);
		AuditedValue av3 = new AuditedValue();
		av3.setId(3);
		auditValues.add(av3);

		when(mockAuditDao.getAuditedValues(1, 2, 3, 4)).thenReturn(auditValues);
		auditService.setAuditDao(mockAuditDao);
		List<AuditedValue> avs = auditService.getAuditedValues(1, 2, 3, 4);
		
		Assert.assertEquals(1, avs.get(0).getId());
		Assert.assertEquals(2, avs.get(1).getId());
		Assert.assertEquals(3, avs.get(2).getId());
	
		System.out.println("TEST: Done");
}
}

