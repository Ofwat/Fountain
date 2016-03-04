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

import java.util.HashMap;
import java.util.List;

import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;

public class CacheTestModelPropertiesMapDaoImpl implements CacheTestModelPropertiesMapDao {

	private ModelPropertiesMap modelPropertiesMap;

	public void setModelPropertiesMap(ModelPropertiesMap modelPropertiesMap) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setModelPropertiesMap");
		this.modelPropertiesMap = modelPropertiesMap;
	
		System.out.println("TEST: Done");
}
	
	public ModelPropertiesMap findById(int id) {
		return this.modelPropertiesMap;
	}

	public void delete(int id) {
		System.out.println("CacheTestDaoImpl.delete()");
	}
	
	public void update(ModelPropertiesMap modelPropertiesMap){
		System.out.println("CacheTestDaoImpl.update()");
	}

	public int create(ModelPropertiesMap modelPropertiesMap, int TableId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ModelPropertiesMap> findByTableId(int tableId) {
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

	public int create(ModelPropertiesMap modelPropertiesMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ModelPropertiesMap findByModelAndItem(int modelId, int itemId) {
		return this.modelPropertiesMap;
	}

	public HashMap<Integer, ItemProperties> getAllForModel(int modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModelItem> searchByCodeOrDescription(String criteria,
			int[] modelFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ModelItem> searchByLatestDefinitions(String criteria,
			int[] modelFilters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 
}
