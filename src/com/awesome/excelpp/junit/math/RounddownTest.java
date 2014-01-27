package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Rounddown;
import com.awesome.excelpp.parser.exception.MathException;

public class RounddownTest {
	@Test
	public void Rounddown() throws MathException {
		Double expected = 10.0;
		Double result = new Rounddown().getValue(10.2);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void Rounddown_invalid() throws MathException {
		new Rounddown().getValue("twee");
	}
}
