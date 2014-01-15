package com.awesome.excelpp.stringmath;


public class Lower extends StringFormula {
	public String getValue(String... vars) {
		String string = vars[0];
		string = string.toLowerCase();
		return string;
	}
}