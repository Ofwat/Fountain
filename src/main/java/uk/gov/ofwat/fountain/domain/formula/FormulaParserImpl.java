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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

/**
 * The default implementation of the {@link FormulaParser} interface.
 */
public class FormulaParserImpl implements FormulaParser {

	private static final Log log = LogFactory.getLog(FormulaParserImpl.class);
	private static final int ALPHANUMERIC = 0;
	private static final int BRACKET = 1;
	private static final int OPERATOR = 2;
	private static final int COMPARATOR = 3;
	private static final int SEPARATOR = 4;
	private static final int UNRECOGNIZED = 5;
	
	private static final String OPERATORS = "-+*/^";
	private static final String COMPARATORS = "=<>";
	private static final String BRACKETS = "()";
	
	private static final int SPACE = 32;
	
	/**
	 * Returns an EvaluatableEquation holding all the 
	 */
	public Formula parseFormula(String formula) {
		EvaluatableFormula e = new EvaluatableFormula();
		
		RE codeRegexp = null;
		try {
			codeRegexp = new RE(CodeToken.CODE_TOKEN_REGEXP);
		} catch (RESyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int start = 0;
		int endOfLast = 0;
		
		while (start < formula.length() && codeRegexp.match(formula, start)) {
			int tokenStart = codeRegexp.getParenStart(0);
			int tokenEnd = codeRegexp.getParenEnd(0);
			log.debug("Code found in [" + tokenStart + ", " + tokenEnd + ") = |" + codeRegexp.getParen(0) + "|");
			
			// Create a TextToken for any text that comes before the code
			String textBeforeCode = formula.substring(endOfLast, tokenStart);
			processNonCode(e, textBeforeCode);
			
			// Get the code that has been matched
			String code = codeRegexp.getParen(0);
			e.tokens.add(new CodeToken(code));

			// Advance past this code
			endOfLast = tokenEnd;
			start = endOfLast + 1;
		}
		
		// Create a TextToken for any text that comes after the last matched code
		String lastPart = formula.substring(endOfLast);
		if (lastPart.length() > 0) {
			processNonCode(e, lastPart);
		}

		// Do a second pass to work out - and NEG operators
		FormulaTools.differentiateNEGandMINUS(e);
		
		// Build postfix notation
		FormulaTools.buildPostfixForFormula(e);
		return e;
	}
	
	private void processNonCode(Formula e, String text) {
		// First pass to work out the rough internal tokens
		List<InternalToken> tokens = buildTokensFromFormula(text);
		
		// Build the correct component tokens
		for (InternalToken token : tokens) {
			String t = token.toString();
			if (token.type==ALPHANUMERIC) {
				if (NumericToken.isNumeric(t)) {
					// Is a number
					e.addToken(new NumericToken(t));
				}
				else if (FunctionToken.isFunction(t)) {
					// Is a function
					e.addToken(new FunctionToken(t));
				}
				else {
					// Simple text
					e.addToken(new TextToken(t));
				}
			}
			else if (token.type==OPERATOR || token.type==COMPARATOR) {
				e.addToken(new OperatorToken(t));
			}
			else if (token.type==BRACKET) {
				e.addToken(new BracketToken(t));
			}
			else if (token.type==SEPARATOR) {
				e.addToken(new SeparatorToken(t));
			}
			else {
				// Not recognised
				e.addToken(new BadToken(t));
			}
		}			
	}	
	
	/**
	 * Given a block of text, do a first pass to get the internal tokens
	 */
	private List<InternalToken> buildTokensFromFormula(String text) {
		boolean quote = false;
		char quoteMark = '?';
		
		List<InternalToken> words = new Vector<InternalToken>();
		StringBuffer word = new StringBuffer();

		int previousType = -1;
		for (int i=0; i<text.length(); i++) {
			// Get a char and work out its type 
			char c = text.charAt(i);
			int type = getCharType(c);
			
			//=== Process quotes ===
			if (quote) {
				if (c==quoteMark) {
					// Found the end of a quote mark so store the word and prepare for next
					words.add(new InternalToken(ALPHANUMERIC, word));
					word = new StringBuffer();
					quote = false;
				}
				else {
					// Just keep adding text to the word
					word.append(c);
				}
			}
			else {
				if (c=='\'' || c=='\"') {
					// Found the start of a quoted string literal
					quote = true;
					quoteMark = c;
					if (word.length()>0) {
						words.add(new InternalToken(previousType, word));
						word = new StringBuffer();
					}
				}
			
				//=== Spaces get ignored ===
				else if (c<=SPACE) {
					if (word.length()>0) words.add(new InternalToken(previousType, word));
					word = new StringBuffer();
				}
				
				//=== Process text ===
				else {
					if (type==previousType && (type!=BRACKET && type!=OPERATOR)) {
						// Got the same sort of string so keep adding to word
						// Operators and brackets can only have one each
						word.append(c);
					}
					else {
						// type changed so we must add a new word
						if (word.length()>0) words.add(new InternalToken(previousType, word));
						word = new StringBuffer();
						word.append(c);
						previousType = type;
					}
				}
			}
		}

		// Flush
		if (word.length()>0) words.add(new InternalToken(previousType, word));

		return words;
	}

	
	private int getCharType(char c) {
		if (c>='a' && c<='z') return ALPHANUMERIC;
		if (c>='A' && c<='Z') return ALPHANUMERIC;
		if (c>='0' && c<='9') return ALPHANUMERIC;
		if (c=='.' || c=='_') return ALPHANUMERIC;
		
		if (c==';') return SEPARATOR;
		
		if (OPERATORS.indexOf(c)>=0) return OPERATOR;
		if (COMPARATORS.indexOf(c)>=0) return COMPARATOR;
		if (BRACKETS.indexOf(c)>=0) return BRACKET;
		
		return UNRECOGNIZED;
	}
	
	
	private class InternalToken {
		public int type;
		public StringBuffer text;
		
		public InternalToken(int type, StringBuffer text) {
			this.type = type;
			this.text = text;
		}
		
		public String toString() {
			return text.toString();
		}
	}
	
}
