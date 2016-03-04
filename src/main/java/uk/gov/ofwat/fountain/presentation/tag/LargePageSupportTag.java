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

/**
 * 
 */
public class LargePageSupportTag extends TagSupport  {
	
	
	/**
	 * does nothing really - always evaluates the body of the tag.
	 * 
	 * What it does do is force the page to break up the JSP service method into 
	 * smaller private methods which prevents large pages breaking the 65kb method limit 
	 * when compiled.
	 */
	private static final long serialVersionUID = -2614605183138292350L;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_INCLUDE;
	}

}
