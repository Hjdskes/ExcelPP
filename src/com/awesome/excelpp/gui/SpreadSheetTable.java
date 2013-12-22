package com.awesome.excelpp.gui;

import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.gui.GUI;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.w3c.dom.Document;

/**
 * Class that sets up everything needed for a new tab in the GUI
 *
 */
public class SpreadSheetTable implements MouseListener, FocusListener {
	private static JScrollPane scrollPane;
	private static JTable tabel;
	private static SpreadSheet sheet;
	private File file = null;
	private static int selectedColumn;
	private static int selectedRow;

	public SpreadSheetTable () throws IOException {
		sheet = new SpreadSheet();
		tabel = new JTable(sheet);
		tabel.setFillsViewportHeight (true);
		tabel.setSelectionBackground (new Color(74, 144, 217));
		scrollPane = new JScrollPane(tabel);

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
		if (file.toString().contains("temp") || file.toString().contains("TEMP")) //check on Windows/OSX
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
		String path = file.getAbsolutePath();
		if (path.contains("tmp") || path.contains("TEMP")) //check on Windows/OSX
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
		//ToDo: dit werkt nog niet als er een nieuw bestand wordt aangemaakt, hier wat aan wordt veranderd en er vervolgens op Nieuw wordt geklikt.
		try {
			Document doc = XML.parse(file);
			SpreadSheet fileSheet = XML.print(doc);
			if(!sheet.equals(fileSheet))
				close = JOptionPane.showConfirmDialog(tabel, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION); //dialog met Yes/No?
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
