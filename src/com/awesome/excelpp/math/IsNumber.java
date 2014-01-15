package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class IsNumber {
	public double getValue(String str) {
		double res = 0.0;
		try {
			Double.parseDouble(str);
			res = 1.0;
		} catch(NumberFormatException e) {
			
		}
		return res;
	}
}
