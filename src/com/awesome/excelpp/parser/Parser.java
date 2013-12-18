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
	
	/**
	 * expression -> signed_term sum_op
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode expression() throws Exception {
		ExpressionNode expr = signedTerm();
		return sumOp(expr);
	}
	
	/**
	 * sum_op -> PLUSMINUS term sum_op
	 * sum_op -> EOL
	 * @param a
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode sumOp(ExpressionNode a) throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			boolean addition = lookahead.data.equals("+");
			
			lookahead = lex.next();
			ExpressionNode sum = new AdditionExpressionNode(a, term(), addition);
			return sumOp(sum);
		}
		return a;
	}
	
	/**
	 * signedTerm -> PLUSMINUS term
	 * signedTerm -> term
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode signedTerm() throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			boolean positive = lookahead.data.equals("+");
			
			lookahead = lex.next();
			return term();
		}
		return term();
	}

	/**
	 * term -> argument term_op
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode term() throws Exception {
		ExpressionNode expr = argument();
		return termOp(expr);
	}

	/**
	 * term_op -> MULTDIV argument term_op
	 * term_op -> EOL
	 * @param a
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode termOp(ExpressionNode a) throws Exception {
		if (lookahead.type == TokenType.MULTDIV) {
			boolean multiply = lookahead.data.equals("*");
			
			lookahead = lex.next();
			ExpressionNode term = new MultiplicationExpressionNode(a, argument(), multiply);
			return termOp(term);
		}
		return a;
	}

	/**
	 * argument -> OPEN_BRACKET sum CLOSE_BRACKET
	 * argument -> value 
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode argument() throws Exception {
		if (lookahead.type == TokenType.LBRACKET) {
			lookahead = lex.next();
			
			ExpressionNode expr = expression();
			
			if (lookahead.type != TokenType.RBRACKET)
				throw new Exception("Closing brackets expected and " + lookahead.type + " found instead");
			
			lookahead = lex.next();
			return expr;
		}
		return value();
	}

	/**
	 * value -> NUMBER
	 * @return
	 * @throws Exception
	 */
	private ExpressionNode value() throws Exception {
		if (lookahead.type == TokenType.NUMBER) {
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
