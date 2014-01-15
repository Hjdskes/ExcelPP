package com.awesome.excelpp.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import com.awesome.excelpp.models.Cell;

/**
 * Custom CellEditor to preserve text markup and colors upon editing a Cell.
 * @author Team Awesome
 * ToDo: sla tekst op ook als er NIET op enter wordt gedrukt
 */
public class AwesomeCellEditor extends AbstractCellEditor implements TableCellEditor, KeyListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField textfield;
	private Cell currentCell;
	private int clickCountToStart;

	public AwesomeCellEditor () {
		this.textfield = new JTextField("");
		textfield.setActionCommand("Edit");
		textfield.addActionListener(this);
		textfield.addKeyListener(this);
		textfield.setBorder(null);

		this.clickCountToStart = 2;
		System.out.println("constructor");
	}

	@Override
	public Object getCellEditorValue() {
		return currentCell;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		currentCell = (Cell)value;
		Font currentFont = textfield.getFont();

		Font newFont = new Font(currentFont.getName(), currentCell.getItalic() | currentCell.getBold(), currentFont.getSize());
		textfield.setFont(newFont);

		textfield.setText(currentCell.getContent());
        return textfield;
	}

	public boolean  isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			return ((MouseEvent)anEvent).getClickCount() >= clickCountToStart;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Edit")) { //begin met edit
			String newText = currentCell.getContent() == null ? "" : currentCell.getContent();
			Font currentFont = textfield.getFont();

			Font newFont = new Font(currentFont.getName(), currentCell.getItalic() | currentCell.getBold(), currentFont.getSize());
			textfield.setFont(newFont);

			Color background = currentCell.getBackgroundColor();
			Color foreground = currentCell.getForegroundColor();

			textfield.setForeground(foreground);
			textfield.setBackground(background);

			textfield.setText(newText);
			fireEditingStopped(); //Renderer weer laten tekenen
		} /*else
			currentCell.setContent(textfield.getText());*/
	}

	@Override
	public final void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)
			currentCell.setContent(textfield.getText());
	}

	/**
	 * clickCountToStart controls the number of clicks required to start
	 * editing if the event passed to isCellEditable() or startCellEditing() is
	 * a MouseEvent.  For example, by default the clickCountToStart for
	 * a JTextField is set to 2, so in a JTable the user will need to
	 * double click to begin editing a cell.
	 */
	public int getClickCountToStart() {
		return clickCountToStart;
	}

	/**
	 * Specifies the number of clicks needed to start editing.
	 *
	 * @param count an int specifying the number of clicks needed to start editing
	 * @see #getClickCountToStart
	 */
	public void setClickCountToStart(int count) {
		this.clickCountToStart = count;
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}