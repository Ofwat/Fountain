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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import uk.gov.ofwat.fountain.domain.Addressable;
import uk.gov.ofwat.fountain.domain.Restrictable;
import uk.gov.ofwat.fountain.rest.dto.Linkable;


@RolesAllowed({"admin", "roleA"})
public abstract class RestResource {


	protected List<Link> createLinks(Collection<?> resources, String resourcePath, UriInfo uriInfo, SecurityContext securityContext) {
		return createLinks(resources, resourcePath, "", uriInfo, securityContext);
	}

	protected List<Link> createLinks(Collection<?> resources, String resourcePathBeforeId, String resourcePathAfterId, UriInfo uriInfo, SecurityContext securityContext) {
		List<Link> links = new ArrayList<Link>();
		Iterator<?> iterator = resources.iterator();
		while(iterator.hasNext()){
			Object object = (Object)iterator.next();
			if(object instanceof Restrictable){
				Restrictable restrictable = (Restrictable)object;
				if (!isViewable(restrictable, securityContext)) {
					continue;
				}
			}
			if (!(object instanceof Addressable)) {
				continue;
			}
			Link link = createLink((Addressable)object,resourcePathBeforeId, resourcePathAfterId, uriInfo, securityContext);
			
			links.add(link);
		}
		return links;
	}

	/**
	 * returns null if object is not viewable
	 * @param resource
	 * @param resourcePathBeforeId
	 * @param resourcePathAfterId
	 * @return
	 */
	protected Link createLink(Addressable resource, String resourcePathBeforeId, String resourcePathAfterId, UriInfo uriInfo, SecurityContext securityContext){
		if(resource instanceof Restrictable){
			Restrictable restrictable = (Restrictable)resource;
			if (!isViewable(restrictable, securityContext)) {
				return null;
			}
		}
		Addressable addressable = (Addressable)resource;
		Link link = new Link();
		link.setName(addressable.getName());
		link.setUri(URI.create(uriInfo.getBaseUri().toASCIIString() + resourcePathBeforeId + addressable.getId()).toASCIIString() + resourcePathAfterId);
		link.setDescription(resource.getDescription());
		return link;
	}



	protected void setLinkOnResource(Addressable addressable, Linkable linkable, String resourcePath, UriInfo uriInfo){
		Link link = new Link();
		link.setName(addressable.getName());
		link.setUri(URI.create(uriInfo.getBaseUri().toASCIIString() + resourcePath + addressable.getId()).toASCIIString());
		linkable.setLink(link);   
	}


	private boolean isViewable(Restrictable restrictable, SecurityContext securityContext) {
		if (null == securityContext.getUserPrincipal()) {
			return true; // not using security
		}

		if (restrictable.getRoles() == null) {
			return true; // default to viewable
		}

		for (String role : restrictable.getRoles()) {
			if (securityContext.isUserInRole(role)) {
				return true;
			}
		}
		return false;
	}

}

