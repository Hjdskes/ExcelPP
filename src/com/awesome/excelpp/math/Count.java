package com.awesome.excelpp.math;


public class Count extends Formula {
	public double getValue(double... numbers) {
		double count = 0;
		for(int i=0; i < numbers.length; i++) {
			
			count = i;
		}
		return count;
	}
	
}
