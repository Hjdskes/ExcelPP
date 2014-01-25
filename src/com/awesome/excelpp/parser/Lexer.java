package com.awesome.excelpp.parser;

import java.util.LinkedList;

import static com.awesome.excelpp.parser.TokenType.*;

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
			tokens.add(new Token(STRING, input));
			return;
		}
			
		
	    for (int i = 1; i < input.length(); i++) {
	    	char ch = input.charAt(i);
	    	
	    	switch (ch) {
	    	case '*':
	    	case '/':
	    		setState(State.NONE);
	    		tokens.add(new Token(MULTDIV, Character.toString(ch)));
	    		break;
	    	case '<':
	    	case '>':
	    		setState(State.NONE);
	    		if (input.charAt(i + 1) == '=') {
	    			tokens.add(new Token(LOGICOP, Character.toString(ch) + "="));
	    		} else {
	    			tokens.add(new Token(LOGICOP, Character.toString(ch)));
	    		}
	    		break;
	    	case '+':
	    	case '-':
	    		setState(State.NONE);
	    		tokens.add(new Token(PLUSMINUS, Character.toString(ch)));
	    		break;
	    	case '(':
	    		setState(State.NONE);
	    		tokens.add(new Token(LBRACKET, null));
	    		break;
	    	case ')':
	    		setState(State.NONE);
	    		tokens.add(new Token(RBRACKET, null));
	    		break;
	    	case ',':
	    		setState(State.NONE);
	    		tokens.add(new Token(DELIM, null));
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
	    		setState(State.NONE);
	    		while (input.charAt(++i) != '"') {
	    			token.append(input.charAt(i));
	    		}
	    		tokens.add(new Token(STRING, token.toString()));
	    		token = new StringBuilder();
	    		break;
	    	case '=':
	    		setState(State.NONE);
	    		if (input.charAt(i + 1) == '=') {
	    			tokens.add(new Token(LOGICEQ, "=="));
	    		}
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
	    tokens.add(new Token(EOL, null));
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
		return hasNext() ? tokens.pop() : new Token(EOL, null);
	}
	
	/**
	 * Changes the parser's internal {@link State}.
	 * @param state	The {@link State} we want to change to
	 */
	private void setState(State state) {
		if (this.state != state &&
				(state != State.CELL && state != State.CELLRANGE)) {
			if (this.state == State.NUMBER) {
				tokens.add(new Token(NUMBER, token.toString()));
			} else if (this.state == State.CELL) {
				String temp = token.toString();
				if (temp.indexOf(':') == -1) {					
					tokens.add(new Token(CELL, temp));
				} else {					
					tokens.add(new Token(CELLRANGE, temp));
				}
			} else if (this.state == State.WORD) {
				tokens.add(new Token(WORD, token.toString()));
			}
			token = new StringBuilder();
		}
		this.state = state;
	}
}
