package com.awesome.excelpp.math;

/**
 * Returns the logical value true if value is a number; otherwise, it returns false.
 * @author Team Awesome
 */
public class IsNumber extends Formula {
	@Override
	public Boolean getValue(Object ... args) {
		boolean res = true;
		for (Object o : args) {
			if (!(o instanceof Double) && !(o instanceof Integer))
				res = false;
		}
		return res;
	}
}
