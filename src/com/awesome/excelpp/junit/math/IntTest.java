package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Int;
import com.awesome.excelpp.math.exception.MathException;

public class IntTest {
	@Test
	public void Int() throws MathException {
		assertEquals("1", new Int().getValue("1.0"));
		assertEquals("2", new Int().getValue("2"));
	}
	
	@Test(expected = MathException.class)
	public void IntFalse() throws MathException {
		assertEquals("1", new Int().getValue("a"));
	}
}
