package com.awesome.excelpp.models;

import java.util.Observable;

public class SpreadSheet extends Observable {
	//protected final short numberOfRows = 1000;
	//protected final short numberOfCols = 676;
	protected final short numberOfRows = 10;
	protected final short numberOfCols = 10;
	private Cell[][] cells;
	
	/**
	 * Constructs an empty SpreadSheet model
	 */
	public SpreadSheet(){
		cells = new Cell[numberOfRows][numberOfCols];
	}
	
	/**
	 * Sets a Cell with coordinates row, col in this SpreadSheet
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @param c			Cell object
	 */
	public void setCell(int row, int col, Cell c){
		if(row < numberOfRows && col < numberOfCols){
			cells[row][col] = c;
		}
		setChanged();
	}
	
	/**
	 * Gets a Cell with coordinates row, col from this SpreadSheet
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @return			Cell object at row, col
	 */
	public Cell getCell(int row, int col){
		return cells[row][col];
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
