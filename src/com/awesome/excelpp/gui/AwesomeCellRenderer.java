package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import sun.swing.DefaultLookup;

import com.awesome.excelpp.models.Cell;

/**
 * @author: Oracle, Philip Milne. Modified by us to support Cell markup
 * @source: http://grepcode.com/file_/repository.grepcode.com/java/root/jdk/openjdk/7-b147/javax/swing/table/DefaultTableCellRenderer.java/?v=source
 */
public class AwesomeCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
	private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
	protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

	public AwesomeCellRenderer () {
		super();
	}

    private Border getNoFocusBorder() {
		Border border = DefaultLookup.getBorder(this, ui, "Table.cellNoFocusBorder");
		if (System.getSecurityManager() != null) {
			if (border != null) return border;
			return SAFE_NO_FOCUS_BORDER;
		} else if (border != null) {
			if (noFocusBorder == null || noFocusBorder == DEFAULT_NO_FOCUS_BORDER)
				return border;
		}
		return noFocusBorder;
	}

    /**
    * During a printing operation, this method will be called with
    * <code>isSelected</code> and <code>hasFocus</code> values of
    * <code>false</code> to prevent selection and focus from appearing
    * in the printed output. To do other customization based on whether
    * or not the table is being printed, check the return value from
    * {@link javax.swing.JComponent#isPaintingForPrint()}.
    *
    * @param table  the <code>JTable</code>
    * @param value  the value to assign to the cell at
    *                  <code>[row, column]</code>
    * @param isSelected true if cell is selected
    * @param hasFocus true if cell has focus
    * @param row  the row of the cell to render
    * @param column the column of the cell to render
    * @return the custom awesome table cell renderer
    * @see javax.swing.JComponent#isPaintingForPrint()
    */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (table == null)
			return this;

		Color fg = null;
		Color bg = null;

		JTable.DropLocation dropLocation = table.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsertRow() && !dropLocation.isInsertColumn() && dropLocation.getRow() == row && dropLocation.getColumn() == column) {
			fg = DefaultLookup.getColor(this, ui, "Table.dropCellForeground");
			bg = DefaultLookup.getColor(this, ui, "Table.dropCellBackground");
			isSelected = true;
		}

		if (isSelected) {
			super.setForeground(fg == null ? table.getSelectionForeground() : fg);
			super.setBackground(bg == null ? table.getSelectionBackground() : bg);
		} else {
			Cell current = (Cell)table.getValueAt(row, column);

			Color background;
			if (current != null)
				background = current.getBackgroundColor();
			else
				background = table.getBackground(); 

			if (background == null || background instanceof javax.swing.plaf.UIResource) {
				Color alternateColor = DefaultLookup.getColor(this, ui, "Table.alternateRowColor");
				if (alternateColor != null && row % 2 != 0)
					background = alternateColor;
			}

			Color foreground;
			if (current != null)
				foreground = current.getForegroundColor();
			else
				foreground = table.getForeground();

			super.setForeground(foreground);
			super.setBackground(background);
		}

		setFont(table.getFont());

		if (hasFocus) {
			Border border = null;
			if (isSelected)
				border = DefaultLookup.getBorder(this, ui, "Table.focusSelectedCellHighlightBorder");
			if (border == null)
				border = DefaultLookup.getBorder(this, ui, "Table.focusCellHighlightBorder");
			setBorder(border);

			if (!isSelected && table.isCellEditable(row, column)) {
				Color col;
				col = DefaultLookup.getColor(this, ui, "Table.focusCellForeground");
				if (col != null)
					super.setForeground(col);
				col = DefaultLookup.getColor(this, ui, "Table.focusCellBackground");
				if (col != null)
					super.setBackground(col);
			}
		} else
			setBorder(getNoFocusBorder());

		setValue(value);

		return this;
	}
}
