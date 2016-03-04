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
import java.util.Map;

import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.User;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.exception.ReportTooLargeException;

public interface DataService {

	Data getDataForPot(Pot pot, Company company, GroupEntry groupEntry, boolean readOnly);
	
	List<Audit> saveUserChanges(Basket basket) throws BranchNotEditableException;

	Data createData(Pot pot, Company company, GroupEntry groupEntry, String value, String confidenceGrade, boolean calculatedValue);

	boolean completeModel(String tagName, int runId, int companyId, int modelId, String name, boolean tagOnCompletion);

	void freeDataTag(String tagDisplayName, int runId, String name, User user) throws TagAllreadyExistsException;

	List<Audit> saveUserChanges(Basket basket, boolean newBranch)
			throws BranchNotEditableException;

	List<Data> getReportData(List<Integer> branchIds, List<Integer> companyIds, List<Integer> itemIds, List<Integer> intervalIds, List<Integer> groupEntryIds);

	Map<String, Object> getTaggedData(List<RunTag> runTags, List<Integer> companyIds, List<Integer> intervalIds, List<Integer> itemIds);

	void addToCache(Map<String, Object> dataMap);

	void addToTaggedCache(Map<String, Object> dataMap);

	Boolean isDataInCache(String key);

	List<Data> getTaggedDataForRun(int sourceRunId, int companyId, int tagId);

	List<Audit> saveNewRunBasket(Basket basket) throws BranchNotEditableException;

}
