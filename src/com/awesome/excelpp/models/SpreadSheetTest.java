package com.awesome.excelpp.models;

public class SpreadSheetTest {
	
	public static void main(String[] args) {
		SpreadSheet hashSheet = new SpreadSheet();
		hashSheet.fillSheet();
		System.out.println(hashSheet);
		hashSheet.insertRow(4);
		System.out.println(hashSheet);
	}

}
