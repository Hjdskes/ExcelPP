package com.awesome.excelpp.graph;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;


import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.awesome.excelpp.gui.SpreadSheetTable;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

public class PieChart extends JFrame{
	public PieChart(ArrayList<String> names, ArrayList<Double> values, String title){
		super("PieChart");
		PieDataset data = createData(names, values);
		JFreeChart  chart= createChart(data, title);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
		
	}
	
	
	private PieDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultPieDataset res = new DefaultPieDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(names.get(i), values.get(i));
			}
		}
		
		else{
			System.err.println("The arrayLists differ in size");
		}
		
		
		return res;
	}
	
	private JFreeChart createChart(PieDataset data, String title){
		JFreeChart chart = ChartFactory.createPieChart3D(title, data, true, true, false);
		return chart;
	}
	
	public static ArrayList<String> getNames(SpreadSheet sheet, String startCell, String endCell){
		char start = startCell.charAt(0);
		char end = endCell.charAt(0);
		int firstRow = Integer.parseInt(startCell.substring(1))-1;
		int startInt = Character.toUpperCase(start) - 65;
		int endInt = Character.toUpperCase(end) - 65;
		
		ArrayList<String> res = new ArrayList<String>();
		
		for(int i = 0; i<=endInt-startInt; i++){
			res.add(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent());
		}
		
		return res;
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, String startCell, String endCell){
		char start = startCell.charAt(0);
		char end = endCell.charAt(0);
		int secondRow = Integer.parseInt(endCell.substring(1))-1;
		int startInt = Character.toUpperCase(start) - 65;
		int endInt = Character.toUpperCase(end) - 65;
		
		ArrayList<Double> res = new ArrayList<Double>();
		
		for(int i = 0; i<=endInt-startInt; i++){
			if(!((Cell) sheet.getValueAt(secondRow, startInt + i)).getContent().toString().startsWith("#")){
				System.out.println(((Cell) sheet.getValueAt(secondRow, startInt + i)).toString());
				res.add(Double.parseDouble(((Cell) sheet.getValueAt(secondRow, startInt + i)).toString()));
			} else{
				System.err.println("Couldn't parse to cell");
			}
		}
		return res;
		
		
	}
	
	
	 public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Double> values = new ArrayList<Double>();
        names.add("hurr");
        
        names.add("durr");
        names.add("test");
        names.add("dfegf");
        
        values.add(1.0);
        values.add(5.0);
        values.add(10.0);
        values.add(6.0);
        
        PieChart test = new PieChart(names, values, "test");
        test.pack();
        test.setVisible(true);
     }
	
}
