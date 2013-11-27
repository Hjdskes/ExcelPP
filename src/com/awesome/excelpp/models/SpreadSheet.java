package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Observable;

public class SpreadSheet extends Observable {
	private HashMap<Integer, Cel> cells;
	
	public SpreadSheet(){
		cells = new HashMap<Integer, Cel>();
	}
	
	public Cel getCel(int row, int col){
		cells.get(row*)
	}
	
	public String getValue(int row, int col){
		
	}
}
