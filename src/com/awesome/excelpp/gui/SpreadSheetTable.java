package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.gui.GUI;

import java.io.File;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * This class sets up the table as seen in the main GUI.
 * @author Team Awesome
 */
public class SpreadSheetTable extends JTable implements MouseListener, UndoableEditListener {
	private static final long serialVersionUID = 1L;
	private File file;
	private SpreadSheet sheet;
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
	 * Returns the <code>SpreadSheet</code> used in this <code>SpreadSheetTable</code>.
	 * @return The currently contained <code>SpreadSheet</code>
	 */
	public final SpreadSheet getSheet() {
		return sheet;
	}

	/**
	 * Returns true if a <code>Cell</code> has been selected ever since
	 * the application was started.
	 * (Otherwise, text typed into the text field will
	 * always replace <code>Cell</code> A1's content).
	 * @return True	if a <code>Cell</code> has ever been selected
	 */
	public final boolean getCellSelected() {
		return cellSelected;
	}

	/**
	 * Returns the currently opened <code>File</code>.
	 * @return The currently opened <code>File</code>/
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * Sets the currently opened <code>File</code>.
	 * @param file The new <code>File</code> to be opened
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Returns this tab's <code>UndoManager</code>.
	 * @return This tab's <code>UndoManager</code>
	 */
	public final UndoManager getUndoManager(){
		return undoManager;
	}

	/**
	 * Listens for all <code>MouseClicked</code> events emitted by the elements of this <code>SpreadSheetTable</code>.
	 */
	public final void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(this)) {
			cellSelected = true;
			int[] selectedColumns = this.getSelectedColumns();
			int[] selectedRows = this.getSelectedRows();

			String text = null;
			int button = e.getButton();
			boolean formule = false; //of er al een (nog niet afgeronde) formule in het tekstvak staat
			String currentText = GUI.functionFieldGetText();

			if (currentText != null && currentText.length() > 0) {
				if (currentText.charAt(0) == '=' && currentText.charAt(currentText.length() - 1) != ';')
					formule = true;
			}

			Cell activeCell = null;
			if(button == MouseEvent.BUTTON1) {
				for (int i = 0; i < selectedColumns.length; i++) {
					for (int j = 0; j < selectedRows.length; j++) {
						activeCell = (Cell)this.getValueAt(selectedRows[j], selectedColumns[i]);
						if (formule)
							text += "(" + activeCell.getContent() + "),";
						else
							text += activeCell.getContent();
					}
				}
			} else if(button == MouseEvent.BUTTON3) {
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

			/*if (this.getSelectedColumnCount() == 1 && this.getSelectedRowCount() == 1) {
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
				int[] selectedRows = this.getSelectedRows();
				int[] selectedColumns = this.getSelectedColumns();
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
			}*/
		}
	}

	/**
	 * Listen for undoable edits and adds them to the <code>UndoManager</code>.
	 */
	@Override
	public final void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
	}

	/**
	 * Listens for all editingStopped events emitted by this <code>SpreadSheetTable's Cells</code>.
	 */
	public final void editingStopped(ChangeEvent e) {
		int row = this.getSelectedRow();
		int column = this.getSelectedColumn();
		Cell current = (Cell)this.getValueAt(row, column);
		super.editingStopped(e);
		if (current != null)
			GUI.functionFieldSetText(current.getContent());
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
