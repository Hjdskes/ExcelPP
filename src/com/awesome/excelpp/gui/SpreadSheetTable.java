package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.readers.XML;
import com.awesome.excelpp.writers.XMLWriter;
import com.awesome.excelpp.gui.GUI;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import org.w3c.dom.Document;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;

/**
 * Class that sets up everything needed for a new tab in the GUI
 */
public class SpreadSheetTable implements MouseListener, Observer, UndoableEditListener {
	private JScrollPane scrollPane;
	private JTable tabel, rowTabel;
	private SpreadSheet sheet;
	private File file = null;
	private int selectedColumn;
	private int selectedRow;
	private UndoManager undoManager;
	private boolean cellSelected;

	public SpreadSheetTable () throws IOException {
		sheet = new SpreadSheet();
		tabel = new JTable(sheet);
		undoManager = new UndoManager();
		cellSelected = false;
		tabel.setFillsViewportHeight (true);
		tabel.setSelectionBackground (new Color(200, 221, 242));
		tabel.setColumnSelectionAllowed(true);
		tabel.setDefaultRenderer(String.class, new AwesomeCellRenderer());
		rowTabel = new RowNumberTable(tabel);
		
		scrollPane = new JScrollPane(tabel);
		scrollPane.setRowHeaderView(rowTabel);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTabel.getTableHeader());

		tabel.addMouseListener(this);
		sheet.addObserver(this);
		sheet.addUndoableEditListener(this);
		
