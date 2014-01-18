package com.awesome.excelpp.junit.models;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import org.junit.Test;

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

public class CellTest {
	double expected, result;
	
	@Test
	public void test_Constructor_String() {
		Cell cell = new Cell(null, "stringCell");
		assertTrue(cell.getContent().equals("stringCell"));
		assertTrue(cell.toString().equals("stringCell"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_Null() {
		Cell cell = new Cell(null, null);
		assertEquals("", cell.getContent());
		assertEquals("", cell.toString());
		assertTrue(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_Formule() {
		Cell cell = new Cell(null, "=Add(2,4)");
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		
		expected = 6.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		Cell cell = new Cell(null, "=Add(32, 56)");
		assertTrue(cell.getContent().equals("=Add(32, 56)"));
		
		expected = 88.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		Cell cell = new Cell(null, "= Add ( 12 , 46 ) ");
		assertTrue(cell.getContent().equals("= Add ( 12 , 46 ) "));
		
		expected = 58.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid1() {
		Cell cell = new Cell(null, "=Add");
		assertTrue(cell.getContent().equals("=Add"));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid2() {
		Cell cell = new Cell(null, "=Add(");
		assertTrue(cell.getContent().equals("=Add("));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid3() {
		Cell cell = new Cell(null, "=Add()");
		assertTrue(cell.getContent().equals("=Add()"));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid4() {
		Cell cell = new Cell(null, "=Invalid(2,4)");
		assertTrue(cell.getContent().equals("=Invalid(2,4)"));
		assertTrue(cell.toString().equals("#OPINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid5() {
		Cell cell = new Cell(null, "=Add(INV,INV)");
		assertTrue(cell.getContent().equals("=Add(INV,INV)"));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid6() {
		Cell cell = new Cell(null, "=6(2,4)");
		assertTrue(cell.getContent().equals("=6(2,4)"));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid7() {
		Cell cell = new Cell(null, "=Add)2,4)");
		assertTrue(cell.getContent().equals("=Add)2,4)"));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid8() {
		Cell cell = new Cell(null, "=Add(2,4(");
		assertTrue(cell.getContent().equals("=Add(2,4("));
		assertTrue(cell.toString().equals("#ARGINV"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid9() {
		Cell cell = new Cell(new SpreadSheet(), "=A1");
		assertTrue(cell.getContent().equals("=A1"));
		assertTrue(cell.toString().equals("0.0"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid10() {
		Cell cell = new Cell(new SpreadSheet(), "=A1+A1");
		assertTrue(cell.getContent().equals("=A1+A1"));
		assertTrue(cell.toString().equals("0.0"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_Constructor_FormuleInvalid11() {
		Cell cell = new Cell(new SpreadSheet(), "=Add(A1:A4)");
		assertTrue(cell.getContent().equals("=Add(A1:A4)"));
		assertTrue(cell.toString().equals("0.0"));
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_SetContent() {
		Cell cell = new Cell(new SpreadSheet(), "stringCell");
		
		cell.setContent("stringCellModified", true);
		assertTrue(cell.getContent().equals("stringCellModified"));
		assertTrue(cell.toString().equals("stringCellModified"));
		
		cell.setContent("=Add(2,4)", true);
		assertTrue(cell.getContent().equals("=Add(2,4)"));
		
		expected = 6.0;
		result = Double.parseDouble(cell.toString());
		assertEquals(expected, result, .001);
		assertFalse(cell.isEmpty());
	}
	
	@Test
	public void test_set_bold() {
		Cell cell = new Cell(new SpreadSheet(), null);
		
		cell.setBold(2, true);
		assertNotEquals(Font.BOLD, cell.getBold());
		assertTrue(cell.isEmpty());
		
		cell.setBold(1, true);
		assertEquals(Font.BOLD, cell.getBold());
		assertFalse(cell.isEmpty());
		
		cell.setBold(0, true);
		assertNotEquals(Font.BOLD, cell.getBold());
		assertTrue(cell.isEmpty());
	}
	
	@Test
	public void test_set_italic() {
		Cell cell = new Cell(new SpreadSheet(), null);
		
		cell.setItalic(1, true);
		assertNotEquals(Font.ITALIC, cell.getItalic());
		assertTrue(cell.isEmpty());
		
		cell.setItalic(2, true);
		assertEquals(Font.ITALIC, cell.getItalic());
		assertFalse(cell.isEmpty());
		
		cell.setItalic(0, true);
		assertNotEquals(Font.ITALIC, cell.getItalic());
		assertTrue(cell.isEmpty());
	}
	
	@Test
	public void test_set_foregroundcolor() {
		Cell cell = new Cell(new SpreadSheet(), null);
		
		assertEquals(Color.BLACK, cell.getForegroundColor());
		assertTrue(cell.isEmpty());
		
		cell.setForegroundColor(Color.RED, true);
		assertEquals(Color.RED, cell.getForegroundColor());
		assertFalse(cell.isEmpty());
		
		cell.setForegroundColor(Color.BLACK, true);
		assertEquals(Color.BLACK, cell.getForegroundColor());
		assertTrue(cell.isEmpty());
	}
	
	@Test
	public void test_set_backgroundcolor() {
		Cell cell = new Cell(new SpreadSheet(), null);
		cell.setBackgroundColor(Color.BLUE, true);
	}
	
	@Test
	public void test_equals() {
		Cell cell1 = new Cell(new SpreadSheet(), null);
		Cell cell2 = new Cell(new SpreadSheet(), null);
		Cell cell3 = new Cell(new SpreadSheet(), null);
		Cell cell4 = new Cell(new SpreadSheet(), null);
		Cell cell5 = new Cell(new SpreadSheet(), null);
		Cell cell6 = new Cell(new SpreadSheet(), "2");
		
		cell2.setBold(1, true);
		cell3.setItalic(2, true);
		cell4.setForegroundColor(Color.RED, true);
		cell5.setBackgroundColor(Color.BLUE, true);
		
		assertFalse(cell1.equals(null));
		assertTrue(cell1.equals(cell1));
		assertFalse(cell1.equals(cell2));
		assertFalse(cell1.equals(cell3));
		assertFalse(cell1.equals(cell4));
		assertFalse(cell1.equals(cell5));
		assertFalse(cell1.equals(cell6));
	}
	
	@Test
	public void test_undoable() {
		Cell cell = new Cell(new SpreadSheet(), null);
		
		cell.setContent("test", false);
		assertTrue(cell.getContent().equals("test"));
		assertFalse(cell.isEmpty());
		
		cell.setBold(1, false);
		assertEquals(Font.BOLD, cell.getBold());
		assertFalse(cell.isEmpty());
		
		cell.setItalic(2, false);
		assertEquals(Font.ITALIC, cell.getItalic());
		assertFalse(cell.isEmpty());
		
		cell.setForegroundColor(Color.RED, false);
		assertEquals(Color.RED, cell.getForegroundColor());
		assertFalse(cell.isEmpty());
		
		cell.setBackgroundColor(Color.BLUE, false);
		assertEquals(Color.BLUE, cell.getBackgroundColor());
		assertFalse(cell.isEmpty());
	}
}
