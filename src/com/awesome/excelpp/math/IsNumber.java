package com.awesome.excelpp.math;

public class IsNumber extends Formula {
	public static double getValue(String str) {
		double res = 0.0;
		try {
			Double.parseDouble(str);
			res = 1.0;
		} catch(NumberFormatException e) {
			//niks doen want res is al 0.0
		}
		return res;
	}

	@Override
	public double getValue(double... numbers) {
		// TODO Auto-generated method stub
		return 0;
	}
}
