package com.awesome.excelpp.scanner;

public enum TokenType {
	NUMBER("-?[0-9]+"), WORD("[a-zA-Z]+"), BINARYOP("[*/+-]"), BRACKET("[\\(\\)]"), WHITESPACE("\\s+");
	
    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}
