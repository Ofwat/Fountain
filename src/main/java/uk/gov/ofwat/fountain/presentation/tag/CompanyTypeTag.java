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

package uk.gov.ofwat.fountain.presentation.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.gov.ofwat.fountain.domain.Company;

public class CompanyTypeTag  extends TagSupport {

	/**
	 * wrap jsp content with this tag and pass in company and company type.
	 * 
	 *  if the company is not suitable for this type the enclosed 
	 *  content will not be rendered.
	 *  
	 *  e.g.
	 *  <ofwat:companyType companyType=\"${companyType}\" company=\"${company}\">
	 *  	... conditional jsp content ...
	 *  </ofwat:companyType>
	 *  
	 */
	private static final long serialVersionUID = 2275133188176356720L;
	private Company company;
	private String companyType;
	
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@Override
	public int doStartTag() throws JspException {
		if (company.isCompanyInType(companyType)) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
	
	

}
