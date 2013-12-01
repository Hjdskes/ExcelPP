package com.awesome.excelpp.models;

public class SpreadSheetManualTest {
	
	public static void main(String[] args) {
		String res1 = test();
		System.out.println(res1);
		
		//String res2 = testInsertCol();
		//System.out.println(res2);
		
		String res3 = testFormules();
		System.out.println(res3);
	}
	
	/*
	public static String testInsertCol(){
		SpreadSheet sheet = new SpreadSheet();
		sheet.fillSheet();
		System.out.println(sheet);
		long insertStart = System.currentTimeMillis();
		sheet.insertCol(2);
		long insertEnd = System.currentTimeMillis();
		System.out.println(sheet);
		
		return "Time to insert: " + (insertEnd - insertStart) + " ms";
	}*/
	
	/*
	public static String testInsertRow(){
		SpreadSheet sheet = new SpreadSheet();
		sheet.fillSheet();
		System.out.println(sheet);
		long insertStart = System.currentTimeMillis();
		sheet.insertRow(4);
		long insertEnd = System.currentTimeMillis();
		System.out.println(sheet);
		
		return "Time to insert: " + (insertEnd - insertStart) + " ms\n";
	}*/
	
	public static String test() {
		SpreadSheet sheet = new SpreadSheet();
		System.out.println("Filling sheet...");
		long fillStart = System.currentTimeMillis();
		sheet.fillSheet();
		long fillEnd = System.currentTimeMillis();
		
		
		System.out.println("Printing sheet...");
		long printStart = System.currentTimeMillis();
		System.out.println(sheet.toString());
		long printEnd = System.currentTimeMillis();
		
		return "Time to fill: " + (fillEnd - fillStart) + " ms\n" +
		       "Time to print: " + (printEnd - printStart) + " ms\n";
	}

	public static String testFormules(){
		SpreadSheet sheet = new SpreadSheet();
		System.out.println("Filling sheet...");
		long fillStart = System.currentTimeMillis();
		sheet.fillSheetFormula();
		long fillEnd = System.currentTimeMillis();
		
		
		System.out.println("Printing sheet...");
		long printStart = System.currentTimeMillis();
		System.out.println(sheet.toString());
		long printEnd = System.currentTimeMillis();
		
		return "Time to fill: " + (fillEnd - fillStart) + " ms\n" +
		       "Time to print: " + (printEnd - printStart) + " ms\n";
	}
}
