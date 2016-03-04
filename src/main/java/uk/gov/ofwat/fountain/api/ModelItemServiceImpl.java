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

import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ModelPropertiesMapDao;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.Pot;

public class ModelItemServiceImpl implements ModelItemService {
	private ModelPropertiesMapDao modelPropertiesMapDao;
	private ItemPropertiesDao itemPropertiesDao; 
	private ItemService itemService;
	private ModelService modelService;
	
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public ModelPropertiesMapDao getModelPropertiesMapDao() {
		return modelPropertiesMapDao;
	}

	public ItemPropertiesDao getItemPropertiesDao() {
		return itemPropertiesDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public void setModelPropertiesMapDao(ModelPropertiesMapDao modelPropertiesMapDao) {
		this.modelPropertiesMapDao = modelPropertiesMapDao;
	}
	
	public List<ModelItem> searchForItem(String criteria, int[] modelFilters) {
		return modelPropertiesMapDao.searchByCodeOrDescription(criteria, modelFilters);
	}

	public List<ModelItem> searchForItemViaDefinition(String criteria,
			int[] modelFilters) {
		return modelPropertiesMapDao.searchByLatestDefinitions(criteria, modelFilters);
	}

	public ModelItem getModelItem(Pot pot) {
		return getModelItem(pot, pot.getModel());
	}
	
	public ModelItem getModelItem(Pot pot, Model parentModel) {
		ModelItem mi = new ModelItem();
		mi.setItemCode(pot.getItem().getCode());
		mi.setItemId(pot.getItem().getId());
		mi.setItemName(pot.getItem().getName());
		mi.setGroup(pot.getItem().getGroup().getName());
		mi.setTeamName(pot.getItem().getTeam().getName());
		mi.setModelCode(parentModel.getCode());
		mi.setModelId(parentModel.getId());
		mi.setModelName(parentModel.getName());
		if(parentModel.getId() == pot.getModel().getId()){
			mi.setModelPropertiesMapId(pot.getModelPropertiesMap().getId());
		}
		else{
			mi.setModelPropertiesMapId(modelPropertiesMapDao.findByModelAndItem(parentModel.getId(), mi.getItemId()).getId());
		}
		ItemProperties itemProperties = itemPropertiesDao.findByItemAndModel(pot.getItem().getId(), parentModel.getId());		
		mi.setDescription(itemProperties.getDescription());
		return mi;
	}
	
	public ModelItem getModelItem(Item item, Model model) {
		ModelPropertiesMap mpm = modelPropertiesMapDao.findByModelAndItem(model.getId(), item.getId());
		if (null == mpm) {
			return null;
		}
		return getModelItem(item, model, mpm);
	}

	public ModelItem getModelItem(Item item, Model model, ModelPropertiesMap mpm) {
		ModelItem mi = new ModelItem();
		mi.setItemCode(item.getCode());
		mi.setItemId(item.getId());
		mi.setItemName(item.getName());
		mi.setGroup(item.getGroup().getName());
		mi.setTeamName(item.getTeam().getName());
		
		mi.setModelCode(model.getCode());
		mi.setModelId(model.getId());
		mi.setModelName(model.getName());
		mi.setModelPropertiesMapId(mpm.getId());

		ItemProperties itemProperties = itemPropertiesDao.findByItemAndModel(item.getId(), model.getId());		
		mi.setDescription(itemProperties.getDescription());
		return mi;
	}

	public ModelPropertiesMap createModelPropertiesMap(Model model, ItemProperties props) {
		ModelPropertiesMap mpm = new ModelPropertiesMap();
		mpm.setItemId(props.getItem().getId());
		mpm.setItemCode(props.getItem().getCode());
		mpm.setItemPropertiesId(props.getId());
		mpm.setModelId(model.getId());
		int id = modelPropertiesMapDao.create(mpm);
		mpm.setId(id);
		return mpm;
	}

}
