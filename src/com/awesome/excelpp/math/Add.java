package com.awesome.excelpp.math;

public class Add extends Formula {
	public int getValue(int ... numbers) {
		int sum = 0;
		for (int i = 0; i < numbers.length; i++)
	        sum += numbers [i];
		return sum;
	}
}
