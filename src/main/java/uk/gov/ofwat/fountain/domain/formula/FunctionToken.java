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

import java.util.HashMap;
import java.util.Map;



/**
 * A token to hold functions
 */
public class FunctionToken extends FormulaToken {
	public static final String FUNCTION_REGEXP = "(?i)(IF)|(OFFSET)|(NEG)|(ISBLANK)|(ROUNDDOWN)|(ROUNDUP)|(AND)|(OR)";
	private static Map<String, Integer> operandsForFunction;

	/**
	 * Given a function, return the number of operands expected 
	 * @return number of operands, or -1 if not a valid function
	 */
	private static int getOperandsForFunction(String function) {
		if (operandsForFunction==null) {
			operandsForFunction = new HashMap<String, Integer>();
			operandsForFunction.put("IF", 3);
			operandsForFunction.put("OFFSET", 2);
			operandsForFunction.put("NEG", 1);
			operandsForFunction.put("ISBLANK", 1);
			operandsForFunction.put("ROUNDDOWN", 2);
			operandsForFunction.put("ROUNDUP", 2);
			operandsForFunction.put("AND", 2);
			operandsForFunction.put("OR", 2);
		}
		if (operandsForFunction.containsKey(function)) {
			return operandsForFunction.get(function);
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Creates a new token from the specified text.
	 * 
	 * @param text the function name
	 */
	public FunctionToken(String text) {
		this.text = text;
	}

	/**
	 * @param text function to test
	 * @return true if a valid function
	 */
	public static boolean isFunction(String text) {
		return text.matches(FUNCTION_REGEXP);
	}
	
    @Override
    public String toString() {
    	return "FunctionToken[" + text + "]";
    }
    
    @Override
	public void process(FormulaContext context) {
		try {
			if (text.equalsIgnoreCase("IF")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				boolean cond = Boolean.parseBoolean(context.getResultStack().pop());
				
				checkForErrors(s1, s2);
				if (cond) {
					context.getResultStack().push(s1);
				}
				else {
					context.getResultStack().push(s2);
				}
			}
			
			else if (text.equalsIgnoreCase("NEG")) {
				String s1 = context.getResultStack().pop();
				checkForErrors(s1, "");
				double v1 = toDouble(s1);
				context.getResultStack().push("" + (-v1));
			}

			else if (text.equalsIgnoreCase("ROUNDDOWN")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				
				checkForErrors(s1, s2);
				double dp = toDouble(s2);
				double v = toDouble(s1);
				
				double exp = 1;
				if (dp>0) {
					exp = Math.pow(10, dp);
					v = v * exp;
				}
				double r = Math.floor(v);
				if (dp>0) {
					r = r / exp;
				}
				context.getResultStack().push("" + r);
			}

			else if (text.equalsIgnoreCase("ROUNDUP")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				
				checkForErrors(s1, s2);
				double dp = toDouble(s2);
				double v = toDouble(s1);
			
				double exp = 1;
				if (dp>0) {
					exp = Math.pow(10, dp);
					v = v * exp;
				}
				double r = Math.ceil(v);
				if (dp>0) {
					r = r / exp;
				}
					
				context.getResultStack().push("" + r);
			}

			else if (text.equalsIgnoreCase("ISBLANK")) {
				String s1 = context.getResultStack().pop();
				checkForErrors(s1, "");
				context.getResultStack().push("" + !hasText(s1));
			}
			else if (text.equalsIgnoreCase("AND")) {
				boolean c2 = Boolean.parseBoolean(context.getResultStack().pop());
				boolean c1 = Boolean.parseBoolean(context.getResultStack().pop());
					
				context.getResultStack().push("" + (c1 && c2));
			}
			else if (text.equalsIgnoreCase("OR")) {
				boolean c2 = Boolean.parseBoolean(context.getResultStack().pop());
				boolean c1 = Boolean.parseBoolean(context.getResultStack().pop());
					
				context.getResultStack().push("" + (c1 || c2));
			}
			else {
				errors.add("Unable to process " + text + ": Not a valid function");
			}
		}
		catch (Exception ex) {
			errors.add("Unable to process function " + text + ": " + ex.toString());
		}
	}
   
	private void checkForErrors(String s1, String s2) {
		if (s1!=null && s1.startsWith("ERR ")) {
			throw new ProcessingException(s1);
		}
		if (s2!=null && s2.startsWith("ERR ")) {
			throw new ProcessingException(s2);
		}
	}
	
	
	@Override
	public int operands() {
		int ops = getOperandsForFunction(text.toUpperCase());
		if (ops<0) {
			throw new ProcessingException("Function [" + text + "] is not a valid function");
		}
		else {
			return ops;
		}
	}

}
