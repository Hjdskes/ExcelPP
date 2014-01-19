package com.awesome.excelpp.junit.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.excelpp.parser.Lexer;
import com.awesome.excelpp.parser.Token;
import com.awesome.excelpp.parser.TokenType;

public class LexerTest {
	Lexer lex;
	
	@Test
	public void test_Constructor_Null() {
		lex = new Lexer(null);
		assertEquals(TokenType.EOL, lex.next().type);
	}
	
	@Test
	public void test_Constructor_Plus() {
		lex = new Lexer("=2+2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.PLUSMINUS, "+"));
	}
	
	@Test
	public void test_Constructor_Minus() {
		lex = new Lexer("=2-2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.PLUSMINUS, "-"));
	}
	
	@Test
	public void test_Constructor_Mult() {
		lex = new Lexer("=2*2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.MULTDIV, "*"));
	}
	
	@Test
	public void test_Constructor_Div() {
		lex = new Lexer("=2/2");
		test_ExprBinaryTwoArgs("2", "2", new Token(TokenType.MULTDIV, "/"));
	}
	
	@Test
	public void test_Constructor_Double() {
		lex = new Lexer("=2.+2");
		test_ExprBinaryTwoArgs("2.", "2", new Token(TokenType.PLUSMINUS, "+"));
	}
	
	@Test
	public void test_Constructor_Formule() {
		lex = new Lexer("=Add(2,4)");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpace() {
		lex = new Lexer("=Add(2, 4)");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpaces() {
		lex = new Lexer("= Add ( 2 , 4 ) ");
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_FormuleSpacesNumbers() {
		lex = new Lexer("= Add ( 24571 , 17843 ) ");
		test_ExprTwoArgs("Add", "24571", "17843");
	}
	
	@Test
	public void test_Constructor_FormuleNumbers() {
		lex = new Lexer("= Add(24571,17843)");
		test_ExprTwoArgs("Add", "24571", "17843");
	}
	
	@Test
	public void test_Constructor_FormuleBinaryOP() {
		lex = new Lexer("=24571+17843-Add(2,4)");
		test_ExprBinaryTwoArgs("24571", "17843", new Token(TokenType.PLUSMINUS, "+"));
		
		Token next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("-"));
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_Cell_1() {
		lex = new Lexer("=B1+B1");
		
		Token next;
		next = lex.next();
		assertEquals(TokenType.CELL, next.type);
		assertTrue(next.data.equals("B1"));
		next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("+"));
		next = lex.next();
		assertEquals(TokenType.CELL, next.type);
		assertTrue(next.data.equals("B1"));
	}
	
	@Test
	public void test_Constructor_Cell_2() {
		lex = new Lexer("=B1:BB78+B1:BB78");
		
		Token next;
		next = lex.next();
		assertEquals(TokenType.CELLRANGE, next.type);
		assertTrue(next.data.equals("B1:BB78"));
		next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("+"));
		next = lex.next();
		assertEquals(TokenType.CELLRANGE, next.type);
		assertTrue(next.data.equals("B1:BB78"));
	}
	
	@Test
	public void test_Constructor_String_1() {
		lex = new Lexer("\"test\"");
		
		Token next;
		next = lex.next();
		assertEquals(TokenType.STRING, next.type);
		assertTrue(next.data.equals("\"test\""));
	}
	
	@Test
	public void test_Constructor_String_2() {
		lex = new Lexer("=\"test\" + 2 + Add(2, 4)");
		
		Token next;
		next = lex.next();
		assertEquals(TokenType.STRING, next.type);
		assertTrue(next.data.equals("\"test\""));
		
		next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("+"));
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals("2"));
		
		next = lex.next();
		assertEquals(TokenType.PLUSMINUS, next.type);
		assertTrue(next.data.equals("+"));
		
		test_ExprTwoArgs("Add", "2", "4");
	}
	
	@Test
	public void test_Constructor_String_3() {
		lex = new Lexer("=Add(\"test\", 4)");
		
		Token next;
		
		next = lex.next();
		assertEquals(TokenType.WORD, next.type);
		assertTrue(next.data.equals("Add"));
		
		next = lex.next();
		assertEquals(TokenType.LBRACKET, next.type);
		assertTrue(next.data.equals("("));
		
		next = lex.next();
		assertEquals(TokenType.STRING, next.type);
		assertTrue(next.data.equals("\"test\""));
		
		next = lex.next();
		assertEquals(TokenType.DELIM, next.type);
		assertTrue(next.data.equals(","));
		
		next = lex.next();
		assertEquals(TokenType.NUMBER, next.type);
		assertTrue(next.data.equals("4"));
		
		next = lex.next();
		assertEquals(TokenType.RBRACKET, next.type);
		assertTrue(next.data.equals(")"));
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
