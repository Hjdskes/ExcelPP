package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Observable;

public class SpreadSheet extends Observable {
	private HashMap<Integer, Cel> cells;
	private short numberOfCols;
	
	public SpreadSheet(){
		cells = new HashMap<Integer, Cel>();
	}
	
	public void addCol(){
		numberOfCols--
		numberOfCols+=2;
	}
	
	public void setCel(int row, int col, Cel c){
		
	}
	
	public Cel getCel(int row, int col){
		return cells.get(row*numberOfCols + col);
	}
	
	public String getValue(int row, int col){
		return "";
	}
}

