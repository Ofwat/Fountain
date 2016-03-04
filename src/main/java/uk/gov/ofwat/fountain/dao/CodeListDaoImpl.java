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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import uk.gov.ofwat.fountain.domain.CodeList;
import uk.gov.ofwat.fountain.domain.ListItem;

public class CodeListDaoImpl extends SimpleJdbcDaoSupport implements CodeListDao{
	
	private static final String CODELIST_TABLE_NAME = "tbl_list";
	private static final String LIST_ITEM_TABLE_NAME = "tbl_listItem";
	
	
	private final RowMapper<CodeList> CODELIST_ROW_MAPPER = new RowMapper<CodeList>() {
	    public CodeList mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	CodeList c = new CodeList();
	        c.setId(rs.getInt("id"));
	        c.setDescription(rs.getString("description"));
	        c.setCode(rs.getString("code"));
	        return c;
	    }
	};
	
	private final RowMapper<ListItem> LIST_ITEM_ROW_MAPPER = new RowMapper<ListItem>() {
	    public ListItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ListItem li = new ListItem();
	    	li.setId(rs.getInt("id"));
	        li.setDescription(rs.getString("description"));
	        li.setCode(rs.getString("code"));
	        return li;
	    }
	};

	public CodeList findByCode(String code) {
		String sql = "select * from " + CODELIST_TABLE_NAME + " where code = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, CODELIST_ROW_MAPPER , new Object[]{code});
	}

	public CodeList findById(int id) {
		String sql = "select * from " + CODELIST_TABLE_NAME + " where id = ?";
		return getSimpleJdbcTemplate().queryForObject(sql, CODELIST_ROW_MAPPER , new Object[]{id});
	}

	public List<CodeList> getAllCodeLists() {
		String sql = "select * from " + CODELIST_TABLE_NAME ;
		return getSimpleJdbcTemplate().query(sql, CODELIST_ROW_MAPPER, new Object[]{});
	}

	public List<ListItem> getAllItemsForCodeList(CodeList list) {
		
		String sql = "select * from " + LIST_ITEM_TABLE_NAME + " where listId = ?"; 
		List<ListItem> items = getSimpleJdbcTemplate().query(sql, LIST_ITEM_ROW_MAPPER, new Object[]{list.getId()});
		for(ListItem li: items){
			li.setCodeList(list);
		}
		return items;
	}

	public ListItem getListItemByCode(CodeList list, String code) {
		
		String sql = "select * from " + LIST_ITEM_TABLE_NAME  + " where code = ? and listId = ?";
		ListItem item = getSimpleJdbcTemplate().queryForObject(sql, LIST_ITEM_ROW_MAPPER, new Object[]{code, list.getId()});
		item.setCodeList(list);
		return item;
	}

	public ListItem getListItemById(int id) {
		String listSql = "SELECT t.* FROM " + CODELIST_TABLE_NAME + " t inner join " + LIST_ITEM_TABLE_NAME  + " i on i.listId = t.id where i.id = ?";
		CodeList cl = getSimpleJdbcTemplate().queryForObject(listSql, CODELIST_ROW_MAPPER, new Object[]{id});
		String itemSql = "select * from " + LIST_ITEM_TABLE_NAME + " where id = ?";
		ListItem item = getSimpleJdbcTemplate().queryForObject(itemSql, LIST_ITEM_ROW_MAPPER, new Object[]{id});
		item.setCodeList(cl);
		return item;
	}

}
