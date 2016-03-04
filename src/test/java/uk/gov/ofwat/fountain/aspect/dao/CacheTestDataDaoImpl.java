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

package uk.gov.ofwat.fountain.aspect.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Data;

public class CacheTestDataDaoImpl implements CacheTestDataDao {

	private Data data;

	public void setData(Data data) {
		System.out.println("TEST: " + this.getClass().getCanonicalName()
				+ ":setData");
		this.data = data;

		System.out.println("TEST: Done");
	}

	public Data findById(int id) {
		return this.data;
	}

	public List<Data> findByTableId(int tableId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemDao getItemDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntervalDao getYearDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		// TODO Auto-generated method stub

	}

	public void setItemDao(ItemDao itemDao) {
		// TODO Auto-generated method stub

	}

	public int create(Data data, Audit audit) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Data getBranchData(int itemId, int yearId, int companyId,
			int groupEntryId, int branchId) {
		return this.data;
	}

	public List<Data> getAllBranchData(int branchId, int companyId) {
		return null;
	}

	public List<Data> getDataForAudit(Audit audit) {
		return null;
	}

	public Data getLatestData(int itemId, int yearId, int companyId,
			int groupEntryId) {
		return this.data;
	}

	public void updateValue(Data data, Audit audit) {
		// TODO Auto-generated method stub
	}

	public void bulkUpdate(List<Data> list, Audit audit) {
		// TODO Auto-generated method stub

	}

	@Override
	public Data getDataForValueId(int valueId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFromCacheForValueId(int valueId) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Data> getReportData(List<Integer> branchIds,
			List<Integer> companyIds, List<Integer> itemIds,
			List<Integer> intervalIds, List<Integer> groupEntryIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToCache(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean isDataInCache(String key) {
		return null;
	}

	@Override
	public List<Data> getDataForValueIds(ArrayList<Integer> valueIdList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Data getTaggedData(int itemId, int yearId, int companyId,
			int groupEntryId, int runId, long tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addToTaggedCache(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Data> getTagDataForBranch(int branchId, int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
