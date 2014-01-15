package com.awesome.excelpp.graph;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

public class LineChart extends JFrame{

	
	private static final long serialVersionUID = 1L;
	
	public LineChart(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values, String mainTitle, String titleX, String titleY){
		super("LineChart");
		DefaultCategoryDataset data = createData(horizontalNames, verticalNames, values);
		JFreeChart  chart= createChart(data, mainTitle, titleX, titleY);
		ChartPanel panel = new ChartPanel(chart);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(panel);
	}
	
	
	private DefaultCategoryDataset createData(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(horizontalNames.size() * verticalNames.size() == values.size()){
			for(int i = 0; i<verticalNames.size(); i++){
				for(int i2 = 0; i2<horizontalNames.size(); i2++){
				res.setValue(values.get(i*horizontalNames.size() + i2), verticalNames.get(i) , horizontalNames.get(i2));
				}
			}
		}
		
		else{
			System.err.println("The arrayLists differ in size");
		}
		
		
		return res;
	}
	
	private JFreeChart createChart(DefaultCategoryDataset data, String mainTitle, String titleX, String titleY){
		JFreeChart chart = ChartFactory.createLineChart(mainTitle,titleX, titleY, data);
		return chart;
	}
	
	public static ArrayList<String> getHorizontalNames(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		char start; char end; int firstRow; int startInt; int endInt;
		try{
			start = startCell.charAt(0);
			end = endCell.charAt(0);
			firstRow = Integer.parseInt(startCell.substring(1))-1;
			startInt = Character.toUpperCase(start) - 65 + 1;
			endInt = Character.toUpperCase(end) - 65;
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		if(endInt-startInt>0 && startInt >= 0 && startInt <=25 && endInt >= 0 && endInt <=25){
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
		System.out.println("H: " + res.toString());
		return res;
	}
	
	public static ArrayList<String> getVerticalNames(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		char start; char end; int firstRow; int startInt; int secondRow;
		try{
			start = startCell.charAt(0);
			end = endCell.charAt(0);
			firstRow = Integer.parseInt(startCell.substring(1));
			secondRow = Integer.parseInt(endCell.substring(1))-1;
			startInt = Character.toUpperCase(start) - 65;

		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		if(startInt >= 0 && startInt <=25){
			for(int i = 0; i<=secondRow-firstRow; i++){
				if(((Cell) sheet.getValueAt(firstRow+i, startInt)).getContent() != null){
					res.add(((Cell) sheet.getValueAt(firstRow + i, startInt)).getContent());
				} else{
					throw new CellDataException();
				}
			}
		} else{
			throw new CellInputException();
		}
		System.out.println("V: " + res.toString());
		return res;
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, String startCell, String endCell) throws CellInputException, CellDataException{
		char start; char end; int firstRow; int secondRow; int startInt; int endInt; Double add; int horizontalLength; int verticalLength;
		try{
			start = startCell.charAt(0);
			end = endCell.charAt(0);
			firstRow = Integer.parseInt(startCell.substring(1));
			secondRow = Integer.parseInt(endCell.substring(1))-1;
			startInt = Character.toUpperCase(start) - 65+1;
			endInt = Character.toUpperCase(end) - 65+1;
			horizontalLength = endInt-startInt;
			verticalLength = secondRow-firstRow;
			System.out.println("startInt: " + startInt);
			System.out.println("endInt: " + endInt);
			System.out.println("horizontalLength: " + horizontalLength);
			System.out.println("verticalLength: " + verticalLength);
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<Double> res = new ArrayList<Double>();
		
		if(endInt-startInt>0 && startInt>=0 && startInt<=25 && endInt >= 0 && endInt <=25){
		for(int i = 0; i<=verticalLength; i++){
			for(int i2 = 0; i2<horizontalLength; i2++){
				try{
					add = Double.parseDouble(((Cell) sheet.getValueAt(firstRow+i, startInt+i2)).toString());
					res.add(add);
				} catch(Exception e){
					throw new CellDataException();
				}
			}
			} 
		} else{
			throw new CellInputException();
		}
		System.out.println("Va: " + res.toString());
		return res;
		
		
	}
}
