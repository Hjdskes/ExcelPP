package com.awesome.excelpp.junit.math;

<<<<<<< HEAD
import static org.junit.Assert.*;
=======
import static org.junit.Assert.assertEquals;
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993

import org.junit.Test;

import com.awesome.excelpp.math.Or;
import com.awesome.excelpp.math.exception.MathException;

public class OrTest {
<<<<<<< HEAD

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
=======
	@Test
	public void Or() throws MathException {
		assertEquals(true, new Or().getValue(2<5, false, "test"));
	}
	
	@Test
	public void OrFalse() throws MathException {
		assertEquals(false, new Or().getValue(18<2, 10>100, "test", false));
>>>>>>> ea49f0f11a0bf3c406b69a4b7feb8c296d656993
	}
}
