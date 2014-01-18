package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Max extends Formula {
	public double getValue(Object ... args) throws MathException {
		double maximum = Double.MIN_VALUE;
		
		for(Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			
			if (!(o instanceof Double))
				throw new MathException();
			
			if (maximum < (Double)o)
				maximum = (Double)o;
		}
		return maximum;	
	}
}
