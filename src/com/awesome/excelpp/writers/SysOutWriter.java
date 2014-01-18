package com.awesome.excelpp.writers;

import java.io.PrintWriter;

import com.awesome.excelpp.models.Cell;

/**
 * The <code>SysOutWriter</code> class is used by <code>SpreadSheet</code> to write to stdout.
 * @author Team Awesome
 */
public class SysOutWriter implements Writer {
	PrintWriter pw;

	/**
	 * Creates a <code>SysOutWriter</code> class which will write to stdout.
	 */
	public SysOutWriter() {
		pw = new PrintWriter(System.out);
	}

	@Override
	public void addCell(Cell cell, int row, int col) {
		pw.write("row=" + row + ", col=" + col + ", content=" + cell.getContent() + "\n");
	}
	
	@Override
	public void close() {
		pw.flush();
		pw.close();
	}
}
