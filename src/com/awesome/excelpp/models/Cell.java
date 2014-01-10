package com.awesome.excelpp.models;

import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.FormulaException;
import com.awesome.excelpp.parser.exception.MissingArgException;
import com.awesome.excelpp.parser.exception.MissingLBracketException;
import com.awesome.excelpp.parser.exception.MissingRBracketException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.ReferenceException;

import java.awt.Color;
import java.util.Observable;

/**
 * Class that represents a cell
 */
public class Cell extends Observable {
	private String content; // =2+2
	private SpreadSheet sheet;
	private Color foregroundColor;
	private Color backgroundColor;
	
	/**
	 * Constructs a new Cell
	 * @param content	String with an unevaluated expression
	 */
	public Cell(SpreadSheet sheet, String content) {
		this.sheet = sheet;
		this.content = content;
		this.foregroundColor = Color.black;
		this.backgroundColor = Color.white;
	}

	/**
	 * Constructs a new Cell with the specified background Color
	 * @param content  String with an unevaluated expression
	 * @param foregroundColor    The foreground Color of this Cell. Can be null.
	 * @param backgroundColor    The background Color of this Cell. Can be null.
	 */
	public Cell(SpreadSheet sheet, String content, Color foregroundColor, Color backgroundColor) {
		this.sheet = sheet;
		this.content = content;

		if (foregroundColor != null)
			this.foregroundColor = foregroundColor;
		else
			this.foregroundColor = Color.BLACK;

		if (backgroundColor != null)
			this.backgroundColor = backgroundColor;
		else
			this.backgroundColor = Color.WHITE;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Cell && ((Cell) obj).getContent().equals(this.getContent());
	}
	
	/**
	 * Gets the unevaluated content of this Cell
	 * Suppose the content of this Cell is "=4+4"
	 * This function will then return "=4+4"
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

	/**
	 * Gets the foreground Color of this Cell
	 * @return      The new foreground Color of this Cell
	 */
	public void setForegroundColor(Color newForegroundColor) {
		this.foregroundColor = newForegroundColor;
	}

	/**
	 * Sets the foreground Color of this Cell
	 * @return      The foreground Color of this Cell
	 */
	public Color getForegroundColor() {
		return foregroundColor;
	}

	/**
	 * Gets the background Color of this Cell
	 * @return      The new background Color of this Cell
	 */
	public void setBackgroundColor(Color newBackgroundColor) {
		this.backgroundColor = newBackgroundColor;
	}

	/**
	 * Sets the background Color of this Cell
	 * @return      The background Color of this Cell
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Gets the evaluated content of this Cell
	 * Suppose the content of this Cell is "=4+4"
	 * This function will then return "8"
	 * @return	String with an evaluated expression
	 */
	public String toString() {
		if (content != null && content.length() > 0 && content.charAt(0) == '=') {
			try {
				Parser parse = new Parser(content.substring(1), sheet);
				parse.toPostfix();
				return String.valueOf(parse.eval());
			} catch (ParserException e) {
				backgroundColor = Color.red;
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
