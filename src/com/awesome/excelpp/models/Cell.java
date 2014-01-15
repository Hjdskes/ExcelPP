package com.awesome.excelpp.models;

import com.awesome.excelpp.parser.Parser;
import com.awesome.excelpp.parser.exception.FormulaException;
import com.awesome.excelpp.parser.exception.MissingArgException;
import com.awesome.excelpp.parser.exception.MissingLBracketException;
import com.awesome.excelpp.parser.exception.MissingRBracketException;
import com.awesome.excelpp.parser.exception.ParserException;
import com.awesome.excelpp.parser.exception.ReferenceException;

import java.awt.Color;

/**
 * Class that represents a cell
 */
public class Cell {
	private String content; // =2+2
	private SpreadSheet sheet;
	private int fontBold; // 1 = bold, 0 = niet bold
	private int fontItalic; // 2 = italic, 0 = niet italic
	private Color foregroundColor;
	private Color backgroundColor;
	
	/**
	 * Constructs a new Cell
	 * @param content	String with an unevaluated expression
	 */
	public Cell(SpreadSheet sheet, String content, int bold, int italic, Color foregroundColor, Color backgroundColor) {
		this.sheet = sheet;
		this.content = content;
		this.fontBold = bold;
		this.fontItalic = italic;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}

	/**
	 * Constructs a new Cell with the specified background Color
	 * @param content  String with an unevaluated expression
	 * @param bold     Whether or not the text in this Cell is bold
	 * @param italic  Whether or not the text in this Cell is in italic
	 * @param foregroundColor    The foreground Color of this Cell. Can be null.
	 * @param backgroundColor    The background Color of this Cell. Can be null.
	 */
	public Cell(SpreadSheet sheet, String content) {
		this(sheet, content, 0, 0, Color.BLACK, Color.WHITE);
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		
		if (obj instanceof Cell) {
			Cell other = (Cell)obj;
			if ((content == null && other.getContent() == null) || content != null && content.equals(other.getContent()) &&
					(this.fontBold == other.getBold()) &&
					(this.fontItalic == other.getItalic()) &&
					(this.foregroundColor.equals(other.getForegroundColor())) &&
					(this.backgroundColor.equals(other.getBackgroundColor())))
			{
				equals = true;
			}
		}
		return equals;
	}
	
	/**
	 * Gets the unevaluated content of this Cell
	 * Suppose the content of this Cell is "=4+4"
	 * This function will then return "=4+4"
	 * @return			String with an unevaluated expression
	 */
	public String getContent() {
		return content == null ? "" : content;
	}
	
	/**
	 * Sets the unevaluated content of this Cell
	 * @param content	String with an unevaluated expression
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Sets the bold state of the font in this Cell
	 * @param bold    Whether or not font is bold: 1 for bold, 0 for plain
	 */
	public void setBold(int bold) {
		if (bold == 0 || bold == 1) //bold mag alleen 0 of 1 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.BOLD
			this.fontBold = bold;
	}

	/**
	 * Gets the current bold state of the font in this Cell
	 * @return     The current bold state of the font in this Cell
	 */
	public int getBold() {
		return fontBold;
	}

	/**
	 * Sets the italic state of the font in this Cell
	 * @param bold    Whether or not font is italic: 2 for italic, 0 for plain
	 */
	public void setItalic(int italic) {
		if (italic == 0 || italic == 2) //italic mag alleen 0 of 2 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.ITALIC
			this.fontItalic = italic;
	}

	/**
	 * Gets the current italic state of the font in this Cell
	 * @return     The current italic state of the font in this Cell
	 */
	public int getItalic() {
		return fontItalic;
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
	
	public String getForegroundColorHex() {
		String hex = "#" + Integer.toHexString(foregroundColor.getRGB()).substring(2);
		return hex;
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
	
	public String getBackgroundColorHex() {
		String hex = "#"+Integer.toHexString(backgroundColor.getRGB()).substring(2);
		return hex;
	}
	
	/**
	 * Gets the sheet of this cell
	 * @return The sheet of this cell
	 */
	public SpreadSheet getSheet(){
		return sheet;
	}
	
	public boolean isEmpty() {
		return (fontBold == 0 && fontItalic == 0 &&
				(foregroundColor == null || foregroundColor == Color.BLACK) &&
				(backgroundColor == null || backgroundColor == Color.WHITE) && 
				(content == null || content.equals("") || content == ""));
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
