package com.awesome.excelpp.parser;

import java.util.LinkedList;

enum State {
	NONE,
	NUMBER,
	CELL,
	WORD
}

public class Lexer {
	public LinkedList<Token> tokens; //public for testing
	StringBuilder token = new StringBuilder();
	State state = State.NONE;
	
	public Lexer(String input) {
		this.tokens = new LinkedList<Token>();
		
		if (input == null)
			return;

	    for (int i = 0; i < input.length(); i++) {
	    	char ch = input.charAt(i);
	    	
	    	switch (ch) {
	    	case '*':
	    	case '/':
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.MULTDIV, Character.toString(ch)));
	    		break;
	    	case '+':
	    	case '-':
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.PLUSMINUS, Character.toString(ch)));
	    		break;
	    	case '(':
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.LBRACKET, Character.toString(ch)));
	    		break;
	    	case ')':
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.RBRACKET, Character.toString(ch)));
	    		break;
	    	case ',':
	    	case ';':
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.DELIM,Character.toString(ch)));
	    		break;
	    	case '.':
	    		setState(State.NUMBER);
	    		token.append(ch);
	    		break;
	    	case ' ': case '\t': case '\n': case '\r': case '\f':
	    		setState(State.NONE);
	    		break;
	    	default:
	    		if (Character.isDigit(ch)) {
	    			if(this.state == State.WORD){
	    				setState(State.CELL);
	    				token.append(ch);
	    			}else{
	    				setState(State.NUMBER);    				
	    				token.append(ch);
	    			}
	    		} else if (Character.isLetter(ch)) {
	    			setState(State.WORD);
	    			token.append(ch);
	    		}
	    	}
	    }
	    setState(State.NONE);
	    tokens.add(new Token(TokenType.EOL, null));
	}
	
	private void setState(State state) {
		if (this.state != state && state != State.CELL) {
			if (this.state == State.NUMBER){
				tokens.add(new Token(TokenType.NUMBER, token.toString()));
			}else if(this.state == State.CELL){
				tokens.add(new Token(TokenType.CELL, token.toString()));
			}else if (this.state == State.WORD){
				tokens.add(new Token(TokenType.WORD, token.toString()));
			}
			token = new StringBuilder();
		}
		this.state = state;
	}
	
	public boolean hasNext() {
		return !tokens.isEmpty();
	}
	
	public Token next() {
		return hasNext() ? tokens.pop() : new Token(TokenType.EOL, null);
	}
}
