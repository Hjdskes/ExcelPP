package com.awesome.excelpp.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.awesome.excelpp.math.exception.MathException;

/**
 * Returns the median of the given numbers.
 * <p>The median is the number in the middle of a set of numbers;
 * that is, half the numbers have values that are greater than the median,
 * and half have values that are less.
 * </br>Syntax: =Median(value1, [value2], ...)</p>
 * @author Team Awesome
 */
public class Median extends Formula {
	@Override
	public Double getValue(Object ... args) throws MathException {
		double median = 0;
		
		List<Double> doubles = new ArrayList<Double>();	
		for(Object o : args) {
			if (!(o instanceof Double))
				throw new MathException();
			
			doubles.add((Double)o);
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
