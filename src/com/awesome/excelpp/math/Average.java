package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Average extends Formula {
	public double getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;
		
		for (Object o : args) {
			if (!(o instanceof Double))
				throw new MathException();
			
			sum += (Double)o;
			total++;
		}
		return sum / total;	
	}
}
