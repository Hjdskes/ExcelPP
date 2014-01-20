package com.awesome.excelpp.junit.models;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.writers.SysOutWriter;

public class SpreadSheetTest {
	@Test
	public void test_constructor() {
		SpreadSheet sheet = new SpreadSheet();
		assertTrue(sheet.isEmpty());
	}
	
	@Test
	public void test_setValueAt_1() {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt(null, 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals(""));
		assertTrue(sheet.isEmpty());
	}
	
	@Test
	public void test_setValueAt_2() {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt(new Cell(sheet, ""), 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals(""));
		assertTrue(sheet.isEmpty());
		
		sheet.setValueAt(new Cell(sheet, "test"), 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("test"));
		assertFalse(sheet.isEmpty());
	}
	
	@Test
	public void test_setValueAt_3() {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt(new Cell(sheet, "2"), 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("2"));
		assertFalse(sheet.isEmpty());
		
		sheet.setValueAt(new Cell(sheet, "test"), 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("test"));
		assertFalse(sheet.isEmpty());
	}
	
	@Test
	public void test_toXML() throws FileNotFoundException {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt(new Cell(sheet, null), 0, 0);
		sheet.setValueAt(new Cell(sheet, "2"), 0, 1);
		sheet.setValueAt(new Cell(sheet, "test"), 0, 2);
		sheet.setValueAt(new Cell(sheet, "=Sum(2,2)"), 0, 3);
		assertTrue(sheet.getValueAt(0, 3).toString().equals("4.0"));
		
		String expected = "row=1, col=2, content=2\n" +
							"row=1, col=3, content=test\n" +
							"row=1, col=4, content==Sum(2,2)\n";
		
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		sheet.write(new SysOutWriter());
		assertTrue(expected.equals(out.toString()));
	}
}
