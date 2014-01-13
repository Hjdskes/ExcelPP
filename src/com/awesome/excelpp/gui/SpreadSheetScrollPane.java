package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

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

	public void setTable(SpreadSheetTable table) {
		this.setViewportView(table);
	}

	/**
	 * Returns the currently contained SpreadSheetTable
	 * @return SpreadSheetTable
	 */
	public SpreadSheetTable getTable() {
		return (SpreadSheetTable)this.getViewport().getComponent(0);
	}
}
