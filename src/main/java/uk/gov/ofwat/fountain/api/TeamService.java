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

import java.util.List;

import uk.gov.ofwat.fountain.api.security.RoleChecker;
import uk.gov.ofwat.fountain.domain.Team;

public interface TeamService {

	/**
	 * get all the Teams (roles) that users can be a member of
	 * @return
	 */
	List<Team> getAllTeams();
	
	/**
	 * Get a team from it's ID
	 * @param id
	 * @return
	 */
	Team getTeamById(int id);
	Team getTeamByName(String name);

	
	// find all teams for the current user
	List<Integer> findTeamIdsForUser(RoleChecker roleChecker);

	Team findTeamForUser(RoleChecker roleChecker);
	
	List<Team> findTeamsForUser(RoleChecker roleChecker);

	// find if this is one of the user's teams
	// (allows user to be a member of more than one security group)
	boolean isMyTeam(Team team, RoleChecker roleChecker);

}
