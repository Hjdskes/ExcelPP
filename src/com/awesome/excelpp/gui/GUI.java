package com.awesome.excelpp.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import javax.xml.parsers.ParserConfigurationException;
import javax.imageio.ImageIO;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.awesome.excelpp.graph.BarChart;
import com.awesome.excelpp.graph.LineChart;
import com.awesome.excelpp.graph.PieChart;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.readers.XML;
import com.awesome.excelpp.writers.XMLWriter;

/**
 * Class that constructs the main window of the GUI.
 * @author Team Awesome
 */
public class GUI extends JFrame implements ActionListener, KeyListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private static final int screenWidth = (int)Utils.getScreenWidth();
	private static final int screenHeight = (int)Utils.getScreenHeight();
	private static BufferedImage mainImage;
	private static JFrame mainFrame;
	private static JTextField functionField;
	private static JTabbedPane mainTabs;
	private static JComboBox<String> functions;
	private static JButton buttonAbout;
	private static JButton buttonBackgroundColor;
	private static JButton buttonForegroundColor;
	private static JButton buttonItalic;
	private static JButton buttonBold;
	private static JButton buttonRedo;
	private static JButton buttonUndo;
	private static JButton buttonSaveAs;
	private static JButton buttonSave;
	private static JButton buttonOpen;
	private static JButton buttonNewTab;
	private static JButton buttonNew;
	private static JButton buttonGraphs;

	/**
	 * Constructs the main window.
	 * @throws IOException
	 */
	public GUI () throws IOException {
		final JPanel buttonPanel = createButtonPanel();
		mainImage = ImageIO.read(new File("data/icons/gnumeric.png"));

		mainFrame = new JFrame ("Excel++");
		mainFrame.setLayout (new BorderLayout());
		mainFrame.setIconImage(mainImage);
		mainFrame.setSize (900, 400);
		mainFrame.setMinimumSize(buttonPanel.getPreferredSize()); //ToDo: andere oplossing?
		mainFrame.setLocation ((screenWidth / 2) - (mainFrame.getWidth() / 2), (screenHeight / 2) - (mainFrame.getHeight() / 2)); //center in het midden
		mainFrame.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(this);

		mainTabs = new JTabbedPane();
		createNewTab(null); //open altijd één tab

		mainFrame.add (buttonPanel, BorderLayout.PAGE_START);
		mainFrame.add (mainTabs, BorderLayout.CENTER);
		mainFrame.setVisible (true);
	}

	/**
	 * Creates a <code>JPanel</code> holding the required buttons.
	 * @return The <code>JPanel</code> holding all the required buttons
	 */
	private final JPanel createButtonPanel() {
		final FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.CENTER);
		layout.setAlignOnBaseline(true);
		final JPanel panel = new JPanel(layout);

		buttonNew = new JButton();
		buttonNewTab = new JButton();
		buttonOpen = new JButton();
		buttonSave = new JButton();
		buttonSaveAs = new JButton();
		buttonUndo = new JButton();
		buttonRedo = new JButton();
		functionField = new JTextField(40);
		buttonBold = new JButton();
		buttonItalic = new JButton();
		buttonForegroundColor = new JButton();
		buttonBackgroundColor = new JButton();
		buttonAbout = new JButton();
		buttonGraphs = new JButton("Graphs");
		
		String[] functionList = {"Average", "Count", "CountA", "CountIf", "If", "Int", "IsLogical",
								 "IsEven", "IsNumber", "Lower", "Max", "Median", "Min", "Mod", "Not",
								 "Or", "Power", "Product", "Proper", "RoundDown", "RoundUp", "Sign",
								 "SQRT", "Sum", "SumIf"};
		functions = new JComboBox<String>(functionList);

		final ImageIcon newIcon = new ImageIcon("data/icons/window-new.png");
		final ImageIcon newTabIcon = new ImageIcon("data/icons/stock_new-tab.png");
		final ImageIcon openIcon = new ImageIcon("data/icons/gtk-open.png");
		final ImageIcon saveIcon = new ImageIcon("data/icons/document-save.png");
		final ImageIcon saveIconAs = new ImageIcon("data/icons/document-save-as.png");
		final ImageIcon undoIcon = new ImageIcon("data/icons/edit-undo.png");
		final ImageIcon redoIcon = new ImageIcon("data/icons/edit-redo.png");
		final ImageIcon formulaIcon = new ImageIcon("data/icons/formula_piet2.png");
		final ImageIcon boldIcon = new ImageIcon("data/icons/format-text-bold.png");
		final ImageIcon italicIcon = new ImageIcon("data/icons/format-text-italic.png");
		final ImageIcon foregroundIcon = new ImageIcon("data/icons/color_line.png");
		final ImageIcon backgroundIcon = new ImageIcon("data/icons/emblem-art.png");
		final ImageIcon aboutIcon = new ImageIcon("data/icons/gtk-about.png");

		final JLabel formulaLabel = new JLabel(formulaIcon);

		buttonNew.setIcon(newIcon);
		buttonNewTab.setIcon(newTabIcon);
		buttonOpen.setIcon(openIcon);
		buttonSave.setIcon(saveIcon);
		buttonSaveAs.setIcon(saveIconAs);
		buttonUndo.setIcon(undoIcon);
		buttonRedo.setIcon(redoIcon);
		buttonBold.setIcon(boldIcon);
		buttonItalic.setIcon(italicIcon);
		buttonForegroundColor.setIcon(foregroundIcon);
		buttonBackgroundColor.setIcon(backgroundIcon);
		buttonAbout.setIcon(aboutIcon);

		buttonNew.setToolTipText("New file");
		buttonNewTab.setToolTipText("New tab");
		buttonOpen.setToolTipText("Open file");
		buttonSave.setToolTipText("Save file");
		buttonSaveAs.setToolTipText("Save as");
		buttonUndo.setToolTipText("Undo last change");
		buttonRedo.setToolTipText("Redo last change");
		buttonBold.setToolTipText("Make this cell's text bold");
		buttonItalic.setToolTipText("Make this cell's text italic");
		buttonForegroundColor.setToolTipText("Set this cell's foreground color");
		buttonBackgroundColor.setToolTipText("Set this cell's background color");
		buttonAbout.setToolTipText("About");
		buttonGraphs.setToolTipText("Graphs");

		panel.add(buttonNew);
		panel.add(buttonNewTab);
		panel.add(buttonOpen);
		panel.add(buttonSave);
		panel.add(buttonSaveAs);
		panel.add(buttonUndo);
		panel.add(buttonRedo);
		panel.add(functions);
		panel.add(formulaLabel);
		panel.add(functionField);
		panel.add(buttonBold);
		panel.add(buttonItalic);
		panel.add(buttonForegroundColor);
		panel.add(buttonBackgroundColor);
		panel.add(buttonGraphs);
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
		buttonSaveAs.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonUndo.addActionListener(this);
		buttonUndo.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonRedo.addActionListener(this);
		buttonRedo.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.SHIFT_DOWN_MASK | KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		functions.addActionListener(this);
		functionField.addKeyListener(this);
		buttonBold.addActionListener(this);
		buttonBold.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonItalic.addActionListener(this);
		buttonItalic.registerKeyboardAction(this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		buttonForegroundColor.addActionListener(this);
		buttonBackgroundColor.addActionListener(this);
		buttonAbout.addActionListener(this);
		buttonGraphs.addActionListener(this);

		return panel;
	}
	
	/**
	 * Opens a help dialog in which the user can get help on formulas and keyboard shortcuts.
	 */
	private final void openHelpDialog() {
		final JDialog helpDialog = new JDialog(this, "Help", true);
		final JPanel helpPanel = new JPanel();
		final JTabbedPane helpTabbedPane = new JTabbedPane();

		final String formulaText = "Implemented are the 25 formules one can see inside the combobox. "
				+ "We adhere to the intimplementation made by Microsoft Excel, so if any formula's use is unclear, please see their documentation.\n\n"
				+ "Our parser supports nested formulas and is tested up until a formula with a length of 30751 characters. Should your formula exceed"
				+ " this, we are very curious to hear about your results!\n\n"
				+ "The syntax is as follows: =Add(2+2);\n"
				+ "The '=' character indicates the start of a new formula. Hereafter follows the name of the formula, as seen in the combobox. "
				+ "Arguments are provided between brackets and are separated by commas. Formulas are ended by a semicolon."
				+ "Here is a more complicated example: =Add(Average(2,4,6), 5, Power(2,4));\n\n"
				+ "Regular math notation, such as =(2+2)*3;, is also supported. One can also make Cell references from other Cells: =A1.\n"
				+ "Cell ranges are not yet supported.";
		final JTextArea formulaPanel = new JTextArea(formulaText);
		formulaPanel.setEditable(false);
		formulaPanel.setLineWrap(true);
		formulaPanel.setWrapStyleWord(true);

		final String hotkeyText = "New file - Control + N\n"
				+ "Open file - Control + O\n"
				+ "Save file - Control + S\n"
				+ "Save file as - Control + Shift + S\n"
				+ "New tab - Control + T\n"
				+ "Close tab - Control + W\n"
				+ "Undo last change - Control + Z\n"
				+ "Redo last change - Control + Shift + Z\n"
				+ "Set current Cell's text bold - Control + B\n"
				+ "Set current Cell's text italic - Control + I\n\n"
				+ "Cel contents to textfield - Left mouse button\n"
				+ "Cel position to textfield - Alt + right mouse button";
		final JTextArea hotkeyPanel = new JTextArea(hotkeyText);
		hotkeyPanel.setEditable(false);
		hotkeyPanel.setLineWrap(false);

		final String aboutText = "Excel++ is a TU Delft project.\nCopyright 2013 Team Awesome.";
		final JTextArea aboutPanel = new JTextArea(aboutText);
		aboutPanel.setEditable(false);
		aboutPanel.setLineWrap(false);

		helpPanel.add(helpTabbedPane);
		helpTabbedPane.addTab("Formula Help", formulaPanel);
		helpTabbedPane.addTab("Hotkeys", hotkeyPanel);
		helpTabbedPane.addTab("About", aboutPanel);

		helpDialog.add(helpPanel);
		helpDialog.setIconImage(mainImage);
		helpDialog.setMinimumSize(new Dimension(292, 450));
		helpDialog.setResizable(true);
		helpDialog.setLocation ((screenWidth / 2) - (helpPanel.getPreferredSize().width / 2), (screenHeight / 2) - (helpPanel.getPreferredSize().height / 2)); //center in het midden
		
		helpDialog.setVisible(true);
	}
	
	public final void openGraphsDialog(){
		final SpreadSheetTable table = ((SpreadSheetScrollPane) mainTabs.getSelectedComponent()).getTable();
		final SpreadSheet sheet = table.getSheet();
		final int[] rows = table.getSelectedRows();
		final int[] columns = table.getSelectedColumns();
		
		final JDialog graphsDialog = new JDialog(this, "Charts", true);
		final JPanel graphsPanel = new JPanel();
		final JTabbedPane graphsTabbedPane = new JTabbedPane();
		
		final JTextField pieTitel = new JTextField();
		
		final JTextField barMainTitel = new JTextField();
		final JTextField barTitelX = new JTextField();
		final JTextField barTitelY = new JTextField();
		
		final JTextField lineMainTitel = new JTextField();
		final JTextField lineTitelX = new JTextField();
		final JTextField lineTitelY = new JTextField();
		
		JPanel pieChartPanel = new JPanel();
		JPanel pieChartPanel1 = new JPanel();
		JPanel pieChartPanel2 = new JPanel();
		JTextArea pieChartText = new JTextArea("Make sure you have selected the cells\nyou want to transform"
				+ "into a piechart\nand pressthe button below");
	
		JTextArea pieTitelText = new JTextArea("Enter the title of the chart:");
		final JButton pieChartButton = new JButton("Draw the chart");
		
		JPanel barChartPanel = new JPanel();
		JPanel barChartPanel1 = new JPanel();
		JPanel barChartPanel2 = new JPanel();
		JPanel barChartPanel3 = new JPanel();
		JPanel barChartPanel4 = new JPanel();
		JTextArea barChartText = new JTextArea("Make sure you have selected the cells\nyou want to transform"
				+ "into a barchart\nand pressthe button below");
		JTextArea barMainTitelText = new JTextArea("Enter the title of the chart (the header):");
		final JTextArea barTitelXText = new JTextArea("Enter the title of the X-axis");
		final JTextArea barTitelYText = new JTextArea("Enter the title of the Y-axis");
		final JButton barChartButton = new JButton("Draw the chart");
		
		JPanel lineChartPanel = new JPanel();
		JPanel lineChartPanel1 = new JPanel();
		JPanel lineChartPanel2 = new JPanel();
		JPanel lineChartPanel3 = new JPanel();
		JPanel lineChartPanel4 = new JPanel();
		JTextArea lineChartText = new JTextArea("Make sure you have selected the cells\nyou want to transform"
				+ "into a linechart\nand pressthe button below");
		JTextArea lineMainTitelText = new JTextArea("Enter the title of the chart (the header):");
		final JTextArea lineTitelXText = new JTextArea("Enter the title of the X-axis");
		final JTextArea lineTitelYText = new JTextArea("Enter the title of the Y-axis");
		final JButton lineChartButton = new JButton("Draw the chart");
		
		
		
		pieChartPanel.setLayout(new BoxLayout(pieChartPanel,BoxLayout.Y_AXIS));
		barChartPanel.setLayout(new BoxLayout(barChartPanel,BoxLayout.Y_AXIS));
		lineChartPanel.setLayout(new BoxLayout(lineChartPanel,BoxLayout.Y_AXIS));
		
		pieChartPanel.add(pieChartPanel1);
		pieChartPanel.add(pieChartPanel2);
		pieChartPanel.add(pieChartButton);
		pieChartPanel1.add(pieChartText);
		
		barChartPanel.add(barChartPanel1);
		barChartPanel.add(barChartPanel2);
		barChartPanel.add(barChartPanel3);
		barChartPanel.add(barChartPanel4);
		barChartPanel.add(barChartButton);
		barChartPanel1.add(barChartText);
		
		lineChartPanel.add(lineChartPanel1);
		lineChartPanel.add(lineChartPanel2);
		lineChartPanel.add(lineChartPanel3);
		lineChartPanel.add(lineChartPanel4);
		lineChartPanel.add(lineChartButton);
		lineChartPanel1.add(lineChartText);
		
		pieChartPanel2.add(pieTitelText);
		pieChartPanel2.add(pieTitel);
		pieChartPanel2.setLayout(new BoxLayout(pieChartPanel2, BoxLayout.Y_AXIS));
		
		barChartPanel2.add(barMainTitelText);
		barChartPanel2.add(barMainTitel);
		barChartPanel2.setLayout(new BoxLayout(barChartPanel2, BoxLayout.Y_AXIS));
		
		lineChartPanel2.add(lineMainTitelText);
		lineChartPanel2.add(lineMainTitel);
		lineChartPanel2.setLayout(new BoxLayout(lineChartPanel2, BoxLayout.Y_AXIS));
		
		barChartPanel3.add(barTitelXText);
		barChartPanel3.add(barTitelX);
		barChartPanel3.setLayout(new BoxLayout(barChartPanel3, BoxLayout.Y_AXIS));
		
		lineChartPanel3.add(lineTitelXText);
		lineChartPanel3.add(lineTitelX);
		lineChartPanel3.setLayout(new BoxLayout(lineChartPanel3, BoxLayout.Y_AXIS));
		
		barChartPanel4.add(barTitelYText);
		barChartPanel4.add(barTitelY);
		barChartPanel4.setLayout(new BoxLayout(barChartPanel4, BoxLayout.Y_AXIS));
		
		lineChartPanel4.add(lineTitelYText);
		lineChartPanel4.add(lineTitelY);
		lineChartPanel4.setLayout(new BoxLayout(lineChartPanel4, BoxLayout.Y_AXIS));
		
		pieChartText.setEditable(false);
		pieTitelText.setEditable(false);
		
		barChartText.setEditable(false);
		barMainTitelText.setEditable(false);
		barTitelXText.setEditable(false);
		barTitelYText.setEditable(false);
		
		lineChartText.setEditable(false);
		lineMainTitelText.setEditable(false);
		lineTitelXText.setEditable(false);
		lineTitelYText.setEditable(false);
		
		pieChartPanel2.setPreferredSize(new Dimension(20,20));
		
		pieChartText.setAlignmentX(RIGHT_ALIGNMENT);
		pieChartButton.setAlignmentX(CENTER_ALIGNMENT);
		
		barChartText.setAlignmentX(RIGHT_ALIGNMENT);
		barChartButton.setAlignmentX(CENTER_ALIGNMENT);
		
		lineChartText.setAlignmentX(RIGHT_ALIGNMENT);
		lineChartButton.setAlignmentX(CENTER_ALIGNMENT);
		
		
		ActionListener graphsAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(pieChartButton)){
					String titelString = pieTitel.getText();
					try{
						PieChart chart = new PieChart(sheet, rows, columns, titelString);
						chart.pack();
						chart.setVisible(true);
						graphsDialog.dispose();
					} catch(CellInputException c){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure your input for the first cell and the last cell is correct");
					} catch(CellDataException d){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure the Data entered in the table can be transformed to a piechart");
					}
					
				}
				
				if(e.getSource().equals(barChartButton)){
					String mainTitelString = barMainTitel.getText();
					String titelXString = barTitelX.getText();
					String titelYString = barTitelY.getText();
					try{
						BarChart chart = new BarChart(sheet, rows, columns, mainTitelString, titelXString, titelYString);
						chart.pack();
						chart.setVisible(true);
						graphsDialog.dispose();
					} catch(CellInputException c){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure your input for the first cell and the last cell is correct");
					} catch(CellDataException d){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure the Data entered in the table can be transformed to a barchart");
					}
					
				}
				
				if(e.getSource().equals(lineChartButton)){
					String mainTitelString = lineMainTitel.getText();
					String titelXString = lineTitelX.getText();
					String titelYString = lineTitelY.getText();
					try{
						LineChart chart = new LineChart(sheet, rows, columns, mainTitelString, titelXString, titelYString);
						chart.pack();
						chart.setVisible(true);
						graphsDialog.dispose();
					} catch(CellInputException c){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure your input for the first cell and the last cell is correct");
					} catch(CellDataException d){
						JOptionPane.showMessageDialog(mainFrame, "Please make sure the Data entered in the table can be transformed to a linechart");
					}
					
				}
			}
			
		};
		
		pieChartButton.addActionListener(graphsAction);
		barChartButton.addActionListener(graphsAction);
		lineChartButton.addActionListener(graphsAction);

		
		graphsPanel.add(graphsTabbedPane);
		graphsTabbedPane.addTab("PieChart", pieChartPanel);
		graphsTabbedPane.addTab("BarChart", barChartPanel);
		graphsTabbedPane.addTab("LineChart", lineChartPanel);
		
		graphsDialog.add(graphsPanel);
		graphsDialog.setIconImage(mainImage);
		graphsDialog.setMinimumSize(new Dimension(370, 350));
		graphsDialog.setResizable(true);
		graphsDialog.setLocation ((screenWidth / 2) - (graphsPanel.getPreferredSize().width / 2), (screenHeight / 2) - (graphsPanel.getPreferredSize().height / 2)); //center in het midden
		
		graphsDialog.setVisible(true);
		
		

	}

	/**
	 * Creates a new tab with everything setup inside it.
	 * @param file The file to open inside the new <code>SpreadSheetTable</code>
	 */
	public final void createNewTab(File file) {
		SpreadSheet sheet = new SpreadSheet();
		SpreadSheetTable table = new SpreadSheetTable(sheet, file);
		SpreadSheetScrollPane pane = new SpreadSheetScrollPane(table);
		String tabTitle = "New file";
		mainTabs.addTab(null, null, pane, tabTitle); // Add tab to pane without label or icon but with tooltip
		int tabCount = mainTabs.getTabCount();
		tabCount = tabCount == 0 ? 0 : (tabCount - 1);
		mainTabs.setTabComponentAt(tabCount, new CloseableTabComponent(tabTitle, tabCount)); // Now assign the component for the tab
		mainTabs.setSelectedIndex(tabCount);
		//ToDo: mainTabs.setMnemonicAt(tabCount, KeyEvent.VK_(tabCount + 1));
	}

	/**
	 * Removes the currently active tab. Makes sure there is always one tab remaining.
	 */
	public static final void removeTab(int index) {
		int tabCount = mainTabs.getTabCount();
		if(tabCount > 1) { //er moet ten minste één tab open blijven
			if (closeFile(index) == 0) {
				CloseableTabComponent[] tabComponents = new CloseableTabComponent[tabCount];
				for (int i = index; i < tabCount; i++) {
					tabComponents[i] = (CloseableTabComponent)mainTabs.getTabComponentAt(i);
				}
				mainTabs.remove(index);
				for(int i = tabCount - 1; i >= index; i--) { //"herstel" alle indices van de CloseableTabComponents
					tabComponents[i].setIndex(i - 1);
				}
			}
		}
	}

	/**
	 * Updates the tab's title.
	 * @param index	The index of the tab to update
	 * @param newTitle the new title to set
	 */
	private final void updateTabTitle(int index, String newTitle) {
		CloseableTabComponent currentComponent = (CloseableTabComponent)mainTabs.getTabComponentAt(index);
		currentComponent.setTitle(newTitle);
	}

	/**
	 * Gets a tab's title.
	 * @param index	The index of the tab to get the title from
	 * @return The title
	 */
	private static final String getTabTitle(int index) {
		CloseableTabComponent currentComponent = (CloseableTabComponent)mainTabs.getTabComponentAt(index);
		return currentComponent.getTitle();
	}

	/**
	 * Helper method to set the text in the function field.
	 * @param text The text to set in the function field
	 */
	public static void functionFieldSetText (String text) {
		functionField.setText(text);
	}

	/**
	 * Helper method to get the text from the function field.
	 * @return The text from the function field
	 */
	public static String functionFieldGetText() {
		return functionField.getText();
	}

	/**
	 * Changes markup in the currently selected <code>Cells</code>.
	 * @param index	The index of the tab whose <code>Cells</code> need to be updated
	 * @param bold True if the boldness is subject to change
	 */
	public final void changeMarkup(int index, boolean bold) {
		Cell current = null;
		SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
		SpreadSheetTable table = scrollPane.getTable();
		int[] row = table.getSelectedRows();
		int[] column = table.getSelectedColumns();

		for (int i = 0; i < column.length; i++) {
			for (int j = 0; j < row.length; j++) {
				current = (Cell)table.getValueAt(row[j], column[i]);
	
				if(bold == true) {
					int bolde = current.getBold()  == 0 ? 1 : 0;
					current.setBold(bolde, true);
				} else if(bold == false) {
					int italic = current.getItalic() == 0 ? 2 : 0;
					current.setItalic(italic, true);
				}
			}
		}

		table.grabFocus();
	}

	/**
	 * Changes the fore- and background <code>Colors</code> in the currently selected <code>Cells</code>.
	 * @param index	The index of the tab whose <code>Cells</code> need to be updated
	 * @param foreground True if the foreground <code>Color</code> is subject to change
	 */
	public final void changeColors(int index, boolean foreground) {
		Color newColor = null;
		Cell current = null;
		SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
		SpreadSheetTable table = scrollPane.getTable();
		int[] row = table.getSelectedRows();
		int[] column = table.getSelectedColumns();

		if(foreground == false)
			newColor = JColorChooser.showDialog(this, "Choose a background color", buttonBackgroundColor.getBackground());
		else if(foreground == true)
			newColor = JColorChooser.showDialog(this, "Choose a foreground color", buttonForegroundColor.getBackground());

		for (int i = 0; i < column.length; i++) {
			for (int j = 0; j < row.length; j++) {
				current = (Cell)table.getValueAt(row[j], column[i]);

				if(newColor != null && foreground == false)
					current.setBackgroundColor(newColor, true);
				else if(newColor != null && foreground == true)
					current.setForegroundColor(newColor, true);
			}
		}

		if(newColor != null && foreground == false)
			buttonBackgroundColor.setBackground(newColor);
		else if(newColor != null && foreground == true)
			buttonForegroundColor.setBackground(newColor);

		table.grabFocus();
	}

	/**
	 * Listens for all events emitted by the elements of the GUI.
	 */
	public final void actionPerformed(ActionEvent e) {
		int index = mainTabs.getSelectedIndex();

		if (e.getSource().equals(buttonOpen))
			openFile();
		else if (e.getSource().equals(buttonNew)) {
			if(closeFile(index) == 0) {
				SpreadSheet newSheet = new SpreadSheet();
				SpreadSheetTable newTable = new SpreadSheetTable(newSheet, null);
				SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
				scrollPane.setTable(newTable);
				updateTabTitle(index, "New file");
			}
		} else if (e.getSource().equals(buttonNewTab))
			createNewTab(null);
		else if (e.getSource().equals(buttonSave))
			saveFile(false);
		else if (e.getSource().equals(buttonSaveAs))
			saveFile(true);
		else if (e.getSource().equals(buttonBold))
			changeMarkup(index, true);
		else if (e.getSource().equals(buttonItalic))
			changeMarkup(index, false);
		else if (e.getSource().equals(buttonForegroundColor))
			changeColors(index, true);
		else if (e.getSource().equals(buttonBackgroundColor))
			changeColors(index, false);
		else if (e.getSource().equals(buttonAbout))
			openHelpDialog();
		else if (e.getSource().equals(functions)) {
			String formula = "=" + (String)functions.getSelectedItem();
			functionField.setText(formula + "(");
		} else if (e.getSource().equals(buttonUndo)) {
			SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
			UndoManager manager = scrollPane.getTable().getUndoManager();
			if (manager.canUndo() == true)
				manager.undo();
		} else if (e.getSource().equals(buttonRedo)) {
			SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
			UndoManager manager = scrollPane.getTable().getUndoManager();
			if (manager.canRedo() == true)
				manager.redo();
		} else if(e.getSource().equals(buttonGraphs)){
			openGraphsDialog();
		}
	}

	/**
	 * Listens for all KeyPressed events emitted by the elements of the GUI.
	 */
	@Override
	public final void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getSelectedComponent();
			SpreadSheetTable table = scrollPane.getTable();
			if (table.getCellSelected() == false)
				JOptionPane.showMessageDialog(this, "Please select a Cell first.", "No Cell selected", JOptionPane.INFORMATION_MESSAGE);
			else {
				Cell current = (Cell) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				current.setContent(functionField.getText(), true);
			}
		}
	}

	/**
	 * Is executed right before the window closes. Used to do some clean ups and check if files
	 * need to be saved.
	 */
	@Override
	public final void windowClosing(WindowEvent e) {
		for(int i = 0; i < mainTabs.getTabCount(); i++) {
			if(closeFile(i) == 1) {
				JOptionPane.showMessageDialog(this, "Please save your files and try again.", "Save your files", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		System.exit(0); //ToDo: niet de beste oplossing? Bastiaan zei dat we het zo konden laten.
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}

	/**
	 * Opens a file dialog in which the user can select the file to open.
	 */
	public final void openFile() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fc.setFileFilter(filter);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			int index = mainTabs.getSelectedIndex();
			File file = fc.getSelectedFile();
			SpreadSheetTable table;
			SpreadSheet sheet;
			try {
				Document doc = XML.parse(file);
				sheet = XML.print(doc);
				table = new SpreadSheetTable(sheet, file);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				sheet = new SpreadSheet();
				table = new SpreadSheetTable(sheet, null);
				ex.printStackTrace();
			} catch (ParserConfigurationException | SAXException ex) {
				JOptionPane.showMessageDialog(this, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				sheet = new SpreadSheet();
				table = new SpreadSheetTable(sheet, file);
				ex.printStackTrace();
			}
			SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
			scrollPane.setTable(table);
			updateTabTitle(index, file.getName());
		}
	}

	/**
	 * Saves the currently opened file.
	 * @param saveAs True if the Save As button has been pressed, will spawn a Save As dialog
	 */
	public final void saveFile (boolean saveAs) {
		SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getSelectedComponent();
		SpreadSheetTable table = scrollPane.getTable();
		File file = table.getFile();
		if (file == null || saveAs == true) {
			final JFileChooser fc = new JFileChooser();
			int choice = fc.showSaveDialog(this);
			if (choice == JFileChooser.APPROVE_OPTION) {
				String fileString = fc.getSelectedFile().getPath();
				if (!fileString.endsWith(".xml"))
					fileString += ".xml";
				file = new File(fileString);
				table.setFile(file);
				updateTabTitle(mainTabs.getSelectedIndex(), file.getName());
			} else if(choice == JFileChooser.CANCEL_OPTION)
				return;
		}
		
		try {
			((SpreadSheet)table.getModel()).write(new XMLWriter(file));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(this, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	/**
	 * Handles closing of a file. Pops up a dialog for confirmation if changes will be lost.
	 * @param index	The index of the tab whose <code>File</code> will be closed
	 * @return 0 for OK, 1 for cancel
	 */
	public static final int closeFile(int index) {
		int close = 0;
		SpreadSheetScrollPane scrollPane = (SpreadSheetScrollPane)mainTabs.getComponentAt(index);
		SpreadSheetTable table = scrollPane.getTable();
		if(getTabTitle(index).equals("New file")) {
			if(table.getSheet().isEmpty() == false)
				close = JOptionPane.showConfirmDialog(mainFrame, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
		} else {
			try {
				Document doc = XML.parse(table.getFile());
				SpreadSheet fileSheet = XML.print(doc);
				if(!table.getSheet().equals(fileSheet))
					close = JOptionPane.showConfirmDialog(mainFrame, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(mainFrame, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		return close;
	}
}
