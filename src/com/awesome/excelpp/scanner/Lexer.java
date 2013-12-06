package com.awesome.excelpp.scanner;

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
	    	if (matcher.group(TokenType.BINARYOP.name()) != null)
	    		tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name())));
	    	if (matcher.group(TokenType.BRACKET.name()) != null)
	    		tokens.add(new Token(TokenType.BRACKET, matcher.group(TokenType.BRACKET.name())));
	    	if (matcher.group(TokenType.COMMA.name()) != null)
	    		tokens.add(new Token(TokenType.COMMA, matcher.group(TokenType.COMMA.name())));
	    }
	}
	
	public boolean hasNext() {
		return (tokens.size() - index) > 0;
	}
	
	public boolean hasNextWord() {
		return hasNext() && tokens.get(index).type == TokenType.WORD;
	}
	
	public Token next() {
		return hasNext() ? tokens.get(index++) : null;
	}
}

enum TokenType {
	NUMBER("-?[0-9]+"),			// (-)0-9
	WORD("[a-zA-Z]+"),			// a-z, A-Z
	BINARYOP("[*/+-]"),			// *, /, +, -
	BRACKET("[\\(\\)]"),		// (, )
	COMMA(","),					// ,
	WHITESPACE("\\s+");			// all whitespace
	
    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}
