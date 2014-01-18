package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Average;
import com.awesome.excelpp.math.exception.MathException;

public class AverageTest {
	@Test
	public void Average() throws MathException {
		double expected = 13.0;
		double result = new Average().getValue(10, 11, 9, 20, 15);
		assertEquals(expected, result, .001);
	}
	
	@Test(expected = MathException.class)
	public void Average_invalid() throws MathException {
		new Average().getValue("test", 2);
	}
}