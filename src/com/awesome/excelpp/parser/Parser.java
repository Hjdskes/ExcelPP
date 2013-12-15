package com.awesome.excelpp.parser;

import com.awesome.excelpp.exprtree.*;

public class Parser {
	Lexer lex;
	Token lookahead;
	ExpressionNode expressionTree;
	
	public Parser(Lexer lex) throws Exception {
		this.lex = lex;
		lookahead = lex.next();
		this.expressionTree = expression();
	}
	
	public Parser(String str) throws Exception {
		this(new Lexer(str));
	}
	 
	private ExpressionNode expression() throws Exception {
		//System.out.println("expression -> signed_term sum_op");
		ExpressionNode expr = signedTerm();		// a
		return sumOp(expr);
	}
	 
	private ExpressionNode sumOp(ExpressionNode a) throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			//System.out.println("sum_op -> PLUSMINUS term sum_op");
			boolean addition = lookahead.data.equals("+");
			
			lookahead = lex.next();
			ExpressionNode sum = new AdditionExpressionNode(a, term(), addition);
			return sumOp(sum);
	    }
	    
		//System.out.println("sum_op -> EOL");
		return a;
	}
	
	private ExpressionNode signedTerm() throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			//System.out.println("signedTerm -> PLUSMINUS term");
			boolean positive = lookahead.data.equals("+");
			
			lookahead = lex.next();
			return term();
		}
		//System.out.println("signedTerm -> term");
		return term();
	}

	private ExpressionNode term() throws Exception {
		//System.out.println("term -> argument term_op");
		ExpressionNode expr = argument();
		return termOp(expr);
	}

	private ExpressionNode termOp(ExpressionNode a) throws Exception {
		if (lookahead.type == TokenType.MULTDIV) {
			//System.out.println("term_op -> MULTDIV argument term_op");
			boolean multiply = lookahead.data.equals("*");
			
			lookahead = lex.next();
			ExpressionNode term = new MultiplicationExpressionNode(a, argument(), multiply);
			return termOp(term);
		}
		
		//System.out.println("term_op -> EOL");
		return a;
	}

	private ExpressionNode argument() throws Exception {
		if (lookahead.type == TokenType.LBRACKET) {
			//System.out.println("argument -> OPEN_BRACKET sum CLOSE_BRACKET");
			lookahead = lex.next();
			
			ExpressionNode expr = expression();
			
			if (lookahead.type != TokenType.RBRACKET)
				throw new Exception("Closing brackets expected and " + lookahead.type + " found instead");
			
			lookahead = lex.next();
			return expr;
		}
		
		//System.out.println("argument -> value");
		return value();
	}

	private ExpressionNode value() throws Exception {
		if (lookahead.type == TokenType.NUMBER) {
			//System.out.println("value -> NUMBER");
			ExpressionNode expr = new ConstantExpressionNode(lookahead.data);
			
			lookahead = lex.next();
			return expr;
		} else {
			throw new Exception("Unexpected symbol " + lookahead.type + " found");
		}
	}
	
	public ExpressionNode getExpressionTree() {
		return expressionTree;
	}
}