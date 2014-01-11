package com.awesome.excelpp.writers;

import com.awesome.excelpp.models.Cell;

public interface Writer {
	public void addCell(Cell cell, int row, int col, int bold);
	public void close();
}
