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

import uk.gov.ofwat.fountain.dao.IntervalDao;
import uk.gov.ofwat.fountain.dao.ItemDao;
import uk.gov.ofwat.fountain.domain.Pot;

public class CacheTestPotDaoImpl implements CacheTestPotDao {

	private Pot pot;
	private List<Pot> pots;

	public void setPot(Pot pot) {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setPot");
		this.pot = pot;
	
		System.out.println("TEST: Done");
}
	
	public void setPots(List<Pot> pots) {
		this.pots = pots;
	}
	
	public Pot findById(int id, Boolean deleted) {
		return this.pot;
	}

	public void delete(int id) {
		System.out.println("CacheTestDaoImpl.delete()");
	}
	
	public void update(Pot pot){
		System.out.println("CacheTestDaoImpl.update()");
	}

	public int create(Pot pot, int TableId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Pot> findByTableId(int tableId) {
		return this.pots;
	}

	public ItemDao getItemDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public IntervalDao getYearDao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setIntervalDao(IntervalDao intervalDao) {
		// TODO Auto-generated method stub
		
	}

	public void setItemDao(ItemDao itemDao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pot findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
