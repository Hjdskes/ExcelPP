package com.awesome.excelpp.models;

import java.util.HashMap;
import java.util.Set;

public class SpreadSheet {
	private HashMap<Integer, Cell> cells;
	protected final short numberOfRows = 10;
	protected final short numberOfCols = 10;
	
	public SpreadSheet() {
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
			int j = key.intValue()%numberOfCols;
			if(j >= col){					//key mod numberOfCols gives us the correct column value.
				if(j + 1 < numberOfCols){					
					temp.put(key + 1, this.cells.get(key));
				}
			}else{
				temp.put(key, this.cells.get(key));
			}
		}
		this.cells = temp;
		for(int i = 0; i < numberOfRows; i++){
			this.setCell(i, col, new Cell("werkt"));
		}
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
			}else{
				temp.put(key, this.cells.get(key));
			}
		}
		this.cells = temp;
		for(int i = 0; i < numberOfCols; i++){
			this.setCell(row, i, new Cell("werkt"));
		}
	}
	
	public void setCell(int row, int col, Cell c){
		cells.put(getNumCell(row, col), c);
	}
	
	/**HELPER FUNCTION
	 * Sets a Cell with coordinates row, col in this SpreadSheet
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @param contentes	String object to store in the Cell object
	 */
	public void setCell(int row, int col, String contents) {
		setCell(row, col, new Cell(contents));
	}
	
	public Cell getCell(int row, int col){
		return cells.get(getNumCell(row, col));
	}
	
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
	
	
	/**HELPER FUNCTION
	 * Gets the String value of the Cell with coordinates row, col from this SpreadSheet
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @return			String value of the Cell object at row, col
	 */
	public String getValue(int row, int col){
		Cell c = getCell(row, col);
		return c == null ? "" : c.getValue();
	}
	
	/**
	 * -=TEST=-
	 */
	public void fillSheet() {
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				setCell(row, col, row + "," + col);
			}
		}
	}
	
	/**
	 * -=TEST=-
	 */
	public String toString() {
		String res = "";
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				res += getValue(row, col) + "\t";
			}
			res += "\n";
		}
		return res;
	}
}
