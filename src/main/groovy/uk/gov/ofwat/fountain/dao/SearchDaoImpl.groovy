package uk.gov.ofwat.fountain.dao

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp
import java.sql.Types;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Branch;
import uk.gov.ofwat.fountain.rest.dto.SaveSearchDto;

class SearchDaoImpl extends JdbcDaoSupport implements SearchDao{

	private static final String TABLE_NAME = "tbl_searchHistory";

	private final RowMapper<SaveSearchDto> ROW_MAPPER = new RowMapper<SaveSearchDto>() {
		public SaveSearchDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			SaveSearchDto ssd = new SaveSearchDto();
			ssd.id = rs.getLong("id")
			ssd.searchName = rs.getString("searchName")
			ssd.searchString  = rs.getString("query")
			ssd.includeDeleted = rs.getBoolean("includeDeleted")
			ssd.myDataOnly = rs.getBoolean("myDataOnly")
			ssd.user = rs.getString("user")
			ssd.createdAt = rs.getDate("createdAt")
			ssd.favorite = rs.getBoolean("favorite")
			ssd.sortByDate = rs.getBoolean("sortByDate")
			ssd.sortOrder = rs.getString("sortOrder")
			ssd.searchType = rs.getString("searchType")
			return ssd
		}
	};

	Boolean checkIfSearchExistsInHistory(SaveSearchDto saveSearchDto){
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE query = ? and user = ? and myDataOnly = ? and includeDeleted = ? and favorite = false"
		SaveSearchDto savedSearch
		try{
			savedSearch = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, saveSearchDto.searchString, saveSearchDto.user, saveSearchDto.myDataOnly, saveSearchDto.includeDeleted)
			return true
		}catch(Exception e){
			println e
			println "Couldn't find existing search." 
		}
		return false
	}


	@Override
	def saveSearchHistory(SaveSearchDto dto) {
		if(!checkIfSearchExistsInHistory(dto)){
			String sql = "INSERT INTO " + TABLE_NAME + " (query, user, searchName, myDataOnly, includeDeleted, createdAt, sortByDate, sortOrder, searchType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
			SqlUpdate su = new SqlUpdate()
			su.setDataSource(getDataSource())
			su.setSql(sql)
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // query
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // user
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // searchName
			su.declareParameter(new SqlParameter(Types.BOOLEAN)) // myDataOnly
			su.declareParameter(new SqlParameter(Types.BOOLEAN)) // includeDeleted
			su.declareParameter(new SqlParameter(Types.TIMESTAMP)) // createdAt
			su.declareParameter(new SqlParameter(Types.BOOLEAN)) // sortByDate
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // sortOrder
			su.declareParameter(new SqlParameter(Types.VARCHAR)) // searchType
			def date = new Date()
			dto.createdAt = date
			Object[] parameters = [dto.searchString, dto.user, "HISTORY", dto.myDataOnly, dto.includeDeleted, new Timestamp(date.getTime()), dto.sortByDate, dto.sortOrder, dto.searchType]
			KeyHolder keyHolder = new GeneratedKeyHolder()
			su.setReturnGeneratedKeys(true)
			su.update(parameters, keyHolder)
		}else{
			dto.createdAt = new Date()
			updateSearchDtoCreated(dto)
		}
		return dto
	}
	
	@Override
	def saveSearchFavorite(SaveSearchDto dto) {
		String sql = "INSERT INTO " + TABLE_NAME + " (query, user, searchName, myDataOnly, includeDeleted, createdAt, favorite, sortByDate, sortOrder, searchType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // query
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // user
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // searchName
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // myDataOnly
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // includeDeleted
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); //createdAt
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // favorite
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // sortByDate
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // sortOrder
		su.declareParameter(new SqlParameter(Types.VARCHAR)) // searchType
		def date = new Date()
		dto.createdAt = date
		Object[] parameters = [dto.searchString, dto.user, dto.searchName, dto.myDataOnly, dto.includeDeleted, new Timestamp(date.getTime()), true, dto.sortByDate, dto.sortOrder, dto.searchType];
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		return dto
	}

	@Override
	def getSearchHistoryDtos(int maxToReturn, String user) {
		//String parsedUser = user.replaceAll("\\","\\\\")
		//String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user='${parsedUser}' AND favorite = false ORDER BY createdAt DESC  limit ${maxToReturn}";
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user= ? AND favorite = false ORDER BY createdAt DESC  limit ?";
		List<SaveSearchDto> savedSearches = [];
		try{
			savedSearches = getJdbcTemplate().query(sql, ROW_MAPPER, user, maxToReturn)
		}
		catch(EmptyResultDataAccessException ex){
			// Its OK to find no data.
			println (ex)
		}
		return savedSearches;
	}
	
	@Override
	def getSearchFavoriteDtos(int maxToReturn, String user) {
		//String parsedUser = user.replaceAll("\\","\\\\")
		//String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user='${parsedUser}' AND favorite = true ORDER BY createdAt DESC limit ${maxToReturn}";
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE user= ? AND favorite = true ORDER BY createdAt DESC limit ?";
		List<SaveSearchDto> savedSearches = [];
		try{
			savedSearches = getJdbcTemplate().query(sql, ROW_MAPPER, user, maxToReturn);
		}
		catch(EmptyResultDataAccessException ex){
			// Its OK to find no data.
			println (ex)
		}
		return savedSearches;
	}
	
	def updateSearchDtoCreated(SaveSearchDto saveSearchDto){
		String sql = "UPDATE " + TABLE_NAME  + " SET createdAt = ? WHERE id = ?"
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // dateCreated
		su.declareParameter(new SqlParameter(Types.BIGINT)); // searchId
		Object[] parameters = [new Timestamp(saveSearchDto.getCreatedAt().getTime()), saveSearchDto.id]
		su.update(parameters)
	}

	@Override
	def getSearchHistoryDtos(int maxToReturn) {
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE favorite = false ORDER BY createdAt DESC limit ${maxToReturn}";
		List<SaveSearchDto> savedSearches = [];
		try{
			savedSearches = getJdbcTemplate().query(sql, ROW_MAPPER)
		}
		catch(EmptyResultDataAccessException ex){
			// Its OK to find no data.
			println (ex)
		}
		return savedSearches;
	}
		
	@Override
	SaveSearchDto getSearchDto(Long id){
		String sql = "SELECT * FROM " + TABLE_NAME  + " WHERE id = ?"
		def savedSearch
		try{
			savedSearch = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id)
		}catch(Exception e){
			println "Couldn't find search with id " + id.toString()
		}
		return savedSearch
	}
	
	@Override
	def deleteSearch(Long id){
		String sql = "DELETE FROM " + TABLE_NAME  + " WHERE id = ?"
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.BIGINT)); // searchId
		Object[] parameters = [id]
		su.update(parameters)
	}
	
}
