package com.awesome.excelpp.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.awesome.excelpp.graph.BarChart;
import com.awesome.excelpp.graph.LineChart;
import com.awesome.excelpp.graph.PieChart;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.SpreadSheet;

public class GraphDialog extends JDialog implements ActionListener {
	static final long serialVersionUID = 1L;
	private SpreadSheet sheet;
	private int[] rows;
	private int[] columns;
	private static JButton pieChartButton;
	private static JTextField pieTitel;
	private static JTextField barMainTitel;
	private static JTextField barTitelX;
	private static JTextField barTitelY;
	private static JButton barChartButton;
	private static JButton lineChartButton;
	private static JTextField lineMainTitel;
	private static JTextField lineTitelX;
	private static JTextField lineTitelY;

	public GraphDialog(SpreadSheetTable table, BufferedImage image, int width, int height) {
		this.sheet = table.getSheet();
		this.rows = table.getSelectedRows();
		this.columns = table.getSelectedColumns();

		final JDialog graphsDialog = new JDialog(); /*ToDo: frame, title and modal*/
		final JPanel graphsPanel = new JPanel();
		final JTabbedPane graphsTabbedPane = new JTabbedPane();

		JPanel pieChartPane = createPieChartPane();
		JPanel barChartPane = createBarChartPane();
		JPanel lineChartPane = createLineChartPane();

		graphsPanel.add(graphsTabbedPane);
		graphsTabbedPane.addTab("PieChart", pieChartPane);
		graphsTabbedPane.addTab("BarChart", barChartPane);
		graphsTabbedPane.addTab("LineChart", lineChartPane);

		graphsDialog.add(graphsPanel);
		graphsDialog.setIconImage(image);
		graphsDialog.setMinimumSize(new Dimension(370, 350));
		graphsDialog.setResizable(true);
		graphsDialog.setLocation ((width / 2) - (graphsPanel.getPreferredSize().width / 2), (height / 2) - (graphsPanel.getPreferredSize().height / 2)); //center in het midden

		graphsDialog.setVisible(true);
	}

	public final JPanel createPieChartPane() {
		pieTitel = new JTextField();

		JPanel pieChartPanel = new JPanel();
		JPanel pieChartPanel1 = new JPanel();
		JPanel pieChartPanel2 = new JPanel();
		JTextArea pieChartText = new JTextArea("Make sure you have selected the cells you want to transform"
				+ "into a piechart and press the button below.");
		pieChartText.setLineWrap(true);
		pieChartText.setWrapStyleWord(true);

		JTextArea pieTitelText = new JTextArea("Enter the title of the chart:");
		pieChartButton = new JButton("Draw the chart");

		pieChartPanel.setLayout(new BoxLayout(pieChartPanel,BoxLayout.Y_AXIS));

		pieChartPanel.add(pieChartPanel1);
		pieChartPanel.add(pieChartPanel2);
		pieChartPanel.add(pieChartButton);
		pieChartPanel1.add(pieChartText);

		pieChartPanel2.add(pieTitelText);
		pieChartPanel2.add(pieTitel);
		pieChartPanel2.setLayout(new BoxLayout(pieChartPanel2, BoxLayout.Y_AXIS));

		pieChartText.setEditable(false);
		pieTitelText.setEditable(false);

		pieChartPanel2.setPreferredSize(new Dimension(20,20));
		pieChartText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pieChartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		pieChartButton.addActionListener(this);

		return pieChartPanel;
	}

	public final JPanel createBarChartPane() {
		barMainTitel = new JTextField();
		barTitelX = new JTextField();
		barTitelY = new JTextField();

		JPanel barChartPanel = new JPanel();
		JPanel barChartPanel1 = new JPanel();
		JPanel barChartPanel2 = new JPanel();
		JPanel barChartPanel3 = new JPanel();
		JPanel barChartPanel4 = new JPanel();
		JTextArea barChartText = new JTextArea("Make sure you have selected the cells\nyou want to transform"
				+ "into a barchart\nand press the button below");
		JTextArea barMainTitelText = new JTextArea("Enter the title of the chart (the header):");
		final JTextArea barTitelXText = new JTextArea("Enter the title of the X-axis");
		final JTextArea barTitelYText = new JTextArea("Enter the title of the Y-axis");
		barChartButton = new JButton("Draw the chart");

		barChartPanel.setLayout(new BoxLayout(barChartPanel,BoxLayout.Y_AXIS));

		barChartPanel2.add(barMainTitelText);
		barChartPanel2.add(barMainTitel);
		barChartPanel2.setLayout(new BoxLayout(barChartPanel2, BoxLayout.Y_AXIS));

		barChartPanel3.add(barTitelXText);
		barChartPanel3.add(barTitelX);
		barChartPanel3.setLayout(new BoxLayout(barChartPanel3, BoxLayout.Y_AXIS));

		barChartPanel4.add(barTitelYText);
		barChartPanel4.add(barTitelY);
		barChartPanel4.setLayout(new BoxLayout(barChartPanel4, BoxLayout.Y_AXIS));

		barChartText.setEditable(false);
		barMainTitelText.setEditable(false);
		barTitelXText.setEditable(false);
		barTitelYText.setEditable(false);

		barChartPanel.add(barChartPanel1);
		barChartPanel.add(barChartPanel2);
		barChartPanel.add(barChartPanel3);
		barChartPanel.add(barChartPanel4);
		barChartPanel.add(barChartButton);
		barChartPanel1.add(barChartText);

		barChartText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		barChartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		barChartButton.addActionListener(this);

		return barChartPanel;
	}

