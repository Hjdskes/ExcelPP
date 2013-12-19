package com.awesome.excelpp.math;

/**
 * Class for the substract formula
 *
 */
public class Subtract extends Formula {
	public double getValue(double ... numbers) {
		double res = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			res -= numbers[i];
		}
		return res;
	}
}
