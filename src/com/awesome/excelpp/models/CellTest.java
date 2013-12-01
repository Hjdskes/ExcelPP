package com.awesome.excelpp.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {
	@Test
	public void test_Constructor_String() {
		Cell cell = new Cell("stringCell");
		assertTrue(cell.getContent().equals("stringCell"));
		assertTrue(cell.getValue().equals("stringCell"));
	}
	
	@Test
	public void test_Constructor_Null() {
		Cell cell = new Cell(null);
		assertNull(cell.getContent());
		assertNull(cell.getValue());
	}
	
	@Test
	public void test_Constructor_Formule() {
		Cell cell = new Cell("=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		assertTrue(cell.getValue().equals("6"));
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		Cell cell = new Cell("=Add(32, 56)");
		assertTrue(cell.getContent().equals("=Add(32, 56)"));
		assertTrue(cell.getValue().equals("88"));
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		Cell cell = new Cell("= Add ( 12 , 46 ) ");
		assertTrue(cell.getContent().equals("= Add ( 12 , 46 ) "));
		assertTrue(cell.getValue().equals("58"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid1() {
		Cell cell = new Cell("=Add");
		assertTrue(cell.getContent().equals("=Add"));
		assertTrue(cell.getValue().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid2() {
		Cell cell = new Cell("=Add(");
		assertTrue(cell.getContent().equals("=Add("));
		assertTrue(cell.getValue().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid3() {
		Cell cell = new Cell("=Add()");
		assertTrue(cell.getContent().equals("=Add()"));
		assertTrue(cell.getValue().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid4() {
		Cell cell = new Cell("=Invalid(2,4)");
		assertTrue(cell.getContent().equals("=Invalid(2,4)"));
		assertTrue(cell.getValue().equals("#OPINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid5() {
		Cell cell = new Cell("=Add(INV,INV)");
		assertTrue(cell.getContent().equals("=Add(INV,INV)"));
		assertTrue(cell.getValue().equals("#NUMINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid6() {
		Cell cell = new Cell("=6(2,4)");
		assertTrue(cell.getContent().equals("=6(2,4)"));
		assertTrue(cell.getValue().equals("OPINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid7() {
		Cell cell = new Cell("=Add)2,4)");
		assertTrue(cell.getContent().equals("=Add)2,4)"));
		assertTrue(cell.getValue().equals("#ARGINV"));
	}
	
	@Test
	public void test_Constructor_FormuleInvalid8() {
		Cell cell = new Cell("=Add(2,4(");
		assertTrue(cell.getContent().equals("=Add(2,4("));
		assertTrue(cell.getValue().equals("#ARGINV"));
	}
	
	@Test
	public void test_SetContent() {
		Cell cell = new Cell("stringCell");
		cell.setContent("stringCellModified");
		assertTrue(cell.getContent().equals("stringCellModified"));
		assertTrue(cell.getValue().equals("stringCellModified"));
		cell.setContent("=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		assertTrue(cell.getValue().equals("6"));
	}
}
