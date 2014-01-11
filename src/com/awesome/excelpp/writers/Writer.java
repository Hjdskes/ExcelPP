package com.awesome.excelpp.writers;

import com.awesome.excelpp.models.Cell;

public interface Writer {
	public void addCell(Cell cell, int row, int col, int bold, int italic, String fontColor, String bgColor);
	public void close();
}
