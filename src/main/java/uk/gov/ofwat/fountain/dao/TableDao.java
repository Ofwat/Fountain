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
package uk.gov.ofwat.fountain.dao;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Table;

public interface TableDao extends CachableEntityDao {

	
	public int create(Table table);
	
	public Table findById(int id);
	
	public List<Table> findByModelId(int modelId);
	
	public List<Table>getAll();
	
	public List<Table> findByModelIdAndCompanyId(int modelId, int companyId);
	
	public List<Table> findByModelIdAndCompanyType(int modelId, int companyTypeId);
	
	public int addCompanyTable(int companyId, int modelId, int tableId);

	/**
	 * @return all of the groups present in the table
	 */
	public List<Integer> getGroupIdsForTable(int tableId);
	
	public List<Table> getTablesForItem(int itemId);
	
	public List<Table>findAll();
	
}
