package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The add function returns the sum of all its arguments.
 * @author Team Awesome
 */
public class Add extends Formula {
	public Double getValue(Object ... args) throws MathException  {
		double sum = 0;
		
		for (Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			
			if (!(o instanceof Double))
				throw new MathException();
			
			sum += (Double)o;
		}
	        
		return sum;
	}
}
