package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * The add function returns the sum of all its arguments.
 * <p>Syntax: =Sum(number1, [number2], ...)</p>
 * @author Team Awesome
 */
public class Sum extends Formula {
	@Override
	public Double getValue(Object ... args) throws MathException  {
		double sum = 0;
		for (Object o : args) {
			sum += getDouble(o);
		}
		return sum;
	}
}
