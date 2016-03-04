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
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemPropertiesDao;
import uk.gov.ofwat.fountain.dao.ItemPropertyIntervalDao;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.ItemPropertyInterval;
import uk.gov.ofwat.fountain.tools.InvalidDocumentException;
import uk.gov.ofwat.model2.CellDocument.Cell;
import uk.gov.ofwat.model2.LineDocument.Line;
import uk.gov.ofwat.model2.ModelDocument.Model;
import uk.gov.ofwat.model2.SectionDocument.Section;

/**
 * <p>This class performs the dictionary mapping for Reservoir files that we need to 
 * import into Fountain.</p>
 * <p>The mapping is needed because Reservoir holds formulae in the models NOT in the
 * dictionary, whereas Fountain has merged this function.</p>
 * <p>To process, this class will read all the items and property version obtained
 * from a Reservoir model and for each item:</p>
 * 
 * <ul>
 * <li>Work out all possible items in the dictionary that it <i>might</i> match 
 * with (there could be >1 because previously imported models with different 
 * equations will create multiple properties). Any item properties having the same 
 * Reservoir version is considered a match.</li>
 * <li>Get the formulae attached to each item - using the cell formulae under each line in the model.</li>
 * <li>Map the items to the appropriate fountain properties so that:
 *   <ul>
 *   <li>If an {@link ItemProperties} is  not currently 'attached' to any model (i.e. where the 
 *   attachedToModel flag is false) - it is marked as attached and given the formulae 
 *   found in the model;</li>
 *   <li>If an {@link ItemProperties} uses the same set of formulae as found in the 
 *   model, we simply point the model at this property;</li>
 *   <li>If none of the potential item properties match, we promote one of the current
 *   property versions (i.e. copy it) to a new fountain version, attach all the items
 *   formulae to this and map the property to the model.</li>
 *   </ul>
 * </li>
 * </ul>
 *  
 * <p>Simples</p>
 */
public class ReservoirDictionaryModelImporter {
	private static final Log log = LogFactory.getLog(ReservoirDictionaryModelImporter.class);
	private ItemPropertiesDao itemPropertiesDao;
	private ItemPropertyIntervalDao itemPropertyIntervalDao;
	private IntervalDao intervalDao;
	

