package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import com.awesome.excelpp.models.Cell;

/**
 * ToDo: meerdere cellen kunnen selecteren
 */
public class AwesomeCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		if (value != null) {
			l.setBackground(((Cell)value).getBackgroundColor());
		} else {
			l.setBackground(Color.WHITE);
		}
		return l;
	}
}
