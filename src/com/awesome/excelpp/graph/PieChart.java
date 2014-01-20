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
import com.awesome.excelpp.models.Cell;
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
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>LineChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>LineChart</code>
	 */
	public PieChart(SpreadSheet sheet, int[] rows, int[] columns, String title) throws CellInputException, CellDataException{
		super("PieChart");
		validate(rows);
		validate(columns);
		ArrayList<String> names = getNames(sheet, rows, columns);
		ArrayList<Double> values = getValues(sheet, rows, columns);
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

	/**
	 * Retrieves the names of each column.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>PieChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>PieChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>PieChart</code>
	 * @return <code>ArrayList</code> containing all the names
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>PieChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>PieChart</code>
	 */
	public static ArrayList<String> getNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int firstRow; int secondRow; int startInt; int endInt;
		try {
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt =columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e){
			throw new CellInputException();
		}

		ArrayList<String> res = new ArrayList<String>();

		if(endInt-startInt>0 && secondRow-firstRow == 1 && startInt >= 0  && endInt >= 0){
			for(int i = 0; i<=endInt-startInt; i++){
				if(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent() != null){
					res.add(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent());
				} else {
					throw new CellDataException();
				}
			}
		} else {
			throw new CellInputException();
		}

		return res;
	}
	
	/**
	 * Retrieves the values of the <code>Cells</code>.
	 * @param sheet <code>SpreadSheet</code> containing the cells you want to transform into a <code>PieChart</code>
	 * @param rows Array of the selected rows that will be put into the <code>PieChart</code>
	 * @param columns Array of the selected columns that will be put into the <code>PieChart</code>
	 * @return <code>ArrayList</code> containing all the values
	 * @throws CellInputException Thrown when the rows or columns is not suited for a <code>PieChart</code>
	 * @throws CellDataException Thrown when the content of the cells is not suited for a <code>PieChart</code>
	 */
	public static ArrayList<Double> getValues(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int firstRow; int secondRow; int startInt; int endInt; Double add;
		try {
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt = columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e) {
			throw new CellInputException();
		}

		ArrayList<Double> res = new ArrayList<Double>();

		if(endInt-startInt>0 && secondRow-firstRow == 1 && startInt>=0 && endInt >= 0){
		for(int i = 0; i<=endInt-startInt; i++){
				try {
					add = Double.parseDouble(((Cell) sheet.getValueAt(secondRow, startInt + i)).toString());
					res.add(add);
				} catch(Exception e) {
					throw new CellDataException();
				}
			}
		} else {
			throw new CellInputException();
		}

		return res;
	}
	
	/**
	 * Checks if the <code>Array</code> contains subsequent <code>Integers</code>.
	 * @param array <code>Array</code> that has to be validated
	 * @throws CellInputException Thrown if the <code>Array</code> does not contain subsequent Integers
	 */
	public static void validate(int[] array) throws CellInputException{
		for(int i = 0; i<array.length-1; i++){
			if(!(array[i] + 1 == array[i+1])){
				throw new CellInputException();
			}
		}
	}
}
