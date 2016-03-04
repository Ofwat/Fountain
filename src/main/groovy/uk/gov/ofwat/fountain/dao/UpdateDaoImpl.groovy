package uk.gov.ofwat.fountain.dao

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import clover.gnu.cajo.utils.extra.Implements;
import uk.gov.ofwat.fountain.domain.update.Release;
import uk.gov.ofwat.fountain.domain.update.Update;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;

class UpdateDaoImpl extends SimpleJdbcDaoSupport implements UpdateDao{

	private static final String RELEASE_TABLE_NAME = "tbl_release";
	private static final String UPDATE_TABLE_NAME = "tbl_update";
	
	private final RowMapper<Release> RELEASE_ROW_MAPPER = new RowMapper<Release>() {
		public Release mapRow(ResultSet rs, int rowNum) throws SQLException {
			Release release = new Release()
			release.id = rs.getLong("id")
			release.releaseName = rs.getString("releaseName")
			release.releaseDate = rs.getTimestamp("releaseDate")
			release.releaseVersion = rs.getString("releaseVersion")
			release.published = rs.getBoolean("published")
			return release
		}
	};
	
	private final RowMapper<Release> UPDATE_ROW_MAPPER = new RowMapper<Update>() {
		public Update mapRow(ResultSet rs, int rowNum) throws SQLException {
			Update update = new Update()
			update.id = rs.getLong("id")
			update.sortOrder = rs.getLong("sortOrder")
			update.title = rs.getString("title")
			update.description = rs.getString("description")
			update.externalLink = rs.getString("link")
			return update
		}
	};

	@Override
	public Release getRelease(Long releaseId) {
		String releaseSql = "SELECT r.id, r.releaseName, r.releaseDate, r.releaseVersion, r.published from tbl_release r where r.id = ?"
		String updateSql = "SELECT u.id, u.title, u.sortOrder, u.description, u.link from tbl_update u where u.releaseId = ? order by u.sortOrder asc"
		Release release
		List<Update> updates
		try{
			release = getSimpleJdbcTemplate().queryForObject(releaseSql, RELEASE_ROW_MAPPER, releaseId)
			//Get all the updates.
			updates = getSimpleJdbcTemplate().query(updateSql, UPDATE_ROW_MAPPER, releaseId)
			release.updates = updates
		}catch(Exception e){
			println e
			println "Couldn't find release."
		}
		return release;
	}

	@Override
	public List<Release> getAllReleases() {
		String releaseSql = "SELECT r.id, r.releaseName, r.releaseDate, r.releaseVersion, r.published from tbl_release r order by r.id desc"
		String updateSql = "SELECT u.id, u.title, u.sortOrder, u.description, u.link from tbl_update u where u.releaseId = ? order by u.sortOrder asc"
		List<Release> releases
		List<Update> updates
		try{
			releases = getSimpleJdbcTemplate().query(releaseSql, RELEASE_ROW_MAPPER)
			//Get all the updates for a release.
			for(Release release : releases){
				updates = getSimpleJdbcTemplate().query(updateSql, UPDATE_ROW_MAPPER, release.id)
				release.updates = updates
			}
		}catch(Exception e){
			println e
			println "Couldn't find release."
		}
		return releases
	}

	@Override
	public Release createRelease(Release release) {
		String sql = "INSERT INTO " + RELEASE_TABLE_NAME + " (releaseName, releaseDate, releaseVersion, published) VALUES (?, ?, ?, ?)"
		SqlUpdate su = new SqlUpdate()
		su.setDataSource(getDataSource())
		su.setSql(sql)
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // releaseName
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // releaseDate
		su.declareParameter(new SqlParameter(Types.DATE)) // releaseVersion
		su.declareParameter(new SqlParameter(Types.BOOLEAN)) // published
		Object[] parameters = [release.releaseName, release.releaseDate, release.releaseVersion, release.published]
		KeyHolder keyHolder = new GeneratedKeyHolder()
		su.setReturnGeneratedKeys(true)
		su.update(parameters, keyHolder)
		release.id = keyHolder.getKey() //TODO Is this correct?
		return release
	}

	@Override
	public Release updateRelease(Release release) {
		String sql = "update " + RELEASE_TABLE_NAME + " set releaseName = ?, releaseDate = ?, releaseVersion = ?, published = ? where id = ?"
		SqlUpdate su = new SqlUpdate()
		su.setDataSource(getDataSource())
		su.setSql(sql)
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // releaseName
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)) // releaseDate
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // releaseVersion
		su.declareParameter(new SqlParameter(Types.BOOLEAN)) // published
		su.declareParameter(new SqlParameter(Types.INTEGER)) // releaseId
		Object[] parameters = [release.releaseName, release.releaseDate, release.releaseVersion, release.published, release.id]
		KeyHolder keyHolder = new GeneratedKeyHolder()
		su.setReturnGeneratedKeys(true)
		su.update(parameters, keyHolder)
		return release
	}
	
	@Override
	public Update getUpdate(Long updateId) {
		String updateSql = "SELECT u.id, u.title, u.sortOrder, u.description, u.link from tbl_update u where u.id = ?"
		Update update = getSimpleJdbcTemplate().queryForObject(updateSql, UPDATE_ROW_MAPPER, updateId)
		return update
	}
	
	@Override
	public Update createUpdate(Update update, Release release) {
		String sql = "INSERT INTO " + UPDATE_TABLE_NAME + " (title, description, link, releaseId, sortOrder) VALUES (?, ?, ?, ?, ?)";
		SqlUpdate su = new SqlUpdate()
		su.setDataSource(getDataSource())
		su.setSql(sql)
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // title
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // desc
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // link
		su.declareParameter(new SqlParameter(Types.BIGINT)) // releaseId
		su.declareParameter(new SqlParameter(Types.BIGINT)) // sortOrder
		Object[] parameters = [update.title, update.description, update.externalLink, release.id, update.sortOrder]
		KeyHolder keyHolder = new GeneratedKeyHolder()
		su.setReturnGeneratedKeys(true)
		su.update(parameters, keyHolder)
		update.id = keyHolder.getKey().toLong()
		return update
	}
	
	@Override
	public Boolean deleteUpdate(Update update){
		String sql = "DELETE from tbl_update where id = ?";
		SqlUpdate su = new SqlUpdate()
		su.setDataSource(getDataSource())
		su.setSql(sql)
		su.declareParameter(new SqlParameter(Types.BIGINT))
		Object[] parameters = [update.id]
		KeyHolder keyHolder = new GeneratedKeyHolder()
		su.setReturnGeneratedKeys(true)
		su.update(parameters, keyHolder)
		return true
	}
	
	
	
}
