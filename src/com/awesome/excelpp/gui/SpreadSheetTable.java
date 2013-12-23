package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.gui.GUI;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.w3c.dom.Document;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class that sets up everything needed for a new tab in the GUI
 */
public class SpreadSheetTable implements MouseListener, FocusListener {
	private static JScrollPane scrollPane;
	private static JTable tabel, rowTabel;
	private static SpreadSheet sheet;
	private File file = null;
	private static int selectedColumn;
	private static int selectedRow;

	public SpreadSheetTable () throws IOException {
		sheet = new SpreadSheet();
		tabel = new JTable(sheet);
		tabel.setFillsViewportHeight (true);
		tabel.setSelectionBackground (new Color(200, 221, 242));
		rowTabel = new RowNumberTable(tabel);
		
		scrollPane = new JScrollPane(tabel);
		scrollPane.setRowHeaderView(rowTabel);
		scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTabel.getTableHeader());

		tabel.addMouseListener(this);

		file = File.createTempFile("excelpp_temp", ".xml");
	}

	/**
	 * Returns the JScrollPane of this tab
	 * @return JScrollPane
	 */
	public JScrollPane getScrollPane () {
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
		if (file.getAbsolutePath().contains("tmp") == true || file.getAbsolutePath().contains("TEMP") == true || file.getAbsolutePath().contains("Temp")) //check on windows/osx
			return "Temporary file";
		return file.toString();
	}

	/**
	 * Opens a file dialog in which the user can select the file to open
	 * @return void
	 */
	public final void openFileDialog() {
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
			} catch (Exception ex) {
				System.err.println (ex.getMessage());
			}
		}
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
		if (file.getAbsolutePath().contains("tmp") == true || file.getAbsolutePath().contains("TEMP") == true || file.getAbsolutePath().contains("Temp")) //check on windows/osx
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
	public final int closeFile () { //ToDo: functioneert nog niet 100%
		int close = 1;
		try {
			if (file.getAbsolutePath().contains("temp") == false && (file.getAbsolutePath().contains("tmp") == false || file.getAbsolutePath().contains("TEMP") == false)) { //check on windows/osx
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

	private final void removeTempFile(File file) {
		if (file.getAbsolutePath().contains("tmp") == true || file.getAbsolutePath().contains("TEMP") == true) //check on windows/osx
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
			if(e.isShiftDown() && tabel.getSelectedColumnCount() == 1 && tabel.getSelectedRowCount() == 1) {
				String cellContent = GUI.functionFieldGetText() + (String) tabel.getValueAt(tabel.getSelectedRow(), tabel.getSelectedColumn());
				GUI.functionFieldSetText(cellContent);
			}
		}
	}

	public void focusGained(FocusEvent e) {}

	/**
	 * Listens for all focusLost events emitted by the elements of the tab
	 * @return void
	 */
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(tabel)){
			selectedColumn = tabel.getSelectedColumn();
			selectedRow = tabel.getSelectedRow();
		}
	}
}
