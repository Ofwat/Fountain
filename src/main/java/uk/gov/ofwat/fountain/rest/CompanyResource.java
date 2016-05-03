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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;

@Path("/company")
public class CompanyResource extends RestResource {

	private ReferenceService referenceService;
	private static Logger logger = LoggerFactory.getLogger(CompanyResource.class);
	
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}
	public CompanyResource(){
	}
	
   /* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.rest.RestResource#getModels()
	 */
	@GET
	@Path("/links")
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="Company-links")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getCompanyLinks(@Context UriInfo uriInfo, @Context SecurityContext securityContext){
		logger.debug("invoked putBasket");
		List<Link> links =  createLinks(referenceService.getCompanies(), "company/", uriInfo, securityContext);
		logger.debug("finished putBasket");
		GenericEntity<List<Link>> entity = new GenericEntity<List<Link>>(links){};
		return Response.ok(entity).build();
	}
   
   /* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.rest.RestResource#getModels()
	 */
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="Company-list")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getCompanies(){
		logger.debug("invoked getCompanies");
		List<Company> companies =  referenceService.getCurrentCompanies();
		logger.debug("finished getCompanies");
//		GenericEntity<List<Company>> entity = new GenericEntity<List<Company>>(companies){};
		GenericEntity<List<Company>> entity = null;
		return Response.ok(entity).build();
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="Company-types")
	@Path("/types")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	public Response getCompanyTypes(){
		logger.debug("invoked getCompanies");
		List<CompanyType> types = referenceService.getCompanyTypes();
		logger.debug("finished getCompanies");
		GenericEntity<List<CompanyType>> entity = new GenericEntity<List<CompanyType>>(types){};
		return Response.ok(entity).build();
	}

	
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="Company-list")
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
	@Path("/all")
	public Response getAllCompanies(){
		logger.debug("invoked getAllCompanies");
		List<Company> companies =  referenceService.getCompanies();
		logger.debug("finished getAllCompanies");
		GenericEntity<List<Company>> entity = new GenericEntity<List<Company>>(companies){};
		return Response.ok(entity).build();
	}

	@GET
   @Path("{id}")
   @Produces({"application/json"})
   @RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.USERS"})
   public Response getCompany(@PathParam("id") int id){
		logger.debug("invoked getCompany");
		Company company =  referenceService.getCompany(id);
		logger.debug("finished getCompany");
		return Response.ok(company).build();
	}

}
