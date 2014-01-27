package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Max;
import com.awesome.excelpp.parser.exception.MathException;

public class MaxTest {
	@Test
	public void Max() throws MathException {
		assertEquals(20, new Max().getValue(10, 11, 9, 20, 15), .001);
	}
}
