package com.awesome.excelpp.junit.parser;

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.MissingArgException;
import com.awesome.excelpp.parser.exception.MissingLBracketException;
import com.awesome.excelpp.parser.exception.MissingRBracketException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.RecursionException;
import com.awesome.excelpp.parser.exception.ReferenceException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParserTest {	
	double expected;
	Object result;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void test_empty() throws ParserException, RecursionException {
		expected = 0.0;
		result = new Parser(";").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_number() throws ParserException, RecursionException {
		expected = 2.0;
		result = new Parser("=2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 450.257;
		result = new Parser("=450.257;").eval();
		assertEquals(expected, (Double)result, .001);		

		expected = 4.0;
		result = new Parser("=2.+2;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_plus() throws ParserException, RecursionException {
		expected = 4.0;
		result = new Parser("=2+2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 28.0;
		result = new Parser("=25.37+2.63;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_minus() throws ParserException, RecursionException {
		expected = 0.0;
		result = new Parser("=2-2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = -3.99;
		result = new Parser("=2.38-6.37;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_unaryminus() throws ParserException, RecursionException {
		expected = -4.0;
		result = new Parser("=-2---2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = -.57;
		result = new Parser("=2+-2.57;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 1.43;
		result = new Parser("=2+-.57;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_multiply() throws ParserException, RecursionException {
		expected = 50.0;
		result = new Parser("=25*2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 5.0;
		result = new Parser("=2*2.5;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_division() throws ParserException, RecursionException {
		expected = 12.5;
		result = new Parser("=25/2;").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 40.0;
		result = new Parser("=30/.75;").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_brackets() throws ParserException, RecursionException {
		expected = 4.0;
		result = new Parser("=(2+2);").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_function() throws ParserException, RecursionException {
		expected = 6.0;
		result = new Parser("=Sum(2,4);").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = 6.0;
		result = new Parser("=Sum(10,-4);").eval();
		assertEquals(expected, (Double)result, .001);
		
		expected = -1.0;
		result = new Parser("=Subtract(5,6);").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_nested_functions() throws ParserException, RecursionException {
		expected = 3.0;
		result = new Parser("=Sum(1,2,Sum(-3,4),Subtract(5,6));").eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_cell_1() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "=4*5;"), 0, 2);
		expected = 16.0;
		result = new Parser("=-2*2+C1;", testSheet).eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_cell_2() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "5;"), 0, 2);
		expected = 3.0;
		result = new Parser("=Subtract(C1,2);", testSheet).eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_cellrange1() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 0, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 1, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 2, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 3, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 4, 0);
		
		expected = 20.0;
		result = new Parser("=Sum(A1:A5);", testSheet).eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_cellrange2() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 0, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 1, 0);
		
		expected = 10.0;
		result = new Parser("=Sum(A1:A2, 2);", testSheet).eval();
		assertEquals(expected, (Double)result, .001);
	}
	
	@Test
	public void test_cellrange_invalid1() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 0, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 1, 0);
		
		exception.expect(ReferenceException.class);
		result = new Parser("=A1:A2;", testSheet).eval();
	}
	
	@Test
	public void test_cellrange_invalid2() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 0, 0);
		testSheet.setValueAt(new Cell(testSheet, "=4;"), 1, 0);
		
		exception.expect(ReferenceException.class);
		result = new Parser("=Sum(2, 2) + A1:A2;", testSheet).eval();
	}
	
	//@Test
	public void test_cellrange_invalid3() throws ParserException, RecursionException {
		SpreadSheet testSheet = new SpreadSheet();
		testSheet.setValueAt("=4", 0, 0);
		testSheet.setValueAt("=4", 1, 0);
		
		exception.expect(ReferenceException.class);
		result = new Parser("=Sum(2+A1:A2)", testSheet).eval();
	}
	
	@Test
	public void test_function_string1() throws ParserException, RecursionException {
		result = new Parser("=IsNumber(2);").eval();
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test_function_string2() throws ParserException, RecursionException {
		result = new Parser("=IsNumber(\"test\");").eval();
		assertFalse((Boolean)result);
	}
	
	@Test
	public void test_logic() throws ParserException, RecursionException {
		result = new Parser("=2<=3;", null).eval();
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test_logic_2() throws ParserException, RecursionException {
		result = new Parser("=2<3==2<4;", null).eval();
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test_logic_3() throws ParserException, RecursionException, RecursionException {
		result = new Parser("=1==1;", null).eval();
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test_logic_4() throws ParserException, RecursionException {
		result = new Parser("=2<=3;", null).eval();
		assertTrue((Boolean)result);
	}
	
	@Test
	public void test_invalid_function1() throws ParserException, RecursionException {
		exception.expect(MissingRBracketException.class);
		result = new Parser("=Sum(;").eval();
	}
	
	@Test
	public void test_invalid_function2() throws ParserException, RecursionException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Sum());").eval();
	}
	
	@Test
	public void test_invalid_function3() throws ParserException, RecursionException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Sum;").eval();
	}
	
	@Test
	public void test_invalid_function4() throws ParserException, RecursionException {
		exception.expect(MissingArgException.class);
		result = new Parser("=Sum();").eval();
	}
	
	@Test
	public void test_invalid_function5() throws ParserException, RecursionException {
		exception.expect(MissingLBracketException.class);
		result = new Parser("=Sum 5,5;").eval();
	}
	
	@Test
	public void test_invalid_function6() throws ParserException, RecursionException {
		exception.expect(MissingArgException.class);
		result = new Parser("=-;").eval();
	}
	
	@Test
	public void test_invalid_function7() throws ParserException, RecursionException {
		exception.expect(MissingArgException.class);
		result = new Parser("=2-;").eval();
	}
	
	//TODO: reference exception testen
}
