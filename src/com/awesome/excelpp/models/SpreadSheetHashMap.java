package com.awesome.excelpp.models;

import java.util.HashMap;

public class SpreadSheetHashMap extends SpreadSheet {
	private final short numberOfRows = 1000;
	private final short numberOfCols = 676;
	private HashMap<Integer, Cel> cells;
	
	public SpreadSheetHashMap() {
		super();
		cells = new HashMap<Integer, Cel>();
	}
	
	public void setCel(int row, int col, Cel c){
		int key = row * numberOfCols + col;
		cells.put(key, c);
	}
	
	public Cel getCel(int row, int col){
		int key = row * numberOfCols + col;
		return cells.get(key);
	}
	
	public String getValue(int row, int col){
		int key = row * numberOfCols + col;
		return cells.get(key).getValue();
	}
}
