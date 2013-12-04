package com.awesome.excelpp.gui;

/*
 * Jente & Bernd
 * We moeten o.a. uitzoeken hoe we kunnen voorkomen dat je een venster zodanig verkleint zodat de componenten
 *   verdwijnen.
 * Misschien een help dialog invoeren als een soort handleiding?
 */

import com.awesome.excelpp.xml.XML;

import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.w3c.dom.Document;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	private static int screenWidth;
	private static int screenHeight;
	private static JButton buttonAbout;
	private static JButton buttonSave;
	private static JButton buttonOpen;
	private static JFrame mainFrame;
	private static JTextField functionField;
	private File file = null;
	private static JComboBox<String> functions;
	private static ImageIcon openIcon;
	private static ImageIcon saveIcon;
	private static ImageIcon aboutIcon;
	
	
	public GUI () {
		screenWidth = (int)getScreenWidth();
		screenHeight = (int)getScreenHeight();

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setLocation ((screenWidth / 2) - (800 / 2), (screenHeight / 2) - (400 / 2)); //center in het midden
		mainFrame.setSize (800, 400);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		mainFrame.add (createButtonPanel (), BorderLayout.PAGE_START);
		mainFrame.add (new JScrollPane (new SpreadSheetTable ()), BorderLayout.CENTER);
		mainFrame.setVisible (true);
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

		buttonPanel.setLayout(new FlowLayout());
		buttonOpen = new JButton();
		buttonSave = new JButton();
		functionField = new JTextField(30);
		buttonAbout = new JButton();
		String[] functionList = {"Average", "Count", "CountA", "CountIf", "If", "Int", "IsLogical", "IsEven", "IsNumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper", "RoundDown", "RoundUp", "Sign", "SQRT", "Sum", "SumIf"};
		functions = new JComboBox<String>(functionList);
		functions.setSelectedIndex(0);

		openIcon = new ImageIcon("data/icons/PNG_32x32_black/folder-icon_32x32px.png");
		saveIcon = new ImageIcon("data/icons/PNG_32x32_black/save-disk-icon_32x32px.png");
		aboutIcon = new ImageIcon("data/icons/PNG_32x32_black/question-mark-icon_32x32px.png");

		buttonOpen.setIcon(openIcon);
		buttonSave.setIcon(saveIcon);
		buttonAbout.setIcon(aboutIcon);

		buttonOpen.setToolTipText("Open file");
		buttonSave.setToolTipText("Save file");
		buttonAbout.setToolTipText("About");

		buttonPanel.add(buttonOpen);
		buttonPanel.add(buttonSave);
		buttonPanel.add(functions);
		buttonPanel.add(functionField);
		buttonPanel.add(buttonAbout);

		buttonOpen.addActionListener(this);
		buttonOpen.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSave.addActionListener(this);
		buttonSave.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		functionField.addActionListener(this);
		buttonAbout.addActionListener(this);
		functions.addActionListener(this);

		return buttonPanel;
	}

	private final void openFileDialog() {
		final JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			try {
				Document doc = XML.parse(file);
				XML.print(doc);
			} catch (Exception ex) {
				System.err.println (ex.getMessage());
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonOpen)) {
			openFileDialog();
		} else if (e.getSource().equals(buttonSave)) {
			//save XML file
		} else if (e.getSource().equals(buttonAbout)) {
			JOptionPane.showMessageDialog(mainFrame, "Excel++ is een project van studenten aan de TU Delft.\nCopyright 2013 Team Awesome.");
		} else if (e.getSource().equals(functions)) {
			String formule = (String)functions.getSelectedItem();
			formule = "=" + formule;
			functionField.setText(formule.toUpperCase());
			//nu nog de geselecteerde cellen erbij
		} else if (e.getSource().equals(functionField)) {
			String enteredText = functionField.getText();
			if (enteredText.charAt(0) == '=') {
				Scanner sc; //we moeten hier ook nog de cellen invoeren en scannen
				sc = new Scanner(enteredText);
				String formule = sc.next();
				if (formule.equals("=SUM")) {
					System.out.println("De formule is: SUM");
				} else {
					System.err.println("De formule wordt niet herkend");
				}
				sc.close();
			} else {
				JOptionPane.showMessageDialog(mainFrame, "De ingevoerde functie is ongeldig.");
			}
		}
	}
	
	//Tijdelijk zodat de GUI nog steeds getest kan worden
	public static void main(String[] args){
		new GUI();
	}
}
