package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

public class Average extends Formula {
	public String getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;
		
		for (Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			
			if (!(o instanceof Double))
				throw new MathException();
			
			sum += (Double)o;
			total++;
		}
		return String.valueOf(sum / total);	
	}
}
