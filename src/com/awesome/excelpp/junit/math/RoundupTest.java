package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Roundup;
import com.awesome.excelpp.math.exception.MathException;

public class RoundupTest {
	@Test
	public void Roundup() {
		assertEquals(11, new Roundup().getValue(10.2), .001);
	}
}
