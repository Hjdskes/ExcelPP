package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the average (arithmetic mean) of the arguments.
 * <p>For example, if the range A1:A20 contains numbers, the formula =Average(A1:A20)
 * returns the average of those numbers.
 * </br>Syntax: =Average(number1, [number2], ...);</p>
 * @author Team Awesome
 */
public class Average extends Formula {
	@Override
	public Double getValue(Object ... args) throws MathException {
		double sum = 0;
		int total = 0;
		for (Object o : args) {
			sum += getDouble(o);
			total++;
		}
		return sum / total;	
	}
}
