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
package uk.gov.ofwat.fountain.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.isA;

import java.util.ArrayList;
import java.util.Stack;

import junit.framework.TestCase;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.api.report.RunTag;
import uk.gov.ofwat.fountain.domain.formula.CodeToken;
import uk.gov.ofwat.fountain.domain.formula.EvaluatableFormula;
import uk.gov.ofwat.fountain.domain.formula.FormulaContext;
import uk.gov.ofwat.fountain.domain.formula.FormulaParserImpl;
import uk.gov.ofwat.fountain.domain.run.Run;
import uk.gov.ofwat.fountain.domain.runTag.RunModelTag;

public class FormulaTest extends TestCase {
	Company company;
	Model model1;
	Model model2;
	Item itemA;
	Item itemB;
	Item itemC;
	FormulaContext mockContext;
	Pot potA;
	Data dataA;
	Pot potB;
	Data dataB;
	Pot potC;
	Data dataC;
	Pot potX;
	Data dataX;
	Interval defaultInterval;
	Interval interval;
	Interval offsetInterval;
	ArrayList<Interval> intervals;
	ReferenceService mockReferenceService;
	FormulaContext context;
	

	public void setUp() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":setUp");
		company = new Company();
		company.setCode("ABC");
		company.setId(1);
		company.setName("ABC Water");

		model1 = mock(Model.class);
		model2 = mock(Model.class);

		mockContext = mock(FormulaContext.class);

		potA = mock(Pot.class);
		potB = mock(Pot.class);
		potC = mock(Pot.class);
		potX = mock(Pot.class);

		dataA = mock(Data.class);
		dataB = mock(Data.class);
		dataC = mock(Data.class);
		dataX = mock(Data.class);

		defaultInterval = mock(Interval.class);
		interval = mock(Interval.class);
		offsetInterval = mock(Interval.class);

		intervals = new ArrayList<Interval>();
		mockReferenceService = mock(ReferenceService.class);
	
		context = new FormulaContext();
		System.out.println("TEST: Done");
}


	public void testEvaluate_twoTermAddition(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_twoTermAddition");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101+B102", "", 1, false);

		Stack<String> resultStack = new Stack<String>();

		// potA
		context();
		noModel();
		noInterval();
		noIntervalOffset();
		when(model1.getPot("B101", defaultInterval)).thenReturn(potA);
		when(dataA.getValue()).thenReturn("10.0");
		when(potA.getData(company, null, null, null, false)).thenReturn(dataA);

		// potB
		noModel();
		noInterval();
		noIntervalOffset();
		when(model1.getPot("B102", defaultInterval)).thenReturn(potB);
		when(dataB.getValue()).thenReturn("20.0");
		when(potB.getData(company, null, null, null, false)).thenReturn(dataB);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "30.0", result);
	
		System.out.println("TEST: Done");
}


	public void testEvaluate_fixedYear(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_fixedYear");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101[2000]", "", 1, false);

		Stack<String> resultStack = new Stack<String>();

		context();
		noModel();
		interval();

		when(model1.getPot("B101", interval)).thenReturn(potA);
		when(dataA.getValue()).thenReturn("10.0");
		when(potA.getData(company, null, null, null, false)).thenReturn(dataA);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "10.0", result);
	
		System.out.println("TEST: Done");
}


	public void testEvaluate_offsetYear(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_offsetYear");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101[+1]", "", 1, false);

		Stack<String> resultStack = new Stack<String>();

		context();
		noModel();
		intervalOffset();

		when(model1.getPot("B101", offsetInterval)).thenReturn(potA);
		when(potA.getData(company, null, null, null, false)).thenReturn(dataA);
		when(dataA.getValue()).thenReturn("11.0");
		
		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());

		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "11.0", result);
	
		System.out.println("TEST: Done");
}

	public void testEvaluate_offsetYear_processingReport(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_offsetYear");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101[+1]", "", 1, false);

		Stack<String> resultStack = new Stack<String>();

		context();
		context.setProcessingReport(true);
		noModel();
		intervalOffset();

		Run run = new Run();
		run.setId(5);
		RunTag runTag = new RunTag(run , RunModelTag.LATEST);
		when(potC.getRunTag()).thenReturn(runTag);

		when(model1.createPot("B101", offsetInterval, 5)).thenReturn(potC);
		when(potC.getData(company, null, null, null, true)).thenReturn(dataA);
		when(dataA.getValue()).thenReturn("12.0");

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());

		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "12.0", result);
	
		System.out.println("TEST: Done");
}

	public void testEvaluateBadOffsetYear(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluateBadOffsetYear");
	ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101[+1]", "", 1, false);

		context();
		noModel();
		invalidOffset();

		when(model1.getPot("B101", offsetInterval)).thenReturn(potA);
		when(dataA.getValue()).thenReturn("11.0");
		when(potA.getData(company, null, null, null, true)).thenReturn(dataA);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertTrue("offset year should throw an error", result.startsWith("ERR -"));
	
		System.out.println("TEST: Done");
}


	public void testEvaluate_modelReferenced(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_modelReferenced");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B201[M2]", "", 1, false);

		context();
		model();
		noIntervalOffset();
		noInterval();

		when(model2.getPot("B201", defaultInterval)).thenReturn(potX);
		when(dataX.getValue()).thenReturn("99.0");
		when(potX.getData(company, null, null, null, false)).thenReturn(dataX);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "99.0", result);
	
		System.out.println("TEST: Done");
}


	public void testEvaluate_fixedYearAndModelReferenced(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_fixedYearAndModelReferenced");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1",null, 2, null, true, "B201[2000,M2]", "", 1, false);

		context();
		model();
		interval();

		when(model2.getPot("B201", interval)).thenReturn(potX);
		when(dataX.getValue()).thenReturn("109.0");
		when(potX.getData(company, null, null, null, false)).thenReturn(dataX);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "109.0", result);
	
		System.out.println("TEST: Done");
}


	public void testEvaluate_offsetYearAndModelReferenced(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_offsetYearAndModelReferenced");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 2, null, true, "B201[+1,M2]", "", 1, false);

		context();
		model();
		noInterval();
		intervalOffset();

		when(model2.getPot("B201", offsetInterval)).thenReturn(potX);
		when(dataX.getValue()).thenReturn("119.0");
		when(potX.getData(company, null, null, null, false)).thenReturn(dataX);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "119.0", result);
	
		System.out.println("TEST: Done");
}
	



	public void testEvaluate_fixedYearMultiplicationOffsetYearAndModelReferenced(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluate_fixedYearMultiplicationOffsetYearAndModelReferenced");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1",null, 3, null, true, "B101[2000]*B201[+1,M2]", "", 1, false);

		// potA
		context();
		noModel();
		interval();

		when(model1.getPot("B101", interval)).thenReturn(potA);
		when(dataA.getValue()).thenReturn("10.0");
		when(potA.getData(company, null, null, null, false)).thenReturn(dataA);

		// potX
		model();
		noIntervalButDontChangeIntervalArray();
		intervalOffset();

		when(model2.getPot("B201", offsetInterval)).thenReturn(potX);
		when(dataX.getValue()).thenReturn("119.0");
		when(potX.getData(company, null, null, null, false)).thenReturn(dataX);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "1190.0", result);
	
		System.out.println("TEST: Done");
}

	public void testEvaluateFunction(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluateFunction");
		ItemProperties prop1 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "IF(true;\"1\";\"2\")", "", 1, false);
		ItemProperties prop2 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "NEG(10)", "", 1, false);
		ItemProperties prop3 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDDOWN(1.123, 2", "", 1, false);
		ItemProperties prop4 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDUP(1.2463, 3)", "", 1, false);
		ItemProperties prop5 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ISBLANK(\"\")", "", 1, false);
		ItemProperties prop6 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "IF(true;IF(true;\"1\";\"2\");\"3\")", "", 1, false);
		ItemProperties prop7 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "IF(true;IF(false;\"1\";\"2\");\"3\")", "", 1, false);


		FormulaParserImpl parser = new FormulaParserImpl();
		mockContext = mock(FormulaContext.class);
		Stack<String> resultStack = new Stack<String>();
		when(mockContext.getResultStack()).thenReturn(resultStack);
		// potA
		noModel();
		interval();

		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop1.getGeneralFormula());
		String result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "1", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop2.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "-10.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop3.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "1.12", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop4.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "1.247", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop5.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "true", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop6.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "1", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop7.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "2", result);

	
		System.out.println("TEST: Done");
}
	
	public void testEvaluateFunction_roundingCornerCases(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testEvaluateFunction_roundingCornerCases");
		ItemProperties prop1 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDUP(7.5, 0", "", 1, false);
		ItemProperties prop2 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDDOWN(7.5, 0", "", 1, false);
		ItemProperties prop3 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDUP(7.55, 1", "", 1, false);
		ItemProperties prop4 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDDOWN(7.55, 1", "", 1, false);
		ItemProperties prop5 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDUP(7.55, 2", "", 1, false);
		ItemProperties prop6 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDDOWN(7.55, 2", "", 1, false);
		ItemProperties prop7 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "ROUNDUP(0.029769392033543, 4", "", 1, false);
		
		FormulaParserImpl parser = new FormulaParserImpl();
		mockContext = mock(FormulaContext.class);
		Stack<String> resultStack = new Stack<String>();
		when(mockContext.getResultStack()).thenReturn(resultStack);
		// potA
		noModel();
		interval();

		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop1.getGeneralFormula());
		String result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "8.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop2.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "7.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop3.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "7.6", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop4.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "7.5", result);
		
		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop5.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "7.55", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop6.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "7.55", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop7.getGeneralFormula());
		result = evaluatableFormula.evaluate(mockContext);
		assertEquals("incorrect value returned", "0.0298", result);

		System.out.println("TEST: Done");
}
	
	public void testDefaultGroupTotal(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testDefaultGroupTotal");
		ItemProperties propC = new ItemProperties(itemC, 1, "Item C properties 1", null, 1, null, true, "B101[TOTAL]", "", 1, false);

		Stack<String> resultStack = new Stack<String>();

		// potA
		context();
		noModel();
		noInterval();
		noIntervalOffset();
		when(model1.getPot("B101", defaultInterval)).thenReturn(potA);

		when(dataA.getValue()).thenReturn("11.0");
		when(dataB.getValue()).thenReturn("22.0");
		when(dataC.getValue()).thenReturn("33.0");
		
		GroupEntry ge1 = new GroupEntry();
		GroupEntry ge2 = new GroupEntry();
		GroupEntry ge3 = new GroupEntry();
		
		when(potA.getGroupTotal(company, null, null)).thenReturn(dataC);

		FormulaParserImpl parser = new FormulaParserImpl();
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(propC.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "33.0", result);
	
		System.out.println("TEST: Done");
}

	public void testArithmaticFormula(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testArithmaticFormula");
		ItemProperties prop1 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "1+2+3", "", 1, false);
		ItemProperties prop2 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "1000/10", "", 1, false);
		ItemProperties prop3 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4*2.3", "", 1, false);
		ItemProperties prop4 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "2^3", "", 1, false);
		ItemProperties prop5 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4<2", "", 1, false);
		ItemProperties prop6 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4>2", "", 1, false);
		ItemProperties prop7 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4<=4", "", 1, false);
		ItemProperties prop8 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4>=2", "", 1, false);
		ItemProperties prop9 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4=4", "", 1, false);
		ItemProperties prop10 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4=2", "", 1, false);
		ItemProperties prop11 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4-2", "", 1, false);

		FormulaParserImpl parser = new FormulaParserImpl();
		context();

		// potA
		noModel();
		interval();

		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop1.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "6.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop2.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "100.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop3.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "9.2", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop4.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "8.0", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop5.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "false", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop6.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "true", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop7.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "true", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop8.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "true", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop9.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "true", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop10.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "false", result);

		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop11.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertEquals("incorrect value returned", "2.0", result);
	
		System.out.println("TEST: Done");
}

	public void testFormulaError(){
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFormulaError");
		ItemProperties prop1 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "0/0", "", 1, false);
		ItemProperties prop2 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "1000/0", "", 1, false);
		ItemProperties prop3 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "4*admiral", "", 1, false);
		ItemProperties prop4 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, "17=", "", 1, false);

		FormulaParserImpl parser = new FormulaParserImpl();
		context();
		itemA = mock(Item.class);
		when(itemA.getCode()).thenReturn("ITEMA");
		when(potA.getItem()).thenReturn(itemA);
		when(potA.getInterval()).thenReturn(interval);

		// potA
		noModel();
		interval();

		// If 0/0 return blank, not an error
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop1.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		assertTrue("no error returned",  !result.contains("ERR -"));

		context.getResultStack().clear();
		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop2.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertTrue("no error returned",  result.contains("ERR -"));

		context.getResultStack().clear();
		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop3.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertTrue("no error returned",  result.contains("ERR -"));

		context.getResultStack().clear();
		evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop4.getGeneralFormula());
		result = evaluatableFormula.evaluate(context);
		assertTrue("no error returned",  result.contains("ERR -"));
	
		System.out.println("TEST: Done");
}

	
    public void testIFEvaluation() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testIFEvaluation");
    	// Start with simple examples to make sure that DIV/0 does cause an error
    	checkEvaluation("9.0", "4+5");
    	checkEvaluation("#DIV0", "3/0");
    	checkEvaluation("#DIV0", "23+10*(4+3)+(6/0)");
    	
    	// Start with simple IF statements to make sure both branches are evaluated
    	checkEvaluation("1", "IF(true;1;2)");
    	checkEvaluation("2", "IF(false;1;2)");
    	
    	// Nested IF statements should work
    	checkEvaluation("1", "IF(true;IF(true;1;2);3)");
    	checkEvaluation("2", "IF(true;IF(false;1;2);3)");
    	checkEvaluation("3", "IF(false;IF(true;1;2);3)");
    	checkEvaluation("3", "IF(false;IF(true;1;2);3)");
    	
    	// DIV 0 - errors if on the evaluated branch, otherwise OK
    	checkEvaluation("3.0", "IF(true; 1+2; 3/0)");
    	checkEvaluation("#DIV0", "IF(false; 1+2; 3/0)");
    	checkEvaluation("#DIV0", "IF(true; 5/0; 4+5)");
    	checkEvaluation("9.0", "IF(false; 5/0; 4+5)");

    	// Nested IF statements with div 0
    	checkEvaluation("#DIV0", "IF(true;IF(true;1/0;2);3)");
    	checkEvaluation("2", "IF(true;IF(false;1/0;2);3)");
    	checkEvaluation("3", "IF(false;IF(true;1/0;2);3)");
    	checkEvaluation("3", "IF(false;IF(true;1/0;2);3)");

    	// Nested IF statements on the condition
    	checkEvaluation("21.0", "IF(IF(1=2; 3; 4)=3; 6/3; 10+11)");
    	checkEvaluation("2.0", "IF(IF(1=2; 3; 4)=4; 6/3; 10+11)");
    	checkEvaluation("21.0", "IF(IF(1=2; 3; 4)=3; 6/0; 10+11)");
    	checkEvaluation("#DIV0", "IF(IF(1=2; 3; 4)=4; 6/0; 10+11)");

    	// ANDs
    	checkEvaluation("12.0", "IF(AND(true; true); (1+2+3)*2; 2^3)"); // simple
    	checkEvaluation("8.0", "IF(AND(false; true); (1+2+3)*2; 2^3)");
    	checkEvaluation("#DIV0", "IF(AND(true; true); ((1+2+3)*2)/0; 2^3)"); // with div 0
    	checkEvaluation("8.0", "IF(AND(false; true); ((1+2+3)*2)/0; 2^3)");

    	// ORs
    	checkEvaluation("12.0", "IF(OR(true; true); (1+2+3)*2; 2^3)"); // simple
    	checkEvaluation("8.0", "IF(OR(false; false); (1+2+3)*2; 2^3)");
    	checkEvaluation("#DIV0", "IF(OR(true; true); ((1+2+3)*2)/0; 2^3)"); // with div 0
    	checkEvaluation("8.0", "IF(OR(false; false); ((1+2+3)*2)/0; 2^3)");
    
		System.out.println("TEST: Done");
}
    
    public void testLogicalFunctions() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testLogicalFunctions");
    	checkEvaluation("true", "AND(true, true)");
    	checkEvaluation("false", "AND(true, false)");
    	checkEvaluation("false", "AND(false, true)");
    	checkEvaluation("false", "AND(false, false)");

    	checkEvaluation("true", "OR(true, true)");
    	checkEvaluation("true", "OR(true, false)");
    	checkEvaluation("true", "OR(false, true)");
    	checkEvaluation("false", "OR(false, false)");
    
		System.out.println("TEST: Done");
}
    
    public void testIFZero() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testIFZero");
    	checkEvaluation("Zero Determination", "If(1.1=0;\"Zero Actual\";If(0=0;\"Zero Determination\";(0.000/0.000)*100))");
    	checkEvaluation("Zero Determination", "If(1.1=0;\"Zero Actual\";If(0.0=0;\"Zero Determination\";(0.000/0.000)*100))");
    	checkEvaluation("Zero Determination", "If(1.1=0;\"Zero Actual\";If(0.00=0;\"Zero Determination\";(0.000/0.000)*100))");
    	checkEvaluation("Zero Determination", "If(1.1=0;\"Zero Actual\";If(0.000=0;\"Zero Determination\";(0.000/0.000)*100))");
    	checkEvaluation("match", "If(\"hello\"=\"hello\";\"match\";\"no match\")");
    	checkEvaluation("no match", "If(\"hello\"=\"XXX hello\";\"match\";\"no match\")");
    
		System.out.println("TEST: Done");
}
    
    private void checkEvaluation(String expected, String formula) throws Exception {
    	ItemProperties prop1 = new ItemProperties(itemC, 1, "Item C properties 1", null, 0, null, true, formula, "", 1, false);
		
    	FormulaParserImpl parser = new FormulaParserImpl();
		context();
		itemA = mock(Item.class);
    	
		EvaluatableFormula evaluatableFormula = (EvaluatableFormula) parser.parseFormula(prop1.getGeneralFormula());
		String result = evaluatableFormula.evaluate(context);
		System.out.println("[" + expected + "] [" + result + "] [" + formula + "]");
		if (expected.equals("#DIV0")) {
			assertTrue("Evaluating " + formula + ": Expected a DIV0 error, but didn't get one",  result.contains("ERR - Div"));
		}
		else {
			assertEquals("Evaluating " + formula + ": Didn't get the correct result", expected, result);
		}
    }
    
    

	private void intervalOffset() {
		context.setPot(potC);
		when(potC.getInterval()).thenReturn(defaultInterval);
		when(potC.getReferenceService()).thenReturn(mockReferenceService);
		when(mockReferenceService.getOffsetInterval(defaultInterval, 1)).thenReturn(offsetInterval);
		when(offsetInterval.getName()).thenReturn("2001");
	}

	private void invalidOffset(){
		context.setPot(potC);
		when(potC.getInterval()).thenReturn(defaultInterval);
		when(potC.getReferenceService()).thenReturn(mockReferenceService);
		when(mockReferenceService.getOffsetInterval(defaultInterval, 1)).thenReturn(null);
	}

	private void noIntervalOffset() {
		context.setPot(potC);
		when(potC.getInterval()).thenReturn(defaultInterval);
		when(potC.getReferenceService()).thenReturn(mockReferenceService);
		when(mockReferenceService.getOffsetInterval(defaultInterval, 0)).thenReturn(defaultInterval);
	}

	private void interval() {
		intervals.add(interval);
		when(mockReferenceService.getIntervals()).thenReturn(intervals);
		when(interval.getName()).thenReturn("2000");
	}

	private void noInterval() {
	}

	private void noIntervalButDontChangeIntervalArray() {
		when(mockContext.getIntervals()).thenReturn(intervals);
	}

	private void model() {
		context.setModel(model1);
		when(model1.getInputModel("M2")).thenReturn(model2);
		when(model1.getModelCodeForInput("M2")).thenReturn("MOD2");
	}

	private void noModel() {
		when(mockContext.getModel()).thenReturn(model1);
		context.setModel(model1);
	}

	private void context() {
		context.setCompany(company);
		context.setReferenceService(mockReferenceService);
		context.setProcessingReport(true);
		Run run = new Run();
		run.setId(6);
		RunTag runTag = new RunTag(run , RunModelTag.LATEST);
		Pot contextPot = new Pot();
		contextPot.setRunTag(runTag);
		context.setPot(contextPot);
		context.setReferenceService(mockReferenceService);
		context.setProcessingReport(false);
	}

}
