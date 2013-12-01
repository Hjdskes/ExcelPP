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
		setAutoResizeMode(AUTO_RESIZE_OFF);
		//setOpaque(true); //MUST do this for background to show up. even verifiëren
		
		SpreadSheet sheet = new SpreadSheet();
		sheet.fillSheetFormula();
		this.setModel(sheet);
	}
}
