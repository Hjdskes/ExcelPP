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
import javax.swing.event.ChangeEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Class that sets up everything needed for a new tab in the GUI
 */
public class SpreadSheetTable extends JTable implements MouseListener, UndoableEditListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private SpreadSheet sheet;
	private int selectedColumn;
	private int selectedRow;
	private boolean cellSelected;
	private UndoManager undoManager;

	public SpreadSheetTable (SpreadSheet sheet, File file) {
		super(sheet);

		this.file = file;
		this.sheet = sheet;
		this.cellSelected = false;
		undoManager = new UndoManager();
		this.setFillsViewportHeight (true);
		this.setSelectionBackground (new Color(200, 221, 242));
		this.setColumnSelectionAllowed(true);
		this.addMouseListener(this);
		this.setDefaultRenderer(Cell.class, new AwesomeCellRenderer());
		this.setDefaultEditor(Cell.class, new AwesomeCellEditor());

		sheet.addTableModelListener(this);
		sheet.addUndoableEditListener(this);
	}

	public SpreadSheetTable() {
		this(new SpreadSheet(), null);
	}

	/**
	 * Returns the sheet used in this SpreadSheetTable
	 * @return SpreadSheet
	 */
	public final SpreadSheet getSheet() {
		return sheet;
	}

	/**
	 * Returns the currently selected row.
	 * This is different from the regular JTable's method
	 * because this value is kept even when the JTable is
	 * not focused.
	 * @return int	the selected row
	 */
	@Override
	public final int getSelectedRow() {
		return selectedRow;
	}

	/**
	 * Returns the currently selected column.
	 * This is different from the regular JTable's method
	 * because this value is kept even when the JTable is
	 * not focused.
	 * @return int	the selected column
	 */
	@Override
	public final int getSelectedColumn() {
		return selectedColumn;
	}

	/**
	 * Returns true if a Cell has been selected ever since
	 * the application was started.
	 * (Otherwise, text typed into the text field will
	 * always replace Cell A1's content).
	 * @return true	if a Cell has ever been selected
	 */
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

	/**
	 * Sets the bold attribute of the selected Cell.
	 * @param bold	Value to give to the attribute. Can be 0 (plain text), or 1 (bold).
	 */
	public final void setCellBold(int bold) {
		Cell current = (Cell)this.getValueAt(selectedRow, selectedColumn);
		if (current != null)
			current.setBold(bold);
	}

	/**
	 * Sets the italic attribute of the selected Cell.
	 * @param italic	Value to give to the attribute. Can be 0 (plain text), or 2 (italic).
	 */
	public final void setCellItalic(int italic) {
		Cell current = (Cell)this.getValueAt(selectedRow, selectedColumn);
		if (current != null)
			current.setItalic(italic);
	}

	/**
	 * Sets the foreground color of a Cell.
	 * @param cell	Which Cell to change the color for.
	 * @param foreground	The new foreground color.
	 */
	public final void setCellForeground(Cell cell, Color foreground) {
		if(cell != null)
			cell.setForegroundColor(foreground);
	}

	/**
	 * Sets the background color of a Cell.
	 * @param cell	Which Cell to change the color for.
	 * @param background	The new background color.
	 */
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
			boolean formule = false; //of er al een (nog niet afgeronde) formule in het tekstvak staat
			String currentText = GUI.functionFieldGetText();

			if (currentText != null && currentText.length() > 0) {
				if (currentText.charAt(0) == '=' && currentText.charAt(currentText.length() - 1) != ';')
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

	public final void editingStopped(ChangeEvent e) {
		Cell current = (Cell)this.getValueAt(selectedRow, selectedColumn);
		if (current != null)
			GUI.functionFieldSetText(current.getContent());
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
