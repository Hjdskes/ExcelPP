package com.awesome.excelpp.models;

public class SpreadSheetTest {
	
	public static void main(String[] args) {
		String res1 = test(new SpreadSheet());
		
		System.out.println(res1);
		
		String res2 = testInsertRow();
		
		System.out.println(res2);
	}
	
	public static String testInsertRow(){
		SpreadSheet sheet = new SpreadSheet();
		sheet.fillSheet();
		System.out.println(sheet);
		long insertStart = System.currentTimeMillis();
		sheet.insertRow(4);
		long insertEnd = System.currentTimeMillis();
		System.out.println(sheet);
		
		return "Time to insert: " + (insertEnd - insertStart) + " ms";
	}
	
	public static String test(SpreadSheet sheet) {
		System.out.println("Filling sheet...");
		long fillStart = System.currentTimeMillis();
		sheet.fillSheet();
		long fillEnd = System.currentTimeMillis();
		
		
		System.out.println("Printing sheet...");
		long printStart = System.currentTimeMillis();
		System.out.println(sheet.toString());
		long printEnd = System.currentTimeMillis();
		
		return "Time to fill: " + (fillEnd - fillStart) + " ms\n" +
						"Time to print: " + (printEnd - printStart) + " ms";
	}

}