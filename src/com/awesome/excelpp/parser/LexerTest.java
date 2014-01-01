package com.awesome.excelpp.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class LexerTest {
	Lexer lex;
	
	@Test
	public void test_Constructor_Null() {
		lex = new Lexer(null);
		assertEquals(TokenType.EOL, lex.next().type);
	}
	
	@Test
	public void test_Constructor_Plus() {
		lex = new Lexer("2+2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.PLUSMINUS, "+"));
	}
	
	@Test
	public void test_Constructor_Minus() {
		lex = new Lexer("2-2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.PLUSMINUS, "-"));
	}
	
	@Test
	public void test_Constructor_Mult() {
		lex = new Lexer("2*2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.MULTDIV, "*"));
	}
	
	@Test
	public void test_Constructor_Div() {
		lex = new Lexer("2/2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.MULTDIV, "/"));
	}
	
	@Test
	public void test_Constructor_Double() {
		lex = new Lexer("2.+2");
		test_ExprBinaryTwoArgs("2.", "2", new Token(TokenType.PLUSMINUS, "+"));
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
	public void test_Constructor_FormuleBinaryOP() {
		lex = new Lexer("24571+17843-Add(2,4)");
		test_ExprBinaryTwoArgs("24571", "17843", new Token(TokenType.PLUSMINUS, "+"));
		
		Token next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("-"));
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	public void test_ExprTwoArgs(String expr, String arg1, String arg2) {
		Token next;
		
		next = lex.next();
		assertEquals(TokenType.WORD, next.type);
		assertTrue(next.data.equals(expr));
		
		next = lex.next();
		assertEquals(TokenType.LBRACKET, next.type);
		assertTrue(next.data.equals("("));
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals(arg1));
		
		next = lex.next();
		assertEquals(TokenType.DELIM, next.type);
		assertTrue(next.data.equals(","));
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals(arg2));
		
		next = lex.next();
		assertEquals(TokenType.RBRACKET, next.type);
		assertTrue(next.data.equals(")"));
		
		next = lex.next();
		assertEquals(TokenType.EOL, next.type);
	}
	
	public void test_ExprBinaryTwoArgs(String arg1, String arg2, Token token) {
		Token next;
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals(arg1));
		
		next = lex.next();
		assertTrue(next.type.equals(token.type));
		assertTrue(next.data.equals(token.data));
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals(arg2));
	}
}
