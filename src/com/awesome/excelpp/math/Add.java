package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Class for the add formula
 *
 */
public class Add extends Formula {
	public double getValue(Object ... args) throws MathException  {
		double sum = 0;
		
		for (Object o : args) {
			if (!(o instanceof Double))
				throw new MathException();
			
			sum += (Double)o;
		}
	        
		return sum;
	}
}
