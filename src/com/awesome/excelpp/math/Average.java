package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Class for the Average formula
 *
 */
public class Average extends Formula {
	public Double getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;
		for (Object o : args) {
			sum += getNumber(o);
			total++;
		}
		return sum / total;	
	}
}
