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
package uk.gov.ofwat.fountain.aspect.Recording;

import java.io.FileWriter;

import org.apache.poi.ss.usermodel.Workbook;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.api.table.TableStructure;
import uk.gov.ofwat.fountain.domain.DataTable;

import com.advancedpwr.record.xstream.XstreamRecorder;

@Aspect
@Order(989)
public class TableWorkbookRecorder {

	@Before("execution(org.apache.poi..usermodel.Workbook uk.gov.ofwat.fountain.api.table.POITableRenderer.renderTable(..)) && args(workBook, tableStructure, table)")
	public void recordReportDef(Workbook workBook, TableStructure tableStructure, DataTable table) {
		XstreamRecorder recorder = new XstreamRecorder();
		recorder.setDestination("test");
		recorder.record(tableStructure);
		System.out.println("recorded TableStructure");

		recorder = new XstreamRecorder();
		recorder.setDestination("test");
		recorder.record(table);
		System.out.println("recorded DataTable");
	}

	@AfterReturning(pointcut="execution(org.apache.poi..usermodel.Workbook uk.gov.ofwat.fountain.api.table.POITableRenderer.renderTable(..))", returning="workBook")
	public void recordWorkBook(Workbook workBook) throws Throwable {
		XstreamRecorder recorder = new XstreamRecorder();
		recorder.setDestination("test");
//		System.out.println("print writer encoding = " + recorder.getXmlFileWriter().getEncoding());
		recorder.record(workBook);
		FileWriter fw = recorder.getXmlFileWriter();
		String encodeing = recorder.getXmlFileWriter().getEncoding();
		System.out.println("recorded Workbook");
	}

}
