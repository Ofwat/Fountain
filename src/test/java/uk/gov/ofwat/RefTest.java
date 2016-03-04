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

package uk.gov.ofwat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RefTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RefTest rt = new RefTest();
//		rt.start();
		try{
			rt.writeXLS();
			
		}
		catch(IOException ioe){
			System.err.println("err: " + ioe.getMessage());
		}

	}
	
	public void start(){
		
		settable words = null;
		Inner i1 = setup(words);
		words = new settable();
		words.value = "value";
		System.out.println("I'm called " + i1.getName().value);
	}
	
	private Inner setup(settable name){
		Inner inner = new Inner();
		inner.setName(name);
		return inner;
	}
	
	
	private class Inner{
		private settable name;
		
		private void setName(settable name){
			this.name = name;
		}
		private settable getName(){
			return name;
		}
	}
	
	private class settable{
		private String value;
	}
	
	public void writeXLS() throws IOException{
		XSSFWorkbook wb = new XSSFWorkbook();
		CreationHelper creationHelper = wb.getCreationHelper();
		// create a new sheet
		Sheet s = wb.createSheet();
		// declare a row object reference
		Row r = null;
		// declare a cell object reference
		Cell c = null;
		// create 2 cell styles
		XSSFCellStyle cs = wb.createCellStyle();

		XSSFCellStyle cs2 = wb.createCellStyle();
		DataFormat df = wb.createDataFormat();

		// create 2 fonts objects
		Font f = wb.createFont();
		Font f2 = wb.createFont();

		// Set font 1 to 12 point type, blue and bold
		f.setFontHeightInPoints((short) 12);
		f.setColor( IndexedColors.RED.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// Set font 2 to 10 point type, red and bold
		f2.setFontHeightInPoints((short) 10);
		f2.setColor( IndexedColors.RED.getIndex() );
		f2.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// Set cell style and formatting
		cs.setFont(f);
		cs.setDataFormat(df.getFormat("#,##0.0"));

		// Set the other cell style and formatting
		cs2.setBorderBottom(cs2.BORDER_THIN);
		cs2.setDataFormat((short) BuiltinFormats.getBuiltinFormat("text"));
		cs2.setFont(f2);


		// Define a few rows
		for(int rownum = 0; rownum < 30; rownum++) {
			 r = s.createRow(rownum);
			for(int cellnum = 0; cellnum < 10; cellnum += 2) {
				c = r.createCell(cellnum);
				Cell c2 = r.createCell(cellnum+1);

				c.setCellValue((double)rownum + (cellnum/10));
				c2.setCellValue(creationHelper.createRichTextString("Hello! " + cellnum));
			}
		}

		File file = new File("d:\\out.xls");
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
//		fos.write(wb.getBytes());
//		fos.flush();
//		fos.close();
		
	}

}
