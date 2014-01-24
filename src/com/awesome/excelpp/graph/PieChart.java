package com.awesome.excelpp.graph;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.SpreadSheet;

/**
 * The PieChart class constructs a <code>PieChart</code> from the data in a <code>SpreadSheet</code>.
 * @author Team Awesome
 */
public class PieChart extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>PieChart</code> and adds it to a <code>ChartPanel</code>.
	 * @param sheet <code>SpreadSheet</code> containing the <code>Cells</code> you want to transform into a <code>LineChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>LineChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>LineChart</code>
	 * @param title The title of the chart
	 * @param headers if true user made headers will be used
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>LineChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>LineChart</code>
	 */
	public PieChart(SpreadSheet sheet, int[] rows, int[] columns, String title, boolean headers) throws CellInputException, CellDataException{
		super("PieChart");
		Utils.validate(rows);
		Utils.validate(columns);
		ArrayList<String> names = Utils.getNames(sheet, rows, columns, headers);
		ArrayList<Double> values = Utils.getValues(sheet, rows, columns);
		PieDataset data = createData(names, values);
		JFreeChart  chart= createChart(data, title);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
		
	}
	
	/**
	 * Creates a <code>DefaultCategoryDataset</code> that can be used for drawing charts.
	 * @param names ArrayList containing the names used for each slice
	 * @param values ArrayList containing the values belonging to the names
	 * @return <code>DefaultCategoryDataset</code> containing the data taken from names and values
	 */
	private PieDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultPieDataset res = new DefaultPieDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(names.get(i), values.get(i));
			}
		} else {
			System.err.println("The arrayLists differ in size");
		}
		return res;
	}
	
	/**
	 * Creates a <code>PieChart</code>.
	 * @param data Contains the data that should be put into the <code>PieChart</code>
	 * @param title The title of the chart
	 * @return The chart created from the specified data
	 */
	private JFreeChart createChart(PieDataset data, String title){
		JFreeChart chart = ChartFactory.createPieChart3D(title, data, true, true, false);
		return chart;
	}
	
}
