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

package uk.gov.ofwat.fountain.api.report;

import uk.gov.ofwat.fountain.api.ReportService;


public class ReportAxisFactory {
	
	/**
	 * get the enum by name
	 * @param code
	 * @return
	 */
	public static ReportAxis create(String code, ReportStructure reportStructure, ReportService reportService) { 
		// 'Year' is used instread of 'interval' in some cases
		String key = code.toUpperCase();
		if(key.equals("YEAR")){
			key = "INTERVAL";
		}

		if (key.equals("COMPANY")) {
			return new CompanyReportAxis(reportStructure, reportService);
		}  
		else if (key.equals("INTERVAL")) {
			return new IntervalReportAxis(reportStructure, reportService);
		} 
		else if (key.equals("ITEM")) {
			return new ItemReportAxis(reportStructure, reportService);
		} 
		else if (key.equals("RUN")) {
			return new RunReportAxis(reportStructure, reportService);
		} 
			
		return null;
    }
	
}
