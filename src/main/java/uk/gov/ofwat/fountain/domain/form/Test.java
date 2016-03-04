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

package uk.gov.ofwat.fountain.domain.form;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[][] arry = new String[4][4];
		arry[0][0] = "0";
		arry[0][1] = "1";
		arry[0][2] = ""+2;
		arry[0][3] = ""+3;
		arry[1][0] = ""+10;
		arry[1][1] = ""+11;
		arry[1][2] = ""+12;
		arry[1][3] = ""+13;
		arry[2][2] = ""+22;
		arry[2][3] = ""+23;
		arry[3][3] = ""+34;
		
		for(String[] as: arry){
			for(String s: as){
				System.out.println(s);
			}
		}
	
	}

}
