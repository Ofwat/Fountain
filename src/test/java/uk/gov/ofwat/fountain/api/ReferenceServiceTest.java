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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.dao.CompanyDao;
import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;


public class ReferenceServiceTest extends TestCase {
	
	private IntervalDao mockIntervalDao;
	private CompanyDao mockCompanyDao;
	ReferenceServiceImpl referenceService;
	private Interval interval;
	private IntervalType intervalType;
	private Interval mockInterval;
	private IntervalType mockIntervalType;
	
	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		referenceService = new ReferenceServiceImpl();
		
		mockIntervalDao = mock(IntervalDao.class);
		mockCompanyDao = mock(CompanyDao.class);
		
		referenceService.setIntervalDao(mockIntervalDao);
		referenceService.setCompanyDao(mockCompanyDao);
	
		System.out.println("TEST: Done");
}

	public void testGetIntervals() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetIntervals");
		List<Interval> intervals = new ArrayList<Interval>();
		when(mockIntervalDao.getAll()).thenReturn(intervals);
		
		Assert.assertEquals(intervals, referenceService.getIntervals());
	
		System.out.println("TEST: Done");
}
	
	public void testGetCompanies() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetCompanies");
		List<Company> companies = new ArrayList<Company>();
		when(mockCompanyDao.getAllCurrent()).thenReturn(companies);
		
		Assert.assertEquals(companies, referenceService.getCompanies());
	
		System.out.println("TEST: Done");
}

	public void testGetCompany() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetCompany");
		Company company = new Company();
		when(mockCompanyDao.findById(1)).thenReturn(company);
		
		Assert.assertEquals(company, referenceService.getCompany(1));
	
		System.out.println("TEST: Done");
}
	
	public void testGetCompanyTypes(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetCompanyTypes");
		List<CompanyType> types = new ArrayList<CompanyType>();
		when (mockCompanyDao.getAllCompanyTypes()).thenReturn(types);
		
		Assert.assertEquals(types, referenceService.getCompanyTypes());
		
	
		System.out.println("TEST: Done");
}
	
	
	public void testGetOffsetYear_0() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetOffsetYear_0");
		intervalOffsetSetUp();
		Assert.assertEquals("", interval, referenceService.getOffsetInterval(interval, 0));
	
		System.out.println("TEST: Done");
}

	public void testGetOffsetYear_1() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetOffsetYear_1");
		intervalOffsetSetUp();
		List<Interval> intervalList = new ArrayList<Interval>();
		intervalList.add(mockInterval);
		
		when(mockIntervalDao.getAll()).thenReturn(intervalList);
		when(mockInterval.getIntervalType()).thenReturn(mockIntervalType);
		when(mockIntervalType.getId()).thenReturn(1);
		when(mockInterval.getIntervalIndex()).thenReturn(3);
		
		Assert.assertEquals("", mockInterval, referenceService.getOffsetInterval(interval, 1));
	
		System.out.println("TEST: Done");
}

	public void testGetOffsetYear_minus1() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetOffsetYear_minus1");
		intervalOffsetSetUp();
		List<Interval> intervalList = new ArrayList<Interval>();
		intervalList.add(mockInterval);
		
		when(mockIntervalDao.getAll()).thenReturn(intervalList);
		when(mockInterval.getIntervalType()).thenReturn(mockIntervalType);
		when(mockIntervalType.getId()).thenReturn(1);
		when(mockInterval.getIntervalIndex()).thenReturn(1);
		
		Assert.assertEquals("", mockInterval, referenceService.getOffsetInterval(interval, -1));
	
		System.out.println("TEST: Done");
}

	public void intervalOffsetSetUp() {

		intervalType = new IntervalType();
		intervalType.setId(1);
		intervalType.setName("fred");
		
		interval = new Interval();
		interval.setId(1);
		interval.setName("2000");
		interval.setIntervalIndex(2);
		interval.setIntervalType(intervalType);
		
		mockInterval = mock(Interval.class);
		mockIntervalType = mock(IntervalType.class);
	}

}
