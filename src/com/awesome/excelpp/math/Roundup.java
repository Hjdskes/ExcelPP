package com.awesome.excelpp.math;

/**
 * Rounds a number up, away from 0 (zero).
 * @author Team Awesome
 */
public class Roundup {
	public double getValue(double...numbers) {
		double up = Math.ceil(numbers[0]);
		return up;
	}
}
