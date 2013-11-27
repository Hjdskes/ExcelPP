package com.awesome.excelpp.models;

public class SpreadSheetTest {
	public static void main(String[] args) {
		SpreadSheet sheet = new SpreadSheet();
		System.out.println("Filling sheet...");
		sheet.fillSheet();
		System.out.println("Printing sheet...");
		System.out.println(sheet.toString());
	}
}
