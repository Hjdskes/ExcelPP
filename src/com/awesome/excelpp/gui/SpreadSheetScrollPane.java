package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * This class packs a <code>SpreadSheetTable</code> inside a <code>JScrollPane</code>.
 * @author Team Awesome
 */
public class SpreadSheetScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;

	public SpreadSheetScrollPane(SpreadSheetTable table) {
		super(table);

		JTable rowTabel = new RowNumberTable(table);
		this.setRowHeaderView(rowTabel);
		this.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTabel.getTableHeader());
	}

	public SpreadSheetScrollPane() {
		this(new SpreadSheetTable());
	}

	/**
	 * Sets the currently contained <code>SpreadSheetTable</code>.
	 * @param table The new <code>SpreadSheetTable</code> to set
	 */
	public void setTable(SpreadSheetTable table) {
		this.setViewportView(table);
	}

	/**
	 * Returns the currently contained <code>SpreadSheetTable</code>.
	 * @return The currently contained <code>SpreadSheetTable</code>
	 */
	public SpreadSheetTable getTable() {
		return (SpreadSheetTable)this.getViewport().getComponent(0);
	}
}
