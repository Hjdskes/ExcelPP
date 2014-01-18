package com.awesome.excelpp.writers;

import com.awesome.excelpp.models.Cell;

/**
 * The Writer interface is used by <code>SpreadSheet</code> to write files to disk
 * <p>All <code>Writer</code> classes for all supported file types implement
 * this interface to offer <code>SpreadSheet</code> a uniform way of writing files,
 * regardless of file type.
 * This way the GUI can decide what kind of <code>Writer</code> to pass to
 * <code>SpreadSheet</code>, allowing the user to select a file type at runtime.</p>
 * @author Team Awesome
 */
public interface Writer {
	/**
	 * Adds a <code>Cell</code> to this <code>Writer</code>'s output.
	 * @param cell	The cell that will be appended
	 * @param row	The y-coordinate of this cell
	 * @param col	The x-coordinate of this cell
	 */
	public void addCell(Cell cell, int row, int col);
	
	/**
	 * Flushes the <code>Writer</code>'s output and closes the <code>Writer</code>'s file.
	 */
	public void close();
}
