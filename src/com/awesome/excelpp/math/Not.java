package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;


/**
 * Reverses the value of its argument.
 * <p>Use Not when you want to make sure a value is not equal to one particular value.
 * </br>Syntax: =Not(logical)</p>
 * @author Team Awesome
 */
public class Not extends Formula {
	@Override
	public Boolean getValue(Object... args) throws MathException {
		if(args.length != 1)
			throw new MathException();

		boolean x = getBoolean(args[0]);
		return !x;
	}
}	


