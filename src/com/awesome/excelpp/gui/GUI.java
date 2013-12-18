package com.awesome.excelpp.gui;

import com.awesome.excelpp.xml.XML;
import com.awesome.excelpp.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.w3c.dom.Document;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Class for the GUI
 *
 */
public class GUI extends JFrame implements ActionListener, MouseListener, FocusListener{
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	private static int screenWidth;
	private static int screenHeight;
	private static SpreadSheetTable tabel;
	private static SpreadSheet sheet;
	private static JButton buttonAbout;
	private static JButton buttonSaveAs;
	private static JButton buttonSave;
	private static JButton buttonNew;
	private static JButton buttonOpen;
	private static JFrame mainFrame;
	private static JTextField functionField;
	private File file = null;
	private static JComboBox<String> functions;
	private static int selectedColumn;
	private static int selectedRow;
	
	/**
	 * Constructor of GUI
	 */
	public GUI () {
		screenWidth = (int)getScreenWidth();
		screenHeight = (int)getScreenHeight();

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setSize (900, 400);
		mainFrame.setLocation ((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)); //center in het midden
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(800, 52));
		tabel = new SpreadSheetTable (new SpreadSheet());

		tabel.addMouseListener(this);
		tabel.addFocusListener(this);

		mainFrame.add (createButtonPanel (), BorderLayout.PAGE_START);
		mainFrame.add (new JScrollPane (tabel), BorderLayout.CENTER);
		mainFrame.setVisible (true);
	}

	/**
	 * Returns the screenwidth
	 */
	private static double getScreenWidth() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getWidth();
	}
	
	/**
	 * Returns the screenheight
	 */
	private static double getScreenHeight() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getHeight();
	}
	
	/**
	 * Creates the buttonpanel for our GUI
	 */
	private JPanel createButtonPanel() {
		final JPanel buttonPanel = new JPanel();
		final ImageIcon openIcon;
		final ImageIcon newIcon;
		final ImageIcon saveIcon;
		final ImageIcon saveIconAs;
		final ImageIcon aboutIcon;

		buttonPanel.setLayout(new FlowLayout());
		buttonOpen = new JButton();
		buttonNew = new JButton();
		buttonSave = new JButton();
		buttonSaveAs = new JButton();
		functionField = new JTextField(30);
		buttonAbout = new JButton();
		String[] functionList = {"Average", "Count", "CountA", "CountIf", "If", "Int", "IsLogical", "IsEven", "IsNumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper", "RoundDown", "RoundUp", "Sign", "SQRT", "Sum", "SumIf"};
		functions = new JComboBox<String>(functionList);
		functions.setSelectedIndex(0);

		functionField.addFocusListener(this);
		
		openIcon = new ImageIcon("data/woo-icons/folder_32.png");
		newIcon = new ImageIcon("data/woo-icons/page_table_add_32.png");
		saveIcon = new ImageIcon("data/woo-icons/save_32.png");
		saveIconAs = new ImageIcon("data/woo-icons/save_download_32.png");
		aboutIcon = new ImageIcon("data/woo-icons/star_32.png");

		buttonOpen.setIcon(openIcon);
		buttonNew.setIcon(newIcon);
		buttonSave.setIcon(saveIcon);
		buttonSaveAs.setIcon(saveIconAs);
		buttonAbout.setIcon(aboutIcon);

		buttonOpen.setToolTipText("Open file");
		buttonOpen.setToolTipText("New file");
		buttonSave.setToolTipText("Save file");
		buttonSaveAs.setToolTipText("Save as");
		buttonAbout.setToolTipText("About");

		buttonPanel.add(buttonOpen);
		buttonPanel.add(buttonNew);
		buttonPanel.add(buttonSave);
		buttonPanel.add(buttonSaveAs);
		buttonPanel.add(functions);
		buttonPanel.add(functionField);
		buttonPanel.add(buttonAbout);

		buttonOpen.addActionListener(this);
		buttonOpen.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonNew.addActionListener(this);
		buttonNew.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSave.addActionListener(this);
		buttonSave.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSaveAs.addActionListener(this);
		functionField.addActionListener(this);
		buttonAbout.addActionListener(this);
		functions.addActionListener(this);

		return buttonPanel;
	}
	
	/**
	 * Opens the file dialog in which the user can select which file to open
	 */
	private final void openFileDialog() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fc.setFileFilter(filter);
		if (fc.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			try {
				Document doc = XML.parse(file);
				sheet = XML.print(doc);
				tabel.setModel(sheet);
				tabel.updateUI();
			} catch (Exception ex) {
				System.err.println (ex.getMessage());
			}
		}
	}

	private final void openSaveDialog() {
		//ToDo: automatisch aanroepen als file niet bekend is
		final JFileChooser fs = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fs.setFileFilter(filter);
		if (fs.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			file = fs.getSelectedFile();
			try {
				sheet.toXML(file);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(mainFrame, "Er is iets mis gegaan: " + ex.toString());
				ex.printStackTrace();
			}
		}
	}
	
	private final void openHelpDialog(){
		JPanel helpPanel = new JPanel();
		JDialog helpDialog = new JDialog(mainFrame, "help");
		JTabbedPane helpTabbedPane = new JTabbedPane();
		
		JPanel formulaPanel = new JPanel();
		helpTabbedPane.addTab("Formula Help", formulaPanel);
		JLabel formulaText = new JLabel("<html>Help for formulas</html>");
		formulaPanel.add(formulaText);
		helpPanel.add(helpTabbedPane);
		
		JPanel hotkeyPanel = new JPanel();
		JLabel hotkeyText = new JLabel("<html>Open - ctrl+o<br>Save - ctrl+s</html>");
		hotkeyPanel.add(hotkeyText);
		helpTabbedPane.addTab("Hotkeys", hotkeyPanel);
		
		JPanel aboutPanel = new JPanel();
		JLabel aboutText = new JLabel("<html>Excel++ is een project van studenten aan de TU Delft.<br>Copyright 2013 Team Awesome.</html>");
		aboutPanel.add(aboutText);
		helpTabbedPane.addTab("About", aboutPanel);
		
		helpDialog.add(helpPanel);
		helpDialog.setSize(500, 200);
		helpDialog.setLocationRelativeTo(null);
		
		helpDialog.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonOpen)) {
			openFileDialog();
		} else if (e.getSource().equals(buttonNew)) {
			// ToDo: flaggen als er een bewerkt bestand verloren zal gaan. of tabs
			// ToDo: hoe op te slaan?
			SpreadSheet newSheet = new SpreadSheet();
			tabel.setModel (newSheet);
			tabel.updateUI();
		} else if (e.getSource().equals(buttonSave)) {
			try {
				sheet.toXML(file);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(mainFrame, "Er is iets mis gegaan: " + ex.toString());
				ex.printStackTrace();
			}
		} else if (e.getSource().equals(buttonSaveAs)) {
			//automatisch detecteren in het geval van nieuwe sheet
			openSaveDialog();
		} else if (e.getSource().equals(buttonAbout)) {
			openHelpDialog();
		} else if (e.getSource().equals(functions)) {
			String formula = (String)functions.getSelectedItem();
			formula = "=" + formula;
			functionField.setText(formula);
			//nu nog de geselecteerde cellen erbij
		} else if (e.getSource().equals(functionField)) {
			String enteredText = functionField.getText();
			if (enteredText.charAt(0) == '=') {
				Scanner sc; //we moeten hier ook nog de cellen invoeren en scannen
				sc = new Scanner(enteredText);
				String formula = sc.next();
				if (formula.equals("=Sum")) {
					System.out.println("De formule is: Sum");
				} else {
					System.err.println("De formule wordt niet herkend");
				}
				sc.close();
			} else {
				JOptionPane.showMessageDialog(mainFrame, "De ingevoerde functie is ongeldig.");
			}
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tabel)) {
			if(tabel.getSelectedColumnCount() == 1 && tabel.getSelectedRowCount() == 1) {
				String cellContent = functionField.getText() + (String) tabel.getValueAt(tabel.getSelectedRow(), tabel.getSelectedColumn());
				functionField.setText(cellContent);
			}
		}
	}
	
	public void focusGained(FocusEvent e) {}
	
	public void focusLost(FocusEvent e){
		if(e.getSource().equals(tabel)){
			selectedColumn = tabel.getSelectedColumn();
			selectedRow = tabel.getSelectedRow();
		}
		
		if(e.getSource().equals(functionField)){
			tabel.setValueAt(functionField.getText(), selectedRow, selectedColumn);
		}
	}

	//Tijdelijk zodat de GUI nog steeds getest kan worden
	public static void main(String[] args){
		new GUI();
	}
}
