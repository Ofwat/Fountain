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
public class OperatorToken extends FormulaToken {


	/**
	 * Creates a new token from the specified text.
	 * 
	 * @param text the text
	 */
	public OperatorToken(String text) {
		this.text = text;
	}

    
    @Override
    public String toString() {
   		return "OperatorToken[" + text + "]";
    }


	@Override
	public void process(FormulaContext context) throws ProcessingException {
		context.processOperatorToken(this);
	}

	public void checkForErrors(String s1, String s2) {
		if (s1!=null && s1.startsWith("ERR ")) {
			throw new ProcessingException(s1);
		}
		if (s2!=null && s2.startsWith("ERR ")) {
			throw new ProcessingException(s2);
		}
	}
	
	@Override
	public int operands() {
		return 2;
	}

}
