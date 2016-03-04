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

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import uk.gov.ofwat.fountain.api.ItemService;
import uk.gov.ofwat.fountain.api.ModelService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.Item;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.TransientDataCache;


/**
 * special case version of formula parser that
 * takes care of group total formulae. It evaluates the individual code 
 * tokens as group totals and substitutes the results into the evaluation chain.
 */
public class GroupTotalFormulaParserImpl extends FormulaParserImpl{

	private Pot originalPot;
	private ItemService itemService;
	private ModelService modelService;
	private Company company;
	private TransientDataCache  transientDataCache;
	private Basket basket;
	private EvaluatableFormula originalFormula;
	
	public GroupTotalFormulaParserImpl(Pot originalPot, 
			                           Company company,
			                           ItemService itemService, 
			                           ModelService modelService,
			                           TransientDataCache  transientDataCache,
			                           Basket basket){
		this.originalPot = originalPot;
		this.company = company;
		this.itemService = itemService;
		this.modelService = modelService;
		this.transientDataCache = transientDataCache;
		this.basket = basket;
	}
	
	public Formula parseFormula(String formula) {
		
		originalFormula = (EvaluatableFormula) super.parseFormula(formula);
		
		Queue<FormulaToken> originalEvalQueue  = originalFormula.getEvaluationQueue();
		Queue<FormulaToken> resolvedQueue = new LinkedList<FormulaToken>();
		FormulaToken ft = null;
		while((ft = originalEvalQueue.poll()) != null){
			if(ft instanceof CodeToken){
						
				List<String> suffixes = ((CodeToken) ft).getSuffixes();
				
				Pot tmpPot = new Pot();
				tmpPot.setDataService(originalPot.getDataService());
				tmpPot.setGroupService(originalPot.getGroupService());
				
				
				Interval newInterval  = calculateInterval(originalPot.getInterval(), originalPot.getReferenceService(), suffixes);
				tmpPot.setInterval(newInterval);
				tmpPot.setFormulaParser(this);
				tmpPot.setModelId(originalPot.getModel().getId());
				tmpPot.setModelService(modelService);
				tmpPot.setModelPropertiesMap(originalPot.getModelPropertiesMap());
				tmpPot.setReferenceService(originalPot.getReferenceService());
				tmpPot.setBranchDao(originalPot.getBranchDao());
				Item tmpItem = itemService.getItem(((CodeToken) ft).getCode());
				tmpPot.setItem(tmpItem);
				tmpPot.setRunTag(originalPot.getRunTag());
				
				// not accounting for any suffixes (year offsets, model inputs etc)
				Data data = tmpPot.getGroupTotal(company, transientDataCache, basket);
				// add a token of the appropriate type in place of the code token.
				String val = data.getValue();
				if(NumericToken.isNumeric(val)){
					resolvedQueue.offer(new NumericToken(val));
				}
				else{
					resolvedQueue.offer(new TextToken(val));
				}
				
			}
			else{
				resolvedQueue.offer(ft);
			}
		}
		EvaluatableFormula ef = new EvaluatableFormula();
		ef.setEvaluationQueue(resolvedQueue);
		
		
		return ef;
	}

	/**
	 * @param interval - the original interval from the caller
	 * @param suffixes - the collection of code suffixes which may
	 * include specific interval or an offset
	 * @return - the interval specified by the offsets if any.
	 */
	private Interval calculateInterval(Interval interval, ReferenceService referenceService, List<String> suffixes) {
		
		if(null == suffixes || 0 == suffixes.size()){
			// no suffixes so use original interval
			return interval;
		}
		
		int intervalOffset = 0;
		for (String suffix : suffixes) {
			if (suffix.matches(CodeToken.YEAR_OFFSET_SUFFIX_REGEXP)) {
				if (suffix.startsWith("+")) {
					suffix = suffix.substring(1);
				}
				intervalOffset = Integer.parseInt(suffix);
			}
		}
		if(0 != intervalOffset){
			return referenceService.getOffsetInterval(interval, intervalOffset);
		}
		
		// the remaining possibility at this point is that there
		// is an absolute interval (e.g.[2007-08]) or that the suffixes are
		// not interval based.	
		for(Interval iv :originalPot.getReferenceService().getIntervals()){
			if (-1 != suffixes.indexOf(iv.getName())) {
				return iv;
			}
		}
		
		// none of the suffixes appear to be intervals or interval offsets 
		// so we'll return the original interval;
		return interval;
		
	}
	

}
