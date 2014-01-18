package com.awesome.excelpp.models;

import java.util.HashMap;

import javax.swing.event.UndoableEditListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.undo.UndoableEditSupport;

import com.awesome.excelpp.writers.Writer;

/**
 * This class represents a <code>SpreadSheet</code>.
 * @author Team Awesome
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

	/**
	 * Returns <code>Cell.class</code> regardless of columnIndex.
	 * @return Cell.class
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Cell.class;
	};

	/**
	 * Returns the number of columns inside this <code>SpreadSheet</code>.
	 * @return The number of columns
	 */
	@Override
	public int getColumnCount() {
		return numberOfCols;
	}

	/**
	 * Returns the number of rows inside this <code>SpreadSheet</code>.
	 * @return The number of rows
	 */
	@Override
	public int getRowCount() {
		return numberOfRows;
	}

	/**
	 * Gets the String value of the <code>Cell</code> with coordinates (row, col) from this <code>SpreadSheet</code>.
	 * @param row Representing y-coordinate
	 * @param col Representing x-coordinate
	 * @return The <code>Cell<code> object at specified row and column
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

	/**
	 * Returns true regardless of row and column.
	 * @return True
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/**
	 * Returns true if this <code>SpreadSheet</code> is empty.
	 * @return True if this <code>SpreadSheet</code> is empty
	 */
	public boolean isEmpty() {
		boolean empty = true;
		for(int index : cells.keySet()) {
			if (!cells.get(index).isEmpty())
				empty = false;
		}
		return empty;
	}
	
	/**
	 * Sets a <code>Cell</code> with coordinates (row, col) in this <code>SpreadSheet</code>.
	 * @param aValue Object to store in the <code>Cell</code> object
	 * @param row Representing y-coordinate
	 * @param col Representing x-coordinate
	 */
	@Override
	public void setValueAt(Object aValue, int row, int col) {
		cells.put(getNumCell(row, col), (Cell)aValue);
		fireTableDataChanged();
	}
	
	/* LISTENERS */
	/**
	 * Adds an <code>UndoableEditListener</code> to this <code>SpreadSheet</code>.
	 * @param l <code>UndoableEditListener</code> to add
	 */
	public void addUndoableEditListener(UndoableEditListener l) {
		this.undoSupport.addUndoableEditListener(l);
	}

	/**
	 * Gets the <code>UndoEditSupport</code> of this <code>SpreadSheet</code>.
	 * @return The <code>UndoEditSupport</code> of this <code>SpreadSheet</code>
	 */
	public UndoableEditSupport getUndoSupport() {
		return undoSupport;
	}
	
	/* OTHER */
	/**
	 * Returns the number the <code>Cell</code> at the specified position inside this <code>SpreadSheet</code>.
	 * @param row The row of this <code>Cell</code>
	 * @param col The column of this <code>Cell</code>
	 * @return The number of this <code>Cell</code>
	 */
	private int getNumCell(int row, int col) {
		return row * numberOfCols + col;
	}
	
	private int[] getXYCell(int nr) {
		int[] temp = {nr % numberOfCols, nr / numberOfCols};
		return temp;
	}
	
	/**
	 * Outputs this <code>SpreadSheet</code> map to a normalized XML file.
	 * @param writer The <code>Writer</code> to use
	 */
	public void write(Writer writer) {
		for (Integer cellnr : cells.keySet()) {
			Cell cell = cells.get(cellnr);
					 
			if (!cell.isEmpty()) {
				int[] xy = getXYCell(cellnr);
				writer.addCell(cell, xy[1] + 1, xy[0] + 1);
			}
		}
		writer.close();
	}
}
