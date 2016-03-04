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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import uk.gov.ofwat.fountain.dao.CodeListDao;
import uk.gov.ofwat.fountain.dao.GroupDao;
import uk.gov.ofwat.fountain.dao.InflationTypeDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ItemPropertyIntervalDao;
import uk.gov.ofwat.fountain.dao.PriceBaseDao;
import uk.gov.ofwat.fountain.dao.PurposeDao;
import uk.gov.ofwat.fountain.domain.Group;
import uk.gov.ofwat.fountain.domain.InflationType;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;
import uk.gov.ofwat.fountain.domain.PriceBase;
import uk.gov.ofwat.fountain.domain.Purpose;
import uk.gov.ofwat.fountain.tools.InvalidDocumentException;
import uk.gov.ofwat.version1.DictionaryDocument.Dictionary;
import uk.gov.ofwat.version1.ItemPropertiesDocument;
import uk.gov.ofwat.version1.ItemPropertiesListDocument.ItemPropertiesList;
import uk.gov.ofwat.version1.TextBlockDocument.TextBlock;
import uk.gov.ofwat.version1.TextDocument.Text;
import uk.gov.ofwat.version2.ItemPropertyIntervalType;
import uk.gov.ofwat.version2.ItemPropertyType;
import uk.gov.ofwat.version2.ItemType;

/**
 * Import dictionary items from the reservoir files
 */
public class ReservoirDictionaryItemsImporter {
	private static final Log log = LogFactory.getLog(ReservoirDictionaryItemsImporter.class);
	private PriceBaseDao priceBaseDao;
	private InflationTypeDao inflationTypeDao;
	private PurposeDao purposeDao;
	private GroupDao groupDao;
	private ItemDao itemDao;
	private ItemPropertiesDao itemPropertiesDao;
	private ItemPropertyIntervalDao itemPropertyIntervalDao;
	
	public void setPriceBaseDao(PriceBaseDao priceBaseDao) {
		this.priceBaseDao = priceBaseDao;
	}

	public void setInflationTypeDao(InflationTypeDao inflationTypeDao) {
		this.inflationTypeDao = inflationTypeDao;
	}

	public void setPurposeDao(PurposeDao purposeDao) {
		this.purposeDao = purposeDao;
	}


	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public void setItemPropertyIntervalDao(
			ItemPropertyIntervalDao itemPropertyIntervalDao) {
		this.itemPropertyIntervalDao = itemPropertyIntervalDao;
	}

