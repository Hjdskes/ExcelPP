package com.awesome.excelpp.parser;

import java.util.LinkedList;

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

public class Parser {
	
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
	private SpreadSheet sheet;
	private Token currentToken;
	
	public Parser(Lexer lex, SpreadSheet sheet){
		this.lex = lex;
		this.sheet = sheet;
		output = new LinkedList<Token>();
		operators = new LinkedList<Token>();
		toPostfix();
	}
	
	public Parser(String expr, SpreadSheet sheet){
		this(new Lexer(expr), sheet);
	}
	
	/**Converts the expression in infix-notation to postfix-notation using the
	 * Shunting-yard Algorithm:
	 * 
	 * infix               | postfix
	 * ------------------- | -----------------
	 * 3 + 7 / (4 * 5 - 6) | 3 7 4 5 * 6 - / +
	 * 
	 */
	public LinkedList<Token> toPostfix(){
		while(lex.hasNext()){
			currentToken = lex.next();
			if(currentToken.getTokenType().equals("NUMBER") || currentToken.getTokenType().equals("CELL")){
				output.push(currentToken);
			}else if(currentToken.getTokenType().equals("MULTDIV")){
				while(!operators.isEmpty() && operators.getFirst().getTokenType().equals("MULTDIV")) {
					output.push(operators.pop());
				}
				operators.push(currentToken);
			}else if(currentToken.getTokenType().equals("PLUSMINUS")){
				while(!operators.isEmpty() && ((operators.getFirst().getTokenType().equals("PLUSMINUS")|| //throws NoSuchElementException, why?
					  operators.getFirst().getTokenType().equals("MULTDIV")))){
					output.push(operators.pop());
				}
				operators.push(currentToken);
			}else if(currentToken.getTokenType().equals("LBRACKET")){
				operators.push(currentToken);
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
		return output;
	}
	
	/**Evaluate the mathematical expression
	 * 
	 * @param - A queue of tokens, representing a mathematical expression in postfix-notation
	 * @return - The evaluated expression
	 */
	public double eval(LinkedList<Token> expr){
		LinkedList<Double> evalStack = new LinkedList<Double>();
		
		while(!output.isEmpty()){
			if(output.getLast().getTokenType().equals("NUMBER")){
				evalStack.push(Double.valueOf(output.removeLast().data));
			}else if(output.getLast().getTokenType().equals("CELL")){
				String ref = output.removeLast().data;
				int row = Integer.parseInt(ref.substring(1));
		        int col = (int) ref.charAt(0);
		        col -= 65;
		        Parser cellParse = new Parser(((Cell) sheet.getValueAt(row - 1, col)).getContent().toString(), sheet);
		        evalStack.push(new Double(cellParse.eval(cellParse.toPostfix())));
			}else if(output.getLast().getTokenType().equals("MULTDIV") ||
			         output.getLast().getTokenType().equals("PLUSMINUS")){
				Double b = evalStack.pop();
				Double a = evalStack.pop();
				if(output.getLast().data.equals("+")){
					output.removeLast();
					evalStack.push(new Double(a.doubleValue() + b.doubleValue()));
				}else if(output.getLast().data.equals("-")){
					output.removeLast();
					evalStack.push(new Double(a.doubleValue() - b.doubleValue()));
				}else if(output.getLast().data.equals("*")){
					output.removeLast();
					evalStack.push(new Double(a.doubleValue() * b.doubleValue()));
				}else{
					output.removeLast();
					evalStack.push(new Double(a.doubleValue() / b.doubleValue()));
				}
			}
		}

		return evalStack.pop().doubleValue();
	}
}
