package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class SpreadSheetTable extends JTable {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	public static JScrollPane scrollPane;
	public static JTable cellTable;

	public SpreadSheetTable () {
		JTable cellTable = new JTable ();
		Color backgroundColor = new Color(222, 222, 222);

		cellTable.setFillsViewportHeight (true);
		cellTable.setSelectionBackground (backgroundColor);
		//cellTable.setOpaque(true); //MUST do this for background to show up. even verifiëren
		JScrollPane scrollPane = new JScrollPane (cellTable);
	}
}
