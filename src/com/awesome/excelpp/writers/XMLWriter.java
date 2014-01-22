package com.awesome.excelpp.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.awesome.excelpp.models.Cell;

/**
 * The <code>XMLWriter</code> class is used by <code>SpreadSheet</code> to write XML files to disk.
 * @author Team Awesome
 */
public class XMLWriter implements Writer {
	PrintWriter pw;
	
	/**
	 * Creates an <code>XMLWriter</code> class which will write to the specified <code>File</code>.
	 * @param file The <code>File</code> to write
	 * @throws FileNotFoundException
	 */
	public XMLWriter(File file) throws FileNotFoundException {
		pw = new PrintWriter(file);
		pw.write("<?xml version=\"1.0\"?>\n");
		pw.write("<SPREADSHEET>\n");
	}
	
	@Override
	public void addCell(Cell cell, int row, int col) {
			pw.write("<CELL row=\"" + row + "\"" +
						" column=\"" + col + "\"" +
						" style=\"" +
							"bold:" + cell.getBold() + ";" +
							"italic:" + cell.getItalic() + ";" +
							"fontColor:" + cell.getForegroundColorHex() + ";" +
							"bgColor:" + cell.getBackgroundColorHex() +
						"\"" +
						">" + cell.getContent() + "</CELL>\n");
		}

	@Override
	public void close() {
		pw.write("</SPREADSHEET>");
		pw.flush();
		pw.close();
	}
}
