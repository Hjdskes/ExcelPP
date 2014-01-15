package com.awesome.excelpp.parser;

/**
 * Enum representing the different TokenTypes the {@link Lexer} can produce.<br>
 * The {@link Parser} will have to understand all the TokenTypes.
 */
public enum TokenType {
	/**
	 * Token representing a NUMBER: <code>(0-9)(.(0-9))</code>
	 */
	NUMBER,
	/**
	 * Token representing a CELL: <code>(A-Z)+(0-9)+</code>
	 */
	CELL,
	/**
	 * Token representing a WORD: <code>a-zA-Z</code>
	 */
	WORD,
	/**
	 * Token representing a PLUSMINUS: <code>+-</code>
	 */
	PLUSMINUS,
	/**
	 * Token representing a MULTDIV: <code>{@literal *}/</code>
	 */
	MULTDIV,
	/**
	 * Token representing an LBRACKET: <code>(</code>
	 */
	LBRACKET,
	/**
	 * Token representing an RBRACKET: <code>)</code>
	 */
	RBRACKET,
	/**
	 * Token representing a DELIM: <code>,</code>
	 */
	DELIM,
	/**
	 * Token representing a CELLDELIM: <code>:</code>
	 */
	CELLDELIM,
	/**
	 * Token representing LOGIC: <code><>=</code>
	 */
	LOGIC,
	/**
	 * Token representing WHITESPACE: <code>\ \t\n\r\f</code>
	 */
	WHITESPACE,
	/**
	 * Pseudo Token representing UNARYMINUS
	 */
	UNARYMINUS,
	/**
	 * Pseudo Token representing EOL
	 */
	EOL;
}
