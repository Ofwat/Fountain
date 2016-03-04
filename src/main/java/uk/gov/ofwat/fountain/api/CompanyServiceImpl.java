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

import org.springframework.beans.factory.annotation.Autowired;

import uk.gov.ofwat.fountain.dao.CompanyDao;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;

public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	public void setCompanyDao(CompanyDao companyDao){
		this.companyDao = companyDao;
	}

	public Company getCompany(int id) {
		return companyDao.findById(id);
	}

	public int getCompanyTypeId(String companyTypeName) {
		return companyDao.findCompanyTypeId(companyTypeName);
	}

	public Company getCompanyByCode(String code) {
		return companyDao.findByCode(code);
	}

	public Company getCompanyByName(String name) {
		for (Company company: companyDao.getAllCurrent()) {
			if (name.trim().equals(company.getName())) {
				return company;
			}
		}
		return null;
	}

	public CompanyType findCompanyTypeByCode(String code) {
		return companyDao.findByCompanyTypeCode(code);
	}
	
	public boolean isCompanyCode(String code) {
		List<Company> companies = companyDao.getAll();
		for (Company company: companies) {
			if (company.getCode().equalsIgnoreCase(code)) {
				return true;
			}
		}
		return false;
	}
	
}
