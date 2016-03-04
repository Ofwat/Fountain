package uk.gov.ofwat.fountain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.domain.RunCompanyTemplate;
import uk.gov.ofwat.fountain.domain.RunRole;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.run.RunTemplate;

public class RunDaoImpl extends SimpleJdbcDaoSupport implements RunDao {

	private static final String RUN_TABLE_NAME = "tbl_run";

	private RunTemplateDao runTemplateDao;
	private RunModelDao runModelDao;
	private RunCompanyTemplateDao runCompanyTemplateDao;

	public RunModelDao getRunModelDao() {
		return runModelDao;
	}

	public void setRunModelDao(RunModelDao runModelDao) {
		this.runModelDao = runModelDao;
	}

	public RunCompanyTemplateDao getRunCompanyTemplateDao() {
		return runCompanyTemplateDao;
	}

	public void setRunCompanyTemplateDao(RunCompanyTemplateDao runCompanyTemplateDao) {
		this.runCompanyTemplateDao = runCompanyTemplateDao;
	}

	public RunTemplateDao getRunTemplateDao() {
		return runTemplateDao;
	}

	public void setRunTemplateDao(RunTemplateDao runTemplateDao) {
		this.runTemplateDao = runTemplateDao;
	}

	private final RowMapper<Run> RUN_ROW_MAPPER = new RowMapper<Run>() {
		public Run mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			String description = rs.getString("description");
			String name = rs.getString("name");
			boolean completed = rs.getBoolean("completed");
			boolean deleted = rs.getBoolean("deleted");
			int runTemplateId = rs.getInt("runTemplateId");
			int runCompanyTemplateId = rs.getInt("runCompanyTemplateId");
			Date created = rs.getTimestamp("created");
			String createdBy = rs.getString("createdBy");
			Date lastModified = rs.getTimestamp("lastModified");
			String lastModifiedBy = rs.getString("lastModifiedBy");
			boolean updating = rs.getBoolean("updating");
			int agendaId = rs.getInt("agendaId");
			int roleId = rs.getInt("roleId");
			boolean adminOnly = rs.getBoolean("adminOnly");

			Run r = new Run();
			r.setId(id);
			r.setDescription(description);
			r.setName(name);
			r.setCompleted(completed);
			r.setDeleted(deleted);
			r.setCreatedBy(createdBy);
			r.setCreated(created);
			r.setLastModified(lastModified);
			r.setLastModifiedBy(lastModifiedBy);
			r.setUpdating(updating);
			r.setAgendaId(agendaId);
			r.setRunRole(RunRole.getById(roleId));
			r.setAdminOnly(adminOnly);
			RunTemplate runTemplate = runTemplateDao.findById(runTemplateId);
			if (null != runTemplate) {
				r.setRunTemplate(runTemplate);
			}
			RunCompanyTemplate runCompanyTemplate = runCompanyTemplateDao.findById(runCompanyTemplateId);
			if (null != runCompanyTemplate) {
				r.setRunCompanyTemplate(runCompanyTemplate);
			}
			int srcRunId = rs.getInt("srcRunId");
			r.setDataSourceId(srcRunId);
			int srcTagId = rs.getInt("srcTagId");
			r.setBaseTagId(srcTagId);
			r.setCode(rs.getString("code"));
			return r;
		}
	};

	private final RowMapper<Integer> MAX_ID_RUN_ROW_MAPPER = new RowMapper<Integer>() {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			int id = rs.getInt("id");
			return id;
		}
	};

	private final RowMapper<Integer> RUN_COMPANY_ROW_MAPPER = new RowMapper<Integer>() {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			int companyId = rs.getInt("companyId");
			return companyId;
		}
	};

	public Run findById(int id) {
		String sql = "SELECT id, description, name, completed, deleted, runTemplateId, runCompanyTemplateId, srcRunId, srcTagId, created, createdBy, lastModified, lastModifiedBy, updating, agendaId, roleId, adminOnly, code FROM "
				+ RUN_TABLE_NAME + " WHERE id=?";
		Run run = null;
		try {
			run = getSimpleJdbcTemplate().queryForObject(sql, RUN_ROW_MAPPER, id);
			readRunCompanies(run);
		} catch (DataAccessException e) {
			// Its OK to find no run
		}
		return run;
	}

	public Integer getNextId() {
		String sql = "select max(id) 'id' from tbl_run";
		Integer maxId = null;
		try {
			maxId = getSimpleJdbcTemplate().queryForObject(sql, MAX_ID_RUN_ROW_MAPPER);
		} catch (DataAccessException e) {
			// Its OK to find no run
		}
		return maxId +1;
	}

	private void readRunCompanies(Run run) {
		String sql;
		sql = "SELECT * FROM tbl_run_company where runId = ?";
		List<Integer> companyIds = getSimpleJdbcTemplate().query(sql, RUN_COMPANY_ROW_MAPPER, run.getId());
		run.setCompanyIds(companyIds);
	}

	public int create(Run run) {
		run.setCode(run.getAgenda().getCode() + "_" + getNextId());

		String sql = "INSERT INTO "
				+ RUN_TABLE_NAME
				+ " (description, name, completed, deleted, runTemplateId, runCompanyTemplateId, srcRunId, srctagId, created, createdBy, updating, agendaId, roleId, adminOnly, code ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // completed
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // deleted
		su.declareParameter(new SqlParameter(Types.INTEGER)); // runTemplateId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // runCompanyTemplateId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // srcRunId
		su.declareParameter(new SqlParameter(Types.BIGINT)); // srctagId
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // updating
		su.declareParameter(new SqlParameter(Types.INTEGER)); // runagendaId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // roleId
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // adminOnly
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // code

		Object[] parameters = new Object[] { run.getDescription(),
				run.getName(), false, false, run.getRunTemplate().getId(),
				run.getRunCompanyTemplate().getId(),
				run.getDataSourceId(), run.getBaseTagId(),
				new Timestamp(new Date().getTime()), run.getCreatedBy(),
				run.isUpdating(), run.getAgendaId(), run.getRunRole().getId(),
				false, run.getCode() };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		run.setId(keyHolder.getKey().intValue());
		writeRunCompanies(run);
		return run.getId();
	}

	private void writeRunCompanies(Run run) {
		StringBuffer sbuf = new StringBuffer("INSERT INTO tbl_run_company (runId, companyId) VALUES ");
		boolean first = true;
		for (Integer id : run.getCompanyIds()) {
			if (!first) {
				sbuf.append(",");
			} else {
				first = false;
			}
			sbuf.append("(");
			sbuf.append(run.getId());
			sbuf.append(", ");
			sbuf.append(id);
			sbuf.append(")");
		}
		getSimpleJdbcTemplate().update(sbuf.toString());
	}

	public void update(Run run) {
		String sql = "UPDATE " + RUN_TABLE_NAME + " SET " + "name=?, "
				+ "description=?, " + "completed=?, " + "runTemplateId=?, "
				+ "runCompanyTemplateId=?, "
				+ "srcRunId=?, " + "srcTagId=?, " + "lastModified=?, "
				+ "lastModifiedBy=?, " + "updating=?, " + "agendaId=?, "
				+ "roleId=?, " + "adminOnly=? " + "WHERE ID=?";
		RunTemplate runTemplate = run.getRunTemplate();
		Integer runTemplateId = null;
		if (null != runTemplate) {
			runTemplateId = runTemplate.getId();
		} else {
			runTemplateId = 0;
		}
		RunCompanyTemplate runCompanyTemplate = run.getRunCompanyTemplate();
		Integer runCompanyTemplateId = null;
		if (null != runCompanyTemplate) {
			runCompanyTemplateId = runCompanyTemplate.getId();
		} else {
			runCompanyTemplateId = 0;
		}
		getSimpleJdbcTemplate().update(sql, run.getName(),
				run.getDescription(), run.isCompleted(), runTemplateId,
				runCompanyTemplateId,
				run.getDataSourceId(), run.getBaseTagId(),
				run.getLastModified(), run.getLastModifiedBy(),
				run.isUpdating(), run.getAgendaId(), run.getRunRole().getId(),
				run.isAdminOnly(), run.getId());
	}

	public void delete(int id) {
		String sql = "UPDATE " + RUN_TABLE_NAME + " SET " + "deleted=? "
				+ "WHERE ID=?";
		getSimpleJdbcTemplate().update(sql, true, id);
	}

	public List<Run> getAll() {
		String sql = "SELECT * FROM " + RUN_TABLE_NAME;
		List<Run> runs = getSimpleJdbcTemplate().query(sql, RUN_ROW_MAPPER,
				new Object[] {});
		for (Run run : runs) {
			readRunCompanies(run);
		}
		return runs;
	}

	public synchronized Run createRun(Run run) {
		run.setCode(run.getAgenda().getCode() + "_" + getNextId());

		String sql = "INSERT INTO "
				+ RUN_TABLE_NAME
				+ " (name, description, completed, deleted, runTemplateId, runCompanyTemplateId, srcRunId, srctagId, created, createdBy, updating, agendaId, roleId, adminOnly, code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		SqlUpdate su = new SqlUpdate();
		su.setDataSource(getDataSource());
		su.setSql(sql);
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // name
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // completed
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // deleted
		su.declareParameter(new SqlParameter(Types.INTEGER)); // runTemplateId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // runCompanyTemplateId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // srcRunId
		su.declareParameter(new SqlParameter(Types.BIGINT)); // srctagId
		su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // createdBy
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // updating
		su.declareParameter(new SqlParameter(Types.INTEGER)); // agendaId
		su.declareParameter(new SqlParameter(Types.INTEGER)); // roleId
		su.declareParameter(new SqlParameter(Types.BOOLEAN)); // adminOnly
		su.declareParameter(new SqlParameter(Types.VARCHAR)); // code

		Timestamp now = new Timestamp((new Date()).getTime());
		Object[] parameters = new Object[] { run.getName(),
				run.getDescription(), run.isCompleted(), run.isDeleted(),
				run.getRunTemplate().getId(), run.getRunCompanyTemplate().getId(), run.getDataSourceId(),
				run.getBaseTagId(), now, run.getCreatedBy(), run.isUpdating(),
				run.getAgendaId(), run.getRunRole().getId(), false, run.getCode() };
		KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
		su.update(parameters, keyHolder);
		run.setId(keyHolder.getKey().intValue());
		run.setCreated(now);
		writeRunCompanies(run);
		return run;
	}

	@Override
	public Run findDefault(Run run) {
		String sql = "SELECT id, description, name, completed, deleted, runTemplateId, runCompanyTemplateId, srcRunId, srcTagId, created, createdBy, lastModified, lastModifiedBy, updating, agendaId, roleId, adminOnly, code FROM "
				+ RUN_TABLE_NAME + " WHERE agendaId=? AND roleId=?";
		run = getSimpleJdbcTemplate().queryForObject(sql, RUN_ROW_MAPPER,
				run.getAgendaId(), RunRole.DEFAULT.getId());
		readRunCompanies(run);
		return run;
	}

	@Override
	public Run findByCode(String code) {
		String sql = "SELECT id, description, name, completed, deleted, runTemplateId, runCompanyTemplateId, srcRunId, srcTagId, created, createdBy, lastModified, lastModifiedBy, updating, agendaId, roleId, adminOnly, code FROM "
				+ RUN_TABLE_NAME + " WHERE code=?";
		Run run = null;
		try {
			run = getSimpleJdbcTemplate().queryForObject(sql, RUN_ROW_MAPPER, code);
			readRunCompanies(run);
		} catch (DataAccessException e) {
			// Its OK to find no run
		}
		return run;
	}

	// @Override
	// public void makeDefaultRunStandard(int agendaId) {
	// String sql = "UPDATE " + RUN_TABLE_NAME + " SET " +
	// "roleId=? " +
	// "WHERE agendaId=? " +
	// "AND roleId=?";
	// getSimpleJdbcTemplate().update(sql, RunRole.STANDARD.getId(), agendaId,
	// RunRole.DEFAULT.getId());
	// }

}
