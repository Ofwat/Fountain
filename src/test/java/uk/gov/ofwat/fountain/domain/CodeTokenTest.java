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

import junit.framework.TestCase;
import org.apache.regexp.RE;

import uk.gov.ofwat.fountain.domain.formula.CodeToken;


public class CodeTokenTest extends TestCase {

    public void testBadCodeToken() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testBadCodeToken");
        String text = "RPIFYE";
        CodeToken codeToken = new CodeToken(text);
        assertFalse("CodeToken should not be complete.", codeToken.isComplete());
        assertTrue("CodeToken should have errors.", codeToken.getErrors().size() > 0);
    
		System.out.println("TEST: Done");
}
    
    public void testCodeToken() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCodeToken");
        String text = "RPIFYE1";
        CodeToken codeToken = new CodeToken(text);
        assertTrue("CodeToken should be complete.", codeToken.isComplete());
        assertEquals("CodeToken should not have errors.", 0, codeToken.getErrors().size());
    
		System.out.println("TEST: Done");
}
    
    public void testCodeTokenWithSuffixes() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCodeTokenWithSuffixes");
        String text = "RPIFYE1[2005-06,1,2,3]";
        CodeToken codeToken = new CodeToken(text);
        assertTrue("CodeToken should be complete.", codeToken.isComplete());
        assertEquals("CodeToken should not have errors.", 0, codeToken.getErrors().size());
        assertEquals("CodeToken should have 4 suffixes.", 4, codeToken.getSuffixes().size());
        assertEquals("Suffix 1 doesn't have expected value.", "2005-06", codeToken.getSuffixes().get(0));
        assertEquals("Suffix 2 doesn't have expected value.", "1", codeToken.getSuffixes().get(1));
        assertEquals("Suffix 3 doesn't have expected value.", "2", codeToken.getSuffixes().get(2));
        assertEquals("Suffix 4 doesn't have expected value.", "3", codeToken.getSuffixes().get(3));
    
		System.out.println("TEST: Done");
}
    
    public void testCodeTokenWithSuffixesContainingSpaces() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testCodeTokenWithSuffixesContainingSpaces");
        String text = "RPIFYE1[2005-06, 1, 2, 3]";
        CodeToken codeToken = new CodeToken(text);
        assertTrue("CodeToken should be complete.", codeToken.isComplete());
        assertEquals("CodeToken should not have errors.", 0, codeToken.getErrors().size());
        assertEquals("CodeToken should have 4 suffixes.", 4, codeToken.getSuffixes().size());
        assertEquals("Suffix 1 doesn't have expected value.", "2005-06", codeToken.getSuffixes().get(0));
        assertEquals("Suffix 2 doesn't have expected value.", "1", codeToken.getSuffixes().get(1));
        assertEquals("Suffix 3 doesn't have expected value.", "2", codeToken.getSuffixes().get(2));
        assertEquals("Suffix 4 doesn't have expected value.", "3", codeToken.getSuffixes().get(3));
    
		System.out.println("TEST: Done");
}
    
    public void testYearOffsetSuffixRegexp() throws Exception {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testYearOffsetSuffixRegexp");
    	RE yearOffsetSuffixRe = new RE(CodeToken.YEAR_OFFSET_SUFFIX_REGEXP);
    	
    	// Must have the +/- prefix
    	assertFalse(yearOffsetSuffixRe.match("1"));
    	assertFalse(yearOffsetSuffixRe.match("abc"));
    	
    	// Only digits are allowed after the +/- prefix
    	assertFalse(yearOffsetSuffixRe.match("+1a"));
    	assertFalse(yearOffsetSuffixRe.match("-1a"));
    	
    	assertTrue(yearOffsetSuffixRe.match("+123"));
    	assertTrue(yearOffsetSuffixRe.getParen(1).equals("123"));
    	
    	assertTrue(yearOffsetSuffixRe.match("-345"));
    	assertTrue(yearOffsetSuffixRe.getParen(1).equals("345"));
    
		System.out.println("TEST: Done");
}
    
}
