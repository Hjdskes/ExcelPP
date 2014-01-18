package com.awesome.excelpp.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.excelpp.math.Add;
import com.awesome.excelpp.math.Average;
import com.awesome.excelpp.math.IsEven;
import com.awesome.excelpp.math.IsNumber;
import com.awesome.excelpp.math.Max;
import com.awesome.excelpp.math.Min;
import com.awesome.excelpp.math.Power;
import com.awesome.excelpp.math.Rounddown;
import com.awesome.excelpp.math.Roundup;
import com.awesome.excelpp.math.Sqrt;
import com.awesome.excelpp.math.exception.MathException;
import com.awesome.excelpp.stringmath.Int;
import com.awesome.excelpp.stringmath.Lower;
import com.awesome.excelpp.stringmath.Upper;

public class MathTest {

	@Test
	public void IsEven() throws MathException {
		assertEquals(1.0,new IsEven().getValue(10),.001);
	}
	
	@Test(expected = MathException.class)
	public void IsEvenError() throws MathException {
		assertEquals(1.0,new IsEven().getValue(10, 20),.001);
	}
	
	@Test
	public void notEven() throws MathException{
		assertEquals(0.0,new IsEven().getValue(11),.001);
	}
		
	@Test
	public void isNumber() {
		assertEquals("true",new IsNumber().getValue("10"));
		assertEquals("false",new IsNumber().getValue("a"));
	}
	
	@Test
	public void Add() throws MathException {
		assertEquals(14.0,new Add().getValue(2,4,8),.001);
	}

	@Test
	public void Power() throws MathException {
		assertEquals(81.0, new Power().getValue(3,4), 0.001);
	}
	
	@Test
	public void Upper() {
		assertEquals("TEST", new Upper().getValue("Test"));
	}
	
	@Test
	public void Lower() {
		assertEquals("test", new Lower().getValue("TEST"));
	}
	
	
	@Test
	public void Int() throws MathException {
		assertEquals("1", new Int().getValue("1.0"));
		assertEquals("2", new Int().getValue("2"));
	}
	
	@Test(expected = MathException.class)
	public void IntFalse() throws MathException {
		assertEquals("1", new Int().getValue("a"));
	} 
	
	@Test
	public void sqrt() {
		assertEquals(Math.sqrt(10), new Sqrt().getValue(10), .001);
	} 
	
	@Test
	public void Min() throws MathException {
		assertEquals(9, new Min().getValue(10, 11, 9, 20, 15), .001);
	} 
	
	@Test
	public void Max() throws MathException {
		assertEquals(20, new Max().getValue(10, 11, 9, 20, 15), .001);
	} 
	
	@Test
	public void Average() throws MathException {
		assertEquals(13, new Average().getValue(10, 11, 9, 20, 15), .001);
	} 
	
	@Test
	public void Roundup() {
		assertEquals(11, new Roundup().getValue(10.2), .001);
	} 	
	
	@Test
	public void Rounddown() {
		assertEquals(10, new Rounddown().getValue(10.2), .001);
	} 
}