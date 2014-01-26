package com.awesome.excelpp.junit.math;

<<<<<<< HEAD
import org.junit.Test;

public class SumIfTest {
	
	@Test
	public void SumTest() {
		System.out.println("error");
=======
import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.SumIf;
import com.awesome.excelpp.math.exception.MathException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.RecursionException;

public class SumIfTest {

	@Test
	public void test_gCondition() throws MathException, ParserException, RecursionException {
		Double expected = 2.0;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\">2.0\"", 1.0, 1.0, 1.0, 1.0);
		assertEquals(expected, result);
	}

	@Test
	public void test_lCondition() throws MathException, ParserException, RecursionException {
		Double expected = 1.0;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\"<2.0\"", 1.0, 1.0, 1.0, 1.0);
		assertEquals(expected, result);
	}
	
	@Test
	public void test_geqCondition() throws MathException, ParserException, RecursionException {
		Double expected = 3.0;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\">=2.0\"", 1.0, 1.0, 1.0, 1.0);
		assertEquals(expected, result);
	}
	
	@Test
	public void test_leqCondition() throws MathException, ParserException, RecursionException {
		Double expected = 2.0;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\"<=2.0\"", 1.0, 1.0, 1.0, 1.0);
		assertEquals(expected, result);
	}
	
	@Test
	public void test_eqCondition() throws MathException, ParserException, RecursionException {
		Double expected = 1.5;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\"2.0\"", 1.0, 1.0, 1.5, 1.0);
		assertEquals(expected, result);
	}
	
	@Test
	public void test_eqStringCondition() throws MathException, ParserException, RecursionException {
		Double expected = 2.0;
		Double result = new SumIf().getValue("Apples", "Poop", "Oranges", "Apples", "\"Apples\"", 1.0, 1.0, 1.0, 1.0);
		assertEquals(expected, result);
	}
	
	@Test
	public void test_no_sumRange() throws MathException, ParserException, RecursionException {
		Double expected = 7.0;
		Double result = new SumIf().getValue(4.0, 3.0, 2.0, 1.0, "\">2.0\"");
		assertEquals(expected, result);
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993
	}
}
