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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.api.LockService;
import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.CacheLevel;
import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;


/**
 * Rest resource for admin specific features like clearing locks
 */
@Path("/lock")
public class LockResource extends RestResource{

	private LockService lockService;
	private BasketService basketService;
	private UserService userService;
	
	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}
	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 
	 * @return - a list of all users that currently have locks within the system
	 */
	@Path("users")
	@GET
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Admins"})
	public Response getLockingUsers(){
		GenericEntity<List<User>> entity = new GenericEntity<List<User>>(lockService.getLockingUsers()){};
		return Response.ok(entity).build();			
	}
	

	/**
	 * Put a list of locks for a user
	 */
	@Path("{userId}")
	@PUT
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response addLocks(@BadgerFish List<Lock> locks,
         					@Context SecurityContext securityContext){

		lockService.refreshLocks(locks);
		
		ResponseBuilder responseBuilder = Response.ok();
		return responseBuilder.build();
	}
	
	

	/**
	 * Delete the locks and pending edits for the given user 
	 * @param userId
	 * @return
	 */
	@Path("{userId}")
	@DELETE
	@RolesAllowed(value={"OFWAT\\Fountain.Admins"})
	public Response removeLocksForUser(@PathParam("userId") int userId){
		User user = userService.getUser(userId);
		lockService.clearLocksForUser(user.getId());
		basketService.clearBasket(user);
		ResponseBuilder responseBuilder = Response.noContent();
		return responseBuilder.build();
	}
	
}
