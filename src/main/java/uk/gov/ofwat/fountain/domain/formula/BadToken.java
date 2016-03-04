/*
 *  Copyright (C) 2007 Water Services Regulation Authority (Ofwat)
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



/**
 * A token to describe bad elements of an equation
 */
public class BadToken extends FormulaToken {

	/**
	 * Creates a new bad token using the given text
	 * 
	 * @param text the text
	 */
	public BadToken(String text) {
		this.text = text;
	}


	/**
	 * @param text numeric value to test
	 * @return true if valid
	 */
	public static boolean isSeparator(String text) {
		return text.equals(";");
	}

    @Override
    public String toString() {
   		return "SeparatorToken[" + text + "]";
    }


	@Override
	public void process(FormulaContext context) {
		// Not processable
	}


	@Override
	public int operands() {
		return 0;
	}

	

}
