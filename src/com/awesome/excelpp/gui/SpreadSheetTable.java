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
	private boolean cellSelected;
	private UndoManager undoManager;

	public SpreadSheetTable (SpreadSheet sheet, File file) {
		super(sheet);
		this.file = file;
		this.cellSelected = false;
		undoManager = new UndoManager();
		this.setFillsViewportHeight (true);
		this.setSelectionBackground (new Color(200, 221, 242));
		this.setColumnSelectionAllowed(true);
		this.addMouseListener(this);
		this.setDefaultRenderer(String.class, new AwesomeCellRenderer());

		sheet.addTableModelListener(this);
		sheet.addUndoableEditListener(this);
	}
	
	public SpreadSheetTable() {
		this(new SpreadSheet(), null);
	}

	@Override
	public final int getSelectedRow() {
		return selectedRow;
	}

	@Override
	public final int getSelectedColumn() {
		return selectedColumn;
	}

	public final boolean getCellSelected() {
		return cellSelected;
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

	/**
	 * Listens for all mouseClicked events emitted by the elements of the tab
	 * @return void
	 */
	public final void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(this)) {
			cellSelected = true;
			Point p = e.getPoint();
			selectedRow = this.rowAtPoint(p);
			selectedColumn = this.columnAtPoint(p);
			this.changeSelection(selectedRow, selectedColumn, false, false);

			String text = null;
			int button = e.getButton();
			boolean alt = e.isAltDown();
			boolean formule = false; //of er al een formule in het tekstvak staat
			String currentText = GUI.functionFieldGetText();

			if (currentText != null && currentText.length() > 0) {
				if (currentText.charAt(0) == '=')
					formule = true;
			}

			if (this.getSelectedColumnCount() == 1 && this.getSelectedRowCount() == 1) {
				if(button == MouseEvent.BUTTON3) {
					if (formule)
						text = currentText + "(" + this.getColumnName(selectedColumn) + (selectedRow + 1) + ")";
					else
						text = this.getColumnName(selectedColumn) + (selectedRow + 1);
				} else {
					Cell activeCell = (Cell)this.getValueAt(selectedRow, selectedColumn);
					if (activeCell != null) {
						if(formule)
							text = currentText + "(" + activeCell.getContent() + ")";
						else
							text = activeCell.getContent();
					}
				}
				if (text != null && text.length() > 0)
					GUI.functionFieldSetText(text);
			} else { //ToDo: wordt overridden door bovenstaande, maar werkt wel als deze wordt veranderd naar bijv BUTTON2 (middlemouse button)
				int selectedRows[] = this.getSelectedRows();
				int selectedColumns[] = this.getSelectedColumns();
				if (alt && button == MouseEvent.BUTTON1) {
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							Cell activeCell = (Cell)this.getValueAt(selectedRows[j], selectedColumns[i]);
							if (formule)
								text += "(" + activeCell.getContent() + "),";
							else
								text += activeCell.getContent();
						}
					}
				} else if(alt && button == MouseEvent.BUTTON3) {
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							if (formule)
								text += "(" + this.getColumnName(selectedColumns[i]) + (selectedRows[j] + 1) + "),";
							else
								text += this.getColumnName(selectedColumns[i]) + (selectedRows[j] + 1);
						}
					}
				}
				if (formule)
					text = currentText + text;
				text = text == null || text.length() == 0 ? "" : text.substring(0, text.length()-1) + ")"; //laatste komma vervangen door een afsluitend haakje
				GUI.functionFieldSetText(text);
			}
		}
	}

	/**
	 * Listen for undoable edits and adds them to the undoManager
	 */
	@Override
	public final void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
