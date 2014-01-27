package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.And;
import com.awesome.excelpp.parser.exception.MathException;

public class AndTest {
	@Test
	public void And() throws MathException {
		assertEquals(true, new And().getValue(2<5));
	}

	@Test
	public void And_false() throws MathException {
		assertEquals(false, new And().getValue(18<2));
	}	
}
