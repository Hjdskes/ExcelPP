package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The If function checks to see if a condition you specify is true or false.
 * <p>If true, one thing happens; if false, something else happens.
 * For example, if you use the If function to see if amounts spent are under or over budget,
 * the result for true could be "within budget" while the result for false could be "Over budget": =If(A2<=100,"Within budget","Over budget");
 * </br>Syntax: =If(condition, value_if_true, value_if_false);</p>
 * @author Team Awesome
 */
public class If extends Formula {

	@Override
	public Object getValue(Object... args) throws MathException {
		String temp = (String) args[0];
		String[] segs = temp.split("<");
		 System.out.println(segs[0] + segs[1]);
		return 1;
	}

}
