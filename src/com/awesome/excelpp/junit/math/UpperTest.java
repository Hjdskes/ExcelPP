package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.awesome.excelpp.math.Upper;
import com.awesome.excelpp.parser.exception.MathException;

public class UpperTest {
	@Test
	public void Upper() throws MathException {
		assertTrue("TEST".equals((String)new Upper().getValue("Test")));
	}
	
	@Test(expected = MathException.class)
	public void NotUpper() throws MathException {
		new Upper().getValue(true);
	}
	
	@Test(expected = MathException.class)
	public void UpperEmpty() throws MathException {
		new Upper().getValue();
	}
}
