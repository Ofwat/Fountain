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
package uk.gov.ofwat.fountain.api;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test_beans.xml"})
public class ReportServiceEditComparisonTest extends AbstractJUnit4SpringContextTests {

	@Resource
	private ReportServiceImpl reportService;
	
	@Test
	public void testValueHasChanged() {
		CellDto cellDto = new CellDto();
		DataDto dataDto = new DataDto();
		
		cellDto.setValue("34.8990253411306");
		dataDto.setRawValue(34.8990253411306);
		Assert.assertFalse(reportService.valueHasChanged(cellDto, dataDto));
		
		cellDto.setValue("34.9");
		dataDto.setRawValue(34.8990253411306);
		Assert.assertTrue(reportService.valueHasChanged(cellDto, dataDto));
		
		cellDto.setValue("34.899");
		dataDto.setRawValue(34.8990253411306);
		Assert.assertTrue(reportService.valueHasChanged(cellDto, dataDto));
		
		cellDto.setValue("171.223");
		dataDto.setRawValue(171.2230000000001);
		Assert.assertFalse(reportService.valueHasChanged(cellDto, dataDto));
		
		cellDto.setValue("171.223");
		dataDto.setRawValue(171.2230000000001);
		Assert.assertFalse(reportService.valueHasChanged(cellDto, dataDto));
		
		cellDto.setValue("41527");
		dataDto.setRawValue(41527.0);
		Assert.assertFalse(reportService.valueHasChanged(cellDto, dataDto));
		
	}
	
	
}
