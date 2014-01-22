package com.awesome.excelpp.graph;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.SpreadSheet;

/**
 * The LineChart class constructs a <code>LineChart</code> from the data in a <code>SpreadSheet</code>.
 * @author Team Awesome
 */
public class LineChart extends JFrame{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a <code>lineChart</code> and adds it to a  <code>ChartPanel</code>.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>LineChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>LineChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>LineChart</code>
	 * @param mainTitle Main title of the chart
	 * @param titleX Title of the x-axis of the chart
	 * @param titleY Title of the y-axis of the chart
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>LineChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>LineChart</code>
	 */
	public LineChart(SpreadSheet sheet, int[] rows, int[] columns, String mainTitle, String titleX, String titleY) throws CellInputException, CellDataException{
		super("LineChart");
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		Utils.validate(rows);
		Utils.validate(columns);

		int startRow = rows[0];
		int endRow = rows[rows.length-1];
		if(endRow-startRow>1){
			ArrayList<String> horizontalNames = Utils.getHorizontalNames(sheet, rows, columns);
			ArrayList<String> verticalNames = Utils.getVerticalNames(sheet, rows, columns);
			ArrayList<Double> values = Utils.getValuesXRowsXColumns(sheet, rows, columns);
			data = createData(horizontalNames, verticalNames, values);
		} else {
			ArrayList<String> names = Utils.getNames(sheet, rows, columns);
			ArrayList<Double> values = Utils.getValues(sheet, rows, columns);
			data = createData(names, values);
		}

		JFreeChart  chart= createChart(data, mainTitle, titleX, titleY);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
	}
	
	/**
	 * Creates a <code>DefaultCategoryDataset</code> that can be used for drawing charts.
	 * @param horizontalNames If put into a table this would be the names of each column
	 * @param verticalNames If put into a table this would be the names of each row
	 * @param values ArrayList containing the values belonging to the horizontal and vertical names.
	 * @return <code>DefaultCategoryDataset</code> containing the data taken from horizontalNames, verticalNames and values
	 */
	static DefaultCategoryDataset createData(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(horizontalNames.size() * verticalNames.size() == values.size()){
			for(int i = 0; i<verticalNames.size(); i++){
				for(int i2 = 0; i2<horizontalNames.size(); i2++){
				res.setValue(values.get(i*horizontalNames.size() + i2), verticalNames.get(i) , horizontalNames.get(i2));
				}
			}
		} else {
			System.err.println("The arrayLists differ in size");
		}
		return res;
	}
	
	/**
	 * Creates a <code>DefaultCategoryDataset</code> that can be used for drawing charts.
	 * @param names ArrayList containing the names used for each line
	 * @param values ArrayList containing the values belonging to the names
	 * @return <code>DefaultCategoryDataset</code> containing the data taken from names and values
	 */
	public static DefaultCategoryDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(values.get(i),"f(x)", names.get(i));
			}
		} else {
			System.err.println("The arrayLists differ in size");
		}
		return res;
	}
	
	/**
	 * Creates a <code>LineChart</code>.
	 * @param data Contains the data that should be put into the <code>LineChart</code>
	 * @param mainTitle Main title of the chart
	 * @param titleX Title of the x-axis of the chart
	 * @param titleY Title of the y-axis of the chart
	 * @return <code>JFreeChart</code> chart created from the specified data
	 */
	private JFreeChart createChart(DefaultCategoryDataset data, String mainTitle, String titleX, String titleY){
		JFreeChart chart = ChartFactory.createLineChart(mainTitle,titleX, titleY, data);
		return chart;
	}
	
}
