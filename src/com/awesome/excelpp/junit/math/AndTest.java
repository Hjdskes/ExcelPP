package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Add;
import com.awesome.excelpp.math.And;
import com.awesome.excelpp.math.exception.MathException;

public class AndTest {
	@Test
	public void And() throws MathException {
		double expected = 1.0;
		double result = new And().getValue(2, 4, 8);
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void And_false() throws MathException {
		double expected = 1.0;
		double result = new And().getValue(2, 0, 8);
		assertEquals(expected, result, .001);
	}
	
	@Test(expected = MathException.class)
	public void And_invalid() throws MathException {
		new Add().getValue("twee", 4, 8);
	}
}
