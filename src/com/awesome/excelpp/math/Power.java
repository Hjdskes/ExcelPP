package com.awesome.excelpp.math;

public class Power extends Formula {
	public double getValue(double ... numbers) {
		return (Math.pow(numbers[0], numbers[1]));
	}
}
