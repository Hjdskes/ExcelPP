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
 * Class that represents a cell inside the <code>SpreadSheetTable</code>.
 * @author Team Awesome
 */
public class Cell {
	private String content; // =2+2
	private SpreadSheet sheet;
	private int fontBold; // 1 = bold, 0 = niet bold
	private int fontItalic; // 2 = italic, 0 = niet italic
	private Color foregroundColor;
	private Color backgroundColor;
	
	/**
	 * Constructs a new <code>Cell</code>.
	 * @param sheet <code>SpreadSheet</code> to add this <code>Cell</code> to
	 * @param content String with an unevaluated expression
	 * @param bold 0 if the text is plain, 1 if the text is bold
	 * @param italic 0 if the text is plain, 2 if the text is italic
	 * @param foregroundColor The foreground <code>Color</code> of this <code>Cell</code> (can be null)
	 * @param backgroundColor The background <code>Color</code> of this <code>Cell</code> (can be null)
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
	 * Constructs a new <code>Cell</code> with default markup.
	 * @param sheet	<code>SpreadSheet</code> to add this <code>Cell</code> to
	 * @param content String with an unevaluated expression
	 */
	public Cell(SpreadSheet sheet, String content) {
		this(sheet, content, 0, 0, Color.BLACK, Color.WHITE);
	}

	/**
	 * Returns true if and only if this <code>Cell</code> equals that <code>Cell</code>.
	 * @param obj The <code>Cell</code> to compare with
	 * @return True if and only if this <code>Cell</code> equals that <code>Cell</code>
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		
		if (obj instanceof Cell) {
			Cell other = (Cell)obj;
			if (	this.getContent().equals(other.getContent()) &&
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
	 * Gets the unevaluated content of this <code>Cell</code>.
	 * Suppose the content of this <code>Cell</code> is "=4+4",
	 * this method will then return "=4+4".
	 * @return The unevaluated expression
	 */
	public String getContent() {
		return content == null ? "" : content;
	}
	
	/**
	 * Sets the unevaluated content of this <code>Cell</code>.
	 * @param content String with an unevaluated expression
	 * @param undoable If false, edit won't be posted to the undoSupport
	 */
	public void setContent(String content, boolean undoable) {
		Cell oldValue = cloneThis();
		this.content = content;
		Cell newValue = cloneThis();
		
		if(undoable) {
			postEdit(oldValue, newValue);
		}
	}
	
	/**
	 * Sets the bold state of the font in this <code>Cell</code>.
	 * @param bold Whether or not the font is bold: 1 for bold, 0 for plain
	 * @param undoable If false, edit won't be posted to the undoSupport
	 */
	public void setBold(int bold, boolean undoable) {
		Cell oldValue = cloneThis();
		if (bold == 0 || bold == 1) //bold mag alleen 0 of 1 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.BOLD
			this.fontBold = bold;
		Cell newValue = cloneThis();
		
		if(undoable) {
			postEdit(oldValue, newValue);
		}
	}

	/**
	 * Gets the current bold state of the font in this <code>Cell</code>.
	 * @return The current bold state of the font in this <code>Cell</code>
	 */
	public int getBold() {
		return fontBold;
	}
	
	/**
	 * Sets the italic state of the font in this <code>Cell</code>.
	 * @param italic Whether or not the font is italic: 2 for italic, 0 for plain
	 * @param undoable If false, edit won't be posted to the undoSupport
	 */
	public void setItalic(int italic, boolean undoable){
		Cell oldValue = cloneThis();
		if (italic == 0 || italic == 2) //italic mag alleen 0 of 2 zijn: http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.Font.ITALIC
			this.fontItalic = italic;
		Cell newValue = cloneThis();
		
		if(undoable) {
			postEdit(oldValue, newValue);
		}
	}

	/**
	 * Gets the current italic state of the font in this <code>Cell</code>.
	 * @return The current italic state of the font in this <code>Cell</code>
	 */
	public int getItalic() {
		return fontItalic;
	}

