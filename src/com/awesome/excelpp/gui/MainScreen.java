package com.awesome.excelpp.gui;

/*
 * Jente & Bernd
 * Uitproberen van GUI (NB: dit is een voorbeeld klasse).
 * We moeten o.a. uitzoeken hoe we kunnen voorkomen dat je een venster zodanig verkleint zodat de componenten
 *   verdwijnen. Misschien kan je een minimale grootte instellen, of anders is de WrapLayout misschien een optie:
 *   http://tips4java.wordpress.com/2008/11/06/wrap-layout/
 * Ook moeten we uitzoeken hoe we images ipv text in de knoppen kunnen krijgen
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?

	public MainScreen () {
		setTitle ("ExcelPP");
		setLayout(new BorderLayout());
		//setLocation (200, 200); Laat de plaatsing van het venster over aan de Window Manager van het OS
		//setSize (300, 200); Laat ook dit over aan Java zelf en het OS
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //applicatie stopt als je het venster sluit

		//zie: http://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		add(createButtonPanel(), BorderLayout.PAGE_START);
		add(createTable(), BorderLayout.CENTER);
		
		setVisible(true);
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
