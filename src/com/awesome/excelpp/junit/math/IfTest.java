package com.awesome.excelpp.junit.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.If;
import com.awesome.excelpp.parser.exception.MathException;

public class IfTest {

	@Test
	public void IfTrue() throws MathException {
		assertEquals("true", new If().getValue(1<2, "true", "false"));
		
	}
	
	@Test
	public void IfFalse() throws MathException {
		assertEquals("false", new If().getValue(1>2, "true", "false"));
		
	}
	
	@Test(expected = MathException.class)
	public void IfError() throws MathException {
		assertEquals("false", new If().getValue(1<2, "true", "false", "error"));
		
	}

	@Test
	public void If() throws MathException {
		assertEquals("Goed", new If().getValue(2<10, "Goed", "Fout"));
	}

	@Test
	public void ifFout() throws MathException {
		assertEquals("Fout", new If().getValue(2>10, "Goed", "Fout"));
	}
	
	@Test
	public void ifArgTest() throws MathException {
		assertEquals("2.0", new If().getValue(1.0, 2.0, true));
	}

	@Test(expected = MathException.class)
	public void ifException() throws MathException {
		assertEquals("", new If().getValue(2<10));
	}
}
