package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Count;
import com.awesome.excelpp.math.CountIf;
import com.awesome.excelpp.math.Counta;
import com.awesome.excelpp.math.exception.MathException;

public class CountTest {
	@Test
	public void Count() throws MathException {
		String expected = "1";
		String result = new Count().getValue(2);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountTest2() throws MathException {
		String expected = "3";
		String result = new Count().getValue(2, 0, 8);
		assertEquals(expected, result);
	}
	
	@Test
	public void CountaTest() throws MathException {
		String expected = "3";
		String result = new Counta().getValue(2.0, 0, 8, "Test");
		assertEquals(expected, result);
	}
	
	@Test
	public void CountIf() throws MathException {
		String expected = "2";
		String result = new CountIf().getValue(8, 0, 8, "Test", "Hallo", 8.0, 5, 8);
		assertEquals(expected, result);
	}
}
