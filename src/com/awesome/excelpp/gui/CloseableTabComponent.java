package com.awesome.excelpp.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * This class makes a <code>Component</code> with a label and an icon, to be used inside tabs.
 * <p>Source: http://paperjammed.com/2012/11/22/adding-tab-close-buttons-to-a-jtabbedpane-in-java-swing/</p>
 *  @author Tad Harrison. Modified by Team Awesome.
 */
public class CloseableTabComponent extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final ImageIcon tabIcon = new ImageIcon("data/icons/stock_edit.png");
	private static final ImageIcon closeTabIcon = new ImageIcon ("data/icons/tab_close_gray.png");
	private static final ImageIcon closeTabIconRollover = new ImageIcon ("data/icons/tab_close.png");
	private JLabel tabTitle;
	private JButton buttonClose;
	private int index;

	/**
	 * Creates a component to add to a <code>JTabbedPane</code> with a little "close tab" 
	 * button on the right side of the tab.
	 * @param title	The title to be displayed on the tab
	 * @param index The initial index of the tab inside the <code>JTabbedPane</code>
	 */
	public CloseableTabComponent (String title, int index) {
		super(new FlowLayout(FlowLayout.CENTER, 5, 0));

		this.index = index;

		// Make a small JPanel with the flow layout, spacing things 5px apart, and make it non-opaque
		setOpaque(false);

		// Add a JLabel with title and the left-side tab icon
		tabTitle = new JLabel(title);
		tabTitle.setIcon(tabIcon);

		// Create a JButton for the close tab button
		buttonClose = new JButton();
		buttonClose.setOpaque(false);
		buttonClose.setContentAreaFilled(false); // Do not draw white-ish glow around icon
		buttonClose.setBorder(null); // Set border null so the button doesn't make the tab too big
		buttonClose.setFocusable(false); // Make sure the button can't get focus, otherwise it looks funny
		buttonClose.setRolloverIcon(closeTabIconRollover); // Configure icon and rollover icon for button
		buttonClose.setRolloverEnabled(true); // Configure icon and rollover icon for button
		buttonClose.setIcon(closeTabIcon); // Configure icon and rollover icon for button
		buttonClose.setToolTipText("Close this tab");

		buttonClose.addActionListener(this);
		buttonClose.registerKeyboardAction (this, "pressed", KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

		add(tabTitle);
		add(buttonClose);
		setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0)); // Add a thin border to keep the image below the top edge of the tab when the tab is selected
	}

	/**
	 * Sets the title of this tab.
	 * @param newTitle	The new title to set
	 */
	public void setTitle(String newTitle) {
		tabTitle.setText(newTitle);
	}

	/**
	 * Returns the title of this tab.
	 * @return The title of this tab
	 */
	public String getTitle() {
		return tabTitle.getText();
	}

	/**
	 * Sets the index of this tab.
	 * @param newIndex The new index to set
	 */
	public void setIndex(int newIndex) {
		index = newIndex;
	}

	/**
	 * Returns the index of this tab.
	 * @return The index of this tab
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Listens for all events emitted by the elements of this tab.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		GUI.removeTab(index);
	}
}
