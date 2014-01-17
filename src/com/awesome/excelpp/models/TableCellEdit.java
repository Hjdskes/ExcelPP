package com.awesome.excelpp.models;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * This class represents an edit in a <code>SpreadSheet</code>.
 * @author Team Awesome.
 */
public class TableCellEdit extends AbstractUndoableEdit{
	private static final long serialVersionUID = 1L;
	private Cell edited;
	private Cell oldValue;
	private Cell newValue;

	/**
	 * Constructs a new edit to be added to the undoManager.
	 * @param edited The cell that was changed.
	 * @param oldValue The previous value of this cell. (The value that will be set after an undo)
	 * @param newValue The new value of this cell. (The value that will be set after a redo)
	 */
	public TableCellEdit(Cell edited, Cell oldValue, Cell newValue){
		this.edited = edited;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Undoes an edit.
	 * @throws CannotUndoException
	 * @return void
	 */
	public void undo() throws CannotUndoException{
		super.undo();
		edited.setContent(oldValue.getContent(), false);
		edited.setBold(oldValue.getBold(), false);
		edited.setItalic(oldValue.getItalic(), false);
		edited.setForegroundColor(oldValue.getForegroundColor(), false);
		edited.setBackgroundColor(oldValue.getBackgroundColor(), false);
		edited.getSheet().fireTableDataChanged();
	}

	/**
	 * Redoes an edit.
	 * @throws CannotUndoException
	 * @return void
	 */
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
