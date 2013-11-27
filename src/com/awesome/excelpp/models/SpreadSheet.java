package com.awesome.excelpp.models;

import java.util.Observable;

public class SpreadSheet extends Observable {
	private Cell[][] cells;
	private short numberOfRows;
	private short numberOfCols;
	
	public SpreadSheet(){
		numberOfRows = 1000;
		numberOfCols = 1000;
		cells = new Cell[numberOfRows][numberOfCols];
	}
	
	public void setCell(int row, int col, Cell c){
		if(row < numberOfRows - 1 && col < numberOfCols - 1){
			cells[row][col] = c;
		}
	}
	
	public void setCell(int row, int col, String contents) {
		setCell(row, col, new Cell(contents));
	}
	
	public Cell getCell(int row, int col){
		return cells[row][col];
	}
	
	public String getValue(int row, int col){
		return cells[row][col].getValue();
	}
}

