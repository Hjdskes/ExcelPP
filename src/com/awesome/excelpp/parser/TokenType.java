package com.awesome.excelpp.parser;

public enum TokenType {
	NUMBER("[0-9]+"),			// (-)0-9
	WORD("[a-zA-Z]+"),			// a-z, A-Z
	PLUSMINUS("[+-]"),			// +, -
	MULTDIV("[*/]"),			// *, /
	LBRACKET("\\("),			// (
	RBRACKET("\\)"),			// )
	DELIM("[;,]"),				// ;, ,
	LOGIC("[<>=]"),				// <, >, =
	WHITESPACE("\\s+"),			// all whitespace
	EOL("");
	
    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}