package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Add;
import com.awesome.excelpp.math.exception.MathException;

public class AddTest {
	@Test
	public void Add() throws MathException {
		Double expected = 14.0;
		Double result = new Add().getValue(2, 4, 8);
		assertEquals(expected, result);
	}
	
	@Test(expected = MathException.class)
	public void Add_invalid() throws MathException {
		new Add().getValue("twee", 4, 8);
	}
}
