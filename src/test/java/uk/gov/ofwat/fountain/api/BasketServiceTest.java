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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.UserEditDao;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;

public class BasketServiceTest extends TestCase {

	private BasketServiceImpl basketService;
	private UserEditDao mockUserEditDao;
	private Basket mockCache;
	private User mockUser;

	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":setUp");
		basketService = new BasketServiceImpl();
		mockUserEditDao = mock(UserEditDao.class);
		mockCache = mock(Basket.class);
		mockUser = mock(User.class);

		System.out.println("TEST: Done");
	}

	public void testGetCacheForUser_NoEdits() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testGetCacheForUser_NoEdits");
		List<UserEdit> userEdits = new ArrayList<UserEdit>();
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(userEdits);

		basketService.setUserEditDao(mockUserEditDao);
		Basket cache = basketService.getBasketForUser(mockUser);

		Assert.assertNull(cache);

		System.out.println("TEST: Done");
	}

	public void testGetCacheForUser_WithEdits() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testGetCacheForUser_WithEdits");
		List<UserEdit> userEdits = new ArrayList<UserEdit>();
		UserEdit edit = new UserEdit(mockUser, 1, 1, 1, 1, 0, "1", "0",
				EditType.VALUE);
		userEdits.add(edit);

		basketService.setUserEditDao(mockUserEditDao);
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(userEdits);

		Basket cache = basketService.getBasketForUser(mockUser);

		Assert.assertEquals(cache.getUser(), mockUser);
		Assert.assertFalse(cache.isEmpty());
		Assert.assertNotNull(cache.getEdit("i1-pnull-y1-c1-g1-mnull-b0"));

		verify(mockUserEditDao).refreshEditsForUser(mockUser);

		System.out.println("TEST: Done");
	}
	public void testSaveEditCacheForUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSaveEditCacheForUser");
		UserEdit mockUserEdit = mock(UserEdit.class);
		List<UserEdit> editList = new ArrayList<UserEdit>();
		editList.add(mockUserEdit);
		when(mockCache.getCachedEdits()).thenReturn(editList);
		when(mockCache.getUser()).thenReturn(mockUser);

		basketService.setUserEditDao(mockUserEditDao);
		basketService.saveBasketForUser(mockCache);

		verify(mockUserEditDao).saveEdits(editList, mockUser);

		System.out.println("TEST: Done");
	}

	public void testClearUserCache() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testClearUserCache");
		basketService.setUserEditDao(mockUserEditDao);
		basketService.clearBasket(mockUser);

		verify(mockUserEditDao).removeUserEdits(mockUser);

		System.out.println("TEST: Done");
	}

	public void testSaveEdit_editNotInCache() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSaveEdit_editNotInCache");
		UserEdit edit = new UserEdit(mockUser, 1, 2, 3, 5, 0, "1", "0",
				EditType.VALUE);
		List<UserEdit> editList = new ArrayList<UserEdit>();
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(editList);

		basketService.setUserEditDao(mockUserEditDao);
		String savedEditKey = basketService.saveEdit(edit, mockUser);
		Assert.assertEquals(edit.getKey(), savedEditKey);

		System.out.println("TEST: Done");
	}

	public void testSaveEdit_editInCacheDoesNotMatch() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSaveEdit_editInCacheDoesNotMatch");
		UserEdit edit = new UserEdit(mockUser, 1, 2, 3, 5, 0, "1", "0",
				EditType.VALUE);
		UserEdit editForCacheOnly = new UserEdit(mockUser, 1, 2, 3, 5, 0, "1",
				"0", EditType.CONFIDENCE_GRADE);
		List<UserEdit> editList = new ArrayList<UserEdit>();
		editList.add(editForCacheOnly);
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(editList);

		basketService.setUserEditDao(mockUserEditDao);
		String savedEditKey = basketService.saveEdit(edit, mockUser);
		Assert.assertEquals(edit.getKey(), savedEditKey);

		System.out.println("TEST: Done");
	}

	public void testSaveEdit_editInCacheMatches() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSaveEdit_editInCacheMatches");
		UserEdit edit = new UserEdit(mockUser, 1, 2, 3, 5, 0, "1", "0",
				EditType.VALUE);
		List<UserEdit> editList = new ArrayList<UserEdit>();
		editList.add(edit);
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(editList);

		basketService.setUserEditDao(mockUserEditDao);
		String savedEditKey = basketService.saveEdit(edit, mockUser);
		Assert.assertEquals(null, savedEditKey); // not saved

		System.out.println("TEST: Done");
	}
	
	public void testStoreChangesToBasket() {
//		storeChangesToBasket(mockUser, DataTable dataTable, TableUploadMetaData metaData) {
		//TODO run AOP to record state of DataTables them we can test it 
	}

	public void testSaveEdits_differingOnlyByBranch() {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":testSaveEdit_editInCacheMatches");
		UserEdit edit = new UserEdit(mockUser, 1, 2, 3, 5, 0, "1", "0", EditType.VALUE);
		List<UserEdit> editList = new ArrayList<UserEdit>();
		when(mockUserEditDao.findByUser(mockUser)).thenReturn(editList);

		basketService.setUserEditDao(mockUserEditDao);
		String savedEditKey = basketService.saveEdit(edit, mockUser);
		Assert.assertEquals(edit.getKey(), savedEditKey);

		UserEdit btEdit = new UserEdit(mockUser, 1, 2, 3, 5, 14, "1", "0", EditType.VALUE);
		String btSavedEditKey = basketService.saveEdit(btEdit, mockUser);

		Assert.assertEquals(btEdit.getKey(), btSavedEditKey);
		Assert.assertFalse(savedEditKey.equals(btSavedEditKey));
		
		Basket basket = basketService.getBasketForUser(mockUser);
		Assert.assertEquals(2, basket.getCachedEdits().size());
		
		System.out.println("TEST: Done");
	}
}
