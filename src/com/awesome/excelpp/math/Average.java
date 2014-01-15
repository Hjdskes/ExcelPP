package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Average extends Formula {
	public double getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;
		
		for (int i = 0; i < args.length; i++) {
			if (!(args[i] instanceof Double))
				throw new MathException();
			
			sum += (Double)args[i];
			total++;
		}
		return sum / total;	
	}
}
