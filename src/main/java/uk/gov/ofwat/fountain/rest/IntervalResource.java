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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;
import uk.gov.ofwat.fountain.rest.dto.IntervalDto;
import uk.gov.ofwat.fountain.rest.dto.IntervalTypeDto;

@Path("/interval")
public class IntervalResource extends RestResource{
	
	private ReferenceService referenceService;
	private static Logger logger = LoggerFactory.getLogger(IntervalResource.class);
	public void setReferenceService(ReferenceService referenceService) {
		this.referenceService = referenceService;
	}

	public IntervalResource(){
	}
	
	@GET
	@Produces({"application/xml", "application/json"})
	@Wrapped(element="intervals")
	@RolesAllowed(value={"OFWAT\\Fountain.Users"})
	public Response getIntervals(@QueryParam("filters") String filters){
		logger.debug("started getIntervals");
		List<IntervalTypeDto>dtos = new ArrayList<IntervalTypeDto>();
		Map<IntervalType, IntervalTypeDto> intervalTypes = new HashMap<IntervalType, IntervalTypeDto>();
		
		// if there are filters, use the filtered method
		List<Interval> intervals = null;
		if(null == filters || "".equals(filters)){
			intervals = referenceService.getIntervals();
		}
		else{
			String[] intervalFilters = filters.split(";");
			int[] ids = new int[intervalFilters.length];
			for(int i = 0; i < intervalFilters.length; i++){
				ids[i] = Integer.parseInt(intervalFilters[i]);
			}
			intervals = referenceService.getIntervals(ids);
		}
//		iterate through the intervals and put them in a list of intervalTypeDtos, each with 
//		it's list of intervals
		for(Interval interval: intervals){
			IntervalType type = interval.getIntervalType();
			if(intervalTypes.keySet().contains(type)){
				// add the interval dto to the approporiate intervalTypeDto
				intervalTypes.get(type).addIntervalDto(new IntervalDto(interval));
			}
			else{
				// new interval type
				IntervalTypeDto typeDto = new IntervalTypeDto(type);
				typeDto.addIntervalDto(new IntervalDto(interval));
				intervalTypes.put(type, typeDto);
			}			
		}
		dtos.addAll(intervalTypes.values());
		Collections.sort(dtos);
		logger.debug("finished getIntervals");
		GenericEntity<List<IntervalTypeDto>> entity = new GenericEntity<List<IntervalTypeDto>>(dtos){};
		return Response.ok(entity).build();			
	}


}
