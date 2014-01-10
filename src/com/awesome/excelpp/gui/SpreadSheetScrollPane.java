package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SpreadSheetScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private SpreadSheetTable table;

	public SpreadSheetScrollPane(SpreadSheetTable table) {
		super(table);
		
		this.table = table;
		
		JTable rowTabel = new RowNumberTable(table);
		this.setRowHeaderView(rowTabel);
		this.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTabel.getTableHeader());
	}
	
	public SpreadSheetScrollPane() {
		this(new SpreadSheetTable());
	}

	public void setTable(SpreadSheetTable table) { //ToDo: nodig? Tabel wordt hier niet door bijgewerkt
		this.table = table;
	}

	public SpreadSheetTable getTable() {
		return this.table;
	}
}
