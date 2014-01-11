package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.*;
import com.awesome.excelpp.math.exception.MathException;

public class MathTest {

	@Test
	public void IsEven() throws MathException {
		assertEquals(1.0, new IsEven().getValue(10), 0.001);
	}
	
	@Test
	public void notEven() throws MathException{
		assertEquals(0.0, new IsEven().getValue(11), 0.001);
	}
	
	@Test(expected = Exception.class) 
	public void EvenException() throws MathException{
		assertEquals(0.0, new IsEven().getValue(11, 1), 0.001);
	}
	
	@Test
	public void isNumber() {
		assertEquals(1.0, new IsNumber.getValue(10), 0.001);
	}
	
	@Test 
	public void isNotNumber() {
		assertEquals(0.0, new IsNumber.getValue("a"), .001);
	}
	
}
