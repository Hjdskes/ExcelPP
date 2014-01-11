package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.gui.GUI;

import java.io.File;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Class that sets up everything needed for a new tab in the GUI
 */
public class SpreadSheetTable extends JTable implements MouseListener, UndoableEditListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private int selectedColumn;
	private int selectedRow;
	private UndoManager undoManager;

	public SpreadSheetTable (SpreadSheet sheet, File file) {
		super(sheet);
		this.file = file;
		undoManager = new UndoManager();
		this.setFillsViewportHeight (true);
		this.setSelectionBackground (new Color(200, 221, 242));
		this.setColumnSelectionAllowed(true);
		this.addMouseListener(this);
		this.setDefaultRenderer(String.class, new AwesomeCellRenderer());

		sheet.addTableModelListener(this);
		//sheet.addUndoableEditListener(this);
	}
	
	public SpreadSheetTable() {
		this(new SpreadSheet(), null);
	}

	/**
	 * Returns the currently opened file
	 * @return File
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * Sets the currently opened file
	 * @param void
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Returns this tab's UndoManager
	 * @return UndoManager
	 */
	public final UndoManager getUndoManager(){
		return undoManager;
	}

	public final void setCellBold(int bold) {
		Cell current = (Cell)this.getValueAt(selectedRow, selectedColumn);
		if (current != null)
			current.setBold(bold);
	}

	public final void setCellItalic(int italic) {
		Cell current = (Cell)this.getValueAt(selectedRow, selectedColumn);
		if (current != null)
			current.setItalic(italic);
	}

	public final void setCellForeground(Cell cell, Color foreground) {
		if(cell != null)
			cell.setForegroundColor(foreground);
	}

	public final void setCellBackground(Cell cell, Color background) {
		if(cell != null)
			cell.setBackgroundColor(background);
	}

	/*
	 * Listens for all mouseClicked events emitted by the elements of the tab
	 * @return void
	 */
	public final void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(this)) {
			Point p = e.getPoint();
			selectedRow = this.rowAtPoint(p);
			selectedColumn = this.columnAtPoint(p);
			this.changeSelection(selectedRow, selectedColumn, false, false);

			String text = null;
			if (this.getSelectedColumnCount() == 1 && this.getSelectedRowCount() == 1) {
				if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON1)
					text = GUI.functionFieldGetText() + "(" + this.getValueAt(this.getSelectedRow(), this.getSelectedColumn()) + ")";
				else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3)
					text = GUI.functionFieldGetText() + "(" + this.getColumnName(this.getSelectedColumn()) + (this.getSelectedRow() + 1) + ")";
				else { //ToDo: moet hier nog een specifieke knop voor worden ingesteld, ipv alle mousebuttons?
					Cell activeCell = (Cell)this.getValueAt(selectedRow, selectedColumn);
					text = activeCell == null ? "" : activeCell.getContent();
				}
				GUI.functionFieldSetText(text);
			} else {
				text = GUI.functionFieldGetText();
				int selectedRows[] = this.getSelectedRows();
				int selectedColumns[] = this.getSelectedColumns();
				if (e.isAltDown() && e.getButton() == MouseEvent.BUTTON1) { //ToDo: wordt overridden door bovenstaande, maar werkt wel als deze wordt veranderd naar bijv BUTTON2 (middlemouse button)
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							text += (String) this.getValueAt(selectedRows[j], selectedColumns[i]) + ",";
						}
					}
				} else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3) {
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							text += "(" + this.getColumnName(selectedColumns[i]) + (selectedRows[j] + 1) + "),";
						}
					}
				}
				text = text == null || text.length() == 0 ? "" : text.substring(0, text.length()-1) + ")"; //laatste komma vervangen door een afsluitend haakje
				GUI.functionFieldSetText(text);
			}
		}
	}

	@Override
	/**
	 * Listen for undoable edits and adds them to the undoManager
	 */
	public final void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
