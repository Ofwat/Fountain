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

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Table;

public class NavigationPaneTest extends TestCase {
	
	public void testNavigationPane(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testNavigationPane");
		NavigationPane np = new NavigationPane();
		
		ModelPage fp = new ModelPage();
		Table t = new Table();
		Model m = new Model("TEST23", "TEST23", ModelType.ICS);
		m.setBranch(new Branch());
		t.setName("TABLE77");
		t.setId(23);
		fp.setModel(m);
		fp.setTable(t);
		fp.setCompanyType("WOC");
		fp.setTableDescription("test_description");
		
		
		
		FormDisplayCell fdc1 = mock(FormDataCell.class);
		FormDisplayCell fdc2 = mock(FormDataCell.class);
		FormDisplayCell fdc3 = mock(FormDataCell.class);
		FormDisplayCell fdc4 = mock(FormDataCell.class);
		List<FormDisplayCell> dataCells = new ArrayList<FormDisplayCell>();
		dataCells.add(fdc1);
		dataCells.add(fdc2);
		dataCells.add(fdc3);
		dataCells.add(fdc4);
		fp.setDataDisplayCells(dataCells);
		
		FormHeaderCell ldc1 = mock(FormHeaderCell.class);
		FormHeaderCell ldc2 = mock(FormHeaderCell.class);
		FormHeaderCell ldc3 = mock(FormHeaderCell.class);
		FormHeaderCell ldc4 = mock(FormHeaderCell.class);
		List<FormDisplayCell> leftDisplayCells = new ArrayList<FormDisplayCell>();
		leftDisplayCells.add(ldc1);
		leftDisplayCells.add(ldc2);
		leftDisplayCells.add(ldc3);
		leftDisplayCells.add(ldc4);
		fp.setLeftDisplayCells(leftDisplayCells);
		
		
		FormHeaderCell tdc1 = mock(FormHeaderCell.class);
		FormHeaderCell tdc2 = mock(FormHeaderCell.class);
		FormHeaderCell tdc3 = mock(FormHeaderCell.class);
		FormHeaderCell tdc4 = mock(FormHeaderCell.class);
		List<FormDisplayCell> topDisplayCells = new ArrayList<FormDisplayCell>();
		topDisplayCells.add(tdc1);
		topDisplayCells.add(tdc2);
		topDisplayCells.add(tdc3);
		topDisplayCells.add(tdc4);
		fp.setTopDisplayCells(topDisplayCells);
		
		
		ModelPage fp2 = new ModelPage();
		Table t2 = new Table();
		Model m2 = new Model("TEST24", "TEST24", ModelType.ICS);
		m2.setBranch(new Branch());
		t2.setName("TABLE88");
		t2.setId(24);
		fp2.setModel(m2);
		fp2.setTable(t2);
		fp2.setTableDescription("test_description2");
		fp2.setCompanyType("WASC");
		np.addPage(fp);
		np.addPage(fp2);
		
		String output = np.toHtml(fp);
		assertNotNull("no output retrieved", output);
		System.out.println(output);
		String expected = "<div rel=\"dijit.layout.ContentPane\" title=\"Tables\"><div id=\"navPane\"><ul id=\"navlist\">" +
				"<li class='NavPane companyType WOC'>" +
				"<a href=\"javascript:ofwat.modelPage.showPage('table_TABLE77')\" id=\"current\">Table TABLE77</a>" +
				"</li>" +
				"<li class='NavPane companyType WASC'>" +
				"<a href=\"javascript:ofwat.modelPage.showPage('table_TABLE88')\">Table TABLE88</a>" +
				"</li>" +
				"</ul></div></div>";
		assertEquals("incorrect output html", expected, output);
		
	
		System.out.println("TEST: Done");
}

}
