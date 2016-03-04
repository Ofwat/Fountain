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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.BranchNotEditableException;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.domain.Agenda;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.run.Run;

@Path("/agenda")
public class AgendaResource extends RestResource {

	private RunService runService;
	private static Logger logger = LoggerFactory.getLogger(AgendaResource.class);
	
	public void setRunService(RunService runService) {
		this.runService = runService;
	}
	
	public AgendaResource(){
	}
	
	@GET
	@Produces({"application/json"})
	@Wrapped(element="Agenda-list")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getAllAgenda(){
		logger.debug("invoked getAllAgenda");
		List<Agenda> agenda =  runService.getAllAgenda();
		GenericEntity<List<Agenda>> entity = new GenericEntity<List<Agenda>>(agenda){};
		logger.debug("finished getAllAgenda");
		return Response.ok(entity).build();
	}

	@POST
	@Path("/{agendaName}")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@RolesAllowed(value={"OFWAT\\G Fountain Run Admin"})
	public Response createRun(@PathParam("agendaName") String agendaName,
			@QueryParam("agendaCode") String agendaCode,
			@QueryParam("agendaDescription") String agendaDescription,
			@Context SecurityContext securityContext) {
		
		ResponseBuilder responseBuilder;
		if (agendaName.length() > 50) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The name of this agenda is too long. Please limit to 50 characters.";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		if (agendaCode.length() > 20) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The code of this agenda is too long. Please limit to 20 characters.";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		if (!agendaCode.matches("^[a-zA-Z0-9_]+$")) { // alpha numeric with underscores 
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The code of this agenda has invalid characters. Please use only alpha/numeric and underscores";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		// is code unique?
		if (null != runService.getAgenda(agendaCode)) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The code of this agenda is already in use. Please use a unique code";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		if (agendaDescription.length() > 500) {
			responseBuilder = Response.status(Response.Status.CONFLICT);
			String errorMessage = "The description of this agenda is too long. Please limit to 500 characters.";
			responseBuilder.entity(errorMessage);
			logger.error(errorMessage);
			return responseBuilder.build();
		}
		
		Agenda agenda = runService.createAgenda(agendaName, agendaCode, agendaDescription);
		responseBuilder = Response.ok(agenda);
		return responseBuilder.build();
	}

}
