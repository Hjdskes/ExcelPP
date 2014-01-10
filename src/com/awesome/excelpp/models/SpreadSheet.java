package com.awesome.excelpp.models;

import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.event.UndoableEditListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.undo.UndoableEditSupport;

import com.awesome.excelpp.writers.Writer;

/**
 * Class that represents a spreadsheet
 * 
 */
public class SpreadSheet extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	protected final short numberOfRows = 42;
	protected final short numberOfCols = 26;
	
	private HashMap<Integer, Cell> cells;
	private UndoableEditSupport undoSupport;
	
	public SpreadSheet() {
		super();
		cells = new HashMap<Integer, Cell>();
		undoSupport = new UndoableEditSupport();
	}

	@Override
	public int getColumnCount() {
		return numberOfCols;
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
		
		if (oldValue != null && oldValue.getContent().length() > 0 && !oldValue.equals(newValue)
				|| newValue.getContent() != null && newValue.getContent().length() > 0 && !newValue.equals(oldValue)) {
			TableCellEdit e = new TableCellEdit(this, oldValue, newValue, row, col);
			undoSupport.postEdit(e);
		}
		
		fireTableDataChanged();
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
		
		fireTableDataChanged();
	}
	
	/* LISTENERS */	
	/**
	 * Adds an UndoableEditListener to the SpreadSheet
	 * @param l UndoableEditListener that is added
	 */
	public void addUndoableEditListener(UndoableEditListener l){
		this.undoSupport.addUndoableEditListener(l);
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
	public void write(Writer writer) throws FileNotFoundException {
		for (Integer cell : cells.keySet()) {
			int[] xy = getXYCell(cell);
			writer.addCell(cells.get(cell), xy[0], xy[1]);
		}
		writer.close();
	}
}
