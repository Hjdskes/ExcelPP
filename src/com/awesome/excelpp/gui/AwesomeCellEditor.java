package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import com.awesome.excelpp.models.Cell;

public class AwesomeCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textfield;
	private Cell currentCell;
	private String currentText;

	public AwesomeCellEditor () {
		textfield = new JTextField();
		textfield.setActionCommand("Edit");
		textfield.addActionListener(this);
		//textfield.setBorderPainted(false);
		textfield.setBackground(Color.RED);
	}

	@Override
	public Object getCellEditorValue() {
		return currentCell.getContent();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		//currentText = (String)value;
		currentCell = (Cell)value;
		textfield.setText(currentCell.getContent());
        return textfield;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Edit")) {
			/* The user has clicked the cell, so
			 * bring up the dialog.
			 */
			textfield.setText(currentText);
			//dialog.setVisible(true);

			fireEditingStopped(); //Make the renderer reappear.
		} else { //User pressed dialog's "OK" button.
			currentText = textfield.getText();
		}
	}
}