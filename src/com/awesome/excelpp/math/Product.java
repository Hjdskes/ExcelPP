package com.awesome.excelpp.math;

/**
 * The Product function multiplies all the numbers given as arguments and returns the product.
 * <p>For example, if cells A1 and A2 contain numbers,
 * you can use the formula =PRODUCT(A1, A2) to multiply those two numbers together.
 * You can also perform the same operation by using the multiply (*) mathematical operator; for example, =A1 * A2.</p>
 * @author Team Awesome
 */
public class Product extends Formula{
	public double getValue(double...numbers) {
		double product = 0;
		for(int i=0; i < numbers.length; i++) {
			product = product*numbers[i];
		}
	return product;
	}
}