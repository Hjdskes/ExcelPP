package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Set;

public class SpreadSheetHashMap extends SpreadSheet {
	private HashMap<Integer, Cell> cells;
	
	public SpreadSheetHashMap() {
		super();
		cells = new HashMap<Integer, Cell>();
	}
	
	/**Inserts a column at the specified column value, and moves each column with
	 * column value greater than or equal to the specified column value to the right.
	 * 
	 * @param col - the column value at which a column will be inserted.
	 */
	public void insertCol(int col){
		if(col >= this.numberOfCols){
			return;
		}
		Set<Integer> s = this.cells.keySet();
		HashMap<Integer, Cell> temp = new HashMap<Integer, Cell>();
		for(Integer key : s){
			if(key.intValue()%numberOfCols >= col){					//key mod numberOfCols gives us the correct column value.
				temp.put(key + 1, this.cells.get(key));
			}
		}
		this.cells = temp;
	}
	
	/**Inserts a row at the specified row value, and moves each row with
	 * row value greater than or equal to the specified row value downwards.
	 * 
	 * @param row - the row value at which a row will be inserted.
	 */
	public void insertRow(int row){
		if(row >= this.numberOfRows){
			return;
		}
		Set<Integer> s = this.cells.keySet();
		HashMap<Integer, Cell> temp = new HashMap<Integer, Cell>();
		for(Integer key : s){
			if(key.intValue()/numberOfCols >= row){					//key divided by numberOfCols gives us the correct column value, because the
				temp.put(key + numberOfCols, this.cells.get(key));	//result will always be rounded down, since it's not a floating point type.
			}
		}
		this.cells = temp;
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
