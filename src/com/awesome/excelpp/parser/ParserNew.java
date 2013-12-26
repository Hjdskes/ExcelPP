package com.awesome.excelpp.parser;

import java.util.LinkedList;

public class ParserNew {
	
	/*
	 * The following Tokens are operands:
	 * 
	 * - NUMBER
	 * - CELL
	 */
	public LinkedList<Token> output; //public for testing
	
	/*
	 * The following Tokens are operators:
	 * 
	 * - PLUS, MINUS
	 * - MULT, DIV
	 * - LBRACKET, RBRACKET
	 * - POW ?
	 * - FACT ?
	 */
	private LinkedList<Token> operators;
	public Lexer lex; //public for testing
	private Token currentToken;
	
	public ParserNew(Lexer lex){
		this.lex = lex;
		output = new LinkedList<Token>();
		operators = new LinkedList<Token>();
		currentToken = lex.next();
		toPostfix();
	}
	
	public ParserNew(String expr){
		this(new Lexer(expr));
	}
	
	/**Converts the expression in infix-notation to postfix-notation using the
	 * Shunting-yard Algorithm:
	 * 
	 * infix               | postfix
	 * ------------------- | -----------------
	 * 3 + 7 / (4 * 5 - 6) | 3 7 4 5 * 6 - / +
	 * 
	 */
	public void toPostfix(){
		while(lex.hasNext()){
			if(currentToken.getTokenType().equals("NUMBER") || currentToken.getTokenType().equals("CELL")){
				output.push(currentToken);
				currentToken = lex.next();
			}else if(currentToken.getTokenType().equals("MULTDIV")){
				if(!operators.isEmpty()){
					System.out.println(operators.getFirst().getTokenType());
					while(operators.getFirst().getTokenType().equals("PLUSMINUS")|| 
						  operators.getFirst().getTokenType().equals("MULTDIV")) {
						output.push(operators.pop());
					}					
				}
				operators.push(currentToken);
				currentToken = lex.next();
			}else if(currentToken.getTokenType().equals("PLUSMINUS")){
				if(!operators.isEmpty()){
					while(operators.getFirst().getTokenType().equals("PLUSMINUS")){
						output.push(operators.pop());
					}					
				}
				operators.push(currentToken);
				currentToken = lex.next();
			}else if(currentToken.getTokenType().equals("LBRACKET")){
				operators.push(currentToken);
				currentToken = lex.next();
			}else if(currentToken.getTokenType().equals("RBRACKET")){
				while(!operators.getFirst().getTokenType().equals("LBRACKET")){
					output.push(operators.pop());
				}
				operators.pop();
			}else if(currentToken.getTokenType().equals("EOL")){
				while(!operators.isEmpty()){
					output.push(operators.pop());
				}
			}
		}
	}
}
