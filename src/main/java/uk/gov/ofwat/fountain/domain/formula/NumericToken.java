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
 * A token to describe all the non-code components of a formula
 */
public class NumericToken extends FormulaToken {
	private static final String NUMERIC_REGEXP = "([0-9]*[\\.][0-9]*)|([0-9]*)";

	/**
	 * Creates a new numeric token using the given text
	 * 
	 * @param text the text
	 */
	public NumericToken(String text) {
		this.text = text;
	}


	/**
	 * @param text numeric value to test
	 * @return true if valid
	 */
	public static boolean isNumeric(String text) {
		return text.matches(NUMERIC_REGEXP);
	}

    @Override
    public String toString() {
   		return "NumericToken[" + text + "]";
    }


	@Override
	public void process(FormulaContext context) {
		// Numeric values are simply pushed to the stack
		context.getResultStack().push(text);
	}


	@Override
	public int operands() {
		return 0;
	}


}