		file = File.createTempFile("excelpp_temp", ".xml");
	}

	/**
	 * Returns the JScrollPane of this tab
	 * @return JScrollPane
	 */
	public final JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Returns the JTable of this tab
	 * @return JTable
	 */
	public final JTable getTable() {
		return tabel;
	}

	/**
	 * Returns the current SpreadSheet
	 * @return SpreadSheet
	 */
	public final SpreadSheet getSheet() {
		return sheet;
	}

	/**
	 * Returns the currently selected column in the JTable
	 * @return int
	 */
	public final int getSelectedColumn() {
		return selectedColumn;
	}

	/**
	 * Returns the currently selected row in the JTable
	 * @return int
	 */
	public final int getSelectedRow() {
		return selectedRow;
	}

	/**
	 * Returns the currently opened file
	 * @return File
	 */
	public final File getFile() {
		return file;
	}

	/**
	 * Returns a String representation of the opened file
	 * @return String
	 */
	public final String getFileString () {
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			return "Temporary file";
		return file.getName();
	}

	/**
	 * Returns this tab's UndoManager
	 * @return UndoManager
	 */
	public final UndoManager getUndoManager(){
		return undoManager;
	}

	/**
	 * Returns true is a Cell has ever been selected
	 * @return boolean
	 */
	public final boolean getCellSelected(){
		return cellSelected;
	}

	public final void setCellBold() {
		Cell current = (Cell)tabel.getValueAt(selectedRow, selectedColumn);
		if (current != null) {
			int bold = current.getBold();
			current.setBold(bold == 0 ? 1 : 0);
		}
	}

	public final void setCellItalic() {
		Cell current = (Cell)tabel.getValueAt(selectedRow, selectedColumn);
		if (current != null) {
			int italic = current.getItalic();
			current.setItalic(italic == 0 ? 2 : 0);
		}
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
	 * Opens a file dialog in which the user can select the file to open
	 * @return int - choice made: 0 for opened, 1 for cancel.
	 */
	public final int openFileDialog() {
		int opened = 1;
		if(closeFile() == 0) {
			final JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
			fc.setFileFilter(filter);
			if (fc.showOpenDialog(tabel) == JFileChooser.APPROVE_OPTION) {
				removeTempFile(file);
				file = fc.getSelectedFile();
				try {
					Document doc = XML.parse(file);
					sheet = XML.print(doc);
					tabel.setModel(sheet);
					tabel.updateUI();
					opened = 0; // Return 0, zoals closeFile
				} catch (Exception ex) {
					System.err.println (ex.getMessage());
				}
			}
		}
		return opened;
	}

	/**
	 * Properly handles opening a new file - spawns a dialog if changes will be lost
	 * @return int - 0 for OK, 1 for cancel.
	 */
	public final int newFile () {
		int res = 1;
		if(closeFile() == 0) {
			try {
				removeTempFile(file);
				file = File.createTempFile("excelpp_temp", ".xml");
				res = 0;
				sheet = new SpreadSheet();
				tabel.setModel (sheet);
				tabel.updateUI();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(tabel, "Can't open a temporary file.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		return res;
	}

	/**
	 * Saves the currently opened file
	 * @return int - choice made: 0 for saved, 1 for cancel.
	 */
	public final int saveFile () {
		int saved = 1;
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			saved = openSaveDialog();
		else {
			try {
				sheet.write(new XMLWriter(file));
				saved = 0;
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(tabel, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		return saved;
	}

	/**
	 * Properly handles closing of a file - spawns a dialog if changes will be lost
	 * @return int - choice made: 0 for OK, 1 for cancel.
	 */
	public final int closeFile () {
		int close = 1;
		try {
			if (file.toString().contains(System.getProperty("java.io.tmpdir")) == false) {
				Document doc = XML.parse(file);
				SpreadSheet fileSheet = XML.print(doc);
				if(!sheet.equals(fileSheet))
					close = JOptionPane.showConfirmDialog(tabel, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
			} else {
				if(sheet.isEmpty() == false) {
					close = JOptionPane.showConfirmDialog(tabel, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
					if(close == 0) {
						if(file.delete() != true)
							JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					if(file.delete() != true)
						JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
					else
						close = 0;
				}
			}
		} catch (Exception ex) {
			System.err.println (ex.getMessage());
		}
		return close;
	}

	/**
	 * Opens a file dialog in which the user can select where to save the current SpreadSheet
	 * @return int - choice made: 0 for saved, 1 for cancel.
	 */
	public final int openSaveDialog() {
		int saved = 1;
		final JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(tabel) == JFileChooser.APPROVE_OPTION) {
			String fileString = fc.getSelectedFile().getPath();
			fileString = fileString.replaceAll("\\...*", "");
			fileString += ".xml";
			removeTempFile(file);
			file = new File(fileString);
			try {
				file.getParentFile().mkdirs();
				sheet.write(new XMLWriter(file));
				saved = 0;
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(tabel, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		return saved;
	}

	/**
	 * Checks if parameter file is a temporary file and if so, tries to delete it
	 * @param file
	 * @return void
	 */
	private final void removeTempFile(File file) {
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			if(file.delete() != true)
				JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Listens for all mouseClicked events emitted by the elements of the tab
	 * @return void
	 */
	public final void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tabel)) {
			cellSelected = true;

			Point p = e.getPoint();
			selectedRow = tabel.rowAtPoint(p);
			selectedColumn = tabel.columnAtPoint(p);
			tabel.changeSelection(selectedRow, selectedColumn, false, false);

			String text = null;
			if (tabel.getSelectedColumnCount() == 1 && tabel.getSelectedRowCount() == 1) {
				if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON1)
					text = GUI.functionFieldGetText() + "(" + tabel.getValueAt(tabel.getSelectedRow(), tabel.getSelectedColumn()) + ")";
				else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3)
					text = GUI.functionFieldGetText() + "(" + tabel.getColumnName(tabel.getSelectedColumn()) + (tabel.getSelectedRow() + 1) + ")";
				else { //ToDo: moet hier nog een specifieke knop voor worden ingesteld, ipv alle mousebuttons?
					Cell activeCell = (Cell)tabel.getValueAt(selectedRow, selectedColumn);
					text = activeCell == null ? "" : activeCell.getContent();
				}
				GUI.functionFieldSetText(text);
			} else {
				text = GUI.functionFieldGetText();
				int selectedRows[] = tabel.getSelectedRows();
				int selectedColumns[] = tabel.getSelectedColumns();
				if (e.isAltDown() && e.getButton() == MouseEvent.BUTTON1) { //ToDo: wordt overridden door bovenstaande, maar werkt wel als deze wordt veranderd naar bijv BUTTON2 (middlemouse button)
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							text += (String) tabel.getValueAt(selectedRows[j], selectedColumns[i]) + ",";
						}
					}
				} else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3) {
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							text += "(" + tabel.getColumnName(selectedColumns[i]) + (selectedRows[j] + 1) + "),";
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
	 * Observes changes in the table
	 */
	public final void update(Observable o, Object arg) {
		tabel.repaint();
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
