package com.awesome.excelpp.math;

public class IsNumber extends Formula {
	public double getValue(Object ... args) {
		double res = 1.0;
		
		for (Object o : args) {
			if (!(o instanceof Double) && !(o instanceof Integer))
				res = 0.0;
		}
		
		return res;
	}
}
