package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Subtract;
import com.awesome.excelpp.math.exception.MathException;

public class SubtractTest {
	@Test
	public void Subtract() throws MathException {
		double expected = -10.0;
		double result = (Double)new Subtract().getValue(2, 4, 8);
		assertEquals(expected, result, .001);
	}
	
	@Test(expected = MathException.class)
	public void Subtract_invalid_1() throws MathException {
		assertEquals("1", new Subtract().getValue("twee", 4, 8), .001);
	}
	
	@Test(expected = MathException.class)
	public void Subtract_overload() throws MathException {
		assertEquals("1", new Subtract().getValue(), .001);
	}
	
	@Test(expected = MathException.class)
	public void Subtract_invalid_2() throws MathException {
		assertEquals("1", new Subtract().getValue(4,"twee", 8), .001);
	}
}
