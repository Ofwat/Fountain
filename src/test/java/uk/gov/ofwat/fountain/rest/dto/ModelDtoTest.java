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
package uk.gov.ofwat.fountain.rest.dto;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import junit.framework.Assert;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.rest.Link;

public class ModelDtoTest {

	@Test
	public void testModelDto() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testModelDto");
		Collection<Link> tableLinks = new ArrayList<Link>();
		Company mockCompany = mock(Company.class);
		ModelType mt = ModelType.ICS;
		Branch bt = new Branch(1, "sketch1", false);
		Model model = new Model("name", "code", mt);
		model.setBranch(bt);
		model.setCode("code");
		model.setId(1);

		ModelDto modelDto = new ModelDto(model, tableLinks);
		modelDto.setCompany(mockCompany);

		Assert.assertEquals("", model.getName(), modelDto.getName());
		Assert.assertEquals("", model.getCode(), modelDto.getCode());
		Assert.assertEquals("", model.getId(), modelDto.getId());
		Assert.assertEquals("", tableLinks, modelDto.getTableLinks());
		Assert.assertEquals("", mockCompany, modelDto.getCompany());
		Assert.assertEquals("", mockCompany, modelDto.getCompany());
	
		System.out.println("TEST: Done");
}
}
