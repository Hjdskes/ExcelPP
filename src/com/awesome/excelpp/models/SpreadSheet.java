package com.awesome.excelpp.models;

import java.util.Observable;

public class SpreadSheet extends Observable {
	private Cel[][] cells;
	private short numberOfRows;
	private short numberOfCols;
	
	public SpreadSheet(){
<<<<<<< HEAD
		cells = new HashMap<Integer, Cel>();
	}
	
	public void addCol() {
		numberOfCols--
		numberOfCols+=2;
=======
		numberOfRows = 1000;
		numberOfCols = 1000;
		cells = new Cel[numberOfRows][numberOfCols];
>>>>>>> add300c4299c69a61db453107a9c28a38cb25528
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

