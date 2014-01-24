package com.awesome.excelpp.junit.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.awesome.excelpp.graph.Utils;
import com.awesome.excelpp.graph.exception.CellDataException;
import com.awesome.excelpp.graph.exception.CellInputException;
import com.awesome.excelpp.models.Cell;
import com.awesome.excelpp.models.SpreadSheet;

public class UtilsTest {

	@Test
	public void testValidate() {
		int[] test1 = {1, 2, 3};
		int[] test2 = {1, 4, 6};
		int[] test3 = {6, 7, 8};
		int[] test4 = {5, 7, 8};
		
		try{
			Utils.validate(test1);
		} catch(CellInputException e){
			assertTrue(false);
		}
		
		try{
			Utils.validate(test2);
			assertTrue(false);
		} catch(CellInputException e){
			assertTrue(true);
		}
		
		try{
			Utils.validate(test3);
		} catch(CellInputException e){
			assertTrue(false);
		}
		
		try{
			Utils.validate(test4);
			assertTrue(false);
		} catch(CellInputException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetNames() throws CellInputException, CellDataException{
		SpreadSheet sheet = new SpreadSheet();
		((Cell) sheet.getValueAt(6, 7)).setContent("A", false);
		((Cell) sheet.getValueAt(6, 8)).setContent("B", false);
		((Cell) sheet.getValueAt(6, 9)).setContent("C", false);
	
		((Cell) sheet.getValueAt(6, 11)).setContent("A", false);
		((Cell) sheet.getValueAt(6, 13)).setContent("C", false);
		
		int[] rowstest1 = {6, 7};
		int[] columnstest1 = {7, 8, 9};
		
		int[] rowstest2 = {6, 7};
		int[] columnstest2 = {11, 12, 13};
		
		ArrayList<String> test1= Utils.getNames(sheet, rowstest1, columnstest1, true);
		ArrayList<String> check1 = new ArrayList<String>();
		check1.add("A");
		check1.add("B");
		check1.add("C");
		
		ArrayList<String> test2 = Utils.getNames(sheet, rowstest1, columnstest1, false);
		ArrayList<String> check2 = new ArrayList<String>();
		check2.add("H");
		check2.add("I");
		check2.add("J");
		
		assertEquals(check1, test1);
		assertEquals(check2, test2);
		
		try{
			Utils.getNames(sheet, rowstest2, columnstest2, true);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetValue() throws CellInputException, CellDataException{
		SpreadSheet sheet = new SpreadSheet();
		((Cell) sheet.getValueAt(6, 7)).setContent("2", false);
		((Cell) sheet.getValueAt(6, 8)).setContent("3", false);
		((Cell) sheet.getValueAt(6, 9)).setContent("5", false);
		
		((Cell) sheet.getValueAt(6, 11)).setContent("2", false);
		((Cell) sheet.getValueAt(6, 13)).setContent("C", false);
		
		int[] rows1 = {5,6};
		int[] rows2 = rows1;
		
		int[] columns1 = {7, 8, 9};
		int[] columns2 = {11, 12, 13};
		
		ArrayList<Double> test1 = Utils.getValues(sheet, rows1, columns1);
		ArrayList<Double> check1 = new ArrayList<Double>();
		check1.add(2.0);
		check1.add(3.0);
		check1.add(5.0);
		
		assertEquals(check1, test1);
		
		try{
			Utils.getValues(sheet, rows2, columns2);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetHorizontalNames() throws CellInputException, CellDataException{
		SpreadSheet sheet = new SpreadSheet();
		((Cell) sheet.getValueAt(6, 7)).setContent("A", false);
		((Cell) sheet.getValueAt(6, 8)).setContent("B", false);
		((Cell) sheet.getValueAt(6, 9)).setContent("C", false);
	
		((Cell) sheet.getValueAt(6, 11)).setContent("A", false);
		((Cell) sheet.getValueAt(6, 13)).setContent("C", false);
		
		int[] rows1 = {6, 7, 8};
		int[] rows2 = rows1;
		
		int[] columns1 = {6, 7, 8, 9};
		int[] columns2 = {10, 11, 12, 13};
		
		ArrayList<String> test1= Utils.getHorizontalNames(sheet, rows1, columns1, true);
		ArrayList<String> check1 = new ArrayList<String>();
		check1.add("A");
		check1.add("B");
		check1.add("C");
		
		ArrayList<String> test2 = Utils.getHorizontalNames(sheet, rows1, columns1, false);
		ArrayList<String> check2 = new ArrayList<String>();
		check2.add("G");
		check2.add("H");
		check2.add("I");
		check2.add("J");
		
		assertEquals(check1, test1);
		assertEquals(check2, test2);
		
		try{
			Utils.getHorizontalNames(sheet, rows2, columns2, true);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetVerticalNames() throws CellInputException, CellDataException{
		SpreadSheet sheet = new SpreadSheet();
		((Cell) sheet.getValueAt(6, 7)).setContent("A", false);
		((Cell) sheet.getValueAt(7, 7)).setContent("B", false);
		((Cell) sheet.getValueAt(8, 7)).setContent("C", false);
	
		((Cell) sheet.getValueAt(6, 11)).setContent("A", false);
		((Cell) sheet.getValueAt(7, 11)).setContent("C", false);
		
		int[] rows1 = {5, 6,7, 8};
		int[] rows2 = {5, 6, 7};
		
		int[] columns1 = {7, 8, 9};
		int[] columns2 = {11, 12, 13};
		
		ArrayList<String> test1= Utils.getVerticalNames(sheet, rows1, columns1, true);
		ArrayList<String> check1 = new ArrayList<String>();
		check1.add("A");
		check1.add("B");
		check1.add("C");
		
		ArrayList<String> test2 = Utils.getVerticalNames(sheet, rows1, columns1, false);
		ArrayList<String> check2 = new ArrayList<String>();
		check2.add("6");
		check2.add("7");
		check2.add("8");
		check2.add("9");
		
		assertEquals(check1, test1);
		assertEquals(check2, test2);
		
		try{
			Utils.getHorizontalNames(sheet, rows2, columns2, true);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testGetValuesXRowsXColumns() throws CellInputException, CellDataException{
		
		SpreadSheet sheet = new SpreadSheet();
		((Cell) sheet.getValueAt(6, 7)).setContent("2", false);
		((Cell) sheet.getValueAt(6, 8)).setContent("3", false);
		((Cell) sheet.getValueAt(6, 9)).setContent("5", false);
		
		((Cell) sheet.getValueAt(6, 7)).setContent("2", false);
		((Cell) sheet.getValueAt(6, 8)).setContent("3", false);
		((Cell) sheet.getValueAt(6, 9)).setContent("5", false);
		((Cell) sheet.getValueAt(7, 7)).setContent("6", false);
		((Cell) sheet.getValueAt(7, 8)).setContent("10", false);
		((Cell) sheet.getValueAt(7, 9)).setContent("15", false);
		
		((Cell) sheet.getValueAt(6, 11)).setContent("2", false);
		((Cell) sheet.getValueAt(6, 12)).setContent("C", false);
		
		((Cell) sheet.getValueAt(7, 11)).setContent("2", false);
		((Cell) sheet.getValueAt(7, 13)).setContent("3", false);
		
		
		int[] rows1 = {5,6};
		int[] rows2 = {6, 7};
		int[] rows3 = {5, 6};
		int[] rows4 = {7};
 		
		int[] columns1 = {6, 7, 8, 9};
		int[] columns2 = {7, 8, 9};
		int[] columns3 = {10, 11, 12};
		int[] columns4 = {11, 12, 13};
		
		ArrayList<Double> test1 = Utils.getValuesXRowsXColumns(sheet, rows1, columns1, true);
		ArrayList<Double> check1 = new ArrayList<Double>();
		check1.add(2.0);
		check1.add(3.0);
		check1.add(5.0);
		assertEquals(check1, test1);
		
		ArrayList<Double> test2 = Utils.getValuesXRowsXColumns(sheet, rows2, columns2, false);
		ArrayList<Double> check2 = new ArrayList<Double>();
		check2.add(2.0);
		check2.add(3.0);
		check2.add(5.0);
		check2.add(6.0);
		check2.add(10.0);
		check2.add(15.0);
		assertEquals(check2, test2);
		
		try{
			Utils.getValuesXRowsXColumns(sheet, rows3, columns3, true);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
		

		try{
			Utils.getValuesXRowsXColumns(sheet, rows4, columns4, false);
			assertTrue(false);
		} catch(CellDataException e){
			assertTrue(true);
		}
		
		}
		
	

}
