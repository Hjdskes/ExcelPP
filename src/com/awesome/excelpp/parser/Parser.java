package com.awesome.excelpp.parser;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.awesome.excelpp.math.Formula;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.parser.exception.*;

public class Parser {
	
	/*
	 * The following Tokens are operands:
	 * 
	 * - NUMBER
	 * - CELL
	 */
	private LinkedList<Token> output;
	private LinkedList<Integer> arityStack;
	
	private Lexer lex;
	private SpreadSheet sheet;

	/**
	 * Creates a new expression Parser
	 * @param lex		the {@link Lexer} containing the expression 
	 * @param sheet		the referenced {@link SpreadSheet}
	 */
	public Parser(Lexer lex, SpreadSheet sheet){
		this.lex = lex;
		this.sheet = sheet;
		output = new LinkedList<Token>();
		arityStack = new LinkedList<Integer>();
	}
	
	/**
	 * Creates a new expression Parser
	 * @param expr		the String containing the expression
	 * @param sheet		the referenced {@link SpreadSheet}
	 */
	public Parser(String expr, SpreadSheet sheet){
		this(new Lexer(expr), sheet);
	}
	
	/**
	 * Creates a new expression Parser
	 * @param expr		the String containing the expression
	 */
	public Parser(String expr){
		this(new Lexer(expr), null);
	}
	
	/**
	 * Converts the expression in infix-notation to postfix-notation using the Shunting-yard Algorithm:<br>
	 * <br>
	 * infix               | postfix<br>
	 * ------------------- | -----------------<br>
	 * 3 + 7 / (4 * 5 - 6) | 3 7 4 5 * 6 - / +<br>
	 * @throws		ParserException 
	 */
	@SuppressWarnings("incomplete-switch")
	public void toPostfix() throws ParserException{
		/*
		 * The following Tokens are operators:
		 * 
		 * - PLUS, MINUS
		 * - MULT, DIV
		 * - LBRACKET, RBRACKET
		 * - POW ?
		 * - FACT ?
		 */
		LinkedList<Token> operators = new LinkedList<Token>();
		LinkedList<Integer> numargsStack = new LinkedList<Integer>();
		boolean lastWasNumber = false;
		Token currentToken;
		
		while(lex.hasNext()){
			currentToken = lex.next();
			
			switch (currentToken.type) {
			case NUMBER:
			case CELL:
				output.push(currentToken);
				lastWasNumber = true;
				break;
			case MULTDIV:
				while(!operators.isEmpty() &&
						(operators.getFirst().type == TokenType.MULTDIV ||
						operators.getFirst().type == TokenType.UNARYMINUS))
				{
					output.push(operators.pop());
				}
				operators.push(currentToken);
				lastWasNumber = false;
				break;
			case PLUSMINUS:
				if(!lastWasNumber && currentToken.data.equals("-")) {
					operators.push(new Token(TokenType.UNARYMINUS, "-"));
				} else {
					while(!operators.isEmpty() &&
							(operators.getFirst().type == TokenType.PLUSMINUS ||
							operators.getFirst().type == TokenType.MULTDIV ||
							operators.getFirst().type == TokenType.UNARYMINUS))
					{
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
				try {
					while(!(operators.getFirst().type == TokenType.LBRACKET)){
						output.push(operators.pop());
					}
					operators.pop();
					if (!operators.isEmpty() && operators.getFirst().type == TokenType.WORD){
						output.push(operators.pop());
						arityStack.push(numargsStack.pop());
					}
				} catch (NoSuchElementException e) {
					throw new MissingLBracketException();
				}
				break;
			case DELIM:
				try {
					Integer numargs = numargsStack.pop() + 1;
					numargsStack.push(numargs);
					while(!(operators.getFirst().type == TokenType.LBRACKET)){
						output.push(operators.pop());
					}
				} catch (NoSuchElementException e) {
					throw new MissingLBracketException();
				}
				break;
			case CELLDELIM:
				//TODO:
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
	}
	
	/**
	 * Evaluate the stored mathematical expression represented in postfix-notation
	 * @return		the evaluated expression
	 * @throws		ParserException
	 */
	@SuppressWarnings("incomplete-switch")
	public double eval() throws ParserException{
		if (output.isEmpty()) {
			toPostfix();
		}
		
		LinkedList<Double> evalStack = new LinkedList<Double>();
		
		while(!output.isEmpty()){
			switch (output.getLast().type) {
			case UNARYMINUS:
				try {
					output.removeLast();
					evalStack.push(new Double(-evalStack.pop().doubleValue()));
				} catch (NoSuchElementException e) {
					throw new MissingArgException();
				}
				break;
			case NUMBER:
				evalStack.push(Double.valueOf(output.removeLast().data));
				break;
			case CELL:
				try {
					String ref = output.removeLast().data;
					int row = Integer.parseInt(ref.substring(1));
					int col = (int) ref.charAt(0);
					col -= 65;
					evalStack.push(Double.parseDouble(sheet.getValueAt(row - 1, col).toString()));
				} catch (NumberFormatException | NullPointerException e) {
					throw new ReferenceException();
				}
				break;
			case MULTDIV:
			case PLUSMINUS:
				try {
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
				} catch (NoSuchElementException e) {
					throw new MissingArgException();
				}
				break;
			case WORD:
				int numArgs = 0;
				double[] args;
				try {
					numArgs = arityStack.removeLast();
					args = new double[numArgs];
				} catch (NoSuchElementException e) {
					throw new MissingLBracketException();
				}
				try {
					for (int i = numArgs - 1; i >= 0; i--) {
						args[i] = evalStack.pop();
					}
					evalStack.push(evalFunction(output.removeLast().data, args));
				} catch (NoSuchElementException e) {
					throw new MissingArgException();
				}
				break;
			case LBRACKET:
				throw new MissingRBracketException();
			}
		}
		
		double retvalue = 0.0;
		try {
			retvalue = evalStack.pop().doubleValue();
		} catch (NoSuchElementException e) {
			throw new MissingArgException();
		}
		return retvalue;
	}
	
	/**
	 * Evaluate a {@link Formula} using the formulas in package com.awesome.excelpp.math
	 * @param formulaName	the {@link Formula} name
	 * @param args			the {@link Formula} arguments (1..*)
	 * @return				the evaluated {@link Formula}
	 * @throws FormulaException
	 */
	private double evalFunction(String function, double ... args) throws FormulaException {
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
			throw new FormulaException();
		}
		return value;
	}
}
