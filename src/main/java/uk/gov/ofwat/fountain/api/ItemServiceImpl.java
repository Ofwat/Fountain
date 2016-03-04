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

import uk.gov.ofwat.fountain.dao.CodeListDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ItemPropertyIntervalDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.dao.TeamDao;
import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;
import uk.gov.ofwat.fountain.domain.ListItem;
import uk.gov.ofwat.fountain.domain.Team;

/**
 * Service class for all operations relating to Items and Item properties.
 */
public class ItemServiceImpl implements ItemService {
	
	private ItemPropertiesDao itemPropertiesDao;
	private ItemDao itemDao;
	private ModelPropertiesMapDao modelPropertiesMapDao;
	private ItemPropertyIntervalDao itemPropertyIntervalDao;
	private TeamDao teamDao;
	private CodeListDao codeListDao;
	
	public void setCodeListDao(CodeListDao codeListDao) {
		this.codeListDao = codeListDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public void setModelPropertiesMapDao(ModelPropertiesMapDao modelPropertiesMapDao) {
		this.modelPropertiesMapDao = modelPropertiesMapDao;
	}
	
	public void setItemPropertyIntervalDao(
			ItemPropertyIntervalDao itemPropertyIntervalDao) {
		this.itemPropertyIntervalDao = itemPropertyIntervalDao;
	}


	public void setTeamDao(TeamDao teamDao) {
		this.teamDao = teamDao;
	}




	public ItemProperties getPropertiesForItemAndModel(int itemId, int modelId){
		
		ItemProperties ip = itemPropertiesDao.findByItemAndModel(itemId, modelId);
		if (null == ip) {
			return null;
		}
		applyItemPropertyIntervals(ip);
		return ip;
	}

	private void applyItemPropertyIntervals(ItemProperties ip) {
		List<ItemPropertyInterval> ipis = itemPropertyIntervalDao.getIntervalPropertiesForItemPropertyId(ip.getId());
		for(ItemPropertyInterval ipi: ipis){
			ip.getFormulae().put(ipi.getInterval(), ipi);
			ipi.setItemProperties(ip);
		}
	}

	public ItemProperties getPropertiesForItemAndModel(String itemCode, int modelId){
		
		ItemProperties ip = itemPropertiesDao.findByItemAndModel(itemCode, modelId);
		applyItemPropertyIntervals(ip);
		return ip;
	}
	
	public HashMap<Integer, ItemProperties> getPropertiesForModel(int modelId) {
		return modelPropertiesMapDao.getAllForModel(modelId);
	}
	
	public Item getItem(int id){
		return itemDao.findById(id);
	}

	public Item getItem(String code) {
		return itemDao.findByCode(code);
	}

	public List<Item> searchForItem(String criteria) {
		return itemDao.searchByCodeOrDescription(criteria);
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.api.ItemService#searchForItemViaDEfinition(java.lang.String)
	 */
	public List<Item> searchForItemViaDefinition(String criteria) {
		return itemDao.searchByLastestDefinitions(criteria);
	}

	public List<Item> searchForItem(String criteria, int[] modelFilters) {
		return itemDao.searchByCodeOrDescription(criteria, modelFilters);
	}

	public List<Item> searchForItemViaDefinition(String criteria,
			int[] modelFilters) {
		return itemDao.searchByLastestDefinitions(criteria, modelFilters);
	}


	public List<ItemProperties> getAllPropertiesForItem(int itemId) {
		return itemPropertiesDao.getAllForItemId(itemId);
	}
	
	
	
	public void setTeamOnItem(int itemId, int teamId) {
		Item item = itemDao.findById(itemId);
		Team team = teamDao.findById(teamId);
		item.setTeam(team);
		itemDao.update(item);
	}
	
	

	public List<ListItem> getItemsForCodeList(CodeList codeList) {
		return codeListDao.getAllItemsForCodeList(codeList);
	}

	
	
	public String getCodeListValue(int id) {
		return codeListDao.getListItemById(id).getCode();
	}

	public CodeList getCodeList(String code) {
		return (CodeList)codeListDao.findByCode(code);
	}
	
	public int createItem(Item item) {
		int id = itemDao.create(item);
		return id;
	}

	public int createItemProperties(ItemProperties itemProperties) {
		List<ItemProperties> listOfItemProperties = itemPropertiesDao.getAllForItemId(itemProperties.getItem().getId());
		if (listOfItemProperties.isEmpty()) {
			// A new item with no item properties yet
			return itemPropertiesDao.create(itemProperties);
		}

		int itemPropsId = itemPropertiesDao.create(itemProperties);
		Item item = itemProperties.getItem();
		itemDao.update(item); // remove from cache so that the next get will use the newly created itemproperties.
		return itemPropsId;
	}

	@Override
	public ItemProperties getLatestPropertiesForItem(int itemId) {
		List<ItemProperties> ips = itemPropertiesDao.getAllForItemId(itemId);
		ItemProperties latest=null;
		for (ItemProperties ip: ips) {
			if (null == latest) {
				latest = ip;
				continue;
			}
			if (ip.getVersion() > latest.getVersion()) {
				latest = ip;
			}
		}
		applyItemPropertyIntervals(latest);
		return latest;
	}

	public ItemProperties getPropertiesForItem(Item item, int version) {
		return itemPropertiesDao.findByItemCodeAndVersion(item.getCode(), version);
	}

}
