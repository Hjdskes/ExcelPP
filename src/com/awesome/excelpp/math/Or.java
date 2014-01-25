package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns true if any argument is true; returns false if all arguments are false.
 * <p>Syntax: =Or(logical1, [logical2], ...)</p>
 * @author Team Awesome
 */
public class Or extends Formula {
	@Override
	public Boolean getValue(Object... args) throws MathException {
		for(Object o : args) {
			if(getBoolean(o) == true)
				return true;
		}
		return false;
	}
}
