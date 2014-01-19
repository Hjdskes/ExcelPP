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
	
	public LineChart(SpreadSheet sheet, int[] rows, int[] columns, String mainTitle, String titleX, String titleY) throws CellInputException, CellDataException{
		super("LineChart");
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		PieChart.validate(rows);
		PieChart.validate(columns);
		
		int startRow = rows[0];
		int endRow = rows[rows.length-1];
		if(endRow-startRow>1){
			ArrayList<String> horizontalNames = getHorizontalNames(sheet, rows, columns);
			ArrayList<String> verticalNames = getVerticalNames(sheet, rows, columns);
			ArrayList<Double> values = getValuesXRowsXColumns(sheet, rows, columns);
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
	
	
	static DefaultCategoryDataset createData(ArrayList<String> horizontalNames, ArrayList<String> verticalNames, ArrayList<Double> values){
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
	
	public static DefaultCategoryDataset createData(ArrayList<String> names, ArrayList<Double> values){
		DefaultCategoryDataset res = new DefaultCategoryDataset();
		if(names.size() == values.size()){
			for(int i = 0; i<names.size(); i++){
				res.setValue(values.get(i),"f(x)", names.get(i));
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
	
	public static ArrayList<String> getHorizontalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int firstRow; int startInt; int endInt;
		try{
			firstRow = rows[0];
			startInt = columns[0];
			endInt = columns[columns.length-1];
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		if(endInt-startInt>0 && startInt >= 0 && endInt >= 0){
			for(int i = 0; i<endInt-startInt; i++){
				if(((Cell) sheet.getValueAt(firstRow, startInt + 1 + i)).getContent() != null){
					res.add(((Cell) sheet.getValueAt(firstRow, startInt + 1 + i)).getContent());
				} else{
					throw new CellDataException();
				}
			}
		} else{
			throw new CellInputException();
		}
		return res;
	}
	
	public static ArrayList<String> getVerticalNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int firstRow; int startInt; int secondRow;
		try{
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt = columns[0];

		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<String> res = new ArrayList<String>();
		
		if(startInt >= 0){
			for(int i = 0; i<secondRow-firstRow; i++){
				if(((Cell) sheet.getValueAt(firstRow+1+i, startInt)).getContent() != null){
					res.add(((Cell) sheet.getValueAt(firstRow + 1 + i, startInt)).getContent());
				} else{
					throw new CellDataException();
				}
			}
		} else{
			throw new CellInputException();
		}
		return res;
	}
	
	public static ArrayList<Double> getValuesXRowsXColumns(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		int firstRow; int secondRow; int startInt; int endInt; Double add; int horizontalLength; int verticalLength;
		try{
			firstRow = rows[0];
			secondRow = rows[rows.length-1];
			startInt = columns[0];
			endInt = columns[columns.length-1];
			horizontalLength = endInt-startInt;
			verticalLength = secondRow-firstRow;
		} catch(Exception e){
			throw new CellInputException();
		}
		
		ArrayList<Double> res = new ArrayList<Double>();
		
		if(endInt-startInt>0 && startInt>=0 && endInt >= 0){
		for(int i = 0; i<verticalLength; i++){
			for(int i2 = 0; i2<horizontalLength; i2++){
				try{
					add = Double.parseDouble(((Cell) sheet.getValueAt(firstRow+ 1 +i, startInt + 1 + i2)).toString());
					res.add(add);
				} catch(Exception e){
					throw new CellDataException();
				}
			}
			} 
		} else{
			throw new CellInputException();
		}
		return res;
		
	}
	
	public static ArrayList<String> getNames(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return PieChart.getNames(sheet, rows, columns);
	}
	
	public static ArrayList<Double> getValues(SpreadSheet sheet, int[] rows, int[] columns) throws CellInputException, CellDataException{
		return PieChart.getValues(sheet, rows, columns);
	}
}
