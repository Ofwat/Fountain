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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.exception.ReportTooLargeException;

/**
 * Not cachable
 */
public interface DataDao extends EntityDao {

	public int create(Data data, Audit audit);
	
	/**
	 * Do a bulk update of all values in the list.
	 * The values are added on one go for the audit. 
	 */
	public void bulkUpdate(List<Data> list, Audit audit);
	
	public Data findById(int id);

	/**
	 * Gets the data for a branch for the given parameters. A lock is applied if the user has the correct role
	 * @param itemId
	 * @param yearId
	 * @param companyId
	 * @param branchId
	 * @return
	 */
	public Data getBranchData(int itemId, int yearId, int companyId, int groupEntryId, int branchId);
	
	
	/**
	 * 
	 * @param audit
	 * @return a list of all Data stored against this audit
	 */
	public List<Data> getDataForAudit(Audit audit);
	
	
	
	/**
	 * Creates a new value against the given data. Does NOT update the data table fields and does NOT create 
	 * the audit - do that first.
	 */
	public void updateValue(Data data, Audit audit);

//	public List<Data> getAllBranchData(int branchId, int companyId);

	public Data getDataForValueId(int valueId);
	public void removeFromCacheForValueId(int valueId);

	List<Data> getReportData(List<Integer> branchIds, List<	Integer> companyIds,
			List<Integer> itemIds, List<Integer> intervalIds,
			List<Integer> groupEntryIds);

	public Data getTaggedData(int itemId, int yearId, int companyId, int groupEntryId, int runId, long tagId);
	
	List<Data> getTagDataForBranch(int branchId, int tagId);

	void addToCache(Map<String, Object> dataMap);
	Boolean isDataInCache(String key);
	List<Data> getDataForValueIds(ArrayList<Integer> valueIdList);

	
	void addToTaggedCache(Map<String, Object> dataMap);
}
