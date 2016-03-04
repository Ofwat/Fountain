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

package uk.gov.ofwat.fountain.rest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.gov.ofwat.fountain.api.ReportService;
import uk.gov.ofwat.fountain.api.TeamService;
import uk.gov.ofwat.fountain.domain.ReportSummary;
import uk.gov.ofwat.fountain.domain.Team;
import uk.gov.ofwat.fountain.rest.dto.ReportDetailsDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test_beans.xml"})
public class ReportResourceTest extends AbstractJUnit4SpringContextTests{
	/************** STATICS **********************/
	private static ReportResource reportResource = new ReportResource();
	private static ReportService mockReportService = mock(ReportService.class);
	private static TeamService mockTeamService = mock(TeamService.class);
	private static UriInfo uriInfo = mock(UriInfo.class);

	private static SecurityContext mockSecurityContext = mock(SecurityContext.class);

	static{
		reportResource = new ReportResource();
		reportResource.setReportService(mockReportService);
		reportResource.setTeamService(mockTeamService);
	}
	
	
	/************** INSTANCE **********************/
	@Test
	public void testGetReportsForUser() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForUser");
		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		List<Team> teams = new ArrayList<Team>();
		when(mockTeamService.getAllTeams()).thenReturn(teams);
		Team team7 = new Team();
		team7.setId(7);
		team7.setName("SuperDuperTeam");
		team7.setCode("SDT");
		when(mockTeamService.getTeamById(7)).thenReturn(team7);
		
		ReportSummary rs1 = new ReportSummary();
		rs1.setId(33);
		rs1.setName("a great report");
		rs1.setLastModified(new Date(System.currentTimeMillis()));
		rs1.setPublicReport(false);
		rs1.setTeamId(7);
		
