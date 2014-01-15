package com.awesome.excelpp.math;

public class Product extends Formula{
	public double getValue(double...numbers) {
		double product = 0;
		for(int i=0; i < numbers.length; i++) {
			product = product*numbers[i];
		}
	return product;
	}
}