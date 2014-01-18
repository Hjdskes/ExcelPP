package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Rounddown;
import com.awesome.excelpp.math.exception.MathException;

public class RounddownTest {
	@Test
	public void Rounddown() {
		assertEquals(10, new Rounddown().getValue(10.2), .001);
	}
}
