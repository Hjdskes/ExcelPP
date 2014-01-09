package com.awesome.excelpp.junit.models;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.awesome.excelpp.models.SpreadSheet;

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
		assertNull(sheet.getValueAt(0, 0));
	}
	
	@Test
	public void test_setValueAt_2() {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt("", 0, 0);
		assertNull(sheet.getValueAt(0, 0));
		
		sheet.setValueAt("test", 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("test"));
	}
	
	@Test
	public void test_setValueAt_3() {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt("2", 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("2"));
		
		sheet.setValueAt("test", 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("test"));
	}
	
	@Test
	public void test_toXML() throws FileNotFoundException {
		SpreadSheet sheet = new SpreadSheet();
		
		sheet.setValueAt("2", 0, 0);
		assertTrue(sheet.getValueAt(0, 0).toString().equals("2"));
		
		sheet.setValueAt("test", 0, 1);
		assertTrue(sheet.getValueAt(0, 1).toString().equals("test"));
		
		sheet.setValueAt("=Add(2,2)", 0, 2);
		assertTrue(sheet.getValueAt(0, 2).toString().equals("4.0"));
		
		sheet.toXML(new File("/dev/null"));
	}
}
