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

package uk.gov.ofwat.fountain.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import junit.framework.TestCase;

import org.apache.regexp.RE;

import uk.gov.ofwat.fountain.domain.formula.BracketToken;
import uk.gov.ofwat.fountain.domain.formula.CodeToken;
import uk.gov.ofwat.fountain.domain.formula.Formula;
import uk.gov.ofwat.fountain.domain.formula.FormulaParser;
import uk.gov.ofwat.fountain.domain.formula.FormulaParserImpl;
import uk.gov.ofwat.fountain.domain.formula.FormulaToken;
import uk.gov.ofwat.fountain.domain.formula.EvaluatableFormula;
import uk.gov.ofwat.fountain.domain.formula.FunctionToken;
import uk.gov.ofwat.fountain.domain.formula.NumericToken;
import uk.gov.ofwat.fountain.domain.formula.OperatorToken;
import uk.gov.ofwat.fountain.domain.formula.TextToken;

public class EquationParserImplTest extends TestCase {

    public void testRe() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testRe");
        RE codeRegexp = new RE(CodeToken.CODE_TOKEN_REGEXP);
        
        String equation = "FDR_OW00025";
        boolean matches = codeRegexp.match(equation);
        assertTrue("RE did not match test equation.", matches);
        assertEquals("RE did not match entire test equation.", equation, codeRegexp.getParen(0));

        equation = "BON101";
        matches = codeRegexp.match(equation);
        assertTrue("RE did not match test equation.", matches);
        assertEquals("RE did not match entire test equation.", equation, codeRegexp.getParen(0));

        equation = "BON101[2002-03, FD, WRZ23]";
        matches = codeRegexp.match(equation);
        assertTrue("RE did not match test equation.", matches);
        assertEquals("RE did not match entire test equation.", equation, codeRegexp.getParen(0));
    
