package com.awesome.excelpp.gui;

import com.awesome.excelpp.gui.SpreadSheetTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Class that constructs everything needed for and by the GUI
 * ToDo:
 *   add new tabs
 *     with new file
 *     with opened file
 *   switch to specific tab with keyboard shortcut / arrow keys
 *   remove tabs
 */
public class GUI extends JFrame implements ActionListener, FocusListener {
	private static final long serialVersionUID = 1L; // anders zeurt eclipse, maar waarom?
	private static int screenWidth;
	private static int screenHeight;
	private static JFrame mainFrame;
	private static JTextField functionField;
	private static JTabbedPane mainTabs;
	private static JComboBox<String> functions;
	private static JButton buttonAbout;
	private static JButton buttonSaveAs;
	private static JButton buttonSave;
	private static JButton buttonOpen;
	private static JButton buttonNewTab;
	private static JButton buttonNew;
	private static ArrayList<SpreadSheetTable> panes = new ArrayList<SpreadSheetTable>();

	/**
	 * Constructor
	 */
	public GUI () throws IOException {
		final JPanel buttonPanel = createButtonPanel();

		screenWidth = (int)Utils.getScreenWidth();
		screenHeight = (int)Utils.getScreenHeight();

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setSize (900, 400);
		mainFrame.setLocation ((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)); //center in het midden
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(buttonPanel.getPreferredSize());

		mainTabs = new JTabbedPane();
		createNewTab(); //open altijd één tab

		mainFrame.add (buttonPanel, BorderLayout.PAGE_START);
		mainFrame.add (mainTabs, BorderLayout.CENTER);
		mainFrame.setVisible (true);
	}

	/**
	 * createButtonPanel creates a JPanel holding the required buttons
	 * @return JPanel
	 */
	private JPanel createButtonPanel() {
		final JPanel panel = new JPanel();
		final ImageIcon newIcon;
		final ImageIcon newTabIcon;
		final ImageIcon openIcon;
		final ImageIcon saveIcon;
		final ImageIcon saveIconAs;
		final ImageIcon aboutIcon;

		panel.setLayout(new FlowLayout());
		buttonNew = new JButton();
		buttonNewTab = new JButton();
		buttonOpen = new JButton();
		buttonSave = new JButton();
		buttonSaveAs = new JButton();
		functionField = new JTextField(30);
		buttonAbout = new JButton();
		String[] functionList = {"Average", "Count", "CountA", "CountIf", "If", "Int", "IsLogical", "IsEven", "IsNumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper", "RoundDown", "RoundUp", "Sign", "SQRT", "Sum", "SumIf"};
		functions = new JComboBox<String>(functionList);
		functions.setSelectedIndex(0);

		functionField.addFocusListener(this);

		newIcon = new ImageIcon("data/woo-icons/page_table_add_32.png");
		newTabIcon = new ImageIcon("data/woo-icons/add_32.png");
		openIcon = new ImageIcon("data/woo-icons/folder_32.png");
		saveIcon = new ImageIcon("data/woo-icons/save_32.png");
		saveIconAs = new ImageIcon("data/woo-icons/save_download_32.png");
		aboutIcon = new ImageIcon("data/woo-icons/star_32.png");

		buttonNew.setIcon(newIcon);
		buttonNewTab.setIcon(newTabIcon);
		buttonOpen.setIcon(openIcon);
		buttonSave.setIcon(saveIcon);
		buttonSaveAs.setIcon(saveIconAs);
		buttonAbout.setIcon(aboutIcon);

		buttonNew.setToolTipText("New file");
		buttonNewTab.setToolTipText("New tab");
		buttonOpen.setToolTipText("Open file");
		buttonSave.setToolTipText("Save file");
		buttonSaveAs.setToolTipText("Save as");
		buttonAbout.setToolTipText("About");

		panel.add(buttonNew);
		panel.add(buttonNewTab);
		panel.add(buttonOpen);
		panel.add(buttonSave);
		panel.add(buttonSaveAs);
		panel.add(functions);
		panel.add(functionField);
		panel.add(buttonAbout);

		buttonNew.addActionListener(this);
		buttonNew.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonNewTab.addActionListener(this);
		buttonNewTab.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonOpen.addActionListener(this);
		buttonOpen.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSave.addActionListener(this);
		buttonSave.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonSaveAs.addActionListener(this);
		functionField.addActionListener(this);
		buttonAbout.addActionListener(this);
		functions.addActionListener(this);

		return panel;
	}
	
