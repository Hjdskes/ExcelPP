package com.awesome.excelpp.math;

public class Max {
	public int getValue(int...numbers) {
		int maximum = 0;
		for(int i=0; i < numbers.length; i++) {
			if(maximum < numbers[i])
				maximum = numbers[i];
		}
		return maximum;	
	}
}
