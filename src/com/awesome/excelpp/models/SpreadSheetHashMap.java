package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Set;

public class SpreadSheetHashMap extends SpreadSheet {
	private final short numberOfRows = 1000;
	private final short numberOfCols = 676;
	private HashMap<Integer, Cell> cells;
	
	public SpreadSheetHashMap() {
		super();
		cells = new HashMap<Integer, Cell>();
	}
	
	public void insertCol(int col){
		Set s = cells.keySet();
		
	}
	
	public void setCel(int row, int col, Cell c){
		cells.put(getNumCell(row, col), c);
	}
	
	public void setCel(int row, int col, String contents) {
		cells.put(getNumCell(row, col), new Cell(contents));
	}
	
	public Cell getCel(int row, int col){
		return cells.get(getNumCell(row, col));
	}
	
	public String getValue(int row, int col){
		return cells.get(getNumCell(row, col)).getValue();
	}
	
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
}
