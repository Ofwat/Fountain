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

package uk.gov.ofwat.fountain.domain.formula;

import java.util.Random;

public class ErrorIdentifier {

	private static final char[] charArray = 
	          {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 
		       'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z', 'B', 'C', 
		       'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
		       'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3',
		       '4', '5', '6', '7', '8', '9'};
	
	private static final int charLen = charArray.length;
	
	private static Random generator = new Random(1758227); // seeded random
	
	public static String genErrorID(){
		
		char[] letters = new char[10];
		for(int i = 0; i < 10; i++){
			letters[i] = charArray[generator.nextInt(charLen)];
		}
		return "ERR - " +  new String(letters);
	}
}
