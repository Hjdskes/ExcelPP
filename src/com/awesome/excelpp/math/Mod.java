package com.awesome.excelpp.math;

/**
 * Returns the remainder after number is divided by divisor.
 * <p>The result has the same sign as divisor.</p>
 * @author Team Awesome
 */
public class Mod {
	public double getValue(double...numbers) {
		double mod = numbers[0]%numbers[1];
		return mod;
	}
}
