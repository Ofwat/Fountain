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

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.dao.CompanyDao;
import uk.gov.ofwat.fountain.dao.InflationTypeDao;
import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.PriceBaseDao;
import uk.gov.ofwat.fountain.dao.TeamDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyPlaceHolder;
import uk.gov.ofwat.fountain.domain.CompanyType;
import uk.gov.ofwat.fountain.domain.InflationType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.PriceBase;
import uk.gov.ofwat.fountain.domain.Team;

/** 
 * Service class responsible for reference data (Companies, Years etc)
 * 
 * Anything not directly relating to models, Data dictionary or instance data.
 */
public class ReferenceServiceImpl implements ReferenceService{
	
	private IntervalDao intervalDao;
	private CompanyDao companyDao;
	public void setInflationTypeDao(InflationTypeDao inflationTypeDao) {
		this.inflationTypeDao = inflationTypeDao;
	}

	private PriceBaseDao priceBaseDao;
	private TeamDao teamDao;
	private InflationTypeDao inflationTypeDao; 
	
	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}
	
	public void setPriceBaseDao(PriceBaseDao priceBaseDao) {
		this.priceBaseDao = priceBaseDao;
	}
	
	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}

	public Interval getInterval(int id) {
		Interval interval = intervalDao.findById(id);
		return interval; 
	}
	
	public Interval getInterval(String name) {
		Interval retInterval = null;
		List<Interval> intervals = getIntervals();
		for (Interval interval: intervals) {
			if (interval.getName().equals(name)) {
				retInterval = interval;
				break;
			}
		}
		return retInterval;
	}

	public List<Interval> getIntervals() {
		List<Interval> intervals = intervalDao.getAll();
		return intervals;
	}
	
	public List<Interval> getIntervals(int[] intervalTypeIds) {
		List<Interval> intervals = new ArrayList<Interval>();
		for(int typeId: intervalTypeIds){
			intervals.addAll(intervalDao.getIntervalsByTypeId(typeId));
		}
		return intervals;
	}

	public List<Company> getCurrentCompanies() {
		List<Company> companies = companyDao.getAllCurrent();
		return companies;
	}
	
	public List<Company> getCompanies() {
		List<Company> companies = companyDao.getAll();
		return companies;
	}
	
	public Company getCompany(int id) {
		if (0 == id) {
			return CompanyPlaceHolder.COMPANY_PLACE_HOLDER;
		}
		Company company = companyDao.findById(id);
		return company;
	}

	public Company getCompany(String code) {
		Company company = companyDao.findByCode(code);
		return company;
	}

	public List<CompanyType> getCompanyTypes() {
		List<CompanyType> types = companyDao.getAllCompanyTypes();
		return types;
	}

	public Interval getOffsetInterval(Interval interval, int intervalOffset) {
		if (0 == intervalOffset) {
			return interval;
		}

		Integer offset = interval.getIntervalIndex() + intervalOffset;

		for (Interval offsetInterval : getIntervals()) {
			if (interval.getIntervalType().getId() == offsetInterval.getIntervalType().getId() &&
				offset == offsetInterval.getIntervalIndex()) {
				return offsetInterval;
			}
		}

		return null;
	}

	public PriceBase getPriceBase(String code) {
		return priceBaseDao.findByCode(code);
	}

	public Team getTeam(String name) {
		return teamDao.findByName(name);
	}

	@Override
	public InflationType getInflationType(String code) {
		return inflationTypeDao.findByCode(code);
	}

}
