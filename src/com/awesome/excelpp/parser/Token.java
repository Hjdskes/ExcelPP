package com.awesome.excelpp.parser;

class Token {
    public TokenType type;
    public String data;

    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }
    
    public String getTokenType(){
    	return this.type.name();
    }
    
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
