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
 * The Barchart class constructs a <code>BarChart</code> from the data in a <code>SpreadSheet</code>.
 * @author Team Awesome
 */
public class BarChart extends JFrame{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>BarChart</code> and adds it to a <code>ChartPanel</code>.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>BarChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>BarChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>BarChart</code>
	 * @param mainTitle Main title of the chart
	 * @param titleX Title of the x-axis of the chart
	 * @param titleY Title of the y-axis of the chart
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public BarChart(SpreadSheet sheet, int[] rows, int[] columns, String mainTitle, String titleX, String titleY) throws CellInputException, CellDataException{
		super("BarChart");
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		PieChart.validate(rows);
		PieChart.validate(columns);
		int startRow = rows[0];
		int endRow = rows[rows.length-1];
		if(endRow-startRow>1){
			ArrayList<String> horizontalNames = getHorizontalNames(sheet, rows, columns);
			ArrayList<String> verticalNames = getVerticalNames(sheet, rows, columns);
			ArrayList<Double> values = getValuesXRowsXcolumns(sheet, rows, columns);
			data = createData(horizontalNames, verticalNames, values);
		} else{
			ArrayList<String> names = getNames(sheet, rows, columns);
			ArrayList<Double> values = getValues(sheet, rows, columns);
			data = createData(names, values);
		}

		JFreeChart  chart= createChart(data, mainTitle, titleX, titleY);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
	}

	/**
	 * Creates a <code>DefaultCategoryDataset</code> that can be used for drawing charts.
	 * @param names ArrayList containing the names used for each bar
	 * @param values ArrayList containing the values belonging to the names
	 * @return <code>DefaultCategoryDataset</code> containing the data taken from names and values
	 */
	public static DefaultCategoryDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(values.get(i),names.get(i) , "");
			}
		}
		else {
			System.err.println("The arrayLists differ in size");
		}

		return res;
	}
	
	/**
	 * Creates a <code>DefaultCategoryDataset</code> that can be used for drawing charts.
	 * @param horizontalNames If put into a table this would be the names of each column
	 * @param verticalNames If put into a table this would be the names of each row
	 * @param values ArrayList containing the values belonging to the horizontal and vertical names.
	 * @return <code>DefaultCategoryDataset</code> containing the data taken from horizontalNames, verticalNames and values
	 */
	private DefaultCategoryDataset createData(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values){
		return LineChart.createData(horizontalNames, verticalNames, values);
	}

	/**
	 * Creates a <code>BarChart</code>.
	 * @param data Contains the data that should be put into the <code>BarChart</code>
	 * @param mainTitle Main title of the chart
	 * @param titleX Title of the x-axis of the chart
	 * @param titleY Title of the y-axis of the chart
	 * @return <code>JFreeChart</code> chart created from the specified data
	 */
	private JFreeChart createChart(DefaultCategoryDataset data, String mainTitle, String titleX, String titleY){
		JFreeChart chart = ChartFactory.createBarChart3D(mainTitle,titleX, titleY, data);
		return chart;
	}

	/**
	 * Retrieves the names of each column (for a 2 rows by x columns <code>BarChart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>BarChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>BarChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>BarChart</code>
	 * @return <code>ArrayList</code> containing all the names
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public static ArrayList<String> getNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		ArrayList<String> res= PieChart.getNames(sheet, rows, columns);
		return res;
	}

	/**
	 * Retrieves the values of the cells (for a 2 rows by x columns <code>BarChart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>BarChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>BarChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>BarChart</code>
	 * @return <code>ArrayList</code> containing all the values
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public static ArrayList<Double> getValues(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		ArrayList<Double> res = PieChart.getValues(sheet, rows, columns);
		return res;
	}

	/**
	 * Retrieves the names of each column (for a x rows by x columns <code>BarChart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>BarChart</code>
	 * @param rows rows Array of the selected rows that will be put into the <code>Barchart</code>
	 * @param columns Array of the selected columns that will be put into the <code>Barchart</code>
	 * @return <code>ArrayList</code> containing all the names of the columns
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public static ArrayList<String> getHorizontalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getHorizontalNames(sheet, rows, columns);
	}

	/**
	 * Retrieves the names of each row (for a x rows by x columns <code>BarChart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to put into a <code>BarChart</code>
	 * @param rows rows Array of the selected rows that will be put into the <code>BarChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>BarChart</code>
	 * @return <code>ArrayList</code> containing all the names of the columns
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public static ArrayList<String> getVerticalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getVerticalNames(sheet, rows, columns);
	}

	/**
	 * Retrieves the values of the </code>Cells</code> (for a x rows by x columns <code>BarChart</code>).
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>BarChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>BarChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>BarChart</code>
	 * @return <code>ArrayList</code> containing all the values
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>BarChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>BarChart</code>
	 */
	public static ArrayList<Double> getValuesXRowsXcolumns(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getValuesXRowsXColumns(sheet, rows, columns);
	}
}
