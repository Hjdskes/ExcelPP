package com.awesome.excelpp.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpreadSheetTest {
	String sheet;
	
	@Before
	public void setup() {
		sheet = "\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n" +
				"\t\t\t\t\t\t\t\t\t\t\n";
	}
	
	@Test
	public void test_Constructor() {
		SpreadSheet sheet = new SpreadSheet();
		assertTrue(sheet.toString().equals(this.sheet));
	}

}
