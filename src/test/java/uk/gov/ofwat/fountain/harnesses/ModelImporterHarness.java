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

package uk.gov.ofwat.fountain.harnesses;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.FileService;
import uk.gov.ofwat.model2.ModelDocument;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.LineDocument.Line;
import uk.gov.ofwat.model2.ModelDocument.Model;
import uk.gov.ofwat.model2.PageDocument.Page;
import uk.gov.ofwat.model2.SectionDocument.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test_beans.xml"})
public class ModelImporterHarness extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private FileService fileService;
	
	private static final String testModelFileName = "d:/temp/builder/JR2011-ICS.xml";
	
	@Test
	@Rollback(true)
	public void testImport() throws Exception {
		ModelDocument md = obtainModel();
		showTable(md.getModel(), "10a(i)");

		List<String> results = new Vector<String>();
		results.addAll(fileService.processModel(md));
		results.addAll(fileService.processModelForms(md));
		
		for (String result : results) {
			System.out.println(result);
		}		
	}
	
	private void showTable(Model model, String tableCode) {
		Page table = null;
		for (Page page : model.getPages().getPageArray()) {
			if (page.getPagedetails().getCode().equalsIgnoreCase(tableCode)) {
				table = page;
				break;
			}
		}
		
		if (table==null) throw new RuntimeException("Couldn't find " + tableCode);
		
		System.out.println("TABLE");
		System.out.println("Code=" + table.getPagedetails().getCode());
		
		for (Section section : table.getSections().getSectionArray()) {
			System.out.println("");
			System.out.println("  SECTION " + section.getSectiondetails().getCode());
			
			for (Line line : section.getLines().getLineArray()) {
				System.out.println("    LINE " + line.getLinedetails().getCode() + " (desc=" + line.getLinedetails().getDescription() + ")");
				
				for (Cell cell : line.getCells().getCellArray()) {
					System.out.println("      CELL " + cell.getYear() + " (eq=" + cell.getEquation() + ")");
				}
			}
		}
		
	}
	
	private ModelDocument obtainModel() throws Exception{
		File testModelFile = new File(testModelFileName);
		ModelDocument md = ModelDocument.Factory.parse(testModelFile);
		return md;
	}
	
	
	

}
