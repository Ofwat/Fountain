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

import uk.gov.ofwat.fountain.dao.LineDao;
import uk.gov.ofwat.fountain.domain.Line;

/** 
 * Service class responsible for reference data (Companies, Years etc)
 * 
 * Anything not directly relating to models, Data dictionary or instance data.
 */
public class DisplayServiceImpl implements DisplayService{
	
	private LineDao lineDao;
	
	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	public Line getLine(int tableId, int itemId) {
		List<Line> lines = lineDao.findByTableItem(tableId, itemId);
		if (null == lines ||
			lines.isEmpty()) {
			return null;
		}
		// TODO what about multiple lines
		return lines.get(0);
	}
}
