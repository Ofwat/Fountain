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

import java.util.ArrayList;
import java.util.List;

import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.dao.TeamDao;
import uk.gov.ofwat.fountain.domain.Team;

public class TeamServiceImpl implements TeamService{

	private TeamDao teamDao;
	
	public void setTeamDao(TeamDao teamDao){
		this.teamDao = teamDao;
	}
	
	public List<Team> getAllTeams() {
		return teamDao.getAllTeams();
	}

	public Team getTeamById(int id) {
		return teamDao.findById(id);
	}

	public Team getTeamByName(String name) {
		return teamDao.findByName(name);
	}
	
	// find all teams for the current user
	public List<Integer> findTeamIdsForUser(RoleChecker roleChecker){
		List<Team> teams = getAllTeams();
		List<Integer> myTeams = new ArrayList<Integer>();
		for (Team t : teams) {
			if (roleChecker.isUserInRole(t.getCode())){
				myTeams.add(t.getId());
			}
		}
		return myTeams;
	}

	// find first team for the current user
	public Team findTeamForUser(RoleChecker roleChecker){
		List<Team> teams = getAllTeams();
		for (Team t : teams) {
			if (roleChecker.isUserInRole(t.getCode())){
				return t;
			}
		}
		return null;
	}
	
	// find first team for the current user
	public List<Team> findTeamsForUser(RoleChecker roleChecker){
		List<Team> teams = getAllTeams();
		List<Team> myTeams = new ArrayList<Team>();
		for (Team t : teams) {
			if (roleChecker.isUserInRole(t.getCode())){
				myTeams.add(t);
			}
		}
		return myTeams;
	}
	
	// find if this is one of the user's teams
	// (allows user to be a member of more than one security group)
	public boolean isMyTeam(Team team, RoleChecker roleChecker){
		if (roleChecker.isUserInRole(team.getCode())){
			return true;
		}
		return false;
	}


}
