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

import java.net.URI;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CannotAcquireLockException;

import uk.gov.ofwat.fountain.api.LockService;
import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.UserEdit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.factory.BasketDtoFactory;
import uk.gov.ofwat.fountain.rest.dto.factory.UserEditDtoFactory;

@Path("/basket")
public class BasketResource extends RestResource{
	private LockService lockService;
	private UserService userService;
	private BasketService basketService;
	private UserEditDtoFactory userEditDtoFactory;
	private BasketDtoFactory basketDtoFactory;
	
	private static Logger logger = LoggerFactory.getLogger(BasketResource.class);
	private static String SERVICE_UNAVAILABLE_MESSAGE = "This service is temporarily unavailable due to load on the system. Please try again later.";
    

	
	public BasketResource(){	
	}
	
	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}
	public UserEditDtoFactory getUserEditDtoFactory() {
		return userEditDtoFactory;
	}
	public void setUserEditDtoFactory(UserEditDtoFactory userEditDtoFactory) {
		this.userEditDtoFactory = userEditDtoFactory;
	}
	public void setBasketDtoFactory(BasketDtoFactory basketDtoFactory) {
		this.basketDtoFactory = basketDtoFactory;
	}

	// create basket
	@PUT
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response putBasket(	@Context SecurityContext securityContext){
		logger.debug("invoked putBasket");
		
		User user = userService.getUser(securityContext);
		Basket cache = basketService.getBasketForUser(user);

		if (null != cache) {
			// There is already a basket so we can't create a new one.
			throw new WebApplicationException(Response.Status.CONFLICT);
		}
		cache = basketService.createBasket(user);
		ResponseBuilder responseBuilder = Response.status(Status.CREATED);
			
		logger.debug("finished putBasket");
		return responseBuilder.build();
	}

	@GET
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getBasket(@Context SecurityContext securityContext) {
		logger.debug("invoked getBasket");

		User user = userService.getUser(securityContext);
		Basket cache = basketService.getBasketForUser(user);

		ResponseBuilder responseBuilder = null;
		if (null == cache) {
			responseBuilder = Response.status(Response.Status.NOT_FOUND);
		} 
		else {
			responseBuilder = Response.ok(basketDtoFactory.createBasketDto(cache));
		}
		logger.debug("finished getBasket");
		return responseBuilder.build();
	}
	
	@DELETE
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response deleteBasket(@Context SecurityContext securityContext) {
		logger.debug("invoked deleteBasket");

		User user = userService.getUser(securityContext);
		lockService.clearLocksForUser(user.getId());
		basketService.clearBasket(user);

		ResponseBuilder responseBuilder = Response.noContent();
		logger.debug("finished deleteBasket");
		return responseBuilder.build();
	}

	
	@GET
	@Path("/edits/{id}")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response getValueEdit(@PathParam("id") String id, 
								@Context SecurityContext securityContext, @Context UriInfo uriInfo){
		logger.debug("invoked getValueEdit");

		User user = userService.getUser(securityContext);
		Basket cache = basketService.getBasketForUser(user);
		ResponseBuilder responseBuilder = null;
		if (null == cache) {
			responseBuilder = Response.status(Response.Status.NOT_FOUND);
			return responseBuilder.build();
		}

		UserEdit edit = cache.getEdit(id);
		if (null == edit) {
			responseBuilder = Response.status(Response.Status.NOT_FOUND);
			return responseBuilder.build();
		}
		
		responseBuilder = Response.ok(userEditDtoFactory.createUserEditDto(edit));
		logger.debug("finished getValueEdit");
		return responseBuilder.build();
	}

	@DELETE
	@Path("/edits/{id}")
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response deleteEdit(	@PathParam("id") String id,
							@Context SecurityContext securityContext){
		logger.debug("invoked deleteEdit");

		User user = userService.getUser(securityContext);
		Basket cache = basketService.getBasketForUser(user);

		if (null != cache) {
			cache.removeEdit(id);
		}

		ResponseBuilder responseBuilder = Response.noContent();
		logger.debug("finished deleteEdit");
		return responseBuilder.build();
	}

	@POST
	@Path("/edits")
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response addEdit(@BadgerFish DataDto dataDto,
			                @Context SecurityContext securityContext, @Context UriInfo uriInfo){
		logger.debug("invoked addEdit");

		User user = userService.getUser(securityContext);
		
		//TODO - don't trust the client dto
		if (!(securityContext.isUserInRole(User.FOUNTAIN_ADMIN_ROLE) || (securityContext.isUserInRole(dataDto.getItem().getTeam().getCode())))) {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
		//TODO - don't trust the client dto
		if (!user.getName().equals(dataDto.getLockingUser())) {
			throw new WebApplicationException(Response.Status.FORBIDDEN);
		}
		
		if (null == basketService.getBasketForUser(user)) {
			throw new WebApplicationException(Response.Status.GONE);
		} 
		
		String editKey = null;
		String valueEditKey = null;
		if (null != dataDto.getUpdatedValue()) {
			// got a value
			UserEdit userEdit = new UserEdit(user, dataDto, EditType.VALUE);
			try {
				valueEditKey = basketService.saveEdit(userEdit, user);
			} 
			catch (CannotAcquireLockException cale){
				// Clear the cache here. The client will inform user, then reload the page. This keeps DB, cache and client all in sync.  
				basketService.clearUserCache(user);
				throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
			}
			if(null != valueEditKey){
				editKey = valueEditKey;
				lockService.lockForEdit(userEdit, user);
			}
		}
		String cgEditKey = null;
		if (null != dataDto.getUpdatedConfidenceGrade()) {
			// got a cg
			UserEdit userEdit = new UserEdit(user, dataDto, EditType.CONFIDENCE_GRADE);
			try {
				cgEditKey = basketService.saveEdit(userEdit, user);
			} 
			catch (CannotAcquireLockException cale){
				// Clear the cache here. The client will inform user, then reload the page. This keeps DB, cache and client all in sync.  
				basketService.clearUserCache(user);
				throw new WebApplicationException(Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(SERVICE_UNAVAILABLE_MESSAGE).build());
			}
			if(null != cgEditKey){
				editKey = cgEditKey;
				lockService.lockForEdit(userEdit, user);
			}
		}
		if (null != valueEditKey &&
			null != cgEditKey) {
			throw new WebApplicationException(Response.Status.CONFLICT);
		}

		URI uri = null;
		if (null != editKey) {
			uri = URI.create(uriInfo.getBaseUri().toASCIIString() + "basket/edits/" + editKey);
		}

		if (uri == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}

		ResponseBuilder responseBuilder = Response.created(uri);
		logger.debug("finished addEdit");
		return responseBuilder.build();
	}

}
