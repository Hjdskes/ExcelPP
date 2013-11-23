package com.awesome.excelpp.gui;

/*
 * Jente & Bernd
 * Uitproberen van GUI (NB: dit is een voorbeeld klasse).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse
	private JPanel demoPanel;
	private JTextField demoTextField = new JTextField(15);
	private JButton demoButton = new JButton("Een demo button");
	private JLabel demoLabel = new JLabel("Een demo label. Vul wat in in het textveld.", JLabel.CENTER);

	public MainScreen () {
		demoPanel = new JPanel();
		setTitle ("Een kleine demo");
		setLayout(new FlowLayout());
		setLocation (200, 200); // default is 0,0 (top left corner)
		setSize (300, 200); // default is 0,0
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //applicatie stopt als je het venster sluit

		demoPanel.add(demoTextField);
		demoPanel.add(demoButton);
		demoPanel.add(demoLabel);
		add(demoPanel);
		demoButton.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String demoAction;
		demoAction = demoTextField.getText();
		demoLabel.setText("Je typte " + "\"" + demoAction + "\"");
	}
}
