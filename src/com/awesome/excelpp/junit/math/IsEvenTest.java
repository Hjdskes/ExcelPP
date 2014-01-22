package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.IsEven;
import com.awesome.excelpp.math.exception.MathException;

public class IsEvenTest {
	@Test
	public void IsEven() throws MathException {
		boolean expected = true;
		boolean result = new IsEven().getValue(10);
		assertEquals(expected, result);
	}
	
	@Test(expected = MathException.class)
	public void IsEvenMultiple() throws MathException {
		boolean expected = true;
		boolean result = new IsEven().getValue(10, 20);
		assertEquals(expected, result);
	}
	
	@Test
	public void notEven() throws MathException{
		boolean expected = false;
		boolean result = new IsEven().getValue(11);
		assertEquals(expected, result);
	}
	
	@Test(expected = MathException.class)
	public void IsEvenError() throws MathException {
		boolean result = new IsEven().getValue("twee");
	}
}
