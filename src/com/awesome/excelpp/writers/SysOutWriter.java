package com.awesome.excelpp.writers;

import java.io.PrintWriter;

import com.awesome.excelpp.models.Cell;

/**
 * This class 
 * @author Team Awesome
 */
public class SysOutWriter implements Writer {
	PrintWriter pw;

	/**
	 * Constructs a new <code>SysOutWriter</code>.
	 */
	public SysOutWriter() {
		pw = new PrintWriter(System.out);
	}

	@Override
	public void addCell(Cell cell, int row, int col, int bold, int italic, String fontColor, String bgColor) {
		pw.write("row=" + row + ", col=" + col + ", content=" + cell.getContent() + "\n");
	}
	
	public void close() {
		pw.flush();
		pw.close();
	}
}
