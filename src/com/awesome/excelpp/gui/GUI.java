package com.awesome.excelpp.gui;

/*
 * Jente & Bernd
 * We moeten o.a. uitzoeken hoe we kunnen voorkomen dat je een venster zodanig verkleint zodat de componenten
 *   verdwijnen.
 * Ook moeten we uitzoeken hoe we images ipv text in de knoppen kunnen krijgen
 */

import java.io.File;

import org.w3c.dom.Document;

import com.awesome.excelpp.xml.XML;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
	private static JPopupMenu functions;
	private static JButton functionsButton;
	private static JMenuItem maximumMenu;
	private static JMenuItem minimumMenu;
	
	public GUI () {
		screenWidth = (int)getScreenWidth();
		screenHeight = (int)getScreenHeight();

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setLocation ((screenWidth / 2) - (800 / 2), (screenHeight / 2) - (400 / 2)); //center in het midden
		mainFrame.setSize (800, 400);
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		mainFrame.add (createButtonPanel (), BorderLayout.PAGE_START);
		mainFrame.add (new SpreadSheetTable (), BorderLayout.CENTER);
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
		buttonOpen = new JButton("Openen");
		buttonSave = new JButton("Opslaan");
		functionField = new JTextField(30);
		buttonAbout = new JButton("Over");
		functions = new JPopupMenu();
		functionsButton = new JButton("Functies");
		maximumMenu = new JMenuItem("Maximum");
		minimumMenu = new JMenuItem("Minimum");
		
		functions.add(maximumMenu);
		functions.add(minimumMenu); 
		 
		 

		buttonPanel.add(buttonOpen);
		buttonPanel.add(buttonSave);
		buttonPanel.add(functionsButton);
		buttonPanel.add(functionField);
		buttonPanel.add(buttonAbout);
		

		buttonOpen.addActionListener(this);
		buttonOpen.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSave.addActionListener(this);
		buttonSave.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		functionField.addActionListener(this);
		buttonAbout.addActionListener(this);
		maximumMenu.addActionListener(this);
		minimumMenu.addActionListener(this);
		functionsButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                functions.show(e.getComponent(), e.getX(), e.getY());
            }
        });

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
		} else if (e.getSource().equals(functionField)) {
			String enteredText;
			enteredText = functionField.getText();
			functionField.setText("Je typte " + "\"" + enteredText + "\"");
		} else if(e.getSource().equals(maximumMenu)){
			JOptionPane.showMessageDialog(mainFrame, "Maximum geselecteerd");
		} else if(e.getSource().equals(minimumMenu)){
			JOptionPane.showMessageDialog(mainFrame, "Minimum geselecteerd");
		}
	}
}
