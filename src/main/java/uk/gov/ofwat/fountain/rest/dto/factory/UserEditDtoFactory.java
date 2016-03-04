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

import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.rest.dto.IntervalDto;
import uk.gov.ofwat.fountain.rest.dto.ItemDto;
import uk.gov.ofwat.fountain.rest.dto.UserEditDto;

public class UserEditDtoFactory {

	ItemService itemService;
	ReferenceService referenceService;
	GroupService groupService;
	RunService runService;
	
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}


	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}


	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}


	public UserEditDto createUserEditDto(UserEdit userEdit) {
		UserEditDto userEditDto = new UserEditDto(); 
		userEditDto.setUser(userEdit.getUser());
		userEditDto.setItemDto(new ItemDto(itemService.getItem(userEdit.getItemId())));
		userEditDto.setIntervalDto(new IntervalDto(referenceService.getInterval(userEdit.getIntervalId())));
		userEditDto.setCompany(referenceService.getCompany(userEdit.getCompanyId()));
		userEditDto.setGroupEntry(groupService.getGroupEntry(userEdit.getGroupEntryId()));
		userEditDto.setValue(userEdit.getValue());
		userEditDto.setOriginal(userEdit.getOriginal());
		userEditDto.setEditType(userEdit.getEditType());

		Run run = runService.getRun((int)userEdit.getRunId());
		if (null != run) {
			// not all edits will be on a run.
			userEditDto.setRunName(run.getName());
			userEditDto.setRunId(run.getId());
		}
		
		Item item = itemService.getItem(userEdit.getItemId());
		scalePercentage(userEditDto, item);

		return userEditDto;
	}
	
	
   	private void scalePercentage(UserEditDto editDto, Item item) {
    	if (editDto.getEditType() == EditType.VALUE && 
    		item.getUnit().equals("%")) {
			try{
				double dValue = Double.parseDouble(editDto.getValue());
				editDto.setValue("" + dValue * 100); 
				double dOriginal = Double.parseDouble(editDto.getOriginal());
				editDto.setOriginal("" + dOriginal * 100);
			}
			catch(NumberFormatException nfe){
				// can't parse it so leave it as it was.
				// numeric validation needs to happen elsewhere.
			}
		}
    }


	public RunService getRunService() {
		return runService;
	}


	public void setRunService(RunService runService) {
		this.runService = runService;
	}


}
