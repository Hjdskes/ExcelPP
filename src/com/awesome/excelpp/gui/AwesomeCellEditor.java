package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import java.util.EventObject;

import com.awesome.excelpp.models.Cell;

/**
 * Custom CellEditor to preserve text markup and fore- and background <code>Color</code> upon editing a <code>Cell</code>.
 * @author Team Awesome. The <code>isCellEditable</code> method is copied from the <code>DefaultCellEditor</code> source code, taken from
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/javax/swing/DefaultCellEditor.java
 * This method is written by Alan Chung and Philip Milne.
 */
public class AwesomeCellEditor extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 1L;
	private JTextField textfield;
	private Cell currentCell;
	private int clickCount;

	public AwesomeCellEditor () {
		this.clickCount = 2;
		this.textfield = new JTextField("");
		textfield.setBorder(null);
	}

	/**
	 * Sets the value of this cell.
	 */
	@Override
	public Object getCellEditorValue() {
		return currentCell;
	}

	/**
	 * Implements the <code>TableCellEditor</code> interface.
	 * @return Component
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		currentCell = (Cell)value;
		Font currentFont = textfield.getFont();

		Font newFont = new Font(currentFont.getName(), currentCell.getItalic() | currentCell.getBold(), currentFont.getSize());
		textfield.setFont(newFont);

		Color background = currentCell.getBackgroundColor();
		Color foreground = currentCell.getForegroundColor();

		textfield.setForeground(foreground);
		textfield.setBackground(background);

		textfield.setText(currentCell.getContent());
        return textfield;
	}

	/**
	 * Returns true if this <code>Cell</code> is editable.
	 * @return boolean	True if this <code>Cell</code> is editable.
	 */
	@Override
	public boolean isCellEditable(EventObject evt) {
		if (evt instanceof MouseEvent)
			return ((MouseEvent)evt).getClickCount() >= clickCount;
		return true;
	}

	/**
	 * Stops editing and returns true to indicate that editing has stopped, thus calling the
	 * <code>AwesomeCellRenderer</code> to take over again.
	 * This method calls <code>fireEditingStopped</code>.
	 * @return true
	 */
	@Override
	public boolean stopCellEditing() {
		currentCell.setContent(textfield.getText(), true);
		return super.stopCellEditing();
	}
}
