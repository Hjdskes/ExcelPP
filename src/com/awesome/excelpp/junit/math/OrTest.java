package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.Or;
import com.awesome.excelpp.math.exception.MathException;

public class OrTest {

	@Test
	public void Or() throws MathException {
		assertEquals(true, new Or().getValue(1<3, 4<5));
	}
	
	@Test
	public void OrNot() throws MathException {
		assertEquals(false, new Or().getValue(1>3, 4>5));
	}
	
	@Test
	public void OrOrder() throws MathException {
		assertEquals(true, new Or().getValue(1<3, 4<5, "error", 1));
	}

	@Test(expected = MathException.class)
	public void OrError() throws MathException {
		assertEquals(true, new Or().getValue("error", 1<3, 4<5, 1));
	}
}
