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

	public BarChart(ArrayList<String> names, ArrayList<Double> values, String mainTitle, String titleX, String titleY){
		super("BarChart");
		DefaultCategoryDataset data = createData(names, values);
		JFreeChart  chart= createChart(data, mainTitle, titleX, titleY);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
	}
	
	private DefaultCategoryDataset createData(ArrayList<String> names, ArrayList<Double> values){
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
	
	private JFreeChart createChart(DefaultCategoryDataset data, String mainTitle, String titleX, String titleY){
		JFreeChart chart = ChartFactory.createBarChart3D(mainTitle,titleX, titleY, data);
		return chart;
	}
	
	public static ArrayList<String> getNames(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		ArrayList<String> res= PieChart.getNames(sheet, startCell, endCell);
		return res;
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		ArrayList<Double> res = PieChart.getValues(sheet, startCell, endCell);
		return res;
	}

}
