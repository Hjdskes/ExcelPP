package com.awesome.excelpp.stringmath;


public class Upper extends StringFormula {
	public String getValue(String... vars) {
		String string = vars[0];
		string = string.toUpperCase();
		return string;
	}
}
