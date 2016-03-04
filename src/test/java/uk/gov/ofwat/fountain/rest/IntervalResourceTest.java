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

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.rest.dto.IntervalDto;
import uk.gov.ofwat.fountain.rest.dto.IntervalTypeDto;
import uk.gov.ofwat.fountain.util.Locations;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({})
@ContextConfiguration(locations = {"classpath:test_beans.xml"})
public class IntervalResourceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	/************** STATICS **********************/
	private static ApplicationContext context = new ClassPathXmlApplicationContext(Locations.CONFIG_LOCATION);
	private static IntervalResource intervalResource = new IntervalResource();
	static{
		intervalResource.setReferenceService((ReferenceService)context.getBean("referenceService"));
	}
	

	@Test
	@Rollback(true)
	public void testGet(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGet");
		Response response = intervalResource.getIntervals(null);
		GenericEntity<List<IntervalTypeDto>> entity = (GenericEntity<List<IntervalTypeDto>>)response.getEntity();
		List<IntervalTypeDto> dtos = entity.getEntity();
		assertNotNull("no dtos were returned", dtos);
		assertNotNull("no type dto has no intervals", dtos.get(0).getIntervalDtos());
		System.out.println("TEST: Done");
}

}
