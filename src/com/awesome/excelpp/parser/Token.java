package com.awesome.excelpp.parser;

public class Token {
    public TokenType type;
    public String data;

    /**
     * Creates a new Token
     * @param type	the {@link TokenType} for this {@link Token}
     * @param data	the String stored in this {@link Token}
     */
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }
    
    @Override
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
