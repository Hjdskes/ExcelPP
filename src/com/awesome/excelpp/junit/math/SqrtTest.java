package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Sqrt;

public class SqrtTest {
	@Test
	public void sqrt() {
		assertEquals(Math.sqrt(10), new Sqrt().getValue(10), .001);
	}
}
