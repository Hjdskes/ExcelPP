package com.awesome.excelpp.scanner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellScanner {
	int index = 0;
	private ArrayList<Token> tokens;
	
	public CellScanner(String input) {
		this.tokens = new ArrayList<Token>();
		lexer(input);
	}
	
	public boolean hasNext() {
		return (tokens.size() - index) > 0;
	}
	
	public boolean hasNextWord() {
		return hasNext() && tokens.get(index).type == TokenType.WORD;
	}
	
	public String next() {
		return hasNext() ? tokens.get(index++).data : null;
	}
	
	private void lexer(String input) {
		String patterns = "";
		for (TokenType tokenType : TokenType.values())
			patterns += String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern);
		Pattern tokenPatterns = Pattern.compile(new String(patterns.substring(1)));

	    Matcher matcher = tokenPatterns.matcher(input);
	    while(matcher.find()) {
	    	if (matcher.group(TokenType.NUMBER.name()) != null)
	    		tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name())));
	    	if (matcher.group(TokenType.WORD.name()) != null)
	    		tokens.add(new Token(TokenType.WORD, matcher.group(TokenType.WORD.name())));
	    	if (matcher.group(TokenType.BRACKET.name()) != null)
	    		tokens.add(new Token(TokenType.BRACKET, matcher.group(TokenType.BRACKET.name())));
	    }
	}
}