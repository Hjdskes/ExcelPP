package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Quotient;
import com.awesome.excelpp.math.exception.MathException;

public class QuotientTest {
	@Test
	public void Quotient() throws MathException {
		assertEquals(2.0, new Quotient().getValue(6.0,3.0), .001);
	}
}
