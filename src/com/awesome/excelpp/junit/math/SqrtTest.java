package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Sqrt;
import com.awesome.excelpp.math.exception.MathException;

public class SqrtTest {
	@Test
	public void sqrt() throws MathException {
		double expected = Math.sqrt(10);
		double result = new Sqrt().getValue(10);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void Sqrt_invalid() throws MathException {
		assertEquals(1 ,new Sqrt().getValue(), .001);
	}
}
