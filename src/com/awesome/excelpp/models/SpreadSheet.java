package com.awesome.excelpp.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.TableModel;
import javax.swing.undo.UndoableEditSupport;

/**
 * Class that represents a spreadsheet
 * 
 */
public class SpreadSheet extends Observable implements TableModel {
	private HashMap<Integer, Cell> cells;
	protected final short numberOfRows = 42;
	protected final short numberOfCols = 26;
	private UndoableEditSupport undoSupport;
	
	public SpreadSheet() {
		cells = new HashMap<Integer, Cell>();
		undoSupport = new UndoableEditSupport();
	}
	
	public boolean equals(Object other){
		Boolean res = false;
		if(other instanceof SpreadSheet){
			SpreadSheet that = (SpreadSheet) other;
			if(this.toString().equals(that.toString())){
				res = true;
			}
		}
		return res;
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
		
		//TODO: Put a warning message if a cell will dissapear.
		Set<Integer> s = this.cells.keySet();
		HashMap<Integer, Cell> temp = new HashMap<Integer, Cell>();
		for(Integer key : s){
			int j = key.intValue()%numberOfCols;
			if(j >= col){										//key mod numberOfCols gives us the correct column value.
				if(j + 1 < numberOfCols){					
					temp.put(key + 1, this.cells.get(key));
				}
			}else{
				temp.put(key, this.cells.get(key));
			}
		}
		this.cells = temp;
		for(int i = 0; i < numberOfRows; i++){
			this.setValueAt("werkt", i, col);
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
		
		//TODO: Put a warning message if a cell will dissapear.
		Set<Integer> s = this.cells.keySet();
		HashMap<Integer, Cell> temp = new HashMap<Integer, Cell>();
		for(Integer key : s){
			int j = key.intValue()/numberOfCols;
			if(j >= row){											//key divided by numberOfCols gives us the correct column value, because the
				temp.put(key + numberOfCols, this.cells.get(key));	//result will always be rounded down, since it's not a floating point type.
			}else{
				temp.put(key, this.cells.get(key));
			}
		}
		this.cells = temp;
		for(int i = 0; i < numberOfCols; i++){
			this.setValueAt("werkt", row, i);
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Adds an UndoableEditListener to the SpreadSheet
	 * @param l UndoableEditListener that is added
	 */
	public void addUndoableEditListener(UndoableEditListener l){
		this.undoSupport.addUndoableEditListener(l);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return numberOfCols;
	}

	@Override
	public String getColumnName(int col) {
		char a = (char) (col + 65);			//column + 65 yields the ASCII code, same as Unicode. Cast to char type.
		return Character.toString(a);		//convert to String, return.
	}

	public int getRowCount() {
		return numberOfRows;
	}

	/**
	 * Gets the String value of the Cell with coordinates row, col from this SpreadSheet
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @return			String value of the Cell object at row, col
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Cell c = cells.get(getNumCell(row, col));
		return c == null ? "" : c.getValue(this);
	}
	
	public Cell getCellAt(int row, int col) {
		return cells.get(getNumCell(row, col));
	}

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Sets a Cell with coordinates row, col in this SpreadSheet
	 * @param aValue	String object to store in the Cell object
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 */
	@Override
	public void setValueAt(Object aValue, int row, int col) {
		Object oldValue = this.getValueAt(row, col);
		if (((String)aValue).length() == 0 && cells.get(getNumCell(row, col)) != null){			
			cells.remove(getNumCell(row, col));
			//voor de observers
			setChanged();
			notifyObservers();
		}else if (((String)aValue).length() != 0){
			cells.put(getNumCell(row, col), new Cell((String)aValue));
			//voor de observers
			setChanged();
			notifyObservers();
		}
		Object newValue = this.getValueAt(row, col);
		if(oldValue != null && !oldValue.equals(newValue) || newValue != null && !newValue.equals(oldValue)){
			TableCellEdit e = new TableCellEdit(this, oldValue, newValue, row, col);
			undoSupport.postEdit(e);
		}
	}
	
	/**
	 * Sets a Cell with coordinates row, col in this SpreadSheet
	 * @param aValue	String object to store in the Cell object
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 * @param postEdit when true an edit is posted so that it can be undone/redone
	 */
	public void setValueAt(Object aValue, int row, int col, boolean postEdit) {
		Object oldValue = this.getValueAt(row, col);
		if (((String)aValue).length() == 0 && cells.get(getNumCell(row, col)) != null){			
			cells.remove(getNumCell(row, col));
			//voor de observers
			setChanged();
			notifyObservers();
		}else if (((String)aValue).length() != 0){
			cells.put(getNumCell(row, col), new Cell((String)aValue));
			//voor de observers
			setChanged();
			notifyObservers();
		}
		if(postEdit == true){
		Object newValue = this.getValueAt(row, col);
		if(oldValue != null && !oldValue.equals(newValue) || newValue != null && !newValue.equals(oldValue)){
			TableCellEdit e = new TableCellEdit(this, oldValue, newValue, row, col);
			undoSupport.postEdit(e);
			System.out.println("edit posted"); //test
		}
		}
	}

	
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
	
	public boolean isEmpty() {
		return cells.isEmpty();
	}
	
	/**
	 * -=TEST=-
	 */
	public String toString() {
		String res = "";
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				res += (String)getValueAt(row, col) + "\t";
			}
			res += "\n";
		}
		return res;
	}
	
	/**
	 * Outputs the assigned spreadsheet map to a normalized XML file
	 * @param dest
	 * @throws FileNotFoundException
	 */
	public void toXML(File file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		String res = "<?xml version=\"1.0\"?>\n";
		res += "<SPREADSHEET>\n";
		
		for (int row = 0; row < numberOfRows; row++) {
			for (int col = 0; col < numberOfCols; col++) {
				if((String)getValueAt(row, col) != "") {
					String temp = (String)getValueAt(row, col);
					
					/* Normalization */
					temp = temp.replace("\n", "");
					temp = temp.replace("\t", "");
					temp = temp.replace("\r", "");
					int store_row = row + 1;
					int store_col = col + 1;
					
					res +="<CELL row=\"" + store_row  + "\" column=\"" + store_col + "\">" + temp + "</CELL>\n";
				}
			}
		}
		res += "</SPREADSHEET>\n";
						
		pw.print(res);
		pw.flush();
		pw.close();
	}
}
