package com.awesome.excelpp.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.awesome.excelpp.models.Cell;

public class XMLWriter implements Writer {
	PrintWriter pw;
	
	public XMLWriter(File file) throws FileNotFoundException {
		pw = new PrintWriter(file);
		pw.write("<?xml version=\"1.0\"?>\n");
		pw.write("<SPREADSHEET>\n");
	}
	
	@Override
	public void addCell(Cell cell, int row, int col, int bold) {
		pw.write("<CELL row=\"" + row + "\" column=\"" + col + " style=\"bold:" + bold +  ";\">" + cell.getContent() + "</CELL>\n");

	}

	public void close() {
		pw.write("</SPREADSHEET>");
		pw.flush();
		pw.close();
	}
}
