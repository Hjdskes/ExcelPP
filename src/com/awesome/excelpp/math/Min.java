package com.awesome.excelpp.math;

public class Min extends Formula {
	public double getValue(double...numbers) {
		double minimum = numbers[0];
		for(int i=0; i < numbers.length; i++)
			if( minimum > numbers[i]) 
				minimum = numbers[i];
		return minimum;
	}
}
