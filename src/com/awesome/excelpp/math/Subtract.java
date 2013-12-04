package com.awesome.excelpp.math;

public class Subtract extends Formula {
	public int getValue(int ... numbers) {
		int res = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			res -= numbers[i];
		}
		return res;
	}
}
