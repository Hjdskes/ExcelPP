package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the largest value in a set of values.
 * @author Team Awesome
 */
public class Max extends Formula {
	public Double getValue(Object ... args) throws MathException {
		double maximum = Double.MIN_VALUE;

		for(Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			else if (!(o instanceof Double))
				throw new MathException();

			if (maximum < (Double)o)
				maximum = (Double)o;
		}
		return maximum;	
	}
}
