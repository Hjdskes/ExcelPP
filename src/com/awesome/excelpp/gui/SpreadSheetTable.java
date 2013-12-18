package com.awesome.excelpp.gui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.awesome.excelpp.models.SpreadSheet;

import java.awt.Color;

/**
 * Tablemodel for the spreadsheet
 *
 */
public class SpreadSheetTable extends JTable {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	public static JScrollPane scrollPane;

	public SpreadSheetTable (SpreadSheet sheet) {
		super(sheet);

		sheet.fillSheetFormula();
		setFillsViewportHeight (true);
		setSelectionBackground (new Color(74, 144, 217));
		setOpaque(true); //MUST do this for background to show up. even verifiëren
	}
}
