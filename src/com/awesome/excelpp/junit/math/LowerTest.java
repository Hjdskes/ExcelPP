package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Lower;
import com.awesome.excelpp.math.exception.MathException;

public class LowerTest {
	@Test
	public void Lower() throws MathException {
		assertEquals("test", new Lower().getValue("TEST"));
	}
	
	@Test(expected = MathException.class)
	public void NotLower() throws MathException {
		assertEquals("test", new Lower().getValue(10));
	}
	
	@Test(expected = MathException.class)
	public void LowerOverload() throws MathException {
		assertEquals("test", new Lower().getValue(10, 20));
	}
}
