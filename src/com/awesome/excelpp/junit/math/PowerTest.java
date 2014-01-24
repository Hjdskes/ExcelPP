package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Power;
import com.awesome.excelpp.math.exception.MathException;

public class PowerTest {
	@Test
	public void Power() throws MathException {
		double expected = 4;
		double result = new Power().getValue(2,2);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void powerHarder() throws MathException {
		double expected = 34.724;
		double result = new Power().getValue(2.31123, 4.234352);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void negativeExp() throws MathException {
		double expected = 0.5;
		double result = new Power().getValue(2, -1);
		assertEquals(expected, result, .001);
	}

	@Test
	public void expZero() throws MathException {
		double expected = 1;
		double result = new Power().getValue(2, 0);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void notTwoArguments() throws MathException {
		@SuppressWarnings("unused")
		double result = new Power().getValue(2);
	}

	@Test(expected = MathException.class)
	public void inValidArgument() throws MathException {
		@SuppressWarnings("unused")
		double result = new Power().getValue(2, "test");
	}
}
