package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Or;
import com.awesome.excelpp.math.exception.MathException;

public class OrTest {
	@Test
	public void Or() throws MathException {
		assertEquals(true, new Or().getValue(2<5, false, "test"));
	}
	
	@Test
	public void OrFalse() throws MathException {
		assertEquals(false, new Or().getValue(18<2, 10>100, "test", false));
	}
}