		System.out.println("TEST: Done");
}
    
    public void testEquationTokens() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquationTokens");
        String equation = "FDR_OW00025[a,b,c] * (256 - BON101^2)^3";
        
        FormulaParser parser = new FormulaParserImpl();
        Formula eqn = parser.parseFormula(equation);
        assertNotNull(eqn);
        assertEquals("Equation should have 1 tokens.", 11, eqn.getTokens().size());
        
        assertIsCode(eqn.getTokens(), 0, "FDR_OW00025");        
        assertIsComponent(eqn.getTokens(), 1, OperatorToken.class, "*");
        assertIsComponent(eqn.getTokens(), 2, BracketToken.class, "(");
        assertIsComponent(eqn.getTokens(), 3, NumericToken.class, "256");
        assertIsComponent(eqn.getTokens(), 4, OperatorToken.class, "-");
        assertIsCode(eqn.getTokens(), 5, "BON101");
        assertIsComponent(eqn.getTokens(), 6, OperatorToken.class, "^");
        assertIsComponent(eqn.getTokens(), 7, NumericToken.class, "2");
        assertIsComponent(eqn.getTokens(), 8, BracketToken.class, ")");
        assertIsComponent(eqn.getTokens(), 9, OperatorToken.class, "^");
        assertIsComponent(eqn.getTokens(), 10, NumericToken.class, "3");
        
    
		System.out.println("TEST: Done");
}

    public void testEquationWithIF() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquationWithIF");
        String equation = "IF(FDR_OW00025[2002-03,JR,WRZ4]=0 ; BON101+BON102; 748)";
        
        FormulaParser parser = new FormulaParserImpl();
        Formula eqn = parser.parseFormula(equation);
        assertNotNull(eqn);
        assertEquals("Equation should have 10 tokens.", 12, eqn.getTokens().size());
    
		System.out.println("TEST: Done");
}

    public void testEquationWithText() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquationWithText");
        String equation = "IF(A1=1; \"This is 1\"; 99)";
        
        FormulaParser parser = new FormulaParserImpl();
        Formula eqn = parser.parseFormula(equation);
        assertNotNull(eqn);
        assertEquals("Equation should have 10 tokens.", 10, eqn.getTokens().size());
        
        assertIsComponent(eqn.getTokens(), 0, FunctionToken.class, "IF");
        assertIsCode(eqn.getTokens(), 2, "A1");        
        assertIsComponent(eqn.getTokens(), 3, OperatorToken.class, "=");

        assertIsComponent(eqn.getTokens(), 6, TextToken.class, "This is 1");

    
		System.out.println("TEST: Done");
}

    private void assertIsCode(Queue<FormulaToken> tokens, int index, String expected) {
    	assertIsCode(new ArrayList<FormulaToken>(tokens), index, expected);
    }
    
    private void assertIsCode(List<FormulaToken> tokens, int index, String expected) {
    	FormulaToken token = tokens.get(index);
        assertTrue("Token " + index + " is not a code token: " + token, token instanceof CodeToken);
        
        CodeToken code = (CodeToken) token;
        assertTrue("Token " + index + " has wrong value - expected " + expected + ", got " + code.toString(), code.getCode().equals(expected));
    }

    private void assertIsComponent(Queue<FormulaToken> tokens, int index, Class<?> testClass, String expected) {
    	assertIsComponent(new ArrayList<FormulaToken>(tokens), index, testClass, expected);
    }
    
    private void assertIsComponent(List<FormulaToken> tokens, int index, Class<?> testClass, String expected) {
    	FormulaToken token = tokens.get(index);
        assertTrue("Token " + index + " is not a component token: " + token, token instanceof FormulaToken);
        
        FormulaToken c = (FormulaToken) token;
        assertTrue("Token " + index + " is not correct type: " + c, c.getClass().getName().equals(testClass.getName()));
        assertTrue("Token " + index + " has incorrect value, expected " + expected + ": " + c, c.getText().equals(expected));
    }
    

    public void testEquationWithNumbers() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquationWithNumbers");
        String equation = "100+ 25.3 * 49.9378734878";
        
        FormulaParser parser = new FormulaParserImpl();
        Formula eqn = parser.parseFormula(equation);

        assertEquals("Equation should have 5 tokens.", 5, eqn.getTokens().size());

        assertIsComponent(eqn.getTokens(), 0, NumericToken.class, "100");
        assertIsComponent(eqn.getTokens(), 2, NumericToken.class, "25.3");
        assertIsComponent(eqn.getTokens(), 4, NumericToken.class, "49.9378734878");
    
		System.out.println("TEST: Done");
}

    public void testEquationWithNegatives() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEquationWithNegatives");
        String equation = "-100.232";
        
        FormulaParser parser = new FormulaParserImpl();
        Formula eqn = parser.parseFormula(equation);

        assertEquals("Equation should have 2 tokens.", 2, eqn.getTokens().size());
        assertIsComponent(eqn.getTokens(), 0, FunctionToken.class, "NEG");
        assertIsComponent(eqn.getTokens(), 1, NumericToken.class, "100.232");

        equation = "(-55)*77.3";
        eqn = parser.parseFormula(equation);
        assertEquals("Equation should have 6 tokens.", 6, eqn.getTokens().size());
        assertIsComponent(eqn.getTokens(), 1, FunctionToken.class, "NEG");
        assertIsComponent(eqn.getTokens(), 2, NumericToken.class, "55");

        equation = "A1*-33.2";
        eqn = parser.parseFormula(equation);
        assertEquals("Equation should have 4 tokens.", 4, eqn.getTokens().size());
        assertIsComponent(eqn.getTokens(), 2, FunctionToken.class, "NEG");
        assertIsComponent(eqn.getTokens(), 3, NumericToken.class, "33.2");

        equation = "-((A1+A2)/A3)-(23/(-12/A3))";
        eqn = parser.parseFormula(equation);
        assertEquals("Equation should have 21 tokens.", 21, eqn.getTokens().size());
        assertIsComponent(eqn.getTokens(), 0, FunctionToken.class, "NEG");
        assertIsComponent(eqn.getTokens(), 10, OperatorToken.class, "-");
        assertIsComponent(eqn.getTokens(), 15, FunctionToken.class, "NEG");
        assertIsComponent(eqn.getTokens(), 16, NumericToken.class, "12");
    
		System.out.println("TEST: Done");
}
    
    
    public void testEvaluation() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluation");
        FormulaParser parser = new FormulaParserImpl();

        // Expecting A1 B1 +
    	String equation = "A1+B1";
        EvaluatableFormula eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 3);
        assertIsCode(eqn.getEvaluationQueue(), 0, "A1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "B1");
        assertIsComponent(eqn.getEvaluationQueue(), 2, OperatorToken.class, "+");

        // Expecting A1 B1 * C1 +
        equation = "A1*B1+C1";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 5);
        assertIsCode(eqn.getEvaluationQueue(), 0, "A1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "B1");
        assertIsComponent(eqn.getEvaluationQueue(), 2, OperatorToken.class, "*");
        assertIsCode(eqn.getEvaluationQueue(), 3, "C1");
        assertIsComponent(eqn.getEvaluationQueue(), 4, OperatorToken.class, "+");

        // Expecting A1 B1 + 3 4 1 - ^ *
        equation = "(A1+B1)*3^(4-1)";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 9);
        assertIsCode(eqn.getEvaluationQueue(), 0, "A1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "B1");
        assertIsComponent(eqn.getEvaluationQueue(), 2, OperatorToken.class, "+");
        assertIsComponent(eqn.getEvaluationQueue(), 3, NumericToken.class, "3");
        assertIsComponent(eqn.getEvaluationQueue(), 4, NumericToken.class, "4");
        assertIsComponent(eqn.getEvaluationQueue(), 5, NumericToken.class, "1");
        assertIsComponent(eqn.getEvaluationQueue(), 6, OperatorToken.class, "-");
        assertIsComponent(eqn.getEvaluationQueue(), 7, OperatorToken.class, "^");
        assertIsComponent(eqn.getEvaluationQueue(), 8, OperatorToken.class, "*");

        // Expecting XXX1 YYY22 + 4 =
        equation = "XXX1+YYY22=4";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 5);
        assertIsCode(eqn.getEvaluationQueue(), 0, "XXX1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "YYY22");
        assertIsComponent(eqn.getEvaluationQueue(), 2, OperatorToken.class, "+");
        assertIsComponent(eqn.getEvaluationQueue(), 3, NumericToken.class, "4");
        assertIsComponent(eqn.getEvaluationQueue(), 4, OperatorToken.class, "=");

        // Expecting A1 B1 C1 IF
        equation = "IF(A1; B1; C1)";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 4);
        assertIsCode(eqn.getEvaluationQueue(), 0, "A1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "B1");
        assertIsCode(eqn.getEvaluationQueue(), 2, "C1");
        assertIsComponent(eqn.getEvaluationQueue(), 3, FunctionToken.class, "IF");

        // Expecting A1 B1 B2 + = "This is text" C1 C2 + C3 * IF
        equation = "IF(A1=(B1+B2);  \"This is text\"; (C1+C2)*C3)";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertEvaluationIsCorrect(eqn, 12);
        assertIsCode(eqn.getEvaluationQueue(), 0, "A1");
        assertIsCode(eqn.getEvaluationQueue(), 1, "B1");
        assertIsCode(eqn.getEvaluationQueue(), 2, "B2");
        assertIsComponent(eqn.getEvaluationQueue(), 3, OperatorToken.class, "+");
        assertIsComponent(eqn.getEvaluationQueue(), 4, OperatorToken.class, "=");
        assertIsComponent(eqn.getEvaluationQueue(), 5, TextToken.class, "This is text");
        assertIsCode(eqn.getEvaluationQueue(), 6, "C1");
        assertIsCode(eqn.getEvaluationQueue(), 7, "C2");
        assertIsComponent(eqn.getEvaluationQueue(), 8, OperatorToken.class, "+");
        assertIsCode(eqn.getEvaluationQueue(), 9, "C3");
        assertIsComponent(eqn.getEvaluationQueue(), 10, OperatorToken.class, "*");
        assertIsComponent(eqn.getEvaluationQueue(), 11, FunctionToken.class, "IF");

    
		System.out.println("TEST: Done");
}
    
    public void assertEvaluationIsCorrect(EvaluatableFormula eqn, int expectedEvaluationTokens) {
        assertEquals(eqn.getText() + " should have " + expectedEvaluationTokens + " evaluated tokens.", expectedEvaluationTokens, eqn.getEvaluationQueue().size());
        String msg = "";
        boolean errors = false;
        for (FormulaToken t : eqn.getEvaluationQueue()) {
        	if (t.hasErrors()) {
        		errors = true;
        		msg += t.getText() + "\n";
        		for (String e : t.getErrors()) {
        			msg += " > " + e + "\n";
        		}
        				
        	}
        }
        assertFalse(eqn.getText() + " is not evaluatable: \n" + msg, errors);
    }
    
    public void testBadEquations() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testBadEquations");
        FormulaParser parser = new FormulaParserImpl();
        
        String equation = "(A1+B1";
        EvaluatableFormula eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertTrue(eqn.getText() + " did not report an error", eqn.hasEvaluationErrors());

        equation = "IF(A1;B1;C1";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertTrue(eqn.getText() + " did not report an error", eqn.hasEvaluationErrors());

        equation = "A1+B1)";
        eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertTrue(eqn.getText() + " did not report an error", eqn.hasEvaluationErrors());
    
		System.out.println("TEST: Done");
}

    
    public void testDodgyEqn() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDodgyEqn");
    	FormulaParser parser = new FormulaParserImpl();
		String equation = "If(CW00330=0;\"Zero Actual\";If(FDR_CW00330=0;\"Zero Determination\";(DD_CW00330/FDR_CW00330)*100))";
		EvaluatableFormula eqn = (EvaluatableFormula) parser.parseFormula(equation);
        assertTrue(eqn.getText() + " did report an error", !eqn.hasEvaluationErrors());
  
        
        
    
		System.out.println("TEST: Done");
}
    
}
