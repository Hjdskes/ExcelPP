package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Set;

public class SpreadSheetHashMap extends SpreadSheet {
	private HashMap<Integer, Cell> cells;
	
	public SpreadSheetHashMap() {
		super();
		cells = new HashMap<Integer, Cell>();
	}
	
	public void insertCol(int col){
		Set s = cells.keySet();
	}
	
	public void setCell(int row, int col, Cell c){
		cells.put(getNumCell(row, col), c);
	}
	
	public Cell getCell(int row, int col){
		return cells.get(getNumCell(row, col));
	}
	
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
}
