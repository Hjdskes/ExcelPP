package com.awesome.excelpp.scanner;

public class Parser {
	Lexer lex;
	Token lookahead;
	
	public Parser(Lexer lex) throws Exception {
		this.lex = lex;
		lookahead = lex.next();
		expression();
	}
	
	public Parser(String str) throws Exception {
		this(new Lexer(str));
	}
	 
	private void expression() throws Exception {
		System.out.println("expression -> term sum_op");
		signedTerm();
		sumOp();
	}
	 
	private void sumOp() throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			System.out.println("sum_op -> PLUSMINUS term sum_op");
			lookahead = lex.next();
			term();
			sumOp();
	    } else {
	    	System.out.println("sum_op -> EOL");
	    }
	}
	
	private void signedTerm() throws Exception {
		if (lookahead.type == TokenType.PLUSMINUS) {
			System.out.println("signedTerm -> PLUSMINUS term");
			lookahead = lex.next();
			term();
		} else {
			System.out.println("signedTerm -> term");
			term();
		}
	}

	private void term() throws Exception {
		System.out.println("term -> argument term_op");
		argument();
		termOp();
	}

	private void termOp() throws Exception {
		if (lookahead.type == TokenType.MULTDIV) {
			System.out.println("term_op -> MULTDIV argument term_op");
			lookahead = lex.next();
			argument();
			termOp();
		} else {
			System.out.println("term_op -> EOL");
		}
	}

	private void argument() throws Exception {
		if (lookahead.type == TokenType.LBRACKET) {
			System.out.println("argument -> OPEN_BRACKET sum CLOSE_BRACKET");
			lookahead = lex.next();
			expression();
			
			if (lookahead.type != TokenType.RBRACKET)
				throw new Exception("Closing brackets expected and " +
											lookahead.type + " found instead");
			
			lookahead = lex.next();
		} else {
			System.out.println("argument -> value");
			value();
		}
	}

	private void value() throws Exception {
		if (lookahead.type == TokenType.NUMBER) {
			System.out.println("value -> NUMBER");
			lookahead = lex.next();
		} else {
			throw new Exception("Unexpected symbol " + lookahead.type + " found");
		}
	}
}