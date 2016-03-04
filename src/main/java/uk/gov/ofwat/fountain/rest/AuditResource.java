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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.AuditService;
import uk.gov.ofwat.fountain.domain.AuditedValue;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.rest.dto.AuditedValueDto;

@Path("/audits")
public class AuditResource extends RestResource{
	private AuditService auditService;
	
	private static Logger logger = LoggerFactory.getLogger(AuditResource.class);
    	
	public AuditResource(){	
	}
	
	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	@GET
	@Path("/{dataKey}")
	@Produces({"application/json"})
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getAuditedValues(@PathParam("dataKey") String key) {

		DataKey dataKey = new DataKey(key);
		List<AuditedValue> avs;

		if (null == dataKey.getRunId()) {
			avs = auditService.getAuditedValues(dataKey.getItemIdInteger(), dataKey.getIntervalIdInteger(), dataKey.getCompanyIdInteger(), dataKey.getGroupEntryIdInteger());
		}
		else {
			avs = auditService.getAuditedValues(dataKey.getItemIdInteger(), dataKey.getIntervalIdInteger(), dataKey.getCompanyIdInteger(), dataKey.getGroupEntryIdInteger(), dataKey.getRunIdInteger(), dataKey.getTagIdInteger());
		}
		
		List<AuditedValueDto> dtos = new ArrayList<AuditedValueDto>();
		for(AuditedValue av: avs){
			dtos.add(new AuditedValueDto(av));
		}
		logger.debug("finished getAuditedValues");
		ResponseBuilder responseBuilder = Response.ok(dtos);
		return responseBuilder.build();
	}


}
