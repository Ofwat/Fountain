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
package uk.gov.ofwat.fountain;

import org.springframework.context.ApplicationContext;

import uk.gov.ofwat.fountain.dao.DataDao;
import uk.gov.ofwat.fountain.domain.Audit;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;

public class Populator {

	private static ApplicationContext context;

	static {
		// context = new ClassPathXmlApplicationContext("beans.xml");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DataDao dataDao = (DataDao) context.getBean("dataDao");
		Audit audit = new Audit();
		audit.setComment("created by uk.gov.ofwat.fountain.Populator");
		for (int i = 1; i <= 10; i++) {
			Item item = new Item();
			item.setId(i);
			item.setName("");
			for (int j = 1; j <= 10; j++) {
				Interval interval = new Interval();
				interval.setId(j);
				interval.setName("");
				Data data = new Data();
				data.setValue("" + i * j);
				data.setItem(item);
				data.setInterval(interval);
				dataDao.create(data, audit);
			}
		}

	}

}
