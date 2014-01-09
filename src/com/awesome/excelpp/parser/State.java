package com.awesome.excelpp.parser;

/**
 * Enum representing the different states the {@link Lexer} can have.
 */
public enum State {
	/**
	 * Used when the Lexer's position is not inside a {@link Token}.
	 */
	NONE,
	/**
	 * Used when the Lexer's position is inside a NUMBER {@link Token}.
	 */
	NUMBER,
	/**
	 * Used when the Lexer's position is inside a CELL {@link Token}.
	 */
	CELL,
	/**
	 * Used when the Lexer's position is inside a WORD {@link Token}.
	 */
	WORD
}