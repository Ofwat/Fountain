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

package uk.gov.ofwat.fountain.api.report;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Interval;

public interface ReportAxis {
	
	/**
	 * if the top axis is company by item, this will build a list
	 * containing company1-item1... company1-itemN , company2-item1 ... company2-itemN etc.
	 * corresponding the the horizontal headings.
	 */
	void buildPrimaryTriplets();
	
	/**
	 * create triplets for the secondary top axis
	 * @param t - a triplet loaded with the entity from the top primary axis. Will be cloned
	 */
	void addSecondaryTriplets(Triplet t);
	void addTertiaryTriplets(Triplet t);
	
	void addPrimaryDataRows();
	void addSecondaryDataRows(ReportLine rl);
	void addTertiaryDataRows(ReportLine rl);

	/**
	 * adds the appropriate lines to the collection based on the heading options
	 * @param lines
	 * @param span - number of blank cells inserted after each cell to line up with other axis.
	 * always 1 for secondary or solitary primary axis.
	 * @param leftOffset - number of blank cells on left for the LH headings
	 */
	void addTopHeadingRows(List<ReportLine> lines, int leftOffset, int span, int repeats, boolean putCG);

	/**
	 * useful for working out the size of the secondary axis while setting spans
	 * on the primary
	 * @param axis
	 * @return
	 */
	int getAxisSize();

	/**
	 * for a given report axis, how many heading cells will be needed 
	 * based on the various heading parameters
	 * @param axis
	 * @return
	 */
	int headingCount();

}
