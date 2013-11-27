package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

//zie: http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple
public class SpreadSheetTable extends JTable {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	public static JScrollPane scrollPane;
	public static JTable cellTable;

	public SpreadSheetTable () {
		JTable cellTable = new JTable ();
		cellTable.setFillsViewportHeight (true);
		JScrollPane scrollPane = new JScrollPane (cellTable);
	}
}
