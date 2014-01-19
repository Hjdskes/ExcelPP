package com.awesome.excelpp.parser;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.awesome.excelpp.math.Formula;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.parser.exception.*;

/**
 * The Parser analyzes whether the tokens follow correct grammar for an Excel++ expression.
 * <p>It then evaluates the expression and calls all specified math functions dynamically, based on the input it is given.
 * The parser supports passing Objects, so that Strings, Doubles and Integers can be detected at runtime.</p>
 * @author Team Awesome
 */
public class Parser {
	/* The following Tokens are operands:
	 * - NUMBER
	 * - CELL
	 */
	private LinkedList<Token> output;
	private LinkedList<Integer> arityStack;
	
	private Lexer lex;
	private SpreadSheet sheet;
	private Cell cell;

	/**
	 * Creates a new expression Parser.
	 * @param lex The {@link Lexer} containing the expression 
	 * @param sheet The referenced {@link SpreadSheet}
	 * @param cell The source {@link Cell}
	 */
	public Parser(Lexer lex, SpreadSheet sheet, Cell cell){
		this.lex = lex;
		this.sheet = sheet;
		this.cell = cell;
		output = new LinkedList<Token>();
		arityStack = new LinkedList<Integer>();
	}
	
	/**
	 * Creates a new expression Parser.
	 * @param expr The String containing the expression
	 * @param sheet	The referenced {@link SpreadSheet}
	 * @param cell The source {@link Cell}
	 */
	public Parser(String expr, SpreadSheet sheet, Cell cell){
		this(new Lexer(expr), sheet, cell);
	}
	
	/**
	 * Creates a new expression Parser.
	 * @param expr The String containing the expression
	 * @param sheet	The referenced {@link SpreadSheet}
	 */
	public Parser(String expr, SpreadSheet sheet){
		this(new Lexer(expr), sheet, null);
	}
	
	/**
	 * Creates a new expression Parser.
	 * @param expr The String containing the expression
	 */
	public Parser(String expr){
		this(new Lexer(expr), null, null);
	}
	
	/**
	 * Converts the expression in infix-notation to postfix-notation using the Shunting-yard Algorithm:<br>
	 * <br>
	 * infix               | postfix<br>
	 * ------------------- | -----------------<br>
	 * 3 + 7 / (4 * 5 - 6) | 3 7 4 5 * 6 - / +<br>
	 * @throws ParserException 
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
			case CELLRANGE:
				output.push(currentToken);
				lastWasNumber = true;
				break;
			case STRING:
				output.push(currentToken);
				lastWasNumber = false;
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
	 * Evaluates the stored mathematical expression represented in postfix-notation.
	 * @return The evaluated expression
	 * @throws ParserException
	 */
	@SuppressWarnings("incomplete-switch")
	public Object eval() throws ParserException{
		if (output.isEmpty()) {
			toPostfix();
		}
		
		LinkedList<Object> evalStack = new LinkedList<Object>();
		
		while(!output.isEmpty()){
			switch (output.getLast().type) {
			case UNARYMINUS:
				Object arg;
				try {
					arg = evalStack.pop();
				} catch (NoSuchElementException e) {
					throw new MissingArgException();
				}
				
				if (arg instanceof Double) {
					output.removeLast();
					evalStack.push(-(Double)arg);
				} else {
					//TODO: Do something with strings...
					throw new MissingArgException();
				}
				break;
			case NUMBER:
				evalStack.push(Double.valueOf(output.removeLast().data));
				break;
			case STRING:
				evalStack.push(output.removeLast().data);
				break;
			case CELLRANGE:
				String[] range;
				int arity;
				try {
					range = output.removeLast().data.split(":");
					arity = arityStack.removeLast() - 1;
				} catch (NoSuchElementException e) {
					throw new ReferenceException();
				}
				int startRow = Integer.parseInt(range[0].substring(1));
				int startCol = (int) range[0].charAt(0);
				startCol -= 65;
				int endRow = Integer.parseInt(range[1].substring(1));
				int endCol = (int) range[1].charAt(0);
				endCol -= 65;

				for(int row = startRow; row <= endRow; row++){
					for(int col = startCol; col <= endCol; col++){
						arity++;
						
						Cell cellref = (Cell)sheet.getValueAt(row - 1, col);
						if (cell == cellref)
							throw new ReferenceException();
						
						if (cell != null)
							cellref.addObserver(cell);
						
						Object refvalue = new Parser(cellref.getContent(), sheet, cell).eval();
						evalStack.push(refvalue);
					}
				}
				arityStack.push(arity);
				break;
			case CELL:
				String ref = output.removeLast().data;
				int row = Integer.parseInt(ref.substring(1));
				int col = (int) ref.charAt(0);
				col -= 65;
				
				Cell cellref = (Cell)sheet.getValueAt(row - 1, col);
				if (cell == cellref)
					throw new ReferenceException();
				
				if (cell != null)
					cellref.addObserver(cell);
				
				Object refvalue = new Parser(cellref.getContent(), sheet, cell).eval();
				evalStack.push(refvalue);
				break;
			case MULTDIV:
			case PLUSMINUS:
				Object a, b;
				Token op;
				try {
					b = evalStack.pop();
					a = evalStack.pop();
					op = output.removeLast();
				} catch (NoSuchElementException e) {
					throw new MissingArgException();
				}
				
				if (a instanceof Double && b instanceof Double) {
					if(op.data.equals("+")){
						evalStack.push(new Double((Double)a + (Double)b));
					}else if(op.data.equals("-")){
						evalStack.push(new Double((Double)a - (Double)b));
					}else if(op.data.equals("*")){
						evalStack.push(new Double((Double)a * (Double)b));
					}else{
						evalStack.push(new Double((Double)a / (Double)b));
					}
				} else {
					// TODO: Do something with strings...
					// Moet dit niet gewoon een MathException returnen? Of wil je iets doen met String concat?
					throw new MissingArgException();
				}
				break;
			case WORD:
				int numArgs = 0;
				Object args[];
				try {
					numArgs = arityStack.removeLast();
					args = new Object[numArgs];
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
		
		Object retvalue = 0.0;
		try {
			retvalue = evalStack.pop();
		} catch (NoSuchElementException e) {
			
		}
		
		if (retvalue instanceof Double) {
			retvalue = (Double)retvalue;
		}
		return retvalue;
	}
	
	/**
	 * Evaluates a {@link Formula} using the formulas in package <code>com.awesome.excelpp.math</code>.
	 * @param formulaName The {@link Formula} name
	 * @param args The {@link Formula} arguments (1..*)
	 * @return The evaluated {@link Formula}
	 * @throws FormulaException
	 */
	private Object evalFunction(String function, Object ... args) throws FormulaException {
		String packageName = "com.awesome.excelpp.math";
		String formulaNameFull = packageName + '.' + function;
		Formula formula;
		Object value = null;
		
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
