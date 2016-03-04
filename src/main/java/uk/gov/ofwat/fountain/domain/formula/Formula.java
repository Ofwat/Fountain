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

import java.util.List;
import java.util.Vector;

/**
 * Represents an equation, consisting of a sequence of tokens. Each token can
 * either be a {@link CodeToken} or a {@link TextToken}.
 */
public class Formula implements Cloneable {
	
	protected List<FormulaToken> tokens;
	
	/**
	 * Creates a new equation consisting of an empty list of tokens.
	 */
	public Formula() {
		this.tokens = new Vector<FormulaToken>();
	}
	
	/**
	 * Adds a new token to this equation.
	 * 
	 * @param token the token to add
	 */
	public void addToken(FormulaToken token) {
		tokens.add(token);
	}
	
	/**
	 * Returns this equation's tokens.
	 * 
	 * @return the equation's tokens
	 */
	public List<FormulaToken> getTokens() {
		return tokens;
	}
	
	/**
	 * Replaces the token at the specified position in this equation with the
	 * specified token.
	 * 
	 * @param index the index of the token to replace
	 * @param newToken new token to be stored at the specified position
	 */
	public void replaceToken(int index, FormulaToken newToken) {
		tokens.set(index, newToken);
	}
	
	public String toString() {
		return "Equation" + tokens;
	}
	
	/**
	 * Returns a flat, textual representation of this equation, by
	 * concatenating the textual representation of the equation's tokens
	 * (irrespective of whether they are valid tokens or not).
	 * 
	 * @return this equation's text
	 */
	public String getText() {
		StringBuffer text = new StringBuffer();
		for (FormulaToken token : tokens) {
			text.append(token.getText());
		}
		return text.toString();
	}
	
	public Formula clone() {
		Formula e = new Formula();
		e.tokens = new Vector<FormulaToken>();
		e.tokens.addAll(tokens);
		return e;
	}

}
