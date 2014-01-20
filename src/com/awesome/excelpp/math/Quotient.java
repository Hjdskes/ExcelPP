package com.awesome.excelpp.math;

import com.awesome.excelpp.math.exception.MathException;

/**
 * The Quotient function divides all the numbers given as arguments and returns the quotient.
 * <p>For example, if cells A1 and A2 contain numbers,
 * you can use the formula =Quotient(A1, A2) to multiply those two numbers together.
 * You can also perform the same operation by using the multiply (/) mathematical operator; for example, =A1 / A2.
 * </br>Syntax: =Quotient(number1, [number2], ...);</p>
 * @author Team Awesome
 */
public class Quotient extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		double quotient = getDouble(args[0]);

		for(int i = 1; i < args.length; i++) {
			quotient /= getDouble(args[i]);
		}
		return quotient;
	}
}
