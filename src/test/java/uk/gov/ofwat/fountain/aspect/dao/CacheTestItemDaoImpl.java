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

import java.util.List;

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateListener;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Item;

public class CacheTestItemDaoImpl implements ItemDao{

	
	public int create(Item item) {
		System.out.println("CacheTestItemDaoImpl create");
		return 0;
	}

	public Item findByCode(String code) {
		System.out.println("CacheTestItemDaoImpl findByCode");
		return null;
	}

	public Item findById(int id) {
		System.out.println("CacheTestItemDaoImpl findById");
		return null;
	}

	public List<Item> getItemsByPosition(int startRec, int noOfRecs) {
		System.out.println("CacheTestItemDaoImpl getItemsByPosition");
		return null;
	}

	public List<Item> searchByCodeOrDescription(String criteria,
			int[] modelFilters) {
		System.out.println("CacheTestItemDaoImpl searchByCodeOrDescription");
		return null;
	}

	public List<Item> searchByCodeOrDescription(String criteria) {
		System.out.println("CacheTestItemDaoImpl searchByCodeOrDescription");
		return null;
	}

	public List<Item> searchByLastestDefinitions(String criteria,
			int[] modelFilters) {
		System.out.println("CacheTestItemDaoImpl searchByLastestDefinitions");
		return null;
	}

	public List<Item> searchByLastestDefinitions(String criteria) {
		System.out.println("CacheTestItemDaoImpl searchByLastestDefinitions");
		return null;
	}

	public void update(Item item) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":update");
		System.out.println("CacheTestItemDaoImpl update");
		
	
		System.out.println("TEST: Done");
}

	public void addItemUpdateListener(UpdateListener listener) {
		System.out.println("CacheTestItemDaoImpl addItemUpdateListener");
		
	}

	@Override
	public List<Item> findByTableId(int tableId) {
		// TODO Implement test
		System.out.println("CacheTestItemDaoImpl findByTableId");
		return null;
	}

	@Override
	public List<Item> findAll() {
		// TODO Implement test
		System.out.println("CacheTestItemDaoImpl findAll");
		return null;
	}
	
	

}
