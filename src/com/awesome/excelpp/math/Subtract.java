package com.awesome.excelpp.math;

public class Subtract extends Formula {
	public String getValue(String a, String b) {
		return "" + (Integer.parseInt(a) - Integer.parseInt(b));
	}
}
