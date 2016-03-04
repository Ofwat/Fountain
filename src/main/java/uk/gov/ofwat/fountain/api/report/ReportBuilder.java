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

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.RunService;
import uk.gov.ofwat.fountain.api.TagService;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.rest.dto.ModelDto;


/**
 * turns an incoming report definition into a report structure object.
 */
public class ReportBuilder {
	
	private ModelService modelService;
	private ItemService itemService;
	private ReferenceService referenceService;
	private ReportService reportService;

	public ReportBuilder(ReportService reportService, 
						 ModelService modelService, 
			             ItemService itemService, 
			             ReferenceService referenceService){
		this.reportService = reportService;
		this.modelService = modelService;
		this.itemService = itemService;
		this.referenceService = referenceService;
	}

	public ReportStructure makeReportStructure(ReportDefinition reportDef, boolean showAllHeadings) {

		ReportStructure reportStructure = new ReportStructure();
		
		// set the basics
		reportStructure.setReportName(reportDef.getName());
		reportStructure.setReportId(reportDef.getId());
		reportStructure.setShowAllHeadings(showAllHeadings);
		
		// set the headings options
		reportStructure.setCg(reportDef.getReportDisplay().isDisplayCGs());
		reportStructure.setFullName(reportDef.getReportDisplay().isDisplayCompanyName());
		reportStructure.setItemDesc(reportDef.getReportDisplay().isDisplayDesc());
		reportStructure.setModel(reportDef.getReportDisplay().isDisplayModel());
		reportStructure.setUnit(reportDef.getReportDisplay().isDisplayUnit());
		reportStructure.setAcronym(reportDef.getReportDisplay().isDisplayCompanyAcronym());
		reportStructure.setBonCode(reportDef.getReportDisplay().isDisplayBoncode());
		// headings options for runs
		reportStructure.setRunName(reportDef.getReportDisplay().isDisplayRunName());
		reportStructure.setRunDesc(reportDef.getReportDisplay().isDisplayRunDesc());
		reportStructure.setTagDisplayName(reportDef.getReportDisplay().isDisplayTagDisplayName());
		reportStructure.setAgendaName(reportDef.getReportDisplay().isDisplayAgendaName());
		reportStructure.setAgendaCode(reportDef.getReportDisplay().isDisplayAgendaCode());
	
		// tell the report which axis to use.
		ReportAxis top1 = ReportAxisFactory.create(reportDef.getLayoutTop().get(0), reportStructure, reportService);
		ReportAxis top2 = null;
		ReportAxis top3 = null;
		if (1 < reportDef.getLayoutTop().size()) {
			top2 = ReportAxisFactory.create(reportDef.getLayoutTop().get(1), reportStructure, reportService);
			if (3 == reportDef.getLayoutTop().size()) {
				top3 = ReportAxisFactory.create(reportDef.getLayoutTop().get(2), reportStructure, reportService);
			}
		}
		reportStructure.setTopPrimary(top1);
		reportStructure.setTopSecondary(top2); // can be null
		reportStructure.setTopTertiary(top3); // can be null

		ReportAxis left1 = ReportAxisFactory.create(reportDef.getLayoutLeft().get(0), reportStructure, reportService);
		ReportAxis left2 = null;
		ReportAxis left3 = null;		
		if (1 < reportDef.getLayoutLeft().size()) {
			left2 = ReportAxisFactory.create(reportDef.getLayoutLeft().get(1), reportStructure, reportService);
			if (3 == reportDef.getLayoutLeft().size()) {
				left3 = ReportAxisFactory.create(reportDef.getLayoutLeft().get(2), reportStructure, reportService);
			}
		}
		reportStructure.setLeftPrimary(left1);
		reportStructure.setLeftSecondary(left2); // can be null
		reportStructure.setLeftTertiary(left3); // can be null
		reportStructure.setGroup(reportDef.getGroup()); // can be null
		
		
		// populate the various entities
		
		// modelItems will arrive with just itemId, modelId and modelPropertiesMapId.
		// we need to fill in the other fields
		List<ReportItem> items = new ArrayList<ReportItem>();
		for(ModelItem mi: reportDef.getModelItems()){
			ReportItem ri = new ReportItem();
			ri.setModelDto(new ModelDto(modelService.getModel(mi.getModelId()), null));
			ri.setItemProperties(itemService.getPropertiesForItemAndModel(mi.getItemId(), mi.getModelId()));
			items.add(ri);
		}
		reportStructure.setReportItems(items);
		
		List<Interval> intervals = new ArrayList<Interval>();
		for(int intervalId: reportDef.getIntervalIds()){
			intervals.add(referenceService.getInterval(intervalId));
		}
		reportStructure.setIntervals(intervals);
		
		List<Company> companies = new ArrayList<Company>();
		for(int companyId: reportDef.getCompanyIds()){
			companies.add(referenceService.getCompany(companyId));
		}
		reportStructure.setCompanies(companies);
		
        List<RunTag> runTags = reportDef.getRunTags();
		reportStructure.setRunTags(runTags);
		return reportStructure;
	}

}