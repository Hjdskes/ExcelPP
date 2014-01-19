package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the smallest number in a set of values.
 * @author Team Awesome
 */
public class Min extends Formula {
	public Double getValue(Object ... args) throws MathException {
		double minimum = Double.MAX_VALUE;

		for(Object o : args) {
			if (o instanceof Integer)
				o = new Double((Integer)o);
			else if (!(o instanceof Double))
				throw new MathException();

			if (minimum > (Double)o)
				minimum = (Double)o;
		}
		return minimum;
	}
}
