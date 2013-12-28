package com.awesome.excelpp.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	private int index = 0;
	public ArrayList<Token> tokens; //public for testing
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
	    	if (matcher.group("NUMBER") != null)
	    		tokens.add(new Token(TokenType.NUMBER, matcher.group("NUMBER")));
	    	if (matcher.group("CELL") != null)
	    		tokens.add(new Token(TokenType.CELL, matcher.group("CELL")));
	    	if (matcher.group("WORD") != null)
	    		tokens.add(new Token(TokenType.WORD, matcher.group("WORD")));
	    	if (matcher.group("PLUSMINUS") != null)
	    		tokens.add(new Token(TokenType.PLUSMINUS, matcher.group("PLUSMINUS")));
	    	if (matcher.group("MULTDIV") != null)
	    		tokens.add(new Token(TokenType.MULTDIV, matcher.group("MULTDIV")));
	    	if (matcher.group("LBRACKET") != null)
	    		tokens.add(new Token(TokenType.LBRACKET, matcher.group("LBRACKET")));
	    	if (matcher.group("RBRACKET") != null)
	    		tokens.add(new Token(TokenType.RBRACKET, matcher.group("RBRACKET")));
	    	if (matcher.group("DELIM") != null)
	    		tokens.add(new Token(TokenType.DELIM, matcher.group("DELIM")));
	    }
	    
	    tokens.add(new Token(TokenType.EOL, "end"));
	}
	
	public boolean hasNext() {
		return (tokens.size() - index) > 0;
	}
	
	public Token next() {
		return hasNext() ? tokens.get(index++) : new Token(TokenType.EOL, null);
	}
}
