package com.awesome.excelpp.junit.math;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;

public class SignTest {

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
=======
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Sign;
import com.awesome.excelpp.math.exception.MathException;

public class SignTest {
	@Test
	public void signPositive() throws MathException {
		int expected = 1;
		int result = new Sign().getValue(2);
		assertEquals(expected, result);
	}
	
	@Test
	public void signNegative() throws MathException {
		int expected = -1;
		int result = new Sign().getValue(-2);
		assertEquals(expected, result);
	}
	
	@Test
	public void signZero() throws MathException {
		int expected = 0;
		int result = new Sign().getValue(0);
		assertEquals(expected, result);
	}

	@Test(expected = MathException.class)
	public void signMulArgs() throws MathException {
		@SuppressWarnings("unused")
		int result = new Sign().getValue(-3,4,99);
	}

	@Test
	public void signDoublePositive() throws MathException {
		double expected = 1.0;
		double result = new Sign().getValue(2.0);
		assertEquals(expected, result, .001);
	}

	@Test
	public void signDoubleNegative() throws MathException {
		double expected = -1.0;
		double result = new Sign().getValue(-2.0);
		assertEquals(expected, result, .001);
	}

	@Test(expected = MathException.class)
	public void signString() throws MathException {
		@SuppressWarnings("unused")
		double result = new Sign().getValue("test");
	}

	@Test(expected = MathException.class)
	public void signBool() throws MathException {
		@SuppressWarnings("unused")
		double result = new Sign().getValue(false);
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993
	}
}
