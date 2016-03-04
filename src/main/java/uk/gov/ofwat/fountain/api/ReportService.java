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

package uk.gov.ofwat.fountain.api;

import java.util.List;

import javax.ws.rs.core.SecurityContext;

import uk.gov.ofwat.fountain.api.ReportServiceImpl.ReportCommitResult;
import uk.gov.ofwat.fountain.api.report.ReportStructure;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.Report;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.rest.dto.BulkDeleteReportDto;
import uk.gov.ofwat.fountain.rest.dto.BulkModifyReportDto;
import uk.gov.ofwat.fountain.rest.dto.CellDto;
import uk.gov.ofwat.fountain.rest.dto.DataDto;
import uk.gov.ofwat.fountain.rest.dto.ReportDto;
import uk.gov.ofwat.fountain.rest.dto.RunTagIdsDto;
import uk.gov.ofwat.fountain.rest.dto.TableDto;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

public interface ReportService {
	
	public int saveReport(final String name, 
			               final User user, 
			               final Team team,
			               final List<ModelItem> modelItems, 
			               final String group,
			               final List<Integer>intervalIds,
			               final List<String>layoutLeft,
			               final List<String>layoutTop, boolean privateReport, 
			               final List<Integer> companyIds,
			               final boolean displayCGs,	
			   			   final boolean displayUnit,
			   			   final boolean displayBoncode,
			   			   final boolean displayDesc,
			   			   final boolean displayModel,
			   			   final boolean displayCompanyName,
			   			   final boolean displayCompanyAcronym,
                           List<RunTagIdsDto> runTagIds,
                           final boolean displayRunName,
                           final boolean displayRunDesc,
                           final boolean displayTagDisplayName,
				   			final boolean displayAgendaName,
				   			final boolean displayAgendaCode,                           
				   			final boolean readOnly,
                           String description);
				   			
	
	public List<ReportSummary> getReportsForTeam(final Integer teamId);
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds);
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, boolean CompleteSummary);
	
	public List<ReportSummary> getReportsForTeam(final Integer teamId, Integer limit);
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, Integer limit);
	
	public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, boolean CompleteSummary, Integer limit);	

	/**
	 * return the executed report as a Report.
	 */
	public Report runReport(final int id, final User user, RoleChecker roleChecker);
	public Report runReport(int id, User user, RoleChecker roleChecker, int suppliedCompanyId);
	public Report runReport(int id, User user, RoleChecker roleChecker, int runId, int runModelTagId);
	public Report runReport(int id, User user, RoleChecker roleChecker, int suppliedCompanyId, int runId, int suppliedRunModelTagId); 

//	public ReportStructure makeReportStructure(int reportId);

	/**
	 * Delete a report, return if it's successful
	 * @param id
	 * @param user
	 * @return
	 */
	public void deleteReport(int id);

	public List<BulkDeleteReportDto> bulkDeleteReports(String ids, SecurityContext sc);
	
	public List<BulkModifyReportDto> bulkUpdateReportReadOnlyStatus(String ids, SecurityContext sc, boolean readOnly);
	
	public ReportSummary getSummaryById(int id);

	public ReportDefinition getReportDefinition(int id);
	
	public ReportStructure getReportStructure(int id, boolean showAllHeadings);
	public ReportStructure getReportStructure(int id);
	
	
	/**
	 * Publish report - allow it to be viewed by all users
	 * @param id
	 * @return
	 */
	public boolean publishReport(int id);
	
	/**
	 * "Unpublish" report - make it only visible to the team.
	 * @param id
	 * @return
	 */
	public boolean hideReport(int id);

	public int saveUpdatedReport(int reportId,
								String name, 
								User user, 
								Team team,
								List<ModelItem> modelItems, 
								String group,
								List<Integer> intervalIds,
								List<String> layoutLeft, 
								List<String> layoutTop,
								boolean publicReport, 
								List<Integer> companyIds,
								final boolean displayCGs,	
								final boolean displayUnit,
								final boolean displayBoncode,
								final boolean displayDesc,
								final boolean displayModel,
								final boolean displayCompanyName,
								final boolean displayCompanyAcronym,
                                List<RunTagIdsDto> runTagIds,
                                final boolean displayRunName,
                                final boolean displayRunDesc,
                                final boolean displayTagDisplayName,
    				   			final boolean displayAgendaName,
    				   			final boolean displayAgendaCode,
                                final boolean readOnly,
                                String description);
	
	public void buildReport(int reportId);
	public TableDto writeReportTable(ReportDto report);
	public TableDto writeReportTableStructure(ReportDto report);

	ReportCommitResult commitReportTableData(TableDto reportTableDto, String auditComment, User user, RoleChecker rc, Report report, int companyId);
	String checkHeaderCellValuesAreTheSame(TableDto serverReportTableDto, TableDto clientReportTableDto);
	void correctHeaderValues(TableDto serverReportTableDto, TableDto clientReportTableDto);
	void copyValuesForItemDescriptionAndUnit(TableDto serverReportTableDto, TableDto clientReportTableDto);
	void copyDataCellKeysAndFormating(TableDto serverReportTableDto, TableDto clientReportTableDto);
	void copyHeaderCellFormating(TableDto serverReportTableDto, TableDto clientReportTableDto);

	String checkReportTableEquality(TableDto clientReportTableDto, TableDto serverReportTableDto);

	public boolean isValidReportTable(int id);
	boolean valueHasChanged(CellDto cellDto, DataDto dataDto);

	boolean isAcronymColEmpty(TableDto reportTableDto);
	void removeCompanyAcronyms(TableDto reportTableDto);

	RunService getRunService();
	RunTagService getRunTagService();
	ReferenceService getReferenceService();
	GroupService getGroupService();
	ItemService getItemService();

	
	public void updateReportPopularity();
	
	public Boolean updateReportDescription(int id, String description);
	
	public ReportSummary getReportSummary(Integer reportId);
	
	public List<ReportStructure> getAllReportStructures();
	
	public List<ReportSearchWrapper> getAllReportSearchWrappers();
	
	public ReportSearchWrapper getReportSearchWrapper(Integer id);
	
	public Boolean indexAllReports();
	
	public boolean isReportReadOnly(Integer id);
	
}
