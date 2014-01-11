package com.awesome.excelpp.gui;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.awesome.excelpp.models.Cell;

/**
 * Use a JTable as a renderer for row numbers of a given main table.
 * This table must be added to the row header of the scrollpane that
 * contains the main table.
 *
 * @author Rob Camick, modified by Team Awesome
 * @source http://tips4java.wordpress.com/2008/11/18/row-number-table/
 */
public class RowNumberTable extends JTable implements ChangeListener, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private JTable main;

	public RowNumberTable(TableCellRenderer renderer, JTable table) {
		main = table;
		main.addPropertyChangeListener (this);

		setFocusable(false);
		setAutoCreateColumnsFromModel (false);
		setModel(main.getModel());
		setSelectionModel(main.getSelectionModel());

		TableColumn column = new TableColumn();
		column.setHeaderValue(" ");
		addColumn(column);

		column.setCellRenderer(renderer);

		getColumnModel().getColumn(0).setPreferredWidth(50);
		setPreferredScrollableViewportSize(getPreferredSize());
	}

	@Override
	public void addNotify() {
		super.addNotify();

		Component c = getParent();

		//  Keep scrolling of the row table in sync with the main table.
		if (c instanceof JViewport) {
			JViewport viewport = (JViewport)c;
			viewport.addChangeListener (this);
		}
	}

	/*
	 * Delegate method to main table
	 */
	@Override
	public int getRowCount() {
		return main.getRowCount();
	}

	@Override
	public int getRowHeight(int row) {
		return main.getRowHeight(row);
	}

	/*
	 * This table does not use any data from the main TableModel,
	 * so just return a value based on the row parameter.
	 */
	@Override
	public Cell getValueAt(int row, int column) {
		return (new Cell(null, Integer.toString(row + 1)));
	}

	/*
	 * Don't edit data in the main TableModel by mistake
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/*
	 * Implement the ChangeListener
	 */
	public void stateChanged(ChangeEvent e) {
		//  Keep the scrolling of the row table in sync with main table
		JViewport viewport = (JViewport) e.getSource();
		JScrollPane scrollPane = (JScrollPane)viewport.getParent();
		scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
	}

	/*
	 * Implement the PropertyChangeListener
	 */
	public void propertyChange(PropertyChangeEvent e) {
		//  Keep the row table in sync with the main table
		if ("selectionModel".equals(e.getPropertyName())) {
			setSelectionModel( main.getSelectionModel() );
		}

		if ("model".equals(e.getPropertyName())) {
			setModel( main.getModel() );
		}
	}
}
