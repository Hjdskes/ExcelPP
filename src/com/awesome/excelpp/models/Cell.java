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
	private int fontBold; // 1 = bold, 0 = niet bold
	private int fontItalics; // 2 = italics, 0 = niet italics
	private Color foregroundColor;
	private Color backgroundColor;
	
	/**
	 * Constructs a new Cell
	 * @param content	String with an unevaluated expression
	 */
	public Cell(SpreadSheet sheet, String content, int bold, int italics, Color foregroundColor, Color backgroundColor) {
		this.sheet = sheet;
		this.content = content;
		this.fontBold = bold;
		this.fontItalics = italics;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Constructs a new Cell with the specified background Color
	 * @param content  String with an unevaluated expression
	 * @param bold     Whether or not the text in this Cell is bold
	 * @param italics  Whether or not the text in this Cell is in italics
	 * @param foregroundColor    The foreground Color of this Cell. Can be null.
	 * @param backgroundColor    The background Color of this Cell. Can be null.
	 */
	public Cell(SpreadSheet sheet, String content) {
		this(sheet, content, 0, 0, Color.BLACK, Color.WHITE);
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

	public void setBold(int bold) {
		if (bold == 0 || bold == 1) //bold mag alleen 0 of 1 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.BOLD
			this.fontBold = bold;
	}

	public int getBold() {
		return fontBold;
	}

	public void setItalics(int italics) {
		if (italics == 0 || italics == 2) //italics mag alleen 0 of 2 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.ITALIC
			this.fontItalics = italics;
	}

	public int getItalics() {
		return fontItalics;
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
