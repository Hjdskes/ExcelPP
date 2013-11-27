package com.awesome.excelpp.models;

import java.util.HashMap;

public class SpreadSheetHashMap extends SpreadSheet {
	private HashMap<Integer, Cel> cells;
	private short numberOfRows;
	private short numberOfCols;
	
	public SpreadSheetHashMap() {
		super();
		numberOfRows = 1000;
		numberOfCols = 676;
		cells = new HashMap<Integer, Cel>();
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
