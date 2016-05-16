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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.CompanyType;

/**
 * Cacheable as companyType is not a cached entity in it's own right 
 */
public class CompanyDaoImpl extends JdbcDaoSupport  implements CompanyDao{
	private static Log log = LogFactory.getLog(CompanyDaoImpl.class);
	private static final String COMPANY_TABLE_NAME = "tbl_company";
	private static final String COMPANY_TYPE_TABLE_NAME = "tbl_companytype";
	
	private final RowMapper<Company> ROW_MAPPER = new RowMapper<Company>() {
	    public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Company c = new Company();
	    	CompanyType t = new CompanyType();
	    	t.setId(rs.getInt("companyTypeId"));
	    	t.setCode(rs.getString("t.code"));
	    	t.setDescription(rs.getString("description"));
	        c.setId(rs.getInt("c.Id"));
	        c.setName(rs.getString("c.Name"));
	        c.setCode(rs.getString("c.Code"));
	        c.setCompanyType(t);
	        c.setExpired(rs.getBoolean("c.expired"));
	        return c;
	    }
	};
	
	private final RowMapper<CompanyType> CT_ROWMAPPER = new RowMapper<CompanyType>() {
		public CompanyType mapRow(ResultSet rs, int rowNum) throws SQLException {
			CompanyType t = new CompanyType();
	    	t.setId(rs.getInt("id"));
	    	t.setCode(rs.getString("code"));
	    	t.setDescription(rs.getString("description"));
			return t;
		}
	};

	public synchronized int create(Company company) {
        String sql = "INSERT INTO "+COMPANY_TABLE_NAME+" (name, code, companyTypeId, expired) VALUES (?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // code
        su.declareParameter(new SqlParameter(Types.INTEGER)); // companyTypeId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // expired
        Object[] parameters = new Object[] {company.getName(), company.getCode(), company.getCompanyType().getId(), company.isExpired()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);
        company.setId(keyHolder.getKey().intValue());
        return company.getId();
	}

	public Company findById(int id) {
		try {
			String sql = "SELECT * FROM "+COMPANY_TABLE_NAME+" as c inner join "+COMPANY_TYPE_TABLE_NAME+" as t on c.companytypeId=t.id WHERE c.Id=? AND c.visible=1";
			return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, id);
		}
		catch (EmptyResultDataAccessException ex) {
			log.error("Couldn't find id " + id, ex);
			throw ex;
		}
	}

	public List<Company> getAllCurrent() {
		String sql = "SELECT * FROM "+COMPANY_TABLE_NAME+" as c inner join "+COMPANY_TYPE_TABLE_NAME+" as t on c.companytypeId=t.id WHERE not c.expired<=>1 AND c.visible=1 ORDER BY t.code, c.name";
        return getJdbcTemplate().query(sql, ROW_MAPPER);
	}
	
	public List<CompanyType> getAllCompanyTypes() {
		String sql = "SELECT * FROM " + COMPANY_TYPE_TABLE_NAME;
		return getJdbcTemplate().query(sql, CT_ROWMAPPER, new Object[]{});
	}

	public Company findByCode(String company) {
		 String sql = "SELECT * FROM "+COMPANY_TABLE_NAME+" as c inner join "+COMPANY_TYPE_TABLE_NAME+" as t on c.companytypeId=t.id WHERE c.code=? AND c.visible=1";
	     return getJdbcTemplate().queryForObject(sql, ROW_MAPPER, company);
	}

	public int findCompanyTypeId(String companyTypeName) {
		int companyTypeId = 0;
		try
		{
			String sql = "SELECT id from " + COMPANY_TYPE_TABLE_NAME + " WHERE code = ?";
			companyTypeId = getJdbcTemplate().queryForObject(sql, new Object[]{companyTypeName}, Integer.class);
		}
		catch(EmptyResultDataAccessException dae){
			// return 0 for 'ALL'
		}
		return companyTypeId;
	}

	public CompanyType findByCompanyTypeId(int id) {
		String sql = "SELECT * FROM " + COMPANY_TYPE_TABLE_NAME + " WHERE id = ?";
		return getJdbcTemplate().queryForObject(sql, CT_ROWMAPPER, id);
	}
	public CompanyType findByCompanyTypeCode(String code) {
		String sql = "SELECT * FROM " + COMPANY_TYPE_TABLE_NAME + " WHERE code = ?";
		return getJdbcTemplate().queryForObject(sql, CT_ROWMAPPER, code.toUpperCase());
	}

	public List<Company> getAll() {
		String sql = "SELECT * FROM "+COMPANY_TABLE_NAME+" as c inner join "+COMPANY_TYPE_TABLE_NAME+" as t on c.companytypeId=t.id WHERE c.visible=1 ORDER BY c.expired, t.code, c.name";
        return getJdbcTemplate().query(sql, ROW_MAPPER);
	}

	public List<Company> getCompaniesForRunCompanyTemplate(int runCompanyTemplateId){
		String sql = "SELECT DISTINCT " +
				"c.*, t.* " +
				"FROM tbl_company AS c " +
				"inner join tbl_companytype as t on c.companytypeId = t.id " +
				"INNER JOIN " + RunCompanyTemplateDaoImpl.RUN_COMPANY_TEMPLATE_COMPANY_TABLE_NAME + " AS rctc on c.id = rctc.companyId " +
				"INNER JOIN " + RunCompanyTemplateDaoImpl.RUN_COMPANY_TEMPLATE_TABLE_NAME + " as rct on rctc.runCompanyTemplateId = ?; ";
		List<Company> companies = getJdbcTemplate().query(sql, ROW_MAPPER, runCompanyTemplateId);
		return companies;
	}
	
}
