package com.awesome.excelpp.parser;

public class Token {
    public TokenType type;
    public String data;

    /**
     * Creates a new Token
     * @param type	the TokenType for this Token
     * @param data	the String stored in this Token
     */
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }
    
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
