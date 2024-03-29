package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * Returns the logical value true if value is a number; otherwise, it returns false.
 * <p>Syntax: =IsNumber(value)</p>
 * @author Team Awesome
 */
public class IsNumber extends Formula {
	@Override
	public Boolean getValue(Object ... args) throws MathException {
		if (args.length != 1)
			throw new MathException();

		boolean res = true;

		if(args[0] instanceof String) {
			try {
				Double.parseDouble((String)args[0]);
			} catch(Exception e) {
				res = false;
			}
		} else if (!(args[0] instanceof Double) && !(args[0] instanceof Integer))
			res = false;

		return res;
	}
}
