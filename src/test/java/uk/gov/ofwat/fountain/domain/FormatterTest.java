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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import junit.framework.Assert;
import junit.framework.TestCase;

public class FormatterTest extends TestCase {

	public void testFormatNumberToAtMost15Digits() {
		System.out.println("TEST: " + this.getClass().getCanonicalName() + ":testFormatNumberToAtMost15Digits");
		Formatter formatter = new Formatter();
		MathContext mathContext = new MathContext(15, RoundingMode.DOWN);
			Assert.assertEquals(0, (new BigDecimal("12.3178961165048")).compareTo(new BigDecimal("12.3178961165048", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12.3178961165048")).compareTo(new BigDecimal("12.31789611650484", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12.3178961165048")).compareTo(new BigDecimal("12.31789611650484111", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12.317896116504")).compareTo(new BigDecimal("12.317896116504", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12.3")).compareTo(new BigDecimal("12.3", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12")).compareTo(new BigDecimal("12", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("10")).compareTo(new BigDecimal("10", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("1")).compareTo(new BigDecimal("1", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("100")).compareTo(new BigDecimal("100", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("1.23178961165048")).compareTo(new BigDecimal("1.231789611650484", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("0.123178961165048")).compareTo(new BigDecimal("0.1231789611650484", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12317.8961165048")).compareTo(new BigDecimal("12317.89611650484", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12317896116504.8")).compareTo(new BigDecimal("12317896116504.84", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("123178961165048")).compareTo(new BigDecimal("123178961165048.4", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("12.3178961165048")).compareTo(new BigDecimal("12.31789611650484999", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("41.832087744073")).compareTo(new BigDecimal("41.83208774407308", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("0.0")).compareTo(new BigDecimal("0.0", mathContext)));
			Assert.assertEquals(0, (new BigDecimal("0.0")).compareTo(new BigDecimal("0", mathContext)));
				
		System.out.println("TEST: Done");
	}
}