	/**
	 * Gets the foreground <code>Color</code> of this <code>Cell</code>.
	 * @param newForegroundColor The new foreground <code>Color</code> of this <code>Cell</code>
	 * @param undoable If false, edit won't be posted to the undoSupport
	 */
	public void setForegroundColor(Color newForegroundColor, boolean undoable) {
		Cell oldValue = cloneThis();
		this.foregroundColor = newForegroundColor;
		Cell newValue = cloneThis();
		
		if(undoable) {
			postEdit(oldValue, newValue);
		}
	}

	/**
	 * Gets the foreground <code>Color</code> of this <code>Cell</code>.
	 * @return The foreground <code>Color</code> of this <code>Cell</code>
	 */
	public Color getForegroundColor() {
		return foregroundColor == null ? Color.BLACK : foregroundColor;
	}

	/**
	 * Returns this <code>Cell's</code> foreground <code>Color</code> as a hex String.
	 * @return This <code>Cell's</code> foreground <code>Color</code> as hex
	 */
	public String getForegroundColorHex() {
		String hex = "#" + Integer.toHexString(foregroundColor.getRGB()).substring(2);
		return hex;
	}
	
	/**
	 * Sets the background <code>Color</code> of this <code>Cell</code>.
	 * @param newBackgroundColor The new background <code>Color</code> of this <code>Cell</code>
	 * @param undoable If false, edit won't be posted to the undoSupport
	 */
	public void setBackgroundColor(Color newBackgroundColor, boolean undoable) {
		Cell oldValue = cloneThis();
		this.backgroundColor = newBackgroundColor;
		Cell newValue = cloneThis();
		
		if(undoable) {
			postEdit(oldValue, newValue);
		}
	}

	/**
	 * Gets the background <code>Color</code> of this <code>Cell</code>.
	 * @return The background <code>Color</code> of this <code>Cell</code>
	 */
	public Color getBackgroundColor() {
		return backgroundColor == null ? Color.WHITE : backgroundColor;
	}

	/**
	 * Returns this <code>Cell's</code> background <code>Color</code> as a hex String.
	 * @return This <code>Cell's</code> background <code>Color</code> as hex
	 */
	public String getBackgroundColorHex() {
		String hex = "#"+Integer.toHexString(backgroundColor.getRGB()).substring(2);
		return hex;
	}
	
	/**
	 * Gets the <code>SpreadSheet</code> this <code>Cell</code> belongs to.
	 * @return The <code>SpreadSheet</code> this <code>Cell</code> belongs to
	 */
	public SpreadSheet getSheet() {
		return sheet;
	}

	/**
	 * Returns true if this <code>Cell</code> is empty.
	 * @return True if this <code>Cell</code> is empty
	 */
	public boolean isEmpty() {
		return (fontBold == 0 && fontItalic == 0 &&
				(getForegroundColor() == Color.BLACK) &&
				(getBackgroundColor() == Color.WHITE) && 
				(this.getContent().equals("")));
	}

	public Object getValue() {
		try {
			Parser parse = new Parser(content, sheet);
			parse.toPostfix();
			return parse.eval();
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
		
		return content;
	}

	/**
	 * Gets the evaluated content of this <code>Cell</code>.
	 * Suppose the content of this <code>Cell</code> is "=4+4",
	 * this method will then return "8".
	 * @return The evaluated expression
	 */
	public String toString() {
		if (content != null && content.length() > 0 && content.charAt(0) == '=') {
			return getValue().toString();
		}

		return content == null ? "" : content;
	}

	/**
	 * Creates a copy of this <code>Cell</code>.
	 * @return The copy of this <code>Cell</code>
	 */
	private Cell cloneThis() {
		return new Cell (this.getSheet(), this.getContent(), this.getBold(), this.getItalic(), this.getForegroundColor(), this.getBackgroundColor());
	}
	
	/**
	 * Posts an edit to undoSupport if <code>oldValue</code> does not equals <code>newValue</code>.
	 * @param oldValue <code>Cell</code> containing the old value
	 * @param newValue <code>Cell</code> containing the new value
	 */
	private void postEdit(Cell oldValue, Cell newValue) {
		if(!oldValue.equals(newValue)) {
			TableCellEdit e = new TableCellEdit(this, oldValue, newValue);
			this.getSheet().getUndoSupport().postEdit(e);
		}
	}
}
