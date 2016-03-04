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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * 
 */
public class FormulaTools {

	private static Map<String, Integer> operatorPrecedenceMap;

	
	/**
	 * Take an EvaluatableEquation and build the post fix notation evaluation stack.
	 * @param equation
	 */
	public static void buildPostfixForFormula(EvaluatableFormula formula) {
		List<FormulaToken> infixTokens = formula.getTokens();
		List<FormulaToken> stack = new Vector<FormulaToken>();
		
		for (FormulaToken infixToken : infixTokens) {
			if (infixToken instanceof CodeToken) {
				// Code token goes straight to output
				formula.addEvaluationToken(infixToken);
			}
			else if (infixToken instanceof NumericToken) {
				// Numerics go to output
				formula.addEvaluationToken(infixToken);
			}
			else if (infixToken instanceof TextToken) {
				// text goes to output
				formula.addEvaluationToken(infixToken);
			}
			else if (infixToken instanceof FunctionToken) {
				// Functions go to stack
				push(stack, infixToken);
			}
			
			else if (infixToken instanceof BracketToken) {
				BracketToken bt = (BracketToken) infixToken;
				if (bt.isOpen()) {
					// Open brackets get put to the stack
					push(stack, infixToken);
				}
				else {
					// Got a closed bracket
					processClosedBracket(formula, stack, bt);
				}
			}
			else if (infixToken instanceof OperatorToken) {				
				// Got an operator
				processOperator(formula, stack, (OperatorToken) infixToken);
			}
			else if (infixToken instanceof SeparatorToken) {
				// Got a function separator
				processSeparator(formula, stack, (SeparatorToken) infixToken);
			}
		}
		
		// Pop off remaining items on stack
		while (stack.size()>0) {
			FormulaToken component = pop(stack);
			if (component instanceof BracketToken) {
				component.getErrors().add("Unmatched bracket");
			}
			formula.addEvaluationToken(component);
		}
	}

	private static void processSeparator(EvaluatableFormula formula, List<FormulaToken> stack, SeparatorToken component) {
		// When a function separator is found, we flush the stack back to the opening bracket
		// This bracket stays on the stack for any further separators
		// Flush the stack back to the bracket
		do {
			FormulaToken top = pop(stack);
			if (top==null) {
				// Error - got an unpaired bracket so add an error
				component.getErrors().add("Invalid function (misplaced separator or bracket)");
				break;
			}
			else if (top instanceof BracketToken && ((BracketToken) top).isOpen()) {
				// found the open bracket so stop
				push(stack, top);
				break;
			}
			else {
				// Push to output
				formula.addEvaluationToken(top);
			}
		} while (true);
	}

	private static void processOperator(EvaluatableFormula formula, List<FormulaToken> stack, OperatorToken component) {
		// Operators pop off the stack as long as the component is of higher precedence
		do {
			FormulaToken top = pop(stack);
			if (top==null) {
				// No more left - so stop
				break;
			}
			else if (!(top instanceof OperatorToken)) {
				// Not an operator so stop
				push(stack, top);
				break;
			}
			else if (comparePrecedence(component, (OperatorToken) top)<0) {
				// Hit a lower precedence so stop
				push(stack, top);
				break;
			}
			else {
				// Higher precedence so output
				formula.addEvaluationToken(top);
			}
		} while (true);
		push(stack, component);
	}
	
	private static void processClosedBracket(EvaluatableFormula formula, List<FormulaToken> stack, BracketToken component) {
		// Closed bracket pop everything off the stack to the first open bracket
		// The closed bracket is then discarded
		do {
			FormulaToken top = pop(stack);
			if (top==null) {
				// Error - got an unpaired bracket so add an error
				push(stack, component);
				component.getErrors().add("Unmatched bracket");
				break;
			}
			else if (top instanceof BracketToken && ((BracketToken) top).isOpen()) {
				// Found the close bracket so stop
				break;
			}
			formula.addEvaluationToken(top);
		} while (true);

	}
	
	private static void push(List<FormulaToken> stack, FormulaToken token) {
		stack.add(token);
	}

	private static FormulaToken pop(List<FormulaToken> stack) {
		if (stack.size()==0) return null;
		int end = stack.size()-1;
		FormulaToken top = stack.get(end);
		stack.remove(end);
		return top;
	}

	
	/**
	 * Return -1 where t1 is higher than t2, 1 where t2 is higher than t1 and 0 when equal
	 */
	private static int comparePrecedence(OperatorToken t1, OperatorToken t2) {
		if (operatorPrecedenceMap==null) {
			// Build the map if none exists
			operatorPrecedenceMap = new HashMap<String, Integer>();
			operatorPrecedenceMap.put("-", 1);
			operatorPrecedenceMap.put("+", 1);
			operatorPrecedenceMap.put("*", 2);
			operatorPrecedenceMap.put("/", 2);
			operatorPrecedenceMap.put("^", 3);
			operatorPrecedenceMap.put("=", 0);
			operatorPrecedenceMap.put("<", 0);
			operatorPrecedenceMap.put(">", 0);
			operatorPrecedenceMap.put("<=", 0);
			operatorPrecedenceMap.put(">=", 0);
			operatorPrecedenceMap.put("<>", 0);
		}
		
		if (!operatorPrecedenceMap.containsKey(t1.getText())) {
			t1.getErrors().add("Unrecognized operator");
			return 0;
		}
		if (!operatorPrecedenceMap.containsKey(t2.getText())) {
			t2.getErrors().add("Unrecognized operator");
			return 0;
		}

		else {
			int l1 = operatorPrecedenceMap.get(t1.getText());
			int l2 = operatorPrecedenceMap.get(t2.getText());
			if (l1>l2) return -1;
			if (l1<l2) return 1;
			return 0;
		}
	}
	

	/**
	 * Convert all instances of negative numbers or codes into
	 * the NEG function. This is necessary because -A is actually a 
	 * function rather than an operator and avoids confusing A-B
	 * with -A. This simplifies the evaluation
	 */
	public static void differentiateNEGandMINUS(Formula e) {
		if (e.getTokens()==null || e.getTokens().size()<=1) {
			return;
		}
		
		for (int i=0; i<(e.getTokens().size()-1); i++) {
			FormulaToken prev = (i==0) ? null : e.getTokens().get(i-1);
			FormulaToken cur = e.getTokens().get(i);
			
			if (isNegative(cur)) {
				if (prev==null) {
					// No previous token, got the form -XXXX so change to NEG
					e.replaceToken(i, new FunctionToken("NEG"));
				}
				else if (prev instanceof OperatorToken) {
					// previous token was an operator - got the form *-XXXX so change to NEG
					e.replaceToken(i, new FunctionToken("NEG"));
				}
				else if (isOpenBracket(prev)) {
					// previous token was an open bracket- got the form xxx * (-55*5) so change to NEG
					e.replaceToken(i, new FunctionToken("NEG"));
				}
			}
		}
	}
	
	private static boolean isNegative(FormulaToken t) {
		if (!(t instanceof OperatorToken)) return false;
		OperatorToken c = (OperatorToken) t;
		if (c.getText().equals("-")) return true;
		return false;
	}
	private static boolean isOpenBracket(FormulaToken t) {
		if (!(t instanceof BracketToken)) return false;
		BracketToken c = (BracketToken) t;
		return c.isOpen();
	}


}
