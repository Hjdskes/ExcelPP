package com.awesome.excelpp.junit.math;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.If;
import com.awesome.excelpp.math.exception.MathException;

public class IfTest {

	@Test
	public void IfTrue() throws MathException {
		assertEquals("true", new If().getValue(1<2, "true", "false"));
		
	}
	
	@Test
	public void IfFalse() throws MathException {
		assertEquals("false", new If().getValue(1<2, "true", "false"));
		
	}
	
	@Test(expected = MathException.class)
	public void IfError() throws MathException {
		assertEquals("false", new If().getValue(1<2, "true", "false", "error"));
		
	}

}
