package com.awesome.excelpp.models;

import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.FormulaException;
import com.awesome.excelpp.parser.exception.MissingArgException;
import com.awesome.excelpp.parser.exception.MissingLBracketException;
import com.awesome.excelpp.parser.exception.MissingRBracketException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.ReferenceException;

import java.util.Observable;

/**
 * Class that represents a cell
 *
 */
public class Cell extends Observable {
	private String content; // =2+2
	private SpreadSheet sheet;
	
	/**
	 * Constructs a new Cell
	 * @param content	String with an unevaluated expression
	 */
	public Cell(SpreadSheet sheet, String content) {
		this.sheet = sheet;
		this.content = content;
	}
	
	/**
	 * Gets the unevaluated content of this Cell
	 * 	Suppose the content of this Cell is "=4+4"
	 * 	This function will then return "=4+4"
	 * @return			String with an unevaluated expression
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Sets the unevaluated content of this Cell
	 * @param content	String with an unevaluated expression
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Cell && ((Cell) obj).getContent().equals(this.getContent());
	}
	
	/**
	 * Gets the evaluated content of this Cell
	 * 	Suppose the content of this Cell is "=4+4"
	 * 	This function will then return "8"
	 * @return String with an evaluated expression
	 */
	public String toString() {
		if (content != null && content.length() > 0 && content.charAt(0) == '=') {
			try {
				Parser parse = new Parser(content.substring(1), sheet);
				parse.toPostfix();
				return String.valueOf(parse.eval());
			} catch (ParserException e) {
				if (e instanceof MissingRBracketException ||
						e instanceof MissingLBracketException ||
						e instanceof MissingArgException)
					return "#ARGINV";
				else if (e instanceof FormulaException)
					return "#OPINV";
				else if (e instanceof ReferenceException)
					return "#REFINV";
			}
		}
		
		return content;
	}
}
