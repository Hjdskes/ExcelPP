package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Lower;

public class LowerTest {
	@Test
	public void Lower() {
		assertEquals("test", new Lower().getValue("TEST"));
	}
}
