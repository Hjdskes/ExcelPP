package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import sun.swing.DefaultLookup;

public class RowNumberTableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	public RowNumberTableRenderer () {
		super();
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

		setBorder(new MatteBorder(1, 1, 0, 0, Color.WHITE));
		setBackground(DefaultLookup.getColor(this, ui, "TableHeader.background"));
		setFont(table.getFont());
		setHorizontalAlignment(SwingConstants.CENTER);
		setValue(value);
		return this;
	}
}
