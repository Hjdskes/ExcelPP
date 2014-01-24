package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The If function checks to see if a condition you specify is true or false.
 * <p>If true, one thing happens; if false, something else happens.
 * For example, if you use the If function to see if amounts spent are under or over budget,
 * the result for true could be "Within budget" while the result for false could be "Over budget": =If(A2<=100,"Within budget","Over budget")
 * </br>Syntax: =If(condition, value_if_true, value_if_false)</p>
 * @author Team Awesome
 */
public class If extends Formula {
	@Override
	public Object getValue(Object... args) throws MathException {
		if(args.length != 3)
			throw new MathException();

		boolean condition = getBoolean(args[0]);
		if(condition == true)
			return (String) args[1];
		else if(condition == false)
			return (String)args[2];
		return false;
	}
}
