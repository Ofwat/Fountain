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

import java.util.List;

import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.IntervalType;


/**
 * DAO concerned with intervals and interval types, for example years, quarters..
 */
public interface IntervalDao extends CachableEntityDao {

	/**
	 * retrieve an interval (e.g. 2004) based on it's id
	 * @param id
	 * @return
	 */
	public Interval findById(int id);
	
	/**
	 * 
	 * @return all known intervals
	 */
	public List<Interval>getAll();
	
	/**
	 * get all the intervals of a given type (e.g. financial years)
	 * @param intervalTypeId
	 * @return
	 */
	public List<Interval>getIntervalsByTypeId(int intervalTypeId);
	
	/**
	 * create a new interval (interval type must be set
	 * @param interval
	 * @return
	 */
	public int create(Interval interval);
	
	/**
	 * return all the interval types, e.g. year, financial year etc.
	 * @return
	 */
	public List<IntervalType>getAllIntervalTypes();
	
	/**
	 * return the interval with this name
	 * @param interval
	 * @return
	 */
	public Interval findByName(String interval);
	
}
