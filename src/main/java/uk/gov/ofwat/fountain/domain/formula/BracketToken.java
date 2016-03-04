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
 * A token to describe the brackets of an equation
 */
public class BracketToken extends FormulaToken {
	private static final String BRACKETS_OPEN = "{(";
	private static final String BRACKETS_CLOSED = ")}";
	
	/**
	 * Creates a new numeric token using the given text
	 * 
	 * @param text the text
	 */
	public BracketToken(String text) {
		this.text = text;
	}


	/**
	 * @param text bracket value to test
	 * @return true if valid
	 */
	public static boolean isBracket(String text) {
		return (BRACKETS_OPEN.contains(text) || BRACKETS_CLOSED.contains(text));
	}

	/**
	 * Return true if this is an open bracket
	 */
	public boolean isOpen() {
		return BRACKETS_OPEN.contains(text);
	}
	
    @Override
    public String toString() {
   		return "BracketToken[" + text + "]";
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
