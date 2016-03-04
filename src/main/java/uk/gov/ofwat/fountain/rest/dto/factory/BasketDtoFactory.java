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
package uk.gov.ofwat.fountain.rest.dto.factory;

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.rest.dto.BasketDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;

public class BasketDtoFactory {

	UserEditDtoFactory userEditDtoFactory;
	
	public void setUserEditDtoFactory(UserEditDtoFactory userEditDtoFactory) {
		this.userEditDtoFactory = userEditDtoFactory;
	}

	public BasketDto createBasketDto(Basket cache) {
		BasketDto basketDto = new BasketDto();
		if (null == cache.getAuditComment()) {
			basketDto.setAudit("");
		} 
		else {
			basketDto.setAudit(cache.getAuditComment());
		}
		
		List<UserEditDto> edits = new ArrayList<UserEditDto>();
		for (UserEdit basketEdit : cache.getCachedEdits()) {
			edits.add(userEditDtoFactory.createUserEditDto(basketEdit));
		}
		basketDto.setEdits(edits);
		
		return basketDto;
	}
	
}
