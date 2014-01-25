package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.IsLogical;
import com.awesome.excelpp.math.exception.MathException;

public class IsLogicalTest {

	@Test
	public void isLogical_int() throws MathException {
		assertEquals(true, new IsLogical().getValue(1));
		assertEquals(true, new IsLogical().getValue(0));
		assertEquals(false, new IsLogical().getValue(15));
	}
	
	@Test
	public void isLogical_double() throws MathException {
		assertEquals(true, new IsLogical().getValue(1.0));
		assertEquals(true, new IsLogical().getValue(0.0));
		assertEquals(false, new IsLogical().getValue(15.0));
	}
	
	@Test
	public void isLogical_string() throws MathException {
		assertEquals(true, new IsLogical().getValue("true"));
		assertEquals(true, new IsLogical().getValue("false"));
		assertEquals(false, new IsLogical().getValue("test"));
	}
	
	@Test
	public void isLogical_boolean() throws MathException {
		assertEquals(true, new IsLogical().getValue(2<5));
		assertEquals(true, new IsLogical().getValue(2+4 == 5));
		assertEquals(true, new IsLogical().getValue(true));
		assertEquals(true, new IsLogical().getValue(false));
	}
	
}
