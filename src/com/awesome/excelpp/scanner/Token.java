package com.awesome.excelpp.scanner;

class Token {
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }
    
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
