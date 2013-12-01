package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.awesome.excelpp.models.SpreadSheet;

import java.awt.Color;

public class SpreadSheetTable extends JTable {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	public static JScrollPane scrollPane;

	public SpreadSheetTable () {
		super(10, 10);
		
		Color backgroundColor = new Color(222, 222, 222);

		setFillsViewportHeight (true);
		setSelectionBackground (backgroundColor);
		//setOpaque(true); //MUST do this for background to show up. even verifiëren
		new JScrollPane (this);
		
		test();
	}
	
	public void test() {
		SpreadSheet sheet = new SpreadSheet();
		sheet.fillSheetFormula();
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				this.setValueAt(sheet.getValue(row, col), row, col);
			}
		}
	}
}
