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
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelType;
import uk.gov.ofwat.fountain.domain.Table;

public class FormPageTest extends TestCase{
	
	
	
	
	private ModelPage fp;
	
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		fp = new ModelPage();
		Table t = new Table();
		Model m = new Model("TEST23", "TEST23", ModelType.ICS);
		m.setBranch(new Branch());
		t.setName("TABLE77");
		t.setId(23);
		fp.setModel(m);
		fp.setTable(t);
		fp.setTableDescription("test_description");
		
		FormDisplayCell fdc1 = mock(FormDataCell.class);
		FormDisplayCell fdc2 = mock(FormDataCell.class);
		FormDisplayCell fdc3 = mock(FormDataCell.class);
		FormDisplayCell fdc4 = mock(FormDataCell.class);
		
		when(fdc1.getSection()).thenReturn("section 1");
		when(fdc1.getRow()).thenReturn(1);		
		when(fdc1.getColumn()).thenReturn(2);
		when(fdc1.getColumnSpan()).thenReturn(1);
		when(fdc1.toHTML()).thenReturn("<td> data 1 </td>");
		when(fdc2.getSection()).thenReturn("section 1");
		when(fdc2.getRow()).thenReturn(2);
		when(fdc2.getColumn()).thenReturn(3);
		when(fdc2.getColumnSpan()).thenReturn(1);
		when(fdc2.toHTML()).thenReturn("<td> data 2 </td>");
		when(fdc3.getSection()).thenReturn("section 1");
		when(fdc3.getRow()).thenReturn(3);
		when(fdc3.getColumn()).thenReturn(4);
		when(fdc3.getColumnSpan()).thenReturn(1);
		when(fdc3.toHTML()).thenReturn("<td> data 3 </td>");
		when(fdc4.getSection()).thenReturn("section 1");
		when(fdc4.getRow()).thenReturn(4);
		when(fdc4.getColumn()).thenReturn(5);
		when(fdc4.getColumnSpan()).thenReturn(1);
		when(fdc4.toHTML()).thenReturn("<td> data 4 </td>");
		
		
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
		
		when(ldc1.getSection()).thenReturn("section 1");
		when(ldc1.getRow()).thenReturn(1);
		when(ldc1.getColumn()).thenReturn(1);
		when(ldc1.toHTML()).thenReturn("<td> left 1 </td>");
		when(ldc2.getSection()).thenReturn("section 1");
		when(ldc2.getRow()).thenReturn(2);
		when(ldc2.getColumn()).thenReturn(1);
		when(ldc2.toHTML()).thenReturn("<td> left 2 </td>");
		when(ldc3.getSection()).thenReturn("section 1");
		when(ldc3.getRow()).thenReturn(3);
		when(ldc3.getColumn()).thenReturn(1);
		when(ldc3.toHTML()).thenReturn("<td> left 3 </td>");
		when(ldc4.getSection()).thenReturn("section 1");
		when(ldc4.getRow()).thenReturn(4);
		when(ldc4.getColumn()).thenReturn(1);
		when(ldc4.toHTML()).thenReturn("<td> left 4 </td>");
		
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
		
		when(tdc1.getSection()).thenReturn("section 1");
		when(tdc1.getRow()).thenReturn(1);
		when(tdc1.getColumn()).thenReturn(2);
		when(tdc1.toHTML()).thenReturn("<td> top 1 </td>");
		when(tdc2.getSection()).thenReturn("section 1");
		when(tdc2.getRow()).thenReturn(1);
		when(tdc2.getColumn()).thenReturn(3);
		when(tdc2.toHTML()).thenReturn("<td> top 2 </td>");
		when(tdc3.getSection()).thenReturn("section 1");
		when(tdc3.getRow()).thenReturn(1);
		when(tdc3.getColumn()).thenReturn(4);
		when(tdc3.toHTML()).thenReturn("<td> top 3 </td>");
		when(tdc4.getSection()).thenReturn("section 1");
		when(tdc4.getRow()).thenReturn(1);
		when(tdc4.getColumn()).thenReturn(5);
		when(tdc4.toHTML()).thenReturn("<td> top 4 </td>");
		
		
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

	}

	public void testGetPageTitleHtml(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetPageTitleHtml");
		
		System.out.println(fp.getPopulatePageHtml());
		System.out.println(fp.getDataTableHtml());
		System.out.println(fp.getPageTitleHtml());
		
		
		assertEquals("incorrect rest url", POPULATE_PAGE, fp.getPopulatePageHtml());
		assertEquals("incorrect page markup", DATA_TABLE, fp.getDataTableHtml());
		assertEquals("incorrect page title", PAGE_TITLE, fp.getPageTitleHtml());
	
		System.out.println("TEST: Done");
}
	//<tr>\n<td>\n<table class=\"pure-table pure-table-bordered\">\n</table>\n</td>\n</tr>\n
	private final static String DATA_TABLE = "<table class=\"pure-table pure-table-bordered ICS_MARKER\">\n</table>\n";
	private final static String POPULATE_PAGE = "ofwat.modelPage.populatePage(\"/Fountain/rest-services/table/23?companyId=\" + companyId);";
	private static final String PAGE_TITLE = "TEST23 Table TABLE77";
}
