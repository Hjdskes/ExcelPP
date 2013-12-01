package com.awesome.excelpp.models;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {
	@Test
	public void testConstructor_String() {
		Cell cell = new Cell("stringCell");
		assertTrue(cell.getContent().equals("stringCell"));
		assertTrue(cell.getValue().equals("stringCell"));
	}
	
	@Test
	public void testConstructor_Null() {
		Cell cell = new Cell(null);
		assertNull(cell.getContent());
		assertNull(cell.getValue());
	}
	
	@Test
	public void testConstructor_Formule() {
		Cell cell = new Cell("=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		assertTrue(cell.getValue().equals("6"));
	}
	
	@Test
	public void testConstructor_FormuleSpace() {
		Cell cell = new Cell("=Add(32, 56)");
		assertTrue(cell.getContent().equals("=Add(32, 56)"));
		assertTrue(cell.getValue().equals("88"));
	}
	
	@Test
	public void testConstructor_FormuleSpaces() {
		Cell cell = new Cell("= Add ( 12 , 46 ) ");
		assertTrue(cell.getContent().equals("= Add ( 12 , 46 ) "));
		assertTrue(cell.getValue().equals("88"));
	}
	
	@Test
	public void testConstructor_FormuleInvalid1() {
		Cell cell = new Cell("=Add");
		assertTrue(cell.getContent().equals("=Add"));
		assertTrue(cell.getValue().equals("#OPINV"));
	}
	
	@Test
	public void testConstructor_FormuleInvalid2() {
		Cell cell = new Cell("=Add(");
		assertTrue(cell.getContent().equals("=Add("));
		assertTrue(cell.getValue().equals("#OPINV"));
	}
	
	@Test
	public void testConstructor_FormuleInvalid3() {
		Cell cell = new Cell("=Add()");
		assertTrue(cell.getContent().equals("=Add()"));
		assertTrue(cell.getValue().equals("#OPINV"));
	}
	
	@Test
	public void testConstructor_FormuleInvalid4() {
		Cell cell = new Cell("=Invalid(2,4)");
		assertTrue(cell.getContent().equals("=Invalid(2,4)"));
		assertTrue(cell.getValue().equals("#OPINV"));
	}
	
	@Test
	public void testSetContent() {
		Cell cell = new Cell("stringCell");
		cell.setContent("stringCellModified");
		assertTrue(cell.getContent().equals("stringCellModified"));
		assertTrue(cell.getValue().equals("stringCellModified"));
		cell.setContent("=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		assertTrue(cell.getValue().equals("6"));
	}
}
