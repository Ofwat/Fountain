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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.annotations.providers.jaxb.json.BadgerFish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.BranchNotEditableException;
import uk.gov.ofwat.fountain.api.DataService;
import uk.gov.ofwat.fountain.api.LockService;
import uk.gov.ofwat.fountain.api.BasketService;
import uk.gov.ofwat.fountain.api.UserService;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.Basket;

@Path("/checkout")
public class CheckoutResource extends RestResource{
	private LockService lockService;
	private UserService userService;
	private DataService dataService;
	private BasketService basketService;
	
	private static Logger logger = LoggerFactory.getLogger(CheckoutResource.class);
	public static String WEB_AUDIT_PREFIX = "WEB:";
	
	public void setLockService(LockService lockService) {
		this.lockService = lockService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	public void setBasketService(BasketService basketService) {
		this.basketService = basketService;
	}

	public CheckoutResource(){	
	
	}
	
	@POST
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Editors"})
	public Response postBasketToCheckout(@BadgerFish String auditComment,
							@Context SecurityContext securityContext){
		logger.debug("invoked postBasketToCheckout");
		ResponseBuilder responseBuilder = null;

		if (null == auditComment) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		} 
		else {
			// retrieve and save the basket 
			User user = userService.getUser(securityContext);
			Basket basket = basketService.getBasketForUser(user);
			if (null == basket) {
				throw new WebApplicationException(Response.Status.CONFLICT);
			}

			// Use the audit comment from the basket
			basket.setAuditComment(WEB_AUDIT_PREFIX + removeSuroundingQuotes(auditComment));
			List<Audit> audits = null;
			try {
				audits = dataService.saveUserChanges(basket);
			} catch (BranchNotEditableException e) {
				throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("BRANCH_NOT_EDITABLE").build());
			}

			// delete basket
			lockService.clearLocksForUser(user.getId());
			basketService.clearBasket(user);
			responseBuilder = Response.noContent().header("audits", audits);
		}
		logger.debug("finished postBasketToCheckout");
		return responseBuilder.build();
	}

	private String removeSuroundingQuotes(String auditComment) {
		if (auditComment.startsWith("\"") &&
			auditComment.endsWith("\"")) {
			auditComment = auditComment.substring(auditComment.indexOf("\"")+1, auditComment.lastIndexOf("\""));
		}
		return auditComment;
	}

}
