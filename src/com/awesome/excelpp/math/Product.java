package com.awesome.excelpp.math;

import com.awesome.excelpp.parser.exception.MathException;

/**
 * The Product function multiplies all the numbers given as arguments and returns the product.
 * <p>For example, if cells A1 and A2 contain numbers,
 * you can use the formula =Product(A1, A2) to multiply those two numbers together.
 * You can also perform the same operation by using the multiply (*) mathematical operator; for example, =A1 * A2.
 * </br>Syntax: =Product(number1, [number2], ...)</p>
 * @author Team Awesome
 */
public class Product extends Formula {
	@Override
	public Double getValue(Object...args) throws MathException {
		double product = getDouble(args[0]);

		for(int i = 1; i < args.length; i++) {
			product *= getDouble(args[i]);
		}
		return product;
	}
}