	/**
	 * Save dictionary items (from the XML document) to the database, saving the 
	 * properties according to the following rules:
	 * <ul>
	 * <li>If the item does not yet exist, then it is created along with the 
	 * item properties. This means that the fountain and reservoir version numbers
	 * will be in sync.</li>
	 * <li>If the item does exist and already has properties then we cannot
	 * guarantee that the reservoir and fountain versions will be in sync. We
	 * therefore save the properties against the next available FOUNTAIN number,
	 * storing a reference to the reservoir number.</li>
	 * </ul>
	 * 
	 * <pre>
	 * If the database contains the following properties for B101:
	 * Code   | Fountain | Reservoir
	 *        | vers no  | vers no
	 * -------+----------+---------
	 * B101   | 1        | 1
	 * B101   | 2        | 2
	 * B101   | 3        | 2
	 * B101   | 4        | 2
	 * 			
	 * then adding a new B102 and a reservoir version 3 would mean:
	 * B101   | 1        | 1
	 * B101   | 2        | 2
	 * B101   | 3        | 2
	 * B101   | 4        | 2
	 * B101   | 5        | 3
	 * B102   | 1        | 1
	 * </pre>
	 * 
	 * <strong>The system is not currently set up to amend existing dictionary items.</strong>
	 * 
	 * @param item
	 */
	public DictionaryImportResults importDictionary(Dictionary dictionary) throws InvalidDocumentException {
	
		log.info("Importing Reservoir style dictionary");
		List<String> errors = new ArrayList<String>();
		Lookups lookups = new Lookups();

		// put the texts into a hashmap
		Map<String, Text> textMap = new HashMap<String, Text>();
		for (Text text : dictionary.getTexts().getTextArray()) {
			textMap.put(text.getCode(), text);
		}

		int total = dictionary.getItems().getItemArray().length;
		int count = 0;
		int c = 0;
		for (uk.gov.ofwat.version1.ItemDocument.Item dictionaryItem : dictionary.getItems().getItemArray()) {
			count++;
			c++;
			if (c>=1000 || count==1) {
				log.info("Importing " + count + " of " + total + " into dictionary");
				if (count>1) c = 0;
			}
			
			// create a new shape dictionary item - and save it
			ItemType item = ItemType.Factory.newInstance();
			item.setCode(dictionaryItem.getCode());
			item.setGroup(dictionaryItem.getGroupTypeCode());
			TextBlock[] definitionList = null;
			if(dictionaryItem.getTextCode()!=null){
				Text text = textMap.get(dictionaryItem.getTextCode());
				if (text==null) {
					errors.add("Item " + dictionaryItem.getCode() + " references non-existent text code " + dictionaryItem.getTextCode());
				}
				else {
					// List of definitions for this text code
					definitionList =  text.getTextBlocks().getTextBlockArray();
				}
			}
			HashMap<String, String> dpMap = new HashMap<String, String>();
			ItemPropertiesList iplist = dictionaryItem.getItemPropertiesList();
			String unit = new String();
			List<ItemPropertyType> propsList = new ArrayList<ItemPropertyType>();
			for (ItemPropertiesDocument.ItemProperties ip : iplist.getItemPropertiesArray()) {
				ItemPropertyType itemProps = ItemPropertyType.Factory.newInstance();
				
				itemProps.setVersion(ip.getVersionNumber());
				itemProps.setDescription(ip.getDescription());
				unit = ip.getUnitCode();
				dpMap.put(ip.getVersionNumber(), ip.getDecimalPlaces());
				itemProps.setDecimalPlaces(ip.getDecimalPlaces());
				itemProps.setPriceBase(ip.getPriceBaseCode());
				itemProps.setPurpose(ip.getPurposeCode());
				itemProps.setInflationType(ip.getInflationTypeCode());
				itemProps.setGroupTotalFormula(ip.getGroupTotalEquation());
				propsList.add(itemProps);
				
			}

			item.setUnit(unit);
		
			// save the item properties
			uk.gov.ofwat.version2.ItemType.ItemProperties newList = item.addNewItemProperties();

			for (ItemPropertyType itemProperty : propsList) {
				if (definitionList!=null && definitionList.length > 0){
					for (int i = definitionList.length; i >= 0; i--) {
						TextBlock definition = (TextBlock)definitionList[i-1];
						if (definition.getTextTypeCode().equals("DEF")) {
							ItemPropertyType ip = newList.addNewItemProperty();
							populateItemProperty(ip, itemProperty, definition.getData());
							break;
						}	
					}
				} else {
					ItemPropertyType ip = newList.addNewItemProperty();
					populateItemProperty(ip, itemProperty, itemProperty.getDefinition());
				
				}
			}

			saveDictionaryItem(lookups, errors, item);
		}
		log.info("Imported " + total + " items");

		DictionaryImportResults results = new DictionaryImportResults();
		results.setErrors(errors);
		return results;
	}


	private void populateItemProperty(ItemPropertyType ip, ItemPropertyType itemProperty, String definition) {
		ip.setDescription(itemProperty.getDescription());
		ip.setDefinition(definition);
		ip.setPriceBase(itemProperty.getPriceBase());
		ip.setPurpose(itemProperty.getPurpose());
		ip.setInflationType(itemProperty.getInflationType());
		ip.setVersion(itemProperty.getVersion());
		ip.setDecimalPlaces(itemProperty.getDecimalPlaces());
		ip.setGroupTotalFormula(itemProperty.getGroupTotalFormula());
	}

