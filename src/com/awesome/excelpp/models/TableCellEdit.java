package com.awesome.excelpp.models;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Class that represents an edit in a SpreadSheet
 *
 */
public class TableCellEdit extends AbstractUndoableEdit{
	private static final long serialVersionUID = 1L;
	private Cell edited;
	private Cell oldValue;
	private Cell newValue;
	
	
	public TableCellEdit(Cell edited, Cell oldValue, Cell newValue){
		this.edited = edited;
		this.oldValue = oldValue;
		this.newValue = newValue;
		
	}
	
	public void undo() throws CannotUndoException{
		super.undo();
		edited.setContent(oldValue.getContent(), false);
		edited.setBold(oldValue.getBold(), false);
		edited.setItalic(oldValue.getItalic(), false);
		edited.setForegroundColor(oldValue.getForegroundColor(), false);
		edited.setBackgroundColor(oldValue.getBackgroundColor(), false);
		edited.getSheet().fireTableDataChanged();
	}
	
	public void redo() throws CannotRedoException{
		super.redo();
		edited.setContent(newValue.getContent(), false);
		edited.setBold(newValue.getBold(), false);
		edited.setItalic(newValue.getItalic(), false);
		edited.setForegroundColor(newValue.getForegroundColor(), false);
		edited.setBackgroundColor(newValue.getBackgroundColor(), false);
		edited.getSheet().fireTableDataChanged();
	}
}
