package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns true if all its arguments evaluate to true; returns false if one or more arguments evaluate to false.
 * <p>Syntax: =And(logical1, [logical2], ...);</p>
 * @author Team Awesome
 */
public class And extends Formula {
	@Override
	public Object getValue(Object... args) throws MathException {
		boolean res = true;;
		for (Object o : args) {
			if (getInteger(o) == 0)
				res = false;
		}
		return res;
	}
}
