package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number down to the nearest integer.
 * @author Team Awesome
 */
public class Int extends Formula {
	@Override
	public Integer getValue(Object...args) throws MathException {
		try {
			double temp = 0.0;
			if(args[0] instanceof String) {
				temp = Double.parseDouble((String)args[0]);
			}
			else if(args[0] instanceof Double) {
				temp = (Double) args[0];
			}
		int integer = (int) temp;
		return integer;
		
		} catch(Exception e) {
			throw new MathException();
		}
	}
}
