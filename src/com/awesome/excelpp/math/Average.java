package com.awesome.excelpp.math;

public class Average extends Formula {
	public int getValue(int...numbers) {
		int sum = 0;
		int totalNumbers = 0;
		for(int i=0; i < numbers.length; i++) {
			sum += numbers[i];
			totalNumbers++;
		}
		return sum/totalNumbers;	
	}
}
