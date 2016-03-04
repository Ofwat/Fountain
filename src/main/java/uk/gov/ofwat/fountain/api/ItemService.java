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

import java.util.HashMap;
import java.util.List;

import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ListItem;

public interface ItemService {


	public ItemProperties getPropertiesForItemAndModel(int itemId, int modelId);

	public ItemProperties getPropertiesForItemAndModel(String itemCode, int modelId);
	
	public HashMap<Integer, ItemProperties> getPropertiesForModel(int modelId);
	
	/**
	 * straightforward find by id
	 * @param id
	 * @return
	 */
	public Item getItem(int id);
	
	/**
	 * straightforward find by code
	 * @param code
	 * @return
	 */
	public Item getItem(String code);
	
	
	/**
	 * Search for items by entering the first part of the code
	 * @param partialCode e.g. Bon1 
	 * @return
	 */
	public List<Item> searchForItem(String criteria);

	public List<Item> searchForItem(String criteria, int[] modelFilters);
	
	public List<Item> searchForItemViaDefinition(String criteria);

	public List<Item> searchForItemViaDefinition(String criteria, int[] modelFilters);

	public List<ItemProperties> getAllPropertiesForItem(int itemId);
	
	public ItemProperties getLatestPropertiesForItem(int itemId);
	
	/**
	 * Set ownership of the item by a team (role)
	 * 
	 * Use TeamService to obtain list of teams 
	 * @param itemId
	 * @param teamId
	 */
	public void setTeamOnItem(int itemId, int teamId);
	
	/**
	 * returns all of the possible values for a fixed list item
	 * (engineering code)
	 * 
	 * @param codeList
	 * @return
	 */
	public List<ListItem> getItemsForCodeList(CodeList codeList);
	
	/**
	 * return the value associated with a list item id.
	 * @param id
	 * @return
	 */
	public String getCodeListValue(int id);

	public CodeList getCodeList(String code);

	int createItem(Item item);
	int createItemProperties(ItemProperties itemProperties);

	public ItemProperties getPropertiesForItem(Item item, int version);

}
