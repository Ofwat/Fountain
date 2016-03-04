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
package uk.gov.ofwat.fountain.domain.form;

import java.util.ArrayList;
import java.util.List;

public class NavigationPane {
	
	private List<ModelPage> modelPages = new ArrayList<ModelPage>();
	
	public void addPage(ModelPage modelPage) {
		modelPages.add(modelPage);
	}
	
	public String toHtml(ModelPage currentFormPage) {
		String html = "";
		html = html + "<div rel=\"dijit.layout.ContentPane\" title=\"Tables\">";
		html = html + "<div id=\"navPane\">"; 
		html = html + "<ul id=\"navlist\">";
		for (ModelPage modelPage: modelPages) {
			html += "<li class='NavPane companyType " + modelPage.getCompanyType().toUpperCase() + "'>";
			if (currentFormPage.getTable().getId() == modelPage.getTable().getId()) {
				html = html + "<a href=\"javascript:ofwat.modelPage.showPage('" + modelPage.getPageName() + "')\" id=\"current\">Table " + modelPage.getTable().getName() + "</a>";
			}
			else {
				html = html + "<a href=\"javascript:ofwat.modelPage.showPage('" + modelPage.getPageName() + "')\">Table " + modelPage.getTable().getName() + "</a>";
			}
			html += "</li>";
		}
		html = html + "</ul>";
		html = html + "</div>";
		html = html + "</div>";
		
		return html;
	}
	
}
