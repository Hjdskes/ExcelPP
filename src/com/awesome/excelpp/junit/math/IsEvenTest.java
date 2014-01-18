package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.IsEven;
import com.awesome.excelpp.math.exception.MathException;

public class IsEvenTest {
	@Test
	public void IsEven() throws MathException {
		double expected = 1.0;
		double result = new IsEven().getValue(10);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void IsEvenMultiple() throws MathException {
		double expected = 1.0;
		double result = new IsEven().getValue(10, 20);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void notEven() throws MathException{
		double expected = 0.0;
		double result = new IsEven().getValue(11);
		assertEquals(expected, result, .001);
	}
	
	@Test(expected = MathException.class)
	public void IsEvenError() throws MathException {
		double result = new IsEven().getValue("twee");
	}
}
