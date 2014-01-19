package com.awesome.excelpp.parser;

import java.util.LinkedList;

/**
 * This class splits an incoming String into Tokens for analysis by the {@link Parser}.
 * @author Team Awesome
 */
public class Lexer {
	private LinkedList<Token> tokens;
	private StringBuilder token = new StringBuilder();
	private State state = State.NONE;
	
	/**
	 * Creates a new expression {@link Lexer}.
	 * @param input	The String containing the expression
	 */
	public Lexer(String input) {
		this.tokens = new LinkedList<Token>();
		
		if (input == null)
			return;
		
		if (input.length() > 0 && input.charAt(0) != '=') {
			try {
				tokens.add(new Token(TokenType.NUMBER, new Double(Double.parseDouble(input)).toString()));
			} catch (NumberFormatException e) {
				tokens.add(new Token(TokenType.STRING, input));
			}
			return;
		}
		
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
	    		setState(State.NONE);
	    		tokens.add(new Token(TokenType.DELIM, Character.toString(ch)));
	    		break;
	    	case ':':
	    		token.append(ch);
	    		break;
	    	case '.':
	    		setState(State.NUMBER);
	    		token.append(ch);
	    		break;
	    	case ' ': case '\t': case '\n': case '\r': case '\f':
	    		setState(State.NONE);
	    		break;
	    	case '"':
	    		do {
	    			token.append(input.charAt(i++));
	    		} while (input.charAt(i) != '"');
	    		token.append(input.charAt(i));
	    		tokens.add(new Token(TokenType.STRING, token.toString()));
	    		setState(State.NONE);
	    		break;
	    	default:
	    		if (Character.isDigit(ch)) {
	    			if(this.state == State.WORD){
	    				setState(State.CELL);
	    				token.append(ch);
	    			} else if (this.state == State.CELL) {
	    				token.append(ch);
	    			} else {
	    				setState(State.NUMBER);
	    				token.append(ch);
	    			}
	    		} else if (Character.isLetter(ch)) {
	    			if (this.state != State.CELL) {
		    			setState(State.WORD);
	    			}
	    			token.append(ch);
	    		}
	    	}
	    }
	    setState(State.NONE);
	    tokens.add(new Token(TokenType.EOL, null));
	}
	
	/**
	 * Checks whether the {@link Lexer} has another {@link Token} in its input. 
	 * @return True is the {@link Lexer} has another {@link Token} in its input
	 */
	public boolean hasNext() {
		return !tokens.isEmpty();
	}
	
	/**
	 * Returns the next {@link Token} the {@link Lexer} has in its input.
	 * @return The next {@link Token}
	 */
	public Token next() {
		return hasNext() ? tokens.pop() : new Token(TokenType.EOL, null);
	}
	
	/**
	 * Changes the parser's internal {@link State}.
	 * @param state	The {@link State} we want to change to
	 */
	private void setState(State state) {
		if (this.state != state &&
				(state != State.CELL && state != State.CELLRANGE)) {
			if (this.state == State.NUMBER){
				tokens.add(new Token(TokenType.NUMBER, token.toString()));
			}else if(this.state == State.CELL){
				String temp = token.toString();
				if(temp.indexOf(':') == -1){					
					tokens.add(new Token(TokenType.CELL, temp));
				}else{					
					tokens.add(new Token(TokenType.CELLRANGE, temp));
				}
			}else if (this.state == State.WORD){
				tokens.add(new Token(TokenType.WORD, token.toString()));
			}
			token = new StringBuilder();
		}
		this.state = state;
	}
}
