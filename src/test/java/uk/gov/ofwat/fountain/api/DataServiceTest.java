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

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.BranchDao;
import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.ConfidenceGrade;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;


public class DataServiceTest extends TestCase {
	private DataServiceImpl dataService;
	private DataDao mockDataDao;
	private Pot mockPot;
	private Company mockCompany;
	private GroupEntry mockGroupEntry;
	private Item mockItem;
	private Interval mockPotInterval;
	private Interval mockDataInterval;
	private Interval mockYearFromRefService;
	private Data mockData;
	private Audit mockAudit;
	private ReferenceService mockReferenceService;
	private AuditService mockAuditService;
	private ItemService mockItemService;
	private LockService mockLockService;
	private GroupService mockGroupService;
	private ConfidenceGradeService mockConfidenceGradeService;
	private User user;
	private Model mockModel;
	private Branch mockBranch;
	private UserService mockUserService;
	private BranchDao mockBranchDao;
	
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		dataService = new DataServiceImpl();
		mockDataDao = mock(DataDao.class);
		mockPot = mock(Pot.class);
		mockCompany = mock(Company.class);
		mockGroupEntry = mock(GroupEntry.class);
		mockItem = mock(Item.class);
		mockPotInterval = mock(Interval.class);
		mockDataInterval = mock(Interval.class);
		mockYearFromRefService = mock(Interval.class);
		mockData = mock(Data.class);
		mockAudit = mock(Audit.class);
		mockModel = mock(Model.class);
		mockBranch = mock(Branch.class);
		mockReferenceService = mock(ReferenceService.class);
		mockAuditService = mock(AuditService.class);
		mockGroupService = mock(GroupService.class);
		mockItemService = mock(ItemService.class);
		mockLockService = mock(LockService.class);
		mockConfidenceGradeService = mock(ConfidenceGradeService.class);
		mockUserService = mock(UserService.class);
		mockBranchDao = mock(BranchDao.class);

		user = new User();
		user.setName("barry");
		user.setId(23);
		
		dataService.setDataDao(mockDataDao);
		dataService.setReferenceService(mockReferenceService);
		dataService.setAuditService(mockAuditService);
		dataService.setLockService(mockLockService);
		dataService.setConfidenceGradeService(mockConfidenceGradeService);
		dataService.setItemService(mockItemService);
		dataService.setGroupService(mockGroupService);
		dataService.setBranchDao(mockBranchDao);
		
		System.out.println("TEST: Done");
}

//	public void testGetDataForPot() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetDataForPot");
//		System.out.println("TEST: DataServiceTest.testGetDataForPot()");
//		when(mockPot.getModel()).thenReturn(mockModel);
//		when(mockModel.getBranch()).thenReturn(null);
//		when(mockPot.getItem()).thenReturn(mockItem);
//		when(mockItem.getId()).thenReturn(1);
//		when(mockPot.getInterval()).thenReturn(mockPotInterval);
//		when(mockPotInterval.getId()).thenReturn(2);
//		when(mockCompany.getId()).thenReturn(3);
//		when(mockGroupEntry.getId()).thenReturn(4);
//		when(mockDataDao.getLatestData(1, 2, 3, 4)).thenReturn(mockData);
//		
//		when(mockData.getInterval()).thenReturn(mockDataInterval);
//		when(mockDataInterval.getId()).thenReturn(1);
//		when(mockReferenceService.getInterval(1)).thenReturn(mockYearFromRefService);
//		mockData.setInterval(mockYearFromRefService);
//		
//		Assert.assertEquals(mockData, dataService.getDataForPot(mockPot, mockCompany, mockGroupEntry, false));
//	
//		System.out.println("TEST: Done");
//}

