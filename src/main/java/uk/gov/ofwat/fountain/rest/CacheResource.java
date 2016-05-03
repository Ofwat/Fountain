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

import java.io.IOException;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import uk.gov.ofwat.fountain.api.DaoCacheService;
import uk.gov.ofwat.fountain.domain.CacheLevel;
import clover.org.jfree.util.Log;


/**
 * Rest resource for admin cache manipulation
 */
@Path("/cache")
public class CacheResource extends RestResource{

	private DaoCacheService daoCacheService;
	
	public void setDaoCacheService(DaoCacheService daoCacheService) {
		this.daoCacheService = daoCacheService;
	}



	/**
	 * Write dao cache levels to the log
	 * @param userId
	 * @return
	 */
	@Path("levels")
	@GET
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response reportCacheLevels(){
		GenericEntity<List<CacheLevel>> entity = new GenericEntity<List<CacheLevel>>(daoCacheService.reportCacheLevels()){};
		return Response.ok(entity).build();
	}

	@Path("persist")
	@PUT
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response cacheToDisk(){
		try {
			daoCacheService.writeToDisk();
		} catch (IOException e) {
			Log.error("Could not clean cache directory. Check that it is in the right place and is writable.");
		}
		return Response.ok().build();
	}
	
	@Path("hydrate")
	@PUT
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response cacheFromDisk(){
		daoCacheService.readFromDisk();
		return Response.ok().build();
	}
	
	@Path("clear")
	@PUT
	@RolesAllowed(value={"ROLE_OFWAT\\FOUNTAIN.ADMINS"})
	public Response clearCaches(){
		daoCacheService.clearCaches();
		return Response.ok().build();
	}
	
}
