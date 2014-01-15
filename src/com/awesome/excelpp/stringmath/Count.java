package com.awesome.excelpp.stringmath;

import com.awesome.excelpp.math.Formula;

public class Count extends Formula {
	public double getValue(double... numbers) {
		double count = 0;
		for(int i=0; i < numbers.length; i++) {
			
			count = i;
		}
		return count;
	}
	
}
