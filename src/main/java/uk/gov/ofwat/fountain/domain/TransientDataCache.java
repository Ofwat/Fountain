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

import java.util.HashMap;
import java.util.Map;

import uk.gov.ofwat.fountain.domain.UserEdit.EditType;
import uk.gov.ofwat.fountain.domain.form.DataKey;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelCompanyTag;

/**
 * A cache that exists only which processing an entity such as a table or report
 */
public class TransientDataCache {
	private Map<String, String> cache = new HashMap<String, String>();
	
	public boolean hasValue(Model model, Company company, Item item, Interval interval, GroupEntry groupEntry, Run run, RunModelCompanyTag tag) {
		String key = getKey(model, company, item, interval, groupEntry, run, tag);
		return cache.containsKey(key);
	}
	
	public String getValue(Model model, Company company, Item item, Interval interval, GroupEntry groupEntry, Run run, RunModelCompanyTag tag) {
		String key = getKey(model, company, item, interval, groupEntry, run, tag);
		return cache.get(key);
	}
	
	public void setValue(Model model, Company company, Item item, Interval interval, GroupEntry groupEntry, Run run, RunModelCompanyTag tag, String value) {
		String key = getKey(model, company, item, interval, groupEntry, run, tag);
		cache.put(key, value);
	}
	
	private String getKey(Model model, Company company, Item item, Interval interval, GroupEntry groupEntry, Run run, RunModelCompanyTag tag) {
		DataKey dataKey = new DataKey(item, interval, company, groupEntry, EditType.VALUE, run, tag);
		return dataKey.getKey(true);
	}
	
}
