package com.awesome.excelpp.models;

public class SpreadSheetTest {
	public static void main(String[] args) {
		String res1 = test(new SpreadSheet());
		String res2 = test(new SpreadSheetHashMap());
		
		System.out.println(res1);
		System.out.println(res2);
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
		
		String res = "Time to fill: " + (fillEnd - fillStart) + " ms\n" +
						"Time to print: " + (printEnd - printStart) + " ms";
		return res;
	}
}
