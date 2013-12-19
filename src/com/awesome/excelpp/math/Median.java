package com.awesome.excelpp.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Median extends Formula {
	public double getValue(double...numbers) {
		double median = 0;
		List<Double> doubles = new ArrayList<Double>();	
		for(double s : numbers) {
			doubles.add(s);
		}
		Collections.sort(doubles);
		if(doubles.size() % 2 == 0) {
			double maxmedian = 0;
			double minmedian = 0;
			maxmedian = doubles.get(doubles.size()/2);
			minmedian = doubles.get(doubles.size()/2-1);
			median = (maxmedian + minmedian)/2;
		} else {
			median = doubles.get(doubles.size()/2);
		}
		return median;
	}
}
