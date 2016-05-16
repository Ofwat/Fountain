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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import uk.gov.ofwat.fountain.api.PopularityPeriod;
import uk.gov.ofwat.fountain.api.ReportServiceImpl;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.Entity;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.ItemProperties;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.ModelItem;
import uk.gov.ofwat.fountain.domain.ModelPropertiesMap;
import uk.gov.ofwat.fountain.domain.ReportDefinition;
import uk.gov.ofwat.fountain.domain.ReportDisplay;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;
import uk.gov.ofwat.fountain.domain.tag.Tag;
import uk.gov.ofwat.fountain.search.wrapper.ItemSearchWrapper;
import uk.gov.ofwat.fountain.search.wrapper.ReportSearchWrapper;

public class ReportDaoImpl  extends JdbcDaoSupport implements ReportDao {
	
	private ItemDao itemDao;
    private ModelDao modelDao;
    private RunDao runDao;
	private ModelPropertiesMapDao modelPropertiesMapDao;
	private ItemPropertiesDao itemPropertiesDao;
	private GroupDao groupDao;
	private RunModelTagDao runModelTagDao;
	private TeamDao teamDao;

	private static Logger logger = LoggerFactory
			.getLogger(ReportDaoImpl.class);	
	
	public void setRunModelTagDao(RunModelTagDao runModelTagDao) {
		this.runModelTagDao = runModelTagDao;
	}

	private static final String REPORTTABLE = "tbl_report";
	
	public void setTeamDao(TeamDao teamDao){
		this.teamDao = teamDao;
	}
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setModelDao(ModelDao modelDao) {
		this.modelDao = modelDao;
	}

	public void setModelPropertiesMapDao(ModelPropertiesMapDao modelPropertiesMapDao) {
		this.modelPropertiesMapDao = modelPropertiesMapDao;
	}

    public RunDao getRunDao() {
        return runDao;
    }

    public void setRunDao(RunDao runDao) {
        this.runDao = runDao;
    }

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setItemPropertiesDao(ItemPropertiesDao itemPropertiesDao) {
		this.itemPropertiesDao = itemPropertiesDao;
	}

