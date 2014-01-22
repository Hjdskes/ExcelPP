package com.awesome.excelpp.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.awesome.excelpp.graph.BarChart;
import com.awesome.excelpp.graph.LineChart;
import com.awesome.excelpp.graph.PieChart;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.SpreadSheet;

/**
 * This class spawns a dialog window to configure the chart that is to be drawn.
 * @author Team Awesome
 */
public class GraphDialog extends JDialog implements ActionListener {
	static final long serialVersionUID = 1L;
	private static final String[] graphList = {"Bar chart", "Pie chart", "Line chart"};
	private static final String pieText = "<html><body style='width:200px'>Make sure you have selected the cells you want to transform "
				+ "into a pie chart and press the button below.";
	private static final String barText = "<html><body style='width:200px'>Make sure you have selected the cells you want to transform "
				+ "into a bar chart and press the button below.";
	private static final String lineText = "<html><body style='width:200px'>Make sure you have selected the cells you want to transform "
				+ "into a line chart and press the button below.";
	private int currentGraph = 0; //selected graph in the combobox. 0 = bar, 1 = pie, 2 = line.
	private SpreadSheet sheet;
	private int[] rows;
	private int[] columns;
	private static JComboBox<String> graphs;
	private static JLabel text;
	private static JTextField title;
	private static JTextField titleX;
	private static JTextField titleY;
	private static JCheckBox headers;
	private static JButton actionButton;

	/**
	 * Constructs the dialog to configure the chart that is to be drawn.
	 * @param table The <code>SpreadSheetTable</code> that contains the data
	 * @param image The image to set in the taskbar of the Desktop Environment
	 * @param width The width of the screen, in pixels, used to calculate the position of the dialog.
	 * @param height The height of the screen, in pixels, used to calculate the position of the dialog.
	 */
	public GraphDialog(SpreadSheetTable table, BufferedImage image, int screenWidth, int screenHeight) {
		this.sheet = table.getSheet();
		this.rows = table.getSelectedRows();
		this.columns = table.getSelectedColumns();

		setLayout (new BorderLayout());
		setIconImage(image);
		setSize(370, 320);
		setResizable(false);
		setLocation ((screenWidth / 2) - (this.getWidth() / 2), (screenHeight / 2) - (this.getWidth() / 2)); //center in het midden

		graphs = new JComboBox<String>(graphList);
		graphs.addActionListener(this);
		//ToDo: all of this inside a thin border?
		text = new JLabel(barText);
		title = new JTextField("Enter the title of the chart");
		titleX = new JTextField("Enter the title of the X-axis");
		titleY = new JTextField("Enter the title of the Y-axis");
		headers = new JCheckBox("Use custom headers (this will be the first row)");
		actionButton = new JButton("Draw the chart");
		actionButton.addActionListener(this);

		final JPanel graphsPanel = new JPanel();
		graphsPanel.setLayout(new BoxLayout(graphsPanel, BoxLayout.Y_AXIS));

		graphsPanel.add(text);
		graphsPanel.add(title);
		graphsPanel.add(titleX);
		graphsPanel.add(titleY);
		graphsPanel.add(headers);

		add(graphs, BorderLayout.PAGE_START);
		add(graphsPanel, BorderLayout.CENTER);
		add(actionButton, BorderLayout.PAGE_END);

		setVisible(true);
	}

	/**
	 * Draws a bar chart.
	 */
	public final void drawBarChart() {
		try {
			BarChart bchart = new BarChart(sheet, rows, columns, title.getText(), titleX.getText(), titleY.getText(), headers.isSelected());
			bchart.pack();
			bchart.setVisible(true);
			this.dispose();
		} catch(CellInputException c) {
			JOptionPane.showMessageDialog(this, "Please make sure your input for the first cell and the last cell is correct");
		} catch(CellDataException d) {
			JOptionPane.showMessageDialog(this, "Please make sure the Data entered in the table can be transformed to a piechart");
		}
	}

	/**
	 * Draws a pie chart.
	 */
	public final void drawPieChart() {
		try {
			PieChart pchart = new PieChart(sheet, rows, columns, title.getText(), headers.isSelected());
			pchart.pack();
			pchart.setVisible(true);
			this.dispose();
		} catch(CellInputException c) {
			JOptionPane.showMessageDialog(this, "Please make sure your input for the first cell and the last cell is correct");
		} catch(CellDataException d) {
			JOptionPane.showMessageDialog(this, "Please make sure the Data entered in the table can be transformed to a piechart");
		}
	}

	/**
	 * Draws a line chart.
	 */
	public final void drawLineChart() {
		try {
			LineChart lchart = new LineChart(sheet, rows, columns, title.getText(), titleX.getText(), titleY.getText(), headers.isSelected());
			lchart.pack();
			lchart.setVisible(true);
			this.dispose();
		} catch(CellInputException c) {
			JOptionPane.showMessageDialog(this, "Please make sure your input for the first cell and the last cell is correct");
		} catch(CellDataException d) {
			JOptionPane.showMessageDialog(this, "Please make sure the Data entered in the table can be transformed to a piechart");
		}
	}

	/**
	 * Listens for all events emitted by the elements of the dialog.
	 */
	public final void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(actionButton)) {
			switch(currentGraph) {
				case 0: drawBarChart();
						break;
				case 1: drawPieChart();
						break;
				case 2: drawLineChart();
						break;
				default: JOptionPane.showMessageDialog(this, "Something went wrong: could not figure out what chart to draw!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		} else if(e.getSource().equals(graphs)) {
			String selected = (String)graphs.getSelectedItem();
			if(selected.equals("Bar chart")) {
				currentGraph = 0;
				text.setText(barText);
				titleX.setEnabled(true);
				titleY.setEnabled(true);
			} else if (selected.equals("Pie chart")) {
				currentGraph = 1;
				text.setText(pieText);
				titleX.setEnabled(false);
				titleY.setEnabled(false);
			} else if (selected.equals("Line chart")) {
				currentGraph = 2;
				text.setText(lineText);
				titleX.setEnabled(true);
				titleY.setEnabled(true);
			}
		}
	}
}
