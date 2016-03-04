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

package uk.gov.ofwat.fountain.aspect.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;

public class CacheTestCompanyDaoImpl implements CacheTestCompanyDao {

	private Company company;
	
	public void setCompany(Company company) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setCompany");
		System.out.println("CacheTestCompanyDaoImpl.setCompany()");
		this.company = company;
	
		System.out.println("TEST: Done");
}
	
	public Company findByCode(String code) {
		System.out.println("CacheTestCompanyDaoImpl.findByCode()");
		return this.company;
	}
	
	public void delete(int id) {
		System.out.println("CacheTestCompanyDaoImpl.delete()");
	}
	
	public void update(Company company){
		System.out.println("CacheTestCompanyDaoImpl.update()");
	}

	public Company findById(int id) {
		System.out.println("CacheTestCompanyDaoImpl.findById()");
		return this.company;
	}

	public int create(Company company) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Company> getAllCurrent() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CompanyType> getAllCompanyTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public int findCompanyTypeId(String companyTypeName) {
		// TODO Auto-generated method stub
		return 0;
	}

	public CompanyType findByCompanyTypeId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public CompanyType findByCompanyTypeCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getCompaniesForRunCompanyTemplate(
			int runCompanyTemplateId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
