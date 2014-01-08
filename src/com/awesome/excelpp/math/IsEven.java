package com.awesome.excelpp.math;


public class IsEven extends Formula {
	public static double getValue(double input) {
		double res = 0.0;
		if (input%2==0)
				res = 1.0;
		return res;
	}

	@Override
	public double getValue(double... numbers) {
		// TODO Auto-generated method stub
		return 0;
	}
}
