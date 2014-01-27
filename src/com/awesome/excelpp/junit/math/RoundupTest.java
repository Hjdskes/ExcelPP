package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Roundup;
import com.awesome.excelpp.parser.exception.MathException;

public class RoundupTest {
	@Test
	public void Rounddown() throws MathException {
		Double expected = 10.0;
		Double result = new Roundup().getValue(9.5);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void Rounddown_invalid() throws MathException {
		new Roundup().getValue("twee");
	}
}
