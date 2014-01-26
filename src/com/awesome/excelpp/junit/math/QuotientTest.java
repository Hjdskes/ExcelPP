package com.awesome.excelpp.junit.math;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;

public class QuotientTest {

	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}

=======
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Quotient;
import com.awesome.excelpp.math.exception.MathException;

public class QuotientTest {
	@Test
	public void Quotient() throws MathException {
		assertEquals(2.0, new Quotient().getValue(6.0,3.0), .001);
	}
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993
}
