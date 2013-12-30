package com.awesome.excelpp.models;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Class that represents an edit in a SpreadSheet
 *
 */
public class TableCellEdit extends AbstractUndoableEdit{
	private SpreadSheet sheet;
	private Object oldValue;
	private Object newValue;
	private int row;
	private int col;
	
	public TableCellEdit(SpreadSheet sheet, Object oldValue, Object newValue, int row, int col){
		this.sheet = sheet;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.row  = row;
		this.col = col;
	}
	
	public void undo() throws CannotUndoException{
		super.undo();
		sheet.setValueAt(oldValue, row, col, false);
		
	}
	
	public void redo() throws CannotRedoException{
		super.redo();
		sheet.setValueAt(newValue, row, col, false);
	}
}
