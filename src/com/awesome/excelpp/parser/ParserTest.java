package com.awesome.excelpp.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {
	Parser parse;
	
	@Test
	public void test_Constructor_Null() throws Exception {
		parse = new Parser("-2-2");
		//assertTrue(parse.getValue().equals("4"));
		
		//parse = new Parser("2++2");
		//assertTrue(parse.getValue().equals("#INVALID"));
		
		//parse = new Parser("+2+2");
		//assertTrue(parse.getValue().equals("#INVALID"));
	}
/*	
	@Test
	public void test_Constructor_Formule() {
		parse = new Parser("Add(2,4)");
		assertTrue(parse.getValue().equals("6"));
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		parse = new Parser("Add(2, 4)");
		assertTrue(parse.getValue().equals("6"));
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		parse = new Parser(" Add ( 2 , 4 ) ");
		assertTrue(parse.getValue().equals("6"));
	}
	
	@Test
	public void test_Constructor_FormuleSpacesNumbers() {
		parse = new Parser(" Add ( 24571 , 17843 ) ");
		assertTrue(parse.getValue().equals("42414"));
	}
	
	@Test
	public void test_Constructor_FormuleNumbers() {
		parse = new Parser(" Add(24571,17843)");
		assertTrue(parse.getValue().equals("42414"));
	}
	
	@Test
	public void test_Constructor_FormuleNumbersInvalid() {
		parse = new Parser(" Add24571,17843");
		assertTrue(parse.getValue().equals("#OPINV"));
		System.out.println(parse.getValue());
	}
	
	@Test
	public void test_Constructor_FormuleBinaryOP() {
		parse = new Parser(" -24571+17843-Add(2,4)");
		assertTrue(parse.getValue().equals("-6734"));
	}*/
}
