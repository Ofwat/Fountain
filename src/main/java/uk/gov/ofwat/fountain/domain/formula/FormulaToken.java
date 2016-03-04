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
 * The base class for tokens that can appear in equations.
 */
public abstract class FormulaToken {
	
    protected List<String> errors;
	protected String text;
    
    public FormulaToken() {
        this.errors = new Vector<String>();
    }
    
    /**
     * Indicates whether this token has any errors.
     * 
     * @return true if the token has errors; false if it does not
     */
    public boolean hasErrors() {
        return (errors.size() > 0);
    }
    
    /**
     * Returns the token's list of errors.
     * 
     * @return the list of errors
     */
    public List<String> getErrors() {
        return errors;
    }
    
    /**
     * Indicates whether this token is complete.
     * 
     * @return true if the token is complete; false if it is not
     */
	public boolean isComplete() {
	    return (text != null);
	}

    /**
     * Returns the text for this token.
     * 
     * @return the text for this token
     */
	public String getText() {
	    return text;
	}

	public abstract void process(FormulaContext context) throws ProcessingException;

	/**
	 * Return the number of operands this token needs to function
	 */
	public abstract int operands();
	
	protected double toDouble(String value) {
		if (value==null || value.length()==0) return 0;
		return Double.parseDouble(value.replaceAll(",", ""));
	}
	
	protected String toNumericOrText(String value) {
		try {
			return "" + toDouble(value);
		}
		catch (NumberFormatException ex) {
			return value;
		}
	}

	protected boolean hasText(String text) {
		return !(text==null || text.isEmpty() || text.trim().isEmpty());
	}

}
