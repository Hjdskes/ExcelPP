package com.awesome.excelpp.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	int index = 0;
	private ArrayList<Token> tokens;
	private String patterns;
	
	public Lexer(String input) {
		this.tokens = new ArrayList<Token>();
		
		for (TokenType tokenType : TokenType.values()) {
			patterns += String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern);
		}
		Pattern tokenPatterns = Pattern.compile(new String(patterns.substring(1)));

		if (input == null)
			return;
		
	    Matcher matcher = tokenPatterns.matcher(input);
	    while(matcher.find()) {
	    	if (matcher.group(TokenType.NUMBER.name()) != null)
	    		tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
	    	if (matcher.group(TokenType.WORD.name()) != null)
	    		tokens.add(new Token(TokenType.WORD, matcher.group(TokenType.WORD.name())));
	    	if (matcher.group(TokenType.PLUSMINUS.name()) != null)
	    		tokens.add(new Token(TokenType.PLUSMINUS, matcher.group(TokenType.PLUSMINUS.name())));
	    	if (matcher.group(TokenType.MULTDIV.name()) != null)
	    		tokens.add(new Token(TokenType.MULTDIV, matcher.group(TokenType.MULTDIV.name())));
	    	if (matcher.group(TokenType.LBRACKET.name()) != null)
	    		tokens.add(new Token(TokenType.LBRACKET, matcher.group(TokenType.LBRACKET.name())));
	    	if (matcher.group(TokenType.RBRACKET.name()) != null)
	    		tokens.add(new Token(TokenType.RBRACKET, matcher.group(TokenType.RBRACKET.name())));
	    	if (matcher.group(TokenType.DELIM.name()) != null)
	    		tokens.add(new Token(TokenType.DELIM, matcher.group(TokenType.DELIM.name())));
	    }
	}
	
	public boolean hasNext() {
		return (tokens.size() - index) > 0;
	}
	
	public boolean hasNextWord() {
		return hasNext() && tokens.get(index).type == TokenType.WORD;
	}
	
	public Token next() {
		return hasNext() ? tokens.get(index++) : new Token(TokenType.EOL, null);
	}
}

enum TokenType {
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
