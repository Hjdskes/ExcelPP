package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Count;
import com.awesome.excelpp.math.CountIf;
import com.awesome.excelpp.math.CountA;
import com.awesome.excelpp.math.exception.MathException;

public class CountTest {
	@Test
	public void Count() throws MathException {
		int expected = 1;
		int result = new Count().getValue(2);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountTest2() throws MathException {
		int expected = 3;
		int result = new Count().getValue(2, 0, 8);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountTest0() throws MathException {
		int expected = 0;
		int result = new Count().getValue();
		assertEquals(expected, result);
	}
	
	@Test
	public void CountaTest() throws MathException {
		int expected = 3;
		int result = new CountA().getValue(2.0, 0, 8, "Test");
		assertEquals(expected, result);
	}
	
	@Test
	public void CountaTest2() throws MathException {
		int expected = 1;
		int result = new CountA().getValue(true);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountaTest3() throws MathException {
		int expected = 0;
		int result = new CountA().getValue("", 0.0, 0);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIf() throws MathException {
		int expected = 1;
		int result = new CountIf().getValue(8, 0, 8, "Test", "Hallo", 8.0, 5, 8, 0.0, "Hallo");
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIfEmpty() throws MathException {
		int expected = 0;
		int result = new CountIf().getValue(8, 0, 0.0, "");
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIf2() throws MathException {
		int expected = 0;
		int result = new CountIf().getValue("Test", "Test", "Hallo");
		assertEquals(expected, result);
	}

	@Test
	public void CountIf3() throws MathException {
		int expected = 2;
		int result = new CountIf().getValue(true, 35.46, "Nancy", "Nijntje", "John", "N");
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIfBoolean() throws MathException {
		int expected = 1;
		int result = new CountIf().getValue(true, "Test", "Hallo", true);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIfBooleanFalse() throws MathException {
		int expected = 0;
		int result = new CountIf().getValue(false, "Test", "Hallo", false, true);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIfDouble() throws MathException {
		int expected = 1;
		int result = new CountIf().getValue(1.0, 2.0, 1.0);
		assertEquals(expected, result);
	}
}
