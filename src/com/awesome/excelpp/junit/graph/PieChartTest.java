package com.awesome.excelpp.junit.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.awesome.excelpp.graph.PieChart;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.SpreadSheet;

public class PieChartTest {
	
	SpreadSheet sheet = new SpreadSheet();
	
	@Rule
	  public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetNames() throws CellInputException, CellDataException{
		sheet.setValueAt("A", 7, 6);
		sheet.setValueAt("B", 7, 7);
		sheet.setValueAt("C", 7, 8);
		sheet.setValueAt("5", 8, 6);
		sheet.setValueAt("10", 8, 7);
		sheet.setValueAt("1", 8, 8);
		
		
		ArrayList<String> test1 = PieChart.getNames(sheet, "G8", "I9");
		ArrayList<String> check1 = new ArrayList<String>();
		System.out.println(test1.toString());
		check1.add("A");
		check1.add("B");
		check1.add("C");
		assertEquals(test1, check1);
		
		try{
			PieChart.getNames(sheet, "A1", "C2");
		} catch(CellDataException e){
			assertTrue(true);
		}
		try{	
			PieChart.getNames(sheet, "A1", "C4");
		} catch(CellInputException e){
			assertTrue(true);
		}
		try{	
			PieChart.getNames(sheet, "H1", "A2");
		} catch(CellInputException e){
			assertTrue(true);
		}
		try{	
			PieChart.getNames(sheet, "C6", "H3");
		} catch(CellInputException e){
			assertTrue(true);
		}
		try{	
			PieChart.getNames(sheet, "4C", "H3");
		} catch(CellInputException e){
			assertTrue(true);
		}
		
	}
	
	public void testGetValues() throws CellInputException, CellDataException{
		sheet.setValueAt("A", 7, 6);
		sheet.setValueAt("B", 7, 7);
		sheet.setValueAt("C", 7, 8);
		sheet.setValueAt("5", 8, 6);
		sheet.setValueAt("10", 8, 7);
		sheet.setValueAt("1", 8, 8);
		
		sheet.setValueAt("Test1", 0, 0);
		sheet.setValueAt("Test2", 0, 1);
		sheet.setValueAt("=G8+C2", 1, 0);
		sheet.setValueAt("9", 1, 0);
		sheet.setValueAt("3", 1, 1);
		
		sheet.setValueAt("A", 6, 0);
		sheet.setValueAt("B", 6, 1);
		sheet.setValueAt("6", 7, 0);
		
		sheet.setValueAt("A", 9, 0);
		sheet.setValueAt("B", 9, 1);
		sheet.setValueAt("fout", 10, 0);
		sheet.setValueAt("42", 10, 1);
		
		sheet.setValueAt("A", 19, 0);
		sheet.setValueAt("B", 19, 1);
		sheet.setValueAt("G7+A8", 20, 0);
		sheet.setValueAt("50", 21, 1);
		
		ArrayList<Double> test1 = PieChart.getValues(sheet, "G8", "I9");
		ArrayList<Double> check1 = new ArrayList<Double>();
		check1.add(5.0);
		check1.add(10.0);
		check1.add(1.0);
		
		assertEquals(test1, check1);
		
		ArrayList<Double> test2 = PieChart.getValues(sheet,  "A20", "B21");
		ArrayList<Double> check2 = new ArrayList<Double>();
		check2.add(11.0);
		check2.add(50.0);
		
		assertEquals(test2, check2);
		
		try{
			PieChart.getValues(sheet,"A7", "B8");
		} catch(CellDataException e){
			assertTrue(true);
		}
		
		try{
			PieChart.getValues(sheet,"A10", "B11");
		} catch(CellDataException e){
			assertTrue(true);
		}
		
		try{
			PieChart.getValues(sheet,"7A", "B8");
		} catch(CellInputException e){
			assertTrue(true);
		}
		
		try{
			PieChart.getValues(sheet,"A7", "B6");
		} catch(CellInputException e){
			assertTrue(true);
		}
		
		try{
			PieChart.getValues(sheet,"C7", "B9");
		} catch(CellInputException e){
			assertTrue(true);
		}
		
		try{
			PieChart.getValues(sheet,"7H", "B8");
		} catch(CellInputException e){
			assertTrue(true);
		}
	}

}
