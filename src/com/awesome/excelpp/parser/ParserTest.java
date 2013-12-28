package com.awesome.excelpp.parser;

import com.awesome.excelpp.models.SpreadSheet;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {	
	double expected, result;
	
	@Test
	public void test_number() {
		expected = 2.0;
		result = new Parser("=2").eval();
		assertEquals(expected, result, .001);
		
		expected = 450.257;
		result = new Parser("=450.257").eval();
		assertEquals(expected, result, .001);
		
		expected = 4.0;
		Lexer lex = new Lexer("2.+2");
		result = new Parser(lex, null).eval();
		assertTrue("2".equals(lex.next().data));
		assertTrue("+".equals(lex.next().data));
		assertTrue("2".equals(lex.next().data));
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_plus() {
		expected = 4.0;
		result = new Parser("=2+2").eval();
		assertEquals(expected, result, .001);
		
		expected = 28.0;
		result = new Parser("=25.37+2.63").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_minus() {
		expected = 0.0;
		result = new Parser("=2-2").eval();
		assertEquals(expected, result, .001);
		
		expected = -3.99;
		result = new Parser("=2.38-6.37").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_unaryminus() {
		expected = -4.0;
		result = new Parser("=-2---2").eval();
		assertEquals(expected, result, .001);
		
		expected = -.57;
		result = new Parser("=2+-2.57").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_multiply() {
		expected = 50.0;
		result = new Parser("=25*2").eval();
		assertEquals(expected, result, .001);
		
		expected = 5.0;
		result = new Parser("=2*2.5").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_division() {
		expected = 12.5;
		result = new Parser("=25/2").eval();
		assertEquals(expected, result, .001);
		
		expected = 40;
		result = new Parser("=30/.75").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_nested_functions() {
		expected = 3.0;
		result = new Parser("=Add(1,2,Add(-3,4),Subtract(5,6))").eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_cell() {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt("=4*5", 0, 2);
		expected = 16.0;
		result = new Parser("=-2*2+C1", testSheet).eval();
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_benchmark() {
		Parser test = new Parser("=2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)", null);
		double startTime = System.currentTimeMillis();
		double result = test.eval(test.toPostfix());
		double endTime = System.currentTimeMillis();
		double totalTime = endTime - startTime;
		System.out.println(result + " (" + totalTime + " ms)");
	}
}
