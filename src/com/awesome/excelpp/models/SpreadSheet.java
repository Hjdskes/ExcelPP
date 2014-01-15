package com.awesome.excelpp.models;

import java.awt.Color;
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
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	};

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
		Cell cell = cells.get(getNumCell(row, col));
		if (cell == null) {
			cell = new Cell(this, null);
			cells.put(getNumCell(row, col), cell);
		}
		return cell;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/**
	 * Returns true if this sheet is empty
	 * @return boolean	true if this sheet is empty
	 */
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
		
		if (!oldValue.equals(newValue)) {
			cells.put(getNumCell(row, col), newValue);
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
		if(postEdit == true){
			setValueAt(aValue, row, col);
		}
		
		else{
			cells.put(getNumCell(row, col), (Cell)aValue);
			fireTableDataChanged();
		}
	}
	
	/**
	 * Sets a Cell with all it's styles from the XML Parser 
	 * @param aValue
	 * @param row
	 * @param col
	 * @param bold
	 * @param italic
	 * @param foregroundColor
	 * @param backgroundColor
	 */
	public void setValueAt(Object aValue, int row, int col, int bold, int italic, Color foregroundColor, Color backgroundColor) {
		Cell newValue = new Cell(this, (String)aValue, bold, italic, foregroundColor, backgroundColor);
		cells.put(getNumCell(row, col), newValue);
	}
	
	/* LISTENERS */	
	/**
	 * Adds an UndoableEditListener to the SpreadSheet
	 * @param l UndoableEditListener that is added
	 */
	public void addUndoableEditListener(UndoableEditListener l){
		this.undoSupport.addUndoableEditListener(l);
	}
	
	public UndoableEditSupport getUndoSupport(){
		return undoSupport;
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
	 */
	public void write(Writer writer) {
		for (Integer cellnr : cells.keySet()) {
			Cell cell = cells.get(cellnr);
					 
			if (!cell.isEmpty()) {
				int[] xy = getXYCell(cellnr);
				writer.addCell(cell, xy[1] + 1, xy[0] + 1, cell.getBold(), cell.getItalic(), cell.getForegroundColorHex(), cell.getBackgroundColorHex());
			}
		}
		writer.close();
	}
}
