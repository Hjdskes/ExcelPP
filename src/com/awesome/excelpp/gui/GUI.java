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
 * Class that constructs everything needed for and by the GUI
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
	 * Constructor
	 */
	public GUI () {
		final JPanel buttonPanel = createButtonPanel();
		screenWidth = (int)getScreenWidth();
		screenHeight = (int)getScreenHeight();

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setSize (900, 400);
		mainFrame.setLocation ((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)); //center in het midden
		mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mainFrame.setMinimumSize(buttonPanel.getPreferredSize());
		tabel = new SpreadSheetTable (new SpreadSheet());

		tabel.addMouseListener(this);
		tabel.addFocusListener(this);

		mainFrame.add (buttonPanel, BorderLayout.PAGE_START);
		mainFrame.add (new JScrollPane (tabel), BorderLayout.CENTER);
		mainFrame.setVisible (true);
	}

	/**
	 * Function that gets the screen width
	 * @return int
	 */
	private static double getScreenWidth() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getWidth();
	}
	
	/**
	 * Function that gets the screen height
	 * @return int
	 */
	private static double getScreenHeight() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();

		return gd.getDefaultConfiguration().getBounds().getHeight();
	}
	
	/**
	 * createButtonPanel creates a JPanel holding the required buttons
	 * @return JPanel
	 */
	private JPanel createButtonPanel() {
		final JPanel panel = new JPanel();
		final ImageIcon openIcon;
		final ImageIcon newIcon;
		final ImageIcon saveIcon;
		final ImageIcon saveIconAs;
		final ImageIcon aboutIcon;

		panel.setLayout(new FlowLayout());
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
		buttonNew.setToolTipText("New file");
		buttonSave.setToolTipText("Save file");
		buttonSaveAs.setToolTipText("Save as");
		buttonAbout.setToolTipText("About");

		panel.add(buttonOpen);
		panel.add(buttonNew);
		panel.add(buttonSave);
		panel.add(buttonSaveAs);
		panel.add(functions);
		panel.add(functionField);
		panel.add(buttonAbout);

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

		return panel;
	}
	
	/**
	 * Opens a file dialog in which the user can select the file to open
	 * @return void
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

	/**
	 * Opens a file dialog in which the user can select where to save the current SpreadSheet
	 * @return void
	 */
	private final void openSaveDialog() {
		final JFileChooser fs = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fs.setFileFilter(filter);
		if (fs.showSaveDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
			String file = fs.getSelectedFile().getName();
			file = file.replaceAll("\\...*", "");
			file += ".xml";
			File fileXML = new File(file);
			try {
				sheet.toXML(fileXML);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(mainFrame, "Er is iets mis gegaan: " + ex.toString());
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Opens a help dialog in which the user can get help on formulas and keyboard shortcuts
	 * @return void
	 */
	private final void openHelpDialog() {
		JDialog helpDialog = new JDialog(mainFrame, "help");
		JPanel helpPanel = new JPanel();
		JTabbedPane helpTabbedPane = new JTabbedPane();
		
		JPanel formulaPanel = new JPanel();
		JLabel formulaText = new JLabel("<html>Help for formulas</html>");
		formulaPanel.add(formulaText);
		
		JPanel hotkeyPanel = new JPanel();
		JLabel hotkeyText = new JLabel("<html>Open file- Control + O<br>New file - Control + N<br>Save file - Control + S<br></html>");
		hotkeyPanel.add(hotkeyText);

		JPanel aboutPanel = new JPanel();
		JLabel aboutText = new JLabel("<html>Excel++ is een project van studenten aan de TU Delft.<br>Copyright 2013 Team Awesome.</html>");
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
			openFileDialog();
		} else if (e.getSource().equals(buttonNew)) {
			// ToDo: flaggen als er een bewerkt bestand verloren zal gaan. of tabs. of automatisch opslaan.
			SpreadSheet newSheet = new SpreadSheet();
			tabel.setModel (newSheet);
			tabel.updateUI();
		} else if (e.getSource().equals(buttonSave)) {
			if(file == null) {
				openSaveDialog();
				return;
			}
			try {
				sheet.toXML(file);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(mainFrame, "Er is iets mis gegaan: " + ex.toString());
				ex.printStackTrace();
			}
		} else if (e.getSource().equals(buttonSaveAs)) {
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

	/**
	 * Listens for all mouseClicked events emitted by the elements of the GUI
	 * @return void
	 */
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(tabel)) {
			if(tabel.getSelectedColumnCount() == 1 && tabel.getSelectedRowCount() == 1) {
				String cellContent = functionField.getText() + (String) tabel.getValueAt(tabel.getSelectedRow(), tabel.getSelectedColumn());
				functionField.setText(cellContent);
			}
		}
	}
	
	public void focusGained(FocusEvent e) {}

	/**
	 * Listens for all focusLost events emitted by the elements of the GUI
	 * @return void
	 */
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
