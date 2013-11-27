package com.awesome.excelpp.models;

public class Cel {
	String contents; // =2+2
	
	public Cel() {
		this.contents = "";
	}
	
	public String getFormula() {
		return contents;
	}
	
	public String getValue() {
		return contents; // 4
	}
}