		List<ReportSummary> reports = new ArrayList<ReportSummary>();
		reports.add(rs1);
		when(mockReportService.getReportsForTeam(null, null)).thenReturn(reports);
		when(mockSecurityContext.isUserInRole("SDT")).thenReturn(true);
		Response response = reportResource.getReportsForUser(mockSecurityContext, uriInfo, false, null); //this is the standard 'REST' call.
		GenericEntity<List<ReportDetailsDto>> entity = (GenericEntity<List<ReportDetailsDto>>)response.getEntity();		
		List<ReportDetailsDto> reportDtos = (List<ReportDetailsDto>)entity.getEntity();
		Assert.assertFalse(reportDtos.isEmpty());
		Assert.assertEquals("a great report", reportDtos.get(0).getName());
		Assert.assertFalse(reportDtos.get(0).isInterchangeableCompany());
		System.out.println("TEST: Done");
	}
	
	@Test
	public void testGetReportsForUserWithSummarySetTrue() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForUserWithSummarySetTrue");
		
		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		List<Team> teams = new ArrayList<Team>();
		when(mockTeamService.getAllTeams()).thenReturn(teams);
		Team team7 = new Team();
		team7.setId(7);
		team7.setName("SuperDuperTeam");
		team7.setCode("SDT");
		when(mockTeamService.getTeamById(7)).thenReturn(team7);
		
		ReportSummary rs1 = new ReportSummary();
		rs1.setId(33);
		rs1.setName("a great report");
		rs1.setLastModified(new Date(System.currentTimeMillis()));
		rs1.setPublicReport(true);
		rs1.setTeamId(7);
		rs1.setInterchangeableCompany(true);
		
		List<ReportSummary> reports = new ArrayList<ReportSummary>();
		reports.add(rs1);
		when(mockReportService.getReportsForTeam(null, null)).thenReturn(reports);
		when(mockSecurityContext.isUserInRole("SDT")).thenReturn(true);
		Response response = reportResource.getReportsForUser(mockSecurityContext, uriInfo, true, null);
		GenericEntity<List<ReportDetailsDto>> entity = (GenericEntity<List<ReportDetailsDto>>)response.getEntity();		
		List<ReportDetailsDto> reportDtos = (List<ReportDetailsDto>)entity.getEntity();
		
		//Check that the reports have the summary - e.g. when the selectableCompany flag is set correctly - True in this case. 
		Assert.assertTrue(reportDtos.get(0).isInterchangeableCompany());		
		Assert.assertFalse(reportDtos.isEmpty());
		Assert.assertEquals("a great report", reportDtos.get(0).getName());		
		
		System.out.println("TEST: Done");
	}
	
	@Test
	public void testGetReportsForUserWithSummarySetFalse() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForUserWithSummarySetFalse");
		
		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		List<Team> teams = new ArrayList<Team>();
		when(mockTeamService.getAllTeams()).thenReturn(teams);
		Team team7 = new Team();
		team7.setId(7);
		team7.setName("SuperDuperTeam");
		team7.setCode("SDT");
		when(mockTeamService.getTeamById(7)).thenReturn(team7);
		
		ReportSummary rs1 = new ReportSummary();
		rs1.setId(33);
		rs1.setName("a great report");
		rs1.setLastModified(new Date(System.currentTimeMillis()));
		rs1.setPublicReport(false);
		rs1.setTeamId(7);
		rs1.setInterchangeableCompany(true);
		
		List<ReportSummary> reports = new ArrayList<ReportSummary>();
		reports.add(rs1);
		when(mockReportService.getReportsForTeam(null, 10)).thenReturn(reports);
		when(mockSecurityContext.isUserInRole("SDT")).thenReturn(true);
		Response response = reportResource.getReportsForUser(mockSecurityContext, uriInfo, false, 10);
		GenericEntity<List<ReportDetailsDto>> entity = (GenericEntity<List<ReportDetailsDto>>)response.getEntity();		
		List<ReportDetailsDto> reportDtos = (List<ReportDetailsDto>)entity.getEntity();
		
		//Check that the reports have the summary - e.g. when the selectableCompany flag is set correctly - False in this case. 
		Assert.assertTrue(reportDtos.get(0).isInterchangeableCompany());		
		Assert.assertFalse(reportDtos.isEmpty());
		Assert.assertEquals("a great report", reportDtos.get(0).getName());		
		
		System.out.println("TEST: Done");
	}	
	
	@Test
	public void testGetReportsForUserWithoutSummarySetTrue() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForUserWithoutSummarySetTrue");

		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		List<Team> teams = new ArrayList<Team>();
		when(mockTeamService.getAllTeams()).thenReturn(teams);
		Team team7 = new Team();
		team7.setId(7);
		team7.setName("SuperDuperTeam");
		team7.setCode("SDT");
		when(mockTeamService.getTeamById(7)).thenReturn(team7);
		
		ReportSummary rs1 = new ReportSummary();
		rs1.setId(33);
		rs1.setName("a great report");
		rs1.setLastModified(new Date(System.currentTimeMillis()));
		rs1.setPublicReport(true);
		rs1.setTeamId(7);
		rs1.setInterchangeableCompany(false);
		
		List<ReportSummary> reports = new ArrayList<ReportSummary>();
		reports.add(rs1);
		when(mockReportService.getReportsForTeam(null, null)).thenReturn(reports);
		when(mockSecurityContext.isUserInRole("SDT")).thenReturn(true);
		Response response = reportResource.getReportsForUser(mockSecurityContext, uriInfo, false, null);
		GenericEntity<List<ReportDetailsDto>> entity = (GenericEntity<List<ReportDetailsDto>>)response.getEntity();		
		List<ReportDetailsDto> reportDtos = (List<ReportDetailsDto>)entity.getEntity();
		
		//Check that the reports have the summary - e.g. when the selectableCompany flag is set correctly - False in this case. 	
		Assert.assertFalse(reportDtos.get(0).isInterchangeableCompany());
		Assert.assertFalse(reportDtos.isEmpty());
		Assert.assertEquals("a great report", reportDtos.get(0).getName());				
		
		System.out.println("TEST: Done");
	}
	
	@Test
	public void testGetReportsForUserWithoutSummarySetFalse() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testGetReportsForUserWithoutSummarySetFalse");

		UriInfo uriInfo = mock(UriInfo.class);
		URI uri = null;
		try {
			uri = new URI("http://host/");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		when(uriInfo.getBaseUri()).thenReturn(uri);
		
		List<Team> teams = new ArrayList<Team>();
		when(mockTeamService.getAllTeams()).thenReturn(teams);
		Team team7 = new Team();
		team7.setId(7);
		team7.setName("SuperDuperTeam");
		team7.setCode("SDT");
		when(mockTeamService.getTeamById(7)).thenReturn(team7);
		
		ReportSummary rs1 = new ReportSummary();
		rs1.setId(33);
		rs1.setName("a great report");
		rs1.setLastModified(new Date(System.currentTimeMillis()));
		rs1.setPublicReport(false);
		rs1.setTeamId(7);
		rs1.setInterchangeableCompany(false);
		
		List<ReportSummary> reports = new ArrayList<ReportSummary>();
		reports.add(rs1);
		
		//public List<ReportSummary> getReportsForTeams(final List<Integer> teamIds, boolean completeSummary)
		
		when(mockReportService.getReportsForTeam(null, null)).thenReturn(reports);
		when(mockSecurityContext.isUserInRole("SDT")).thenReturn(true);
		Response response = reportResource.getReportsForUser(mockSecurityContext, uriInfo, false, null);
		GenericEntity<List<ReportDetailsDto>> entity = (GenericEntity<List<ReportDetailsDto>>)response.getEntity();		
		List<ReportDetailsDto> reportDtos = (List<ReportDetailsDto>)entity.getEntity();
		
		//Check that the reports have the summary - e.g. when the selectableCompany flag is set correctly - False in this case. 	
		Assert.assertFalse(reportDtos.get(0).isInterchangeableCompany());
		Assert.assertFalse(reportDtos.isEmpty());
		Assert.assertEquals("a great report", reportDtos.get(0).getName());				
		
		System.out.println("TEST: Done");
	}
	

}
