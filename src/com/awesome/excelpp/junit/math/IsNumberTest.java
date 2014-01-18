package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.IsNumber;

public class IsNumberTest {
	@Test
	public void isNumber() {
		assertEquals("true",new IsNumber().getValue("10"));
		assertEquals("false",new IsNumber().getValue("a"));
	}
}
