package com.awesome.excelpp.parser;

/**
 * Enum representing the different TokenTypes the {@link Lexer} can produce.
 * <p>The {@link Parser} will have to understand all the TokenTypes.</p>
 * @author Team Awesome
 */
public enum TokenType {
	/**
	 * Token representing a NUMBER: <code>(0-9)(.(0-9))</code>.
	 */
	NUMBER,
	/**
	 * Token representing a CELL: <code>(A-Z)+(0-9)+</code>.
	 */
	CELL,
	/**
	 * Token representing a CELLRANGE: <code>(A-Z)+(0-9)+:(A-Z)+(0-9)+</code>.
	 */
	CELLRANGE,
	/**
	 * Token representing a WORD: <code>a-zA-Z</code>.
	 */
	WORD,
	/**
	 * Token representing a STRING: <code>"string"</code>.
	 */
	STRING,
	/**
	 * Token representing a PLUSMINUS: <code>+-</code>.
	 */
	PLUSMINUS,
	/**
	 * Token representing a MULTDIV: <code>{@literal *}/</code>.
	 */
	MULTDIV,
	/**
	 * Token representing an LBRACKET: <code>(</code>.
	 */
	LBRACKET,
	/**
	 * Token representing an RBRACKET: <code>)</code>.
	 */
	RBRACKET,
	/**
	 * Token representing a DELIM: <code>,</code>.
	 */
	DELIM,
	/**
	 * Token representing LOGIC: <code><>=</code>.
	 */
	LOGIC,
	/**
	 * Pseudo Token representing UNARYMINUS.
	 */
	UNARYMINUS,
	/**
	 * Pseudo Token representing EOL.
	 */
	EOL;
}