	/**
	 * Opens a help dialog in which the user can get help on formulas and keyboard shortcuts
	 * @return void
	 */
	private final void openHelpDialog() {
		JDialog helpDialog = new JDialog(mainFrame, "Help");
		JPanel helpPanel = new JPanel();
		JTabbedPane helpTabbedPane = new JTabbedPane();
		
		JPanel formulaPanel = new JPanel();
		JLabel formulaText = new JLabel("<html>Help for formulas<br>Shift + left mouse click to get cel content into entry</html>");
		formulaPanel.add(formulaText);
		
		JPanel hotkeyPanel = new JPanel();
		JLabel hotkeyText = new JLabel("<html>Open file - Control + O<br>New file - Control + N<br>Save file - Control + S<br></html>");
		hotkeyPanel.add(hotkeyText);

		JPanel aboutPanel = new JPanel();
		JLabel aboutText = new JLabel("<html>Excel++ is a TU Delft project.<br>Copyright 2013 Team Awesome.</html>");
		aboutPanel.add(aboutText);

		helpPanel.add(helpTabbedPane);
		helpTabbedPane.addTab("Formula Help", formulaPanel);
		helpTabbedPane.addTab("Hotkeys", hotkeyPanel);
		helpTabbedPane.addTab("About", aboutPanel);

		helpDialog.add(helpPanel);
		helpDialog.setSize(helpPanel.getPreferredSize().width, helpPanel.getPreferredSize().height);
		helpDialog.setMinimumSize(helpPanel.getPreferredSize());
		helpDialog.setLocation ((screenWidth / 2) - (helpPanel.getPreferredSize().width / 2), (screenHeight / 2) - (helpPanel.getPreferredSize().height / 2)); //center in het midden
		
		helpDialog.setVisible(true);
	}

	/**
	 * Listens for all events emitted by the elements of the GUI
	 * Calls other functions
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonOpen)) {
			int index = mainTabs.getSelectedIndex();
			panes.get(index).openFileDialog();
		} else if (e.getSource().equals(buttonNew)) {
			//ToDo: keuze laten aan gebruiker of hij de veranderingen wil opslaan of gewoon doorgaan
			/*try {
				Document doc = XML.parse(file);
				SpreadSheet fileSheet = XML.print(doc);
				if(!sheet.equals(fileSheet))
					JOptionPane.showMessageDialog(mainFrame, "Changes made to the current spreadsheet will be lost. Continue?"); //dialog met Yes/No?
			} catch (Exception ex) {
				System.err.println (ex.getMessage());
			}
			try {
				file = File.createTempFile("excelpp_temp", ".xml");
				sheet = new SpreadSheet();
				tabel.setModel (sheet);
				tabel.updateUI();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(mainFrame, "Can't open a temporary file.");
				return;
			}*/
		} else if (e.getSource().equals(buttonNewTab)) {
			createNewTab();
		} else if (e.getSource().equals(buttonSave)) {
			int index = mainTabs.getSelectedIndex();
			panes.get(index).saveFile();
			if(panes.get(index).getFileString().equals("Temporary file"))
				mainTabs.setTitleAt(index, panes.get(index).getFileString()); //niet echt optimaal?
		} else if (e.getSource().equals(buttonSaveAs)) {
			int index = mainTabs.getSelectedIndex();
			panes.get(index).openSaveDialog();
			mainTabs.setTitleAt(index, panes.get(index).getFileString()); //niet echt optimaal?
		} else if (e.getSource().equals(buttonAbout)) {
			openHelpDialog();
		} else if (e.getSource().equals(functions)) {
			String formula = "=" + (String)functions.getSelectedItem();
			//nu nog de geselecteerde cellen erbij
			functionField.setText(formula);
		} else if (e.getSource().equals(functionField)) {
			String enteredText = functionField.getText();
			if (enteredText.charAt(0) == '=') {
				Scanner sc; //we moeten hier ook nog de cellen invoeren en scannen
				sc = new Scanner(enteredText);
				String formula = sc.next();
				sc.close();
			} else
				JOptionPane.showMessageDialog(mainFrame, "The entered formula is invalid.");
		}
	}

	private static void createNewTab() {
		SpreadSheetTable table;
		try {
			table = new SpreadSheetTable();
			panes.add(table);
			int last = panes.size() - 1;
			mainTabs.addTab(panes.get(last).getFileString(), new ImageIcon("data/woo-icons/page_16.png"), panes.get(last).getScrollPane(), null);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(mainFrame, "Something went wrong: " + ex.toString());
			ex.printStackTrace();
		}
	}

	public void focusGained(FocusEvent e) {}

	/**
	 * Listens for all focusLost events emitted by the elements of the GUI
	 * @return void
	 */
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(functionField)) {
			int index = mainTabs.getSelectedIndex();
			panes.get(index).getTable().setValueAt(functionField.getText(), panes.get(index).getSelectedRow(), panes.get(index).getSelectedColumn());
		}
	}

	/**
	 * Helper method to set the text in the function field
	 * @param String - text to set
	 */
	public static void functionFieldSetText (String text) {
		functionField.setText(text);
	}

	/**
	 * Helper method to get the text from the function field
	 * @return String
	 */
	public static String functionFieldGetText () {
		return functionField.getText();
	}

	//Tijdelijk zodat de GUI nog steeds getest kan worden
	public static void main(String[] args) {
		try {
			new GUI();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
