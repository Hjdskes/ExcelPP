package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Median;
import com.awesome.excelpp.math.exception.MathException;

public class MedianTest {
	@Test 
	public void Median() throws MathException {
		assertEquals(4.0, new Median().getValue(1.0, 4.0, 6.0), .001);
	}
	
	@Test
	public void Median2() throws MathException {
		assertEquals(5.0, new Median().getValue(1.0, 4.0, 6.0, 8.0), .001);
	}
	
	@Test(expected = MathException.class)
	public void MedianError() throws MathException {
		assertEquals(5.0, new Median().getValue(1.0, 4.0, 6.0, 8.0, "Error"), .001);
	}
}
