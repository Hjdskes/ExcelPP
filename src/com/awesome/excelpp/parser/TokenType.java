package com.awesome.excelpp.parser;

public enum TokenType {
	NUMBER,		// (0-9)(.(0-9))	done?
	CELL,		// A-Z0-9			done
	WORD,		// a-z, A-Z			done?
	PLUSMINUS,	// +, -				done
	MULTDIV,	// *, /				done
	LBRACKET,	// (				done
	RBRACKET,	// )				done
	DELIM,		// ;, ,				done
	LOGIC,		// <, >, =
	WHITESPACE,	// all whitespace	done
	
	// PSEUDO TOKENS
	UNARYMINUS,
	EOL;
}
