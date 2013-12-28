package com.awesome.excelpp.parser;

import java.lang.reflect.Constructor;
import java.util.LinkedList;

import com.awesome.excelpp.math.Formula;
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
	
	public LinkedList<Integer> arityStack;
	
	public Parser(Lexer lex, SpreadSheet sheet){
		this.lex = lex;
		this.sheet = sheet;
		output = new LinkedList<Token>();
		operators = new LinkedList<Token>();
		arityStack = new LinkedList<Integer>();
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
		boolean lastWasNumber = false;
		LinkedList<Integer> numargsStack = new LinkedList<Integer>();
		
		while(lex.hasNext()){
			currentToken = lex.next();
			
			switch (currentToken.type) {
			case NUMBER:
			case CELL:
				output.push(currentToken);
				lastWasNumber = true;
				break;
			case MULTDIV:
				while(!operators.isEmpty() && (operators.getFirst().getTokenType().equals("MULTDIV") ||
					   operators.getFirst().getTokenType().equals("UNARYMINUS"))) {
					output.push(operators.pop());
				}
				operators.push(currentToken);
				lastWasNumber = false;
				break;
			case PLUSMINUS:
				if(!lastWasNumber){
					operators.push(new Token(TokenType.UNARYMINUS, "-"));
				}else{
					while(!operators.isEmpty() && ((operators.getFirst().getTokenType().equals("PLUSMINUS")|| //throws NoSuchElementException, why?
						   operators.getFirst().getTokenType().equals("MULTDIV") || 
						   operators.getFirst().getTokenType().equals("UNARYMINUS")))){
						output.push(operators.pop());
					}
					operators.push(currentToken);
					lastWasNumber = false;
				}
				break;
			case LBRACKET:
				operators.push(currentToken);
				lastWasNumber = false;
				break;
			case RBRACKET:
				while(!operators.getFirst().getTokenType().equals("LBRACKET")){
					output.push(operators.pop());
				}
				operators.pop();
				if (operators.getFirst().getTokenType().equals("WORD")){
					output.push(operators.pop());
					arityStack.push(numargsStack.pop());
				}
				break;
			case DELIM:
				Integer numargs = numargsStack.pop() + 1;
				numargsStack.push(numargs);
				while(!operators.getFirst().getTokenType().equals("LBRACKET")){
					output.push(operators.pop());
				}
				break;
			case WORD:
				numargsStack.push(1);
				operators.push(currentToken);
				lastWasNumber = false;
				break;
			case EOL:
				while(!operators.isEmpty()){
					output.push(operators.pop());
				}
				break;
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
			switch (output.getLast().type) {
			case UNARYMINUS:
				output.removeLast();
				evalStack.push(new Double(-evalStack.pop().doubleValue()));
				break;
			case NUMBER:
				evalStack.push(Double.valueOf(output.removeLast().data));
				break;
			case CELL:
				String ref = output.removeLast().data;
				int row = Integer.parseInt(ref.substring(1));
				int col = (int) ref.charAt(0);
				col -= 65;
				Parser cellParse = new Parser(sheet.getCellAt(row - 1, col).getContent().toString(), sheet);
				evalStack.push(new Double(cellParse.eval(cellParse.toPostfix())));
				break;
			case MULTDIV:
			case PLUSMINUS:
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
				break;
			case WORD:
				int numArgs = arityStack.removeLast();
				double[] args = new double[numArgs];
				for (int i = numArgs - 1; i >= 0; i--) {
					args[i] = evalStack.pop();
				}
				evalStack.push(evalFunction(output.removeLast().data, args));
				break;
			}
		}
		return evalStack.pop().doubleValue();
	}
	
	public double evalFunction(String function, double ... args) {
		String packageName = "com.awesome.excelpp.math";
		String formulaNameFull = packageName + '.' + function;
		Formula formula;
		double value = 0;
		
		try {
			Class<?> formulaClass = Class.forName(formulaNameFull);
			Constructor<?> opConstructor = formulaClass.getConstructor();
			formula = (Formula)opConstructor.newInstance();
			value = formula.getValue(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
