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

import java.util.List;

import uk.gov.ofwat.fountain.aspect.cache.dao.UpdateBroadcaster;
import uk.gov.ofwat.fountain.domain.Item;

public interface ItemDao extends  CachableCodedDao, CachableEntityDao, UpdateBroadcaster {
	
	public int create(Item item);

	public Item findById(int id);

	/**
	 * @return provides an {@link Item} if the code is available in the database. Otherwise returns null; 
	 */
	public Item findByCode(String code);
	
	public void update(Item item);
	
	public List<Item> getItemsByPosition(int startRec, int noOfRecs);
	
	/**
	 * 
	 * Search function that matches the beginning part of the item code or any part of the
	 * latest item description
	 * @param criteria - string to match
	 * @return list of matches
	 */
	public List<Item> searchByCodeOrDescription(String criteria);
	
	
	/**
	 * Alternative search function that trawls the latest item properties definitions
	 * @param criteria
	 * @return
	 */
	public List<Item> searchByLastestDefinitions(String criteria);

	/**
	 * @param criteria
	 * @param modelFilters
	 * @return
	 */
	public List<Item> searchByCodeOrDescription(String criteria,
			int[] modelFilters);

	/**
	 * @param criteria
	 * @param modelFilters
	 * @return
	 */
	public List<Item> searchByLastestDefinitions(String criteria,
			int[] modelFilters); 
	
	public List<Item> findByTableId(int tableId);
	
	public List<Item> findAll();

}