	private int saveDictionaryItem(Lookups lookups, List<String> errors, ItemType dictionaryItem) {
		
		uk.gov.ofwat.fountain.domain.Item item = new uk.gov.ofwat.fountain.domain.Item();
		item.setCode(decode(dictionaryItem.getCode()));
		item.setName(decode(dictionaryItem.getName()));
		item.setUnit(decode(dictionaryItem.getUnit()));
		
		Group group = null;
		try {
			group = groupDao.findByName(dictionaryItem.getGroup()==null?"NONE":dictionaryItem.getGroup());
		}
		catch (EmptyResultDataAccessException ex) {
			// Group does not exist so create it
			group = new Group();
			group.setName(dictionaryItem.getGroup());
			int id = groupDao.create(group);
			group.setId(id);
			log.info("Created group: id=" + id + ", name=" + group.getName());
		}
		
		item.setGroup(group);
		ItemPropertyType ips[] = dictionaryItem.getItemProperties().getItemPropertyArray();
		item.setLatestDescription(decode(ips[ips.length-1].getDescription().toString()));
		//TODO We should get the list-code at this point and add it to the item. It is de facto optional in the dictionary. However it needs to be added to the dictionary xsd and the xml beans regenerated.

		// Get the item - creating it if it's new - and build a reference of any
		// existing item properties
		uk.gov.ofwat.fountain.domain.Item i = itemDao.findByCode(item.getCode());
		if (i==null) {
			// Item does not exist so create it
			int id = itemDao.create(item);
			item.setId(id);
			addNewProperties(lookups, item, ips);
		}
		else {
			// Item exists so read it (and its properties)
			int id = i.getId();
			item.setId(id);
			addToExistingProperties(lookups, item, ips);
		}
		
		return item.getId();
	}

	private class Lookups {
		
		private Map<String, PriceBase> pbMap;
		private Map<String, Purpose> pMap;
		private Map<String, InflationType> itMap;
		
		public PriceBase getPriceBase(String code) {
			if (code==null) return null;
			if (pbMap==null) pbMap= new HashMap<String, PriceBase>();
			if (pbMap.containsKey(code)) return pbMap.get(code);
			try {
				PriceBase pb = priceBaseDao.findByCode(code);
				pbMap.put(code, pb);
				return pb;
			}
			catch (EmptyResultDataAccessException ex) {
				throw new InvalidDocumentException("Couldn't resolve price base " + code);
			}
		}
	
		public Purpose getPurpose(String code) {
			if (code==null) return null;
			if (pMap==null) pMap= new HashMap<String, Purpose>();
			if (pMap.containsKey(code)) return pMap.get(code);
			try {
				Purpose p = purposeDao.findByCode(code);
				pMap.put(code, p);
				return p;
			}
			catch (EmptyResultDataAccessException ex) {
				throw new RuntimeException("Couldn't resolve purpose " + code);
			}
		}
	
		public InflationType getInflationType(String code) {
			if (code==null) return null;
			if (itMap==null) itMap= new HashMap<String, InflationType>();
			if (itMap.containsKey(code)) return itMap.get(code);
			try {
				InflationType it = inflationTypeDao.findByCode(code);
				itMap.put(code, it);
				return it;
			}
			catch (EmptyResultDataAccessException ex) {
				throw new RuntimeException("Couldn't resolve inflation type " + code);
			}
		}	
	}
	
	//TODO - remove this when encoding on resouces if fixed
	private String decode(String original){
		return null == original? original: original.replaceAll("~~~~pound~~~~", "£");
	}
	