//	public void testGetDataForPot_fromABranch() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetDataForPot_fromABranch");
//		when(mockPot.getModel()).thenReturn(mockModel);
//		when(mockCompany.getId()).thenReturn(3);
//		when(mockPot.getBranch(3)).thenReturn(mockBranch);
//		when(mockBranch.getId()).thenReturn(5);
//		when(mockBranch.getName()).thenReturn("A fine branch");
//		when(mockPot.getItem()).thenReturn(mockItem);
//		when(mockItem.getId()).thenReturn(1);
//		when(mockPot.getInterval()).thenReturn(mockPotInterval);
//		when(mockPotInterval.getId()).thenReturn(2);
//		when(mockCompany.getId()).thenReturn(3);
//		when(mockGroupEntry.getId()).thenReturn(4);
//		when(mockDataDao.getBranchData(1, 2, 3, 4, 5)).thenReturn(mockData);
//		
//		when(mockData.getInterval()).thenReturn(mockDataInterval);
//		when(mockDataInterval.getId()).thenReturn(1);
//		when(mockReferenceService.getInterval(1)).thenReturn(mockYearFromRefService);
//		mockData.setInterval(mockYearFromRefService);
//		
//		Assert.assertEquals(mockData, dataService.getDataForPot(mockPot, mockCompany, mockGroupEntry, false));
//	
//		System.out.println("TEST: Done");
//}

	public void testCreateData() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCreateData");
		System.out.println("TEST: DataServiceTest.testCreateData");
		String cg = "cg";
		when(mockPot.getItem()).thenReturn(mockItem);
		when(mockItem.getId()).thenReturn(1);
		when(mockPot.getInterval()).thenReturn(mockPotInterval);
		when(mockPotInterval.getId()).thenReturn(2);
		when(mockGroupEntry.getId()).thenReturn(4);
		ConfidenceGrade confidenceGrade = new ConfidenceGrade();
		confidenceGrade.setCode(cg);
		when(mockConfidenceGradeService.getConfidenceGrade(cg)).thenReturn(confidenceGrade);
		when(mockPot.getModel()).thenReturn(mockModel);
		when(mockModel.getBranch()).thenReturn(mockBranch);
				
		Data createdData = dataService.createData(mockPot, mockCompany, mockGroupEntry, "value", cg, false);
		
		Assert.assertEquals(mockPot.getItem(), createdData.getItem());
		Assert.assertEquals(mockPot.getInterval(), createdData.getInterval());
		Assert.assertEquals(mockCompany, createdData.getCompany());
		Assert.assertEquals("value", createdData.getValue());
		Assert.assertEquals(cg, createdData.getConfidenceGrade().getCode());
	
		System.out.println("TEST: Done");
}
	
//	public void testSaveDataChanges() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSaveDataChanges");
//		System.out.println("TEST: DataServiceTest.testSaveDataChanges");
//		String auditComment = "test audit comment";
//
//		Basket mockBasket = mock(Basket.class);
//
//		when(mockBasket.getAuditComment()).thenReturn(auditComment);
//		when(mockBasket.getUser()).thenReturn(user);
//		when(mockAuditService.createAudit(auditComment, user, mockCompany)).thenReturn(mockAudit);
//
//		List<UserEdit> editList = new ArrayList<UserEdit>();
//		UserEdit mockUserEdit1 = mock(UserEdit.class);
//		UserEdit mockUserEdit2 = mock(UserEdit.class);
//		editList.add(mockUserEdit1);
//		editList.add(mockUserEdit2);
//		when(mockBasket.getCachedEdits()).thenReturn(editList);
//		
//		// first edit
//		when(mockUserEdit1.getBranchId()).thenReturn(0);
//		when(mockBranchDao.findById(0)).thenReturn(null);
//
//		when(mockUserEdit1.getEditType()).thenReturn(EditType.VALUE);
//		when(mockUserEdit1.getValue()).thenReturn("1");
//		when(mockUserEdit1.getKey()).thenReturn("1-1-1-1-0");
//		ConfidenceGrade mockConfidenceGrade = mock(ConfidenceGrade.class); 
//		when(mockConfidenceGradeService.getConfidenceGrade("A1")).thenReturn(mockConfidenceGrade);
//		
//		when(mockReferenceService.getInterval(1)).thenReturn(mockDataInterval);
//		when(mockItemService.getItem(1)).thenReturn(mockItem);
//		when(mockReferenceService.getCompany(1)).thenReturn(mockCompany);
//		when(mockGroupService.getGroupEntry(1)).thenReturn(mockGroupEntry);
//		when(mockUserEdit1.getIntervalId()).thenReturn(1);
//		when(mockUserEdit1.getItemId()).thenReturn(1);
//		when(mockUserEdit1.getCompanyId()).thenReturn(1);
//		when(mockUserEdit1.getGroupEntryId()).thenReturn(1);
//
//		when(mockItem.getId()).thenReturn(1);
//		when(mockItem.getUnit()).thenReturn("nr");
//		when(mockDataInterval.getId()).thenReturn(1);
//		when(mockConfidenceGrade.getCode()).thenReturn("A1");
//		Data mockOldData = mock(Data.class);
//		when(mockOldData.getValue()).thenReturn("1.1");
//		when(mockOldData.getItem()).thenReturn(mockItem);
//		when(mockOldData.getId()).thenReturn(1);
//		
//		when(mockDataDao.getLatestData(1, 1, 1, 1)).thenReturn(mockOldData);
//
//		// second edit
//		when(mockUserEdit2.getBranchId()).thenReturn(0);
//		when(mockBranchDao.findById(0)).thenReturn(null);
//
//		when(mockUserEdit2.getEditType()).thenReturn(EditType.CONFIDENCE_GRADE);
//		when(mockUserEdit2.getValue()).thenReturn("2");
//		when(mockConfidenceGradeService.getConfidenceGrade("A1")).thenReturn(mockConfidenceGrade);
//		when(mockConfidenceGradeService.getConfidenceGrade(2)).thenReturn(mockConfidenceGrade);
//		when(mockUserEdit2.getKey()).thenReturn("1-1-1-1-1");
//		
//		when(mockUserEdit2.getIntervalId()).thenReturn(1);
//		when(mockUserEdit2.getItemId()).thenReturn(1);
//		when(mockUserEdit2.getCompanyId()).thenReturn(1);
//		when(mockUserEdit2.getGroupEntryId()).thenReturn(1);
//
//		try {
//			dataService.saveUserChanges(mockBasket);
//		} catch (BranchNotEditableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		verify(mockDataDao, times(2)).updateValue(eq(mockOldData), eq(mockAudit));
//		verify(mockLockService).clearLocksForUser(user.getId());
//	
//		System.out.println("TEST: Done");
//}
	

