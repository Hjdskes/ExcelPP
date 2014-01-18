package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Add;
import com.awesome.excelpp.math.And;
import com.awesome.excelpp.math.Count;
import com.awesome.excelpp.math.exception.MathException;

public class CountTest {
	@Test
	public void Count() throws MathException {
		double expected = 2.0;
		double result = new And().getValue(2);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void CountTest2() throws MathException {
		double expected = 3.0;
		double result = new And().getValue(2, 0, 8);
		assertEquals(expected, result, .001);
	}
}