	public int create(ReportDefinition reportDef) {
		String sql = "INSERT INTO "+ REPORTTABLE +" (Name, User, created, lastModified, public, teamId, groupId, deleted, iplRun, readOnly, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // Name
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // user
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // created
        su.declareParameter(new SqlParameter(Types.TIMESTAMP)); // lastModified - same as created
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // public report
        su.declareParameter(new SqlParameter(Types.INTEGER)); // teamId
        su.declareParameter(new SqlParameter(Types.INTEGER)); // groupId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // deleted
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // iplRun
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // readOnly
        su.declareParameter(new SqlParameter(Types.VARCHAR)); // description
        Integer groupId = null;
        if(null != reportDef.getGroup()){
        	groupId = reportDef.getGroup().getId();
        }
        Object[] parameters = new Object[] {reportDef.getName(), reportDef.getOwnerUser(), new Timestamp((new Date()).getTime()), new Timestamp((new Date()).getTime()),reportDef.isPublicReport(), 
        		reportDef.getTeamId(), groupId,  reportDef.isDeleted(), reportDef.isIplRun(), reportDef.isReadOnly(), reportDef.getDescription()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        try {
			su.update(parameters, keyHolder);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
			e.getCause();
		}
        System.out.println("keyHolder.getKey().intValue() " + keyHolder.getKey().intValue());
        reportDef.setId(keyHolder.getKey().intValue());
        System.out.println("reportDef.getId() " + reportDef.getId());
        
        StringBuffer sbuf = new StringBuffer("INSERT INTO tbl_report_interval (reportId, intervalId) VALUES ");
        boolean first = true;
        for(Integer id: reportDef.getIntervalIds()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append(id);
        	sbuf.append(")");
        	
        }
        getJdbcTemplate().update(sbuf.toString());

        logger.info("reportDef.getId() " + reportDef.getId());
        
        sbuf = new StringBuffer("INSERT INTO tbl_report_items (reportId, itemId, modelId) VALUES ");
        first = true;
        for(ModelItem mi: reportDef.getModelItems()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append(mi.getItemId());
        	sbuf.append(", ");
        	sbuf.append(mi.getModelId());
        	sbuf.append(")");
        	
        }
        getJdbcTemplate().update(sbuf.toString());
       
        logger.info("reportDef.getId() " + reportDef.getId());

		// add new companies if there are any!
		if (!reportDef.getCompanyIds().isEmpty()) {
	        sbuf = new StringBuffer("INSERT INTO tbl_report_company (reportId, companyId) VALUES ");
	        first = true;
	        for(Integer id: reportDef.getCompanyIds()){
	        	if(! first){
	        		sbuf.append(",");
	        	}
	        	else{
	        		first = false;
	        	}
	        	sbuf.append("(" );
	        	sbuf.append(reportDef.getId());
	        	sbuf.append(", ");
	        	sbuf.append(id);
	        	sbuf.append(")");
	        	
	        }
	        getJdbcTemplate().update(sbuf.toString());
        }

        System.out.println("reportDef.getId() " + reportDef.getId());

        // add runTags if there are any!
        if (!reportDef.getRunTags().isEmpty()) {
            sbuf = new StringBuffer("INSERT INTO tbl_report_runs (reportId, runId, tagId) VALUES ");
            first = true;
            for(RunTag runTag: reportDef.getRunTags()){
                if(! first){
                    sbuf.append(",");
                }
                else{
                    first = false;
                }
                sbuf.append("(" );
                sbuf.append(reportDef.getId());
                sbuf.append(", ");
                sbuf.append(runTag.getRun().getId());
                sbuf.append(", ");
                sbuf.append(runTag.getRunModelTag().getId());
                sbuf.append(")");

            }
            getJdbcTemplate().update(sbuf.toString());
        }

        sbuf = new StringBuffer("INSERT INTO tbl_report_layout (reportId, position, entry) VALUES ");
        first = true;
        for(String entry: reportDef.getLayoutLeft()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append("'left', '");
        	sbuf.append(entry);
        	sbuf.append("')");
        	
        }
        for(String entry: reportDef.getLayoutTop()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append("'top', '");
        	sbuf.append(entry);
        	sbuf.append("')");
        	
        }
        getJdbcTemplate().update(sbuf.toString());
        
        System.out.println("reportDef.getId() " + reportDef.getId());

        // create the values in tbl_report_display
        sql = "INSERT INTO tbl_report_display "+
                "(reportId, cgs, description, unit, boncode, model, companyName, companyAcronym, runName, runDescription, tagName, agendaName, agendaCode) "+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ReportId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // cgs
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // description
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // unit
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // boncode
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // model
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // companyName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // companyAcronym
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // runName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // runDescription
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // tagName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // agendaName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // agendaCode
        parameters = new Object[] {reportDef.getId(), reportDef.getReportDisplay().isDisplayCGs(), 
        		reportDef.getReportDisplay().isDisplayDesc(), reportDef.getReportDisplay().isDisplayUnit(), reportDef.getReportDisplay().isDisplayBoncode(), 
        		reportDef.getReportDisplay().isDisplayModel(), reportDef.getReportDisplay().isDisplayCompanyName(), reportDef.getReportDisplay().isDisplayCompanyAcronym(),
                reportDef.getReportDisplay().isDisplayRunName(), reportDef.getReportDisplay().isDisplayRunDesc(), reportDef.getReportDisplay().isDisplayTagDisplayName(), reportDef.getReportDisplay().isDisplayAgendaName(), reportDef.getReportDisplay().isDisplayAgendaCode()};
        keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);

        System.out.println("reportDef.getId() 0: " + reportDef.getId());

        return reportDef.getId();
	}
	
	
	private final RowMapper<ReportSearchWrapper> REPORT_SEARCH_WRAPPER_MAPPER = new RowMapper<ReportSearchWrapper>() {
		public ReportSearchWrapper mapRow(ResultSet rs, int rowNum) throws SQLException{
			ReportSearchWrapper rsw = new ReportSearchWrapper();
			//String sqlBasic = "select id, name, user, lastModified, deleted, public, groupId, iplRun, description from tbl_report where id = " + reportId;
			rsw.setId(rs.getInt("id"));
			rsw.setDeleted(rs.getBoolean("deleted"));
			rsw.setGroupId(rs.getInt("groupId"));
			rsw.setIplRun(rs.getBoolean("iplRun"));
			rsw.setLastModified(rs.getTimestamp("lastModified"));
			rsw.setName(rs.getString("name"));
			rsw.setPublicReport(rs.getBoolean("public"));
			rsw.setUser(rs.getString("user"));
			rsw.setReadOnly(rs.getBoolean("readOnly"));
			rsw.setDescription(rs.getString("description"));
			return rsw;
		}
	};
	
