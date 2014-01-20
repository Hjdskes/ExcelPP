package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Min;
import com.awesome.excelpp.math.exception.MathException;

public class MinTest {
	@Test
	public void Min() throws MathException {
		assertEquals(9, new Min().getValue(10, 11, 9, 20, 15), .001);
	} 
}
