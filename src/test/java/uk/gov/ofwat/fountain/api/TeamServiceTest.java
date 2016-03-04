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
package uk.gov.ofwat.fountain.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.dao.TeamDao;
import uk.gov.ofwat.fountain.domain.Team;


public class TeamServiceTest extends TestCase {
	
	private TeamDao mockTeamDao;
	private RoleChecker mockRoleChecker;
	private TeamServiceImpl teamService; 
	private List<Team> teams;

	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		mockTeamDao = mock(TeamDao.class);
		mockRoleChecker = mock(RoleChecker.class);
		teamService = new TeamServiceImpl();
		teamService.setTeamDao(mockTeamDao);

		Team team1 = new Team();
		team1.setId(0);
		team1.setCode("code1");
		team1.setName("name1");
		Team team2 = new Team();
		team2.setId(1);
		team2.setCode("code2");
		team2.setName("name2");
		Team team3 = new Team();
		team3.setId(2);
		team3.setCode("code3");
		team3.setName("name3");
		
		teams = new ArrayList<Team>();
		teams.add(team1);
		teams.add(team2);
		teams.add(team3);
	
		System.out.println("TEST: Done");
}

	public void testFindTeamIdsForUser(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFindTeamIdsForUser");
		when(mockTeamDao.getAllTeams()).thenReturn(teams);
		when(mockRoleChecker.isUserInRole("code1")).thenReturn(true);
		when(mockRoleChecker.isUserInRole("code2")).thenReturn(false);
		when(mockRoleChecker.isUserInRole("code3")).thenReturn(true);
		
		List<Integer> teamIds = teamService.findTeamIdsForUser(mockRoleChecker);
		Assert.assertEquals((Integer)0, teamIds.get(0));
		Assert.assertEquals((Integer)2, teamIds.get(1));
	
		System.out.println("TEST: Done");
}

}

