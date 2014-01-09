package com.awesome.excelpp.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.event.TableModelListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.TableModel;
import javax.swing.undo.UndoableEditSupport;

/**
 * Class that represents a spreadsheet
 * 
 */
public class SpreadSheet extends Observable implements TableModel {
	protected final short numberOfRows = 42;
	protected final short numberOfCols = 26;
	
	private HashMap<Integer, Cell> cells;
	private UndoableEditSupport undoSupport;
	
	public SpreadSheet() {
		cells = new HashMap<Integer, Cell>();
		undoSupport = new UndoableEditSupport();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return numberOfCols;
	}
	
	@Override
	public String getColumnName(int col) {
		char a = (char) (col + 65);			//column + 65 yields the ASCII code, same as Unicode. Cast to char type.
		return Character.toString(a);		//convert to String, return.
	}
	
	@Override
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
		return cells.get(getNumCell(row, col));
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	public boolean isEmpty() {
		return cells.isEmpty();
	}

	/**
	 * Sets a Cell with coordinates row, col in this SpreadSheet
	 * @param aValue	String object to store in the Cell object
	 * @param row		int representing x-coordinate
	 * @param col		int representing y-coordinate
	 */
	@Override
	public void setValueAt(Object aValue, int row, int col) {
		Cell oldValue = (Cell)this.getValueAt(row, col);
		Cell newValue = new Cell(this, (String)aValue);
		
		if (newValue.getContent() == null
				|| newValue.getContent().length() == 0) {
			cells.remove(getNumCell(row, col));
		} else {
			cells.put(getNumCell(row, col), newValue);
		}
		
		//voor de observers
		setChanged();
		notifyObservers();
		
		if (oldValue != null && oldValue.getContent().length() > 0 && !oldValue.equals(newValue)
				|| newValue.getContent() != null && newValue.getContent().length() > 0 && !newValue.equals(oldValue)) {
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
		Cell newValue = (Cell)aValue;
		
		if (newValue == null || newValue.getContent() == null || newValue.getContent().length() == 0){			
			cells.remove(getNumCell(row, col));
		} else {
			cells.put(getNumCell(row, col), (Cell)aValue);
		}
		
		//voor de observers
		setChanged();
		notifyObservers();
	}
	
	/* LISTENERS */
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
	
	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
	}
	
	/* OTHER */
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
	
	private int[] getXYCell(int nr) {
		int[] temp = {nr % numberOfCols, nr / numberOfCols};
		return temp;
	}
	
	/**
	 * Outputs the assigned spreadsheet map to a normalized XML file
	 * @param file		the output file
	 * @throws FileNotFoundException
	 */
	public void toXML(File file) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		String res = "<?xml version=\"1.0\"?>\n";
		res += "<SPREADSHEET>\n";
		
		for (Integer cell : cells.keySet()) {
			int[] xy = getXYCell(cell);
			res +="<CELL row=\"" + xy[0] + "\" column=\"" + xy[1] + "\">" + cells.get(cell).getContent() + "</CELL>\n";
		}
		res += "</SPREADSHEET>\n";
		
		System.out.println("debug\n" + res);
						
		pw.print(res);
		pw.flush();
		pw.close();
	}
}
