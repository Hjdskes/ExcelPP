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

public class BarChart extends JFrame{
	
	
	private static final long serialVersionUID = 1L;

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
	
	public static DefaultCategoryDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(values.get(i),names.get(i) , "");
			}
		}
		
		else{
			System.err.println("The arrayLists differ in size");
		}
		
		
		return res;
	}
	
	private DefaultCategoryDataset createData(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values){
		return LineChart.createData(horizontalNames, verticalNames, values);
	}
	
	private JFreeChart createChart(DefaultCategoryDataset data, String mainTitle, String titleX, String titleY){
		JFreeChart chart = ChartFactory.createBarChart3D(mainTitle,titleX, titleY, data);
		return chart;
	}
	
	public static ArrayList<String> getNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		ArrayList<String> res= PieChart.getNames(sheet, rows, columns);
		return res;
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		ArrayList<Double> res = PieChart.getValues(sheet, rows, columns);
		return res;
	}
	
	public static ArrayList<String> getHorizontalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getHorizontalNames(sheet, rows, columns);
	}
	
	public static ArrayList<String> getVerticalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getVerticalNames(sheet, rows, columns);
	}
	
	public static ArrayList<Double> getValuesXRowsXcolumns(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return LineChart.getValuesXRowsXColumns(sheet, rows, columns);
	}
}
