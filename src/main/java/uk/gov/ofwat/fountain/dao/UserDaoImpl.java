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
import java.sql.Types;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.User;

/**
 * used to map the username received from the realm database to the application user.
 * 
 * The application user table stores userid and is used to keep track of edits. The
 * users in the application table can continue to exist after they have been removed from the realm.
 * 
 * 
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

	private final static String TABLE_NAME = "tbl_user";
//	 private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	
	private final RowMapper<User> ROW_MAPPER = new RowMapper<User>() {
	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	User user = new User();
	    	user.setId(rs.getInt("id"));
	    	user.setName(rs.getString("name"));
	    	return user;
	    }
	};
	 
	public User findUserByName(String name) {

		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE name = ?";
		User user = null;
		try{
			user = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, name);
		}
		catch(EmptyResultDataAccessException e){
			// create the user on the local database (they're authenticated by Active Directory so
			// we're not worried about secutrity. This is just to map the user to their reports, 
			// edits and audits etc.
			return create(name);
		}
		return user;
	}
	
	public User findById(int userId) {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id  = ?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, userId);
	}

	public User create(String name) {
		String sql = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?)";
		SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        Object[] parameters = new Object[] {name};
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        
        // return the new user by id;
		String returnSql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? ";
		int id = keyHolder.getKey().intValue();
		return getJdbcTemplate().queryForObject(returnSql, ROW_MAPPER, id);
	}
	
	public List<User> getAll() {
		String sql = "SELECT * FROM " + TABLE_NAME;
		return getJdbcTemplate().query(sql, ROW_MAPPER);
	}
	
}
