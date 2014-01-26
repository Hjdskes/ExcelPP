package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;
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

	@Test
	public void Or3() throws MathException {
		assertEquals(false, new Or().getValue("false", 1>3, 4>5, 0));
	}

	@Test
	public void Or2() throws MathException {
		assertEquals(true, new Or().getValue(2<5, false, "test"));
	}
	
	@Test
	public void OrFalse() throws MathException {
		assertEquals(false, new Or().getValue(18<2, 10>100, "test", false));
	}
}
