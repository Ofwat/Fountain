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

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/wikiAddress")
public class WikiAddressResource extends RestResource {

	private String wikiAddress;
	private static Logger logger = LoggerFactory.getLogger(WikiAddressResource.class);
	
	public WikiAddressResource(){
	}
	
	public void setWikiAddress(String wikiAddress) {
		this.wikiAddress = wikiAddress;
	}
	public String getWikiAddress() {
		return wikiAddress;
	}

	@GET
	@Produces({"application/xml", "application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response wikiAddress(){
		logger.debug("invoked wikiAddress");
		Link link = new Link();
		link.setName("Wiki address");
		link.setUri(wikiAddress);
		ResponseBuilder responseBuilder = Response.ok(link);
		logger.debug("finished wikiAddress");
		return responseBuilder.build();
	}
}
