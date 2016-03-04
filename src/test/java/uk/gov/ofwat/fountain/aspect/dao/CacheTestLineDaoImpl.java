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
package uk.gov.ofwat.fountain.aspect.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Line;

public class CacheTestLineDaoImpl implements CacheTestLineDao {

	private List<Line> lines;
	
	public void delete(int id) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":delete");
		// TODO Auto-generated method stub

	
		System.out.println("TEST: Done");
}

	public ItemProperties findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void update(ItemProperties itemProperties) {
		// TODO Auto-generated method stub

	}

	public void attachToModel(int itemPropertiesId) {
		// TODO Auto-generated method stub

	}

	public int create(ItemProperties itemProperties) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ItemProperties findByItemAndModel(int itemId, int modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemProperties findByItemAndModel(String itemCode, int modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemProperties findByItemCodeAndVersion(String itemCode, int version) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ItemProperties> getAllForItemId(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ItemProperties> getForCodes(String[] itemCodes) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLines( List<Line> lines) {
		this.lines = lines;
		
	}

	public void create(Line line) {
		// TODO Auto-generated method stub
		
	}

	public List<Line> findByTableItem(int tableId, int itemId) {
		return lines;
	}

	public Line findByTableItemLineNumber(int tableId, int itemId,
			int lineNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
