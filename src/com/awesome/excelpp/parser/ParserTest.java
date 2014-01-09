package com.awesome.excelpp.parser;

import com.awesome.excelpp.parser.exception.ParserException;

public class ParserTest {
	public static void main(String[] args) throws ParserException {
		double totalFirst = 0, totalSecond = 0, totalLexer = 0;
		double startTime, endTime;
		
		startTime = System.currentTimeMillis();
		Lexer lex = new Lexer("=2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2*(2*2+4*5*6+2*(2*2+4*5*6+2))+(((2*2+4*5*6+2)*2*2+4*5*6+2)*2*2+4*5*6+2)");
		endTime = System.currentTimeMillis();
		totalLexer = endTime - startTime;

		Parser firstTest = new Parser(lex, null);
		
		startTime = System.currentTimeMillis();
		firstTest.toPostfix();
		endTime = System.currentTimeMillis();
		totalFirst = endTime - startTime;
		
		startTime = System.currentTimeMillis();
		firstTest.eval();
		endTime = System.currentTimeMillis();
		totalSecond = endTime - startTime;
		
		System.out.println("Lexer first run: " + totalLexer + " ms");
		System.out.println("toPostfix() first run: " + totalFirst + " ms");
		System.out.println("eval() first run: " + totalSecond + " ms");
	}
}
