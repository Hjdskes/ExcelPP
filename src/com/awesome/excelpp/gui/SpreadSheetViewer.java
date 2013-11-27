package com.awesome.excelpp.gui;

/*
 * Jente & Bernd
 * We moeten o.a. uitzoeken hoe we kunnen voorkomen dat je een venster zodanig verkleint zodat de componenten
 *   verdwijnen.
 * Ook moeten we uitzoeken hoe we images ipv text in de knoppen kunnen krijgen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;

public class SpreadSheetViewer extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	private static int screenWidth;
	private static int screenHeight;

	public SpreadSheetViewer () {
		screenWidth = (int)getScreenWidth();
		screenHeight = (int)getScreenHeight();

		setTitle ("ExcelPP");
		setLayout (new BorderLayout());
		setLocation ((screenWidth / 2) - (800 / 2), (screenHeight / 2) - (400 / 2)); //center in het midden
		setSize (800, 400);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //applicatie stopt als je het venster sluit

		//zie: http://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		add(createButtonPanel(), BorderLayout.PAGE_START);
		add(createTable(), BorderLayout.CENTER);
		
		setVisible(true);
	}

	private static double getScreenWidth() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getWidth();
	}

	private static double getScreenHeight() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getHeight();
	}

	private JPanel createButtonPanel() {
		final JPanel buttonPanel = new JPanel();
		final JButton buttonOpen;
		final JButton buttonSave;
		final JButton buttonFunctions;
		final JButton buttonUnk1;
		final JButton buttonUnk2;
		final JButton buttonUnk3;
		final JTextField functionField;

		buttonPanel.setLayout(new FlowLayout());
		buttonOpen = new JButton("Openen");
		buttonSave = new JButton("Opslaan");
		buttonFunctions = new JButton("Functies");
		functionField = new JTextField(60);
		buttonUnk1 = new JButton("Placeholder");
		buttonUnk2 = new JButton("Placeholder");
		buttonUnk3 = new JButton("Placeholder");
		buttonPanel.add(buttonOpen);
		buttonPanel.add(buttonSave);
		buttonPanel.add(buttonFunctions);
		buttonPanel.add(functionField);
		buttonPanel.add(buttonUnk1);
		buttonPanel.add(buttonUnk2);
		buttonPanel.add(buttonUnk3);
		buttonOpen.addActionListener(this);
		buttonSave.addActionListener(this);
		buttonFunctions.addActionListener(this);
		functionField.addActionListener(this);

		return buttonPanel;
	}

	// zie: http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#simple
	private JScrollPane createTable() {
		String[] columnNames = { "1", "2", "3", "4", "5" };
		Object[][] data = {
			    { "1.1", "1.2", "1.3", "1.4", "1.5" },
			    { "2.1", "2.2", "2.3", "2.4", "2.5" },
			    { "3.1", "3.2", "3.3", "3.4", "3.5" },
			    { "4.1", "4.2", "4.3", "4.4", "4.5" },
			    { "5.1", "5.2", "5.3", "5.4", "5.5" }
			};

		final JTable cellTable = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(cellTable);
		cellTable.setFillsViewportHeight(true);

		return scrollPane;
	}

	public void actionPerformed(ActionEvent e) {
		/*String demoAction;
		demoAction = demoTextField.getText();
		demoLabel.setText("Je typte " + "\"" + demoAction + "\"");*/
	}
}
