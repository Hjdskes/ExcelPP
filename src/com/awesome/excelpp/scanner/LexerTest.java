package com.awesome.excelpp.scanner;

import static org.junit.Assert.*;

import org.junit.Test;

public class LexerTest {
	Lexer lex;
	
	@Test
	public void test_Constructor_Null() {
		lex = new Lexer(null);
		assertFalse(lex.hasNextWord());
		assertTrue(lex.next().type == TokenType.EOL);
	}
	
	@Test
	public void test_Constructor_Formule() {
		lex = new Lexer("Add(2,4)");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		lex = new Lexer("Add(2, 4)");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		lex = new Lexer(" Add ( 2 , 4 ) ");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpacesNumbers() {
		lex = new Lexer(" Add ( 24571 , 17843 ) ");
		test_ExprTwoArgs("Add", "24571", "17843");
	}
	
	@Test
	public void test_Constructor_FormuleNumbers() {
		lex = new Lexer(" Add(24571,17843)");
		test_ExprTwoArgs("Add", "24571", "17843");
	}
	
	@Test
	public void test_Constructor_FormuleNumbersInvalid() {
		lex = new Lexer(" Add24571,17843");
		test_ExprInvTwoArgs("Add", "24571", "17843");
	}
	
	@Test
	public void test_Constructor_FormuleBinaryOP() {
		lex = new Lexer("24571+17843-Add(2,4)");
		test_ExprBinaryTwoArgs("24571", "17843");
		
		Token next = lex.next();
		assertTrue(next.type == TokenType.PLUSMINUS);
		assertTrue(next.data.equals("-"));
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	public void test_ExprTwoArgs(String expr, String arg1, String arg2) {
		Token next;
		
		next = lex.next();
		assertTrue(next.type == TokenType.WORD);
		assertTrue(next.data.equals(expr));
		
		next = lex.next();
		assertTrue(next.type == TokenType.LBRACKET);
		assertTrue(next.data.equals("("));
		
		next = lex.next();
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg1));
		
		next = lex.next();
		assertTrue(next.type == TokenType.COMMA);
		assertTrue(next.data.equals(","));
		
		next = lex.next();
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg2));
		
		next = lex.next();
		assertTrue(next.type == TokenType.RBRACKET);
		assertTrue(next.data.equals(")"));
		
		next = lex.next();
		assertTrue(next.type == TokenType.EOL);
	}
	
	public void test_ExprInvTwoArgs(String expr, String arg1, String arg2) {
		Token next;
		
		next = lex.next();
		assertTrue(next.type == TokenType.WORD);
		assertTrue(next.data.equals(expr));
		
		next = lex.next();
		assertFalse(lex.hasNextWord());
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg1));
		
		next = lex.next();
		assertFalse(lex.hasNextWord());
		assertTrue(next.type == TokenType.COMMA);
		assertTrue(next.data.equals(","));
		
		next = lex.next();
		assertFalse(lex.hasNextWord());
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg2));
		
		next = lex.next();
		assertTrue(next.type == TokenType.EOL);
	}
	
	public void test_ExprBinaryTwoArgs(String arg1, String arg2) {
		Token next;
		
		next = lex.next();
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg1));
		
		next = lex.next();
		assertTrue(next.type == TokenType.PLUSMINUS);
		assertTrue(next.data.equals("+"));
		
		next = lex.next();
		assertTrue(next.type == TokenType.NUMBER);
		assertTrue(next.data.equals(arg2));
	}
}
