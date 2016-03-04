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
package uk.gov.ofwat.fountain.domain;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import uk.gov.ofwat.fountain.rest.dto.DataDto;

public class Formatter implements Serializable {
	protected static Log log = LogFactory.getLog(Formatter.class);

	private static final long serialVersionUID = -7745767895914298522L;
	private Map<Integer, DecimalFormat> formatMap = null;
	private DecimalFormat anyDPFormat;
	
	public Formatter() {
		// Prebuild with 6 formats - the most common
		DecimalFormat[] dfs = { 
 	         new DecimalFormat("#,##0"),
	         new DecimalFormat("#,##0.0"),
	         new DecimalFormat("#,##0.00"),
	         new DecimalFormat("#,##0.000"),
	         new DecimalFormat("#,##0.0000"),
	         new DecimalFormat("#,##0.00000")
		};
	
		formatMap = new HashMap<Integer, DecimalFormat>();
		
		for (int i=0; i<dfs.length; i++) {
			formatMap.put(i, dfs[i]);
		}
		
		anyDPFormat = new DecimalFormat("#,##0.############");
	}

	/**
	 * Format the da
	 * @param model
	 * @param item
	 * @param data
	 */
	public void formatData(Model model, Item item, Data data) {
		
		// Do nothing if there is no value
		String value = data.getValue();		
		if (!StringUtils.hasText(value)) {
			data.setValue("");
			return;
		}

		// For text values - do not format
		if (item.getUnit().equalsIgnoreCase("Text")) { 
			return; 
		}
		boolean isPercent = item.getUnit().equals("%");

		ItemProperties ip = model.getItemPropertiesForItem(item.getId());
		double roundingFactor = Math.pow(10, ip.getDecimalPlaces());
		if(isPercent){
			roundingFactor*=100;
		}
		try {
			double raw = Double.parseDouble(value);
			double v = raw;
			
			// get rid of insignificant digits
			v = (Math.round(v * (roundingFactor))) / roundingFactor;
			if(0 == v){
				v = 0; // gets rid of -0
			}
			if(isPercent){
				v = v * 100;
			}
			DecimalFormat df = getFormat(ip.getDecimalPlaces());
			if (null == data.getRawValue() ||
				data.getRawValue().isNaN()) {
				// only write if its not yet set.
				data.setRawValue(raw);
			}
			data.setValue(df.format(v));
		}
		catch (NumberFormatException ex) {
			// If we can't format it, then just return as is
			return;
		}
	}
	
	/**
	 * Format the value
	 * @param dataDto
	 * @param value
	 * @returns formated value
	 */
	public String formatValue(DataDto dataDto, Double value) {
		
		boolean isPercent = dataDto.getItemPropertiesDto().getItem().getUnit().equals("%");

		double roundingFactor = Math.pow(10, dataDto.getItemPropertiesDto().getDecimalPlaces());
		if (isPercent) {
			roundingFactor*=100;
		}
		
		try {
			double raw = value;
			double v = raw;
			
			// get rid of insignificant digits
			v = (Math.round(v * (roundingFactor))) / roundingFactor;
			if(0 == v){
				v = 0; // gets rid of -0
			}
			if(isPercent){
				v = v * 100;
			}
			DecimalFormat df = getFormat(dataDto.getItemPropertiesDto().getDecimalPlaces());
			return df.format(v);
		}
		catch (NumberFormatException ex) {
			// If we can't format it, then just return as is
			return String.valueOf(value);
		}
	}
	
	/**
	 * Build the format for the given dp
	 */
	private DecimalFormat getFormat(int dps) {
		
		if(dps<0) {
			// a negative value???
			return formatMap.get(0);
		}
		else if (formatMap.containsKey(dps)) {
			return formatMap.get(dps);
		}
		else {
			// create a new one for 6 or more dps.
			StringBuffer fmt = new StringBuffer("#,##0.");
			for(int i = 0; i < dps; i++){
				fmt.append("0");
			}
			DecimalFormat df = new DecimalFormat(fmt.toString());
			formatMap.put(dps, df);
			return df;
		}
	}	

	/**
	 * Build the format for the given dp
	 */

	public String formatValueToAtLeastDP(DataDto dataDto, Double value) {
		// display all decimal places with a value, but at least the number of dp in the itemproperties. 
		boolean isPercent = dataDto.getItemPropertiesDto().getItem().getUnit().equals("%");

		try {
			if(isPercent){
				value = value * 100;
			}

			DecimalFormat specificDecimalFormat = getFormat(dataDto.getItemPropertiesDto().getDecimalPlaces());
			String specificDPValue = specificDecimalFormat.format(value);
			String anyDPValue = anyDPFormat.format(value);
			if (specificDPValue.length() >= anyDPValue.length()) {
				return specificDPValue;
			}
			else {
				return anyDPValue;
			}
		}
		catch (NumberFormatException ex) {
			// If we can't format it, then just return as is
			return String.valueOf(value);
		}
	}

}
