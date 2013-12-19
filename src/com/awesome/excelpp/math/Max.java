package com.awesome.excelpp.math;

public class Max extends Formula {
	public double getValue(double...numbers) {
		double maximum = 0;
		for(int i=0; i < numbers.length; i++) {
			if(maximum < numbers[i])
				maximum = numbers[i];
		}
		return maximum;	
	}
}
