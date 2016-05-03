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

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.TeamService;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.rest.security.RestServiceRoleChecker;

@Path("/team")
public class TeamResource {

	private static Logger logger = LoggerFactory.getLogger(TeamResource.class);

	private TeamService teamService;
	
	public void setTeamService(TeamService teamService){
		this.teamService = teamService;
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="teams")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTeams(){
		GenericEntity<List<Team>> entity = new GenericEntity<List<Team>>(teamService.getAllTeams()){};
		return Response.ok(entity).build();
	}

	@GET
	@Path("/user")
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="teams")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getTeamsForUser(@Context SecurityContext securityContext){
		RoleChecker roleChecker = new RestServiceRoleChecker(securityContext);
		final GenericEntity<List<Team>> entity = new GenericEntity<List<Team>>(teamService.findTeamsForUser(roleChecker)){};
		return Response.ok(entity).build();
	}

}
