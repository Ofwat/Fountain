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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * checks user is in role, if so evaluates tag content.
 * 
 * takes multiple comma delimited role names (role attribute)
 */
public class UserRoleTag  extends TagSupport {

	
	private static final long serialVersionUID = 771808932095338889L;
	
	private List<String> roleList = new ArrayList<String>();
	
	public void setRole(String roleString){
		roleList.clear();
		for(String role: roleString.split(",")){
			roleList.add(role.trim());
		}
	}
	
	@Override
	public int doStartTag() throws JspException {
		ServletRequest req = pageContext.getRequest();
		if(req instanceof HttpServletRequest){
			HttpServletRequest httpReq = (HttpServletRequest)req;
			for(String role: roleList){
				if(httpReq.isUserInRole(role)){
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		return SKIP_BODY;
	}

}
