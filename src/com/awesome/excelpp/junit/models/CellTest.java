package com.awesome.excelpp.junit.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.models.Cell;

public class CellTest {
	double expected, result;
	
	@Test
	public void test_Constructor_String() {
		Cell cell = new Cell(null, "stringCell");
		assertTrue(cell.getContent().equals("stringCell"));
		assertTrue(cell.toString().equals("stringCell"));
	}
	
	@Test
	public void test_Constructor_Null() {
		Cell cell = new Cell(null, null);
		assertNull(cell.getContent());
		assertNull(cell.toString());
	}
	
	@Test
	public void test_Constructor_Formule() {
		Cell cell = new Cell(null, "=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		
		expected = 6.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		Cell cell = new Cell(null, "=Add(32, 56)");
		assertTrue(cell.getContent().equals("=Add(32, 56)"));
		
		expected = 88.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		Cell cell = new Cell(null, "= Add ( 12 , 46 ) ");
		assertTrue(cell.getContent().equals("= Add ( 12 , 46 ) "));
		
		expected = 58.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
	}
	
	@Test
	public void test_Constructor_FormuleInvalid1() {
		Cell cell = new Cell(null, "=Add");
		assertTrue(cell.getContent().equals("=Add"));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid2() {
		Cell cell = new Cell(null, "=Add(");
		assertTrue(cell.getContent().equals("=Add("));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid3() {
		Cell cell = new Cell(null, "=Add()");
		assertTrue(cell.getContent().equals("=Add()"));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid4() {
		Cell cell = new Cell(null, "=Invalid(2,4)");
		assertTrue(cell.getContent().equals("=Invalid(2,4)"));
		assertTrue(cell.toString().equals("#OPINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid5() {
		Cell cell = new Cell(null, "=Add(INV,INV)");
		assertTrue(cell.getContent().equals("=Add(INV,INV)"));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid6() {
		Cell cell = new Cell(null, "=6(2,4)");
		assertTrue(cell.getContent().equals("=6(2,4)"));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid7() {
		Cell cell = new Cell(null, "=Add)2,4)");
		assertTrue(cell.getContent().equals("=Add)2,4)"));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid8() {
		Cell cell = new Cell(null, "=Add(2,4(");
		assertTrue(cell.getContent().equals("=Add(2,4("));
		assertTrue(cell.toString().equals("#ARGINV"));
	}
	
	@Test
	public void test_SetContent() {
		Cell cell = new Cell(null, "stringCell");
		
		cell.setContent("stringCellModified", true);
		assertTrue(cell.getContent().equals("stringCellModified"));
		assertTrue(cell.toString().equals("stringCellModified"));
		
		cell.setContent("=Add(2,4)", true);
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		
		expected = 6.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
	}
}
