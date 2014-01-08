package com.awesome.excelpp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.*;

public class MathTest {

	@Test
	public void IsEven() {
		assertTrue(IsEven.getValue(10));
	}
	
	@Test
	public void notEven(){
		assertFalse(IsEven.getValue(11));
	}
	
	@Test
	public void isNumber() {
		assertTrue(IsNumber.getValue("10"));
	}
	
	@Test 
	public void isNotNumber() {
		assertFalse(IsNumber.getValue("a"));
	}
	
	@Test 
	public void Power() {
		assertEquals(Math.pow(2, 2), Power.getValue(2,2));
	}

}
