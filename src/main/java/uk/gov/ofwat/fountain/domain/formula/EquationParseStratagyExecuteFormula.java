package uk.gov.ofwat.fountain.domain.formula;

import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.TransientDataCache;

public class EquationParseStratagyExecuteFormula implements EquationParseStratagy {

	@Override
	public void processOperatorToken(FormulaContext context, OperatorToken operatorToken) {
		// will always need 2 entries on the result stack
		if (context.getResultStack().size()<2){
			throw new ProcessingException("Error processing operator, unbalanced formula");
		}
		try{
			if (operatorToken.getText().equals("+")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1+v2));
			}
			else if (operatorToken.getText().equals("&")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				context.getResultStack().push(""+s1+s2);
			}
			else if (operatorToken.getText().equals("-")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1-v2));
			}
			else if (operatorToken.getText().equals("*")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1*v2));
			}
			else if (operatorToken.getText().equals("/")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				if(0.0 == v2){
					if (0.0 != v1) {
						throw new DivByZeroException("Div by zero error");
					} else {
						// IF IT'S 0/0 - BLANK
						context.getResultStack().push("");
					}
				} else {
					// NORMAL PROCESSING
					context.getResultStack().push("" + (v1/v2));
				}
			}
			else if (operatorToken.getText().equals("^")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (Math.pow(v1, v2)));
			}
			else if (operatorToken.getText().equals("=")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				String t1 = operatorToken.toNumericOrText(s1);
				String t2 = operatorToken.toNumericOrText(s2);
				context.getResultStack().push("" + (t1.equals(t2)));
			}
			else if (operatorToken.getText().equals("<=")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1<=v2));
			}
			else if (operatorToken.getText().equals("<")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1<v2));
			}
			else if (operatorToken.getText().equals(">=")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1>=v2));
			}
			else if (operatorToken.getText().equals(">")) {
				String s2 = context.getResultStack().pop();
				String s1 = context.getResultStack().pop();
				operatorToken.checkForErrors(s1, s2);
				double v2 = operatorToken.toDouble(s2);
				double v1 = operatorToken.toDouble(s1);
				context.getResultStack().push("" + (v1>v2));
			}
			else {
				throw new ProcessingException("Invalid operator " + operatorToken.getText());
			}
		}
		catch(DivByZeroException dbze){
			throw dbze;
		}
		catch(ProcessingException pe){
			// throw it
			throw pe;
		}
		catch(Exception e){
			throw new ProcessingException("Exception processing Operator Token " + e.getMessage(), e);
		}
	}

	@Override
	public void processCodeToken(FormulaContext context, CodeToken codeToken) {
		String itemCode = null;
		Interval interval = null;
		boolean total = false;
		for (String suffix : codeToken.getSuffixes()) {
			if (suffix.equalsIgnoreCase("TOTAL")) {
				total = true;
				break;
			}
		}
		try {	
			Model model = codeToken.getModel(context);
			itemCode = codeToken.getCode();
			interval = codeToken.getYear(context);

			Pot pot = null;
			if (context.isProcessingReport()) {
				pot = model.createPot(itemCode, interval, context.getPot().getRunTag().getRun().getId()); // Defensive copy
			}
			else {
				pot = model.getPot(itemCode, interval);
			}
			
			Basket basket = context.getBasket();
			String value = null;
			Data data = null;
			TransientDataCache transientDataCache = context.getTransientDataCache();
			if(total){
				data = pot.getGroupTotal(context.getCompany(), transientDataCache, basket);
			}
			else{
				data = pot.getData(context.getCompany(), transientDataCache, basket, codeToken.getGroupEntry(context), context.isProcessingReport());	
			}
			if (null != data){
				value = data.getValue();
			}
			context.getResultStack().push(value);
		}
		catch(ProcessingException pe){
			throw pe; // one of ours - pass it up
		}
		catch (Exception ex) {
			throw new ProcessingException("Unable to process " + itemCode + "," + interval.getName(), ex);
		}
	}

}
