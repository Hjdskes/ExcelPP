package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.xml.XML;
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
public class SpreadSheetTable implements MouseListener, Observer, UndoableEditListener{
	private JScrollPane scrollPane;
	private JTable tabel, rowTabel;
	private SpreadSheet sheet;
	private File file = null;
	private int selectedColumn;
	private int selectedRow;
	private UndoManager undoManager;

	public SpreadSheetTable () throws IOException {
		sheet = new SpreadSheet();
		tabel = new JTable(sheet);
		undoManager = new UndoManager();
		tabel.setFillsViewportHeight (true);
		tabel.setSelectionBackground (new Color(200, 221, 242));
		tabel.setColumnSelectionAllowed(true);
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
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Returns the JTable of this tab
	 * @return JTable
	 */
	public JTable getTable() {
		return tabel;
	}

	/**
	 * Returns the currently selected column in the JTable
	 * @return int
	 */
	public int getSelectedColumn() {
		return selectedColumn;
	}

	/**
	 * Returns the currently selected row in the JTable
	 * @return int
	 */
	public int getSelectedRow() {
		return selectedRow;
	}

	/**
	 * Returns a String representation of the opened file
	 * @return String
	 */
	public String getFileString () {
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			return "Temporary file";
		return file.getName();
	}
	
	public UndoManager getUndoManager(){
		return undoManager;
	}

	/**
	 * Opens a file dialog in which the user can select the file to open
	 * @return int
	 */
	public final int openFileDialog() {
		int opened = 1;
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
		return opened;
	}

	/**
	 * Properly handles opening a new file - spawns a dialog if changes will be lost
	 * @return void
	 */
	public final void newFile () {
		if(closeFile() == 0) {
			try {
				removeTempFile(file);
				file = File.createTempFile("excelpp_temp", ".xml");
				sheet = new SpreadSheet();
				tabel.setModel (sheet);
				tabel.updateUI();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(tabel, "Can't open a temporary file.", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
	}

	/**
	 * Saves the currently opened file
	 * @return void
	 */
	public final void saveFile () {
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			openSaveDialog();
		else {
			try {
				sheet.toXML(file);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(tabel, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
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
					System.out.println("sheet is niet empty");
					sheet.toXML(file);
					close = JOptionPane.showConfirmDialog(tabel, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
					if(close == 0) {
						if(file.delete() != true)
							JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					System.out.println("sheet is empty");
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
	 * @return void
	 */
	public final void openSaveDialog() {
		final JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(tabel) == JFileChooser.APPROVE_OPTION) {
			String fileString = fc.getSelectedFile().getPath();
			fileString = fileString.replaceAll("\\...*", "");
			fileString += ".xml";
			removeTempFile(file);
			file = new File(fileString);
			try {
				file.getParentFile().mkdirs();
				sheet.toXML(file);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(tabel, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
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
	

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	/**
	 * Listens for all mouseClicked events emitted by the elements of the tab
	 * @return void
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tabel)) {
			if(e.getButton() == MouseEvent.BUTTON3) { //anders werkt alt + right klik niet op de cel die je wilt
				Point p = e.getPoint();
				int row = tabel.rowAtPoint(p);
				int column = tabel.columnAtPoint(p);
				tabel.changeSelection(row, column, false, false);
			}

			String currentText = GUI.functionFieldGetText();
			if (tabel.getSelectedColumnCount() == 1 && tabel.getSelectedRowCount() == 1) {
				if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON1){
					currentText += "(" + tabel.getValueAt(tabel.getSelectedRow(), tabel.getSelectedColumn()) + ")";
					GUI.functionFieldSetText(currentText);
				}
				else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3) {
					currentText += "(" + tabel.getColumnName(tabel.getSelectedColumn()) + (tabel.getSelectedRow() + 1) + ")";
					GUI.functionFieldSetText(currentText);
				}
				else {
					selectedColumn = tabel.getSelectedColumn();
					selectedRow = tabel.getSelectedRow();
				}
			} else {
				int selectedRows[] = tabel.getSelectedRows();
				int selectedColumns[] = tabel.getSelectedColumns();
				if (e.isAltDown() && e.getButton() == MouseEvent.BUTTON1) { //ToDo: wordt overridden door bovenstaande, maar werkt wel als deze wordt veranderd naar bijv BUTTON2 (middlemouse button)
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							currentText += (String) tabel.getValueAt(selectedRows[j], selectedColumns[i]) + ",";
						}
					}
				} else if(e.isAltDown() && e.getButton() == MouseEvent.BUTTON3) {
					for (int i = 0; i < selectedColumns.length; i++) {
						for (int j = 0; j < selectedRows.length; j++) {
							currentText += "(" + tabel.getColumnName(selectedColumns[i]) + (selectedRows[j] + 1) + "),";
						}
					}
				}
				currentText = currentText.substring(0, currentText.length()-1) + ")"; //laatste komma vervangen door een afsluitend haakje
				GUI.functionFieldSetText(currentText);
			}
		}
	}

	@Override
	/**
	 * Observes changes in the table
	 */
	public void update(Observable o, Object arg) {
		tabel.repaint();
	}

	@Override
	/**
	 * Listen for undoable edits and adds them to the undoManager
	 */
	public void undoableEditHappened(UndoableEditEvent e) {
		undoManager.addEdit(e.getEdit());
	}
}
