package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the result true if value refers to a logical value; otherwise, it returns false.
 * <p>Syntax: =IsLogical(value)</p> 
 * @author Team Awesome
 */
public class IsLogical extends Formula {
	@Override
	public Boolean getValue(Object... args) throws MathException {
		if(args.length != 1)
			throw new MathException();

		return getBoolean(args[0]);
	}
}