	public void setIntervalDao(IntervalDao intervalDao) {
		this.intervalDao = intervalDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public void setItemPropertyIntervalDao(
			ItemPropertyIntervalDao itemPropertyIntervalDao) {
		this.itemPropertyIntervalDao = itemPropertyIntervalDao;
	}


	/**
	 * Reservoir shaped models hold the equations in the lines and cells. 
	 * Fountain puts these all in the dictionary. This method takes all the
	 * formulae in cells and puts them in the dictionary. If takes care of
	 * models that have the same properties BUT DIFFERENT formulae, making 
	 * sure that both the Fountain and Reservoir models stay in sync.
	 */
	public DictionaryImportResults importModel(Model model) {
		// Setup the document and lookups
		Model mdl = (Model) model;
		Lookups lookups = new Lookups();
		List<String> errors = new Vector<String>();
		
		// Make sure that the equations in the model are attached to the appropriate properties
		buildEquationsForModel(lookups, errors, mdl);
		
		// Build the returns - building a picture of all the attached properties
		log.info("Preparing to return results of dictionary import");
		DictionaryImportResults results = new DictionaryImportResults();
		results.setErrors(errors);
		results.setAttachedPropertiesMap(new HashMap<String, ItemProperties>());
		for (String code : lookups.propertiesForItem.keySet()) {
			ItemProperties ip = lookups.propertiesForItem.get(code).attachedProperties;
			results.getAttachedPropertiesMap().put(code, ip);
		}
		return results;
	}



	private class Lookups {
		public Map<String, CurrentItemProperties> propertiesForItem = new HashMap<String, CurrentItemProperties>();
 
		private Map<String, Interval> iMapByName;
		
		public Interval getIntervalByName(String name) {
			if (iMapByName==null) iMapByName = new HashMap<String, Interval>();
			if (iMapByName.containsKey(name)) return iMapByName.get(name);
			try {
				Interval i = intervalDao.findByName(name);
				iMapByName.put(i.getName(), i);
				return i;
			}
			catch (EmptyResultDataAccessException ex) {
				throw new InvalidDocumentException("Couldn't resolve interval code " + name);
			}
		}	
	}
	

	private void buildEquationsForModel(Lookups lookups, List<String> errors, Model model) {
		
		// Get the possible properties for each item
		getPossiblePropertiesForItems(lookups, model, errors);
		
		// Work out the formula used for each interval
		getFormulaeForItems(lookups, model, errors);
		
		// Map the formulae - promoting any property not matching the cell formulae
		mapFormulaeToItems(lookups, model, errors);
	}
	

	/**
	 * For each item in the model, work out the potential properties to use for each item.
	 * Report any bad items where:
	 * <ul>
	 * <li>the item is not in the dictionary xml.</li>
	 * <li>the item has property versions not defined in the dictionary xml.</li>
	 * </ul> 
	 * @param model
	 * @param errors
	 */
	private void getPossiblePropertiesForItems(Lookups lookups, Model model, List<String> errors) {
		log.info("Reading current items in dictionary");

		// Build an array of all item codes
		String[] codes = new String[model.getItems().getItemArray().length];
		int n=0;
		for (uk.gov.ofwat.model2.ItemDocument.Item i : model.getItems().getItemArray()) {
			codes[n++] = i.getCode();
		}
		
		// Do a bulk read of all items and properties, pushing them all into a map
		List<ItemProperties> itemProperties = itemPropertiesDao.getForCodes(codes);
		Map<String, List<ItemProperties>> propsForItemMap = new HashMap<String, List<ItemProperties>>();
		for (ItemProperties ip : itemProperties) {
			String code = ip.getItem().getCode();
			if (!propsForItemMap.containsKey(code)) {
				propsForItemMap.put(code, new Vector<ItemProperties>());
			}
			propsForItemMap.get(code).add(ip);
		}
		
		// Now we have a map so that given an item code, we get ALL properties and intervals
		log.info("Finding possible properties");
		int count=0;			
		int total = 0;
		for (uk.gov.ofwat.model2.ItemDocument.Item i : model.getItems().getItemArray() ) {
			if (!propsForItemMap.containsKey(i.getCode())) {
				errors.add("Item code " + i.getCode() + " is not present in the dictionary");
				continue;
			}
			
			// Get the reservoir version number the item is attached to 
			int reservoirVersionNumber = Integer.parseInt(i.getVersionNumber());
			
			// Work out the largest version number and the properties attached to the item
			// We need all the properties with the same reservoir version number
			int largestVersioNumber = 0;
			List<ItemProperties> props = propsForItemMap.get(i.getCode());
			List<ItemProperties> possibleProperties = new Vector<ItemProperties>();
			for (ItemProperties ip : props) {
				
				if (ip.getVersion()>=largestVersioNumber) largestVersioNumber = ip.getVersion();
				if (ip.getReservoirVersion()==reservoirVersionNumber) {
					possibleProperties.add(ip);
				}
			}
			if (possibleProperties.size()==0) {
				// This model does not have the specified properties in the dictionary
				errors.add ("Item code " + i.getCode() + " specifies reservoir version number " + reservoirVersionNumber + " - which does not exist in the dictionary");
			}
			else {
				// Found the current properties so store these and read the attached intervals
				CurrentItemProperties cp = new CurrentItemProperties();
				cp.possibleProperties = possibleProperties;
				cp.nextVersionNumber = largestVersioNumber+1;
				lookups.propertiesForItem.put(i.getCode(), cp);
			}
			
			count++;
			total++;
			if (count>=1000 || total==1) {
				log.info("Processed " + total + " of " + model.getItems().getItemArray().length + " items in model");
				if (total>1) count = 0;
			}
		}
		log.info("Finished Processing " + total + " items in model");
}
	
	/**
	 * Step through the pages in the model and work out all 
	 * formulae used for each year
	 */
	private void getFormulaeForItems(Lookups lookups, Model model, List<String> errors) {
		log.info("Reading formulae in model");
		for (uk.gov.ofwat.model2.PageDocument.Page page : model.getPages().getPageArray()) {
			log.info("Processing formulae in page " + page.getPagedetails().getCode());
			for (Section section : page.getSections().getSectionArray()) {
				for(Line line: section.getLines().getLineArray()) {
					if (line.getCells()!=null && line.getCells().getCellArray() != null) {
						// Get the current properties for the item code
						String itemCode = line.getLinedetails().getCode();
						CurrentItemProperties cp = lookups.propertiesForItem.get(itemCode);
						if (cp==null) {
							errors.add("Line " + line.getLinedetails().getLinenumber() + " " +
									   "in table " + page.getPagedetails().getCode() + " " +
									   "refers to invalid item " + itemCode + "\n");
							continue;
						}
						
						String eq = line.getLinedetails().getEquation();
						for(Cell cell: line.getCells().getCellArray()) {
							if (!cell.getType().equalsIgnoreCase("COPYCELL") && 
								!cell.getType().equalsIgnoreCase("INPUT")) {

								// Process anything not a copycell
								eq = cell.getEquation()==null ? eq : cell.getEquation();

								// if the code is not the same as the equation (can happen in copydata cells)
								if (!eq.equals(itemCode)){
									String interval = cell.getYear();
									cp.formulaeForInterval.put(interval, eq);
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Now we now all the possible properties AND the equations needed, 
	 * map the item properties - promoting each item if the model equations
	 * do not match.
	 */
	private void mapFormulaeToItems(Lookups lookups, Model model, List<String> errors) {
		log.info("Mapping the cell formulae to item properties");
		int count = 0;
		int total = 0;
		int max = lookups.propertiesForItem.size();
		for (CurrentItemProperties cp : lookups.propertiesForItem.values()) {
			if (cp.attachedProperties!=null) {
				// already processed
			}
			else {
				// Find out if there is an unattached item properties
				ItemProperties unattached = null;
				for (ItemProperties ip : cp.possibleProperties) {
					// Item 
					if (!ip.isAttachedToModel()) {
						unattached = ip;
						break;
					}
				}
				if (unattached!=null) {
					// There is an unattached properties so put all formula here
					attachAllFormulaeToItem(lookups, cp, unattached);
					cp.attachedProperties = unattached;
				}
				else {
					// No unattached properties so test each possible properties
					ItemProperties matchingProperties = findFormulaThatMatches(lookups, cp);
					if (matchingProperties==null) {
						// At this point, we have a set of formulae that do not
						// match ANYTHING available for this item. So - we promote the
						// reservoir version to the next available fountain item and 
						// attach all the intervals
						ItemProperties newProperties = promoteProperties(cp);
						attachAllFormulaeToItem(lookups, cp, newProperties );
						cp.attachedProperties = newProperties;
					}
					else {
						// We have a set of properties that match exactly.
						// Just use this.
						cp.attachedProperties = matchingProperties;
					}
				}
				cp.formulaeForInterval = null;
				cp.possibleProperties = null;
	
			}
			
			count++;
			total++;
			if (count>=500 || total==1) {
				log.info("Mapped " + total + " of " + max + " items");
				if (total>1) count = 0;
			}
		}
		log.info("Finished Processing " + total + " items");

	}

	/**
	 * Create a new item properties based on the possible properties - but with the next available version
	 */
	private ItemProperties promoteProperties(CurrentItemProperties cp) {
		// Copy the properties giving it the next version number and
		// flagging it as attached
		ItemProperties ip = cp.possibleProperties.get(0);
		ip.setAttachedToModel(true);
		ip.setVersion(cp.nextVersionNumber);
		cp.nextVersionNumber++;
		
		int id = itemPropertiesDao.create(ip);
		ip.setId(id);
		return ip;
	}
	
	/**
	 * Find the possible item properties that match ALL of the 
	 * formulae in the model. To match a possible item properties must:
	 * <ul>
	 * <li>Have the same number of formulae</li>
	 * <li>Have the same intervals</li>
	 * <li>Have the same formulae</li>
	 * </ul>
	 * 
	 */
	private ItemProperties findFormulaThatMatches(Lookups lookups, CurrentItemProperties cp) {
		ItemProperties match = null;
		for (ItemProperties ip : cp.possibleProperties) {
			if (ip.getFormulae().size()!=cp.formulaeForInterval.size()) {
				// the number of formulae don't match - so nothing more to do for THIS property
			}
			else {
				boolean isSame = true;
				int checked=0;
				for (String intervalName : cp.formulaeForInterval.keySet()) {
					checked++;
					Interval interval = lookups.getIntervalByName(intervalName);
					String xmlFormula = cp.formulaeForInterval.get(intervalName);
					String dbFormula = ip.getFormula(interval);
					if (!xmlFormula.equalsIgnoreCase(dbFormula)) {
						// This doesn't match so stop the processing of this properties
						isSame = false;
						break;
					}
				}
				
				if (isSame && ip.getFormulae().size()==checked) {
					// The properties on the dictionary item match those specified by the model
					// AND the same years are present
					match = ip;
					break;
				}
			}
			
		}
		return match;
	}
		
		
	/**
	 * Given the current properties and a set of actual item properties, 
	 * add add the cell intervals to 
	 * @param cp
	 * @param ip
	 */
	private void attachAllFormulaeToItem(Lookups lookups, CurrentItemProperties cp, ItemProperties ip) {
		ip.setAttachedToModel(true);
		itemPropertiesDao.attachToModel(ip.getId());
		
		Map<Interval, ItemPropertyInterval> formulaMap = new HashMap<Interval, ItemPropertyInterval>();
		
		for (String interval : cp.formulaeForInterval.keySet()) {
			String fml = cp.formulaeForInterval.get(interval);
			ItemPropertyInterval ipi = new ItemPropertyInterval();
			ipi.setDescription("Generated by importer");
			ipi.setFormula(fml);
			ipi.setInterval(lookups.getIntervalByName(interval));
			ipi.setItemProperties(ip);
			ipi.setId(0); // to show it must be created
			
			formulaMap.put(ipi.getInterval(), ipi);
			int id = itemPropertyIntervalDao.create(ipi);
			ipi.setId(id);
		}
		
		ip.setFormulae(formulaMap);
		cp.attachedProperties = ip;
		

	}

	
	private class CurrentItemProperties {
		List<ItemProperties> possibleProperties = new Vector<ItemProperties>();
		Map<String, String> formulaeForInterval = new TreeMap<String, String>();
		ItemProperties attachedProperties;
		int nextVersionNumber = 0;
	}


}
