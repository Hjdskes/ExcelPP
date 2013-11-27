package com.awesome.excelpp;

import org.junit.Test;
import static org.junit.Assert.*;


public class SpreadSheetTest {

	@Test
	public void testMain() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testLoadXML() {
		assertTrue("test" == "test");
	}
	
	@Test(expected = Exception.class)
	public void testLoadXMLError() {
		SpreadSheet.loadXML("hallo.xml");
	}

	@Test
	public void testLoadXMLSAX() {
		fail("Not yet implemented"); // TODO
	}

}
