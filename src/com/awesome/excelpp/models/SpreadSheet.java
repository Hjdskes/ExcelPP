package com.awesome.excelpp.models;

import java.util.Observable;

public class SpreadSheet extends Observable {
	private Cel[][] cells;
	private short numberOfRows;
	private short numberOfCols;
	
	public SpreadSheet(){
		numberOfRows = 1000;
		numberOfCols = 1000;
		cells = new Cel[numberOfRows][numberOfCols];
	}
	
	public void setCel(int row, int col, Cel c){
		if(row < numberOfRows - 1 && col < numberOfCols - 1){
			cells[row][col] = c;
		}
	}
	
	public Cel getCel(int row, int col){
		return cells[row][col];
	}
	
	public String getValue(int row, int col){
		return cells[row][col].getValue();
	}
}

