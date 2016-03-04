package uk.gov.ofwat.fountain.domain.formula;

import java.util.List;

import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemInEquation;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;

public class EquationParseStratagyCollectItems implements EquationParseStratagy {

	@Override
	public void processOperatorToken(FormulaContext context, OperatorToken operatorToken) {
		// Do nothing!
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
			ItemInEquation itemInEquation = new ItemInEquation(itemCode, interval); 
			context.getItemsInEquation().add(itemInEquation);

			Pot pot = null;
			if (context.isProcessingReport()) {
				pot = model.createPot(itemCode, interval, context.getPot().getRunTag().getRun().getId()); // Defensive copy
			}
			else {
				pot = model.getPot(itemCode, interval);
			}

			if(total){
				pot.getGroupTotal(context.getCompany(), context.getGroupEntry(), context.getItemsInEquation(), null, null);
			}
			else{
				pot.getItemsInEquation(context.getCompany(), context.getGroupEntry(), context.getItemsInEquation());
			}
		}
		catch(ProcessingException pe){
			throw pe; // one of ours - pass it up
		}
		catch (Exception ex) {
			throw new ProcessingException("Unable to process " + itemCode + "," + interval.getName(), ex);
		}
	}

}
