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

public class PieChart extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


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
		
	
	
	public static ArrayList<String> getNames(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		char start; char end; int firstRow; int secondRow; int startInt; int endInt;
		try{
			start = startCell.charAt(0);
			end = endCell.charAt(0);
			firstRow = Integer.parseInt(startCell.substring(1))-1;
			secondRow = Integer.parseInt(endCell.substring(1))-1;
			startInt = Character.toUpperCase(start) - 65;
			endInt = Character.toUpperCase(end) - 65;
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		if(endInt-startInt>0 && secondRow-firstRow == 1 && startInt >= 0 && startInt <=25 && endInt >= 0 && endInt <=25){
			for(int i = 0; i<=endInt-startInt; i++){
				if(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent() != null){
					res.add(((Cell) sheet.getValueAt(firstRow, startInt + i)).getContent());
				} else{
					throw new CellDataException();
				}
			}
		} else{
			throw new CellInputException();
		}
		
		return res;
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		char start; char end; int firstRow; int secondRow; int startInt; int endInt; Double add;
		try{
			start = startCell.charAt(0);
			end = endCell.charAt(0);
			firstRow = Integer.parseInt(startCell.substring(1))-1;
			secondRow = Integer.parseInt(endCell.substring(1))-1;
			startInt = Character.toUpperCase(start) - 65;
			endInt = Character.toUpperCase(end) - 65;
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<Double> res = new ArrayList<Double>();
		
		if(endInt-startInt>0 && secondRow-firstRow == 1 && startInt>=0 && startInt<=25 && endInt >= 0 && endInt <=25){
		for(int i = 0; i<=endInt-startInt; i++){
				
				try{
					add = Double.parseDouble(((Cell) sheet.getValueAt(secondRow, startInt + i)).toString());
					res.add(add);
				} catch(Exception e){
					throw new CellDataException();
				}
			} 
		} else{
			throw new CellInputException();
		}
		
		return res;
		
		
	}
	
}
