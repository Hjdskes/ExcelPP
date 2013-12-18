package com.awesome.excelpp.math;

public class Count extends Formula {
	public int getValue(int...numbers) {
		int count = 0;
		for(int i=0; i < numbers.length; i++) 
			count = i;
		return count;
	}
	
}