	public final JPanel createLineChartPane() {
		lineMainTitel = new JTextField();
		lineTitelX = new JTextField();
		lineTitelY = new JTextField();

		JPanel lineChartPanel = new JPanel();
		JPanel lineChartPanel1 = new JPanel();
		JPanel lineChartPanel2 = new JPanel();
		JPanel lineChartPanel3 = new JPanel();
		JPanel lineChartPanel4 = new JPanel();
		JTextArea lineChartText = new JTextArea("Make sure you have selected the cells\nyou want to transform"
				+ "into a linechart\nand press the button below");
		JTextArea lineMainTitelText = new JTextArea("Enter the title of the chart (the header):");
		final JTextArea lineTitelXText = new JTextArea("Enter the title of the X-axis");
		final JTextArea lineTitelYText = new JTextArea("Enter the title of the Y-axis");
		lineChartButton = new JButton("Draw the chart");

		lineChartPanel.setLayout(new BoxLayout(lineChartPanel,BoxLayout.Y_AXIS));

		lineChartPanel.add(lineChartPanel1);
		lineChartPanel.add(lineChartPanel2);
		lineChartPanel.add(lineChartPanel3);
		lineChartPanel.add(lineChartPanel4);
		lineChartPanel.add(lineChartButton);
		lineChartPanel1.add(lineChartText);

		lineChartPanel2.add(lineMainTitelText);
		lineChartPanel2.add(lineMainTitel);
		lineChartPanel2.setLayout(new BoxLayout(lineChartPanel2, BoxLayout.Y_AXIS));

		lineChartPanel3.add(lineTitelXText);
		lineChartPanel3.add(lineTitelX);
		lineChartPanel3.setLayout(new BoxLayout(lineChartPanel3, BoxLayout.Y_AXIS));

		lineChartPanel4.add(lineTitelYText);
		lineChartPanel4.add(lineTitelY);
		lineChartPanel4.setLayout(new BoxLayout(lineChartPanel4, BoxLayout.Y_AXIS));

		lineChartText.setEditable(false);
		lineMainTitelText.setEditable(false);
		lineTitelXText.setEditable(false);
		lineTitelYText.setEditable(false);

		lineChartText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lineChartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		lineChartButton.addActionListener(this);

		return lineChartPanel;
	}

	/**
	 * Listens for all events emitted by the elements of the GUI.
	 */
	public final void actionPerformed(ActionEvent e) {
		Object button = e.getSource();

		if(button.equals(pieChartButton)) {
			String titelString = pieTitel.getText();
			try {
				PieChart chart = new PieChart(sheet, rows, columns, titelString);
				chart.pack();
				chart.setVisible(true);
				this.dispose();
			} catch(CellInputException c) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure your input for the first cell and the last cell is correct");
			} catch(CellDataException d) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure the Data entered in the table can be transformed to a piechart");
			}
		} else if(button.equals(barChartButton)) {
			String mainTitelString = barMainTitel.getText();
			String titelXString = barTitelX.getText();
			String titelYString = barTitelY.getText();
			try {
				BarChart chart = new BarChart(sheet, rows, columns, mainTitelString, titelXString, titelYString);
				chart.pack();
				chart.setVisible(true);
				this.dispose();
			} catch(CellInputException c) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure your input for the first cell and the last cell is correct");
			} catch(CellDataException d) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure the Data entered in the table can be transformed to a barchart");
			}
		} else if(button.equals(lineChartButton)) {
			String mainTitelString = lineMainTitel.getText();
			String titelXString = lineTitelX.getText();
			String titelYString = lineTitelY.getText();
			try {
				LineChart chart = new LineChart(sheet, rows, columns, mainTitelString, titelXString, titelYString);
				chart.pack();
				chart.setVisible(true);
				this.dispose();
			} catch(CellInputException c) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure your input for the first cell and the last cell is correct");
			} catch(CellDataException d) { /*ToDo: add frame*/
				JOptionPane.showMessageDialog(null, "Please make sure the Data entered in the table can be transformed to a linechart");
			}
		}
	}
}
