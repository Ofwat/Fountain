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

import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.report.ReportLine;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.domain.ReportDefinition;

import com.advancedpwr.record.xstream.XstreamRecorder;

@Aspect
@Order(999)
public class ReportLinesStubRecorder {

	private ReportService reportService;
	
	@Before("execution(int uk.gov.ofwat.fountain.dao.ReportDaoImpl.create(*)) && args(reportDef)")
	public void recordReportDef(ReportDefinition reportDef) {
		XstreamRecorder recorder = new XstreamRecorder();
		recorder.setDestination("test");
		recorder.record(reportDef);
	}

	@AfterReturning(pointcut="execution(public int uk.gov.ofwat.fountain.rest.ReportResource.save*Report(..))", returning="reportId")
	public void recordLines(int reportId) throws Throwable {
		ReportStructure reportStructure = reportService.getReportStructure(reportId);
		reportStructure.getLines();
		
		XstreamRecorder recorder = new XstreamRecorder();
		recorder.setDestination("test");
		recorder.record(reportStructure);
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}
}
