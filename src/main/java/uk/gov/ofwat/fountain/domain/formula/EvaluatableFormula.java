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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class EvaluatableFormula extends Formula {
	private static final Log log = LogFactory.getLog(EvaluatableFormula.class);
	protected Queue<FormulaToken> evaluationQueue = new LinkedList<FormulaToken>();

	public Queue<FormulaToken> getEvaluationQueue() {
		return evaluationQueue;
	}
	
	public void setEvaluationQueue(Queue<FormulaToken> evaluationQueue){
		this.evaluationQueue = evaluationQueue;
	}

	public void addEvaluationToken(FormulaToken token) {
		evaluationQueue.add(token);
	}

	public boolean hasEvaluationErrors() {
		for (FormulaToken token : evaluationQueue) {
			if (token.hasErrors()) return true;
		}
		return false;
	}

	public String getEvaluationErrors() {
		StringBuffer r = new StringBuffer();
		for (FormulaToken token : evaluationQueue) {
			if (token.hasErrors()) {
				boolean first = false;
				for (String error : token.getErrors()) {
					if (!first) r.append(", ");
					r.append("[" + error + "]");
					first = false;
				}
				r.append("\n");
			}
		}
		return r.toString();

	}


	
	public String evaluate(FormulaContext context) {
		try {
			int pos = getPositionOfConditional();
			if (pos>=0) {
				// Got a conditional so process
				return processAsConditional(context, pos);
			}
			else {
				return processAsUnconditional(context);
			}
		}
		catch(DivByZeroException dbze){
			return "ERR - Div by zero error";
		}
		catch (ProcessingException e) {
			if(e.getMessage().contains("Div by zero")){
				// this is a cascading div by zero error
				return "ERR - Div by zero error";
			}
			if(e.getMessage().contains("ERR - ")){
				// this is a cascading error caused by another identified problem
				return e.getMessage();
			}
			
			String errorId = ErrorIdentifier.genErrorID();
			String msg = "Error: " + errorId + "\n" +
						 "Company: " + context.getCompany().getName() + "\n" +
					     "Model: " + context.getModel().getCode() + "\n" +
					     "PotId: " + context.getPot().getId();
			
			if(null != context.getPot().getItem()){
				msg += "\nItem: " + context.getPot().getItem().getCode();
			}
			log.error(msg, e);
			return errorId;
		}
	}
	
	private String processAsConditional(FormulaContext context, int positionOfConditional) {
		Queue<FormulaToken> leading = new LinkedList<FormulaToken>();
		Queue<FormulaToken> trueBranch = new LinkedList<FormulaToken>();
		Queue<FormulaToken> falseBranch = new LinkedList<FormulaToken>();
		Queue<FormulaToken> condition = new LinkedList<FormulaToken>();
		Queue<FormulaToken> trailing = new LinkedList<FormulaToken>();
		splitQueueToComponents(leading, condition, trueBranch, falseBranch, trailing, positionOfConditional);
		
		if (condition.isEmpty()) throw new ProcessingException("IF statement contains no condition");
		if (trueBranch.isEmpty()) throw new ProcessingException("IF statement contains no TRUE branch");
		if (falseBranch.isEmpty()) throw new ProcessingException("IF statement contains no FALSE branch");
		
		// Store current stack and clear it ready for evaluating the condition
		Stack<String> currentResults = context.getResultStack();
		context.setResultStack(new Stack<String>());
		evaluationQueue = condition;
		String result = evaluate(context);
		boolean cond = Boolean.parseBoolean(result);

		// Rebuild the evaluation stack, starting with anything that was trailing
		evaluationQueue.addAll(leading);
		
		if (cond) {
			// Condition is true so we only bother with the TRUE branch
			evaluationQueue.addAll(trueBranch);
		}
		else {
			// otherwise it's the FALSE branch
			evaluationQueue.addAll(falseBranch);			
		}
		
		// Add any trailing part and reinstate the result stack
		evaluationQueue.addAll(trailing);
		context.setResultStack(currentResults);
		return evaluate(context);
	}

	/**
	 * The formula is unconditional, therefore we can just evaluate it
	 */
	private String processAsUnconditional(FormulaContext context) {
		// Not conditional so evaluate
		while (!getEvaluationQueue().isEmpty() ) {
			FormulaToken token = getEvaluationQueue().poll();
			token.process(context);
		}

		if(context.getResultStack().isEmpty()){
			return null;
		}
		return context.getResultStack().pop();
	}

	/**
	 * Return TRUE only if the queue contains a conditional IF statement
	 */
	private int getPositionOfConditional() {
		// Work tail to head through the queue looking for the first conditional
		List<FormulaToken> list = new Vector<FormulaToken>(getEvaluationQueue());
		for (int i=list.size()-1; i>=0; i--) {
			FormulaToken token = list.get(i);
			if (token instanceof FunctionToken) {
				if (token.getText().equalsIgnoreCase("IF")) {
					return i;
				}
			}
		}
		
		return -1;
	}

	/**
	 * If you take the following formula :
	 * <pre>
	 * formula:
	 * TX101+IF(B101=1; B102+B103+B104; B102*B106)+TX102
	 * 
	 * which in reverse polish is: 
	 * TX101 B101 1 = B102 B103 + B104 + B102 B126 * IF + TX102 +
	 * </pre>
	 * 
	 * ...then this function assumes that the processing order is for
	 * conditionals is IF (CONDITION; TRUE-PART; FALSE-PART). It then 
	 * returns the component queues. The above example would return:
	 * 
	 * <pre>
	 * leading:    TX101
	 * condition:  B101 1 =
	 * trueBranch: B102 B103 + B104 +
	 * falseBrach: B102 B106 *
	 * trailing:   + TX102 +
	 * </pre>
	 * 
	 * The conditional, true and false branches should be self contained. 
	 * The leading and trailing queues probably won't be. The queues can
	 * then be processed and the appropriate branch followed.
	 * 
	 */
	private void splitQueueToComponents(Queue<FormulaToken> leading, 
										Queue<FormulaToken> condition,
										Queue<FormulaToken> trueBranch,
										Queue<FormulaToken> falseBranch,
										Queue<FormulaToken> trailing,
										int posOfConditional) {
		List<FormulaToken> list = new Vector<FormulaToken>(getEvaluationQueue());

		// All items before the condition are leading
		List<FormulaToken> result = new ArrayList<FormulaToken>();
		for (int i=list.size()-1; i>posOfConditional; i--) {
			result.add(0, list.get(i));
		}
		leading.addAll(result);

		// Now work out the false branch
		// For each token, start at 1 and add the number of operands
		// Keep going until the counter stays at 1
		result = new ArrayList<FormulaToken>();
		int counter = 1;
		int pos = posOfConditional-1;
		for (int i=pos; i>=0; i--) {
			FormulaToken token = list.get(i);
			counter += token.operands();
			result.add(0, token);
			if (counter==1) {
				pos = i-1;
				break;
			}
			counter--;
		}
		falseBranch.addAll(result);
		
		// Now work out the true branch
		result = new ArrayList<FormulaToken>();
		counter = 1;
		for (int i=pos; i>=0; i--) {
			FormulaToken token = list.get(i);
			counter += token.operands();
			result.add(0, token);
			if (counter==1) {
				pos = i-1;
				break;
			}
			counter--;
		}
		trueBranch.addAll(result);

		
		// Now work out the conditional
		result = new ArrayList<FormulaToken>();
		counter = 1;
		for (int i=pos; i>=0; i--) {
			FormulaToken token = list.get(i);
			counter += token.operands();
			result.add(0, token);
			if (counter==1) {
				pos = i-1;
				break;
			}
			counter--;
		}
		condition.addAll(result);
		

		// finally - build the trailing queue
		result = new ArrayList<FormulaToken>();
		for (int i=pos; i>=0; i--) {
			FormulaToken token = list.get(i);
			result.add(0, token);
		}
		trailing.addAll(result);

		
	}
}
