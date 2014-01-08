package com.awesome.excelpp.math;

public class IsNumber {
	public static boolean getValue(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
