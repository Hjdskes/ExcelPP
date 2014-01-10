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
 * @author Tad Harrison, modified by us
 * @source http://paperjammed.com/2012/11/22/adding-tab-close-buttons-to-a-jtabbedpane-in-java-swing/
 */
public class CloseableTabComponent extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final ImageIcon tabIcon = new ImageIcon("data/icons/stock_edit.png");
	private static final ImageIcon closeTabIcon = new ImageIcon ("data/icons/tab_close_gray.png");
	private static final ImageIcon closeTabIconRollover = new ImageIcon ("data/icons/tab_close.png");
	private JLabel tabTitle;
	private JButton buttonClose;

	/**
	 * Creates a component to add to a JTabbedPane with a little "close tab" 
	 * button on the right side of the tab.
	 * @param title - the title to be displayed by the tab.
	 */
	public CloseableTabComponent (String title) {
		super(new FlowLayout(FlowLayout.CENTER, 5, 0));
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

	public void setTitle(String newTitle) {
		tabTitle.setText(newTitle);
	}

	public String getTitle() {
		return tabTitle.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GUI.removeTab();
	}
}
