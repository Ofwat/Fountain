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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Lock;
import uk.gov.ofwat.fountain.domain.User;

public class LockDaoImpl extends JdbcDaoSupport implements LockDao {
	private static final org.apache.commons.logging.Log log = LogFactory.getLog(LockDaoImpl.class);
	private static final String TABLE_NAME = "tbl_lock"; // TODO this may not stay as this table
	private UserDao userDao;
	
	public void setUserDao(UserDao dao){
		this.userDao = dao;
	}
	
	private final RowMapper<Lock> ROW_MAPPER = new RowMapper<Lock>() {
	    public Lock mapRow(ResultSet rs, int rowNum) throws SQLException {
	        
	    	String userName = rs.getString("user");
	    	int id = rs.getInt("id");
	    	int itemId = rs.getInt("itemId");
	    	int yearId = rs.getInt("yearId");
	    	int companyId = rs.getInt("companyId");
	    	Long runId = rs.getLong("runId");
	        User user = new User();
	        user.setName(userName);
	    	
	    	Lock lock = new Lock();
	    	lock.setId(id);
	        lock.setCompanyId(companyId);
	        lock.setItemId(itemId);
	        lock.setUser(user);
	        lock.setIntervalId(yearId);
	        lock.setRunId(runId);
	        return lock;
	    }
	};	
	

	// TODO Not cached
	public Lock findByLockId(int lockId){
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, new Object[]{lockId});
	}
	
	public void deleteLocksForUser(User user){
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE user = ?";
		getJdbcTemplate().update(sql, user.getName());
	}

	// TODO Not cached
	public List<Lock> getExpiredLocks() {
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE TIMESTAMP < (NOW() - INTERVAL 60 SECOND)"; // timestamp is older than a minute
		return getJdbcTemplate().query(sql, ROW_MAPPER);
	}
	
	public void deleteExpiredLocks() {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE TIMESTAMP < (NOW() - INTERVAL 60 SECOND)"; // timestamp is older than a minute
		getJdbcTemplate().update(sql);
	}

	// TODO Not cached
	public void deleteLocks(List<Integer> lockIds) {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id in (?)";

		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (Integer lockId: lockIds) {
			Object[] lockIdObjectArray = new Object[1];
			lockIdObjectArray[0] = lockId;
			batchArgs.add(lockIdObjectArray);
		}
		try{
			getJdbcTemplate().batchUpdate(sql, batchArgs);
		}catch(DeadlockLoserDataAccessException dldae){
			//retry
			getJdbcTemplate().batchUpdate(sql, batchArgs);
		}
	}
	
	// TODO Not cached
	public List<Lock>getLocksForUser(User user){
		String sql = "SELECT * FROM " + TABLE_NAME + " WHERE user = ?";
		return getJdbcTemplate().query(sql, ROW_MAPPER, user.getName());
	}

	/* (non-Javadoc)
	 * @see uk.gov.ofwat.fountain.dao.LockDao#create(uk.gov.ofwat.fountain.domain.Lock)
	 */
	public int create(Lock lock) {
		String sql = "INSERT INTO " + TABLE_NAME + " (ItemId, YearId, CompanyId, User, runId) VALUES (?,?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // itemId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // intervalId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // CompanyId
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // User
        su.declareParameter(new SqlParameter(Types.BIGINT)); // runId
        Object[] parameters = new Object[] {lock.getItemId(), 
        									lock.getIntervalId(),
        									lock.getCompanyId(),
        									lock.getUser().getName(),
        									lock.getRunId(),
        									};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        lock.setId(keyHolder.getKey().intValue());
        log.debug("Creating a lock:" + lock.toString());
        return lock.getId();
	}

	// TODO Not cached
	public boolean isItemLockedByOtherUser(int itemId, int intervalId,
			int companyId, User currentUser, long runId) {
		String sql = "SELECT user FROM " + TABLE_NAME + " WHERE itemId = ? AND yearId = ? AND companyId = ? and runId = ? ";
		String lockUserName = null;
		try{
			lockUserName = getJdbcTemplate().queryForObject(sql, String.class, itemId, intervalId, companyId, runId);
		}
		catch(EmptyResultDataAccessException erdae){
			System.out.println("Empty data for itemId " + itemId);
		}
		if(null == currentUser){
			return null != lockUserName;
		}
		return !currentUser.getName().equals(lockUserName);
		
	}

	public User getLockingUser(int itemId, int intervalId, int companyId, long runId) {
		String sql = "SELECT user FROM " + TABLE_NAME + " WHERE itemId = ? AND yearId = ? AND companyId = ? and runId = ? ";
		String userName = null;
		try{
			userName = getJdbcTemplate().queryForObject(sql, String.class, itemId, intervalId, companyId, runId);
		}
		catch(EmptyResultDataAccessException erdae){
			return null;
		}
		return userDao.findUserByName(userName);
	}

	// TODO Not cached
	public boolean refreshLock(Lock lock) {
		String sql = "update " + TABLE_NAME + " set timestamp = CURRENT_TIMESTAMP where itemId = ? AND yearId = ? AND companyId = ? and user = ? and runId = ? " +
					"and timestamp < CURRENT_TIMESTAMP";  // Only refresh locks that don't belong to edits.
		try {
			getJdbcTemplate().update(sql, lock.getItemId(), lock.getIntervalId(), lock.getCompanyId(), lock.getUser().getName(), lock.getRunId());
		} catch (DeadlockLoserDataAccessException e) {
			// caller deals with this.
			return false;
		}
		return true;
	}

	// TODO Not cached
	public void refreshLocks(List<Integer> lockIds) {
		String sql = "update " + TABLE_NAME + " set timestamp = CURRENT_TIMESTAMP where id in (?) " +
					"and timestamp < CURRENT_TIMESTAMP"; // Only refresh locks that don't belong to edits.   
		
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for (Integer lockId: lockIds) {
			Object[] lockIdObjectArray = new Object[1];
			lockIdObjectArray[0] = lockId;
			batchArgs.add(lockIdObjectArray);
		}
		getJdbcTemplate().batchUpdate(sql, batchArgs);
	}

	// TODO Not cached
	public void lockForEdit(Lock lock) {
		String sql = "update " + TABLE_NAME + " set timestamp = (CURRENT_TIMESTAMP + INTERVAL 1 YEAR) where itemId = ? AND yearId = ? AND companyId = ? and user = ? and runId = ?";
		getJdbcTemplate().update(sql, lock.getItemId(), lock.getIntervalId(), lock.getCompanyId(), lock.getUser().getName(), lock.getRunId());
	}

	public List<User> getAllLockingUsers() {
		String sql = "SELECT DISTINCT user FROM " + TABLE_NAME ;
		 RowMapper<User> rm = new RowMapper<User>() {  
		        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		            User user = userDao.findUserByName(rs.getString("user"));
		        	return user;
		        }
		 };
		return getJdbcTemplate().query(sql, rm, new Object[]{});
	}

	public List<Lock> getAllLocks() {
		String sql = "SELECT * FROM " + TABLE_NAME;
		List<Lock> locks = getJdbcTemplate().query(sql, ROW_MAPPER);
		return locks;
	}
}
