package com.awesome.excelpp.parser;

/**
 * Enum representing the different states the Lexer can have.
 */
public enum State {
	/**
	 * Used when the Lexer's position is not inside a Token.
	 */
	NONE,
	/**
	 * Used when the Lexer's position is inside a NUMBER.
	 */
	NUMBER,
	/**
	 * Used when the Lexer's position is inside a CELL.
	 */
	CELL,
	/**
	 * Used when the Lexer's position is inside a WORD.
	 */
	WORD
}