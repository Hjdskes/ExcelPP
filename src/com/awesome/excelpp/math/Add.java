package com.awesome.excelpp.math;

/**
 * Class for the add formula
 *
 */
public class Add extends Formula {
	public double getValue(double ... numbers) {
		double sum = 0;
		for (int i = 0; i < numbers.length; i++)
	        sum += numbers [i];
		return sum;
	}
}