//	public void testSaveDataChangesWithNoOriginalValue() {
//		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSaveDataChangesWithNoOriginalValue");
//		System.out.println("TEST: DataServiceTest.testSaveDataChangesWithNoOriginalValue()");
//		String auditComment = "test audit comment";
//
//		Basket mockBasket = mock(Basket.class);
//
//		when(mockBasket.getAuditComment()).thenReturn(auditComment);
//		when(mockBasket.getUser()).thenReturn(user);
//		when(mockAuditService.createAudit(auditComment, user, mockCompany)).thenReturn(mockAudit);
//
//		List<UserEdit> editList = new ArrayList<UserEdit>();
//		UserEdit mockUserEdit1 = mock(UserEdit.class);
//		UserEdit mockUserEdit2 = mock(UserEdit.class);
//		editList.add(mockUserEdit1);
//		editList.add(mockUserEdit2);
//		when(mockBasket.getCachedEdits()).thenReturn(editList);
//		
//		// first edit
//		when(mockUserEdit1.getEditType()).thenReturn(EditType.VALUE);
//		when(mockUserEdit1.getValue()).thenReturn("1");
//		when(mockUserEdit1.getKey()).thenReturn("1-1-1-1-0");
//		ConfidenceGrade mockConfidenceGrade = mock(ConfidenceGrade.class); 
//		when(mockConfidenceGradeService.getConfidenceGrade("A1")).thenReturn(mockConfidenceGrade);
//		
//		when(mockReferenceService.getInterval(1)).thenReturn(mockDataInterval);
//		when(mockItemService.getItem(1)).thenReturn(mockItem);
//		when(mockReferenceService.getCompany(1)).thenReturn(mockCompany);
//		when(mockGroupService.getGroupEntry(1)).thenReturn(mockGroupEntry);
//		when(mockBranchDao.findById(0)).thenReturn(mockBranch);
//		when(mockBranch.isEditable()).thenReturn(true);
//		
//		when(mockUserEdit1.getIntervalId()).thenReturn(1);
//		when(mockUserEdit1.getItemId()).thenReturn(1);
//		when(mockUserEdit1.getCompanyId()).thenReturn(1);
//		when(mockUserEdit1.getGroupEntryId()).thenReturn(1);
//		when(mockUserEdit1.getBranchId()).thenReturn(0);
//
//		
//		when(mockItem.getId()).thenReturn(1);
//		when(mockItem.getUnit()).thenReturn("nr");
//		when(mockDataInterval.getId()).thenReturn(1);
//		when(mockConfidenceGrade.getCode()).thenReturn("A1");
//		Data mockOldData = mock(Data.class);
//		
//		when(mockDataDao.getLatestData(1, 1, 1, 1)).thenReturn(null);
//		when(mockDataDao.create(mockOldData, mockAudit)).thenReturn(1);
//
//		// second edit
//		when(mockUserEdit2.getEditType()).thenReturn(EditType.CONFIDENCE_GRADE);
//		when(mockUserEdit2.getValue()).thenReturn("2");
//		when(mockConfidenceGradeService.getConfidenceGrade("A1")).thenReturn(mockConfidenceGrade);
//		when(mockConfidenceGradeService.getConfidenceGrade(2)).thenReturn(mockConfidenceGrade);
//		when(mockUserEdit2.getKey()).thenReturn("2-2-2-2-2");
//		when(mockBranchDao.findById(0)).thenReturn(mockBranch);
//		when(mockBranch.isEditable()).thenReturn(true);
//		
//		when(mockUserEdit2.getIntervalId()).thenReturn(2);
//		when(mockUserEdit2.getItemId()).thenReturn(1);
//		when(mockUserEdit2.getCompanyId()).thenReturn(1);
//		when(mockUserEdit2.getGroupEntryId()).thenReturn(2);
//		when(mockUserEdit2.getBranchId()).thenReturn(0);
//
//		Data mockOldData2 = mock(Data.class);
//		when(mockOldData2.getValue()).thenReturn("1.1");
//		when(mockOldData2.getItem()).thenReturn(mockItem);
//		when(mockOldData2.getId()).thenReturn(1);
//		
//		when(mockDataDao.getLatestData(1, 2, 1, 2)).thenReturn(mockOldData2);
//
//		
//		try {
//			dataService.saveUserChanges(mockBasket);
//		} catch (BranchNotEditableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		verify(mockDataDao, times(1)).updateValue(eq(mockOldData2), eq(mockAudit));
//		verify(mockDataDao, times(1)).create(isA(Data.class), eq(mockAudit));
//		verify(mockLockService).clearLocksForUser(user.getId());
//		
//	
//		System.out.println("TEST: Done");
//}

	public void testSaveDataChanges_onBranch() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testSaveDataChanges");
		System.out.println("TEST: DataServiceTest.testSaveDataChanges");
		String auditComment = "test audit comment";

		Basket mockBasket = mock(Basket.class);

		when(mockBasket.getAuditComment()).thenReturn(auditComment);
		when(mockBasket.getUser()).thenReturn(user);
		when(mockAuditService.createAudit(auditComment, user, mockCompany)).thenReturn(mockAudit);

		List<UserEdit> editList = new ArrayList<UserEdit>();
		UserEdit mockUserEdit1 = mock(UserEdit.class);
		editList.add(mockUserEdit1);
		when(mockBasket.getCachedEdits()).thenReturn(editList);
		
		// first edit
		when(mockUserEdit1.getBranchId()).thenReturn(7);
		when(mockBranchDao.findById(7)).thenReturn(mockBranch);
		when(mockBranch.isEditable()).thenReturn(true);

		when(mockUserEdit1.getEditType()).thenReturn(EditType.VALUE);
		when(mockUserEdit1.getValue()).thenReturn("1");
		when(mockUserEdit1.getKey()).thenReturn("1-1-1-1-0");
		ConfidenceGrade mockConfidenceGrade = mock(ConfidenceGrade.class); 
		when(mockConfidenceGradeService.getConfidenceGrade("A1")).thenReturn(mockConfidenceGrade);
		
		when(mockReferenceService.getInterval(1)).thenReturn(mockDataInterval);
		when(mockItemService.getItem(1)).thenReturn(mockItem);
		when(mockReferenceService.getCompany(1)).thenReturn(mockCompany);
		when(mockGroupService.getGroupEntry(1)).thenReturn(mockGroupEntry);

		when(mockUserEdit1.getBranchId()).thenReturn(7);
		when(mockUserEdit1.getIntervalId()).thenReturn(1);
		when(mockUserEdit1.getItemId()).thenReturn(1);
		when(mockUserEdit1.getCompanyId()).thenReturn(1);
		when(mockUserEdit1.getGroupEntryId()).thenReturn(1);
		when(mockUserEdit1.getBranchId()).thenReturn(7);

		when(mockItem.getId()).thenReturn(1);
		when(mockItem.getUnit()).thenReturn("nr");
		when(mockDataInterval.getId()).thenReturn(1);
		when(mockConfidenceGrade.getCode()).thenReturn("A1");
		Data mockOldData = mock(Data.class);
		when(mockOldData.getValue()).thenReturn("1.1");
		when(mockOldData.getItem()).thenReturn(mockItem);
		when(mockOldData.getId()).thenReturn(1);
		
		when(mockDataDao.getBranchData(1, 1, 1, 1, 7)).thenReturn(mockOldData);

		try {
			dataService.saveUserChanges(mockBasket);
		} catch (BranchNotEditableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		verify(mockDataDao).updateValue(eq(mockOldData), eq(mockAudit));
		verify(mockLockService).clearLocksForUser(user.getId());
	
		System.out.println("TEST: Done");
}


}
