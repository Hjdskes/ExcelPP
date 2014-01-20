package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.Not;
import com.awesome.excelpp.math.exception.MathException;

public class NotTest {

	@Test
	public void NotTrue() throws MathException {
		assertEquals("false", new Not().getValue(true));
	}
	
	@Test
	public void NotFalse() throws MathException {
		assertEquals("true", new Not().getValue(false));
	}
	
	@Test
	public void NotDoubleFalse() throws MathException {
		assertEquals("0.0", new Not().getValue(1.0));
	}
	
	@Test
	public void NotDoubleTrue() throws MathException {
		assertEquals("1.0", new Not().getValue(0.0));
	}
	
	@Test(expected = MathException.class)
	public void NotDoubleError() throws MathException {
		assertEquals("true", new Not().getValue(2.0));
	}
	
	@Test
	public void NotIntFalse() throws MathException {
		assertEquals("0", new Not().getValue(1));
	}
	
	@Test
	public void NotIntTrue() throws MathException {
		assertEquals("1", new Not().getValue(0));
	}
	
	@Test(expected = MathException.class)
	public void NotIntError() throws MathException {
		assertEquals("true", new Not().getValue(2));
	}
	
	@Test(expected = MathException.class)
	public void NotGeneralException() throws MathException {
		assertEquals("0.0", new Not().getValue("Hallo"));
	}
}
