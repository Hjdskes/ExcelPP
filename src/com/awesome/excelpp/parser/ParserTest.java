package com.awesome.excelpp.parser;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.parser.exception.MissingArgException;
import com.awesome.excelpp.parser.exception.MissingLBracketException;
import com.awesome.excelpp.parser.exception.MissingRBracketException;
import com.awesome.excelpp.parser.exception.ParserException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParserTest {	
	double expected, result;
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void test_number() throws ParserException {
		expected = 2.0;
		result = new Parser("=2").eval();
		assertEquals(expected, result, .001);
		
		expected = 450.257;
		result = new Parser("=450.257").eval();
		assertEquals(expected, result, .001);		

		expected = 4.0;
		result = new Parser("2.+2").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_plus() throws ParserException {
		expected = 4.0;
		result = new Parser("=2+2").eval();
		assertEquals(expected, result, .001);
		
		expected = 28.0;
		result = new Parser("=25.37+2.63").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_minus() throws ParserException {
		expected = 0.0;
		result = new Parser("=2-2").eval();
		assertEquals(expected, result, .001);
		
		expected = -3.99;
		result = new Parser("=2.38-6.37").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_unaryminus() throws ParserException {
		expected = -4.0;
		result = new Parser("=-2---2").eval();
		assertEquals(expected, result, .001);
		
		expected = -.57;
		result = new Parser("=2+-2.57").eval();
		assertEquals(expected, result, .001);
		
		expected = 1.43;
		result = new Parser("=2+-.57").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_multiply() throws ParserException {
		expected = 50.0;
		result = new Parser("=25*2").eval();
		assertEquals(expected, result, .001);
		
		expected = 5.0;
		result = new Parser("=2*2.5").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_division() throws ParserException {
		expected = 12.5;
		result = new Parser("=25/2").eval();
		assertEquals(expected, result, .001);
		
		expected = 40.0;
		result = new Parser("=30/.75").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_brackets() throws ParserException {
		expected = 4.0;
		result = new Parser("=(2+2)").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_function() throws ParserException {
		expected = 6.0;
		result = new Parser("=Add(2,4)").eval();
		assertEquals(expected, result, .001);
		
		expected = -1.0;
		result = new Parser("=Subtract(5,6)").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_nested_functions() throws ParserException {
		expected = 3.0;
		result = new Parser("=Add(1,2,Add(-3,4),Subtract(5,6))").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_cell() throws ParserException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt("=4*5", 0, 2);
		expected = 16.0;
		result = new Parser("=-2*2+C1", testSheet).eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_invalid_function1() throws ParserException {
		exception.expect(MissingRBracketException.class);
		result = new Parser("=Add(").eval();
	}
	
	@Test
	public void test_invalid_function2() throws ParserException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Add())").eval();
	}
	
	@Test
	public void test_invalid_function3() throws ParserException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Add").eval();
	}
	
	@Test
	public void test_invalid_function4() throws ParserException {
		exception.expect(MissingArgException.class);
		result = new Parser("=Add()").eval();
	}
	
	@Test
	public void test_invalid_function5() throws ParserException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Add 5,5").eval();
	}
}
