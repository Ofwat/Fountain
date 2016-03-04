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

package uk.gov.ofwat.fountain.dao;

import java.util.Calendar;
import java.util.List;

import uk.gov.ofwat.fountain.api.PopularityPeriod;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

/**
 * Not cachable
 */
public interface ReportDao extends CachableEntityDao {

	public int create(ReportDefinition reportDef);
	
	public List<ReportSummary> getReportsForTeam(Integer teamId, Integer limit);
	
	public List<ReportSummary> getReportsForTeams(List<Integer> teamIds, Integer limit);
	
	public List<ReportSummary> getReportsForTeam(Integer teamId);
	
	public List<ReportSummary> getReportsForTeams(List<Integer> teamIds);	
	
	public List<ReportSummary> getAll();
	
	public ReportDefinition findById(int id);
	
	public List<Integer> findReportCompaniesById(int id);

	public ReportSummary findSummaryById(int reportId);

	public void delete(int id);

	public void setWriteStatus(int id, boolean readOnly);
	
	/**
	 * Toggle between a public and team report
	 * If pass in true, will make it public, if false, will hide it
	 * @param toPublish
	 * @return
	 */
	public boolean updatePublishedStatus(boolean toPublish, int id);
	
	public void update(ReportDefinition reportDef);

	List<Integer> findReportRunsById(int id);
	
	public List<ReportSummary> findReportsByItemId(Integer itemId);
	
	public List<ReportSearchWrapper> getAllReportSearchWrappers();
	
	public ReportSearchWrapper getReportSearchWrapper(Integer id);
	
	
	
}
