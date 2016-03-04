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

import java.util.Collection;
import java.util.List;
import java.util.Stack;

import uk.gov.ofwat.fountain.api.GroupService;
import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemInEquation;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.TransientDataCache;

public class FormulaContext {
	private Stack<String> resultStack;
	private Pot pot;
	private Model model;
	private Company company;
	private GroupEntry groupEntry;
	private ReferenceService referenceService;
	private GroupService groupService;
	private Basket basket;
	private TransientDataCache transientDataCache;
	private List<ItemInEquation> itemsInEquation;
	private EquationParseStratagy equationParseStratagy;
	private boolean processingReport; 
	
	public EquationParseStratagy getEquationParseStratagy() {
		return equationParseStratagy;
	}

	public void setEquationParseStratagy(EquationParseStratagy equationParseStratagy) {
		this.equationParseStratagy = equationParseStratagy;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public void setReferenceService(ReferenceService service) {
		this.referenceService = service;
	}

	
	public GroupService getGroupService() {
		return groupService;
	}

	public TransientDataCache getTransientDataCache() {
		return transientDataCache;
	}

	public void setTransientDataCache(TransientDataCache transientDataCache) {
		this.transientDataCache = transientDataCache;
	}

	public FormulaContext(){
		resultStack = new Stack<String>();
		this.setEquationParseStratagy(new EquationParseStratagyExecuteFormula());
	}

	public FormulaContext(Pot pot, Model model, Company company, GroupEntry groupEntry, ReferenceService referenceService, GroupService groupService, boolean processingReport) {
		resultStack = new Stack<String>();
		this.pot = pot;
		this.model = model;
		this.company = company;
		this.groupEntry = groupEntry;
		this.referenceService = referenceService;
		this.groupService = groupService;
		this.itemsInEquation = itemsInEquation;
		this.setEquationParseStratagy(new EquationParseStratagyExecuteFormula());
		this.processingReport = processingReport;
	}
	
	public FormulaContext(Pot pot, Model model, Company company, GroupEntry groupEntry, ReferenceService referenceService, GroupService groupService, List<ItemInEquation> itemsInEquation) {
		this(pot, model, company, groupEntry, referenceService, groupService, false);
		this.itemsInEquation = itemsInEquation;
	}

	public FormulaContext(Pot pot, Model model, Company company, GroupEntry groupEntry, ReferenceService referenceService, GroupService groupService, List<ItemInEquation> itemsInEquation, boolean processingReport) {
		this(pot, model, company, groupEntry, referenceService, groupService, processingReport);
		this.itemsInEquation = itemsInEquation;
	}

	public Pot getPot() {
		return pot;
	}

	public GroupEntry getGroupEntry() {
		return groupEntry;
	}

	public Model getModel() {
		return model;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Collection<Interval> getIntervals() {
		return referenceService.getIntervals();
	}
	
	public Stack<String> getResultStack() {
		return resultStack;
	}

	public void setResultStack(Stack<String> resultStack) {
		this.resultStack = resultStack;
	}

	public List<ItemInEquation> getItemsInEquation() {
		return itemsInEquation;
	}

	public void processOperatorToken(OperatorToken operatorToken) {
		this.getEquationParseStratagy().processOperatorToken(this, operatorToken);
	}

	public void processCodeToken(CodeToken codeToken) {
		this.getEquationParseStratagy().processCodeToken(this, codeToken);
	}

	public boolean isProcessingReport() {
		return processingReport;
	}
	
	public void setProcessingReport(boolean processingReport) {
		this.processingReport = processingReport;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setPot(Pot pot) {
		this.pot = pot;
	}

}