	private final RowMapper<ItemSearchWrapper> REPORT_ITEM_SEARCH_WRAPPER_MAPPER = new RowMapper<ItemSearchWrapper>() {
		public ItemSearchWrapper mapRow(ResultSet rs, int rowNum) throws SQLException{
			ItemSearchWrapper isw = new ItemSearchWrapper();
			isw.setId(rs.getInt("id"));
			isw.setCode(rs.getString("code"));
			isw.setDescription(rs.getString("description"));
			isw.setDefinition(rs.getString("definition"));
			isw.setModelId(rs.getInt("modelId"));
			isw.setUnit(rs.getString("unit"));
			isw.setVersion(rs.getInt("version"));
			return isw;
		}
	};
	
	private final RowMapper<ReportDefinition> ROW_MAPPER = new RowMapper<ReportDefinition>() {
	    public ReportDefinition mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	ReportDefinition rd = new ReportDefinition();
	    	
	    	rd.setId(rs.getInt("Id"));
	        rd.setName(rs.getString("Name"));
	        rd.setOwnerUser(rs.getString("user"));
	        rd.setTeamId(rs.getInt("teamId"));
	        int groupId = rs.getInt("groupId");
	        if(0 != groupId){
	        	rd.setGroup(groupDao.findById(groupId));
	        }
	        rd.setDeleted(rs.getBoolean("deleted"));
	        rd.setPublicReport(rs.getBoolean("public"));
	        rd.setIplRun(rs.getBoolean("iplRun"));
	        rd.setReadOnly(rs.getBoolean("readOnly"));
	        rd.setDescription(rs.getString("description"));
	        return rd;
	    }
	};	
	
	private final RowMapper<Integer> ID_ROW_MAPPER = new RowMapper<Integer>() {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("idValue");
		}
	};

    private final RowMapper<Long> ID_ROW_MAPPER_LONG = new RowMapper<Long>() {
        public Long mapRow(ResultSet rs, int rowNum) throws SQLException { return rs.getLong("idValue" ); }
    };

	private final RowMapper<String> REPORT_LAYOUT_ENTRY_ROW_MAPPER = new RowMapper<String>() {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("entry");
		}
	};
	
	private final RowMapper<ReportSummary> SUMMARY_ROW_MAPPER = new RowMapper<ReportSummary>(){
		public ReportSummary mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReportSummary reportSummary = new ReportSummary();
			reportSummary.setId(rs.getInt("id"));
			reportSummary.setName(rs.getString("name"));
			reportSummary.setLastModified(rs.getTimestamp("lastModified"));
			reportSummary.setTeamId(rs.getInt("teamId"));
			reportSummary.setPublicReport(rs.getBoolean("public"));
			reportSummary.setReadOnly(rs.getBoolean("readOnly"));
			reportSummary.setDescription(rs.getString("description"));
			return reportSummary;
		}
	};
	
	private final RowMapper<ModelItem> MODEL_ITEM_ROW_MAPPER = new RowMapper<ModelItem>() {
		
		public ModelItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			ModelItem mi = new ModelItem();
			Model model = modelDao.findById(rs.getInt("modelId"));
			Item item = itemDao.findById(rs.getInt("itemId"));
			ModelPropertiesMap mpm = modelPropertiesMapDao.findByModelAndItem(model.getId(), item.getId());
			ItemProperties itemProperties = itemPropertiesDao.findByItemAndModel(item.getId(), model.getId());  
			
			mi.setItemId(item.getId());
			mi.setItemName(item.getName());
			mi.setItemCode(item.getCode());
			mi.setModelId(model.getId());
			mi.setModelCode(model.getCode());
			mi.setModelName(model.getName());
			mi.setModelPropertiesMapId(mpm.getId());
			mi.setDescription(itemProperties.getDescription());
			return mi;
		}
	};
	
	
	private final RowMapper<ReportDisplay> REPORT_DISPLAY_ROW_MAPPER = new RowMapper<ReportDisplay>() {
		public ReportDisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReportDisplay rd = new ReportDisplay();
			rd.setDisplayBoncode(rs.getBoolean("boncode"));
			rd.setDisplayCGs(rs.getBoolean("cgs"));
			rd.setDisplayCompanyAcronym(rs.getBoolean("companyAcronym"));
			rd.setDisplayCompanyName(rs.getBoolean("companyName"));
			rd.setDisplayDesc(rs.getBoolean("description"));
			rd.setDisplayModel(rs.getBoolean("model"));
			rd.setDisplayUnit(rs.getBoolean("unit"));
            rd.setDisplayRunName(rs.getBoolean("runName"));
            rd.setDisplayRunDesc(rs.getBoolean("runDescription"));
            rd.setDisplayTagDisplayName(rs.getBoolean("tagName"));
            rd.setDisplayAgendaName(rs.getBoolean("agendaName"));
            rd.setDisplayAgendaCode(rs.getBoolean("agendaCode"));
			return rd;
		}
	};
    private final RowMapper<RunTag> RUN_TAG_ROW_MAPPER = new RowMapper<RunTag>() {
        public RunTag mapRow(ResultSet rs, int rowNum) throws SQLException {
            Run run = new Run();
            run.setId(rs.getInt("runId"));
            RunModelTag tag = new RunModelTag();
            tag.setId(rs.getInt("tagId"));
            RunTag rt = new RunTag(run, tag);
            return rt;
        }
    };

    public ReportDefinition findById(int id){
		String sql = "SELECT * FROM "+ REPORTTABLE +" WHERE id = ?";
					
		ReportDefinition rd = getJdbcTemplate().queryForObject(sql, ROW_MAPPER, new Object[]{id});

		rd.setId(id);
		rd.setName(rd.getName());
		rd.setOwnerUser(rd.getOwnerUser());
		rd.setReadOnly(rd.isReadOnly());
		
		sql = "SELECT * FROM tbl_report_display where reportId = ?";
		
		ReportDisplay repDisp = getJdbcTemplate().queryForObject(sql, REPORT_DISPLAY_ROW_MAPPER, id);
		rd.setReportDisplay(repDisp);
		
		sql = "SELECT entry FROM tbl_report_layout where reportId = ? and position = 'left'";
		List<String> left = getJdbcTemplate().query(sql, REPORT_LAYOUT_ENTRY_ROW_MAPPER, new Object[]{id});
		rd.setLayoutLeft(left);
		
		sql = "SELECT entry FROM tbl_report_layout where reportId = ? and position = 'top'";
		List<String> top = getJdbcTemplate().query(sql, REPORT_LAYOUT_ENTRY_ROW_MAPPER, new Object[]{id});
		rd.setLayoutTop(top);
		
		sql = "SELECT itemId, modelId FROM tbl_report_items where reportId = ?";
		List<ModelItem> modelItems = getJdbcTemplate().query(sql, MODEL_ITEM_ROW_MAPPER, new Object[]{id});
		rd.setModelItems(modelItems);
		
		sql = "SELECT intervalId as idValue FROM tbl_report_interval where reportId = ?";
		List<Integer> intervalIds = getJdbcTemplate().query(sql, ID_ROW_MAPPER, new Object[]{id});
		rd.setIntervalIds(intervalIds);

		sql = "SELECT companyId as idValue FROM tbl_report_company where reportId = ?";
		List<Integer> companyIds = getJdbcTemplate().query(sql, ID_ROW_MAPPER, new Object[]{id});
		rd.setCompanyIds(companyIds);

        sql = "select runId, tagId from tbl_report_runs where reportId = ?";
        List<RunTag> runTags = getJdbcTemplate().query(sql, RUN_TAG_ROW_MAPPER, new Object[]{id});
        rd.setRunTags(runTags);

		return rd;	
	}

	public List<Integer> findReportCompaniesById(int id){
		
		String sql = "SELECT companyId as idValue FROM tbl_report_company where reportId = ?";
		List<Integer> companyIds = getJdbcTemplate().query(sql, ID_ROW_MAPPER, new Object[]{id});
		
		return companyIds;	
	}
	
	public List<ReportSummary> findReportsByItemId(Integer itemId){
		String sql = "SELECT r.id, r.name, r.lastModified, r.public, r.teamid, r.iplRun, r.readOnly, r.description FROM "+ REPORTTABLE 
				+" r, tbl_report_items ri WHERE ri.itemId = ? and r.id = ri.reportId order by lastModified desc";
		
		return getJdbcTemplate().query(sql, SUMMARY_ROW_MAPPER,  itemId);
	}	
	
	
	public List<Integer> findReportRunsById(int id){
		
		String sql = "SELECT runId as idValue FROM tbl_report_runs where reportId = ?";
		List<Integer> runIds = getJdbcTemplate().query(sql, ID_ROW_MAPPER, new Object[]{id});
		
		return runIds;	
	}
	
	public List<ReportSummary> getReportsForTeam(Integer teamId) {
		return getReportsForTeam(teamId, null);
	}
	
	public List<ReportSummary> getReportsForTeam(Integer teamId, Integer limit) {
		String sql = "SELECT id, name, lastModified, public, teamid, iplRun, readOnly, description FROM "+ REPORTTABLE 
				+" WHERE (teamid = ? and deleted = 0) or (public=1 and deleted=0) order by teamid, lastModified desc";
		if((limit != null) && (limit != 0)){
			sql = sql + " limit " + limit;
		}
		return getJdbcTemplate().query(sql, SUMMARY_ROW_MAPPER,  teamId);
	}
	
	public List<ReportSummary> getReportsForTeams(List<Integer> teamIds) {
		return getReportsForTeams(teamIds, null);
	}
	
	// get more than just this team, get all teams for the user
	public List<ReportSummary> getReportsForTeams(List<Integer> teamIds, Integer limit) {

		List<ReportSummary> reportSummaries = new ArrayList<ReportSummary>();
		for(Integer teamId : teamIds){
			String sql = "SELECT id, name, lastModified, public, teamid, iplRun, readOnly, description FROM "+ REPORTTABLE 
			+" WHERE (teamid = ? and deleted = 0) order by teamid, lastModified desc";
			if((limit != null) && (limit != 0)){				
				sql = sql + " limit " + limit;
			}
			List<ReportSummary> rs = getJdbcTemplate().query(sql, SUMMARY_ROW_MAPPER,  teamId);
			reportSummaries.addAll(rs);
		}

		
		//for all the teams we've not already got get up to limit public reports ordered by date. 
		List<Team> teams = teamDao.getAllTeams();
		//Knock out all the ones we already have...
		List<Integer> teamsToAddPublicReports = new ArrayList<Integer>();
		for(Team team: teams){
			if(!teamIds.contains(team.getId())){
				teamsToAddPublicReports.add(team.getId());
			}
		}
		
		for(Integer teamId : teamsToAddPublicReports){
			//NamedParameterJdbcTemplate npjdbc = new NamedParameterJdbcTemplate(getJdbcTemplate().getJdbcOperations());
			String sql = "SELECT id, name, lastModified, public, teamid, iplRun, readOnly, description FROM "+ REPORTTABLE 
			+" WHERE (teamid = ? and deleted = 0 and public = 1) order by teamid, lastModified desc";
			if((limit != null) && (limit != 0)){				
				sql = sql + " limit " + limit;
			}
			List<ReportSummary> rs = getJdbcTemplate().query(sql, SUMMARY_ROW_MAPPER,  teamId);
			reportSummaries.addAll(rs);
		}		
		return reportSummaries;
	}
	
	public List<ReportSummary> getAll() {
		String sql = "SELECT id, name, lastModified, public, teamid, iplRun, readOnly, description FROM "+ REPORTTABLE ;
		return getJdbcTemplate().query(sql, SUMMARY_ROW_MAPPER);
	}

	public ReportSummary findSummaryById(int reportId) {
		String sql = "SELECT id, name, lastModified, public, teamid, iplRun, readOnly, description FROM " + REPORTTABLE + " WHERE id = ?";
		try {
			return getJdbcTemplate().queryForObject(sql, SUMMARY_ROW_MAPPER, reportId);
		} catch (DataAccessException e) {
			// Its OK to have no report. 
			return null;
		}
	}

	public void delete(int id) {
		String sql = "UPDATE " + REPORTTABLE + " set deleted=1 WHERE id=?";
		getJdbcTemplate().update(sql, id);
		return;
	}

	public boolean updatePublishedStatus(boolean toPublish, int id) {
		String sql = "UPDATE " + REPORTTABLE + " set public=? " + "WHERE id=?"; 
		return (getJdbcTemplate().update(sql, toPublish ,id)==1);
	}

	public void update(ReportDefinition reportDef) {
		
		String sql = "UPDATE " + REPORTTABLE + " SET " +
		"name = ?, User = ?, lastModified =?, public = ?, groupId = ?, teamId = ?, iplRun = ? , readOnly = ?, description = ? where id=?";

        Integer groupId = null;
        if(null != reportDef.getGroup()){
        	groupId = reportDef.getGroup().getId();
        }
		getJdbcTemplate().update(sql, reportDef.getName(), reportDef.getOwnerUser(), new Date(),
				 reportDef.isPublicReport(), groupId, reportDef.getTeamId(), reportDef.isIplRun(), reportDef.isReadOnly(), reportDef.getDescription(), reportDef.getId());  
		
		// remove the old intervals, add the new ones
		sql = "DELETE FROM tbl_report_interval WHERE reportId=?";
		getJdbcTemplate().update(sql, reportDef.getId());
		 
		StringBuffer sbuf = new StringBuffer("INSERT INTO tbl_report_interval (reportId, intervalId) VALUES ");
        boolean first = true;
        for(Integer id: reportDef.getIntervalIds()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append(id);
        	sbuf.append(")");
        	
        }
        getJdbcTemplate().update(sbuf.toString());

        //remove the old runTags add the new ones.
        sql = "DELETE FROM tbl_report_runs WHERE reportId=?";
        getJdbcTemplate().update(sql, reportDef.getId());

        if (!reportDef.getRunTags().isEmpty()) {
	        sbuf = new StringBuffer("INSERT INTO tbl_report_runs (reportId, runId, tagId) VALUES ");
	        first = true;
	        for(RunTag runTag: reportDef.getRunTags()){
	            if(! first){
	                sbuf.append(",");
	            }
	            else{
	                first = false;
	            }
	            sbuf.append("(" );
	            sbuf.append(reportDef.getId());
	            sbuf.append(", ");
	            sbuf.append(runTag.getRun().getId());
	            sbuf.append(", ");
	            sbuf.append(runTag.getRunModelTag().getId());
	            sbuf.append(")");
	        }
	        getJdbcTemplate().update(sbuf.toString());
        }
        
        // remove the old items, add the new ones
		sql = "DELETE FROM tbl_report_items WHERE reportId=?";
		getJdbcTemplate().update(sql, reportDef.getId());
		
        sbuf = new StringBuffer("INSERT INTO tbl_report_items (reportId, itemId, modelId) VALUES ");
        first = true;
        for(ModelItem mi: reportDef.getModelItems()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append(mi.getItemId());
        	sbuf.append(", ");
        	sbuf.append(mi.getModelId());
        	sbuf.append(")");
        	
        }
	    getJdbcTemplate().update(sbuf.toString());
       
        // remove the old companies
		sql = "DELETE FROM tbl_report_company WHERE reportId=?";
		getJdbcTemplate().update(sql, reportDef.getId());
		
		// add new companies if there are any!
		if (!reportDef.getCompanyIds().isEmpty()) {
	        sbuf = new StringBuffer("INSERT INTO tbl_report_company (reportId, companyId) VALUES ");
	        first = true;
	        for(Integer id: reportDef.getCompanyIds()){
	        	if(! first){
	        		sbuf.append(",");
	        	}
	        	else{
	        		first = false;
	        	}
	        	sbuf.append("(" );
	        	sbuf.append(reportDef.getId());
	        	sbuf.append(", ");
	        	sbuf.append(id);
	        	sbuf.append(")");
	        	
	        }
	        getJdbcTemplate().update(sbuf.toString());
		}
		
        // remove the old layout, add the new ones
		sql = "DELETE FROM tbl_report_layout WHERE reportId=?";
		getJdbcTemplate().update(sql, reportDef.getId());
		
        sbuf = new StringBuffer("INSERT INTO tbl_report_layout (reportId, position, entry) VALUES ");
        first = true;
        for(String entry: reportDef.getLayoutLeft()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append("'left', '");
        	sbuf.append(entry);
        	sbuf.append("')");
        	
        }
        for(String entry: reportDef.getLayoutTop()){
        	if(! first){
        		sbuf.append(",");
        	}
        	else{
        		first = false;
        	}
        	sbuf.append("(" );
        	sbuf.append(reportDef.getId());
        	sbuf.append(", ");
        	sbuf.append("'top', '");
        	sbuf.append(entry);
        	sbuf.append("')");
        	
        }
        
        getJdbcTemplate().update(sbuf.toString());
        
        // update the report display options
        sql = "DELETE FROM tbl_report_display WHERE reportId=?";
		getJdbcTemplate().update(sql, reportDef.getId());
		
        // insert the new values in tbl_report_display
        sql = "INSERT INTO tbl_report_display "+
        	"(reportId, cgs, description, unit, boncode, model, companyName, companyAcronym, runName, runDescription, tagName, agendaName, agendaCode) "+
        	"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SqlUpdate su = new SqlUpdate();
        su.setDataSource(getDataSource());
        su.setSql(sql);
        su.declareParameter(new SqlParameter(Types.INTEGER)); // ReportId
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // cgs
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // description
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // unit
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // boncode
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // model
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // companyName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // companyAcronym
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // runName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // runDescription
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // tagName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // agendaName
        su.declareParameter(new SqlParameter(Types.BOOLEAN)); // agendaCode
        Object[] parameters = new Object[] {reportDef.getId(), reportDef.getReportDisplay().isDisplayCGs(), 
        		reportDef.getReportDisplay().isDisplayDesc(), reportDef.getReportDisplay().isDisplayUnit(), reportDef.getReportDisplay().isDisplayBoncode(), 
        		reportDef.getReportDisplay().isDisplayModel(), reportDef.getReportDisplay().isDisplayCompanyName(), reportDef.getReportDisplay().isDisplayCompanyAcronym(),
                reportDef.getReportDisplay().isDisplayRunName(), reportDef.getReportDisplay().isDisplayRunDesc(), reportDef.getReportDisplay().isDisplayTagDisplayName(), 
                reportDef.getReportDisplay().isDisplayAgendaName(), reportDef.getReportDisplay().isDisplayAgendaCode()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
		su.setReturnGeneratedKeys(true);
        su.update(parameters, keyHolder);

        return;
	}
	
	/**
	 * Return a list of the lightweight ReportSearchWrapper objects - using a custom rowmapper. 
	 * @return
	 */
	public List<ReportSearchWrapper> getAllReportSearchWrappers(){
		
		//Just get a list of all the reportIds!
		List<ReportSummary> reportSummaries = getAll();
		List<ReportSearchWrapper> reportSearchWrappers = new ArrayList<ReportSearchWrapper>();
		
		Integer reportId = null;
		
		
		//This is choking - Swallowing 2GB of data! - we need to grab each report and then push to ES, not a bulk job like this. 
		for(ReportSummary rs : reportSummaries){
			reportId = rs.getId();
			List<ItemSearchWrapper> itemSearchWrappers;
			ReportSearchWrapper rsw;
			
			//Basic report properties. 
			String sqlBasic = "select id as id, name as name, user as user, lastModified as lastModified, deleted as  deleted, public as public, groupId as groupId, iplRun as iplRun, readOnly as readOnly, description as description from tbl_report where id = " + reportId;
			rsw = getJdbcTemplate().queryForObject(sqlBasic, REPORT_SEARCH_WRAPPER_MAPPER);
			
			//Further properties. -- We will iterate over this. 
			String sqlDetail = "select distinct i.id as id, i.code as code, i.unit as unit, ip.description as description, ip.version as version, ip.definition as definition, tri.modelId as modelId from tbl_item i, tbl_report_items tri, tbl_itemproperties ip where tri.itemId = i.id and ip.itemId = i.id and tri.reportId = " + reportId;
			itemSearchWrappers = getJdbcTemplate().query(sqlDetail, REPORT_ITEM_SEARCH_WRAPPER_MAPPER);
			
			rsw.setItems(itemSearchWrappers);
			reportSearchWrappers.add(rsw);
		}
		return reportSearchWrappers;
	}
	
	/**
	 * Get an individual ReportSearchWrapper.
	 * @param id
	 * @return
	 */
	public ReportSearchWrapper getReportSearchWrapper(Integer id){
		Integer reportId = id;
		List<ItemSearchWrapper> itemSearchWrappers;
		ReportSearchWrapper rsw;
		
		//Basic report properties. 
		String sqlBasic = "select id as id, name as name, user as user, lastModified as lastModified, deleted as  deleted, public as public, groupId as groupId, iplRun as iplRun, readOnly as readOnly, description as description from tbl_report where id = " + reportId;
		rsw = getJdbcTemplate().queryForObject(sqlBasic, REPORT_SEARCH_WRAPPER_MAPPER);
		
		//Further properties. -- We will iterate over this. 
		String sqlDetail = "select distinct i.id as id, i.code as code, i.unit as unit, ip.description as description, ip.version as version, ip.definition as definition, tri.modelId as modelId from tbl_item i, tbl_report_items tri, tbl_itemproperties ip where tri.itemId = i.id and ip.itemId = i.id and tri.reportId = " + reportId + " and ip.version = (SELECT max(version) FROM tbl_itemproperties WHERE itemid = i.id)";
		itemSearchWrappers = getJdbcTemplate().query(sqlDetail, REPORT_ITEM_SEARCH_WRAPPER_MAPPER);
		
		rsw.setItems(itemSearchWrappers);
		return rsw;
	}
	
	/**
	 * Set the readOnly status for a particular report.
	 */
	public void setWriteStatus(int id, boolean readOnly){
		Integer status = 0;
		if(readOnly){
			status = 1;
		}
		String sql = "UPDATE " + REPORTTABLE + " set readOnly=" + status.toString() + " WHERE id=?";
		getJdbcTemplate().update(sql, id);
		return;		
	}
	
}
