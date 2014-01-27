package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Mod;
import com.awesome.excelpp.parser.exception.MathException;

public class ModTest {
	@Test
	public void Mod() throws MathException {
		double expected = 0;
		double result = new Mod().getValue(2,2);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void ModHarder() throws MathException {
		double expected = 2;
		double result = new Mod().getValue(98, 4);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void negative() throws MathException {
		double expected = -1;
		double result = new Mod().getValue(-3, -2);
		assertEquals(expected, result, .001);
	}

	@Test
	public void divZero() throws MathException {
		double expected = Double.NaN;
		double result = new Mod().getValue(2, 0);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void notTwoArguments() throws MathException {
		@SuppressWarnings("unused")
		double result = new Mod().getValue(2);
	}

	@Test(expected = MathException.class)
	public void inValidArgument() throws MathException {
		@SuppressWarnings("unused")
		double result = new Mod().getValue(2, "test");
	}
}
