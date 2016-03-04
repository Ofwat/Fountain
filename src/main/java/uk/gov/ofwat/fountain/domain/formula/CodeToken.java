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

import uk.gov.ofwat.fountain.api.ReferenceService;
import uk.gov.ofwat.fountain.domain.Basket;
import uk.gov.ofwat.fountain.domain.Company;
import uk.gov.ofwat.fountain.domain.Data;
import uk.gov.ofwat.fountain.domain.GroupEntry;
import uk.gov.ofwat.fountain.domain.Interval;
import uk.gov.ofwat.fountain.domain.ItemInEquation;
import uk.gov.ofwat.fountain.domain.Model;
import uk.gov.ofwat.fountain.domain.Pot;
import uk.gov.ofwat.fountain.domain.TransientDataCache;

/**
 * A code token, representing an item code with zero or more suffixes.
 */
public class CodeToken extends FormulaToken {

	private Interval interval = null;
	private int intervalOffset = 0;
	private static final Log log = LogFactory.getLog(CodeToken.class);
	

	/**
	 * Regular expression that matches an item code followed by zero more
	 * comma-separated suffixes, with the list of suffixes enclosed in square
	 * brackets.
	 */
	public static final String CODE_TOKEN_REGEXP = "(" + "[A-Z][A-Z_]*[0-9]+[A-Z0-9_]*" + ")(\\[(.*?)\\])?";

	public static final String YEAR_OFFSET_SUFFIX_REGEXP = "^[+-](\\d+)$";
	
//	public static final String GROUP_ENTRY_REGEX = "";

	public static final String MODEL_NONE = "NONE";

	public static final String GROUP_ENTRY_NONE = "NO_GROUP";

//	public static final String MODEL_SUFFIX_REGEX = "";

	protected String code;
	protected List<String> suffixes;


	
	/**
	 * Creates a new code token from the supplied expression.
	 *
	 * @param text an expression, e.g. <code>"TX001[JR]"</code>
	 */
	public CodeToken(String text) {

		// Create an RE for matching the expression
		RE codeRegexp = null;
		try {
			codeRegexp = new RE(CODE_TOKEN_REGEXP);
		} catch (RESyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// The expression must be matched by the RE
		boolean matches = codeRegexp.match(text);
		if (!matches) {
            errors.add("\"" + text + "\" is not a valid code");
		}

        else {
    		// Extract the code
    		code = codeRegexp.getParen(1);
//    		log.debug("Code is " + code);

    		// Extract the suffixes (if there are any)
            suffixes = new Vector<String>();
    		String suffixPart = codeRegexp.getParen(3);
    		if (suffixPart != null) {
//    			log.debug("Suffix part is " + suffixPart);
    			String[] suffixArray = suffixPart.split(",");
                for (String suffix : suffixArray) {
                    suffixes.add(suffix.trim());
                }
    		}
        }
	}


	/**
	 * Returns this token's code.
	 *
	 * <p>For example, in the equation <code>"BON103[2006-07]"</code> the code
	 * is <code>"BON103"</code>.
	 *
	 * @return the token's code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns this token's suffixes.
	 *
	 * <p>For example, in the equation <code>"TX002[2001-02, SEF2, PS]"</code>
	 * the suffixes are <code>"2001-02"</code>, <code>"SEF2"</code> and
	 * <code>"PS"</code>.
	 *
	 * @return the token's suffixes
	 */
	public List<String> getSuffixes() {
		return suffixes;
	}

	public Model getModel(FormulaContext context) {
		// we may need to use an input model to get the correct pot
		Model model = context.getModel();
		String modSuffix =getModelSuffix(context); 
		if(modSuffix != MODEL_NONE){
			// Retrieve the actual model code from the input
			model = model.getInputModel(modSuffix);
			if (null == model) {
				log.error("Invalid model suffix " + modSuffix + " in equation for " + context.getPot().getItem().getCode() + " " + context.getPot().getInterval().getName());
				throw new ProcessingException("Invalid model suffix " + modSuffix + " in equation for " + context.getPot().getItem().getCode() + " " + context.getPot().getInterval().getName());
			}
		}
		return model;
	}

	private String getModelSuffix(FormulaContext context){
		// return a suffix that is not a year, offset, 'TOTAL' or a group entry

		String model = null;
		for(String suffix : getSuffixes()){
			if((!suffix.matches(YEAR_OFFSET_SUFFIX_REGEXP)
			   && (!suffix.equalsIgnoreCase("TOTAL"))
			   && (!suffix.equalsIgnoreCase("NONE")))){
				// either a year or a model
				
				if(!Character.isDigit(suffix.charAt(0))){
					// this might be the model
					if(null != context.getModel().getInputModel(suffix)){
						model = suffix;
						break; // recognised a model code
					}
				}

			}
		}
		if(null == model){
			// set model to NONE
			model = MODEL_NONE;

		}
		return model;
	}
	
	public GroupEntry getGroupEntry(FormulaContext context){
		String geString = getGroupEntrySuffix(context); 
		if(geString != GROUP_ENTRY_NONE){
			// There is a group entry suffix so jump to that one
			return context.getGroupService().getGroupEntryForCompany(context.getCompany(), geString);
		}
		return context.getGroupEntry(); // no group entry specified so continue in current context.
	}
	
	
	private String getGroupEntrySuffix(FormulaContext context){
		String ge = null;
		for(String suffix : getSuffixes()){
			if((!suffix.matches(YEAR_OFFSET_SUFFIX_REGEXP)
			   && (!suffix.equalsIgnoreCase("TOTAL"))
			   && (!suffix.equalsIgnoreCase("NONE")))){
				// either a year or a model
				
				if(!Character.isDigit(suffix.charAt(0))){
					// this might be the model
					if(null == context.getModel().getInputModel(suffix)){
						ge = suffix;
						break; // must be a group entry
					}
				}

			}
		}
		if(null == ge){
			ge = GROUP_ENTRY_NONE;
		}
		return ge;
	}

    @Override
    public boolean isComplete() {
        return (code != null);
    }

    @Override
    public String getText() {
        return "CodeToken";
    }

    @Override
    public String toString() {
        return "CodeToken[" + code + ", suffixes=" + suffixes + "]";
    }

	public void process(FormulaContext context) {
		context.processCodeToken(this);
	}

	public Interval getYear(FormulaContext context) {
		if (null == interval) {
			List<String> suffixes = getSuffixes();
			for (Interval tmpYear : context.getIntervals()) {
				if (-1 != suffixes.indexOf(tmpYear.getName())) {
					interval = tmpYear;
				}
			}
		}
		if (null == interval) {
			ReferenceService referenceService = context.getPot().getReferenceService();
			interval = referenceService.getOffsetInterval(context.getPot().getInterval(), getYearOffset());
		}
		if(null == interval){
			// error
			log.error("unable to find offset interval: " + context.getPot().getInterval() + " " + getYearOffset());
			throw new ProcessingException("missing offset interval " + context.getPot().getInterval() + " " + getYearOffset());
		}
		return interval;
	}


	private int getYearOffset() {
		if (0 == intervalOffset) {
			for (String suffix : suffixes) {
				if (suffix.matches(CodeToken.YEAR_OFFSET_SUFFIX_REGEXP)) {
					if (suffix.startsWith("+")) {
						suffix = suffix.substring(1);
					}
					intervalOffset = Integer.parseInt(suffix);
				}
			}
		}
		return intervalOffset;
	}
	
	@Override
	public int operands() {
		return 0;
	}

}
