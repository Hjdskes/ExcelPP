package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Upper;
import com.awesome.excelpp.math.exception.MathException;

public class UpperTest {
	@Test
	public void Upper() throws MathException {
		assertEquals("TEST", new Upper().getValue("Test"));
	}
	
	@Test(expected = MathException.class)
	public void NotUpper() throws MathException {
		assertEquals("TEST", new Upper().getValue(10));
	}
}
