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
}
