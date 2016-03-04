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

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class WikiPageTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 103025710152549420L;

	private String wikiAddress;

	/**
	 * The wiki page to refer to - ie. My_Reports
	 */
	private String helpPage;
	private String toolTip = "Help";
	private boolean model = false;
	
	public void setWikiAddress(String wikiAddress) {
		this.wikiAddress = wikiAddress;
	}

	public void setHelpPage(String helpPage) {
		this.helpPage = helpPage;
	}

	public void setToolTip(String toolTip){
		this.toolTip = toolTip;
	}
	
//	public void setWikiAddress(String wikiAddress) {
//		this.wikiAddress = wikiAddress;
//	}

	public void setModel(boolean model) {
		this.model = model;
	}

	@Override
	public int doStartTag() throws JspException {
	
		String url = "<a href=\""+wikiAddress+helpPage+".aspx\" target=\"_blank\"><img alt=\"help\" src=\""+(model?"../../":"")+"../../images/help.png\" title=\""+toolTip+"\" border=0></img></a>";
		
		try {
			pageContext.getOut().print(url);
		} catch (IOException ioe) {
			throw new JspException("Error: IOException while writing to client");
		}	
		return SKIP_BODY;
	}
}
