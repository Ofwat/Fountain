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

package uk.gov.ofwat.fountain.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.GroupDto;
import uk.gov.ofwat.fountain.rest.dto.GroupEntryDto;

@Path("/group")
public class GroupResource extends RestResource{
	
	
	private GroupService groupService;
	
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}



	@GET
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getGroups(){
		List<Group> groups = groupService.getGroups();
		List<GroupDto> dtos = new ArrayList<GroupDto>();
		for(Group group: groups){
			dtos.add(new GroupDto(group));
		}
		GenericEntity<List<GroupDto>> entity = new GenericEntity<List<GroupDto>>(dtos){};
		return Response.ok(entity).build();		
	}
	
	@GET
	@Path("/{groupId}/company/{companyId}")
	@Produces({"application/xml", "application/json"})
	
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getCompanyGroupEntries(@PathParam("companyId") int companyId, @PathParam("groupId")int groupId){
		List<GroupEntry> entries = groupService.getGroupEntriesForCompany(companyId, groupId);
		List<GroupEntryDto> dtos = new ArrayList<GroupEntryDto>();
		for(GroupEntry entry: entries){
			dtos.add(new GroupEntryDto(entry));
		}
		GenericEntity<List<GroupEntryDto>> entity = new GenericEntity<List<GroupEntryDto>>(dtos){};
		return Response.ok(entity).build();	
	}
	
	@POST
	@Path("/group-entry/")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.EDITORS"})
	public Response updateGroupName(@BadgerFish GroupEntryDto groupEntryDto){
		//
		groupService.setGroupEntryDescription(groupEntryDto.getId(), groupEntryDto.getDescription());
		return null;
	}

}
