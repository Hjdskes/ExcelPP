package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the largest value in a set of values.
 * <p>Syntax: =Max(value1, [value2], ...)</p>
 * @author Team Awesome
 */
public class Max extends Formula {
	@Override
	public Double getValue(Object ... args) throws MathException {
		double maximum = Double.MIN_VALUE;

		for(Object o : args) {
			try {
				if (maximum < getDouble(o))
					maximum = getDouble(o);
			} catch (MathException e) {
			}
		}
		return maximum == Double.MIN_VALUE ? 0 : maximum;	
	}
}
