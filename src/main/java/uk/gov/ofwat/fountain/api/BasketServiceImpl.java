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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import uk.gov.ofwat.fountain.dao.UserEditDao;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.DataTable;
import uk.gov.ofwat.fountain.domain.TableUploadMetaData;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class BasketServiceImpl implements BasketService {

	private Map<String, Basket> userCaches = new HashMap<String, Basket>();

	private UserEditDao userEditDao;
	private LockService lockService;
	
	
	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public BasketServiceImpl() {
		System.out.println("Creating BasketServiceImpl");
	}
	
	public void setUserEditDao(UserEditDao userEditDao) {
		this.userEditDao = userEditDao;
	}

	public Basket createBasket(User user) {
		Basket cache = new Basket(user, this);
		userCaches.put(cacheKey(user), cache);
		return cache;
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.BasketService#getCacheForUser(java.lang.String)
	 */
	public Basket getBasketForUser(User user) {
		Basket basket = userCaches.get(cacheKey(user)); 
		if (null == basket) {
			List<UserEdit> edits = userEditDao.findByUser(user);
			if (!edits.isEmpty()) {
				basket = createBasket(user);
				for(UserEdit edit: edits){
					basket.putEdit(edit);
				}
				userEditDao.refreshEditsForUser(user);
			}
		}
		return basket;
	}

	public void saveBasketForUser(Basket basket) {
		userEditDao.saveEdits(basket.getCachedEdits(), basket.getUser());
	}
	
	public void clearBasket( User user){
		userEditDao.removeUserEdits(user);
		userCaches.remove(cacheKey(user));
	}

	public void clearUserCache( User user){
		userCaches.remove(cacheKey(user));
	}

	private String cacheKey(User user) {
		return user.getName();
	}

	@Transactional
	public String saveEdit(UserEdit userEdit, User user) {
		Basket basket = getBasketForUser(user);
		if (null == basket) {
			basket = createBasket(user);
		}

		String key = null;
		if (null == basket.getEdit(userEdit.getKey()) ||
			!userEdit.equals(basket.getEdit(userEdit.getKey()))) {
			// Edit is not in the basket yet or it is in the cache but it has changed. Either way it needs to be cached!
			key = basket.putEdit(userEdit);
			saveBasketForUser(basket);
		}
		
		return key;
	}
	
	public void storeChangesToBasket(User user, DataTable dataTable, TableUploadMetaData metaData) {
		Map<String, DataDto> dataMap = dataTable.getDataList();
		for (DataDto dataDto: dataMap.values()) {
			if (null != dataDto.getUpdatedValue()) {
				storeChangeToBasket(EditType.VALUE, dataDto, user, metaData); 
			}
			if (null != dataDto.getUpdatedConfidenceGrade() &&
				!dataDto.getUpdatedConfidenceGrade().isEmpty()) {
				storeChangeToBasket(EditType.CONFIDENCE_GRADE, dataDto, user, metaData); 
			}
		}
	}

	private void storeChangeToBasket(EditType editType, DataDto dataDto, User user, TableUploadMetaData metaData) {
		String message = "Item: " + dataDto.getItem().getCode() + " Company: " + dataDto.getCompany().getName() + " Interval: " + dataDto.getIntervalDto().getName();

		if (!dataDto.isEditable()) {
			metaData.getErrors().add(message + " is not editable by " + user.getName());
			return;
		}

		if (!user.getName().equals(dataDto.getLockingUser())) {
			metaData.getErrors().add(message + " is locked for editing by " + dataDto.getLockingUser());
			return;
		}

		if (dataDto.getValue() == null) {
			metaData.getErrors().add(message + " value is null. updatedValue is " + dataDto.getUpdatedValue());
		}
		
		UserEdit userEdit = new UserEdit(user, dataDto, editType);
		if(null == saveEdit(userEdit, user)){
			if (EditType.VALUE == editType) {
				metaData.addWarning(message + " Value: " + dataDto.getValue() + " has already been changed during this import.");
			}
			else {
				metaData.addWarning(message + " Confidence Grade: " + dataDto.getConfidenceGrade() + " has already been changed during this import.");
			}
			return;
		}

		lockService.lockForEdit(userEdit, user);
	}

	public List<UserEdit> getEditsForBranch(Branch branch) {
		return userEditDao.findByBranchId(branch.getId());
	}
	
}
