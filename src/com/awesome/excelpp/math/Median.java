package com.awesome.excelpp.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Median extends Formula {
	public int getValue(int...numbers) {
		int median = 0;
		List<Integer> integers = new ArrayList<Integer>();	
		for(int s : numbers) {
			integers.add(s);
		}
		Collections.sort(integers);
		if(integers.size() % 2 == 0) {
			int maxmedian = 0;
			int minmedian = 0;
			maxmedian = integers.get(integers.size()/2);
			minmedian = integers.get(integers.size()/2-1);
			median = (maxmedian + minmedian)/2;
		} else {
			median = integers.get(integers.size()/2);
		}
		return median;
	}
}
