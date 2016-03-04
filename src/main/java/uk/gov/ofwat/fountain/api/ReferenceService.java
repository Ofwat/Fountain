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

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.domain.InflationType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.PriceBase;
import uk.gov.ofwat.fountain.domain.Team;

public interface ReferenceService {

	List<Interval> getIntervals();
	
	/**
	 * fetches a list of intervals restricted to the types indicated
	 * @param intervalTypeIds
	 * @return
	 */
	List<Interval> getIntervals(int[] intervalTypeIds);
	
	Interval getInterval(int id);

	Interval getInterval(String name);
	
	List<Company> getCompanies();
	
	List<Company> getCurrentCompanies();
	
	Company getCompany(int id);
	
	Company getCompany(String code);
	
	List<CompanyType> getCompanyTypes();
	
	Interval getOffsetInterval(Interval interval, int intervalOffset);

	PriceBase getPriceBase(String code);
	
	InflationType getInflationType(String code);
	
	Team getTeam(String name);

}
