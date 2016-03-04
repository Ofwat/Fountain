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

import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.Pot;

public interface ModelItemService {
	
	
	public List<ModelItem> searchForItem(String criteria, int[] modelFilters);
	
	
	public List<ModelItem> searchForItemViaDefinition(String criteria, int[] modelFilters);
	
	/** 
	 * get the appropriate modelItem for this pot. Do not use this for reports based on
	 * models that are in families unless they are the parent.
	 * @param pot
	 * @return
	 */
	public ModelItem getModelItem(Pot pot);
	
	/**
	 * Get the appropriate model item for the model which is the parent
	 * of the model owning the pot. For example - creating a report based against a table in
	 * an ICS should get item properties from the foundation model.
	 * @param pot
	 * @param parentModel
	 * @return
	 */
	public ModelItem getModelItem(Pot pot, Model parentModel);

	public ModelItem getModelItem(Item item, Model model);
	public ModelItem getModelItem(Item item, Model model, ModelPropertiesMap mpm);
	public ModelPropertiesMap createModelPropertiesMap(Model model, ItemProperties props);
}
