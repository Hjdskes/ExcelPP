
package com.awesome.excelpp.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
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

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.readers.XML;
import com.awesome.excelpp.writers.XMLWriter;

/**
 * Class that constructs everything needed for and by the GUI
 * ToDo: fix color buttons
 *         make them more distinct
 *         coloring whole button with last selected color is a bit too much?
 *       even display color when cell is selected?
 *       color multiple cells at the same time
 */
public class GUI extends JFrame implements ActionListener, KeyListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private static final int screenWidth = (int)Utils.getScreenWidth();
	private static final int screenHeight = (int)Utils.getScreenHeight();
	private static final ArrayList<SpreadSheetScrollPane> panes = new ArrayList<SpreadSheetScrollPane>();
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

	public GUI () throws IOException {
		final JPanel buttonPanel = createButtonPanel();
		BufferedImage mainImage = ImageIO.read(new File("data/icons/gnumeric.png"));
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
	 * Creates a JPanel holding the required buttons
	 * @return JPanel
	 */
	private final JPanel createButtonPanel() {
		FlowLayout layout = new FlowLayout();
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
		buttonBold = new JButton("Bold");
		buttonItalic = new JButton("Italic");
		buttonForegroundColor = new JButton();
		buttonBackgroundColor = new JButton();
		buttonAbout = new JButton();
		String[] functionList = {"Average", "Count", "CountA", "CountIf", "If", "Int", "IsLogical", "IsEven", "IsNumber", "Lower", "Max", "Median", "Min", "Mod", "Not", "Or", "Power", "Product", "Proper", "RoundDown", "RoundUp", "Sign", "SQRT", "Sum", "SumIf"};
		functions = new JComboBox<String>(functionList);
		functions.setSelectedIndex(0);

		final ImageIcon newIcon = new ImageIcon("data/icons/window-new.png");
		final ImageIcon newTabIcon = new ImageIcon("data/icons/stock_new-tab.png");
		final ImageIcon openIcon = new ImageIcon("data/icons/gtk-open.png");
		final ImageIcon saveIcon = new ImageIcon("data/icons/document-save.png");
		final ImageIcon saveIconAs = new ImageIcon("data/icons/document-save-as.png");
		final ImageIcon undoIcon = new ImageIcon("data/icons/edit-undo.png");
		final ImageIcon redoIcon = new ImageIcon("data/icons/edit-redo.png");
		final ImageIcon colorIcon = new ImageIcon("data/icons/gnome-colors.png");
		final ImageIcon aboutIcon = new ImageIcon("data/icons/gtk-about.png");

		buttonNew.setIcon(newIcon);
		buttonNewTab.setIcon(newTabIcon);
		buttonOpen.setIcon(openIcon);
		buttonSave.setIcon(saveIcon);
		buttonSaveAs.setIcon(saveIconAs);
		buttonUndo.setIcon(undoIcon);
		buttonRedo.setIcon(redoIcon);
		buttonForegroundColor.setIcon(colorIcon);
		buttonBackgroundColor.setIcon(colorIcon);
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

		panel.add(buttonNew);
		panel.add(buttonNewTab);
		panel.add(buttonOpen);
		panel.add(buttonSave);
		panel.add(buttonSaveAs);
		panel.add(buttonUndo);
		panel.add(buttonRedo);
		panel.add(functions);
		panel.add(functionField);
		panel.add(buttonBold);
		panel.add(buttonItalic);
		panel.add(buttonForegroundColor);
		panel.add(buttonBackgroundColor);
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
		buttonItalic.addActionListener(this);
		buttonForegroundColor.addActionListener(this);
		buttonBackgroundColor.addActionListener(this);
		buttonAbout.addActionListener(this);

		return panel;
	}
	
	/**
	 * Opens a help dialog in which the user can get help on formulas and keyboard shortcuts
	 * @return void
	 */
	private final void openHelpDialog() {
		JDialog helpDialog = new JDialog(this, "Help");
		JPanel helpPanel = new JPanel();
		JTabbedPane helpTabbedPane = new JTabbedPane();
		
		JPanel formulaPanel = new JPanel();
		JLabel formulaText = new JLabel("<html>Help for formulas<br>Alt + left mouse click to get cel content into entry</html>");
		formulaPanel.add(formulaText);
		
		JPanel hotkeyPanel = new JPanel();
		JLabel hotkeyText = new JLabel("<html>Open file - Control + O<br>New file - Control + N<br>Save file - Control + S<br>New tab - Control + T<br><hr>Cel contents to textfield - Alt + left mouse button<br>Cel position to textfield - Alt + right mouse button</html>");
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
	 * Creates a new tab with everything setup inside it
	 * @return void
	 */
	public final void createNewTab(File file) {
		SpreadSheet sheet = new SpreadSheet();
		SpreadSheetTable table = new SpreadSheetTable(sheet, file);
		SpreadSheetScrollPane pane = new SpreadSheetScrollPane(table);
		int last = panes.size();
		panes.add(pane);
		String tabTitle = "New file";
		mainTabs.addTab(null, null, pane, tabTitle); // Add tab to pane without label or icon but with tooltip
		mainTabs.setTabComponentAt(last, new CloseableTabComponent(tabTitle)); // Now assign the component for the tab
		mainTabs.setSelectedIndex(last);
		//ToDo: mainTabs.setMnemonicAt(last, KeyEvent.VK_(last + 1));
	}

	/**
	 * Removes the currently active tab. Makes sure there is always one tab remaining.
	 * @return void
	 */
	public static final void removeTab() {
		if(mainTabs.getTabCount() > 1) { //er moet ten minste één tab open blijven
			int index = mainTabs.getSelectedIndex();
			if (closeFile(index) == 0) {
				mainTabs.remove(index);
				panes.remove(index);
			}
		}
	}

	/**
	 * Updates the tab's title.
	 * @param newTitle - the new title to set.
	 * @return void
	 */
	private final void updateTabTitle(int index, String newTitle) {
		CloseableTabComponent currentComponent = (CloseableTabComponent)mainTabs.getTabComponentAt(index);
		currentComponent.setTitle(newTitle);
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

	/**
	 * Listens for all events emitted by the elements of the GUI
	 * Calls other functions
	 * @return void
	 */
	public final void actionPerformed(ActionEvent e) {
		int index = mainTabs.getSelectedIndex();

		if (e.getSource().equals(buttonOpen))
			openFile();
		else if (e.getSource().equals(buttonNew)) {
			if(closeFile(index) == 0) {
				SpreadSheet newSheet = new SpreadSheet();
				SpreadSheetTable newTable = new SpreadSheetTable(newSheet, null);
				panes.get(index).setTable(newTable);
				updateTabTitle(index, "New file");
			}
		} else if (e.getSource().equals(buttonNewTab))
			createNewTab(null);
		else if (e.getSource().equals(buttonSave))
			saveFile(false);
		else if (e.getSource().equals(buttonSaveAs))
			saveFile(true);
		else if (e.getSource().equals(buttonBold)) {
			int row = panes.get(index).getTable().getSelectedRow();
			int column = panes.get(index).getTable().getSelectedColumn();
			Cell current = (Cell)panes.get(index).getTable().getValueAt(row, column);
			if (current != null) {
				int bold = current.getBold()  == 0 ? 1 : 0;
				current.setBold(bold);
			} else
				JOptionPane.showMessageDialog(this, "Please select a Cell first.", "No Cell selected", JOptionPane.INFORMATION_MESSAGE);
			panes.get(index).getTable().grabFocus();
		} else if (e.getSource().equals(buttonItalic)) {
			int row = panes.get(index).getTable().getSelectedRow();
			int column = panes.get(index).getTable().getSelectedColumn();
			Cell current = (Cell)panes.get(index).getTable().getValueAt(row, column);
			if (current != null) {
				int italic = current.getItalic() == 0 ? 2 : 0;
				current.setItalic(italic);
			} else
				JOptionPane.showMessageDialog(this, "Please select a Cell first.", "No Cell selected", JOptionPane.INFORMATION_MESSAGE);
			panes.get(index).getTable().grabFocus();
		} else if (e.getSource().equals(buttonForegroundColor)) {
			Color foreground = null;
			int row = panes.get(index).getTable().getSelectedRow();
			int column = panes.get(index).getTable().getSelectedColumn();
			Cell current = (Cell)panes.get(index).getTable().getValueAt(row, column);
			if(current != null) {
				foreground = JColorChooser.showDialog(this, "Choose a background color", current.getForegroundColor());
				if(foreground != null) {
					panes.get(index).getTable().setCellForeground(current, foreground);
					buttonForegroundColor.setBackground(foreground);
				}
			} else
				JOptionPane.showMessageDialog(this, "Please select a Cell first.", "No Cell selected", JOptionPane.INFORMATION_MESSAGE);
			panes.get(index).getTable().grabFocus();
		} else if (e.getSource().equals(buttonBackgroundColor)) {
			Color background = null;
			int row = panes.get(index).getTable().getSelectedRow();
			int column = panes.get(index).getTable().getSelectedColumn();
			Cell current = (Cell)panes.get(index).getTable().getValueAt(row, column);
			if (current != null) {
				background = JColorChooser.showDialog(this, "Choose a background color", current.getBackgroundColor());
				if(background != null) {
					panes.get(index).getTable().setCellBackground(current, background);
					buttonBackgroundColor.setBackground(background);
				}
			} else
				JOptionPane.showMessageDialog(this, "Please select a Cell first.", "No Cell selected", JOptionPane.INFORMATION_MESSAGE);
			panes.get(index).getTable().grabFocus();
		} else if (e.getSource().equals(buttonAbout))
			openHelpDialog();
		else if (e.getSource().equals(functions)) {
			String formula = "=" + (String)functions.getSelectedItem();
			functionField.setText(formula + "(");
		} else if (e.getSource().equals(buttonUndo)) {
			UndoManager manager = panes.get(index).getTable().getUndoManager();
			if (manager.canUndo() == true)
				manager.undo();
		} else if (e.getSource().equals(buttonRedo)) {
			UndoManager manager = panes.get(index).getTable().getUndoManager();
			if (manager.canRedo() == true)
				manager.redo();
		}
	}

	/**
	 * Listens for all KeyPressed events emitted by the elements of the GUI
	 * @return void
	 */
	@Override
	public final void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			int index = mainTabs.getSelectedIndex();
			panes.get(index).getTable().setValueAt(functionField.getText(), panes.get(index).getTable().getSelectedRow(), panes.get(index).getTable().getSelectedColumn());
		}
	}

	/**
	 * Is executed right before the window closes. We use this to do some clean ups and check if files
	 * need to be saved.
	 */
	@Override
	public final void windowClosing(WindowEvent e) {
		for(int i = 0; i < panes.size(); i++) {
			if(closeFile(i) == 1) {
				JOptionPane.showMessageDialog(this, "Please save your files and try again.", "Save your files", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		System.exit(0); //ToDo: niet de beste oplossing?
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
	 * Opens a file dialog in which the user can select the file to open
	 * @return void
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
			panes.get(index).setTable(table);
			updateTabTitle(index, file.getName());
		}
	}

	/**
	 * Saves the currently opened file
	 * @return void
	 */
	public final void saveFile (boolean saveAs) {
		SpreadSheetScrollPane pane = panes.get(mainTabs.getSelectedIndex());
		SpreadSheetTable table = pane.getTable();
		File file = null;
		if (table.getFile() == null || saveAs == true) {
			final JFileChooser fc = new JFileChooser();
			int choice = fc.showSaveDialog(this);
			if (choice == JFileChooser.APPROVE_OPTION) {
				String fileString = fc.getSelectedFile().getPath();
                fileString = fileString.replaceAll("\\...*", "");
                fileString += ".xml";
				file = new File(fileString);
				table.setFile(file);
				updateTabTitle(mainTabs.getSelectedIndex(), file.getName());
			} else if(choice == JFileChooser.CANCEL_OPTION)
				return;
		}
		
		try {
			if(file != null)
				((SpreadSheet)table.getModel()).write(new XMLWriter(file));
			else
				System.err.println("File is null");
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(this, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	/**
	 * Handles closing of a file. Pops up a dialog for confirmation if changes will be lost.
	 * @param index
	 * @return int - 0 for OK, 1 for cancel
	 */
	public static final int closeFile(int index) {
		int close = 0;
		if(panes.get(index).getTable().getUndoManager().canUndo() == true) //ToDo: misschien niet de beste oplossing
			close = JOptionPane.showConfirmDialog(mainFrame, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
		return close;
	}
}
