package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Rounds a number down to the nearest integer.
 * @author Team Awesome
 */
public class Int extends Formula {
	public Integer getValue(Object...vars) throws MathException {
		try {
			double temp = 0.0;
			if(vars[0] instanceof String) {
				temp = Double.parseDouble((String)vars[0]);
			}
			else if(vars[0] instanceof Double) {
				temp = (Double) vars[0];
			}
		int integer = (int) temp;
		return integer;
		
		} catch(Exception e) {
			throw new MathException();
		}
	}
}
