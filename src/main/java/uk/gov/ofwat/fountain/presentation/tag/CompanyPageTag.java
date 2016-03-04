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

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import uk.gov.ofwat.fountain.domain.Table;

public class CompanyPageTag  extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7014743012646403487L;
	
	private List<Table> tables;
	private String tableName;
	
	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
		if(null != tableName && tableName.indexOf("table_") > -1){
			// remove table_ prefix
			this.tableName = this.tableName.substring(6);
		}
	}



	@Override
	public int doStartTag() throws JspException {
		for(Table table: tables){
			if(table.getName().equalsIgnoreCase(tableName)){
				return EVAL_BODY_INCLUDE;
			}
		}
		return SKIP_BODY;
	}

}
