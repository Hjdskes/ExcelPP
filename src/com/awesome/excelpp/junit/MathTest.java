package com.awesome.excelpp.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.math.*;
import com.awesome.excelpp.math.exception.MathException;

public class MathTest {

	@Test
	public void IsEven() throws MathException {
		assertEquals(1.0,new IsEven().getValue(10),.001);
	}
	
	@Test
	public void notEven() throws MathException{
		assertEquals(0.0,new IsEven().getValue(11),.001);
	}
		
	@Test
	public void isNumber() {
		assertEquals(1.0,new IsNumber().getValue("10"),.001);
	}
	
	@Test
	public void Add() {
		assertEquals(14.0,new Add().getValue(2,4,8),.001);
	}
	
<<<<<<< HEAD
	@Test
	public void Power() {
		assertEquals(81.0, new Power().getValue(3,4), 0.001);
	}
	
	@Test
	public void Count() {
		System.out.println(new IsNumber().getValue("a"));
	}
	 
=======
>>>>>>> ba6f6274d725b4f4128e553b608c08cff9f632e4
}