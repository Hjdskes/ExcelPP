
package com.awesome.excelpp.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
import javax.imageio.ImageIO;

import org.w3c.dom.Document;

import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;
import com.awesome.excelpp.readers.XML;
import com.awesome.excelpp.writers.SysOutWriter;

/**
 * Class that constructs everything needed for and by the GUI
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
		createNewTab(); //open altijd één tab

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
		functionField = new JTextField(50);
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
		final ImageIcon aboutIcon = new ImageIcon("data/icons/gtk-about.png");

		buttonNew.setIcon(newIcon);
		buttonNewTab.setIcon(newTabIcon);
		buttonOpen.setIcon(openIcon);
		buttonSave.setIcon(saveIcon);
		buttonSaveAs.setIcon(saveIconAs);
		buttonUndo.setIcon(undoIcon);
		buttonRedo.setIcon(redoIcon);
		buttonAbout.setIcon(aboutIcon);

		buttonNew.setToolTipText("New file");
		buttonNewTab.setToolTipText("New tab");
		buttonOpen.setToolTipText("Open file");
		buttonSave.setToolTipText("Save file");
		buttonSaveAs.setToolTipText("Save as");
		buttonUndo.setToolTipText("Undo last change");
		buttonRedo.setToolTipText("Redo last change");
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
		buttonAbout.addActionListener(this);

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
	 * Creates a new tab with a new SpreadSheetTable inside it
	 * @return void
	 */
	private final void createNewTab() {
		SpreadSheetTable table = new SpreadSheetTable();
		SpreadSheetScrollPane pane = new SpreadSheetScrollPane(table);
		
		int last = panes.size();
		panes.add(pane);
		mainTabs.addTab(null, null, pane, "New File"); // Add tab to pane without label or icon but with tooltip
		mainTabs.setTabComponentAt(last, new CloseableTabComponent("New File")); // Now assign the component for the tab
		mainTabs.setSelectedIndex(last);
		//ToDo: mainTabs.setMnemonicAt(last, KeyEvent.VK_(last + 1));
	}

	/**
	 * Removes the currently active tab. Makes sure there is always one tab remaining.
	 * @return void
	 */
	final static void removeTab() {
		int index = mainTabs.getSelectedIndex();
		if(mainTabs.getTabCount() > 1) { //er moet ten minste één tab open blijven
			mainTabs.remove(index);
			panes.remove(index);
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

	/**
	 * Listens for all events emitted by the elements of the GUI
	 * Calls other functions
	 * @return void
	 */
	public final void actionPerformed(ActionEvent e) {
		int index = mainTabs.getSelectedIndex();

		if (e.getSource().equals(buttonOpen)) {
				openFileDialog();
		} else if (e.getSource().equals(buttonNew)) {
			//TODO: New file
			//if(panes.get(index).newFile() == 0)
				//updateTabTitle(index, panes.get(index).getFileString());
		} else if (e.getSource().equals(buttonNewTab))
			createNewTab();
		else if (e.getSource().equals(buttonSave)) {
			//TODO: Save file
			//if(panes.get(index).saveFile() == 0)
				//updateTabTitle(index, panes.get(index).getFileString());
		} else if (e.getSource().equals(buttonSaveAs)) {
			//TODO: Save as
			//if(panes.get(index).openSaveDialog() == 0)
				//updateTabTitle(index, panes.get(index).getFileString());
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
			SpreadSheetTable currentSheet = panes.get(i).getTable();
			//TODO:
			//if(currentPane.closeFile() == 1) {
				//JOptionPane.showMessageDialog(mainFrame, "Please save your files and try again.", "Save your files", JOptionPane.INFORMATION_MESSAGE);
				//return;
			//}
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
	 * @return int - choice made: 0 for opened, 1 for cancel.
	 */
	public final int openFileDialog() {
		int opened = 1;
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
		fc.setFileFilter(filter);
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				Document doc = XML.parse(file);
				SpreadSheet sheet = XML.print(doc);
				sheet.write(new SysOutWriter());
				SpreadSheetTable table = new SpreadSheetTable(sheet, file);
				SpreadSheetScrollPane pane = new SpreadSheetScrollPane(table);
				
				int last = panes.size();
				panes.add(pane);
				mainTabs.addTab(null, null, pane, table.getFileString()); // Add tab to pane without label or icon but with tooltip
				mainTabs.setTabComponentAt(last, new CloseableTabComponent(table.getFileString())); // Now assign the component for the tab
				mainTabs.setSelectedIndex(last);
				opened = 0; // Return 0, zoals closeFile
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println ("debug: " + ex.getMessage());
			}
		}
		return opened;
	}

	/**
	 * Properly handles opening a new file - spawns a dialog if changes will be lost
	 * @return int - 0 for OK, 1 for cancel.
	 *
	public final int newFile () {
		int res = 1;
		if(closeFile() == 0) {
			try {
				removeTempFile(file);
				file = File.createTempFile("excelpp_temp", ".xml");
				res = 0;
				this.setModel (new SpreadSheet());
				this.updateUI();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Can't open a temporary file.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		return res;
	}*/

	/**
	 * Saves the currently opened file
	 * @return int - choice made: 0 for saved, 1 for cancel.
	 *
	public final int saveFile () {
		int saved = 1;
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			saved = openSaveDialog();
		else {
			//try {
				//sheet.write(new XMLWriter(file));
				saved = 0;
			//} catch (FileNotFoundException ex) {
				//JOptionPane.showMessageDialog(this, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				//ex.printStackTrace();
			//}
		}
		return saved;
	}*/

	/**
	 * Properly handles closing of a file - spawns a dialog if changes will be lost
	 * @return int - choice made: 0 for OK, 1 for cancel.
	 *
	public final int closeFile () {
		int close = 1;
		try {
			if (file.toString().contains(System.getProperty("java.io.tmpdir")) == false) {
				Document doc = XML.parse(file);
				SpreadSheet fileSheet = XML.print(doc);
				if(!this.getModel().equals(fileSheet))
					close = JOptionPane.showConfirmDialog(this, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
			} else {
				//if(sheet.isEmpty() == false) {
					//close = JOptionPane.showConfirmDialog(tabel, "Changes made to the current spreadsheet will be lost. Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
					//if(close == 0) {
						//if(file.delete() != true)
							//JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
					//}
				//} else {
					//if(file.delete() != true)
						//JOptionPane.showMessageDialog(tabel, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
					//else
						//close = 0;
				//}
			}
		} catch (Exception ex) {
			System.err.println (ex.getMessage());
		}
		return close;
	}*/

	/**
	 * Opens a file dialog in which the user can select where to save the current SpreadSheet
	 * @return int - choice made: 0 for saved, 1 for cancel.
	 *
	public final int openSaveDialog() {
		int saved = 1;
		final JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			String fileString = fc.getSelectedFile().getPath();
			fileString = fileString.replaceAll("\\...*", "");
			fileString += ".xml";
			removeTempFile(file);
			file = new File(fileString);
			//try {
				//file.getParentFile().mkdirs();
				//sheet.write(new XMLWriter(file));
				saved = 0;
			//} catch (FileNotFoundException ex) {
				//JOptionPane.showMessageDialog(tabel, "Something went wrong: " + ex.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
				//ex.printStackTrace();
			//}
		}
		return saved;
	}*/

	/**
	 * Checks if parameter file is a temporary file and if so, tries to delete it
	 * @param file
	 * @return void
	 *
	private final void removeTempFile(File file) {
		if (file.getAbsolutePath().contains(System.getProperty("java.io.tmpdir")) == true)
			if(file.delete() != true)
				JOptionPane.showMessageDialog(this, "Can not delete temporary file", "Warning", JOptionPane.WARNING_MESSAGE);
	}*/
}