	/**
	 * Create the properties for a new item. All of these will match the reservoir version.
	 */
	private void addNewProperties(Lookups lookups, Item item, ItemPropertyType ips[]) {
		for (ItemPropertyType ip : ips) {
			// Create a new item properties that has no intervals, has the same version as
			// the reservoir entry and is not yet attached to any model
			ItemProperties itemProps = 
				new ItemProperties(item, 
		   	 					   new Integer(ip.getVersion()), 
								   decode(ip.getDescription()),
								   decode(ip.getDefinition()), 
								   new Integer(null != ip.getDecimalPlaces() ? ip.getDecimalPlaces(): "0"), 
								   null, 					// No intervals to start  
								   false, 
								   ip.getGeneralFormula(),
								   ip.getGroupTotalFormula(), 
								   new Integer(ip.getVersion()), false);
			
			itemProps.setPurpose(lookups.getPurpose(decode(ip.getPurpose())));
			itemProps.setInflationType(lookups.getInflationType(decode(ip.getInflationType())));
			itemProps.setPriceBase(lookups.getPriceBase(decode(ip.getPriceBase())));
			int id = itemPropertiesDao.create(itemProps);
			itemProps.setId(id);
			addIntervals(ip, itemProps);
		}
		
	}

	/**
	 * Take the properties and add them to the existing item and properties
	 */
	private void addToExistingProperties(Lookups lookups, Item item, ItemPropertyType ips[]) {
		// Read the current properties
		List<ItemProperties> existingIps = itemPropertiesDao.getAllForItemId(item.getId());
		
		// Build a set of existing reservoir versions and find the largest fountain version
		int maxVersion = 0;
		Set<String> existingReservoirVersions = new HashSet<String>();
		for (ItemProperties eip : existingIps) {
			if (eip.getVersion()>maxVersion) {
				maxVersion = eip.getVersion();
			}
			existingReservoirVersions.add("" + eip.getReservoirVersion());
		}
		
		for (ItemPropertyType ip : ips) {
			if (existingReservoirVersions.contains(ip.getVersion())) {
				// This reservoir version has already been imported. We
				// are not currently updating the dictionary with new details
				// of existing item properties. 
				// TODO Is this correct approach? If not, we will need to implement
				// an update function here.
			}
			else {
				// This reservoir version doesn't exist yet so create it:
				// * The fountain version is just the next available number
				// * The reservoir version is the actual number in the XML file
				// * The new properties are imported showing that no model is attached
				maxVersion++;
				ItemProperties itemProps =
					new ItemProperties(item, 
			   	 					   maxVersion, 
									   decode(ip.getDescription()),
									   decode(ip.getDefinition()), 
									   new Integer(null != ip.getDecimalPlaces() ? ip.getDecimalPlaces(): "0"), 
									   null, 					// No intervals to start  
									   false, 
									   ip.getGeneralFormula(),
									   ip.getGroupTotalFormula(), 
									   new Integer(ip.getVersion()), false);
				
				itemProps.setPurpose(lookups.getPurpose(decode(ip.getPurpose())));
				itemProps.setInflationType(lookups.getInflationType(decode(ip.getInflationType())));
				itemProps.setPriceBase(lookups.getPriceBase(decode(ip.getPriceBase())));
				int id = itemPropertiesDao.create(itemProps);
				itemProps.setId(id);
				addIntervals(ip, itemProps);
			}
		}
	}

	private void addIntervals(ItemPropertyType ip, ItemProperties itemProps) {
		if (ip.getItemPropertyIntervals()==null) return;
		
		for (ItemPropertyIntervalType ipi : ip.getItemPropertyIntervals().getItemPropertyIntervalArray()) {
			ItemPropertyInterval itemPropInterval = new ItemPropertyInterval();
			itemPropInterval.setDescription(decode(ipi.getDescription()));
			itemPropInterval.setFormula(decode(ipi.getFormula()));
			
			Interval interval = new Interval();
			interval.setName(ipi.getInterval().toString());
			itemPropInterval.setInterval(interval);
			itemPropInterval.setItemProperties(itemProps);
			itemPropertyIntervalDao.create(itemPropInterval);
		}
	}

	
	




	


}
