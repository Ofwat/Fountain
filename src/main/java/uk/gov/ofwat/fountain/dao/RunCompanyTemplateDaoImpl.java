package uk.gov.ofwat.fountain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;

public class RunCompanyTemplateDaoImpl extends JdbcDaoSupport implements
		RunCompanyTemplateDao {

	private CompanyDao companyDao;

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public static final String RUN_COMPANY_TEMPLATE_TABLE_NAME = "tbl_run_company_template";
	public static final String RUN_COMPANY_TEMPLATE_COMPANY_TABLE_NAME = "tbl_run_company_template_company";

	private final RowMapper<RunCompanyTemplate> RUN_TEMPLATE_ROW_MAPPER = new RowMapper<RunCompanyTemplate>() {
		public RunCompanyTemplate mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			Date dateCreated = rs.getDate("created");
			String createdBy = rs.getString("createdBy");

			RunCompanyTemplate runCompanyTemplate = new RunCompanyTemplate();
			runCompanyTemplate.setId(id);
			runCompanyTemplate.setDescription(description);
			runCompanyTemplate.setName(name);
			runCompanyTemplate.setCreatedBy(createdBy);
			runCompanyTemplate.setCreated(dateCreated);

			List<Company> companies = companyDao.getCompaniesForRunCompanyTemplate(id);
			runCompanyTemplate.setCompanies(companies);
			List<Integer> companyIds = new ArrayList<Integer>();
			for (Company company: companies) {
				companyIds.add(company.getId());
			}
			runCompanyTemplate.setCompanyIds(companyIds);
			
			return runCompanyTemplate;
		}
	};

	@Override
	public List<RunCompanyTemplate> getAll() {
		String sql = "SELECT id, description, name, created, createdBy FROM "
				+ RUN_COMPANY_TEMPLATE_TABLE_NAME + " where deleted = 0";
		List<RunCompanyTemplate> runTemplates = getJdbcTemplate().query(
				sql, RUN_TEMPLATE_ROW_MAPPER);
		return runTemplates;
	}

	@Override
	public RunCompanyTemplate create(RunCompanyTemplate runCompanyTemplate) {
		String sql = "INSERT INTO "
				+ RUN_COMPANY_TEMPLATE_TABLE_NAME
				+ " (name, description, created, createdBy) VALUES (?, ?, ?, ?)";
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
		Timestamp now = new Timestamp((new Date()).getTime());
		Object[] parameters = new Object[] { runCompanyTemplate.getName(),
				runCompanyTemplate.getDescription(), now,
				runCompanyTemplate.getCreatedBy() };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		runCompanyTemplate.setId(keyHolder.getKey().intValue());
		runCompanyTemplate.setCreated(now);
		return runCompanyTemplate;
	}

	@Override
	public boolean delete(int runCompanyTemplateId) {
		String sql = "UPDATE " + RUN_COMPANY_TEMPLATE_TABLE_NAME
				+ " SET DELETED = 1 WHERE id = ?";
		int result = getJdbcTemplate().update(sql, runCompanyTemplateId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void addCompany(int runCompanyTemplateId, int companyId) {
		String sql = "INSERT INTO " + RUN_COMPANY_TEMPLATE_COMPANY_TABLE_NAME
				+ " (runCompanyTemplateId, companyId) VALUES (?, ?)";
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.INTEGER));
		su.declareParameter(new SqlParameter(Types.INTEGER));
		Object[] parameters = new Object[] { runCompanyTemplateId, companyId };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
	}

	@Override
	public void removeAllCompanies(int runCompanyTemplateId) {
		String sql = "DELETE FROM " + RUN_COMPANY_TEMPLATE_COMPANY_TABLE_NAME
				+ " WHERE runCompanyTemplateId = ?";
		getJdbcTemplate().update(sql, runCompanyTemplateId);
	}

	@Override
	public void removeCompany(int runCompanyTemplateId, int companyId) {
		String sql = "DELETE FROM " + RUN_COMPANY_TEMPLATE_COMPANY_TABLE_NAME
				+ " WHERE runCompanyTemplateId = ? and companyId = ?";
		getJdbcTemplate().update(sql, runCompanyTemplateId, companyId);
	}

	@Override
	public RunCompanyTemplate findById(int runCompanyTemplateId) {
		String sql = "SELECT id, description, name, created, createdBy FROM "
				+ RUN_COMPANY_TEMPLATE_TABLE_NAME
				+ " WHERE id=? and deleted = 0";
		try {
			return getJdbcTemplate().queryForObject(sql,
					RUN_TEMPLATE_ROW_MAPPER, runCompanyTemplateId);
		} catch (DataAccessException e) {
			// Its OK to find no RunCompanyTemplate.
			return null;
		}
	}

}
