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
package uk.gov.ofwat.fountain.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.rest.dto.BasketDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;

/**
 * Class to take the place of an input model for the duration of a client's
 * editing session.
 * 
 * All calculated items will be resolved and their values will be available
 * instantly. Only calculations within the cells on the page will be
 * re-calculated. This will be a repository for the results of calculations
 * deemed 'external' to the edit set.
 * 
 * The initial cache is a simple map with no expiry or other functionality.
 */
public class Basket {

	private Map<String, UserEdit> cachedEdits = new HashMap<String, UserEdit>();
	private User user;
	private BasketService basketService;
	private String auditComment;

	public Basket(User user, BasketService basketService) {
		setUser(user);
		setBasketService(basketService);
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public BasketService getBasketService() {
		return basketService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserEdit getEdit(String key) {
		return cachedEdits.get(key);
	}

	public String putEdit(UserEdit edit) {
		cachedEdits.put(edit.getKey(), edit);
		return edit.getKey();
	}

	public void removeEdit(String key) {
		cachedEdits.remove(key);
	}

	public List<UserEdit> getCachedEdits() {
		return new ArrayList<UserEdit>(cachedEdits.values());
	}

	public boolean isEmpty() {
		return cachedEdits.isEmpty();
	}

}
