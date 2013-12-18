package com.awesome.excelpp.math;

public class Min extends Formula {
	public int getValue(int...numbers) {
		int minimum = 0;
		for(int i=0; i<numbers.length; i++)
			if( minimum < numbers[i]) 
				minimum = numbers[i];
		return minimum;
	}
}
